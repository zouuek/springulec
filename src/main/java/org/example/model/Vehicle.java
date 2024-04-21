package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.example.BooleanToShortConventer;

@Entity
@Table(name = "tvehicle")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "vehicle_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Vehicle {
    private String brand;
    private String model;
    private int year;
    @Column(columnDefinition = "numeric")
    private double price;
    @Id
    private String plate;
    @Convert(converter = BooleanToShortConventer.class)
    private boolean rent;
    @JsonIgnore
    @OneToOne(mappedBy = "vehicle", fetch = FetchType.EAGER)
    private User user;

    public Vehicle(String brand, String model, int year, double price, String plate) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        this.plate = plate;
        this.rent = false;
        //this.user = null; //redundant

    }
    public Vehicle(String brand, String model, int year, double price, String plate,boolean rent) {
        this(brand,model,year,price,plate);
        this.rent = rent;
    }

    public Vehicle() {
        //IT IS MANDATORY !!
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", plate='" + plate + '\'' +
                ", rent=" + rent +
                '}';
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public boolean isRent() {
        return rent;
    }

    public void setRent(boolean rent) {
        this.rent = rent;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String toCSV() {
        return new StringBuilder()
                .append(this.getClass().getSimpleName())
                .append(";")
                .append(this.brand)
                .append(";")
                .append(this.model)
                .append(";")
                .append(this.year)
                .append(";")
                .append(this.price)
                .append(";")
                .append(this.plate)
                .append(";")
                .append(this.rent)
                .toString();
    }
}
