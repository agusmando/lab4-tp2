package com.TPGrupalLab4.JavaPostgre.controller;

import com.TPGrupalLab4.JavaPostgre.model.Empresa;
import com.TPGrupalLab4.JavaPostgre.model.Noticia;
import com.TPGrupalLab4.JavaPostgre.model.YesNoEnum;
import com.TPGrupalLab4.JavaPostgre.service.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.Date;

@Controller
@RequestMapping("/noticia")
public class NoticiaController {
    @Autowired
    private NoticiaService noticiaService;
    // METODO ORIGINAL
    /*@PostMapping
    public Empresa crearEmpresa(@RequestBody Empresa empresa){
        return empresaService.guardarEmpresa(empresa);
    }*/
    // METODO DE PRUEBA PARA PROBAR LA CREACION DE UNA EMPRESA
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Noticia crearNoticia(@RequestParam String Titulo,
                                @RequestParam String Resumen,
                                @RequestParam String Imagen,
                                @RequestParam String ContenidoHTML,
                                @RequestParam YesNoEnum Publicada,
                                @RequestParam Date FechaPublicacion)
                                {

        Noticia noticia = new Noticia();
        noticia.setTitulo(Titulo);
        noticia.setResumen(Resumen);
        noticia.setImagen(Imagen);
        noticia.setContenidoHTML(ContenidoHTML);
        noticia.setPublicada(Publicada);
        noticia.setFechaPublicacion(FechaPublicacion);


        return noticiaService.guardarNoticia(noticia);
    }

    // METODO DE PRUEBA PARA INGRESAR LOS DATOS DE UNA EMPRESA A TRAVÉZ DE UN FORM
    // LINK: http://localhost:8080/empresas/empresaPrueba.html
    @GetMapping("/noticiaPrueba.html")
    public String mostrarPaginaPrueba() {
        return "noticiaPrueba"; // Este es el nombre del archivo HTML sin la extensión ".html"
    }
}