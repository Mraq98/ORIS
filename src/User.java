public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String city;
    private String licenseNumber;
    private Integer experience;

    public User(Long id, String firstName, String lastName, Integer age, String city, String licenseNumber, Integer experience) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.city = city;
        this.licenseNumber = licenseNumber;
        this.experience = experience;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public Integer getExperience() {
        return experience;
    }
}
