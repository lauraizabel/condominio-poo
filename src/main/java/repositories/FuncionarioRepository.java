package repositories;

import java.util.List;

import DAO.FuncionarioDAO;
import dados.Funcionario;

public class FuncionarioRepository implements IRepository<Funcionario> {
    private FuncionarioDAO funcionarioDAO;

    @Override
    public Funcionario getById(Integer id) {
        Funcionario funcionario = funcionarioDAO.getById(id);
        return funcionario;
    }

    @Override
    public List<Funcionario> getAll() {
        List<Funcionario> funcionarios = funcionarioDAO.getAll();
        return funcionarios;
    }

    @Override
    public boolean deleteById(Integer id) {
        boolean result = funcionarioDAO.deleteById(id);
        return result;
    }

    @Override
    public boolean save(Funcionario funcionario) {
        boolean result = funcionarioDAO.save(funcionario);
        return result;
    }

    @Override
    public Funcionario update(Funcionario funcionario) {
        Funcionario funcionarioUpdated = funcionarioDAO.update(funcionario);
        return funcionarioUpdated;
    }
    
}
