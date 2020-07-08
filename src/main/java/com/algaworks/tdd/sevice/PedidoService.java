package com.algaworks.tdd.sevice;

import java.util.List;

import com.algaworks.tdd.email.NotificarEmail;
import com.algaworks.tdd.model.Pedido;
import com.algaworks.tdd.model.StatusPedido;
import com.algaworks.tdd.repository.Pedidos;
import com.algaworks.tdd.sms.NotificarSms;

public class PedidoService {

	
	private List<AcaoLancamentoPedido> acoes;  
	private Pedidos pedidos; 
	
	public PedidoService(Pedidos pedidos ,List<AcaoLancamentoPedido> acoes ) {
			this.acoes = acoes;
			this.pedidos = pedidos;
	}

	public double lancar(Pedido pedido) {
		
		acoes.forEach(x-> x.executar(pedido));
		double imposto = pedido.getValor() * 0.10; 
		
		return imposto;
	}

	public Pedido pagar(Long codigo) {
		Pedido pedido = pedidos.buscaPedidoPorCodigo(codigo);
		if(!pedido.getStatusPedido().equals(StatusPedido.PENDENTE))
			throw new StatusPedidoInvalidoException();

	
		pedido.setStatusPedido(StatusPedido.PAGO);

		return pedido;
	}
}
