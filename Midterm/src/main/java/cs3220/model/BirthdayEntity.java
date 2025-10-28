package cs3220.model;

public class BirthdayEntity {
    private int id;
    private String friendName;
    private int month; // 1-12
    private int day;   // 1-31

    public BirthdayEntity() {}

    public BirthdayEntity(int id, String friendName, int month, int day) {
        this.id = id;
        this.friendName = friendName == null ? "" : friendName.trim();
        this.month = month;
        this.day = day;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFriendName() { return friendName; }
    public void setFriendName(String friendName) { this.friendName = friendName == null ? "" : friendName.trim(); }

    public int getMonth() { return month; }
    public void setMonth(int month) { this.month = month; }

    public int getDay() { return day; }
    public void setDay(int day) { this.day = day; }

    /** MM/DD for display like the screenshots */
    public String getFormatted() {
        return String.format("%02d/%02d", month, day);
    }

    /** For sorting by month/day (no year). */
    public int sortKey() {
        return month * 100 + day;
    }
}
