import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentsXML implements StudentInterface{

    private String _file;

    public StudentsXML(String file){
        this._file = file;
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> allStudents = new ArrayList<>();

        try {
            File xmlFile = new File(this._file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();


            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("student");

            for(int i = 0; i < nList.getLength(); i++){
                Node nNode = nList.item(i);

                if(nNode.getNodeType() == Node.ELEMENT_NODE){
                    Element eElement = (Element) nNode;

                    String id = eElement.getElementsByTagName("id").item(0).getTextContent();
                    String name = eElement.getElementsByTagName("name").item(0).getTextContent();

                    Student s = new Student();
                    s.setName(name);
                    s.setId(Integer.parseInt(id));

                    allStudents.add(s);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return allStudents;
    }

    @Override
    public Student getStudent(String studentName) {
        Student s = new Student();

        try {
            File xmlFile = new File(this._file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("student");

            for(int i = 0; i < nList.getLength(); i++){
                Node nNode = nList.item(i);

                if(nNode.getNodeType() == Node.ELEMENT_NODE){
                    Element eElement = (Element) nNode;

                    String id = eElement.getElementsByTagName("id").item(0).getTextContent();
                    String name = eElement.getElementsByTagName("name").item(0).getTextContent();

                    if(name.equals(studentName)){
                        s.setName(name);
                        s.setId(Integer.parseInt(id));

                        return s;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void addStudent(String name) {
        try{
            File xmlFile = new File(this._file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            Element dElement = doc.getDocumentElement();

            List<Student> allStudents= getAllStudents();
            int lastId = allStudents.get(allStudents.size() - 1).getId();

            Element textNode = doc.createElement("id");
            textNode.setTextContent(Integer.toString(lastId+1));

            Element textNode1 = doc.createElement("name");
            textNode1.setTextContent(name);

            Element nodeElement = doc.createElement("student");
            nodeElement.appendChild(textNode);
            nodeElement.appendChild(textNode1);

            dElement.appendChild(nodeElement);
            doc.replaceChild(dElement, dElement);

            Transformer tFormer = TransformerFactory.newInstance().newTransformer();
            tFormer.setOutputProperty(OutputKeys.METHOD, "xml");
            Source source = new DOMSource(doc);
            Result result = new StreamResult(xmlFile);

            tFormer.transform(source, result);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
