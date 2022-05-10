package validation;
import domain.Grade;

public class GradeValidator implements Validator<Grade> {
    public void validate(Grade grade) throws ValidationException {
        if (grade.getID().getObject1() == null || grade.getID().equals("")) {
            throw new ValidationException("ID Student invalid! \n");
        }
        if (grade.getID().getObject2() == null || grade.getID().equals("")) {
            throw new ValidationException("ID Homework invalid! \n");
        }
        if (grade.getGrade() < 0 || grade.getGrade() > 10) {
            throw new ValidationException("Grade invalid! \n");
        }
        if (grade.getDeliveryWeek() < 0) {
            throw new ValidationException("Delivery week invalid! \n");
        }
    }
}
