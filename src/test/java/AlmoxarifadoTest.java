import static org.junit.Assert.*;

import java.util.ArrayList;

import com.github.javafaker.Faker;

import org.junit.Test;

import dados.Almoxarifado;
import dados.Produto;

public class AlmoxarifadoTest {
    Faker faker = new Faker();
    TakeClasses takeClasses = new TakeClasses();
    Almoxarifado almoxarifado = takeClasses.almoxarifado();

    @Test
    public void TestClassNotNull() {
        assertNotNull(almoxarifado);
    }

    @Test
    public void TestGetProduto(){
        assertEquals(takeClasses.getAlmoxarifado().getProdutos(), almoxarifado.getProdutos());
    }

    @Test
    public void TestSetProduto(){
        ArrayList<Produto> newProdutos = new ArrayList<>();
        almoxarifado.setProdutos(newProdutos);
        assertEquals(newProdutos, almoxarifado.getProdutos());
    }
}
