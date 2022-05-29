import java.util.List;
import java.util.Locale;
import java.time.LocalDate;
import java.time.Month;

public class HoroscopeCalc {
    private StudentInterface _student;
    private final String vowels = "aeiou";

    public HoroscopeCalc(StudentInterface student){
        this._student = student;
    }

    public void printHoroscopeResult(String name){
        Student student = _student.getStudent(name);

        if(student == null){
            System.out.println("Student " + name + " does not exist");
            return;
        }

        System.out.println(getHoroscopeMessage(student));
    }

    public void printAllHoroscopeResult(){
        List<Student> allStudents = _student.getAllStudents();

        if(allStudents.isEmpty()){
            System.out.println("There are no students");
            return;
        }

        for(Student s: allStudents){
            System.out.println(getHoroscopeMessage(s));
        }
    }

    public String getHoroscopeMessage(Student student){
        String verdict = getVerdict(student);

        return student.getName() + " will have a " + verdict + " day!";
    }

    public String getVerdict(Student student){
        String name = student.getName().toLowerCase();
        int cnt = 0;
        String strC;

        for(char ch : name.toCharArray()){
            strC = Character.toString(ch);

            if (vowels.contains(strC))
                cnt++;
        }

        LocalDate currentdate = LocalDate.now();
        int day = currentdate.getDayOfMonth();


        if((cnt != 0) && (day % cnt == 0))
            return "good";
        return "bad";
    }

}
