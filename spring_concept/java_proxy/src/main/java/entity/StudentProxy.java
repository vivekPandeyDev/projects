package entity;

public class StudentProxy extends Student{
    public StudentProxy(Attendance attendance) {
        super(attendance);
    }

    @Override
    public void attendLesson() {
        if(super.getAttendance().isPresent())
            super.attendLesson();
        else{
            System.out.println("student is not present cannot attend lesson");
        }
    }
}
