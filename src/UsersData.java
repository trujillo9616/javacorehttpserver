import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class UsersData {

    public final ConcurrentMap<String, User> usersMap;

    public UsersData() {
        this.usersMap = new ConcurrentHashMap<>();
    }

    // CREATE
    public User create(String name, String lastName, String age, String email) {
        User newUser = new User(name, lastName, age, email);
        if (usersMap.putIfAbsent(newUser.getEmail(), newUser) != null) {
            return null;
        }
        return newUser;
    }

    // READ
    public User get(String email) {
        return usersMap.get(email);
    }

    // UPDATE
    public User update(String name, String lastName, String age, String email) {
        User user = usersMap.get(email);
        if (name != null) user.setName(name);
        if (lastName != null) user.setLastName(lastName);
        if (age != null) user.setAge(age);
        user.setDateModified(LocalDateTime.now().toString());
        return user;
    }

    // DELETE
    public boolean delete(String email) {
        return usersMap.remove(email) != null;
    }

    // List
    public List<User> listUsers() {
        return usersMap.values()
                .stream()
                .sorted(Comparator.comparing(User::getName))
                .collect(Collectors.toList());
    }
}
