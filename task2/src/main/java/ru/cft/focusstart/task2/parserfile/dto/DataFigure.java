package ru.cft.focusstart.task2.parserfile.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.cft.focusstart.task2.figures.enumfigures.Figures;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Getter
public class DataFigure  {
    Figures typeOfFigure;
    List<Double> paramsFigure;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof DataFigure) {
            DataFigure that = (DataFigure) obj;
            return typeOfFigure == that.typeOfFigure &&
                    Objects.equals(paramsFigure, that.paramsFigure);
        }
        return  false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeOfFigure, paramsFigure);
    }
}
