package com.m.jcp.chapter_1;

/**
 * @author zhangtian1
 */
public class Removing_leading_and_trailing_spaces {
    public static void main(String[] args) {
        String text = "   hello world   ";
        System.out.println(text.trim());

        char space = '\u2002';
        text = space + "\n \n\n hello \t \n \r" + space;
        System.out.println(text.strip());
//        text.stripLeading();
//        text.stripTrailing();
    }
}
