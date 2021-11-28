package br.edu.utfpr.pb.tcc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Cobertura implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O campo não pode ser vazio!")
    @Column(length = 300, nullable = false)
    private String nome;

    @NotNull(message = "O campo não pode ser vazio!")
    @Column(nullable = false)
    private Double valor;

    public String toString()
    {

        return getNome() + " -  Valor: R$" + getValor();
    }

}
