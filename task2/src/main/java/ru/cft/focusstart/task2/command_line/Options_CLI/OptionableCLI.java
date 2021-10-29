package ru.cft.focusstart.task2.command_line.Options_CLI;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;

public interface OptionableCLI {
    void addInOptions(Option option);
    void addInOptions(OptionGroup optionGroup);
    OptionGroup addInGroupOption(Option... options);
    Option createOption(ParamsCMD paramsCMD);
}
