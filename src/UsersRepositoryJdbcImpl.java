import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UserRepository {
    private Connection connection;

    private static final String SQL_SELECT_ALL_FROM_DRIVER = "SELECT * FROM driver";

    public UsersRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> findAll() {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_FROM_DRIVER);
            List<User> result = new ArrayList<>();

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getInt("age"),
                        resultSet.getString("city"),
                        resultSet.getString("license_number"),
                        resultSet.getInt("experience")
                );
                result.add(user);
            }

            return result;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        String query = "SELECT * FROM driver WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getInt("age"),
                        resultSet.getString("city"),
                        resultSet.getString("license_number"),
                        resultSet.getInt("experience")
                );
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return Optional.empty();
    }

    @Override
    public void save(User entity) {
        String query = "INSERT INTO driver (firstname, lastname, age, city, license_number, experience) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setInt(3, entity.getAge());
            statement.setString(4, entity.getCity());
            statement.setString(5, entity.getLicenseNumber());
            statement.setInt(6, entity.getExperience());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void update(User entity) {
        String query = "UPDATE driver SET firstname = ?, lastname = ?, age = ?, city = ?, license_number = ?, experience = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setInt(3, entity.getAge());
            statement.setString(4, entity.getCity());
            statement.setString(5, entity.getLicenseNumber());
            statement.setInt(6, entity.getExperience());
            statement.setLong(7, entity.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                System.out.println("Пользователь с id " + entity.getId() + " не найден для обновления.");
            } else {
                System.out.println("Пользователь с id " + entity.getId() + " успешно обновлен.");
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void remove(User entity) {
        removeById(entity.getId());
    }

    @Override
    public void removeById(Long id) {
        String query = "DELETE FROM driver WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<User> findAllByAge(Integer age) {
        throw new UnsupportedOperationException("Метод не реализован.");
    }

    @Override
    public List<User> findByCity(String city) {
        String query = "SELECT * FROM driver WHERE city = ?";
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, city);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getInt("age"),
                        resultSet.getString("city"),
                        resultSet.getString("license_number"),
                        resultSet.getInt("experience")
                ));
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return users;
    }

    @Override
    public Optional<User> findByLicenseNumber(String licenseNumber) {
        String query = "SELECT * FROM driver WHERE license_number = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, licenseNumber);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getInt("age"),
                        resultSet.getString("city"),
                        resultSet.getString("license_number"),
                        resultSet.getInt("experience")
                ));
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<User> findByExperience(int experience) {
        String query = "SELECT * FROM driver WHERE experience >= ?";
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, experience);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getLong("id"),
                        resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getInt("age"),
                        resultSet.getString("city"),
                        resultSet.getString("license_number"),
                        resultSet.getInt("experience")
                ));
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return users;
    }
}
