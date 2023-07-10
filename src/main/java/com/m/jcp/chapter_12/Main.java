package com.m.jcp.chapter_12;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author zhangtian1
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
//        OptMain.displayStatus(null);

//        Optional<String> status = OptMain.findStatus(null);
//        assert status.isPresent();
//        assert status.get().equals("UNKNOWN");
//        OptMain.findStatusBadPractice(null);
//        OptMain.assertEquals();
        System.out.println(OptMain.chainOptWithStream(getBooks()));
    }

    public static List<String> getBooks() {
        return List.of("Java", "Python", "C++", "Go", "Rust", "JavaScript", "TypeScript");
    }

}
