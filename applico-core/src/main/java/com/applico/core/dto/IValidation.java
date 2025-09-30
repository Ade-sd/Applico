package com.applico.core.dto;

import jakarta.validation.ValidationException;

public interface IValidation {

    void validate() throws ValidationException;
}