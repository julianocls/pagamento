package br.mg.jcls.pagamento.vo;

import br.mg.jcls.pagamento.entity.Produto;
import br.mg.jcls.pagamento.entity.ProdutoVenda;
import br.mg.jcls.pagamento.entity.Venda;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonPropertyOrder({"id", "estoque"})
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class ProdutoVO extends RepresentationModel<ProdutoVO> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("estoque")
    private Integer estoque;

    public static ProdutoVO create(Produto produto) {
        return new ModelMapper().map(produto, ProdutoVO.class);
    }
}