package com.api.senac.service;

import com.api.senac.data.FilmeEntity;
import com.api.senac.data.FilmeRepository;
import com.api.senac.exception.ResourceNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmeService {

    @Autowired
    FilmeRepository filmeRepository;
    

    public FilmeEntity criarFilme(FilmeEntity filme) {

        filme.setId(null);
        filmeRepository.save(filme);
        return filme;

    }

    public FilmeEntity atualizarFilme(Integer filmeId, FilmeEntity filmeRequest) {
        FilmeEntity filme = getFilmeId(filmeId);
        filme.setTitulo(filmeRequest.getTitulo());
        filme.setGenero(filmeRequest.getGenero());
        filme.setStreaming(filmeRequest.getStreaming());
        filmeRepository.save(filme);
        return filme;

    }

    public FilmeEntity getFilmeId(Integer filmeId) {
        return filmeRepository.findById(filmeId)
                .orElseThrow(() -> new ResourceNotFoundException("Filme não encontrado " + filmeId));

    }

    public List<FilmeEntity> listarTodosFilmes() {
        return filmeRepository.findAll();

    }

    public void deletarFilme(Integer filmeId) {
        FilmeEntity filme = getFilmeId(filmeId);
        filmeRepository.deleteById(filme.getId());

    }

    public List<FilmeEntity> getFilmePorTitulo(String titulo) {

        return filmeRepository.findByTituloContaining(titulo);

    }

}
