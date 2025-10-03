package cs3220.model;

import java.util.ArrayList;
import java.util.List;

/**
 * UsersEntity handles all user information for authentication.
 * Provides static seed data and lookup methods.
 */
public class UsersEntity {
    private int id;
    private String email;
    private String password;
    private String name;

    public UsersEntity(int id, String email, String password, String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public int getId() { return id; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getName() { return name; }

    // Static seed users for this lab
    private static final List<UsersEntity> USERS = new ArrayList<>();
    
    static {
        USERS.add(new UsersEntity(1, "admin@calstatela.edu", "admin123", "Admin User"));
        USERS.add(new UsersEntity(2, "rlai2@calstatela.edu", "password", "Ringo Lai"));
        USERS.add(new UsersEntity(3, "test@calstatela.edu", "test", "Test User"));
    }

    /**
     * Validates user credentials and returns the user if found.
     * @param email user email
     * @param password user password
     * @return UsersEntity if credentials match, null otherwise
     */
    public static UsersEntity authenticate(String email, String password) {
        if (email == null || password == null) {
            return null;
        }
        for (UsersEntity user : USERS) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Returns all users (for testing/debugging purposes).
     */
    public static List<UsersEntity> getAllUsers() {
        return new ArrayList<>(USERS);
    }
}
