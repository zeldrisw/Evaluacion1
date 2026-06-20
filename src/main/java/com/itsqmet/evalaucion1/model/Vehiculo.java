package com.itsqmet.evalaucion1.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name="vehiculos")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message="Modelo obligatorio")
    @Size(min=3,max=100)
    private String modelo;

    @NotBlank(message="Categoría obligatoria")
    private String categoria;

    @Size(max=255)
    private String descripcion;

    @NotNull
    @Positive
    private Double precioPorDia;

    @NotNull
    @Min(0)
    private Integer unidadesDisponibles;

    public Vehiculo() {
    }

    public Vehiculo(Long id, String modelo, String categoria,
                    String descripcion,
                    Double precioPorDia,
                    Integer unidadesDisponibles) {

        this.id=id;
        this.modelo=modelo;
        this.categoria=categoria;
        this.descripcion=descripcion;
        this.precioPorDia=precioPorDia;
        this.unidadesDisponibles=unidadesDisponibles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id=id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo=modelo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria=categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion=descripcion;
    }

    public Double getPrecioPorDia() {
        return precioPorDia;
    }

    public void setPrecioPorDia(Double precioPorDia) {
        this.precioPorDia=precioPorDia;
    }

    public Integer getUnidadesDisponibles() {
        return unidadesDisponibles;
    }

    public void setUnidadesDisponibles(Integer unidadesDisponibles) {
        this.unidadesDisponibles=unidadesDisponibles;
    }
}