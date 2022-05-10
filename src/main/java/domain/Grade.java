package domain;

public class Grade implements HasID<Pair<String, String>> {
    Pair<String, String> idGrade;
    private double grade;
    private int deliveryWeek;
    private String feedback;

    public Grade(Pair<String, String> idGrade, double grade, int deliveryWeek, String feedback) {
        this.idGrade = idGrade;
        this.grade = grade;
        this.deliveryWeek = deliveryWeek;
        this.feedback = feedback;
    }

    @Override
    public Pair<String, String> getID() { return idGrade; }

    @Override
    public void setID(Pair<String, String> idGrade) { this.idGrade = idGrade; }

    public double getGrade() { return grade; }

    public void setGrade(double grade) { this.grade = grade; }

    public int getDeliveryWeek() { return deliveryWeek; }

    public void setDeliveryWeek(int deliveryWeek) { this.deliveryWeek = deliveryWeek; }

    public String getFeedback() { return feedback; }

    public void setFeedback(String feedback) { this.feedback = feedback; }

    @Override
    public String toString() {
        return "Grade{" +
                "id_student = " + idGrade.getObject1() +
                ", id_homework = " + idGrade.getObject2() +
                ", grade = " + grade +
                ", deliveryWeek = " + deliveryWeek +
                ", feedback = '" + feedback + '\'' +
                '}';
    }
}
