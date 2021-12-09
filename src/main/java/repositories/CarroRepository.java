package repositories;
import java.util.ArrayList;

import DAO.CarroDAO;
import dados.Carro;

public class CarroRepository implements IRepository<Carro> {
    private CarroDAO carroDAO = new CarroDAO();

    @Override
    public Carro getById(Integer id) {
        Carro carro = carroDAO.getById(id);
        return carro;
    }

    @Override
    public ArrayList<Carro> getAll() {
        ArrayList<Carro> carro = carroDAO.getAll();
        return carro;
    }

    @Override
    public boolean deleteById(Integer id) {
        boolean result = carroDAO.deleteById(id);
        return result;
    }

    @Override
    public boolean save(Carro carro) {
        boolean result = carroDAO.save(carro);
        return result;
    }

    @Override
    public Carro update(Carro carro) {
        Carro carroUpdated = carroDAO.update(carro);
        return carroUpdated;
    }

}
