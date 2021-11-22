package ru.cft.focusstart.task2.parserfile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.cft.focusstart.task2.figures.enumfigures.Figures;
import ru.cft.focusstart.task2.parserfile.dto.DataFigure;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class ParserFileTest {
    private ParserFile parserFile;
    public static final String RESOURCES_TEST = "src/test/resources/";

    @BeforeEach
    void setUp() {
        parserFile = new ParserFile();
    }

    @Test
    void parse_fileWithCorrectDataForCircle_ReturnDataFigure() throws IOException {
        File file = new File(RESOURCES_TEST + "correct_Circle.txt");
        DataFigure dataFigure = parserFile.parse(file);
        DataFigure expected = new DataFigure(Figures.CIRCLE, Collections.singletonList(5.));
        assertEquals(expected, dataFigure);
    }

    @Test
    void parse_fileWithCorrectDataForRectangle_ReturnDataFigure() throws IOException {
        File file = new File(RESOURCES_TEST + "correct_Rectangle.txt");
        DataFigure dataFigure = parserFile.parse(file);
        DataFigure expected = new DataFigure(Figures.RECTANGLE, Arrays.asList(5., 10.));

        assertEquals(expected, dataFigure);
    }

    @Test
    void parse_fileWithCorrectDataForTriangle_ReturnDataFigure() throws IOException {
        File file = new File(RESOURCES_TEST + "correct_Triangle.txt");
        DataFigure dataFigure = parserFile.parse(file);
        DataFigure expected = new DataFigure(Figures.TRIANGLE, Arrays.asList(3., 6., 12.));

        assertEquals(expected, dataFigure);
    }

    @Test
    void parse_fileWithWrongDataWhenParamsExtra_ReturnDataFigureTypeCircleWithoutOneParams() throws IOException {
        File file = new File(RESOURCES_TEST + "wrong_countParamsExtra.txt");
        DataFigure dataFigure = parserFile.parse(file);
        DataFigure expected = new DataFigure(Figures.CIRCLE, Arrays.asList(4., 5.));

        assertEquals(expected, dataFigure);
    }

    @Test
    void parse_fileWithWrongDataWhenParamsNotEnough_ReturnDataFigureTypeTriangleOneParam() throws IOException {
        File file = new File(RESOURCES_TEST + "wrong_countParamsNotEnough.txt");
        DataFigure dataFigure = parserFile.parse(file);
        DataFigure expected = new DataFigure(Figures.TRIANGLE, Collections.singletonList(4.));

        assertEquals(expected, dataFigure);
    }

    @Test
    void parse_fileWithWrongDataWhenNoParams_ReturnDataFigureTypeCircleWithEmptyParam() throws IOException {
        File file = new File(RESOURCES_TEST + "wrong_noParams.txt");
        DataFigure dataFigure = parserFile.parse(file);
        DataFigure expected = new DataFigure(Figures.CIRCLE, new ArrayList<>());

        assertEquals(expected, dataFigure);
    }

    @Test
    void parse_fileWithWrongDataWhenNoType_ReturnDataFigureTypeUnknownWithEmptyParam() throws IOException {
        File file = new File(RESOURCES_TEST + "wrong_noType.txt");
        DataFigure dataFigure = parserFile.parse(file);
        DataFigure expected = new DataFigure(Figures.UNKNOWN, new ArrayList<>());

        assertEquals(expected, dataFigure);
    }

    @Test
    void parse_fileWithWrongRowOrderAndEmptyLine_ReturnDataFigureTypeRectangleWithEmptyParam() throws IOException {
        File file = new File(RESOURCES_TEST + "wrong_RowOrder.txt");
        DataFigure dataFigure = parserFile.parse(file);
        DataFigure expected = new DataFigure(Figures.RECTANGLE, new ArrayList<>());

        assertEquals(expected, dataFigure);
    }

    @Test
    void parse_fileWithWrongAllLines_ReturnDataFigureTypeUnknownWithEmptyParam() throws IOException {
        File file = new File(RESOURCES_TEST + "wrong_linesAll.txt");
        DataFigure dataFigure = parserFile.parse(file);
        DataFigure expected = new DataFigure(Figures.UNKNOWN, new ArrayList<>());

        assertEquals(expected, dataFigure);
    }

    @Test
    void parse_fileWithWrongLinesWithCorrect_ReturnDataFigureTypeCircleRadius7() throws IOException {
        File file = new File(RESOURCES_TEST + "wrong_linesWithCorrect.txt");
        DataFigure dataFigure = parserFile.parse(file);
        DataFigure expected = new DataFigure(Figures.CIRCLE, Collections.singletonList(7.));

        assertEquals(expected, dataFigure);
    }

    @Test
    void parse_fileNotFound_throwFileNotFoundException() {
        File file = new File(RESOURCES_TEST + "abc.txt");
        assertThrows(FileNotFoundException.class, () -> parserFile.parse(file));
    }

    @Test
    void parse_fileWithWrongParamsSymbol_ReturnDataFigureTypeTriangleTwoParams() throws IOException {
        File file = new File(RESOURCES_TEST + "wrong_ParamsSymbol.txt");
        DataFigure dataFigure = parserFile.parse(file);
        DataFigure expected = new DataFigure(Figures.TRIANGLE, Arrays.asList(5., 7.));

        assertEquals(expected, dataFigure);
    }

    @Test
    void parse_fileWithWrongSomeLinesParams_ReturnDataFigureWithFirstSuitableCountParams() throws IOException {
        File file = new File(RESOURCES_TEST + "wrong_SomeLinesParams.txt");
        DataFigure dataFigure = parserFile.parse(file);
        DataFigure expected = new DataFigure(Figures.RECTANGLE, Arrays.asList(8., 10.));

        assertEquals(expected, dataFigure);
    }

    @Test
    void parse_fileWithWrongSomeLinesTypes_ReturnDataWithFirstTypeSuitable() throws IOException {
        File file = new File(RESOURCES_TEST + "wrong_SomeLinesTypes.txt");
        DataFigure dataFigure = parserFile.parse(file);
        DataFigure expected = new DataFigure(Figures.RECTANGLE, Arrays.asList(8., 10.));

        assertEquals(expected, dataFigure);
    }

}