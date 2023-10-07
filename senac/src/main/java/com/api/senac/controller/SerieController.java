package com.api.senac.controller;

import com.api.senac.data.StreamingEntity;
import com.api.senac.service.StreamingService;
import com.api.senac.data.SerieEntity;
import com.api.senac.data.GeneroEntity;
import com.api.senac.service.SerieService;
import com.api.senac.service.GeneroService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller

public class SerieController {

    @Autowired
    SerieService serieService;
    private final StreamingService streamingService;
    private final GeneroService generoService;

    @Autowired
    public SerieController(StreamingService streamingService, GeneroService generoService) {
        this.streamingService = streamingService;
        this.generoService = generoService;
    }

    @GetMapping("/series")
    public String viewHomePage(Model model) {
        model.addAttribute("listarSeries", serieService.listarTodosSeries());
        return "listar-series";
    }

    @GetMapping("/deletarSerie/{id}")
    public String deletarSerie(@PathVariable(value = "id") Integer id) {
        serieService.deletarSerie(id);
        return "redirect:/series";
    }

    @GetMapping("/criarSerieForm")
    public String criarSerieForm(Model model) {
        SerieEntity serie = new SerieEntity();
        List<StreamingEntity> streamings = streamingService.listarTodosStreamings();
        List<GeneroEntity> generos = generoService.listarTodosGeneros();
        model.addAttribute("serie", serie);
        model.addAttribute("streamings", streamings);
        model.addAttribute("generos", generos);
        return "inserir-series";
    }

    @PostMapping("/salvarSerie")

    public String salvarSerie(@Valid @ModelAttribute("serie") SerieEntity serie,
            BindingResult result) {

        if (result.hasErrors()) {
            return "inserir-series";
        }

        if (serie.getId() == null) {
            serieService.criarSerie(serie);
        } else {
            serieService.atualizarSerie(serie.getId(), serie);
        }

        return "redirect:/series";
    }

    @GetMapping("/atualizarSerieForm/{id}")

    public String atualizarSerieForm(@PathVariable(value = "id") Integer id, Model model) {

        SerieEntity serie = serieService.getSerieId(id);
        List<StreamingEntity> streamings = streamingService.listarTodosStreamings();
        List<GeneroEntity> generos = generoService.listarTodosGeneros();
        model.addAttribute("serie", serie);
        model.addAttribute("streamings", streamings);
        model.addAttribute("generos", generos);
        return "atualizar-series";
    }
}