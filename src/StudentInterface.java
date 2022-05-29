import java.util.List;

public interface StudentInterface {
    List<Student> getAllStudents();
    Student getStudent(String name);
    void addStudent(String name);
}
