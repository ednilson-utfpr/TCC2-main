package br.edu.utfpr.pb.tcc.controller;


import br.edu.utfpr.pb.tcc.model.Formato;
import br.edu.utfpr.pb.tcc.service.CoberturaService;
import br.edu.utfpr.pb.tcc.service.FormatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("formato")
public class FormatoController {

    @Autowired
    private FormatoService formatoService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("formatos", formatoService.findAll());
        return "formato/list";
    }

    @GetMapping(value = {"new", "novo", "form"})
    public String form(Model model) {
        model.addAttribute("formato", new Formato());
        return "formato/form";
    }

    @PostMapping
    public String save(@Valid Formato formato,
                       BindingResult result,
                       Model model,
                       RedirectAttributes attributes) {
        if ( result.hasErrors() ) {
            model.addAttribute("formato", formato);
            return "formato/form";
        }

        formatoService.save(formato);
        attributes.addFlashAttribute("sucesso",
                "Registro salvo com sucesso!");
        return "redirect:/formato";
    }

    @GetMapping("{id}")
    public String form(@PathVariable Long id, Model model) {
        model.addAttribute("formato", formatoService.findOne(id));
        return "formato/form";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Long id,
                         RedirectAttributes attributes) {
        try {
            formatoService.delete(id);
            attributes.addFlashAttribute("sucesso",
                    "Registro removido com sucesso!");
        } catch (Exception e) {
            attributes.addFlashAttribute("erro",
                    "Falha ao remover o registro!");
        }
        return "redirect:/formato";
    }
}
