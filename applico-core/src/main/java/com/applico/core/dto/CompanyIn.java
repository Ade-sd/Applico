package com.applico.core.dto;

import jakarta.validation.ValidationException;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompanyIn {
    @NotNull(message = "name is required")
    private String name;

    @Digits(integer = 4, fraction = 0, message = "Year must be exactly 4 digits")
    private Integer founded;

    private String industry;

    @NotNull(message = "website is required")
    private String website;

    @Size(max = 5000, message = "about cannot exceed 5000 characters")
    private String about;

    private String headquarter;
}
