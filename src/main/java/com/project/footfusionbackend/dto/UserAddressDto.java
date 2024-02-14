package com.project.footfusionbackend.dto;

import com.project.footfusionbackend.model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAddressDto {
    private Long userId;
    private String fullName;
    private String contactNo;
    private String emailId;
    private List<Address> address;
}
