package mfa.website.multi_factor_auth.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import mfa.website.multi_factor_auth.model.Users;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
}

