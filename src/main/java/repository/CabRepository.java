package repository;

import cab.demo.cab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CabRepository extends JpaRepository<cab, Long> {
    List<cab> findByCityAndStatus(String city, String available);

}
