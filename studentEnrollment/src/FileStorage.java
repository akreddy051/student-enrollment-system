import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileStorage implements Idao{

    @Override
    public void serialize(ArrayList<Student> students, ArrayList<Course> courses) {
        String fileName ="students.ser";
        try{
            //saving object in a file
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(file);

            for(Student i:students){
                out.writeObject(i);
            }
            out.close();
            file.close();

            System.out.println("Object has been serialized");
        }
        catch(IOException e) {
            System.out.println(e);
        }
        
    }

    @Override
    public ArrayList<Student> deserialize() {
        ArrayList<Student> a = new ArrayList<Student>();
        String fileName = "students.ser";
        try{
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(file);
            
            Student student = (Student) in.readObject();
            a.add(student);
            in.close();
            file.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return a;
    }

    
}
