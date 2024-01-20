package org.learn.exception;


public class UserExceptionHandler extends RuntimeException
{
    private static final long serialVersionUID = 1L;
    public UserExceptionHandler(String string) {
        super(string);
    }

}



