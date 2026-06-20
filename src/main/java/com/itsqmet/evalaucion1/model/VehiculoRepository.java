package com.itsqmet.evalaucion1.repository;

import com.itsqmet.evalaucion1.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehiculoRepository
        extends JpaRepository<Vehiculo, Long> {

    List<Vehiculo> findByCategoriaAndUnidadesDisponiblesGreaterThan(
            String categoria,
            Integer unidades);

    List<Vehiculo> findByPrecioPorDiaBetweenOrderByPrecioPorDiaAsc(
            Double min,
            Double max);

    List<Vehiculo> findByModeloContainingIgnoreCase(
            String modelo);
}