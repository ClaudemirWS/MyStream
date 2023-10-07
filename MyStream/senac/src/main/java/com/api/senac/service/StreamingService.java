package com.api.senac.service;

import com.api.senac.data.StreamingEntity;
import com.api.senac.data.StreamingRepository;
import com.api.senac.exception.ResourceNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StreamingService {

    @Autowired
    StreamingRepository streamingRepository;

    public StreamingEntity getStreamingId(Integer streamingId) {
        return streamingRepository.findById(streamingId)
                .orElseThrow(() -> new ResourceNotFoundException("Streaming não encontrado " + streamingId));

    }

    public List<StreamingEntity> listarTodosStreamings() {
        return streamingRepository.findAll();

    }

    public List<StreamingEntity> getStreamingPorTitulo(String titulo) {

        return streamingRepository.findByTituloContaining(titulo);

    }

}
