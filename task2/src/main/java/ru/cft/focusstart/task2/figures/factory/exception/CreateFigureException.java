package ru.cft.focusstart.task2.figures.factory.exception;

import lombok.Getter;

@Getter
public class CreateFigureException extends Exception{
    private final String figureName;
    private final String figureParams;

    public CreateFigureException(String msg, String figureName, String figureParams) {
        super(msg);
        this.figureName = figureName;
        this.figureParams = figureParams;
    }
}
