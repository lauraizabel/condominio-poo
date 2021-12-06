import static org.junit.Assert.*;

import com.github.javafaker.Faker;

import org.junit.Test;

import dados.Fornecedor;
import dados.Funcionario;
import dados.Servico;

public class ServicoTest {
    Faker faker = new Faker();
    TakeClasses takeClasses = new TakeClasses();
    Servico servico = takeClasses.servico();

    @Test
    public void TestClassNotNull() {
        assertNotNull(servico);
    }

              // GETTERS
    @Test
    public void TesteGetId(){
        assertEquals(takeClasses.getId(), servico.getId());
    }

    @Test
    public void TesteGetDescricao(){
        assertEquals(takeClasses.getDescricao(), servico.getDescricao());
    }

    @Test
    public void TesteGetValor(){
        assertEquals(takeClasses.getValor(), servico.getValor());
    }

    @Test
    public void TesteGetCodigo(){
        assertEquals(takeClasses.getCodigo(), servico.getCodigo());
    }

    @Test
    public void TesteGetFuncionario(){
        assertEquals(takeClasses.getFuncionario(), servico.getRequerente());
    }

    @Test
    public void TesteGeFornecedor(){
        assertEquals(takeClasses.getFornecedor(), servico.getFornecedor());
    }

    // setters 
    @Test
    public void TesteSetDescricao(){
        String newDescricao = faker.lorem().word();
        servico.setDescricao(newDescricao);
        assertEquals(newDescricao, servico.getDescricao());
    }

    @Test
    public void TesteSetValor(){
        Double newValor = faker.random().nextDouble();
        servico.setValor(newValor);
        assertEquals(newValor, servico.getValor());
    }

    @Test
    public void TesteSetCodigo(){
        String newCodigo = faker.random().toString();
        servico.setCodigo(newCodigo);
        assertEquals(newCodigo, servico.getCodigo());
    }

    @Test
    public void TesteSetFuncionario(){
        Funcionario newFuncionario = takeClasses.funcionario();
        servico.setRequerente(newFuncionario);
        assertEquals(newFuncionario, servico.getRequerente());
    }

    @Test
    public void TesteSeFornecedor(){
        Fornecedor newFornecedor = takeClasses.fornecedor();
        servico.SetFornecedor(newFornecedor);
        assertEquals(newFornecedor, servico.getFornecedor());
    }
}
