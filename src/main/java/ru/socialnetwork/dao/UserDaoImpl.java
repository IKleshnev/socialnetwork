package ru.socialnetwork.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.socialnetwork.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserDaoImpl implements UserDao {
    private final DataSource dataSource;

    @Override
    public void createUser(User user) {
        String sql = "INSERT INTO users (first_name, last_name, age, gender, interests, city) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setInt(3, user.getAge());
            pstmt.setString(4, user.getGender());
            pstmt.setString(5, user.getInterests());
            pstmt.setString(6, user.getCity());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при инсерте %s".formatted(e.getMessage()));
        }
    }

    @Override
    public Optional<User> getUserById(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setLong(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(User.builder()
                            .id(rs.getLong("id"))
                            .firstName(rs.getString("first_name"))
                            .lastName(rs.getString("last_name"))
                            .age(rs.getInt("age"))
                            .gender(rs.getString("gender"))
                            .interests(rs.getString("interests"))
                            .city((rs.getString("city")))
                            .build());

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при селекте %s".formatted(e.getMessage()));
        }
        return Optional.empty();
    }
}
