package com.example.company.Service;

import com.example.company.Controller.Result;
import com.example.company.Dto.AddressDto;
import com.example.company.Entity.Address;
import com.example.company.Repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    public List<Address> getAddress() {
        return addressRepository.findAll();
    }

    public Result postAddress(AddressDto addressDto) {
        try {
            if (addressRepository.existsByHomeNumberOrStreet(addressDto.getHomeNumber(), addressDto.getStreet())) {
                return new Result("Bunday address mavjud", false);
            }
            Address address = new Address();
            address.setStreet(addressDto.getStreet());
            address.setHomeNumber(addressDto.getHomeNumber());
            addressRepository.save(address);
            return new Result("Qoshildi", true);
        } catch (
                Exception e) {
            return new Result("Qoshilmadi", false);
        }
    }

    public Result putAddress(Integer id, AddressDto addressDto) {
        try {
            Optional<Address> byId = addressRepository.findById(id);
            if (byId.isPresent()) {
                Address address = byId.get();
                address.setStreet(addressDto.getStreet());
                address.setHomeNumber(addressDto.getHomeNumber());
                addressRepository.save(address);
                return new Result("Ozgardi", false);
            }
            return new Result("bunay address topilmadi", false);
        } catch (Exception e) {
            return new Result("bunay address topilmadi", false);
        }
    }

    public Result deleteAddress(Integer id) {
        try {
            if (addressRepository.findById(id).isPresent()) {
                addressRepository.deleteById(id);
                return new Result("Ochirildi", true);
            }
            return new Result("Bunday address topilmadi", false);
        } catch (Exception e) {
            return new Result("Bunday address topilmadi", false);
        }
    }

}
