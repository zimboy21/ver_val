package domain;

import java.util.Objects;

public class Homework implements HasID<String> {
    private String idHomework;
    private String description;
    private int deadline;
    private int startline;

    public Homework(String idHomework, String description, int deadline, int startline) {
        this.idHomework = idHomework;
        this.description = description;
        this.deadline = deadline;
        this.startline = startline;
    }

    @Override
    public String getID() { return idHomework; }

    @Override
    public void setID(String idHomework) { this.idHomework = idHomework; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public int getDeadline() { return deadline; }

    public void setDeadline(int deadline) { this.deadline = deadline; }

    public int getStartline() { return startline; }

    public void setStartline(int startline) { this.startline = startline; }

    @Override
    public String toString() {
        return "Homework{" + "id='" + idHomework + "', description='" + description + ", deadline=" + deadline +
                ", startline=" + startline + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Homework homework = (Homework) o;
        return Objects.equals(idHomework, homework.idHomework);
    }

    @Override
    public int hashCode() { return Objects.hash(idHomework); }
}
