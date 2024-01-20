package temp_entity;

import org.springframework.stereotype.Component;

@Component
public class Calculator {

    public int add(int a,int b){
        return a+b;
    }

    public double add(double a, double b){
        return a+b;
    }

    public String add(String a,String b){
        return a+b;
    }

    public int subtract(int a, int b){
        return  a-b;
    }

    public double subtract(double a, double b){
        return a-b;
    }

    public int multiply(int a, int b){
        return  a*b;
    }

    public double multiply(double a,double b){
        return  a*b;
    }

    public int divide(int a, int b){
        if(b == 0)
            throw new ArithmeticException("denominator cannot be zero!!!");
        return a/b;
    }

    public double divide(double a, double b){
        return a/b;
    }
}
