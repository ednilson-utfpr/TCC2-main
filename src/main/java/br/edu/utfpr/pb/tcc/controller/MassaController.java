package br.edu.utfpr.pb.tcc.controller;

import br.edu.utfpr.pb.tcc.model.Massa;
import br.edu.utfpr.pb.tcc.service.MassaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("massa")
public class MassaController {

    @Autowired
    private MassaService massaService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("massas", massaService.findAll());
        return "massa/list";
    }

    @GetMapping(value = {"new", "novo", "form"})
    public String form(Model model) {
        model.addAttribute("massa", new Massa());
        return "massa/form";
    }

    @PostMapping
    public String save(@Valid Massa massa,
                       BindingResult result,
                       Model model,
                       RedirectAttributes attributes) {
        if ( result.hasErrors() ) {
            model.addAttribute("massa", massa);
            return "massa/form";
        }

        massaService.save(massa);
        attributes.addFlashAttribute("sucesso",
                "Registro salvo com sucesso!");
        return "redirect:/massa";
    }

    @GetMapping("{id}")
    public String form(@PathVariable Long id, Model model) {
        model.addAttribute("massa", massaService.findOne(id));
        return "massa/form";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Long id,
                         RedirectAttributes attributes) {
        try {
            massaService.delete(id);
            attributes.addFlashAttribute("sucesso",
                    "Registro removido com sucesso!");
        } catch (Exception e) {
            attributes.addFlashAttribute("erro",
                    "Falha ao remover o registro!");
        }
        return "redirect:/massa";
    }
}




