package com.TPGrupalLab4.JavaPostgre.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Optional;

@Entity
@Data
public class Noticia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String Titulo;
    private String Resumen;
    private String Imagen;
    private String ContenidoHTML;
    @Enumerated(EnumType.STRING)
    private YesNoEnum Publicada;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date FechaPublicacion;
    @OneToOne
    @JoinColumn(name = "IdEmpresa")
    private Empresa IdEmpresa;

}
