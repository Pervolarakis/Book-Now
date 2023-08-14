package com.example.Book.now.RequestBodies;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingStatusRequestBody {
    @NotNull(message = "Status is required")
    private String status;
}
