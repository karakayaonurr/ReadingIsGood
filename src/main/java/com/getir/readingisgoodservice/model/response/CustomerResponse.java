package com.getir.readingisgoodservice.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by TCOKARAKAYA on 23.10.2022.
 */
@Getter
@Setter
@Builder
@ToString
public class CustomerResponse
{
    private String name;
    private String surname;
    private String email;
}
