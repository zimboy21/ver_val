package console;

import domain.*;
import service.Service;

import java.util.Scanner;

public class UI {
    private Service service;

    public UI(Service service) {
        this.service = service;
    }

    public void printMenu() {
        System.out.println("1. List all students.");
        System.out.println("2. List all homework.");
        System.out.println("3. List all grades.");

        System.out.println("4. Add a new student.");
        System.out.println("5. Add a new homework.");
        System.out.println("6. Add a grade to a student for a homework.");

        System.out.println("7. Delete an existing student.");
        System.out.println("8. Delete an existing homework.");

        System.out.println("9. Update a student.");

        System.out.println("10. Extend the deadline for a homework.");

        System.out.println("0. EXIT \n");
    }

    public void uiPrintAllStudents() {
        for(Student student : service.findAllStudents()) {
            System.out.println(student);
        }
    }

    public void uiPrintAllHomework() {
        for(Homework homework : service.findAllHomework()) {
            System.out.println(homework);
        }
    }

    public void uiPrintAllGrades() {
        for(Grade grade : service.findAllGrades()) {
            System.out.println(grade);
        }
    }

    public void uiSaveStudent() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the ID of the student: ");
        String id = scanner.nextLine();

        System.out.println("Enter the name of the student: ");
        String name = scanner.nextLine();

        System.out.println("Enter the group of the student: ");
        int group = scanner.nextInt();

        if (service.saveStudent(id, name, group) != 0) {
            System.out.println("Student added successfully! \n");
        }
        else {
            System.out.println("Student exists or is invalid! \n");
        }
    }

    public void uiSaveHomework() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the ID of the homework: ");
        String id = scanner.nextLine();

        System.out.println("Enter the description of the homework: ");
        String description = scanner.nextLine();

        System.out.println("Enter the week of deadline of the homework: ");
        int deadline = scanner.nextInt();

        System.out.println("Enter the startline week for the homework: ");
        int startline = scanner.nextInt();

        if (service.saveHomework(id, description, deadline, startline) != 0) {
            System.out.println("Homework added successfully! \n");
        }
        else {
            System.out.println("Homework exists or is invalid! \n");
        }
    }

    public void uiSaveGrade() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the ID of the student: ");
        String idStudent = scanner.nextLine();

        System.out.println("Enter the ID of the homework: ");
        String idHomework = scanner.nextLine();

        System.out.println("Enter the value of the grade: ");
        String line = scanner.nextLine();
        double valGrade = Double.parseDouble(line);

        System.out.println("Enter the week the homework was delivered: ");
        String line2 = scanner.nextLine();
        int delivered = Integer.parseInt(line2);

        System.out.println("Add a feedback for the homework: ");
        String feedback = scanner.nextLine();

        int result = service.saveGrade(idStudent, idHomework, valGrade, delivered, feedback);
        if (result == 1) {
            service.createStudentFile(idStudent, idHomework);
            System.out.println("Grade added successfully! \n");
        }
        else if (result == 0) {
            System.out.println("Grade exists! \n");
        }
        else {
            System.out.println("Student or grade doesn't exist! \n");
        }
    }

    public void uiDeleteStudent() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the ID of the student: ");
        String id = scanner.nextLine();

        if (service.deleteStudent(id) != 0) {
            System.out.println("Student deleted successfully! \n");
        }
        else {
            System.out.println("Student doesn't exist! \n");
        }
    }

    public void uiDeleteHomework() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the ID of the homework: ");
        String id = scanner.nextLine();

        if (service.deleteHomework(id) != 0) {
            System.out.println("Homework deleted successfully! \n");
        }
        else {
            System.out.println("Homework doesn't exist! \n");
        }
    }

    public void uiUpdateStudent() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the ID of the student: ");
        String id = scanner.nextLine();

        System.out.println("Enter the new name of the student: ");
        String newName = scanner.nextLine();

        System.out.println("Enter the new group for the student: ");
        int newGroup = scanner.nextInt();

        if (service.updateStudent(id, newName, newGroup) != 0) {
            System.out.println("Student updated successfully! \n");
        }
        else {
            System.out.println("Student doesn't exist! \n");
        }
    }

    public void uiExtendDeadline() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the ID of the homework: ");
        String id = scanner.nextLine();

        System.out.println("Enter the new number of weeks added for deadline: ");
        int nrWeeks = scanner.nextInt();

        if (service.extendDeadline(id, nrWeeks) != 0) {
            System.out.println("Deadline extended successfully! \n");
        }
        else {
            System.out.println("Homework doesn't exist! \n");
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int cmd = -1;

        printMenu();

        while(cmd != 0) {
            System.out.println("Please select an option:");
            cmd = scanner.nextInt();

            switch(cmd) {
                case 1:
                    uiPrintAllStudents();
                    break;
                case 2:
                    uiPrintAllHomework();
                    break;
                case 3:
                    uiPrintAllGrades();
                    break;
                case 4:
                    uiSaveStudent();
                    break;
                case 5:
                    uiSaveHomework();
                    break;
                case 6:
                    uiSaveGrade();
                    break;
                case 7:
                    uiDeleteStudent();
                    break;
                case 8:
                    uiDeleteHomework();
                    break;
                case 9:
                    uiUpdateStudent();
                    break;
                case 10:
                    uiExtendDeadline();
                    break;
                case 0:
                    cmd = 0;
                    break;
            }
        }
    }
}
