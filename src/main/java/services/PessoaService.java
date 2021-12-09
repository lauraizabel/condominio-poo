package services;

import java.util.List;

import dados.Pessoa;
import repositories.PessoaRepository;

public class PessoaService implements IService<Pessoa> {
    private PessoaRepository pessoaRepository;

    @Override
    public Pessoa getById(Integer id) {
        return pessoaRepository.getById(id);
    }

    @Override
    public List<Pessoa> getAll() {
        return pessoaRepository.getAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        return pessoaRepository.deleteById(id);
    }

    @Override
    public boolean save(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @Override
    public Pessoa update(Pessoa pessoa) {
        return pessoaRepository.update(pessoa);
    }
    
}
