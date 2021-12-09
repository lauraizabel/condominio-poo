package services;

import DAO.FornecedorDAO;
import dados.Fornecedor;
import repositories.IRepository;

import java.util.List;

public class FornecedorService implements IRepository<Fornecedor> {
    private FornecedorDAO fornecedorDAO;

    @Override
    public Fornecedor getById(Integer id) {
        Fornecedor fornecedor = fornecedorDAO.getById(id);
        return fornecedor;
    }

    @Override
    public List<Fornecedor> getAll() {
        List<Fornecedor> fornecedor = fornecedorDAO.getAll();
        return fornecedor;
    }

    @Override
    public boolean deleteById(Integer id) {
        boolean result = fornecedorDAO.deleteById(id);
        return result;
    }

    @Override
    public boolean save(Fornecedor fornecedor) {
        boolean result = fornecedorDAO.save(fornecedor);
        return result;
    }

    @Override
    public Fornecedor update(Fornecedor fornecedor) {
        Fornecedor fornecedorUpdated = fornecedorDAO.update(fornecedor);
        return fornecedorUpdated;
    }
}
