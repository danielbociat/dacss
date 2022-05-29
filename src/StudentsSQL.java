import com.sun.corba.se.pept.transport.ConnectionCache;

import java.sql.*;
import java.util.List;

public class StudentsSQL implements StudentInterface {

    private String _conn;

    public StudentsSQL(String conn){
        this._conn = conn;
    }

    @Override
    public List<Student> getAllStudents() {

        Connection conn = null;
        try {
            //Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(_conn);

            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM students";

            ResultSet rs = stmt.executeQuery(query);
            System.out.println("id\tname");
            while(rs.next()){
                System.out.println(rs.getInt("id") + "\t" + rs.getString("name"));
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Student getStudent(String name) {
        return null;
    }

    @Override
    public void addStudent(String name) {

    }
}
