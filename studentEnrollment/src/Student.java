import java.io.Serializable;

public class Student implements Serializable {
    private static int roll = 100;
    private Integer rollNo;
    private String firstName;
    private String lastName;
    private String dob;
    private int courseId;

    public Student(String firstName, String lastName,String dob){
        ++roll;
        this.rollNo = roll;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }
    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public Integer getRollNo(){
        return this.rollNo;
    }
    public void setCourseId(int courseId){
        this.courseId = courseId;
    }
    public int getCourseId(){
        return this.courseId;
    }

    public void setDob(String dob){
        this.dob = dob;
    }
    public String getDob(){
        return this.dob;
    }

    public void details(){
        System.out.println(rollNo+" "+firstName+" "+lastName+" "+courseId);
    }
}
