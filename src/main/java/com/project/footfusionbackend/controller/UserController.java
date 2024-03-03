package com.project.footfusionbackend.controller;


import com.project.footfusionbackend.model.Address;
import com.project.footfusionbackend.model.User;
import com.project.footfusionbackend.service.AddressService;
import com.project.footfusionbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    // Only for development purpose
    @GetMapping("/fetch")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}/view")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User newUser = userService.getUserById(id);
        newUser.setContactNo(user.getContactNo());
        newUser.setEmailId(user.getEmailId());
        newUser.setFullName(user.getFullName());

        userService.addUser(newUser);
        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/{id}/address/create")
    public ResponseEntity<Address> createAddress(@PathVariable Long id, @RequestBody Address address) {
        User user = userService.getUserById(id);
        address.setUser(user);

        addressService.addAddress(address);
        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }
    @GetMapping("/{id}/address/viewall")
    public ResponseEntity<List<Address>> getAllAddress(@PathVariable Long id) {
        List<Address> addresses = addressService.getAllAddressByUserId(id);
        return ResponseEntity.ok(addresses);
    }

    @GetMapping("/{id}/address/{address_id}/view")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id, @PathVariable Long address_id) {
        Address address = addressService.getAddressByAddressId(id, address_id);
        return ResponseEntity.ok(address);
    }

    @PutMapping("/{id}/address/{address_id}/update")
    public ResponseEntity<Address> updateAddressById(@PathVariable Long id, @PathVariable Long address_id, @RequestBody Address address) {
        Address newAddress = addressService.getAddressByAddressId(id, address_id);

        newAddress.setBlockNo(address.getBlockNo());
        newAddress.setDescription(address.getDescription());
        newAddress.setCity(address.getCity());
        newAddress.setState(address.getState());
        newAddress.setCountry(address.getCountry());
        newAddress.setZipcode(address.getZipcode());

        addressService.addAddress(newAddress);

        return ResponseEntity.ok(newAddress);
    }

    @DeleteMapping("/{id}/address/{address_id}/delete")
    public ResponseEntity<String> deleteAddress(@PathVariable Long id, @PathVariable Long address_id) {
        addressService.deleteAddress(id, address_id);
        return ResponseEntity.ok("Deleted Successfully!!");
    }

}
