package com.api.senac.service;

import com.api.senac.data.GeneroEntity;
import com.api.senac.data.GeneroRepository;
import com.api.senac.exception.ResourceNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneroService {

    @Autowired
    GeneroRepository generoRepository;

    public GeneroEntity getGeneroId(Integer generoId) {
        return generoRepository.findById(generoId)
                .orElseThrow(() -> new ResourceNotFoundException("Genero não encontrado " + generoId));

    }

    public List<GeneroEntity> listarTodosGeneros() {
        return generoRepository.findAll();

    }

    public List<GeneroEntity> getGeneroPorTitulo(String titulo) {

        return generoRepository.findByTituloContaining(titulo);

    }

}
