package com.m.jcp.chapter_8.command_pattern;

/**
 * @author zhangtian1
 * 将命令封装在对象中，这样可以用参数化的方式来代替具体的命令
 */
public class Main {
    public static void main(String[] args) {
        HardDisk hd = new HardDisk();
        Sequence sequence = new Sequence();
        System.out.println(">>> Classic:");
        sequence.recordSequence(new DeleteCommand(hd));
        sequence.runSequence();
        sequence.clearSequence();

        System.out.println("\n>>> Lambda:");
        // 直接写入具体的行为
        sequence.recordSequence(hd::copy);
        sequence.recordSequence(hd::delete);
        sequence.runSequence();
        sequence.clearSequence();
    }
}
