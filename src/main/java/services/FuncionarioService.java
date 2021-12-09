package services;

import java.util.List;

import dados.Funcionario;
import repositories.FuncionarioRepository;

public class FuncionarioService implements IService<Funcionario> {
    private FuncionarioRepository funcionarioRepository;

    @Override
    public Funcionario getById(Integer id) {
       return funcionarioRepository.getById(id);
    }

    @Override
    public List<Funcionario> getAll() {
        return funcionarioRepository.getAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean save(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    @Override
    public Funcionario update(Funcionario funcionario) {
        return funcionarioRepository.update(funcionario);
    }
    
}
