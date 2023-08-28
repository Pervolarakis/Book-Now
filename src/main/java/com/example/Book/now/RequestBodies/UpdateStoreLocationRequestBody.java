package com.example.Book.now.RequestBodies;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStoreLocationRequestBody {
    private String storeName;
    private String country;
    private String state;
    private String city;
    private String fullAddress;
}
