package br.edu.utfpr.pb.tcc.controller;

import br.edu.utfpr.pb.tcc.model.Usuario;
import br.edu.utfpr.pb.tcc.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired private UsuarioService usuarioService;

    @GetMapping("new")
    public String novo(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "new";
    }

    @PostMapping
    public String save(@Valid Usuario usuario, BindingResult result, Model model, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute("usuario", usuario);
            return "new";
        }

        Usuario user = usuarioService.findByEmail(usuario.getEmail());
        if (user != null) {
            result.rejectValue("email", null, "O email informado já está cadastrado.");
            return "new";
        }

        Usuario user1 = usuarioService.findByUsername(usuario.getUsername());
        if (user1 != null) {
            result.rejectValue("username", null, "O usuário informado já está cadastrado.");
            return "new";
        }

        usuario.setPassword(BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt()));
        usuarioService.save(usuario);
        attributes.addFlashAttribute("sucesso", "Usuário salvo com sucesso!");
        return "redirect:/login";
    }
}