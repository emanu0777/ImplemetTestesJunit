package com.algaworks.tdd.repository;

import com.algaworks.tdd.model.Pedido;
import com.algaworks.tdd.sevice.AcaoLancamentoPedido;

public class Pedidos implements AcaoLancamentoPedido {

	@Override
	public void executar(Pedido pedido) {
		System.out.println("Salvando no banco de dado...");	
	}
	
	public Pedido buscaPedidoPorCodigo(Long codigo) {
		return new Pedido();
	}
}
