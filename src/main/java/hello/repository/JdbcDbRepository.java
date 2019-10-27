package hello.repository;

import hello.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("jdbc")
public class JdbcDbRepository implements DbRepository {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final JdbcTemplate jdbcTemplate;

    public JdbcDbRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getUser(String id) {
        log.debug("getUser({})", id);
        return jdbcTemplate.queryForObject(
                "SELECT * FROM users WHERE id = ?",
                (rs, rn) -> User(rs.getString("id"), rs.getString("firstName"), rs.getString("lastName")),
                id);
    }

    private User updateUser(User user) {
        jdbcTemplate.update(
                "UPDATE users SET firstName = ?, lastName = ? WHERE id = ?",
                user.firstName,
                user.lastName,
                user.id);
        return user;
    }

    @Override
    public User updateUser(User user, String firstName, String lastName) {
        user.firstName = firstName;
        user.lastName = lastName;
        return updateUser(user);
    }

    @Override
    public List<User> users() {
        return jdbcTemplate.query(
                "SELECT * FROM users LIMIT 10",
                (rs, rn) -> new User(rs.getString("id"), rs.getString("firstName"), rs.getString("lastName")));
    }
}
