package utility;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateOfBirthEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	LocalDate date = LocalDate.parse(text, formatter);
	setValue(date);
    }

}
