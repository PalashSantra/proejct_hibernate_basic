package com.palash.proejct_hibernate_basic;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Management ME = new Management();
    	
        Department cse= new Department();
        cse.setDName("Computer Science");
        Department it = new Department();
        it.setDName("Information Technology");
        
        Laptop l1= new Laptop();
        l1.setName("HP");
        l1.setPrice(30000);
        
        Laptop l2= new Laptop();
        l2.setName("Dell");
        l2.setPrice(20000);
        
        Student s1= new Student();
        s1.setName("palash");
        s1.setMarks(70);
        s1.setDept(cse);
        s1.getLaptop().add(l1);
        
        Student s2= new Student();
        s2.setName("arnab");
        s2.setMarks(80);
        s2.setDept(cse);
        s2.getLaptop().add(l2);
        
        Student s3= new Student();
        s3.setName("badal");
        s3.setMarks(50);
        s3.setDept(it);
        s3.getLaptop().add(l1);
        s3.getLaptop().add(l2);
        
        
        cse.getStudent().add(s1);
        cse.getStudent().add(s2);
        it.getStudent().add(s3);
        
        l1.getStrudent().add(s1);
        l1.getStrudent().add(s3);
        l2.getStrudent().add(s2);
        l2.getStrudent().add(s3);
        
        //Save Department
        ME.addDepartment(cse);
        ME.addDepartment(it);
      //Save Laptop
        ME.addLaptop(l1);
        ME.addLaptop(l2);
      //Save Student
        ME.addStudent(s1);
        ME.addStudent(s2);
        ME.addStudent(s3);
        
        //Update Student
        Student stud = ME.getStudent(1);
        stud.setName("santra");
        stud.getDept().setDName("Computer Science & Engineering");
        Department ec= ME.getDepartment(2);
        stud.setDept(ec);
        
        //Delete Student
        //ME.deleteStudent(ME.getStudent(2));
        
        //Detach object
        //ME.detach(stud);
        //stud.setName("Durga");
        
        //Print Full Data
        stud= ME.getStudent(3);
        ME.printStudent(stud);
        //Print Student Info marks>70
        List<Student> students = ME.getStudentMarksGreaterEqual70();
        for (Student s : students) {
        	ME.printStudent(s);
        }
        //Print only Student Name and Roll No where marks>70
        List<Object[]> students_field = ME.getStudentMarksGreaterEqual70FieldOnly();
        if(students.size()>0) {
        	for(Object[] abc : students_field) {
        		System.out.println(abc[0]+" : "+abc[1]);
        		//System.out.println(ME.getStudent(Integer.parseInt(abc[0].toString())).getRollno()+" : "+ME.getStudent(Integer.parseInt(abc[0].toString())).getName());
        		
        	}
        }
        //Get All Student of IT Department
        students = ME.getAllStudentofDepartment("Information Technology");
        for (Student s : students) {
        	ME.printStudent(s);
        }
        //who carry dell laptop with 30000 price
        students = ME.getAllStudenCarryLaptopPrice(30000);
        for (Student s : students) {
        	ME.printStudent(s);
        }
        //Use of Second Level Cache
        System.out.println(ME.getStudent(3).getName());
        //System.out.println(ME.getStudent(1).getName());
        
    }
    
}
