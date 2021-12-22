package ru.cft.focusstart.task2.parserfile;

import lombok.extern.slf4j.Slf4j;
import ru.cft.focusstart.task2.figures.enumfigures.Figures;
import ru.cft.focusstart.task2.parserfile.converter.DataConverter;
import ru.cft.focusstart.task2.parserfile.dto.DataFigure;
import ru.cft.focusstart.task2.validators.FigureValidator;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class ParserFile implements ParsingFiles{

    @Override
    public DataFigure parse(File inputFile) throws IOException {
        Figures typeOfFigure = Figures.UNKNOWN;
        List<Double> paramsFigure = new ArrayList<>();
        String line;

        try(BufferedReader reader = new BufferedReader(new FileReader(inputFile, StandardCharsets.UTF_8))) {
            while ((line = reader.readLine()) != null){
                if (isEmptyLine(line, inputFile)){
                    continue;
                }

                if (typeOfFigure == Figures.UNKNOWN){
                    typeOfFigure = parseTypeFigure(line);
                } else if (paramsFigure.isEmpty()){
                    paramsFigure = parseParamsFigure(line);
                }
            }

        } catch (FileNotFoundException ex) {
            log.error("Файл {} не найден.", inputFile.getName());
            throw ex;
        } catch (IOException ex) {
            log.error("Ошибка при чтении файла {}", inputFile.getName());
            throw ex;
        }

        return new DataFigure(typeOfFigure, paramsFigure);
    }

    private static boolean isEmptyLine(String line, File file){
        if (line.isEmpty() ) {
            log.warn("Пустая строка в файле {} будет пропущена.", file.getName());
            return true;
        }

        return false;
    }

    private Figures parseTypeFigure(String line){
        String typeFromFile = line.trim().toUpperCase();

        if (FigureValidator.isFigure(typeFromFile)){
            return Figures.valueOf(typeFromFile);
        }

        log.warn("Неизвестный тип фигуры: {}.", typeFromFile);
        return Figures.UNKNOWN;
    }

    private List<Double> parseParamsFigure(String params){
        List<Double> paramsFigureList = new ArrayList<>();
        String paramsFigure = params.trim();

        if (paramsFigure.contains(" ")){
            String[] paramsArr = paramsFigure.split("\\s+");
            paramsFigureList  = DataConverter.convertParamsArrToDouble(paramsArr);
        } else {
               Optional<Double> paramOptional = DataConverter.convertParamToDouble(paramsFigure);

               if (paramOptional.isPresent()){
                   paramsFigureList.add(paramOptional.get());
               }
        }

        return paramsFigureList;
    }

}
