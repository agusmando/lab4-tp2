package com.TPGrupalLab4.JavaPostgre;

import com.TPGrupalLab4.JavaPostgre.controller.EmpresaController;
import com.TPGrupalLab4.JavaPostgre.model.Empresa;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaPostgreApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaPostgreApplication.class, args);
		System.out.println("--Estoy vivo--");

	}

}
