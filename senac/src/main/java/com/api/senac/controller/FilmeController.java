package com.api.senac.controller;

import com.api.senac.data.StreamingEntity;
import com.api.senac.service.StreamingService;
import com.api.senac.data.FilmeEntity;
import com.api.senac.data.GeneroEntity;
import com.api.senac.service.FilmeService;
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

public class FilmeController {

    @Autowired
    FilmeService filmeService;
    private final StreamingService streamingService;
    private final GeneroService generoService;

    @Autowired
    public FilmeController(StreamingService streamingService, GeneroService generoService) {
        this.streamingService = streamingService;
        this.generoService = generoService;
    }

    @GetMapping("/filmes")
    public String viewHomePage(Model model) {
        model.addAttribute("listarFilmes", filmeService.listarTodosFilmes());
        return "listar-filmes";
    }

    @GetMapping("/deletarFilme/{id}")
    public String deletarFilme(@PathVariable(value = "id") Integer id) {
        filmeService.deletarFilme(id);
        return "redirect:/filmes";
    }

    @GetMapping("/criarFilmeForm")
    public String criarFilmeForm(Model model) {
        FilmeEntity filme = new FilmeEntity();
        List<StreamingEntity> streamings = streamingService.listarTodosStreamings();
        List<GeneroEntity> generos = generoService.listarTodosGeneros();
        model.addAttribute("filme", filme);
        model.addAttribute("streamings", streamings);
        model.addAttribute("generos", generos);
        return "inserir-filmes";
    }

    @PostMapping("/salvarFilme")

    public String salvarFilme(@Valid @ModelAttribute("filme") FilmeEntity filme,
            BindingResult result) {

        if (result.hasErrors()) {
            return "inserir-filmes";
        }

        if (filme.getId() == null) {
            filmeService.criarFilme(filme);
        } else {
            filmeService.atualizarFilme(filme.getId(), filme);
        }

        return "redirect:/filmes";
    }

    @GetMapping("/atualizarFilmeForm/{id}")

    public String atualizarFilmeForm(@PathVariable(value = "id") Integer id, Model model) {

        FilmeEntity filme = filmeService.getFilmeId(id);
        List<StreamingEntity> streamings = streamingService.listarTodosStreamings();
        List<GeneroEntity> generos = generoService.listarTodosGeneros();
        model.addAttribute("filme", filme);
        model.addAttribute("streamings", streamings);
        model.addAttribute("generos", generos);
        return "atualizar-filmes";
    }
}