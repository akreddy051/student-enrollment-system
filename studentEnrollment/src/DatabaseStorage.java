import java.sql.*;
import java.util.ArrayList;

public class DatabaseStorage implements Idao{

    @Override
    public void serialize(ArrayList<Student> students, ArrayList<Course> courses) {
        try{
            Connection con = MysqlConnection.getCon();
            java.sql.Statement stmt = con.createStatement();
            
            String sql;

            sql="truncate table student";
            stmt.execute(sql);

            sql="truncate table course";
            stmt.execute(sql);

            for(Student i:students){
                sql="insert into student(rollNo,firstName,lastName,courseId,dob) values('"+i.getRollNo()+"','"+i.getFirstName()+"','"+i.getLastName()+"','"+i.getCourseId()+"','"+i.getDob()+"')";
                stmt.execute(sql);
            }
            for(Course i:courses){
                sql="insert into course(courseId,courseName,courseFees) values('"+i.getCourseId()+"','"+i.getCourseName()+"','"+i.getFees()+"')";
                stmt.execute(sql);
            }
            con.close();            
        }catch(Exception e){
            System.out.println(e);
        }
    }

    @Override
    public ResultSet deserialize() {
        try{
            Connection con = MysqlConnection.getCon();
            java.sql.Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("select * from student");
            while(rs.next()){
                System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getString(5));
            }
            System.out.println();
            con.close();
            return rs;
        }catch(Exception e){
            System.out.println(e);
        }

        return null;
    }
    
}
