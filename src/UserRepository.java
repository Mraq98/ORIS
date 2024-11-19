import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User> {
    List<User> findAllByAge(Integer age);

    List<User> findByCity(String city);

    Optional<User> findByLicenseNumber(String licenseNumber);

    List<User> findByExperience(int experience);
}
