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
    private byte[] Imagen;
    private String ContenidoHTML;
    @Enumerated(EnumType.STRING)
    private YesNoEnum Publicada;
    @Temporal(TemporalType.DATE)
    private Date FechaPublicacion;
    @ManyToOne
    @JoinColumn(name = "IdEmpresa")
    private Empresa IdEmpresa;

}
