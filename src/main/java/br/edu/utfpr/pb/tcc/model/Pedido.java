package br.edu.utfpr.pb.tcc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pedido implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "situacao_id", referencedColumnName = "id")
    private Situacao situacao;

    @Column(nullable = false)
    private Double valorTotal;

    @Column(nullable = false)
    private Double valorFrete;

    @Column(nullable = false)
    private Integer tipoFrete;


    @Column(length = 50)
    private String tipoPagamento;

    @Column(nullable = false)
    private LocalDate dataPedido;

    @Column
    private LocalDate dataAlteracao;
}
