package repository;
import domain.*;
import validation.*;

import java.io.*;
import java.util.stream.Collectors;

public class GradeFileRepository extends AbstractFileRepository<Pair<String, String>, Grade> {

    public GradeFileRepository(Validator<Grade> validator, String filename) {
        super(validator, filename);
        loadFromFile();
    }

    protected void loadFromFile() {
        try (BufferedReader buffer = new BufferedReader(new FileReader(filename))) {
            buffer.lines().collect(Collectors.toList()).forEach(line -> {
                String[] result = line.split("#");
                Grade grade = new Grade(new Pair(result[0], result[1]), Double.parseDouble(result[2]),
                        Integer.parseInt(result[3]), result[4]);
                try {
                    super.save(grade);
                } catch (ValidationException ve) {
                    ve.printStackTrace();
                }
            });
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    protected void writeToFile(Grade grade) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
            bw.write(grade.getID().getObject1() + "#" + grade.getID().getObject2() + "#" + grade.getGrade() + "#"
                    + grade.getDeliveryWeek() + "#" + grade.getFeedback() + "\n");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    protected void writeToFileAll() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, false))) {
            super.entities.values().forEach(grade -> {
                try {
                    bw.write(grade.getID().getObject1() + "#" + grade.getID().getObject2() + "#" + grade.getGrade()
                            + "#" + grade.getDeliveryWeek() + "#" + grade.getFeedback() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


}