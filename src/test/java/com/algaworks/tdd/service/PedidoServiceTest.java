package com.algaworks.tdd.service;


import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.algaworks.tdd.email.NotificarEmail;
import com.algaworks.tdd.model.Pedido;
import com.algaworks.tdd.model.StatusPedido;
import com.algaworks.tdd.model.builder.PedidoBuilder;
import com.algaworks.tdd.repository.Pedidos;
import com.algaworks.tdd.sevice.AcaoLancamentoPedido;
import com.algaworks.tdd.sevice.PedidoService;
import com.algaworks.tdd.sms.NotificarSms;

public class PedidoServiceTest {

	private PedidoService pedidoService;
	
	@Mock
	private Pedidos pedidos;
	
	@Mock
	private NotificarEmail notificarEmail;
	
	@Mock
	private NotificarSms notificarSms;
	
	
	private Pedido pedido;
		
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		List<AcaoLancamentoPedido> acoes =  Arrays.asList(pedidos,notificarEmail, notificarSms);
		pedidoService = new PedidoService(pedidos, acoes);
		pedido = new PedidoBuilder().para("Joao", "joao_ls@gmail.com", "9999-19231")
			  	   .comValor(100.0)
			  	   .construir();
	}
	
	
	@Test
	public void deveCalcularImposto() throws Exception {
		 
			double valorDesconto = pedidoService.lancar(pedido);	
			assertEquals(10.0, valorDesconto,0.0001);
		
	}
	
 
	@Test
	public void devePermitirSalvarNoBanco() throws Exception {
		pedidoService.lancar(pedido);
		Mockito.verify(pedidos).executar(pedido);
		 
	}
	
	@Test
	public void deveNotificarSms() throws Exception {
		
		pedidoService.lancar(pedido);
		Mockito.verify(notificarSms).executar(pedido);
	}
	
	@Test
	public void deveNotificarEmail() throws Exception {
	
		pedidoService.lancar(pedido);
		Mockito.verify(notificarEmail).executar(pedido);
		 
	}

	@Test
	public void devePagarStatusPendente() throws Exception {
		 Long codigoPedido  = 135L;
		 Pedido pedidoPendente = new Pedido();
		 pedidoPendente.setStatusPedido(StatusPedido.PENDENTE);
		 Mockito.when(pedidos.buscaPedidoPorCodigo(codigoPedido)).thenReturn(pedidoPendente);
		 
		 Pedido pedido = pedidoService.pagar(codigoPedido);
		
		assertEquals(StatusPedido.PAGO, pedido.getStatusPedido()); 
	}
}
