public class StudentWithTotalCredits {
    private int id;
    private String name;
    private int totalCredits;

    public StudentWithTotalCredits(int id, String name, int totalCredits) {
        this.id = id;
        this.name = name;
        this.totalCredits = totalCredits;
    }

    @Override
    public String toString() {
        return "StudentWithTotalCredits{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", totalCredits=" + totalCredits +
                '}';
    }
}
