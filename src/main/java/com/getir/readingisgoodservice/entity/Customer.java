package com.getir.readingisgoodservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * Created by TCOKARAKAYA on 23.10.2022.
 */
@Entity
@Table(name = "customer")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity
{
    @NotEmpty(message = "Name can not be empty.")
    private String name;

    @NotEmpty(message = "Surname can not be empty.")
    private String surname;

    @Email(message = "Invalid email format")
    private String email;
}
