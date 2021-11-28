package br.edu.utfpr.pb.tcc.controller;



import br.edu.utfpr.pb.tcc.model.Cobertura;
import br.edu.utfpr.pb.tcc.service.CoberturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.validation.Valid;


@Controller
@RequestMapping("cobertura")
public class CoberturaController {

    @Autowired
    private CoberturaService coberturaService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("coberturas", coberturaService.findAll());
        return "cobertura/list";
    }

    @GetMapping(value = {"new", "novo", "form"})
    public String form(Model model) {
        model.addAttribute("cobertura", new Cobertura());
        return "cobertura/form";
    }

    @PostMapping
    public String save(@Valid Cobertura cobertura, BindingResult result, Model model, RedirectAttributes attributes) {
        if ( result.hasErrors() ) {
            model.addAttribute("cobertura", cobertura);
            return "cobertura/form";
        }

        coberturaService.save(cobertura);
        attributes.addFlashAttribute("sucesso",
                "Registro salvo com sucesso!");
        return "redirect:/cobertura";
    }

    @GetMapping("{id}")
    public String form(@PathVariable Long id, Model model) {
        model.addAttribute("cobertura", coberturaService.findOne(id));
        return "cobertura/form";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Long id,
                         RedirectAttributes attributes) {
        try {
            coberturaService.delete(id);
            attributes.addFlashAttribute("sucesso",
                    "Registro removido com sucesso!");
        } catch (Exception e) {
            attributes.addFlashAttribute("erro",
                    "Falha ao remover o registro!");
        }
        return "redirect:/cobertura";
    }
}
