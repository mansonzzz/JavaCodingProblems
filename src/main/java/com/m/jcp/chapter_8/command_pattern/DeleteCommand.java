package com.m.jcp.chapter_8.command_pattern;

/**
 * @author zhangtian1
 */
public class DeleteCommand implements Command {
    private final IODevice action;

    public DeleteCommand(IODevice ioDevice) {
        action = ioDevice;
    }

    @Override
    public void execute() {
        action.delete();
    }
}
