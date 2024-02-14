package com.project.footfusionbackend.repository;

import com.project.footfusionbackend.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByUserUserId(Long id);

    Address findByAddressId(Long id);
}
