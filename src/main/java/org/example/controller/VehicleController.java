package org.example.controller;

import org.example.dto.CreateUserDto;
import org.example.dto.UserDto;
import org.example.dto.VehicleDto;
import org.example.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @GetMapping("{plate}")
    public ResponseEntity<VehicleDto> getVehicle(@PathVariable String plate){
        VehicleDto vehicleDto = vehicleService.getVehicle(plate);
        if(vehicleDto != null){
            return ResponseEntity.ok(vehicleDto);
        }
        else return ResponseEntity.notFound().build();
    }
    @PostMapping("/add")
    public ResponseEntity<String> addVehicle(@RequestBody VehicleDto vehicleDto) {

        if(vehicleService.getVehicle(vehicleDto.getPlate()) == null){
            vehicleService.addVehicle(vehicleDto);
            return ResponseEntity.ok(("Vehicle created successfully"));}
        else return ResponseEntity.badRequest().body("Vehicle with these plates already exists");
    }
    @DeleteMapping("{plate}")
    public ResponseEntity<String> removeVehicle(@PathVariable String plate){
        return switch (vehicleService.removeVehicle(plate)) {
            case "deleted" -> ResponseEntity.ok().body("Vehicle has been deleted");
            case "not found" -> ResponseEntity.notFound().build();
            case "in use" -> ResponseEntity.badRequest().body("Can not delete rented vehicle!");
            default -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        };
    }
    @RequestMapping("/all")
    public ResponseEntity<Collection<VehicleDto>> getVehicles(){
        Collection<VehicleDto> users = vehicleService.getVehicles();
        return ResponseEntity.ok(users);
    }

}
