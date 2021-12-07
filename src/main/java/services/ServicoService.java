package services;

import dados.Servico;
import repositories.ServicoRepository;

import java.util.List;

public class ServicoService implements IService<Servico> {
    private ServicoRepository servicoRepository;

    @Override
    public Servico getById(Integer id) {
        return servicoRepository.getById(id);
    }

    @Override
    public List<Servico> getAll() {
        return servicoRepository.getAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        return servicoRepository.deleteById(id);
    }

    @Override
    public boolean save(Servico servico) {
        return servicoRepository.save(servico);
    }

    @Override
    public Servico update(Servico servico) {
        return servicoRepository.update(servico);
    }
}
