package ru.cft.focusstart.task5.task1;

import ru.cft.focusstart.task5.task1.msg.MsgErrors;

public class InputValidator {

    public static boolean checkTableSize(int size) throws IllegalArgumentException{
        if (size >= 1 && size <= 32){
            return true;
        }

        System.err.println(MsgErrors.MSG_ERR_SIZE_TABLE);
        return false;
    }
}
