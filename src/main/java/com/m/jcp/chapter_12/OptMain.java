package com.m.jcp.chapter_12;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toList;

/**
 * @author zhangtian1
 */
@Slf4j
public class OptMain {

    private static final String STATUS = "UNKNOWN";

    private static final Random rnd = new Random();

    public static void displayStatus(String status) {
        Optional<String> opt = Optional.ofNullable(status);
        opt.ifPresentOrElse(System.out::println, () -> System.out.println("Status is null"));
    }

    /**
     * replace `Optional.of(status.orElseGet(() -> (BOOK_STATUS)));` in JDK 9
     */
    public static Optional<String> findStatus(String status) {
        Optional<String> opt = Optional.ofNullable(status);
//        return Optional.of(opt.orElse(STATUS));
        return opt.or(() -> Optional.of(STATUS));
    }

    /**
     * Opt是为了获取某个值而连锁使用的方法
     */
    public static String findStatusBadPractice(String status) {
        // AVOID
        // return Optional.ofNullable(status).orElse(STATUS);
        // PREFER
        return status == null ? STATUS : status;
    }

    /**
     * @see Optional#filter(Predicate)
     * filter方法会先判断Opt是否有值，
     * <br>
     * 如果有值，否则返回当前Opt
     * <br>
     * 再判断值是否满足Predicate条件，如果满足，返回当前Optional，否则返回空Opt
     */
    public static void findProperStatus(String status) {
        Optional<String> opt = Optional.ofNullable(status).filter(not(String::isEmpty));
    }

    /**
     * @see Optional#equals(Object)
     * Opt中的值相等，Opt就相等
     */
    public static void assertEquals() {
        Optional<String> opt1 = Optional.of(STATUS);
        Optional<String> opt2 = Optional.of(STATUS);
        System.out.println(opt1);
        System.out.println(opt2);
        assert opt1.equals(opt2);
    }

    /**
     * flatMap会过滤空的Stream，所以不需要再使用filter
     */
    public static List<Book> chainOptWithStream(List<String> books) {
        // AVOID
        // return books.stream()
        // .map(OptMain::getBook)
        // .filter(Optional::isPresent)
        // .map(Optional::get)
        // .collect(toList());
        return books.stream()
                .map(OptMain::getBook)
                .flatMap(Optional::stream)
                .collect(toList());
    }

    public static Optional<Book> getBook(String name) {
        int i = rnd.nextInt(100);
        if (i < 50) {
            log.info(">>> empty book:{}", name);
        }
        return Optional.ofNullable(i > 50 ? new Book(name, BigDecimal.valueOf(i)) : null);
    }

    //@formatter:off
    private static record Book(String name, BigDecimal price) {}
    //@formatter:on

}
