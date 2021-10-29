package ru.cft.focusstart.task2.figures;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public abstract class Figure {
    private final String name;

    protected Figure(String name) {
        this.name = name;
    }

    public abstract double calcArea();
    public abstract double calcPerimeter();

}
