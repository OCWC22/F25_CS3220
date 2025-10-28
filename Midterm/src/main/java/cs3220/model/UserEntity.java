package cs3220.model;

public class UserEntity {
    private String email;
    private String name;
    private String password;

    public UserEntity() {}

    public UserEntity(String email, String name, String password) {
        this.email = email == null ? "" : email.trim().toLowerCase();
        this.name = name == null ? "" : name.trim();
        this.password = password == null ? "" : password;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email == null ? "" : email.trim().toLowerCase(); }

    public String getName() { return name; }
    public void setName(String name) { this.name = name == null ? "" : name.trim(); }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password == null ? "" : password; }
}
