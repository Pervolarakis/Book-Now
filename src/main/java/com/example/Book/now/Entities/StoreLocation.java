package com.example.Book.now.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class StoreLocation {

    @Id
    private Integer storeId;
    private String storeName;
    private String country;
    private String state;
    private String city;
    private String fullAddress;

}
