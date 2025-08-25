package repository;

import cab.demo.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface UserRepository extends JpaRepository<user, Long> {
   
    user findByEmail(String email);

    user findByEmail(String email, String password);


}
