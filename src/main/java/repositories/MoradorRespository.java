package repositories;

import java.util.ArrayList;

import DAO.MoradorDAO;
import dados.Morador;

public class MoradorRespository implements IRepository<Morador> {
        private MoradorDAO moradorDAO = new MoradorDAO();

        @Override
        public Morador getById(Integer id) {
            Morador morador = moradorDAO.getById(id);
            return morador;
        }

        @Override
        public ArrayList<Morador> getAll() {
            ArrayList<Morador> morador = moradorDAO.getAll();
            return morador;
        }

        @Override
        public boolean deleteById(Integer id) {
            boolean result = moradorDAO.deleteById(id);
            return result;
        }

        @Override
        public boolean save(Morador morador) {
            boolean result = moradorDAO.save(morador);
            return result;
        }

        @Override
        public Morador update(Morador morador) {
            Morador moradorUpdated = moradorDAO.update(morador);
            return moradorUpdated;
        }

}
