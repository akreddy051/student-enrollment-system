import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static void sortName(ArrayList<Student> list){
        list.sort((obj1, obj2) ->obj1.getFirstName().compareTo(obj2.getFirstName()));
    }

    public static void sortRoll(ArrayList<Student> list){
        list.sort((obj1, obj2) ->obj1.getRollNo().compareTo(obj2.getRollNo()));
    }

    public static void main(String[] args) throws SQLException{
        Scanner input1 = new Scanner(System.in);
        boolean dontExitLoop=true;
        ArrayList<Student> students = new ArrayList<Student>();
        ArrayList<Course> courses = new ArrayList<Course>();
        
        while(dontExitLoop){
            System.out.println("Choose from the below options : ");
            menu();
            
            int choice = input1.nextInt();
            Scanner input = new Scanner(System.in);
            switch(choice){
                case 1:
                    
                    String firstName,lastName,dob;
                    System.out.println("Enter first name: ");
                    firstName = input.nextLine();
                    System.out.println();
                    System.out.println("Enter last name: ");
                    lastName = input.nextLine();
                    System.out.println("Enter date of birth: ");
                    dob = input.nextLine();
                    if(Utility.isValidDate(dob)){
                        Student student = new Student(firstName,lastName,dob);
                        students.add(student);
                    }
                    else{
                        System.out.println("Enter Date in DD/MM/YYYY format");
                    }
                    break;
                case 2:
                    int courseId;
                    int fee;
                    String name;
                    System.out.println("Enter course id: ");
                    courseId = input.nextInt();
                    System.out.println("Enter course name: ");
                    name = input.next();
                    System.out.println("Enter course fee: ");
                    fee = input.nextInt();
                    Course course = new Course(courseId, name, fee);
                    courses.add(course);
                    break;
                case 3:
                    System.out.println("Enter roll no to enroll");
                    int rollNo = input.nextInt();
                    for(Student i:students){
                        if(rollNo==i.getRollNo()){
                            System.out.println("Enter the course Id that you want to enroll into: ");
                            courseId= input.nextInt();
                            i.setCourseId(courseId);
                        }
                    }
                    break;
                case 4:
                    courseId=0;
                    System.out.println("Enter roll no of the student : ");
                    rollNo = input.nextInt();
                    for(Student i:students){
                        if(rollNo==i.getRollNo()){
                            courseId=i.getCourseId();
                        }
                    }
                    for(Course i: courses){
                        if(courseId==i.getCourseId()){
                            System.out.println("Fees : "+i.getFees());
                        }
                    }

                    break;

                case 5:
                    System.out.println("Select anyone: \n1. Sort by Student Name\n2.Sort by Student Roll Number.:");
                    int sortChoice = input.nextInt();
                    switch(sortChoice){
                        case 1:
                            sortName(students);
                            for(Student i:students){
                                i.details();
                            }
                            break;
                        case 2:
                            sortRoll(students);
                            for(Student i:students){
                                i.details();
                            }
                            break;
                        default:
                            System.out.println("invalid option");
                    }
                    break;
                case 6:
                    System.out.println("Select anyone : \n1.File System\n2.MySQL Database");
                    int storageChoice = input.nextInt();
                    switch(storageChoice){
                        case 1:
                            FileStorage fs = new FileStorage();
                            fs.serialize(students,courses);
                            break;
                        case 2:
                            DatabaseStorage ds = new DatabaseStorage();
                            ds.serialize(students,courses);
                            break;
                        default:
                            System.out.println("invalid option");
                    }
                    break;
                case 7:
                    try{
                        Connection con = MysqlConnection.getCon();
                        Statement stmt = con.createStatement();
                        
                        ResultSet rs = stmt.executeQuery("select rollNo,firstName,lastName,courseName,courseFees from student inner join course on student.courseId = course.courseId");
                        while(rs.next()){
                            System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getInt(5));
                        }
                        System.out.println();
                        rs = stmt.executeQuery("select * from course");
                        while(rs.next()){
                            System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3));
                        }
                        con.close();
                    }catch(Exception e){
                        System.out.println(e);
                    } 
                    break;  
                case 8:
                    System.out.println("Enter the name be searched : ");
                    String searchName = input.nextLine();
                    try{
                        Connection con = MysqlConnection.getCon();
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery("select * from student where firstName = '" + searchName +"'");
                        while(rs.next()){
                            System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getInt(5));
                        }
                    }catch(Exception e){
                        System.out.println(e);
                    }
                    break;
                case 9:
                    dontExitLoop=false;
                    input.close();
                    break;
                default:
                    System.out.println("invalid option");
            }


        }
        input1.close();
    }
    public static void menu(){
        System.out.println("\n\n1.Create New Student Data\n2.Create New Course Data\n3.Enroll Student to a Course.\n4.Display Fees of a Student.\n5.Sort Student Data.\n6.Persist Student Data\n7.Show All Student With Courses.\n8.Search Students\n9.Exit");
    }
}
