package repository;
import domain.Student;
import validation.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class StudentXMLRepository extends AbstractXMLRepository<String, Student> {

    public StudentXMLRepository(Validator<Student> validator, String XMLfilename) {
        super(validator, XMLfilename);
        loadFromXmlFile();
    }

    protected Element getElementFromEntity(Student student, Document XMLdocument) {
        Element element = XMLdocument.createElement("student");
        element.setAttribute("ID", student.getID());

        element.appendChild(createElement(XMLdocument, "Name", student.getName()));
        element.appendChild(createElement(XMLdocument, "Group", String.valueOf(student.getGroup())));

        return element;
    }

    protected Student getEntityFromNode(Element node) {
        String ID = node.getAttributeNode("ID").getValue();
        String name = node.getElementsByTagName("Name").item(0).getTextContent();
        int group = Integer.parseInt(node.getElementsByTagName("Group").item(0).getTextContent());

        return new Student(ID, name, group);
    }
}
