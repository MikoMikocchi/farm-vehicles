package org.miko.training.ddnp.generator;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class Randoms {

    private Randoms() {
    }

    public static LocalDate randomDate(LocalDate from, LocalDate to) {
        return LocalDate.ofEpochDay(ThreadLocalRandom.current().nextLong(from.toEpochDay(), to.toEpochDay() + 1));
    }

    public static boolean randomBoolean() {
        return ThreadLocalRandom.current().nextBoolean();
    }

    public static <T> T randomElement(List<T> items) {
        return items.get(ThreadLocalRandom.current().nextInt(items.size()));
    }
}
