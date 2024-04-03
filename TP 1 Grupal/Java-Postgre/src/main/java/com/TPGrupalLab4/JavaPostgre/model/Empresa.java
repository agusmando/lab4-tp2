package com.TPGrupalLab4.JavaPostgre.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String Denominacion;
    private String Telefono;
    private String HorarioAtencion;
    private String QuienesSomos;
    private double Latitud;
    private double Longitud;
    private String domicilio;
    private String Email;

    public Empresa() {
    }

}
