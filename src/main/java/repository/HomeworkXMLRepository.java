package repository;

import domain.Homework;
import validation.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class HomeworkXMLRepository extends AbstractXMLRepository<String, Homework> {

    public HomeworkXMLRepository(Validator<Homework> validator, String XMLfilename) {
        super(validator, XMLfilename);
        loadFromXmlFile();
    }

    protected Element getElementFromEntity(Homework homework, Document XMLdocument) {
        Element element = XMLdocument.createElement("homework");
        element.setAttribute("ID", homework.getID());

        element.appendChild(createElement(XMLdocument, "Description", homework.getDescription()));
        element.appendChild(createElement(XMLdocument, "Deadline", String.valueOf(homework.getDeadline())));
        element.appendChild(createElement(XMLdocument, "Startline", String.valueOf(homework.getStartline())));

        return element;
    }

    protected Homework getEntityFromNode(Element node) {
        String ID = node.getAttributeNode("ID").getValue();
        String description = node.getElementsByTagName("Description").item(0).getTextContent();
        int deadline = Integer.parseInt(node.getElementsByTagName("Deadline").item(0).getTextContent());
        int startline = Integer.parseInt(node.getElementsByTagName("Startline").item(0).getTextContent());

        return new Homework(ID, description, deadline, startline);
    }
}
