package repository;

import domain.Homework;
import validation.*;

import java.io.*;
import java.util.stream.Collectors;

public class HomeworkFileRepository extends AbstractFileRepository<String, Homework> {

    public HomeworkFileRepository(Validator<Homework> validator, String filename) {
        super(validator, filename);
        loadFromFile();
    }

    protected void loadFromFile() {
        try (BufferedReader buffer = new BufferedReader(new FileReader(filename))) {
            buffer.lines().collect(Collectors.toList()).forEach(line -> {
                String[] result = line.split("#");
                Homework homework = new Homework(result[0], result[1], Integer.parseInt(result[2]), Integer.parseInt(result[3]));
                try {
                    super.save(homework);
                } catch (ValidationException ve) {
                    ve.printStackTrace();
                }
            });
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    protected void writeToFile(Homework homework) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
            bw.write(homework.getID() + "#" + homework.getDescription() + "#" + homework.getDeadline() + "#" + homework.getStartline() + "\n");
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    protected void writeToFileAll() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, false))) {
            super.entities.values().forEach(homework -> {
                try {
                    bw.write(homework.getID() + "#" + homework.getDescription() + "#" + homework.getDeadline() + "#" + homework.getStartline() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
