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
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
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
    @PostMapping(value = "/guardar",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String crearNoticia(@RequestParam String titulo,
                                @RequestParam String resumen,
                                @RequestParam MultipartFile imagen,
                                @RequestParam String contenido,
                                @RequestParam YesNoEnum publicada,
                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaPublicacion,
                                @RequestParam int empresaid) {
        Empresa empresa = empresaService.buscarEmpresaPorID(empresaid);

        Noticia noticia = new Noticia();
        noticia.setTitulo(titulo);
        noticia.setResumen(resumen);

        try {
            if (!imagen.isEmpty()) {
                byte[] imageData = imagen.getBytes();
                noticia.setImagen(imageData);
            }
        } catch (IOException e) {
            System.out.println("Error al guardar la imagen");
        }

        noticia.setContenidoHTML(contenido);
        noticia.setPublicada(publicada);
        noticia.setFechaPublicacion(fechaPublicacion);
        noticia.setIdEmpresa(empresa);

        noticiaService.guardarNoticia(noticia);
        return "redirect:/empresas/listaEmpresas";

    }

    // METODO DE PRUEBA PARA INGRESAR LOS DATOS DE UNA NOTICIA A TRAVÉZ DE UN FORM
    // LINK: http://localhost:8080/noticias/noticiaPrueba.html
    @GetMapping("/noticiaPrueba.html")
    public String mostrarPaginaPrueba(Model model) {

        List<Empresa> empresas = empresaService.obtenerEmpresas();
        model.addAttribute("empresas", empresas);
        return "noticiaPrueba"; // Este es el nombre del archivo HTML sin la extensión ".html"
    }
    @GetMapping("/eliminarNoticia/{id}")
    public String eliminarNoticia(@PathVariable int id) {
        noticiaService.eliminarNoticia(id);
        return "redirect:/empresas/listaEmpresas";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable int id, Model model) {
        // Aquí podrías recuperar la empresa con el id especificado de la base de datos
        // y luego pasarla al modelo para que se muestre en el formulario de edición
        Noticia noticia = noticiaService.buscarNoticiaPorID(id);
        model.addAttribute("noticia", noticia);
        List<Empresa> empresas = empresaService.obtenerEmpresas();
        model.addAttribute("empresas", empresas);
        return "formulario-edicion-noticia"; // Nombre del archivo HTML del formulario de edición
    }
    @PostMapping("/actualizar")
    public String actualizarNoticia(@RequestParam int Id,
                                    @RequestParam String Titulo,
                                    @RequestParam String Resumen,
                                    @RequestParam MultipartFile Imagen,
                                    @RequestParam String Contenido,
                                    @RequestParam YesNoEnum Publicada,
                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date FechaPublicacion,
                                    @RequestParam int IdEmpresa) {
        // Obtener la noticia existente
        Noticia noticia = noticiaService.buscarNoticiaPorID(Id);

        // Verificar si la noticia existe
        if (noticia == null) {
            // Manejar el caso en el que la noticia no existe
            return "redirect:/error"; // Redirigir a una página de error o mostrar un mensaje adecuado al usuario
        }

        // Actualizar los campos de la noticia existente
        noticia.setTitulo(Titulo);
        noticia.setResumen(Resumen);
        noticia.setContenidoHTML(Contenido);
        noticia.setPublicada(Publicada);
        noticia.setFechaPublicacion(FechaPublicacion);

        // Verificar si se ha proporcionado una nueva imagen
        try {
            if (!Imagen.isEmpty()) {
                byte[] imageData = Imagen.getBytes();
                noticia.setImagen(imageData);
            }
        } catch (IOException e) {
            System.out.println("Error al guardar la imagen");
        }

        // Obtener y establecer la empresa correspondiente
        Empresa empresa = empresaService.buscarEmpresaPorID(IdEmpresa);
        noticia.setIdEmpresa(empresa);

        // Actualizar la noticia en la base de datos
        noticiaService.actualizarNoticia(Id, noticia);

        return "redirect:/empresas/listaEmpresas";
    }
}