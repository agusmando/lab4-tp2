package com.TPGrupalLab4.JavaPostgre.service;


import com.TPGrupalLab4.JavaPostgre.model.Noticia;
import com.TPGrupalLab4.JavaPostgre.repository.NoticiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoticiaService {
    @Autowired
    private NoticiaRepository noticiaRepository;

    public Noticia guardarNoticia(Noticia noticia){
        return noticiaRepository.save(noticia);
    }


    public Noticia buscarNoticiaPorID(int id){
        Optional<Noticia> optionalNoticia= noticiaRepository.findById(id);
        return optionalNoticia.orElse(null);
    }

    public List<Noticia> obtenerNoticias(){
        return noticiaRepository.findAll();
    }

    public void eliminarNoticia(int id){
        noticiaRepository.deleteById(id);
    }

    public Noticia actualizarNoticia(int id, Noticia noticiaActualizada){
        Noticia noticia= buscarNoticiaPorID(id);
        if (noticia == null){
            System.out.println("No se encontro la noticia por Id");
            return null;
        }else{
            return noticia;
        }
    }
}
