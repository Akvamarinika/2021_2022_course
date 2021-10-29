package ru.cft.focusstart.task2.command_line.Options_CLI;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ParamsCMD {
    INPUT("i", "input",true, "input from file", 1, "входной файл"),
    OUT_CMD("c", "cmd",false, "output in console", 0, "консоль"),
    OUT_FILE("f", "file",true, "output in file", 1, "выходной файл");

    private final String flagName;
    private final String longName;
    private final boolean hasArg;
    private final String description;
    private final int countArgs;
    private final String nameArg;
}
