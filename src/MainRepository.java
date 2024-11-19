import java.sql.Connection;
import java.sql.DriverManager;

public class MainRepository {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "8154310112";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/1_ORIS_DataBase";

    public static void main(String[] args) throws Exception {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        UsersRepositoryJdbcImpl repository = new UsersRepositoryJdbcImpl(connection);

        System.out.println("Все пользователи:");
        repository.findAll().forEach(user -> System.out.println(user.getFirstName()));

        System.out.println("\nПользователи из города Москва:");
        repository.findByCity("Москва").forEach(user -> System.out.println(user.getFirstName()));

        System.out.println("\nПользователь с номером водительских прав 'AB12345':");
        repository.findByLicenseNumber("AB12345").ifPresent(user -> System.out.println(user.getLastName()));

        System.out.println("\nПользователи со стажем 5 лет и более:");
        repository.findByExperience(5).forEach(user -> System.out.println(user.getFirstName()));
    }
}
