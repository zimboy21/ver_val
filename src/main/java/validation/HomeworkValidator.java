package validation;
import domain.Homework;

public class HomeworkValidator implements Validator<Homework> {
    public void validate(Homework homework) throws ValidationException {
        if (homework.getID() == null || homework.getID().equals("")) {
            throw new ValidationException("ID invalid! \n");
        }
        if (homework.getDescription() == null || homework.getDescription().equals("")) {
            throw new ValidationException("Description invalid! \n");
        }
        if (homework.getDeadline() < 1 || homework.getDeadline() > 14 || homework.getDeadline() < homework.getStartline()) {
            throw new ValidationException("Deadline invalid! \n");
        }
        if (homework.getStartline() < 1 || homework.getStartline() > 14 || homework.getStartline() > homework.getDeadline()) {
            throw new ValidationException("Date received is invalid! \n");
        }
    }
}

