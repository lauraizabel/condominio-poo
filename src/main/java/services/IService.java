package services;

import dados.Reserva;
import repositories.ReservaRepository;

import java.util.List;

public interface IService<T> {

    T getById(Integer id) ;

    List<T> getAll();

    boolean deleteById(Integer id);

    boolean save(T object);

    T update(T object);

}
