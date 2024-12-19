package com.test_technique.backend.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CoordonneesGpsDto(
        @NotNull(message = "La latitude est obligatoire")
        @Min(value = -90, message = "La latitude ne doit pas être inférieure à -90")
        @Max(value = 90, message = "La latitude ne doit pas être supérieure à 90")
        Double latitude,

        @NotNull(message = "La longitude est obligatoire")
        @Min(value = -180, message = "La longitude ne doit pas être inférieure à -180")
        @Max(value = 180, message = "La longitude ne doit pas être supérieure à 180")
        Double longitude)
{}
