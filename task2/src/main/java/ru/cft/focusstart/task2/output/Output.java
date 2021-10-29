package ru.cft.focusstart.task2.output;

import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.task2.command_line.dto.UserOption;
import ru.cft.focusstart.task2.figures.Circle;
import ru.cft.focusstart.task2.figures.Figure;
import ru.cft.focusstart.task2.figures.Rectangle;
import ru.cft.focusstart.task2.figures.enum_figures.Figures;
import ru.cft.focusstart.task2.figures.Triangle;
import ru.cft.focusstart.task2.output.characteristics_of_figures.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.StringJoiner;

@Slf4j
public class Output {
    private static StringJoiner joiner = new StringJoiner("\n");

    public static void outputIn(UserOption userOption, Figure figure){
        File outFile = userOption.getOutputFile();
        joiner = preparationOfOutputData(figure);

        if (outFile == null){
            Output.outputInCMD(joiner);
        } else {
            Output.outputInFile(joiner, outFile);
        }
    }

    private static void outputInCMD(StringJoiner joiner){
        System.out.println(joiner);

    }

    private static void outputInFile(StringJoiner joiner, File file){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(joiner.toString());
        } catch (IOException ex){
            log.error("Ошибка при записи в файл {}", file.getName());
        }
    }

    private static StringJoiner preparationOfOutputData(Figure figure){
        String area = formatNum(figure.calcArea());
        String perimeter = formatNum(figure.calcPerimeter());

        joiner.add(BasicParams.TYPE_FIGURE.getRus() + figure.getName())
                .add(BasicParams.AREA.getRus() + area + Units.CM_SQUARE.getRus())
                .add(BasicParams.PERIMETER.getRus() + perimeter + Units.CM.getRus());

        if (figure.getName().equals(Figures.CIRCLE.getRusName())) {
            Circle circle = (Circle) figure;
            addCharacteristic(circle);

        } else if (figure.getName().equals(Figures.RECTANGLE.getRusName())){
            Rectangle rectangle = (Rectangle) figure;
            addCharacteristic(rectangle);

        } else if (figure.getName().equals(Figures.TRIANGLE.getRusName())){
            Triangle triangle = (Triangle) figure;
            addCharacteristic(triangle);
        }

        return joiner;
    }


    private static void addCharacteristic(Circle circle){
        String radius = formatNum(circle.getRadius());
        String diameter = formatNum(circle.calcDiameter());

        joiner.add(CircleParams.RADIUS.getRus() + radius + Units.CM.getRus())
                .add(CircleParams.DIAMETER.getRus() + diameter + Units.CM.getRus());

    }

    private static void addCharacteristic(Rectangle rectangle){
        String diagonal = formatNum(rectangle.calcDiagonal());
        String height = formatNum(rectangle.getHeight());
        String weigh = formatNum(rectangle.getWidth());

        joiner.add(RectangleParams.DIAGONAL_LENGTH.getRus() + diagonal + Units.CM.getRus())
                .add(RectangleParams.HEIGHT.getRus() + height + Units.CM.getRus())
                .add(RectangleParams.WEIGHT.getRus() + weigh + Units.CM.getRus());

    }

    private static void addCharacteristic(Triangle triangle){
        String sideA = formatNum(triangle.getSideA());
        String sideB = formatNum(triangle.getSideB());
        String sideC = formatNum(triangle.getHypotC());

        String alpha = formatNum(triangle.calcAngleAlpha());
        String betta = formatNum(triangle.calcAngleBetta());
        String gamma = formatNum(triangle.calcAngleGamma());

        joiner.add(TriangleParams.A_LENGTH.getRus() + sideA + Units.CM.getRus())
                .add(TriangleParams.ALPHA_ANGLE.getRus() + alpha + Units.DEGREE.getRus())
                .add(TriangleParams.B_LENGTH.getRus() + sideB + Units.CM.getRus())
                .add(TriangleParams.BETTA_ANGLE.getRus() + betta + Units.DEGREE.getRus())
                .add(TriangleParams.C_LENGTH.getRus() + sideC + Units.CM.getRus())
                .add(TriangleParams.GAMMA_ANGLE.getRus() + gamma + Units.DEGREE.getRus());
    }

    private static String formatNum(double num){
        return new DecimalFormat("#.##").format(num);
    }

}
