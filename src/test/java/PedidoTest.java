import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import com.github.javafaker.Faker;

import org.junit.Before;
import org.junit.Test;

import dados.Funcionario;
import dados.Pedido;
import dados.Produto;

public class PedidoTest {
  Faker fake = new Faker();

  TakeClasses takeClasses = new TakeClasses();
  Pedido pedido = takeClasses.pedido();

  @Test
  public void testGetId() {
    assertEquals(takeClasses.getPedido().getId(), pedido.getId());
  }

  @Test
  public void testGetRequerente() {
    assertEquals(takeClasses.getPedido().getRequerente(), pedido.getRequerente());
  }

  @Test
  public void testGetProdutos() {
    assertEquals(takeClasses.getPedido().getProduto(), pedido.getProduto());
  }

  @Test
  public void testSetRequerente() {
    Funcionario funcionario = takeClasses.funcionario();
    pedido.setRequerente(funcionario);
    assertEquals(funcionario, pedido.getRequerente());
  }

  @Test
  public void testSetProdutos() {
    Produto p1 = takeClasses.produto();
    Produto p2 = takeClasses.produto();
    ArrayList<Produto> produtos = new ArrayList<Produto>();
    produtos.add(p1);
    produtos.add(p2);
    pedido.setProdutos(produtos);
    assertEquals(produtos, pedido.getProduto());
  }
}
