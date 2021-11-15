package br.edu.utfpr.pb.tcc.controller;

import br.edu.utfpr.pb.tcc.model.PedidoItem;
import br.edu.utfpr.pb.tcc.service.PedidoItemService;
import br.edu.utfpr.pb.tcc.service.ProdutoCustomizadoService;
import br.edu.utfpr.pb.tcc.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("pedidoItem")
public class PedidoItemController {

    @Autowired
    private PedidoItemService pedidoItemService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoCustomizadoService produtoCustomizadoService;

    @GetMapping(value = {"new", "novo", "form"})
    public String form(Model model) {
        model.addAttribute("pedidoItem", new PedidoItem());
        return "pedidoItem/form";
    }

    @PostMapping
    public String save(@Valid PedidoItem pedidoItem, BindingResult result, Model model, RedirectAttributes attributes) {
        System.out.println(pedidoItem);
        if ( result.hasErrors() ) {
            model.addAttribute("pedidoItem", pedidoItem);
            return "pedidoItem/form";
        }
        pedidoItemService.save(pedidoItem);
        attributes.addFlashAttribute("sucesso", "Registro salvo com sucesso!");
        return "redirect:/produto";
    }

    @PostMapping("{id}")
    public String save(@PathVariable Long id, @Valid PedidoItem pedidoItem, BindingResult result, Model model, RedirectAttributes attributes) {
        if ( result.hasErrors() ) {
            model.addAttribute("pedidoItem", pedidoItem);
            return "pedidoItem/form";
        }
        pedidoItem.setProduto(produtoService.findOne(id));
        //pedido item prod customizado
        pedidoItem.setProdutoCustomizado(produtoCustomizadoService.findOne(id));
        System.out.println(pedidoItem);

        pedidoItemService.save(pedidoItem);
        attributes.addFlashAttribute("sucesso", "Registro salvo com sucesso!");
        return "redirect:/pedidoItem";
    }

    @GetMapping("/pedido/{id}")
    public String findPedidoItemByPedido(@PathVariable Long id, Model model) {
        List<PedidoItem> pedidoItems = pedidoItemService.findAllByPedidoId(id);
        double total = pedidoItems.stream().map(PedidoItem::getTotal).reduce(0.0, Double::sum);
        DecimalFormat df = new DecimalFormat("¤ ###,###,##0.00", new DecimalFormatSymbols(new Locale("pt","BR")));
        model.addAttribute("pedidoItems", pedidoItems);
        model.addAttribute("total", df.format(total));

        return "pedidoItem/list";
    }

    @GetMapping("{id}")
    public String form(@PathVariable Long id, Model model) {
        model.addAttribute("pedidoItem", pedidoItemService.findOne(id));
        return "pedidoItem/form";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        try {
            pedidoItemService.delete(id);
            attributes.addFlashAttribute("sucesso", "Registro removido com sucesso!");
        } catch (Exception e) {
            attributes.addFlashAttribute("erro", "Falha ao remover o registro!");
        }
        return "redirect:/cart";
    }
}