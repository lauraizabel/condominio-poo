package business;

import DAO.FornecedorDAO;
import DAO.IEntityDAO;
import DAO.implementation.EntityDAO;
import dados.Auditoria;
import dados.Fornecedor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.hibernate.transform.Transformers;
import validation.validacao;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class FornecedorService implements IService<Fornecedor> {
    private FornecedorDAO fornecedorDAO = new FornecedorDAO();

    @Override
    public Fornecedor getById(Integer id) {
        Fornecedor fornecedor = fornecedorDAO.getById(id);
        return fornecedor;
    }

    @Override
    public ArrayList<Fornecedor> getAll() {
        ArrayList<Fornecedor> providers = fornecedorDAO.getAll();
        return providers;
    }

    @Override
    public boolean deleteById(Integer id) {
        boolean result = fornecedorDAO.deleteById(id);
        return result;
    }

    @Override
    public boolean save(Fornecedor fornecedor) throws Exception {
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

    @Override
    public ArrayList<Auditoria> getAllAuditory() {
        return fornecedorDAO.getAllAuditory();
    }
}
