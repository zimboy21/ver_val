package validation;
import domain.Student;

public class StudentValidator implements Validator<Student> {
    public void validate(Student student) throws ValidationException {
        if (student.getID() == null || student.getID().equals("")) {
            throw new ValidationException("ID invalid! \n");
        }
        if (student.getName() == null || student.getName().equals("")) {
            throw new ValidationException("Name invalid! \n");
        }
        if (student.getGroup() <= 110 || student.getGroup() >= 938) {
            throw new ValidationException("Group invalid! \n");
        }
    }
}

