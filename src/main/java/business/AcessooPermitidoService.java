package business;

import DAO.AcessoPermitidoDAO;
import dados.AcessoPermitido;
import dados.Fornecedor;
import validation.validacao;

import java.util.ArrayList;

public class AcessooPermitidoService implements IService <AcessoPermitido>{
    private AcessoPermitidoDAO AcessoPermitidoDAO = new AcessoPermitidoDAO();
    
    @Override
    public AcessoPermitido getById(Integer id) {
        return AcessoPermitidoDAO.getById(id);
    }

    @Override
    public ArrayList<AcessoPermitido> getAll() {
        return AcessoPermitidoDAO.getAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        return AcessoPermitidoDAO.deleteById(id);
    }

    @Override
    public boolean save(AcessoPermitido acessoPermitido) throws Exception {
        validacao.validaCpf(acessoPermitido.getCpf());
        validacao.validaEmail(acessoPermitido.getEmail());
        return AcessoPermitidoDAO.save(acessoPermitido);
    }

    @Override
    public AcessoPermitido update(AcessoPermitido acessoPermitido) {
        return AcessoPermitidoDAO.update(acessoPermitido);
    }

    @Override
    public ArrayList<AcessoPermitido> getAllAuditory() {
        return AcessoPermitidoDAO.getAllAuditory();
    }
}