import static org.junit.Assert.*;

import com.github.javafaker.Faker;

import org.junit.Test;

import dados.Produto;

public class ProdutoTest {
  Faker fake = new Faker();
  TakeClasses takeClasses = new TakeClasses();
  Produto produto = takeClasses.produto();

  @Test
  public void testGetCodigo() {
    assertEquals(takeClasses.getProduto().getCodigo(), produto.getCodigo());
  }

  @Test
  public void testGetFornecedor() {
    assertEquals(takeClasses.getProduto().getFornecedor(), produto.getFornecedor());
  }

  @Test
  public void testGetNome() {
    assertEquals(takeClasses.getProduto().getNome(), produto.getNome());
  }

  @Test
  public void testSetNome() {
    String novoNome = fake.name().fullName();
    produto.setNome(novoNome);
    assertEquals(novoNome, produto.getNome());
  }

  @Test
  public void testSetCod() {
    String novoCod = fake.idNumber().valid();
    produto.setCodigo(novoCod);
    assertEquals(novoCod, produto.getCodigo());
  }
}
