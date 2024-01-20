package entity;

public class Student implements DailySession {

    private Attendance attendance;

    public Student() {
    }

    public Student(Attendance attendance) {
        this.attendance = attendance;
    }

    @Override
    public void attendLesson() {
        System.out.println("attending lesson!!!!");
    }

    public void attendLesson(Integer id){
        System.out.println("attending lesson with id: "+id);
    }
    public Attendance getAttendance() {
        return attendance;
    }

    public Student setAttendance(Attendance attendance) {
        this.attendance = attendance;
        return this;
    }
}
