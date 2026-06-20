package com.itsqmet.evalaucion1.service;

import com.itsqmet.evalaucion1.model.Vehiculo;
import com.itsqmet.evalaucion1.repository.VehiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoService {

    private final VehiculoRepository repository;

    public VehiculoService(VehiculoRepository repository) {
        this.repository = repository;
    }

    public List<Vehiculo> listar() {
        return repository.findAll();
    }

    public Vehiculo buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Vehiculo guardar(Vehiculo vehiculo) {
        return repository.save(vehiculo);
    }

    public Vehiculo actualizar(Long id, Vehiculo vehiculo) {

        Vehiculo existente = repository.findById(id)
                .orElse(null);

        if (existente != null) {

            existente.setModelo(vehiculo.getModelo());
            existente.setCategoria(vehiculo.getCategoria());
            existente.setDescripcion(vehiculo.getDescripcion());
            existente.setPrecioPorDia(vehiculo.getPrecioPorDia());
            existente.setUnidadesDisponibles(vehiculo.getUnidadesDisponibles());

            return repository.save(existente);
        }

        return null;
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public List<Vehiculo> buscarCategoria(
            String categoria,
            Integer unidades) {

        return repository
                .findByCategoriaAndUnidadesDisponiblesGreaterThan(
                        categoria,
                        unidades);
    }

    public List<Vehiculo> buscarPrecio(
            Double min,
            Double max) {

        return repository
                .findByPrecioPorDiaBetweenOrderByPrecioPorDiaAsc(
                        min,
                        max);
    }

    public List<Vehiculo> buscarModelo(String modelo) {

        return repository
                .findByModeloContainingIgnoreCase(modelo);
    }
}