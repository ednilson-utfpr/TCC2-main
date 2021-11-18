package br.edu.utfpr.pb.tcc.controller;

import br.edu.utfpr.pb.tcc.model.Categoria;
import br.edu.utfpr.pb.tcc.model.Cliente;
import br.edu.utfpr.pb.tcc.model.Recheio;
import br.edu.utfpr.pb.tcc.model.Usuario;
import br.edu.utfpr.pb.tcc.service.ClienteService;
import br.edu.utfpr.pb.tcc.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PedidoController pedidoController;

    @Autowired
    private PedidoItemController pedidoItemController;




    @GetMapping
    public String list(Model model) {
        model.addAttribute("clientes", clienteService.findAll());

            return "/index";

    }

    @GetMapping(value = {"new", "novo", "form"})
    public String form(Model model) {
        model.addAttribute("cliente", new Cliente());
        carregarCombosUsuario(model);
        return "cliente/form";
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid Cliente cliente, BindingResult result, Model model, RedirectAttributes attributes) {
        if ( result.hasErrors() ) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        cliente.setUsuario((Usuario)(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
        clienteService.save(cliente);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{id}")
    public String form(@PathVariable Long id, Model model) {
        model.addAttribute("cliente", clienteService.findOne(id));
        carregarCombosUsuario(model);
        return "cliente/form";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        try {
            clienteService.delete(id);
            attributes.addFlashAttribute("sucesso", "Registro removido com sucesso!");
        } catch (Exception e) {
            attributes.addFlashAttribute("erro", "Falha ao remover o registro!");
        }
        return "redirect:/cliente";
    }

    //Carrega os combos de user na edição da tabela
    private void carregarCombosUsuario(Model model) {
        model.addAttribute("usuarios", usuarioService.findAll());
    }
}