package com.test_technique.backend.dto;

public record DistanceRequest(

        CoordonneesGpsDto pointA,
        CoordonneesGpsDto pointB
) {
}
