package br.edu.utfpr.pb.tcc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Produto implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "O campo não pode ser vazio!")
	@ManyToOne
	@JoinColumn(name = "categoria_id", referencedColumnName = "id")
	private Categoria categoria;

	@NotEmpty(message = "O campo não pode ser vazio!")
	@Column(length = 40, nullable = false)
	private String nome;

	@NotEmpty(message = "O campo não pode ser vazio!")
	@Column(length = 3000, nullable = false)
	private String descricao;

	@NotNull(message = "O campo não pode ser vazio!")
	@Column(nullable = false)
	private Double valor;

	@NotNull(message = "O campo não pode ser vazio!")
	@Column(nullable = false)
	private Integer estoque;

	@NotEmpty(message = "O campo não pode ser vazio!")
	@Column(length = 300, nullable = true)
	private String imagem;


}
