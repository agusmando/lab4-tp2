package com.TPGrupalLab4.JavaPostgre.controller;

import com.TPGrupalLab4.JavaPostgre.model.Empresa;
import com.TPGrupalLab4.JavaPostgre.model.Noticia;
import com.TPGrupalLab4.JavaPostgre.service.EmpresaService;
import com.TPGrupalLab4.JavaPostgre.service.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;
    @Autowired
    private NoticiaService noticiaService;

    //BUENA PRACTICA
    /*private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }*/

    // METODO ORIGINAL
    @PostMapping
    public String crearEmpresa(Empresa empresa){
        empresaService.guardarEmpresa(empresa);
        return "redirect:/empresas/listaEmpresas";
    }

    // METODO DE PRUEBA PARA INGRESAR LOS DATOS DE UNA EMPRESA A TRAVÉZ DE UN FORM
    // LINK: http://localhost:8080/empresas/empresaPrueba.html
    @GetMapping("/empresaPrueba.html")
    public String mostrarPaginaPrueba() {
        return "empresaPrueba"; // Este es el nombre del archivo HTML sin la extensión ".html"
    }

    @GetMapping("/listaEmpresas")
    public String mostrarListaEmpresas(Model model){
        List<Empresa> empresas = empresaService.obtenerEmpresas();
        model.addAttribute("empresas", empresas);
        List<Noticia> noticias = noticiaService.obtenerNoticias();
        model.addAttribute("noticias", noticias);

        return "index";
    }
    @GetMapping("/eliminarEmpresa/{id}")
    public String eliminarEmpresa(@PathVariable int id) {
        noticiaService.eliminarNoticiaPorEmpresa(id);
        empresaService.eliminarEmpresa(id);
        return "redirect:/empresas/listaEmpresas";
    }
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable int id, Model model) {
        Empresa empresa = empresaService.buscarEmpresaPorID(id);
        model.addAttribute("empresa", empresa);
        return "formulario-edicion"; // Nombre del archivo HTML del formulario de edición
    }
    @PostMapping("/actualizar")
    public String actualizarEmpresa(Empresa empresa){
        empresaService.actualizarEmpresa(empresa.getId(), empresa);
        return "redirect:/empresas/listaEmpresas";
    }

    @GetMapping("/home/{id}")
    public String mostrarEmpresa(@PathVariable int id, Model model){
        Empresa empresa = empresaService.buscarEmpresaPorID(id);
        model.addAttribute("empresa", empresa);
        List<Noticia> noticias = noticiaService.encontrarNoticiasPorEmpresa(id);
        model.addAttribute("noticias", noticias);
        return "home";
    }

    @GetMapping("/home/detalle/{id}")
    public String mostrarNoticia(@PathVariable int id, Model model) throws IOException {
        Empresa empresa = empresaService.buscarEmpresaPorID(id);
        model.addAttribute("empresa", empresa);
        List<Noticia> noticias = noticiaService.encontrarNoticiasPorEmpresa(id);
        List <BufferedImage> imagenes = new ArrayList<>();
        for (Noticia noticia: noticias
             ) {
            imagenes.add(ImageIO.read(new ByteArrayInputStream(noticia.getImagen())));
        }
        model.addAttribute("noticias", noticias);
        model.addAttribute("imagenes", imagenes);
        return "detalle";
    }

}
