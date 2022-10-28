package com.getir.readingisgoodservice.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * Created by TCOKARAKAYA on 23.10.2022.
 */
@Getter
@ToString
@Builder
public class CustomerRequest {
    @NotEmpty(message = "Name can not be empty.")
    private String name;

    @NotEmpty(message = "Surname can not be empty.")
    private String surname;

    @Email(message = "Invalid email format")
    private String email;
}
