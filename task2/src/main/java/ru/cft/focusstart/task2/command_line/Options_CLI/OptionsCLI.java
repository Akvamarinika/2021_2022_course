package ru.cft.focusstart.task2.command_line.Options_CLI;

import lombok.Getter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;

@Getter
public class OptionsCLI implements OptionableCLI {
    private Options options = new Options();

    @Override
    public void addInOptions(Option option) {
        options.addOption(option);
    }

    @Override
    public void addInOptions(OptionGroup optionGroup) {
        options.addOptionGroup(optionGroup);
    }

    @Override
    public Option createOption(ParamsCMD paramsCMD) {
        String flagName = paramsCMD.getFlagName();
        String longName = paramsCMD.getLongName();
        boolean hasArg = paramsCMD.isHasArg();
        String description = paramsCMD.getDescription();
        int countArgs = paramsCMD.getCountArgs();
        String nameArg = paramsCMD.getNameArg();

        Option option = new Option(flagName, longName, hasArg, description);
        option.setArgs(countArgs);
        option.setArgName(nameArg);
        return option;
    }

    @Override
    public OptionGroup addInGroupOption(Option... options) {
        OptionGroup optionGroup = new OptionGroup();
        for (Option option : options){
            optionGroup.addOption(option);
        }

        return optionGroup;
    }


}
