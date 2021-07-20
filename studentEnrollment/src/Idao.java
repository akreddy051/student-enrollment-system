import java.util.ArrayList;

public interface Idao {
    public abstract void serialize(ArrayList<Student> students,ArrayList<Course> courses);
    public abstract Object deserialize();
}
