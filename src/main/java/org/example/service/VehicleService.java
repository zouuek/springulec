package org.example.service;

import org.example.dao.IVehicleRepository;
import org.example.dto.UserDto;
import org.example.dto.VehicleDto;
import org.example.model.Car;
import org.example.model.Motorcycle;
import org.example.model.User;
import org.example.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class VehicleService {
    private IVehicleRepository vehicleRepository;
    @Autowired
    public VehicleService(IVehicleRepository vehicleRepository){
        this.vehicleRepository = vehicleRepository;
    }
    public VehicleDto getVehicle(String plate){
        Vehicle vehicle = vehicleRepository.getVehicle(plate);

        if (vehicle != null && vehicle.getClass().getSimpleName().equals("Motorcycle"))
            return new VehicleDto(vehicle.getBrand(),vehicle.getModel(),vehicle.getYear(), vehicle.getPrice(), vehicle.getPlate(), vehicle.isRent(), ((Motorcycle) vehicle).getCategory());
        else if (vehicle != null)
            return new VehicleDto(vehicle.getBrand(),vehicle.getModel(),vehicle.getYear(), vehicle.getPrice(), vehicle.getPlate(), vehicle.isRent(), null);
        else
            return null;
    }

    public boolean addVehicle(VehicleDto vehicleDto){
        Vehicle newVehicle;
        if(vehicleDto.getCategory() == null){
        newVehicle = new Car();
        newVehicle.setBrand(vehicleDto.getBrand());
        newVehicle.setModel(vehicleDto.getModel());
        newVehicle.setPlate(vehicleDto.getPlate());
        newVehicle.setYear(vehicleDto.getYear());
        newVehicle.setPrice(vehicleDto.getPrice());
        }
        else {
            newVehicle = new Motorcycle();
            newVehicle.setBrand(vehicleDto.getBrand());
            newVehicle.setModel(vehicleDto.getModel());
            newVehicle.setPlate(vehicleDto.getPlate());
            newVehicle.setYear(vehicleDto.getYear());
            newVehicle.setPrice(vehicleDto.getPrice());
            ((Motorcycle) newVehicle).setCategory(vehicleDto.getCategory());
        }
        if (getVehicle(newVehicle.getPlate()) != null){
            return false;
        }
        else vehicleRepository.addVehicle(newVehicle);
        return true;

    }
    public String removeVehicle(String plate){
        Vehicle vehicle = vehicleRepository.getVehicle(plate);
        if (vehicle!=null && !vehicle.isRent()){
            vehicleRepository.removeVehicle(plate);
            return "deleted";
        }
        else if (vehicle == null){
            return "not found";
        }
        else if (vehicle.isRent()){
            return "in use";
        }
        else return  "unexpected";
    }

    public Collection<VehicleDto> getVehicles(){
        Collection<VehicleDto> vehicleDtos = new ArrayList<>();
        Collection<Vehicle> vehicles = vehicleRepository.getVehicles();
        VehicleDto vehicleDto;
        for (Vehicle vehicle : vehicles){
            if(vehicle.getClass().getSimpleName().equals("Car")){
                 vehicleDto= new VehicleDto(vehicle.getBrand(),vehicle.getModel(),vehicle.getYear(),vehicle.getPrice(),vehicle.getPlate(),vehicle.isRent(), null);}
            else { vehicleDto= new VehicleDto(vehicle.getBrand(),vehicle.getModel(),vehicle.getYear(),vehicle.getPrice(),vehicle.getPlate(),vehicle.isRent(), ((Motorcycle) vehicle).getCategory());}
            vehicleDtos.add(vehicleDto);
        }
        return vehicleDtos;
    }
}
