package services;

import java.util.ArrayList;

import DAO.PessoaDAO;
import dados.Pessoa;

public class PessoaService implements IService<Pessoa> {
    private PessoaDAO pessoaDAO = new PessoaDAO();

    @Override
    public Pessoa getById(Integer id) {
        return pessoaDAO.getById(id);
    }

    @Override
    public ArrayList<Pessoa> getAll() {
        return pessoaDAO.getAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        return pessoaDAO.deleteById(id);
    }

    @Override
    public boolean save(Pessoa pessoa) {
        return pessoaDAO.save(pessoa);
    }

    @Override
    public Pessoa update(Pessoa pessoa) {
        return pessoaDAO.update(pessoa);
    }
    
}
