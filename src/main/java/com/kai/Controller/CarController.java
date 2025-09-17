package com.kai.Controller;

import com.kai.DTO.CarDTO;
import com.kai.Exception.ResourceNotFoundException;
import com.kai.Repository.CarRepository;
import com.kai.Model.Car;
import com.kai.Service.CarService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CarController {

    private final CarService carService;
    CarRepository carRepository;

    public CarController(CarRepository carRepository, CarService carService) {
        this.carRepository = carRepository;
        this.carService = carService;
    }

    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "") String search, HttpSession session, Model model) {
        List<Car> cars;

        if (search.isEmpty()){
            cars = carRepository.findAll();// ✅ only keep this
        }else{
            cars = carRepository.findByMakeContainingIgnoreCaseOrLicensePlateNumberContainingIgnoreCaseOrColorContainingIgnoreCaseOrBodyTypeContainingIgnoreCaseOrEngineTypeContainingIgnoreCaseOrTransmissionContainingIgnoreCase(search,search,search, search, search, search);
        }

        model.addAttribute("cars", cars);
        model.addAttribute("search", search);
        cars.forEach(car -> {
            System.out.println(car.getMake());
        });
        return "index";
    }

    @GetMapping("/delete")
    public String deleteCar(@RequestParam int id, HttpSession session) {

        carRepository.deleteById(id);
        return "redirect:/";
    }
    @GetMapping("/new")
    public String add(Model model, HttpSession session) {

        CarDTO carDTO = new CarDTO();
        model.addAttribute("car", carDTO);
        model.addAttribute("activeMenu", "new");
        model.addAttribute("body", new String[]{"Sedan", "SUV", "Hatchback", "Pickup", "Coupe", "Convertible"});
        model.addAttribute("types", new String[]{"Gasoline", "Diesel", "Electric", "Hybrid"});
        model.addAttribute("sizes", new String[]{"Automatic", "Manual"});
        return "new";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("car") @Valid CarDTO carDTO, BindingResult bindingResult, HttpSession session, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("car", carDTO);
            model.addAttribute("body", new String[]{"Sedan", "SUV", "Hatchback", "Pickup", "Coupe", "Convertible"});
            model.addAttribute("types", new String[]{"Gasoline", "Diesel", "Electric", "Hybrid"});
            model.addAttribute("sizes", new String[]{"Automatic", "Manual"});
            return "new";
        }

        Car car = new Car();
        car.setMake(carDTO.getMake());
        car.setYear(carDTO.getYear());
        car.setLicensePlateNumber(carDTO.getLicensePlateNumber());
        car.setColor(carDTO.getColor());
        car.setBodyType(carDTO.getBodyType());
        car.setEngineType(carDTO.getEngineType());
        car.setTransmission(carDTO.getTransmission());
        carRepository.save(car);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam int id, Model model, HttpSession session) {

        Car c = carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));

        CarDTO carDTO = new CarDTO();
        carDTO.setId(c.getId());
        carDTO.setMake(c.getMake());
        carDTO.setYear(c.getYear());
        carDTO.setLicensePlateNumber(c.getLicensePlateNumber());
        carDTO.setColor(c.getColor());
        carDTO.setBodyType(c.getBodyType());
        carDTO.setEngineType(c.getEngineType());
        carDTO.setTransmission(c.getTransmission());

        model.addAttribute("car", carDTO);
        model.addAttribute("body", new String[]{"Sedan", "SUV", "Hatchback", "Pickup", "Coupe", "Convertible"});
        model.addAttribute("types", new String[]{"Gasoline", "Diesel", "Electric", "Hybrid"});
        model.addAttribute("sizes", new String[]{"Automatic", "Manual"});
        return "edit";
    }


    @PostMapping("/update")
    public String update(@ModelAttribute("car") @Valid CarDTO carDTO, BindingResult bindingResult, HttpSession session, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("car", carDTO);
            model.addAttribute("body", new String[]{"Sedan", "SUV", "Hatchback", "Pickup", "Coupe", "Convertible"});
            model.addAttribute("types", new String[]{"Gasoline", "Diesel", "Electric", "Hybrid"});
            model.addAttribute("sizes", new String[]{"Automatic", "Manual"});
            return "edit";
        }

        Car car = carRepository.findById(carDTO.getId())
                .orElseThrow(() -> new RuntimeException("Car not found"));
        car.setMake(carDTO.getMake());
        car.setYear(carDTO.getYear());
        car.setLicensePlateNumber(carDTO.getLicensePlateNumber());
        car.setColor(carDTO.getColor());
        car.setBodyType(carDTO.getBodyType());
        car.setEngineType(carDTO.getEngineType());
        car.setTransmission(carDTO.getTransmission());

        carRepository.save(car);
        return "redirect:/";
    }
}