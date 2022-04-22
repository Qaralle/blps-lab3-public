package itmo.blps.lab1.repository.xml;

import java.util.List;
import java.util.Optional;

public interface XmlRepository<T> {
    List<T> findAll();
    Optional<T> getById(Long id);
    Optional<T> getByUsername(String username);
    void save(T t);
}
