package repository;

import cab.demo.driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<driver, Long> {
    // custom query methods if needed
}
