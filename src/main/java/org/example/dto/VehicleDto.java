package org.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.BooleanToShortConventer;
import org.example.model.User;
import org.example.model.Vehicle;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDto {
    private String brand;
    private String model;
    private int year;
    private double price;
    private String plate;

    private boolean rent;
    private String category;

}