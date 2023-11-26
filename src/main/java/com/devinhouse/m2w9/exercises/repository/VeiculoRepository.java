package com.devinhouse.m2w9.exercises.repository;

import com.devinhouse.m2w9.exercises.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, String> {
}
