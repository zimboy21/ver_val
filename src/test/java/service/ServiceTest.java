package service;

import domain.Grade;
import domain.Homework;
import domain.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import repository.GradeXMLRepository;
import repository.HomeworkXMLRepository;
import repository.StudentXMLRepository;
import validation.*;

class ServiceTest {
    static Service service;

    @BeforeAll
    static void setUp() {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Homework> homeworkValidator = new HomeworkValidator();
        Validator<Grade> gradeValidator = new GradeValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "students.xml");
        HomeworkXMLRepository fileRepository2 = new HomeworkXMLRepository(homeworkValidator, "homework.xml");
        GradeXMLRepository fileRepository3 = new GradeXMLRepository(gradeValidator, "grades.xml");

        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    //1
    @Test
    void studentShouldBeDeleted() {
        service.saveStudent("deleteStudentTesting", "deleteStudentTesting", 500);
        Iterable<Student> students = service.findAllStudents();
        String id = "";
        boolean ok = true;
        for (Student s : students) {
            id = s.getID();
        }
        service.deleteStudent(id);
        students = service.findAllStudents();
        for (Student s : students) {
            if (id.equals(s.getID())) {
                ok = false;
                break;
            }
        }
        Assertions.assertTrue(ok);
    }

    //2
    @Test
    void studentShouldBeSaved() {
        int counter = 0;
        int counterAfterInsert = 0;

        Iterable<Student> students = service.findAllStudents();
        for (Student s : students) {
            counter += 1;
        }

        service.saveStudent("insertStudentTesting", "insertStudentTesting", 501);
        students = service.findAllStudents();
        for (Student s : students) {
            counterAfterInsert += 1;
        }

        Assertions.assertEquals(counterAfterInsert, counter + 1);
    }

    //3
    @Test
    void homeworkShouldBeSaved() {
        int counter = 0;
        int counterAfterInsert = 0;

        Iterable<Homework> homeworks = service.findAllHomework();
        for (Homework hw : homeworks) {
            counter += 1;
        }

        service.saveHomework("insertHomeworkTesting", "insertHomeworkTesting", 5, 1);
        homeworks = service.findAllHomework();
        for (Homework hw : homeworks) {
            counterAfterInsert += 1;
        }

        Assertions.assertEquals(counterAfterInsert, counter + 1);
    }

    //4
    @ParameterizedTest
    @ValueSource(ints = {110, 111, 937 , 938})
    void testingStudentGroupUpdateOnBoundaries(int group) {
        Iterable<Student> students = service.findAllStudents();
        int actualGroup = 0;
        String name = "";
        for (Student s : students) {
            if (s.getID().equals("2")) {
                actualGroup = s.getGroup();
                name = s.getName();
                break;
            }
        }
        service.updateStudent("2", name, group);

        students = service.findAllStudents();
        for (Student s : students) {
            if (s.getID().equals("2")) {
                actualGroup = s.getGroup();
            }
        }
        Assertions.assertEquals(actualGroup, group);
    }

    //5
    @Test
    void homeworkShouldBeUpdated() {
        service.updateHomework("updateHomeworkTest", "updated", 0, 1);
        Iterable<Homework> hw = service.findAllHomework();
        Homework updatedHomework = new Homework("updateHomeworkTest", "updated", 0, 1);
        Homework actualHomework = new Homework("updateHomeworkTest", "updated", 0, 1);
        for (Homework h : hw) {
            if (h.getID().equals("updateHomeworkTest")) {
                updatedHomework = h;
            }
        }
        Assertions.assertEquals(updatedHomework, actualHomework);
    }

    @Test
    void testingSaveStudentWithInvalidData() {
        Assertions.assertThrows(ValidationException.class, () -> {
            service.saveStudent("validId", "validName", 0);
        });
    }
}