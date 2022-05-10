package repository;

import domain.Homework;
import validation.*;

public class HomeworkRepository extends AbstractCRUDRepository<String, Homework> {
    public HomeworkRepository(Validator<Homework> validator) {
        super(validator);
    }
}

