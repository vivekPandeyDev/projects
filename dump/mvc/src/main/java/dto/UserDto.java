package dto;

import java.time.LocalDate;
import java.util.Arrays;

public class UserDto {
    private int userId;
    private String userName;
    private String password;
    private String gender;
    private String[] hobbies;
    private String preferredHolidayLocation;
    private int age;
    private LocalDate date;

    public int getUserId() {
        return userId;
    }

    public UserDto setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public UserDto setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public UserDto setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String[] getHobbies() {
        return hobbies;
    }

    public UserDto setHobbies(String[] hobbies) {
        this.hobbies = hobbies;
        return this;
    }

    public String getPreferredHolidayLocation() {
        return preferredHolidayLocation;
    }

    public UserDto setPreferredHolidayLocation(String preferredHolidayLocation) {
        this.preferredHolidayLocation = preferredHolidayLocation;
        return this;
    }

    public int getAge() {
        return age;
    }

    public UserDto setAge(int age) {
        this.age = age;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public UserDto setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", hobbies=" + Arrays.toString(hobbies) +
                ", preferredHolidayLocation='" + preferredHolidayLocation + '\'' +
                ", age=" + age +
                ", date=" + date +
                '}';
    }
}
