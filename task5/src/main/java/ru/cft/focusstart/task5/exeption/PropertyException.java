package ru.cft.focusstart.task5.exeption;

public class PropertyException extends RuntimeException{
    String propertyName;

    public PropertyException(String message, String propertyName) {
        super(message);
        this.propertyName = propertyName;
    }

    public String getPropertyName() {
        return propertyName;
    }
}
