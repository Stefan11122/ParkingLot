package org.example.parkinglot.servlets;

import jakarta.inject.Inject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.parkinglot.parkinglot.common.CarPhotoDto;
import org.example.parkinglot.ejb.CarsBean;

import java.io.IOException;

@WebServlet(name = "CarPhotos", value = "/CarPhotos")
public class CarPhotos extends HttpServlet {

    @Inject
    CarsBean carsBean;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer carId = Integer.parseInt(request.getParameter("id"));
        CarPhotoDto photo = carsBean.findPhotoByCarId(Long.valueOf(carId));
        if (photo != null) {
            response.setContentType(photo.getFileType());
            response.setContentLength(photo.getFileContent().length);
            response.getOutputStream().write(photo.getFileContent());
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // Error 404
        }
    }
}