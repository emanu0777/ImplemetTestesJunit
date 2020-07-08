package com.algaworks.tdd.email;

import com.algaworks.tdd.model.Pedido;
import com.algaworks.tdd.sevice.AcaoLancamentoPedido;


public class NotificarEmail implements AcaoLancamentoPedido {

	public void executar(Pedido pedido) {
		System.out.println("Email");
	}
}
