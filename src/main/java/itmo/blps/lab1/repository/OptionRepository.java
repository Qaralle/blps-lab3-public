package itmo.blps.lab1.repository;

import itmo.blps.lab1.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Long> {
}
