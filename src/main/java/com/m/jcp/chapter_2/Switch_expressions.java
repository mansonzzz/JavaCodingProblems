package com.m.jcp.chapter_2;

/**
 * @author zhangtian1
 */
public class Switch_expressions {
    public static void main(String[] args) {
        String name = createPlayerV2("A");
        assert name.equals("AB Player");

        String nameC = createPlayerV2("C");
        assert nameC.equals("C Player");
    }

    private static String createPlayerV1(String playerType) {
        switch (playerType) {
            case "tennis":
                return "Tennis Player";
            case "cricket":
                return "Cricket Player";
            case "football":
                return "Football Player";
            default:
                return "Invalid Player";
        }
    }

    private static String createPlayerV2(String playerType) {
        return switch (playerType) {
            // 箭头作用是防止fall-through
            case "tennis" -> "Tennis Player";
            case "cricket" -> "Cricket Player";
            case "football" -> "Football Player";
            case "A", "B" -> "AB Player";
            case "C" -> {
                System.out.println("this is C");
                yield "C Player";
            }
            // 如果没有覆盖所有的情况，编译器会抛出'switch' expression does not cover all possible input values的异常
            default -> "Invalid Player";
        };
    }
}
