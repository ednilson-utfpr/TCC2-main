package br.edu.utfpr.pb.tcc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class PedidoItem implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id", referencedColumnName = "id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "produtocustomizado_id", referencedColumnName = "id")
    private ProdutoCustomizado produtoCustomizado;

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private Integer quantidade;

    public double getTotal(){
        BigDecimal x = new BigDecimal(valor * quantidade, MathContext.DECIMAL32).setScale(2);
        return x.doubleValue();
    }

    public String getTotalFormat(){
        DecimalFormat df = new DecimalFormat("¤ ###,###,##0.00", new DecimalFormatSymbols(new Locale("pt","BR")));
        return df.format(valor * quantidade);
    }
}
