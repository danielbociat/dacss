import java.util.List;
import java.util.Scanner;

public class ClientSide {

    public static void main(String[] arg){
        String xmlFileName = "src/students.xml";
        StudentsXML fileXML = new StudentsXML(xmlFileName);

        String SQLConn = "jdbc:jtds:sqlserver://127.0.0.1:1433/DACSS";
        StudentsSQL dbSQL = new StudentsSQL(SQLConn);

        HoroscopeCalc horoscope;

        boolean ok = true;

        while(ok){
            System.out.println("MENU");
            System.out.println("Select an option:");
            System.out.println("1. Get the horoscope for all the students in XML");
            System.out.println("2. Get the horoscope for a single student in XML");
            System.out.println("3. Add a student to the XML file");
            System.out.println("4. Get the horoscope for all the students in SQL database");
            System.out.println("5. Get the horoscope for a single student in SQL database");
            System.out.println("6. Add a student to the SQL database");
            System.out.println("0. Exit");
            System.out.println("Enter your choice: ");

            int option;
            Scanner keyboard = new Scanner(System.in);
            option = Integer.parseInt(keyboard.nextLine());

            switch(option){
                case 1:{
                    horoscope = new HoroscopeCalc(fileXML);
                    horoscope.printAllHoroscopeResult();
                    break;
                }

                case 2:{
                    System.out.println("Enter the student's name: ");
                    String sname = keyboard.nextLine();
                    horoscope = new HoroscopeCalc(fileXML);
                    horoscope.printHoroscopeResult(sname);
                    break;
                }

                case 3:{
                    System.out.println("Enter the student's name: ");
                    String sname = keyboard.nextLine();
                    fileXML.addStudent(sname);
                    break;
                }

                case 4:{
                    horoscope = new HoroscopeCalc(dbSQL);
                    horoscope.printAllHoroscopeResult();
                    break;
                }

                case 5:{
                    System.out.println("Enter the student's name: ");
                    String sname = keyboard.nextLine();
                    horoscope = new HoroscopeCalc(dbSQL);
                    horoscope.printHoroscopeResult(sname);
                    break;
                }

                case 6:{
                    System.out.println("Enter the student's name: ");
                    String sname = keyboard.nextLine();
                    dbSQL.addStudent(sname);
                    break;
                }

                default:{
                    ok = false;
                    break;
                }
            }

            System.out.println();
        }




    }

}
