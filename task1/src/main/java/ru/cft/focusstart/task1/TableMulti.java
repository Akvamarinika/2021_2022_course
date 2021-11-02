package ru.cft.focusstart.task1;

public class TableMulti {
    public static final String VERTICAL_DELIMITER = "|";
    public static final String HORIZONTAL_DELIMITER = "-";
    public static final String HORIZONTAL_DELIMITER_COL = "+";
    public static final String NEW_LINE_SYMBOL = "\n";
    public static final String INDENT_SYMBOL = " ";

    private final StringBuilder builderTable;
    private final int size;
    private final int bigNumber;

    public TableMulti(int size) {
        this.size = size;
        bigNumber = size * size;
        builderTable = new StringBuilder(bigNumber);
    }

    public String generateTable(){
        String indentFirstCol;
        String indentMain;
        String delimiterLine = generateDelimiterLine();

        addTableHeader();

        for(int row = 1; row <= size; row++){

            builderTable.append(NEW_LINE_SYMBOL)
                    .append(delimiterLine)
                    .append(NEW_LINE_SYMBOL);

            indentFirstCol = createIndent(size, row);
            builderTable.append(indentFirstCol)
                        .append(row);

            for (int col = 1; col <= size; col++){
                   int number = row * col;
                   indentMain = createIndent(bigNumber, number);

                   builderTable.append(VERTICAL_DELIMITER)
                               .append(indentMain)
                               .append(number);
            }
        }

        return builderTable.toString();
    }

    private void addTableHeader(){
        String indentMain;
        builderTable.append(INDENT_SYMBOL.repeat(countSymbolsInNumber(size)));

        for(int row = 1; row <= size; row++) {
            indentMain = createIndent(bigNumber, row);

            builderTable.append(VERTICAL_DELIMITER)
                    .append(indentMain)
                    .append(row);
        }
    }

    private String createIndent(int numMaxLength, int number){
        int countSpace = countSymbolsInNumber(numMaxLength) - countSymbolsInNumber(number);
        return INDENT_SYMBOL.repeat(countSpace);
    }

    private String createDelimiter(){
        int countMinus = countSymbolsInNumber(bigNumber);
        return HORIZONTAL_DELIMITER_COL + HORIZONTAL_DELIMITER.repeat(countMinus);
    }

    private String generateDelimiterLine(){
        String delimiterFirst = generateDelimiterFirstColumn();
        String delimiter = createDelimiter();
        return delimiterFirst + delimiter.repeat(size);
    }


    private int countSymbolsInNumber(int number){
        return (number == 0) ? 1 : (int) Math.ceil(Math.log10(Math.abs(number) + 0.5));
    }

    private String generateDelimiterFirstColumn(){
        int countMinus = countSymbolsInNumber(size);
        return HORIZONTAL_DELIMITER.repeat(countMinus);
    }





}
