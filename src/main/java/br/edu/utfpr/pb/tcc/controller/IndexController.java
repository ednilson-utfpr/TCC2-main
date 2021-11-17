package br.edu.utfpr.pb.tcc.controller;

import br.edu.utfpr.pb.tcc.model.*;
import br.edu.utfpr.pb.tcc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class IndexController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PedidoService pedidoService;

    @GetMapping(value = {"", "index", "home"}) // /produto?page=1&size=5
    public String list(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size, Model model) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        Page<Produto> list = this.produtoService.findAll(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("produtos", list);

        if (list.getTotalPages() > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, list.getTotalPages()).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("categorias", categoriaService.findAll());

        return "index";
    }

    @GetMapping("cart")
    public String indexCart(Model model) {
        return "cart";
    }

    @GetMapping("checkout")
    public String indexCheckout(Model model) {
        Usuario u = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Cliente c = clienteService.findClienteByUsuarioId(u.getId());
        model.addAttribute("cliente", c == null ? new Cliente() : c);
        return "checkout";
    }

    @GetMapping("account")
    public String indexAccount(Model model) {
        Usuario u = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Cliente c = clienteService.findClienteByUsuarioId(u.getId());
        if(c != null){
            List<Pedido> pedidos = pedidoService.findAllByClienteId(c.getId());
            if(pedidos.size() > 0){
                model.addAttribute("pedidos", pedidos);
            }
            model.addAttribute("cliente", c);
        }
        return "account";
    }
}
