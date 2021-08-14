package com.ejs.algaworksCurso.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.ejs.algaworksCurso.domain.event.PedidoCanceladoEvent;
import com.ejs.algaworksCurso.domain.model.Pedido;
import com.ejs.algaworksCurso.domain.services.EnvioEmailService;
import com.ejs.algaworksCurso.infrastructure.email.Mensagem;

@Component
public class NotificacaoClientePedidoCanceladoListener {

	@Autowired
	private EnvioEmailService emailService;
	
//	@EventListener
//	@TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void aoCancelarPedido(PedidoCanceladoEvent event) {
		Pedido pedido = event.getPedido();
		Mensagem mensagem = new Mensagem.Builder()
				.assunto("Cancelamento pedido")
				.corpo("pedido-cancelado.html" )
				.variaveis("pedido", pedido)
				.destinatarios(pedido.getCliente().getEmail())
				.build();
		emailService.enviar(mensagem);
	}
}
