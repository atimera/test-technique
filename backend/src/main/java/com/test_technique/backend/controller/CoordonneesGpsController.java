package com.test_technique.backend.controller;

import com.test_technique.backend.dto.CoordonneesGpsDto;
import com.test_technique.backend.dto.DistanceRequest;
import com.test_technique.backend.dto.ReponseHttp;
import com.test_technique.backend.service.CoordonneesGpsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/coordonnees-gps")
public class CoordonneesGpsController {
    private final static Logger LOG = LoggerFactory.getLogger(CoordonneesGpsController.class);

    private final CoordonneesGpsService coordonneesGpsService;


    public CoordonneesGpsController(CoordonneesGpsService coordonneesGpsService) {
        this.coordonneesGpsService = coordonneesGpsService;
    }

    @GetMapping
    public ReponseHttp getAll(final HttpServletRequest request) {
        ReponseHttp reponse = new ReponseHttp();
        reponse.setData(Map.of("listeCoordonneesGps", coordonneesGpsService.listerPoints()));
        reponse.setHttpStatus(HttpStatus.OK);
        reponse.setHttpStatusCode(HttpStatus.OK.value());
        reponse.setResourceUrl(request.getRequestURI());
        LOG.info("Ressource : {}", reponse.getResourceUrl());
        return reponse;
    }

    @PostMapping
    public ReponseHttp creerCoordonneesGps(
            @RequestBody @Valid CoordonneesGpsDto coordonneesGpsDto,
            final HttpServletRequest request
    ) {
        ReponseHttp reponse = new ReponseHttp();
        reponse.setData(Map.of(
                "coordonneesGps",
                coordonneesGpsService.creerCoordonne(coordonneesGpsDto.latitude(), coordonneesGpsDto.longitude()))
        );
        reponse.setHttpStatus(HttpStatus.CREATED);
        reponse.setHttpStatusCode(HttpStatus.CREATED.value());
        reponse.setResourceUrl(request.getRequestURI());
        return reponse;
    }

    @PostMapping("/distance")
    public ReponseHttp distanceEntreCoordGps(
            @RequestBody @Valid DistanceRequest distanceRequest,
            final HttpServletRequest request
    ) {
        ReponseHttp reponse = new ReponseHttp();
        reponse.setData(Map.of("distance",
                coordonneesGpsService.calculerDistance(
                    distanceRequest.pointA(), distanceRequest.pointB()))
        );
        reponse.setHttpStatus(HttpStatus.OK);
        reponse.setHttpStatusCode(HttpStatus.OK.value());
        reponse.setResourceUrl(request.getRequestURI());

        //return ResponseEntity.ok(coordonneesGpsService.calculerDistance(pointA, pointB));
        return reponse;
    }

    @DeleteMapping("/{id}")
    public ReponseHttp supprimerCoordonneesGps(
            @PathVariable(name = "id") Long id,
            final HttpServletRequest request
    ) {
        coordonneesGpsService.supprimerCoordonneesGpsParId(id);

        ReponseHttp reponse = new ReponseHttp();
        reponse.setHttpStatus(HttpStatus.OK);
        reponse.setHttpStatusCode(HttpStatus.OK.value());
        reponse.setMessage("La coordonnées GPS d'identifiant %s a été supprimée".formatted(id));
        reponse.setResourceUrl(request.getRequestURI());
        return reponse;
    }




}
