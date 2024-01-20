package customtag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

public class PrintMessage extends SimpleTagSupport {
    private String message;

    public void setMessage(String msg) {
        this.message = msg;
    }

    StringWriter stringWriter = new StringWriter();

    public void doTag() throws JspException, IOException {
        if (message != null) {
            JspWriter out = getJspContext().getOut();
            out.println(message);
        } else {
            getJspBody().invoke(stringWriter);
            getJspContext().getOut().println(stringWriter.toString());
        }
    }
}


