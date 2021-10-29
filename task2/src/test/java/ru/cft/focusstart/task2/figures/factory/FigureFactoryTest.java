package ru.cft.focusstart.task2.figures.factory;

import org.junit.jupiter.api.Test;
import ru.cft.focusstart.task2.figures.Circle;
import ru.cft.focusstart.task2.figures.Figure;
import ru.cft.focusstart.task2.figures.Rectangle;
import ru.cft.focusstart.task2.figures.Triangle;
import ru.cft.focusstart.task2.figures.enum_figures.Figures;
import ru.cft.focusstart.task2.figures.factory.exception.CreateFigureException;
import ru.cft.focusstart.task2.parser_file.dto.DataFigure;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class FigureFactoryTest {

    @Test
    void createFigure_whenCorrectCircle_returnCircle() throws CreateFigureException {
        DataFigure dataFigure = new DataFigure(Figures.CIRCLE, Collections.singletonList(5.));
        Figure figure = FigureFactory.createFigure(dataFigure);
        Figure expectedFigure = new Circle(5);

        assertEquals(expectedFigure, figure);
    }

    @Test
    void createFigure_whenCorrectRectangle_returnRectangle() throws CreateFigureException {
        DataFigure dataFigure = new DataFigure(Figures.RECTANGLE, Arrays.asList(10., 5.));
        Figure figure = FigureFactory.createFigure(dataFigure);
        Figure expectedFigure = new Rectangle(5, 10);

        assertEquals(expectedFigure, figure);
    }

    @Test
    void createFigure_whenCorrectTriangle_returnTriangle() throws CreateFigureException {
        DataFigure dataFigure = new DataFigure(Figures.TRIANGLE, Arrays.asList(12., 5., 10.));
        Figure figure = FigureFactory.createFigure(dataFigure);
        Figure expectedFigure = new Triangle(5., 10., 12.);

        assertEquals(expectedFigure, figure);
    }

    @Test
    void createFigure_whenNegativeParams_throwCreateFigureException() {
        DataFigure dataFigure = new DataFigure(Figures.TRIANGLE, Arrays.asList(12., -5., 10.));

        assertThrows(CreateFigureException.class, () -> FigureFactory.createFigure(dataFigure));
    }

    @Test
    void createFigure_whenZeroParams_throwCreateFigureException() {
        DataFigure dataFigure = new DataFigure(Figures.TRIANGLE, Arrays.asList(0., 5., 0.));

        assertThrows(CreateFigureException.class, () -> FigureFactory.createFigure(dataFigure));
    }

    @Test
    void createFigure_whenSquareWightEqualsHeight_throwCreateFigureException() {
        DataFigure dataFigure = new DataFigure(Figures.RECTANGLE, Arrays.asList(5., 5.));

        assertThrows(CreateFigureException.class, () -> FigureFactory.createFigure(dataFigure));
    }

    @Test
    void createFigure_whenEmptyParams_throwCreateFigureException() {
        DataFigure dataFigure = new DataFigure(Figures.RECTANGLE, new ArrayList<>());

        assertThrows(CreateFigureException.class, () -> FigureFactory.createFigure(dataFigure));
    }

    @Test
    void createFigure_whenUnknownFigure_throwCreateFigureException() {
        DataFigure dataFigure = new DataFigure(Figures.UNKNOWN, Arrays.asList(5., 10.));

        assertThrows(CreateFigureException.class, () -> FigureFactory.createFigure(dataFigure));
    }

    @Test
    void createFigure_whenWrongTriangleSideA_throwCreateFigureException() {
        DataFigure dataFigure = new DataFigure(Figures.TRIANGLE, Arrays.asList(50., 10., 20.));

        assertThrows(CreateFigureException.class, () -> FigureFactory.createFigure(dataFigure));
    }

    @Test
    void createFigure_whenWrongTriangleSideB_throwCreateFigureException() {
        DataFigure dataFigure = new DataFigure(Figures.TRIANGLE, Arrays.asList(10., 50., 20.));

        assertThrows(CreateFigureException.class, () -> FigureFactory.createFigure(dataFigure));
    }

    @Test
    void createFigure_whenWrongTriangleHypotC_throwCreateFigureException() {
        DataFigure dataFigure = new DataFigure(Figures.TRIANGLE, Arrays.asList(10., 20., 50.));

        assertThrows(CreateFigureException.class, () -> FigureFactory.createFigure(dataFigure));
    }

    @Test
    void createFigure_whenWrongTriangleTwoParams_throwCreateFigureException() {
        DataFigure dataFigure = new DataFigure(Figures.TRIANGLE, Arrays.asList(50., 100.));

        assertThrows(CreateFigureException.class, () -> FigureFactory.createFigure(dataFigure));
    }

    @Test
    void createFigure_whenWrongTriangleOneParams_throwCreateFigureException() {
        DataFigure dataFigure = new DataFigure(Figures.TRIANGLE, Collections.singletonList(50.));

        assertThrows(CreateFigureException.class, () -> FigureFactory.createFigure(dataFigure));
    }

    @Test
    void createFigure_whenWrongRectangleOneParams_throwCreateFigureException() {
        DataFigure dataFigure = new DataFigure(Figures.RECTANGLE, Collections.singletonList(50.));

        assertThrows(CreateFigureException.class, () -> FigureFactory.createFigure(dataFigure));
    }

    @Test
    void createFigure_whenWrongRectangleExtraParams_throwCreateFigureException() {
        DataFigure dataFigure = new DataFigure(Figures.RECTANGLE, Arrays.asList(50., 100., 11.));

        assertThrows(CreateFigureException.class, () -> FigureFactory.createFigure(dataFigure));
    }
}