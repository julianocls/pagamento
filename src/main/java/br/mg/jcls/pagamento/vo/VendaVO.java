package br.mg.jcls.pagamento.vo;

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

@JsonPropertyOrder({"id", "data", "produtos", "valorTotal"})
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class VendaVO extends RepresentationModel<VendaVO> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("data")
    private Date data;

    @JsonProperty("produtos")
    private List<ProdutoVenda> produtos = new ArrayList<>(); ;

    @JsonProperty("valorTotal")
    private Double valorTotal;

    public static VendaVO create(Venda venda) {
        return new ModelMapper().map(venda, VendaVO.class);
    }
}