package br.edu.utfpr.pb.tcc.controller;


import br.edu.utfpr.pb.tcc.model.Recheio;

import br.edu.utfpr.pb.tcc.service.RecheioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("recheio")
public class RecheioController {

    @Autowired
    private RecheioService recheioService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("recheios", recheioService.findAll());
        return "recheio/list";
    }

    @GetMapping(value = {"new", "novo", "form"})
    public String form(Model model) {
        model.addAttribute("recheio", new Recheio());
        return "recheio/form";
    }

    @PostMapping
    public String save(@Valid Recheio recheio,
                       BindingResult result,
                       Model model,
                       RedirectAttributes attributes) {
        if ( result.hasErrors() ) {
            model.addAttribute("recheio", recheio);
            return "recheio/form";
        }

        recheioService.save(recheio);
        attributes.addFlashAttribute("sucesso",
                "Registro salvo com sucesso!");
        return "redirect:/recheio";
    }

    @GetMapping("{id}")
    public String form(@PathVariable Long id, Model model) {
        model.addAttribute("recheio", recheioService.findOne(id));
        return "recheio/form";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Long id,
                         RedirectAttributes attributes) {
        try {
            recheioService.delete(id);
            attributes.addFlashAttribute("sucesso",
                    "Registro removido com sucesso!");
        } catch (Exception e) {
            attributes.addFlashAttribute("erro",
                    "Falha ao remover o registro!");
        }
        return "redirect:/recheio";
    }
}
