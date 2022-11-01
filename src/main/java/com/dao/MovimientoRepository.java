package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.Movimientos;

public interface MovimientoRepository extends JpaRepository<Movimientos, Integer> {

}
