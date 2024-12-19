package com.test_technique.backend.entity;

import jakarta.persistence.*;


@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"latitude", "longitude"})
})
public class CoordonneesGps {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generateur_seq")
    @SequenceGenerator(name = "generateur_seq", sequenceName = "point_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private Double latitude;
    @Column(nullable = false)
    private Double longitude;

    public CoordonneesGps() {
    }

    public CoordonneesGps(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Coordonnee{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }


}
