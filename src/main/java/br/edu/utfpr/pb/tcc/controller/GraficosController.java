package br.edu.utfpr.pb.tcc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("graficos")
public class GraficosController {


    @GetMapping
    public String list(Model model) {
        return "grafico/graficos";
    }
}

