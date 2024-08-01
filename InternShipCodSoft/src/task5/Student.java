package task5;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private String studentID;
    private String name;
    private List<Course> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public boolean registerCourse(Course course) {
        if (registeredCourses.contains(course) || !course.registerStudent()) {
            return false;
        }
        registeredCourses.add(course);
        return true;
    }

    public boolean dropCourse(Course course) {
        if (!registeredCourses.contains(course) || !course.dropStudent()) {
            return false;
        }
        registeredCourses.remove(course);
        return true;
    }
}

