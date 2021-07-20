public class Course {
    private int courseId;
    private String courseName;
    private int fees;

    public Course(int courseId, String courseName, int fees) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.fees = fees;
    }
    public int getCourseId() {
        return this.courseId;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public int getFees() {
        return this.fees;
    }
    public void details(){
        System.out.println(this.courseId+" "+this.courseName+" "+this.fees);
    }
}
class ScienceCourse extends Course{

    public ScienceCourse(int courseId, String courseName, int fees) {
        super(courseId, courseName, fees);
        
    }
    
}
class ArtsCourse extends Course{

    public ArtsCourse(int courseId, String courseName, int fees) {
        super(courseId, courseName, fees);
        
    }
    
}
class CommerceCourse extends Course{

    public CommerceCourse(int courseId, String courseName, int fees) {
        super(courseId, courseName, fees);
        
    }
    
}

