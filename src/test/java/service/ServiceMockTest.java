package service;

import domain.Grade;
import domain.Homework;
import domain.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import repository.GradeXMLRepository;
import repository.HomeworkXMLRepository;
import repository.StudentXMLRepository;
import validation.GradeValidator;
import validation.HomeworkValidator;
import validation.StudentValidator;
import validation.Validator;

import static org.mockito.Mockito.*;

public class ServiceMockTest {
    @InjectMocks
    static Service mockService;
    @Mock
    static StudentXMLRepository mockStudentXMLRepo;
    @Mock
    static HomeworkXMLRepository mockHomeworkXMLRepo;
    @Mock
    static GradeXMLRepository mockGradeXMLRepo;
    @Mock
    static Validator<Student> studentValidator;
    @Mock
    static Validator<Homework> homeworkValidator;
    @Mock
    static Validator<Grade> gradeValidator;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    static void setUp() {
        studentValidator = mock(StudentValidator.class);
        homeworkValidator = mock(HomeworkValidator.class);
        gradeValidator = mock(GradeValidator.class);

        mockStudentXMLRepo = mock(StudentXMLRepository.class);
        mockHomeworkXMLRepo = mock(HomeworkXMLRepository.class);
        mockGradeXMLRepo = mock(GradeXMLRepository.class);

        mockService = new Service(mockStudentXMLRepo, mockHomeworkXMLRepo, mockGradeXMLRepo);
    }

    @Test
    void testingSaveHomework() {
        Homework homework = new Homework("mockhomework", "this is a mocked homework", 10, 15);
        when(mockHomeworkXMLRepo.save(any(Homework.class))).thenReturn(homework);
        int res = mockService.saveHomework("mockhomework", "this is a mocked homework", 10, 15);
        verify(mockHomeworkXMLRepo).save(new Homework("mockhomework", "this is a mocked homework", 10, 15));
        Assertions.assertEquals(0,res);
    }

    @Test
    void testingDeleteStudent() {
        Student student = new Student("mockstudent", "Taltos Bandi", 420);
        when(mockStudentXMLRepo.delete(anyString())).thenReturn(student);
        int res = mockService.deleteStudent("mockstudent");
        verify(mockStudentXMLRepo).delete(anyString());
        Assertions.assertEquals(1, res);
    }

    @Test
    void testingUpdateStudent() {
        Student student = new Student("mockstudent", "Taltos Bandi", 420);
        when(mockStudentXMLRepo.update(any(Student.class))).thenReturn(student);
        int res = mockService.updateStudent("mockstudent", "Taltos Bandi", 420);
        verify(mockStudentXMLRepo).update(student);
        Assertions.assertEquals(1, res);

    }
}
