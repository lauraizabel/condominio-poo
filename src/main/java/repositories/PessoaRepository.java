package repositories;

import java.util.List;

import DAO.PessoaDAO;
import dados.Pessoa;

public class PessoaRepository implements IRepository<Pessoa>{
    private PessoaDAO pessoaDAO;

    @Override
    public Pessoa getById(Integer id) {
        Pessoa pessoa = pessoaDAO.getById(id);
        return pessoa;
    }

    @Override
    public List<Pessoa> getAll() {
        List<Pessoa> pessoas = pessoaDAO.getAll(); 
        return pessoas;
    }

    @Override
    public boolean deleteById(Integer id) {
        boolean result = pessoaDAO.deleteById(id);
        return result;
    }

    @Override
    public boolean save(Pessoa pessoa) {
        boolean result = pessoaDAO.save(pessoa);
        return result;
    }

    @Override
    public Pessoa update(Pessoa pessoa) {
        Pessoa pessoaUpdated = pessoaDAO.update(pessoa);
        return pessoaUpdated;
    }

    
}
