package org.example.parkinglot.ejb;

import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.example.parkinglot.entities.Car;
import org.example.parkinglot.entities.User;
import org.parkinglot.parkinglot.common.CarDto;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class CarsBean {

    private static final Logger LOG = Logger.getLogger(CarsBean.class.getName());

    @PersistenceContext
    EntityManager enityManager;

    public List<CarDto> findAllCars(){
        LOG.info("Finding all cars");
        try {
            TypedQuery<Car>typedQuery = enityManager.createQuery("SELECT c FROM Car c", Car.class);
            List<Car> cars = typedQuery.getResultList();
            return copyCarsToDto(cars);
        }catch (Exception ex){
            throw new EJBException(ex);
        }
    }

    private List<CarDto> copyCarsToDto(List<Car> cars) {
        return cars.stream()
                .map(car -> new CarDto(
                        car.getId(),
                        car.getLicensePlate(),
                        car.getParkingSpot(),
                        car.getOwner().getUsername()

                ))
                .toList();
    }
    public void createCar(String licensePlate,String parkingSpot,Long useId){
        LOG.info("createCar");

        Car car = new Car();
        car.setLicensePlate(licensePlate);
        car.setParkingSpot(parkingSpot);

        User user=enityManager.find(User.class, useId);
        car.setOwner(user);
        user.getCars().add(car);

        enityManager.persist(car);
    }

    public CarDto findById(Long cardId) {
        LOG.info("findById: "+cardId);
        Car car=enityManager.find(Car.class, cardId);
        if(car==null){
            return null;
        }
        return new CarDto(
                car.getId(),
                car.getLicensePlate(),
                car.getParkingSpot(),
                car.getOwner().getUsername()
        );
    }

    public void updateCar(Long carId, String licensePlate, String parkingSpot, Long userId) {
        LOG.info("updateCar");

        Car car=enityManager.find(Car.class, carId);
        car.setLicensePlate(licensePlate);
        car.setParkingSpot(parkingSpot);
        //remove from old user
        User oldUSer=car.getOwner();
        oldUSer.getCars().remove(car);

        //set new user
        User user=enityManager.find(User.class, userId);
        car.setOwner(user);
        user.getCars().add(car);
    }

    public void deleteCarsByIds(Collection<Long> carIds) {
        LOG.info("deleteCarsByIds");
        for(Long carId:carIds){
            Car car=enityManager.find(Car.class, carId);
            enityManager.remove(car);
        }
    }
}
