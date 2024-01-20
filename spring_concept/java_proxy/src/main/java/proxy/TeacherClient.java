package proxy;

import entity.Attendance;
import entity.DailySession;
import entity.Student;
import entity.StudentProxy;

import java.time.LocalDate;

public class TeacherClient {
    public static void main(String[] args) {
        DailySession student = new StudentProxy(new Attendance(LocalDate.now(),true));
        DailySession student2 = new StudentProxy(new Attendance(LocalDate.now(),false));
        student.attendLesson();
        student2.attendLesson();
        student.attendLesson();
    }
}
