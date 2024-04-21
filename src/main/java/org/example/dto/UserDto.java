package org.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.model.User;
import org.example.model.Vehicle;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//todo: przy zwracaniu dodac informacje na temat wypozyczonego pojazdu
public class UserDto {
    private String login;
    private User.Role role;
    private Vehicle vehicle;
}