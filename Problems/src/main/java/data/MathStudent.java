package data;

public class MathStudent implements Student{
    private String name;
    private String surname;
    private int age;
    private String nationality;
    private double gpa;
    private int mathLevel;

    public MathStudent(String name, String surname, int age, String nationality, double gpa, int mathLevel) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.nationality = nationality;
        this.gpa = gpa;
        this.mathLevel = mathLevel;
    }

    public MathStudent(){};

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void displayMathStudentInfo() {
        System.out.println("Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Age: " + age);
        System.out.println("Nationality: " + nationality);
        System.out.println("GPA: " + gpa);
        System.out.println("Level of knowledge: " + mathLevel);
    }
}
