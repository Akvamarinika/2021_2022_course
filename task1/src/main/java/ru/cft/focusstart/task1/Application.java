package ru.cft.focusstart.task1;

public class Application {

    public static void main(String[] args) {
        String table;

        if (InputValidator.countArgs(args) && InputValidator.isInteger(args[0])){
            table = createTable(args[0]);
            System.out.println(table);
        }

    }

    public static String createTable(String tableSize){
        int sizeTable = Integer.parseInt(tableSize);

        if (InputValidator.checkTableSize(sizeTable)) {
            TableMulti tableMulti = new TableMulti(sizeTable);
            return tableMulti.generateTable();
        }

        return "";
    }
}
