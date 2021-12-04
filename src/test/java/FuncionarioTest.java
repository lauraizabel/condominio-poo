import com.github.javafaker.Faker;
import dados.Funcionario;
import org.junit.*;

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.*;

public class FuncionarioTest  {
    Faker fake = new Faker();

    private Integer id = 1;
    private String name = fake.name().firstName();
    private String phone = fake.phoneNumber().cellPhone();
    private String email = "email@email.com";
    private String cpf = fake.random().toString();
    private String cargo = fake.commerce().department();
    private double salario = fake.random().nextDouble();
    private String endereco = fake.address().fullAddress();
    private Date dataAdmissao = fake.date().birthday();
    private Date dataDemissao = fake.date().birthday();




    private Funcionario funcionario = new Funcionario(id, name, phone, email, cpf, cargo, salario, endereco, dataAdmissao, dataDemissao);

    /* Testando se a classe não iniciou como null */
    @Test
    public void TestClassNotNull() {
        assertNotNull(funcionario);
    }

    /* Testando Getters */
    @Test
    public void TestGetterId () {
        assertEquals(id, funcionario.getId());
    }

    @Test
    public void TestGetterName () {
        assertEquals(name, funcionario.getNome());
    }

    @Test
    public void TestGetterPhone () {
        assertEquals(phone, funcionario.getTelefone());
    }

    @Test
    public void TestGetterEmail () {
        assertEquals(email, funcionario.getEmail());
    }

    @Test
    public void TestGetterCargo () {
        assertEquals(cargo, funcionario.getCargo());
    }

    @Test
    public void TestGetterEndereco () {
        assertEquals(endereco, funcionario.getEndereco());
    }

    @Test
    public void TestGetterSalario () {
        assertTrue(salario == funcionario.getSalario());
    }

    @Test
    public void TestGetterDataAdmissao () {
        assertEquals(dataAdmissao, funcionario.getDataAdmissao());
    }

    @Test
    public void TestGetterDataDemissao () {
        assertEquals(dataDemissao, funcionario.getDataDemissao());
    }

    @Test
    public void TestGetterCPF () {
        assertEquals(cpf, funcionario.getCpf());
    }


    /* Testando Setters */
    @Test
    public void TestSetCPF () {
        String newCpf = fake.random().toString();
        funcionario.setCpf(newCpf);
        assertEquals(newCpf, funcionario.getCpf());
    }

    @Test
    public void TestSetName() {
        String newName = fake.name().fullName();
        funcionario.setNome(newName);
        assertEquals(newName, funcionario.getNome());
    }

    @Test
    public void TestSetPhone() {
        String newName = fake.phoneNumber().cellPhone();
        funcionario.setNome(newName);
        assertEquals(newName, funcionario.getNome());
    }

    @Test
    public void TestSetName() {
        String newName = fake.name().fullName();
        funcionario.setNome(newName);
        assertEquals(newName, funcionario.getNome());
    }
    @Test
    public void TestSetName() {
        String newName = fake.name().fullName();
        funcionario.setNome(newName);
        assertEquals(newName, funcionario.getNome());
    }

    @Test
    public void TestSetName() {
        String newName = fake.name().fullName();
        funcionario.setNome(newName);
        assertEquals(newName, funcionario.getNome());
    }
    @Test
    public void TestSetName() {
        String newName = fake.name().fullName();
        funcionario.setNome(newName);
        assertEquals(newName, funcionario.getNome());
    }

    @Test
    public void TestSetName() {
        String newName = fake.name().fullName();
        funcionario.setNome(newName);
        assertEquals(newName, funcionario.getNome());
    }

    @Test
    public void TestSetName() {
        String newName = fake.name().fullName();
        funcionario.setNome(newName);
        assertEquals(newName, funcionario.getNome());
    }
    @Test
    public void TestSetName() {
        String newName = fake.name().fullName();
        funcionario.setNome(newName);
        assertEquals(newName, funcionario.getNome());
    }
    @Test
    public void TestSetName() {
        String newName = fake.name().fullName();
        funcionario.setNome(newName);
        assertEquals(newName, funcionario.getNome());
    }

    @Test
    public void TestSetName() {
        String newName = fake.name().fullName();
        funcionario.setNome(newName);
        assertEquals(newName, funcionario.getNome());
    }






}
