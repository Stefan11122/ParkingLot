package org.example.parkinglot.ejb;

import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.example.parkinglot.entities.Car;
import org.parkinglot.parkinglot.common.CarDto;

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
}