package br.mg.jcls.pagamento.message;

import br.mg.jcls.pagamento.entity.Produto;
import br.mg.jcls.pagamento.repository.ProdutoRepository;
import br.mg.jcls.pagamento.vo.ProdutoVO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ProdutoReceiveMessage {

    private final ProdutoRepository repository;

    @Autowired
    public ProdutoReceiveMessage(ProdutoRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = {"${crud.rabbitmq.queue}"})
    public void receive(@Payload ProdutoVO produtoVO) {
        repository.save(Produto.create(produtoVO));
    }
}
