package com.estudospring.model;

import javax.persistence.Entity;

import com.estudospring.model.enums.EstadoPagamento;

@Entity
public class PagamentoCartao extends Pagamento{

	private Integer numeroParcelas;
	
	public PagamentoCartao() {
		
	}

	public PagamentoCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroParcelas) {
		super(id, estado, pedido);
		this.numeroParcelas = numeroParcelas;
	}

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}
	
	
}
