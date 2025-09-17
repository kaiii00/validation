package com.kai.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NotBlank(message = "Make cannot be empty")
    @Size(min= 2,max = 50)
    String make;

    @Min(1886)
    @Max(2025)
    int year;

    @NotBlank(message = "License Plate cannot be empty")
    String licensePlateNumber;

    @NotBlank(message = "Pick a color")
    String color;

    String bodyType;
    String engineType;
    String transmission;

    public Car(Integer id, String make, int year, String licensePlateNumber, String color, String bodyType, String engineType, String transmission) {
        this.id = id;
        this.make = make;
        this.year = year;
        this.licensePlateNumber = licensePlateNumber;
        this.color = color;
        this.bodyType = bodyType;
        this.engineType = engineType;
        this.transmission = transmission;
    }

    public Car(){}

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {this.id = id;}
    public String getMake() {
        return make;
    }
    public void setMake(String make) {
        this.make = make;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }
    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getBodyType() {
        return bodyType;
    }
    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }
    public String getEngineType() {
        return engineType;
    }
    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }
    public String getTransmission() {
        return transmission;
    }
    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

}