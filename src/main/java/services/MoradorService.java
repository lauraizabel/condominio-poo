package services;

import java.util.ArrayList;

import DAO.MoradorDAO;
import dados.Morador;
import utils.validacao;


public class MoradorService implements IService<Morador> {
    private MoradorDAO moradorDAO = new MoradorDAO();

    @Override
    public Morador getById(Integer id) {
        return moradorDAO.getById(id);
    }

    @Override
    public ArrayList<Morador> getAll() {
        return moradorDAO.getAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        return moradorDAO.deleteById(id);
    }

    @Override
    public boolean save(Morador morador) throws Exception {
        validacao.validaCpf(morador.getCpf());
        validacao.validaEmail(morador.getEmail());
        return moradorDAO.save(morador);
    }

    @Override
    public Morador update(Morador morador) {
        return moradorDAO.update(morador);
    }
}
