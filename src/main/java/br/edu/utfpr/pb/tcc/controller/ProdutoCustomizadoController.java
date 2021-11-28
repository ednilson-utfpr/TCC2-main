package br.edu.utfpr.pb.tcc.controller;

import br.edu.utfpr.pb.tcc.model.Produto;
import br.edu.utfpr.pb.tcc.model.ProdutoCustomizado;
import br.edu.utfpr.pb.tcc.service.*;

import br.edu.utfpr.pb.tcc.service.impl.CoberturaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("produtocustomizado")
public class ProdutoCustomizadoController {

    @Autowired
    private CoberturaService coberturaService;

    @Autowired
    private RecheioService recheioService;

    @Autowired
    private FormatoService formatoService;

    @Autowired
    private PedidoItemService pedidoItemService;

    @Autowired
    private MassaService massaService;

    @Autowired
    private ProdutoCustomizadoService produtoCustomizadoService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("produtocustomizados", produtoCustomizadoService.findAll());
        return "produtoCustomizado/list";
    }

    //chama o form p add ProdutoCustomizado
    @GetMapping(value = {"new", "novo", "form"})
    public String form(Model model) {
        carregarCombosCobertura(model);
        carregarCombosFormato(model);
        carregarCombosRecheio(model);
        carregarCombosMassa(model);
        model.addAttribute("produtocustomizado", new ProdutoCustomizado());

        return "produtoCustomizado/form";
    }

    //SALVAR PRODUTO CUSTOMIZADO
    @PostMapping
    public String save(@Valid ProdutoCustomizado produtocustomizado, BindingResult result, Model model, RedirectAttributes attributes) {
        //Valida formulário
        if (result.hasErrors()) {
            model.addAttribute("produtocustomizado", produtocustomizado);
            return "produtoCustomizado/form";
        }
        produtoCustomizadoService.save(produtocustomizado);
        attributes.addFlashAttribute("sucesso", "Registro salvo com sucesso e adicionado ao carrinho!");
        attributes.addFlashAttribute("produtoCustomizadoId", produtocustomizado.getId());
        //IMAGEM PADRÃO DO PRODUTO-CUSTOMIZADO
        attributes.addFlashAttribute("produtoCustomizadoImagem", produtocustomizado.getImagem());
        attributes.addFlashAttribute("produtoCustomizadoValor", produtocustomizado.getValor());
        attributes.addFlashAttribute("coberturaNome", produtocustomizado.getCobertura().getNome());
        attributes.addFlashAttribute("recheioNome", produtocustomizado.getRecheio().getNome());
        attributes.addFlashAttribute("formatoNome", produtocustomizado.getFormato().getNome());
        attributes.addFlashAttribute("massaNome", produtocustomizado.getMassa().getNome());
        return "redirect:/index";
    }

    //EDITAR PRODUTO
    @GetMapping("{id}")
    public String form(@PathVariable Long id, Model model) {
        model.addAttribute("produtocustomizado", produtoCustomizadoService.findOne(id));
        //Carrega os combos na edição da tabela
        carregarCombosCobertura(model);
        carregarCombosFormato(model);
        carregarCombosRecheio(model);
        carregarCombosMassa(model);
        return "produtoCustomizado/form";
    }

    //REMOVER PRODUTO
    @DeleteMapping("{id}")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        try {
            produtoCustomizadoService.delete(id);

            attributes.addFlashAttribute("sucesso",
                    "Registro removido com sucesso!");
        } catch (Exception e) {
            attributes.addFlashAttribute("erro",
                    "Falha ao remover o registro!");
        }
        return "redirect:/produtocustomizado";
    }

    //Carrega os combos na edição da tabela
    private void carregarCombosCobertura(Model model) {
        model.addAttribute("coberturas", coberturaService.findAll());
    }

    //Carrega os combos na edição da tabela
    private void carregarCombosFormato(Model model) {
        model.addAttribute("formatos", formatoService.findAll());
    }

    //Carrega os combos na edição da tabela
    private void carregarCombosRecheio(Model model) {
        model.addAttribute("recheios", recheioService.findAll());
    }

    //Carrega os combos na edição da massa
    private void carregarCombosMassa(Model model) {
        model.addAttribute("massas", massaService.findAll());
    }
}
