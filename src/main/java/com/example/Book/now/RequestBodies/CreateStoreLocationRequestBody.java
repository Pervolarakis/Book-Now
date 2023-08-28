package com.example.Book.now.RequestBodies;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStoreLocationRequestBody {

    @NotBlank(message = "Please provide a store name")
    private String storeName;
    @NotBlank(message = "Please provide a country")
    private String country;
    @NotBlank(message = "Please provide a state")
    private String state;
    @NotBlank(message = "Please provide a city")
    private String city;
    @NotBlank(message = "Please provide a full address")
    private String fullAddress;

}
