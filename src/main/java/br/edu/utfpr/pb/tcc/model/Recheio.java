package br.edu.utfpr.pb.tcc.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Recheio implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O campo não pode ser vazio!")
    @Column(length = 50, nullable = false)
    private String nome;


    @Column(nullable = false)
    private Double valor;

    public String toString()
    {
        return getNome() + " - Valor: R$" + getValor();
    }

}

