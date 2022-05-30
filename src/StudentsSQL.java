import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentsSQL implements StudentInterface {

    private String _conn;

    public StudentsSQL(String conn){
        this._conn = conn;
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> allStudents = new ArrayList<>();
        Connection conn;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");

            conn = DriverManager.getConnection(_conn);

            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM dacss.students";

            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()){
                Student s = new Student();
                s.setName( rs.getString("name"));
                s.setId(rs.getInt("id"));

                allStudents.add(s);

            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return allStudents;
    }

    @Override
    public Student getStudent(String studentName) {
        Student s = new Student();
        Connection conn;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");

            conn = DriverManager.getConnection(_conn);

            Statement stmt = conn.createStatement();

            studentName = '\'' + studentName + '\'';

            String query = "SELECT * FROM dacss.students WHERE name = " + studentName;

            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()){
                s.setName( rs.getString("name"));
                s.setId(rs.getInt("id"));

                return s;
            }
            rs.close();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public void addStudent(String studentName) {
        Connection conn;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");

            conn = DriverManager.getConnection(_conn);

            studentName = '\'' + studentName + '\'';

            List<Student> allStudents = getAllStudents();
            int lastId;

            if(allStudents != null)
                lastId= allStudents.get(allStudents.size() - 1).getId() + 1;
            else
                lastId = 1;

            String query = "INSERT INTO dacss.students VALUES (" + lastId + ","  + studentName + ")";

            PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.executeUpdate();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
