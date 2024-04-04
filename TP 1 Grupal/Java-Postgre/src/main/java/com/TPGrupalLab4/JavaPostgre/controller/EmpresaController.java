package com.TPGrupalLab4.JavaPostgre.controller;

import com.TPGrupalLab4.JavaPostgre.model.Empresa;
import com.TPGrupalLab4.JavaPostgre.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/empresas")
public class EmpresaController {
    @Autowired
    private EmpresaService empresaService;

    // METODO ORIGINAL
    @PostMapping
    public Empresa crearEmpresa(@RequestBody Empresa empresa){
        return empresaService.guardarEmpresa(empresa);
    }


    // METODO DE PRUEBA PARA PROBAR LA CREACION DE UNA EMPRESA
    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Empresa crearEmpresa(@RequestParam String denominacion,
                                @RequestParam String domicilio,
                                @RequestParam String email,
                                @RequestParam double latitud,
                                @RequestParam double longitud,
                                @RequestParam String horarioAtencion,
                                @RequestParam String quienesSomos,
                                @RequestParam String telefono) {

        Empresa empresa = new Empresa();
        empresa.setDenominacion(denominacion);
        empresa.setDomicilio(domicilio);
        empresa.setEmail(email);
        empresa.setLatitud(latitud);
        empresa.setLongitud(longitud);
        empresa.setHorarioAtencion(horarioAtencion);
        empresa.setQuienesSomos(quienesSomos);
        empresa.setTelefono(telefono);

        return empresaService.guardarEmpresa(empresa);
    }



    // METODO DE PRUEBA PARA INGRESAR LOS DATOS DE UNA EMPRESA A TRAVÉZ DE UN FORM
    // LINK: http://localhost:8080/empresas/empresaPrueba.html
    @GetMapping("/empresaPrueba.html")
    public String mostrarPaginaPrueba() {
        return "empresaPrueba"; // Este es el nombre del archivo HTML sin la extensión ".html"
    }
}
