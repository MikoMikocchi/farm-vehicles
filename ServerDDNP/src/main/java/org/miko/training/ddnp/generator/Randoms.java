package org.miko.training.ddnp.generator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public final class Randoms {

    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

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

    public static <T extends Enum<T>> T randomEnum(Class<T> enumClass) {
        T[] values = enumClass.getEnumConstants();
        return values[ThreadLocalRandom.current().nextInt(values.length)];
    }

    public static int randomInt(int from, int toInclusive) {
        return ThreadLocalRandom.current().nextInt(from, toInclusive + 1);
    }

    /**
     * Случайное число из {@code length} цифр (без ведущих нулей), приведённое к строке.
     */
    public static String randomDigits(int length) {
        StringBuilder digits = new StringBuilder();
        digits.append(ThreadLocalRandom.current().nextInt(1, 10));

        for (int i = 1; i < length; i++) {
            digits.append(ThreadLocalRandom.current().nextInt(10));
        }

        return digits.toString();
    }

    /**
     * Случайная последовательность из {@code length} цифр (ведущие нули допустимы).
     */
    public static String randomDigitSequence(int length) {
        StringBuilder digits = new StringBuilder();

        for (int i = 0; i < length; i++) {
            digits.append(ThreadLocalRandom.current().nextInt(10));
        }

        return digits.toString();
    }

    /**
     * Случайный номер в формате +7-xxx-xxx-xx-xx.
     */
    public static String randomPhoneNumber() {
        return String.format("+7-%03d-%03d-%02d-%02d",
                ThreadLocalRandom.current().nextInt(1000),
                ThreadLocalRandom.current().nextInt(1000),
                ThreadLocalRandom.current().nextInt(100),
                ThreadLocalRandom.current().nextInt(100));
    }

    public static BigDecimal randomAmount(long fromInclusive, long toInclusive) {
        return BigDecimal.valueOf(ThreadLocalRandom.current().nextLong(fromInclusive, toInclusive + 1));
    }

    /**
     * Случайный набор из {@code length} заглавных латинских букв и цифр.
     */
    public static String randomAlphanumeric(int length) {
        StringBuilder text = new StringBuilder();

        for (int i = 0; i < length; i++) {
            text.append(ALPHANUMERIC.charAt(ThreadLocalRandom.current().nextInt(ALPHANUMERIC.length())));
        }

        return text.toString();
    }
}
