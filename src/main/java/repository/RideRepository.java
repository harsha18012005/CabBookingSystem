package repository;

import cab.demo.ride;
import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RideRepository extends JpaRepository<ride, Long> {
    List<ride> findByUserIdOrderByCreatedAtDesc(Long userId);

    @Nullable Object findByUserId(Long id);
}
