package com.vivek.pandey.api.movie.formatter;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class SpaceToUnderscoreFormatter implements AnnotationFormatterFactory<ConvertSpaceToUnderscore>{
    @Override
    public Set<Class<?>> getFieldTypes() {
        return Set.of(String.class);
    }

    @Override
    public Printer<?> getPrinter(ConvertSpaceToUnderscore annotation, Class<?> fieldType) {
        return (value,locale) -> String.valueOf(value);
    }

    @Override
    public Parser<?> getParser(ConvertSpaceToUnderscore annotation, Class<?> fieldType) {
        return (text, locale) -> text.replace('_', ' ');
    }
}
