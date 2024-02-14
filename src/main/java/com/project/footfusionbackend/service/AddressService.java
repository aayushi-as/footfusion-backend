package com.project.footfusionbackend.service;

import com.project.footfusionbackend.model.Address;
import com.project.footfusionbackend.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public void addAddress(Address address) {
        addressRepository.save(address);
    }

    public List<Address> getAllAddressByUserId(Long userId) {
        return addressRepository.findByUserUserId(userId);
    }

    public Address getAddressByAddressId(Long userId, Long addressId) {
        return addressRepository.findByAddressId(addressId);
   }

    public void deleteAddress(Long id, Long addressId) {
        Address address = addressRepository.findByAddressId(addressId);
        addressRepository.delete(address);
    }
}
