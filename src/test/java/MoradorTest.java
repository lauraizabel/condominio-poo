import static org.junit.Assert.*;

import com.github.javafaker.Faker;

import org.junit.Test;

import dados.Morador;

public class MoradorTest {
  Faker fake = new Faker();
  TakeClasses takeClasses = new TakeClasses();
  Morador morador = takeClasses.morador();

  @Test
  public void testGetId() {
    assertEquals(takeClasses.getId(), morador.getId());
  }

  @Test
  public void testGetNome() {
    assertEquals(takeClasses.getName(), morador.getNome());
  }

  @Test
  public void testGetCpf() {
    assertEquals(takeClasses.getCpf(), morador.getCpf());
  }

  @Test
  public void testGetEmail() {
    assertEquals(takeClasses.getEmail(), morador.getEmail());
  }

  @Test
  public void testGetTelefone() {
    assertEquals(takeClasses.getPhone(), morador.getTelefone());
  }

  @Test
  public void testSetNome() {
    String novoNome = fake.name().fullName();
    morador.setNome(novoNome);
    assertEquals(novoNome, morador.getNome());
  }

  @Test
  public void testSetCpf() {
    String novoCpf = fake.idNumber().ssnValid();
    morador.setCpf(novoCpf);
    assertEquals(novoCpf, morador.getCpf());
  }
}
