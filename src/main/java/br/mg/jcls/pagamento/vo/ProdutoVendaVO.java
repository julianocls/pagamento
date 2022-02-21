package br.mg.jcls.pagamento.vo;

import br.mg.jcls.pagamento.entity.Produto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@JsonPropertyOrder({"idProduto", "quantidade"})
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class ProdutoVendaVO extends RepresentationModel<ProdutoVendaVO> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("idProduto")
    private Long idProduto;

    @JsonProperty("quantidade")
    private Integer quantidade;

    public static ProdutoVendaVO create(Produto produto) {
        return new ModelMapper().map(produto, ProdutoVendaVO.class);
    }
}