package com.api.senac.controller;

import com.api.senac.service.UsuarioService;
import com.api.senac.data.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsuarioController {
    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/login")
    public String mostrarFormularioLogin(@RequestParam(name = "error", required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("mensagemErro", "Usuário ou senha incorretos.");
        }
        return "index.html";
    }

    @PostMapping("/autenticar")
    public String autenticarUsuario(String usuario, String senha, Model model) {
        UsuarioEntity usuarioEntity = usuarioService.buscarUsuarioPorUsuario(usuario);
        
        if (usuarioEntity != null && usuarioService.validarUsuario(usuario, senha)) {
            return "redirect:/filmes"; 
        } else {
            return "redirect:/login?error=true"; 
        }
    }
}

