package com.TPGrupalLab4.JavaPostgre.service;

import com.TPGrupalLab4.JavaPostgre.model.Empresa;
import com.TPGrupalLab4.JavaPostgre.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {
    @Autowired
    private EmpresaRepository empresaRepository;

    public Empresa guardarEmpresa(Empresa empresa){
        return empresaRepository.save(empresa);
    }

    public Empresa buscarEmpresaPorID(int id){
        Optional<Empresa> optionalEmpresa = empresaRepository.findById(id);
        return optionalEmpresa.orElse(null);
    }

    public List<Empresa> obtenerEmpresas(){
        return empresaRepository.findAll();
    }
    public void eliminarEmpresa(int id){
        empresaRepository.deleteById(id);
    }

    public Empresa actualizarEmpresa(int id, Empresa empresaActualizada){
        Empresa empresa = buscarEmpresaPorID(id);
        if(empresa == null){
            System.out.println("No se encontro la empresa con ese ID");
            return null;
        }else{
            empresa = empresaRepository.save(empresaActualizada);
            return empresa;
        }
    }


}
