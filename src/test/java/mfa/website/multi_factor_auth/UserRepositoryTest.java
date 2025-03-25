package mfa.website.multi_factor_auth;

import mfa.website.multi_factor_auth.model.Users;
import mfa.website.multi_factor_auth.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import static org.assertj.core.api.Assertions.assertThat; // Import AssertJ

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // Instantiate PasswordEncoder

        Users user = new Users();
        user.setUsername("test1zx");
        user.setPassword(passwordEncoder.encode("test1233")); // Hash the password
        user.setRole("USER");

        Users savedUser = repo.save(user);

        Users existUser = entityManager.find(Users.class, savedUser.getId());

        assertThat(existUser.getUsername()).isEqualTo(user.getUsername());
        assertThat(passwordEncoder.matches("test1233", existUser.getPassword())).isTrue(); // Verify password is hashed correctly
    }
}
