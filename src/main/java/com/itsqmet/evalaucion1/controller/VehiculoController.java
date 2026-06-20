package com.itsqmet.evalaucion1.controller;

import com.itsqmet.evalaucion1.model.Vehiculo;
import com.itsqmet.evalaucion1.service.VehiculoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
@CrossOrigin("*")
public class VehiculoController {

    private final VehiculoService vehiculoService;

    public VehiculoController(
            VehiculoService vehiculoService) {

        this.vehiculoService = vehiculoService;
    }

    @GetMapping
    public List<Vehiculo> listar() {

        return vehiculoService.listar();
    }

    @GetMapping("/{id}")
    public Vehiculo buscarPorId(
            @PathVariable Long id) {

        return vehiculoService.buscarPorId(id);
    }

    @PostMapping
    public Vehiculo guardar(
            @Valid @RequestBody Vehiculo vehiculo) {

        return vehiculoService.guardar(vehiculo);
    }

    @PutMapping("/{id}")
    public Vehiculo actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Vehiculo vehiculo) {

        return vehiculoService.actualizar(id, vehiculo);
    }

    @DeleteMapping("/{id}")
    public void eliminar(
            @PathVariable Long id) {

        vehiculoService.eliminar(id);
    }

    @GetMapping("/categoria")
    public List<Vehiculo> categoria(
            @RequestParam String categoria,
            @RequestParam Integer unidades) {

        return vehiculoService.buscarCategoria(
                categoria,
                unidades);
    }

    @GetMapping("/precio")
    public List<Vehiculo> precio(
            @RequestParam Double min,
            @RequestParam Double max) {

        return vehiculoService.buscarPrecio(
                min,
                max);
    }

    @GetMapping("/modelo")
    public List<Vehiculo> modelo(
            @RequestParam String modelo) {

        return vehiculoService.buscarModelo(modelo);
    }
}