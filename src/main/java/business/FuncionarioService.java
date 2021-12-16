package business;

import DAO.FuncionarioDAO;
import dados.Funcionario;
import validation.validacao;

import java.util.ArrayList;

public class FuncionarioService implements IService<Funcionario> {
    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    @Override
    public Funcionario getById(Integer id) {
       return funcionarioDAO.getById(id);
    }

    @Override
    public ArrayList<Funcionario> getAll() {
        return funcionarioDAO.getAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        return funcionarioDAO.deleteById(id);
    }

    @Override
    public boolean save(Funcionario funcionario) throws Exception {
        validacao.validaCpf(funcionario.getCpf());
        validacao.validaEmail(funcionario.getEmail());
        return funcionarioDAO.save(funcionario);
    }

    @Override
    public Funcionario update(Funcionario funcionario) {
        return funcionarioDAO.update(funcionario);
    }
    
}
