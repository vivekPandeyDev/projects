package reflectionApi;

import entity.Student;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Class<?> studentClass = Class.forName("entity.Student");
        Constructor<?>[] studentConstructors =  studentClass.getConstructors();
        for(Constructor studentConstructor : studentConstructors){
            System.out.println(studentConstructor);
        }
        Method [] methods =  studentClass.getDeclaredMethods();
        for(Method method : methods){
            System.out.println(method);
        }

//        Class<Student> studentClass1 =Student.class;
//        new Student().getClass();

//        calling the method using reflection api
        Class[] methodArgs = new Class[]{Integer.class};
        Student student = (Student)studentClass.newInstance();
        Method attendLesson =  studentClass.getDeclaredMethod("attendLesson",methodArgs);
        attendLesson.invoke(student,10);
    }
}
