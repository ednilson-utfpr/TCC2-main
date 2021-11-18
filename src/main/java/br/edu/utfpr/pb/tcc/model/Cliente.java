package br.edu.utfpr.pb.tcc.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O campo não pode ser vazio!")
    @Column(length = 60, nullable = false)
    private String nome;

    @NotEmpty(message = "O campo não pode ser vazio!")
    @Column(length = 14, nullable = false)
    private String cpf;

    @NotEmpty(message = "O campo não pode ser vazio!")
    @Column(length = 20, nullable = false)
    private String telefone;

    @NotEmpty(message = "O campo não pode ser vazio!")
    @Column(length = 200, nullable = false)
    private String endereco;

    @Column(length = 200, nullable = false)
    private String enderecoEntrega;

    @NotEmpty(message = "O campo não pode ser vazio!")
    @Column(length = 30, nullable = false)
    private String bairro;

    @NotEmpty(message = "O campo não pode ser vazio!")
    @Column(length = 50, nullable = false)
    private String cidade;

    @NotEmpty(message = "O campo não pode ser vazio!")
    @Column(length = 30, nullable = false)
    private String estado;

    @NotEmpty(message = "O campo não pode ser vazio!")
    @Column(length = 10, nullable = false)
    private String cep;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
