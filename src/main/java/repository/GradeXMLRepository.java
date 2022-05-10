package repository;

import domain.Grade;
import domain.Pair;
import domain.Student;
import validation.StudentValidator;
import validation.HomeworkValidator;
import validation.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GradeXMLRepository extends AbstractXMLRepository<Pair<String, String>, Grade> {

    public GradeXMLRepository(Validator<Grade> validator, String XMLfilename) {
        super(validator, XMLfilename);
        loadFromXmlFile();
    }

    protected Element getElementFromEntity(Grade grade, Document XMLdocument) {
        Element element = XMLdocument.createElement("grade");
        element.setAttribute("IDStudent", grade.getID().getObject1());
        element.setAttribute("IDHomework", grade.getID().getObject2());

        element.appendChild(createElement(XMLdocument, "GradeValue", String.valueOf(grade.getGrade())));
        element.appendChild(createElement(XMLdocument, "DeliveryWeek", String.valueOf(grade.getDeliveryWeek())));
        element.appendChild(createElement(XMLdocument, "Feedback", grade.getFeedback()));

        return element;
    }

    protected Grade getEntityFromNode(Element node) {
        String IDStudent = node.getAttributeNode("IDStudent").getValue();
        String IDHomework= node.getAttributeNode("IDHomework").getValue();
        double gradeValue = Double.parseDouble(node.getElementsByTagName("GradeValue").item(0).getTextContent());
        int deliveryWeek = Integer.parseInt(node.getElementsByTagName("DeliveryWeek").item(0).getTextContent());
        String feedback = node.getElementsByTagName("Feedback").item(0).getTextContent();

        return new Grade(new Pair(IDStudent, IDHomework), gradeValue, deliveryWeek, feedback);
    }

    public void createFile(Grade gradeObj) {
        String idStudent = gradeObj.getID().getObject1();
        StudentValidator sval = new StudentValidator();
        HomeworkValidator tval = new HomeworkValidator();
        StudentFileRepository srepo = new StudentFileRepository(sval, "students.txt");
        HomeworkFileRepository trepo = new HomeworkFileRepository(tval, "homework.txt");

        Student student = srepo.findOne(idStudent);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(student.getName() + ".txt", false))) {
            super.findAll().forEach(nota -> {
                if (nota.getID().getObject1().equals(idStudent)) {
                    try {
                        bw.write("Homework: " + nota.getID().getObject2() + "\n");
                        bw.write("Grade: " + nota.getGrade() + "\n");
                        bw.write("Delivered in week: " + nota.getDeliveryWeek() + "\n");
                        bw.write("Deadline: " + trepo.findOne(nota.getID().getObject2()).getDeadline() + "\n");
                        bw.write("Feedback: " + nota.getFeedback() + "\n\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}

