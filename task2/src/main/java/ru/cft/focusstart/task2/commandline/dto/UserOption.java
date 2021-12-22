package ru.cft.focusstart.task2.commandline.dto;

import lombok.Getter;
import lombok.Setter;
import java.io.File;
import java.util.Objects;

@Getter
@Setter
public class UserOption {
    private File inputFile;
    private File outputFile;

    @Override
    public boolean equals(Object obj) {
        if (obj == this){return true;}

        if (obj instanceof UserOption) {
            UserOption userOption = (UserOption) obj;

            return Objects.equals(inputFile, userOption.inputFile) &&
                    Objects.equals(outputFile, userOption.outputFile);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(inputFile, outputFile);
    }
}
