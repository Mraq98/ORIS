import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Main {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "8154310112";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/1_ORIS_DataBase";

    public static void main(String[] args) throws Exception {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        UsersRepositoryJdbcImpl repository = new UsersRepositoryJdbcImpl(connection);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Сколько пользователей вы хотите добавить?");
        int count = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i <= count; i++) {
            System.out.println("Введите данные для пользователя #" + i);
            System.out.print("Имя: ");
            String firstName = scanner.nextLine();
            System.out.print("Фамилия: ");
            String lastName = scanner.nextLine();
            System.out.print("Возраст: ");
            int age = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера
            System.out.print("Город: ");
            String city = scanner.nextLine();
            System.out.print("Номер водительских прав: ");
            String licenseNumber = scanner.nextLine();
            System.out.print("Стаж: ");
            int experience = scanner.nextInt();
            scanner.nextLine();

            User user = new User(null, firstName, lastName, age, city, licenseNumber, experience);
            repository.save(user);
        }

        System.out.println("Добавление завершено.");
    }
}
