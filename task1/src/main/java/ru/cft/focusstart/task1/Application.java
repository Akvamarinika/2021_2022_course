package ru.cft.focusstart.task1;
public class Application {

    public static void main(String[] args) {
        try {
            String table;
            if (InputValidator.countArgs(args) && InputValidator.isInteger(args[0])){
                table = createTable(args[0]);
                System.out.println(table);
            }
        } catch (IllegalArgumentException ex) {
            System.err.println(ex.getMessage());
        }


    }

    public static String createTable(String tableSize)throws IllegalArgumentException{
        int sizeTable = Integer.parseInt(tableSize);

        if (InputValidator.checkTableSize(sizeTable)) {
            TableMulti tableMulti = new TableMulti(sizeTable);
            return tableMulti.generateTable();
        }

        return "";
    }
}
