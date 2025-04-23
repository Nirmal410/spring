package net.codejava;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.annotation.Rollback;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@DataJpaTest
@ContextConfiguration(classes = CodeJavaAppApplication.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository repo;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("user" + System.currentTimeMillis() + "@example.com");
        user.setPassword("pass2");
        user.setFirstName("Arun");
        user.setLastName("Kumar");

        User savedUser = repo.save(user);
        assertThat(savedUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
public void testFindUserByEmail() {
    String email = "testuser" + System.currentTimeMillis() + "@example.com";

    User user = new User();
    user.setEmail(email);
    user.setPassword("test123");
    user.setFirstName("Test");
    user.setLastName("User");

    repo.save(user);

    User foundUser = repo.findByEmail(email);
    assertThat(foundUser).isNotNull();
    assertThat(foundUser.getEmail()).isEqualTo(email);
}

}
