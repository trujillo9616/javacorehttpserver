import java.time.LocalDateTime;
import java.util.Objects;

public class User {

    private String name;
    private String lastName;
    private String age;
    private final String email;
    private final String dateCreated;
    private String dateModified;

    public User(
            String name,
            String lastName,
            String age,
            String email
    ) {
        super();
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.dateCreated = LocalDateTime.now().toString();
        this.dateModified = LocalDateTime.now().toString();
    }

    // GETTERS

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getDateModified() {
        return dateModified;
    }

    // SETTERS

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age='" + age + '\'' +
                ", email='" + email + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                ", dateModified='" + dateModified + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(lastName, user.lastName) && Objects.equals(age, user.age) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, age, email);
    }
}
