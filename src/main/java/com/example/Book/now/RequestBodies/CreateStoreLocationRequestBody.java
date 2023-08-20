package com.example.Book.now.RequestBodies;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStoreLocationRequestBody {

    private Integer storeId;
    private String storeName;
    private String country;
    private String state;
    private String city;
    private String fullAddress;

}
