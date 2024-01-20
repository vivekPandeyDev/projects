package utility;

import java.beans.PropertyEditorSupport;


public class LoanStatusEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
	LoanStatus loanStatus = LoanStatus.valueOf(text);
	setValue(loanStatus);
    }

}
