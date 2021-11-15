package br.edu.utfpr.pb.tcc.controller;

import br.edu.utfpr.pb.tcc.model.*;

import br.edu.utfpr.pb.tcc.service.ClienteService;
import br.edu.utfpr.pb.tcc.service.CategoriaService;
import br.edu.utfpr.pb.tcc.service.PedidoItemService;
import br.edu.utfpr.pb.tcc.service.PedidoService;
import br.edu.utfpr.pb.tcc.service.SituacaoService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoItemService pedidoItemService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private SituacaoService situacaoService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("pedidos", pedidoService.findAll());
        return "pedido/list";
    }

    @GetMapping(value = {"pesquisar"})
    public String pesquisar() {
        return "pedido/buscapedido";
    }


    @GetMapping(value = {"new", "novo", "form"})
    public String form(Model model) {
        model.addAttribute("pedido", new Pedido());
        carregarCombosCliente(model);
        carregarCombosCategoria(model);
        carregarCombosSituacao(model);
        return "pedido/formSituacao";
    }


    //SALVA O PEDIDO
    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid Map<String, Object> json, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ObjectMapper mapper = new ObjectMapper();
        Pedido pedido = mapper.convertValue(json.get("pedido"), Pedido.class);

        Usuario u = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Cliente c = clienteService.findClienteByUsuarioId(u.getId());
        pedido.setCliente(c);
        pedido.setDataPedido(LocalDate.now());
        pedido.setDataAlteracao(LocalDate.now());

        //Tipo de Pagamento
        if (pedido.getTipoPagamento().equals("0")) {
            pedido.setTipoPagamento("Transferência Bancária");
        } else if (pedido.getTipoPagamento().equals("1")) {
            pedido.setTipoPagamento("Boleto");
        } else if (pedido.getTipoPagamento().equals("2")) {
            pedido.setTipoPagamento("PayPal");
        } else if (pedido.getTipoPagamento().equals("3")) {
            pedido.setTipoPagamento("PIX");
        }
        //salva a situação como Pedido Efetuado
        pedido.setSituacao(situacaoService.findOne(1L));
        pedidoService.save(pedido);

        //Percorrer os itemsPedido e salvar o código do pedido
        List<Map<String, Object>> pedidoItems = mapper.convertValue(json.get("pedidoItem"), new TypeReference<>() {
        });
        pedidoItems.forEach((pedidoItem) -> {
            PedidoItem p = mapper.convertValue(pedidoItem, PedidoItem.class);
            p.setPedido(pedido);
            pedidoItemService.save(p);
        });

        return new ResponseEntity<>(HttpStatus.OK);
    }


    //EDITAR PRODUTO
    @GetMapping("{id}")
    public String form(@PathVariable Long id, Model model) {
        model.addAttribute("pedido", pedidoService.findOne(id));
        carregarCombosCliente(model);
        carregarCombosCategoria(model);
        carregarCombosSituacao(model);
        return "pedido/formSituacao";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        try {
            pedidoService.delete(id);
            attributes.addFlashAttribute("sucesso", "Registro removido com sucesso!");
        } catch (Exception e) {
            attributes.addFlashAttribute("erro", "Falha ao remover o registro!");
        }
        return "redirect:/pedido";
    }


    ////MUDAR STATUS SITUAÇÃO PEDIDO
    @PostMapping(value = {"/situacao"})
    public String save(@Valid Pedido pedido, BindingResult result, Model model, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            model.addAttribute("pedido", pedido);
            return "pedido/formSituacao";
        }
        pedidoService.save(pedido);
        attributes.addFlashAttribute("sucesso", "Registro salvo com sucesso!");
        return "redirect:/pedido";
    }


    //Carrega os combos de clientes na edição da tabela
    private void carregarCombosCliente(Model model) {
        model.addAttribute("clientes", clienteService.findAll());
    }

    //Carrega os combos de clientes na edição da tabela
    private void carregarCombosCategoria(Model model) {
        model.addAttribute("categorias", categoriaService.findAll());
    }

    //Carrega os combos de Situação na edição da tabela
    private void carregarCombosSituacao(Model model) {
        model.addAttribute("situacoes", situacaoService.findAll());
    }
}