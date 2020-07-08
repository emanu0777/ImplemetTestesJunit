package com.algaworks.tdd.sms;

import com.algaworks.tdd.model.Pedido;
import com.algaworks.tdd.sevice.AcaoLancamentoPedido;

public class NotificarSms implements AcaoLancamentoPedido{

	
	public void executar(Pedido pedido) {
		System.out.println("Enviando Sms ...");
	}
}
