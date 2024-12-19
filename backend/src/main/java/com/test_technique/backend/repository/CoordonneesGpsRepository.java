package com.test_technique.backend.repository;

import com.test_technique.backend.entity.CoordonneesGps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordonneesGpsRepository extends JpaRepository<CoordonneesGps, Long> {

}
