package com.kai.Service;

import com.kai.Model.Car;
import com.kai.DTO.CarDTO;
import com.kai.Repository.CarRepository;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void save(CarDTO carDTO) {
        Car car = new Car();
        car.setMake(carDTO.getMake());
        car.setYear(carDTO.getYear());
        car.setLicensePlateNumber(carDTO.getLicensePlateNumber());
        car.setColor(carDTO.getColor());

        carRepository.save(car);
    }
}