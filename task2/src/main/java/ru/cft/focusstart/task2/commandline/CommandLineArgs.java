package ru.cft.focusstart.task2.commandline;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;
import ru.cft.focusstart.task2.commandline.Options_CLI.OptionsCLI;
import ru.cft.focusstart.task2.commandline.Options_CLI.ParamsCMD;
import ru.cft.focusstart.task2.commandline.dto.UserOption;

import java.io.File;

@Slf4j
public class CommandLineArgs {

    public UserOption parse(String[] args) throws ParseException {
        Options options = createParseOptions();
        CommandLineParser parserCMD = new DefaultParser();
        UserOption userOption = new UserOption();

        try {
            CommandLine cmd = parserCMD.parse(options, args);
            userOption = cmdSaveArgs(cmd);
        } catch (ParseException ex) {
            log.error("Введены не все обязательные параметры. Или используются несовместимые флаги.");
            throw ex;
        }

        return userOption;

    }

    private Options createParseOptions(){
        OptionsCLI optionsCLI = new OptionsCLI();

        Option optOutFile = optionsCLI.createOption(ParamsCMD.OUT_FILE);
        Option optOutCMD = optionsCLI.createOption(ParamsCMD.OUT_CMD);
        OptionGroup optionGroupOut = optionsCLI.addInGroupOption(optOutCMD, optOutFile);
        optionGroupOut.setRequired(true);
        optionsCLI.addInOptions(optionGroupOut);

        Option optionInput = optionsCLI.createOption(ParamsCMD.INPUT);
        optionInput.setRequired(true);
        optionsCLI.addInOptions(optionInput);

        return optionsCLI.getOptions();
    }

    private UserOption cmdSaveArgs(CommandLine cmdArgs){
        UserOption userOption = new UserOption();
        String inputFlag = ParamsCMD.INPUT.getFlagName();
        String outputFlag = ParamsCMD.OUT_FILE.getFlagName();

        if(cmdArgs.hasOption(inputFlag)) {
            String[] arguments = cmdArgs.getOptionValues(inputFlag);
            userOption.setInputFile(new File(arguments[0]));
        }

        if(cmdArgs.hasOption(outputFlag)) {
            String[] arguments = cmdArgs.getOptionValues(outputFlag);
            userOption.setOutputFile(new File(arguments[0]));
        }

        return  userOption;
    }

}
