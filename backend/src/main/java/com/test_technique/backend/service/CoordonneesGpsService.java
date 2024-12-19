package com.test_technique.backend.service;

import com.test_technique.backend.dto.CoordonneesGpsDto;
import com.test_technique.backend.entity.CoordonneesGps;
import com.test_technique.backend.exception.ResourceNotFound;
import com.test_technique.backend.repository.CoordonneesGpsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utils.GeoUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CoordonneesGpsService {
    private final CoordonneesGpsRepository coordonneesGpsRepository;

    public CoordonneesGpsService(CoordonneesGpsRepository coordonneesGpsRepository) {
        this.coordonneesGpsRepository = coordonneesGpsRepository;
    }

    public CoordonneesGps creerCoordonne(double latitude, double longitude) {
        return coordonneesGpsRepository.save(new CoordonneesGps(latitude, longitude));
    }

    public List<CoordonneesGps> listerPoints() {
        return coordonneesGpsRepository.findAll().stream()
                .sorted(Comparator.comparing(CoordonneesGps::getId).reversed())
                .collect(Collectors.toList());
    }

    public Double calculerDistance(CoordonneesGpsDto pointA, CoordonneesGpsDto pointB) {
        if(coordonneeInvalide(pointA) || coordonneeInvalide(pointB)){
            throw new RuntimeException("Coordonne invalide");
        }

        return GeoUtils.haversine(
                pointA.latitude(), pointA.longitude(),
                pointB.latitude(), pointB.longitude()
        );
    }

    private boolean coordonneeInvalide(CoordonneesGpsDto point) {
        return !(point.latitude() >= -90 && point.latitude() <= 90
                && point.longitude() >= -180 && point.longitude() <= 180);
    }

    public void supprimerCoordonneesGpsParId(Long id) {
        coordonneesGpsRepository.findById(id).
                ifPresentOrElse(
                        (coordonneesGps) -> coordonneesGpsRepository.deleteById(id),
                        () -> {
                            throw new ResourceNotFound(
                                "Il n'existe aucune coordonnées GPS avec l'identifiant %s".formatted(id));
                        }
                );
    }
}
