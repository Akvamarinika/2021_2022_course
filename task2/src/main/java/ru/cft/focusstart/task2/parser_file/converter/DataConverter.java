package ru.cft.focusstart.task2.parser_file.converter;

import lombok.extern.slf4j.Slf4j;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class DataConverter {
    public static Optional<Double> convertParamToDouble(String param){
        Optional<Double> optional = Optional.empty();

        try {
            double paramNum = Double.parseDouble(param);
            optional = Optional.of(paramNum);
        } catch (NumberFormatException ex) {
            log.warn("Не удалось преобразовать параметр \"{}\" фигуры к числу.", param);
        }

        return optional;
    }

    public static List<Double> convertParamsArrToDouble(String[] params){
        return Arrays.stream(params)
                .map(DataConverter::convertParamToDouble)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}
