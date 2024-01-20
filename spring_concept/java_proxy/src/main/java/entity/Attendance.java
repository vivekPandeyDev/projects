package entity;

import java.time.LocalDate;

public class Attendance {
    private LocalDate date;
    private boolean isPresent;

    public Attendance(LocalDate date, boolean isPresent) {
        this.date = date;
        this.isPresent = isPresent;
    }

    public LocalDate getDate() {
        return date;
    }

    public Attendance setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public Attendance setPresent(boolean present) {
        isPresent = present;
        return this;
    }
}
