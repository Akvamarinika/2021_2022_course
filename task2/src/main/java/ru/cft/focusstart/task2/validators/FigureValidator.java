package ru.cft.focusstart.task2.validators;
import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.task2.figures.enumfigures.Figures;
import ru.cft.focusstart.task2.parserfile.dto.DataFigure;

import java.util.List;

@Slf4j
public class FigureValidator {
    public static boolean isFigure(String typeFigure){
        return (Figures.CIRCLE.name().equalsIgnoreCase(typeFigure))
                || (Figures.RECTANGLE.name().equalsIgnoreCase(typeFigure))
                || (Figures.TRIANGLE.name().equalsIgnoreCase(typeFigure));
    }

    public static boolean isPositiveParamsFigure(List<Double> params){
        for (double param : params){
            if (param <= 0){
                log.warn("В параметрах фигуры найдены отрицательные или нулевые значения: {}", param);
                return false;
            }
        }
        return true;
    }

//    public static boolean isMatchCountParamsFigureType(Figures typeOfFigure, List<Double> paramsFigure){
//        int countParams = paramsFigure.size();
//        int expectedCountParams = typeOfFigure.getNumberOfParams();
//
//        if (countParams != expectedCountParams ) {
//            log.warn("Несоответствие количества параметров для фигуры {}. Ожидалось {}, получено {}.",
//                    typeOfFigure.name(), expectedCountParams, countParams);
//            return false;
//        }
//
//        return true;
//    }

//    public static boolean isCircle(DataFigure dataFigure){//************************************************************
//        List<Double> paramsFigure = dataFigure.getParamsFigure();
//        return paramsFigure.size() == Figures.CIRCLE.getNumberOfParams();
// }

//    public static boolean isPositiveParamsFigure(List<Double> params){
//        for (double param : params){
//            if (param <= 0){
//                log.warn("В параметрах фигуры найдены отрицательные или нулевые значения: {}", param);
//                return false;
//            }
//        }
//        return true;
//    }


//    public static boolean isRectangle(DataFigure dataFigure){
//        List<Double> paramsFigure = dataFigure.getParamsFigure();
//
//        if (paramsFigure.size() != Figures.RECTANGLE.getNumberOfParams()){
//            return false;
//        }
//
//        double sideA = paramsFigure.get(0);
//        double sideB = paramsFigure.get(1);
//
//        if (isSquare(sideA, sideB)){
//            log.error("извините, данная фигура \"SQUARE\" не поддерживается.");
//            return false;
//        }
//
//        return true;
//    }
//
//    private static boolean isSquare(double sideA, double sideB){
//        double eps = 0.0001;
//        return Math.abs(sideA - sideB) < eps;
//    }


//    public static boolean isTriangle(DataFigure dataFigure){
//        List<Double> paramsFigure = dataFigure.getParamsFigure();
//
//        if (paramsFigure.size() != Figures.TRIANGLE.getNumberOfParams()){
//            return false;
//        }
//
//        double sideA = paramsFigure.get(0);
//        double sideB = paramsFigure.get(1);
//        double hypotC = paramsFigure.get(2);
//
//        if (!isExistTriangle(sideA, sideB, hypotC)){
//            log.error("Треугольник не существует. Сумма длин двух его сторон, должна быть больше длины третьей стороны.");
//            return false;
//        }
//
//        return true;
//    }
//
//    private static boolean isExistTriangle(double sideA, double sideB, double hypotC){
//        return (sideA + sideB > hypotC) && (sideA + hypotC > sideB) && (sideB + hypotC > sideA);
//    }

}
