package com.m.jcp.chapter_8.command_pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangtian1
 */
public class Sequence {
    private final List<Command> commands = new ArrayList<>();

    public void recordSequence(Command command) {
        commands.add(command);
    }

    public void runSequence() {
        commands.forEach(Command::execute);
    }

    public void clearSequence() {
        commands.clear();
    }
}
