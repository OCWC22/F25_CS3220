package cs3220.model;

import java.time.LocalDateTime;

public class GuestBookEntry {
    private int id;
    private String name;
    private String message;
    private LocalDateTime created;

    public GuestBookEntry(int id, String name, String message) {
        this.id = id;
        this.name = name;
        this.message = message;
        this.created = LocalDateTime.now();
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getMessage() { return message; }
    public LocalDateTime getCreated() { return created; }

    public void setName(String name) { this.name = name; }
    public void setMessage(String message) { this.message = message; }
}