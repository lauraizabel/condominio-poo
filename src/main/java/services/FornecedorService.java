package services;

import DAO.FornecedorDAO;
import dados.Fornecedor;
import utils.validacao;

import java.util.ArrayList;

public class FornecedorService implements IService<Fornecedor> {
    private FornecedorDAO fornecedorDAO = new FornecedorDAO();

    @Override
    public Fornecedor getById(Integer id) {
        Fornecedor fornecedor = fornecedorDAO.getById(id);
        return fornecedor;
    }

    @Override
    public ArrayList<Fornecedor> getAll() {
        ArrayList<Fornecedor> fornecedor = fornecedorDAO.getAll();
        return fornecedor;
    }

    @Override
    public boolean deleteById(Integer id) {
        boolean result = fornecedorDAO.deleteById(id);
        return result;
    }

    @Override
    public boolean save(Fornecedor fornecedor) throws Exception{
        validacao.validaCnpj(fornecedor.getCnpj());
        validacao.validaEmail(fornecedor.getEmail());
        boolean result = fornecedorDAO.save(fornecedor);
        return result;
    }

    @Override
    public Fornecedor update(Fornecedor fornecedor) {
        Fornecedor fornecedorUpdated = fornecedorDAO.update(fornecedor);
        return fornecedorUpdated;
    }
}
