package business;

import DAO.CarroDAO;
import dados.Apartamento;
import dados.Carro;

import java.util.ArrayList;

public class CarroServices implements IService<Carro>{
    private CarroDAO carroDAO = new CarroDAO();

    @Override
    public Carro getById(Integer id) {
        return carroDAO.getById(id);
    }

    @Override
    public ArrayList<Carro> getAll() {
        return carroDAO.getAll();
    }

    @Override
    public boolean deleteById(Integer id) {
        return carroDAO.deleteById(id);
    }

    @Override
    public boolean save(Carro carro) {
        return carroDAO.save(carro);
    }

    @Override
    public Carro update(Carro carro) {
        return carroDAO.update(carro);
    }

    @Override
    public ArrayList<Carro> getAllAuditory() {
        return carroDAO.getAllAuditory();
    }
}
