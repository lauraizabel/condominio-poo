import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import com.github.javafaker.Faker;

import org.junit.Test;

import dados.Compra;
import dados.Produto;

public class CompraTest {
    Faker faker = new Faker();
    TakeClasses takeClasses = new TakeClasses();
    Compra compra = takeClasses.compra();

    @Test
    public void testClassNotNull() {
        assertNotNull(compra);
    }
    
    @Test
    public void testGetProduto() {
        assertEquals(takeClasses.getProduto(), compra.getProduto());
    }

    @Test
    public void testSetProduto() {
        Produto produto = takeClasses.produto();
        compra.setProduto(produto);
        assertEquals(produto, compra.getProduto());
    }

    @Test
    public void testGetDataAlteracao() {
        assertEquals(takeClasses.getData(), compra.getDataAlteracao());
    }

    @Test
    public void testSetDataAlteracao() {
        Date newData = faker.date().birthday();
        compra.setDataAlteracao(newData);
        assertEquals(newData, compra.getDataAlteracao());
    }

    @Test
    public void testGetValorUnitario() {
        assertEquals(takeClasses.getValor(), compra.getValorUnitario());
    }
}
