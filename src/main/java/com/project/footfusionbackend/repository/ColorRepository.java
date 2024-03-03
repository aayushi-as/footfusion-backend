package com.project.footfusionbackend.repository;

import com.project.footfusionbackend.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {
    Color findByColorId(Long id);
}
