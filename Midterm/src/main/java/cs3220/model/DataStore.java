package cs3220.model;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class DataStore {
    /** email -> user */
    public static final Map<String, UserEntity> USERS = new ConcurrentHashMap<>();
    /** email -> that user's birthday list */
    public static final Map<String, List<BirthdayEntity>> BIRTHDAYS = new ConcurrentHashMap<>();
    /** global incremental id for birthdays */
    public static final AtomicInteger ID_GEN = new AtomicInteger(1);

    public static List<BirthdayEntity> getListFor(String email) {
        return BIRTHDAYS.computeIfAbsent(email, k -> Collections.synchronizedList(new ArrayList<>()));
    }

    public static void sortListByMonthDay(List<BirthdayEntity> list) {
        list.sort(Comparator.comparingInt(BirthdayEntity::sortKey));
    }
}
