package br.mg.jcls.pagamento.services;

import br.mg.jcls.pagamento.entity.ProdutoVenda;
import br.mg.jcls.pagamento.entity.Venda;
import br.mg.jcls.pagamento.repository.ProdutoVendaRepository;
import br.mg.jcls.pagamento.repository.VendaRepository;
import br.mg.jcls.pagamento.services.exception.ResourceNotFoundException;
import br.mg.jcls.pagamento.vo.VendaVO;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class VendaService {

    public static final String NO_RECORD_FOUND_FOR_THIS_ID = "No record found for this ID";
    private final VendaRepository vendaRepository;
    private ProdutoVendaRepository produtoVendaRepository;

    @Autowired
    public VendaService(VendaRepository vendaRepository, ProdutoVendaRepository produtoVendaRepository) {
        this.vendaRepository = vendaRepository;
        this.produtoVendaRepository = produtoVendaRepository;
    }

    public VendaVO create(VendaVO vendaVO) {
        var venda = vendaRepository.save(Venda.create(vendaVO));

        venda = salvaItens(vendaVO, venda);

        return VendaVO.create(venda);
    }

    public Page<VendaVO> findAll(Pageable pageable) {
        var page = vendaRepository.findAll(pageable);
        return page.map(this::convertToVendaVO);
    }

    public VendaVO findById(Long id) {
        var entity = vendaRepository.findById(id)
                .orElseThrow( () ->  new ResourceNotFoundException(NO_RECORD_FOUND_FOR_THIS_ID));
        return VendaVO.create(entity);
    }

    public VendaVO update(VendaVO vendaVO) {
        final var entityOptional = vendaRepository.findById(vendaVO.getId());

        if (!entityOptional.isPresent()) {
            throw new ResourceNotFoundException(NO_RECORD_FOUND_FOR_THIS_ID);
        }

        var venda = vendaRepository.save(Venda.create(vendaVO));
        venda = salvaItens(vendaVO, venda);

        return VendaVO.create(venda);
    }

    public void delete(Long id) {
        var entity = vendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NO_RECORD_FOUND_FOR_THIS_ID));
        vendaRepository.delete(entity);
    }

    private VendaVO convertToVendaVO(Venda venda) {
        return VendaVO.create(venda);
    }

    private Venda salvaItens(VendaVO vendaVO, Venda venda) {
        var produtosSalvos = new ArrayList<ProdutoVenda>();
        vendaVO.getProdutos().forEach(p -> {
            ProdutoVenda pv = ProdutoVenda.create(p);
            pv.setVenda(venda);
            produtosSalvos.add(produtoVendaRepository.save(pv));
        });
        venda.setProdutos(produtosSalvos);
        return venda;
    }
}