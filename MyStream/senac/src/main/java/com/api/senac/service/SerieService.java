package com.api.senac.service;

import com.api.senac.data.SerieEntity;
import com.api.senac.data.SerieRepository;
import com.api.senac.exception.ResourceNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SerieService {

    @Autowired
    SerieRepository serieRepository;
    

    public SerieEntity criarSerie(SerieEntity serie) {

        serie.setId(null);
        serieRepository.save(serie);
        return serie;

    }

    public SerieEntity atualizarSerie(Integer serieId, SerieEntity serieRequest) {
        SerieEntity serie = getSerieId(serieId);
        serie.setTitulo(serieRequest.getTitulo());
        serie.setGenero(serieRequest.getGenero());
        serie.setStreaming(serieRequest.getStreaming());
        serieRepository.save(serie);
        return serie;

    }

    public SerieEntity getSerieId(Integer serieId) {
        return serieRepository.findById(serieId)
                .orElseThrow(() -> new ResourceNotFoundException("Serie não encontrado " + serieId));

    }

    public List<SerieEntity> listarTodosSeries() {
        return serieRepository.findAll();

    }

    public void deletarSerie(Integer serieId) {
        SerieEntity serie = getSerieId(serieId);
        serieRepository.deleteById(serie.getId());

    }

    public List<SerieEntity> getSeriePorTitulo(String titulo) {

        return serieRepository.findByTituloContaining(titulo);

    }

}
