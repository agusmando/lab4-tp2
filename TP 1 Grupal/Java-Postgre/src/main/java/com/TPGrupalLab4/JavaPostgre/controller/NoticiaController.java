package com.TPGrupalLab4.JavaPostgre.controller;

import com.TPGrupalLab4.JavaPostgre.model.Empresa;
import com.TPGrupalLab4.JavaPostgre.model.Noticia;
import com.TPGrupalLab4.JavaPostgre.model.YesNoEnum;
import com.TPGrupalLab4.JavaPostgre.service.EmpresaService;
import com.TPGrupalLab4.JavaPostgre.service.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/noticias")
public class NoticiaController {
    @Autowired
    private NoticiaService noticiaService;
    @Autowired
    EmpresaService empresaService;
    // METODO ORIGINAL
    /*@PostMapping
    public Noticia crearNoticia(@RequestBody Noticia noticia){
        return empresaNoticia.guardarNoticia(noticia);
    }*/
    // METODO DE PRUEBA PARA PROBAR LA CREACION DE UNA NOTICIA
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Noticia crearNoticia(@RequestParam String titulo,
                                @RequestParam String resumen,
                                @RequestParam String imagen,
                                @RequestParam String contenido,
                                @RequestParam YesNoEnum publicada,
                                @RequestParam Date fechaPublicacion,
                                @RequestParam int empresaid) {
       // System.out.println("Datos de la PETICION: titulo:"+titulo+" resumen: "+resumen+" imagen "+imagen+" contenido "+contenido+" publicada "+publicada+" fechaPublicacion: "+fechaPublicacion+" empresaId: "+empresaid);
        Empresa empresa = empresaService.buscarEmpresaPorID(empresaid);

        Noticia noticia = new Noticia();
        noticia.setTitulo(titulo);
        noticia.setResumen(resumen);
        noticia.setImagen(imagen);
        noticia.setContenidoHTML(contenido);
        noticia.setPublicada(publicada);
        noticia.setFechaPublicacion(fechaPublicacion);
        noticia.setIdEmpresa(empresa);
        return noticiaService.guardarNoticia(noticia);
    }

    // METODO DE PRUEBA PARA INGRESAR LOS DATOS DE UNA NOTICIA A TRAVÉZ DE UN FORM
    // LINK: http://localhost:8080/noticias/noticiaPrueba.html
    @GetMapping("/noticiaPrueba.html")
    public String mostrarPaginaPrueba(Model model) {

        List<Empresa> empresas = empresaService.obtenerEmpresas();
        model.addAttribute("empresas", empresas);
        return "noticiaPrueba"; // Este es el nombre del archivo HTML sin la extensión ".html"
    }
}