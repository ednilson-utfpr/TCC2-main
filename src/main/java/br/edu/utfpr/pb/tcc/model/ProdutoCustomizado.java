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
public class ProdutoCustomizado implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
//    @NotEmpty(message="Cobertura deve ser preenchida")
    @JoinColumn(name = "cobertura_id", referencedColumnName = "id")
    private Cobertura cobertura;

    @ManyToOne
//    @NotNull (message="Recheio deve ser preenchido")
    @JoinColumn(name = "recheio_id", referencedColumnName = "id")
    private Recheio recheio;

    @ManyToOne
//    @NotNull (message="Formato deve ser preenchido")
    @JoinColumn(name = "formato_id", referencedColumnName = "id")
    private Formato formato;

    @Column(length = 300, nullable = false)
    private String imagem= "/images/home/meubolo.jpg";

    @Column(nullable = false)
    private Double valor;

//    @NotEmpty(message = "O campo não pode ser vazio!")
//    @Column(length = 400)
//    private String nome;



    public void setImagem(String imagem) {
        this.imagem = "/images/home/meubolo.jpg";
    }


}
