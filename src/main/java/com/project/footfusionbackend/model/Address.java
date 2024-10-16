package com.project.footfusionbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @ManyToOne
    @JoinColumn(name="userId", nullable=false)
    @JsonIgnore
    private User user;

    @NotBlank(message = "Please enter block number")
    private String blockNo;
    private String description;

    @NotBlank(message = "Please enter city")
    private String city;

    @NotBlank(message = "Please enter state")
    private String state;

    @NotBlank(message = "Please enter country")
    private String country;

    @NotBlank(message = "Please enter zipcode")
    private String zipcode;

    @Column(nullable = false)
    @Pattern(regexp = "^\\d{10}$",message = "Mobile number entered is invalid")
    private String contactNo;
}
