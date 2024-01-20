package propertyEditor;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        LocalDate date =  LocalDate.parse(text,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        setValue(date);
    }
}
