package com.estudospring;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.estudospring.model.Categoria;
import com.estudospring.model.Cidade;
import com.estudospring.model.Cliente;
import com.estudospring.model.Endereco;
import com.estudospring.model.Estado;
import com.estudospring.model.ItemPedido;
import com.estudospring.model.Pagamento;
import com.estudospring.model.PagamentoBoleto;
import com.estudospring.model.PagamentoCartao;
import com.estudospring.model.Pedido;
import com.estudospring.model.Produto;
import com.estudospring.model.enums.EstadoPagamento;
import com.estudospring.model.enums.TipoCliente;
import com.estudospring.repository.CategoriaRepository;
import com.estudospring.repository.CidadeRepository;
import com.estudospring.repository.ClienteRepository;
import com.estudospring.repository.EnderecoRepository;
import com.estudospring.repository.EstadoRepository;
import com.estudospring.repository.ItemPedidoRepository;
import com.estudospring.repository.PagamentoRepository;
import com.estudospring.repository.PedidoRepository;
import com.estudospring.repository.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoria;
	
	@Autowired
	private ProdutoRepository produto;
	
	@Autowired
	private CidadeRepository cidade;
	
	@Autowired 
	private EstadoRepository estado;
	
	@Autowired
	private ClienteRepository cliente;
	
	@Autowired
	private EnderecoRepository endereco;
	
	@Autowired
	private PagamentoRepository pagamento;
	
	@Autowired
	private PedidoRepository pedido;
	
	@Autowired
	private ItemPedidoRepository itemPedido;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade city1 = new Cidade(null, "Belo Horizonte", est1);
		Cidade city2 = new Cidade(null, "São Paulo", est2);
		Cidade city3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(city1));
		est2.getCidades().addAll(Arrays.asList(city2, city3));
		
		Cliente c1 = new Cliente(null, "Maria Aparecida", "maria@gmail.com", "42256467856", TipoCliente.PESSOAFISICA);
		c1.getTelefones().addAll(Arrays.asList("958959292", "58915666"));
		
		Endereco e1 = new Endereco(null, "Rua um", "300", "Apto 303", "Jardim", "38220834", c1, city1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", c1, city2);
		c1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date data = new Date();
		
		Pedido pd1 = new Pedido(null, data, c1, e1);
		Pedido pd2 = new Pedido(null, data, c1, e2);
		
		Pagamento pag = new PagamentoCartao(null, EstadoPagamento.QUITADO, pd1, 6);
		pd1.setPagamento(pag);
		
		Pagamento pag2 = new PagamentoBoleto(null, EstadoPagamento.PEDENTE, pd2, data, data);
		pd2.setPagamento(pag2);
		
		c1.getPedidos().addAll(Arrays.asList(pd1, pd2));
		
		ItemPedido ip1 = new ItemPedido(pd1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(pd1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(pd2, p2, 100.00, 1, 800.00);
		
		pd1.getItens().addAll(Arrays.asList(ip1, ip2));
		pd2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		categoria.saveAll(Arrays.asList(cat1, cat2));
		produto.saveAll(Arrays.asList(p1, p2, p3));
		estado.saveAll(Arrays.asList(est1, est2));
		cidade.saveAll(Arrays.asList(city1, city2, city3));
		cliente.saveAll(Arrays.asList(c1));
		endereco.saveAll(Arrays.asList(e1, e2));
		pedido.saveAll(Arrays.asList(pd1, pd2));
		pagamento.saveAll(Arrays.asList(pag, pag2));
		itemPedido.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}
