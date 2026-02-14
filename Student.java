public class Student {
    private String id;
    private String course;
    private String name;

    public Student(String id, String course, String name) {
        this.id = id;
        this.course = course;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String toString() {
        return "ID: " + id + ", Course: " + course + ", Name: " + name;
    }
}