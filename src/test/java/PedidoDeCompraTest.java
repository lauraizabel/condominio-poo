import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.github.javafaker.Faker;

import org.junit.Test;

import dados.PedidoDeCompra;
import dados.Produto;

public class PedidoDeCompraTest {
    Faker faker = new Faker();
    TakeClasses takeClasses = new TakeClasses();
    PedidoDeCompra pedidoDeCompra = takeClasses.pedidoDeCompra();

    @Test
    public void testClassNotNull() {
        assertNotNull(pedidoDeCompra);
    }

    @Test
    public void testGetProduto() {
        assertEquals(takeClasses.getProduto(), pedidoDeCompra.getProduto());
    }

    @Test
    public void testSetProduto() {
        Produto produto = takeClasses.produto();
        pedidoDeCompra.setProduto(produto);
        assertEquals(produto, pedidoDeCompra.getProduto());
    }
}
