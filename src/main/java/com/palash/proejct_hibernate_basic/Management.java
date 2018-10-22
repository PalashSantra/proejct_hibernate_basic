package com.palash.proejct_hibernate_basic;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


public class Management {

	private Configuration con;
	private ServiceRegistry reg;
	private SessionFactory sf;
	Session session;
	public Management() {
		con = new Configuration().configure().addAnnotatedClass(Student.class).addAnnotatedClass(Laptop.class).addAnnotatedClass(Department.class);
		reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		sf = con.buildSessionFactory(reg);
		session = sf.openSession();
	}
	public void addDepartment(Department dept) {
		Transaction tx=null;
		try {
			tx = session.beginTransaction();
			session.save(dept);
			tx.commit();
		}
		catch(Exception e) {
			if (tx!=null) 
				tx.rollback();
			e.printStackTrace();
		}
	}
	public void addStudent(Student stud) {
		Transaction tx=null;
		try {
			tx = session.beginTransaction();
			session.save(stud);
			tx.commit();
		}
		catch(Exception e) {
			if (tx!=null) 
				tx.rollback();
			e.printStackTrace();
		}
	}
	public void addLaptop(Laptop lap) {
		Transaction tx=null;
		try {
			tx = session.beginTransaction();
			session.save(lap);
			tx.commit();
		}
		catch(Exception e) {
			if (tx!=null) 
				tx.rollback();
			e.printStackTrace();
		}
	}

	public void deleteStudent(Student stud) {
		Transaction tx=null;
		try {
			tx = session.beginTransaction();
			session.delete(stud);
			tx.commit();
		}
		catch(Exception e) {
			if (tx!=null) 
				tx.rollback();
			e.printStackTrace();
		}
	}
	public void deleteLaptop(Laptop lap) {
		Transaction tx=null;
		try {
			tx = session.beginTransaction();
			session.delete(lap);
			tx.commit();
		}
		catch(Exception e) {
			if (tx!=null) 
				tx.rollback();
			e.printStackTrace();
		}
	}
	public void deleteDepatment(Department dept) {
		Transaction tx=null;
		try {
			tx = session.beginTransaction();
			session.delete(dept);
			tx.commit();
		}
		catch(Exception e) {
			if (tx!=null) 
				tx.rollback();
			e.printStackTrace();
		}
	}
	public Student getStudent(int Rollno) {
		Student stud=null;
		Transaction tx=null;
		try {
			tx = session.beginTransaction();
			stud=(Student)session.get(Student.class, Rollno);
			tx.commit();
		}
		catch(Exception e) {
			if (tx!=null) 
				tx.rollback();
			e.printStackTrace();
		}
		return stud;
	}
	public Department getDepartment(int DNo) {
		Department dept=null;
		Transaction tx=null;
		try {
			tx = session.beginTransaction();
			dept=(Department)session.get(Department.class, DNo);
			tx.commit();
		}
		catch(Exception e) {
			if (tx!=null) 
				tx.rollback();
			e.printStackTrace();
		}
		return dept;
	}
	public void detach(Object obj) {
		Transaction tx=null;
		try {
			tx = session.beginTransaction();
			session.evict(obj);
			tx.commit();
		}
		catch(Exception e) {
			if (tx!=null) 
				tx.rollback();
			e.printStackTrace();
		}
	}
	public List<Student> getStudentMarksGreaterEqual70(){
		String HQL= "from Student s where s.marks>=70";
		List<Student> students = new ArrayList<Student>();
		Transaction tx=null;
		try {
			tx = session.beginTransaction();
			Query q=session.createQuery(HQL);
			q.setCacheable(true);
			students= q.list();
			tx.commit();
		}
		catch(Exception e) {
			if (tx!=null) 
				tx.rollback();
			e.printStackTrace();
		}
		return students;
	}
	public List<Object[]> getStudentMarksGreaterEqual70FieldOnly(){
		long b=50;
		String HQL= "select s.Rollno,s.Name from Student s where s.marks>= :b";
		List<Object[]> students = new ArrayList<Object[]>();
		Transaction tx=null;
		try {
			tx = session.beginTransaction();
			Query q=session.createQuery(HQL);
			q.setParameter("b", b);
			q.setCacheable(true);
			students= (List<Object[]>) q.list();
			tx.commit();
		}
		catch(Exception e) {
			if (tx!=null) 
				tx.rollback();
			e.printStackTrace();
		}
		return students;
	}
	public List<Student> getAllStudentofDepartment(String dept_name){
		//String HQL= "from Student s where s.department.DName like :d";
		String HQL= "select s from Student s join s.department t where t.DName like :d";
		List<Student> students = new ArrayList<Student>();
		Transaction tx=null;
		try {
			tx = session.beginTransaction();
			Query q=session.createQuery(HQL);
			q.setParameter("d", "%"+dept_name+"%");
			q.setCacheable(true);
			students= q.list();
			tx.commit();
		}
		catch(Exception e) {
			if (tx!=null) 
				tx.rollback();
			e.printStackTrace();
		}
		return students;
	}
	public List<Student> getAllStudenCarryLaptopPrice(long price){
		String HQL= "select s from Student s join s.laptop t where t.Price = :d";
		List<Student> students = new ArrayList<Student>();
		Transaction tx=null;
		try {
			tx = session.beginTransaction();
			Query q=session.createQuery(HQL);
			q.setParameter("d", price);
			q.setCacheable(true);
			students= q.list();
			tx.commit();
		}
		catch(Exception e) {
			if (tx!=null) 
				tx.rollback();
			e.printStackTrace();
		}
		return students;
	}
	public void printStudent(Student stud) {
    	if(stud!=null) {
        	System.out.println("Name : "+stud.getName());
        	System.out.println("Marks : "+stud.getMarks());
        	System.out.println("Department : "+stud.getDept().getDName());
        	List<Laptop> laptop = stud.getLaptop();
        	if(laptop.size()>0) {
        		System.out.println("Laptop");
        		for(Laptop lap : laptop) {
        			System.out.println("Laptop Name : "+lap.getName());
        			System.out.println("Laptop Price : "+lap.getPrice());
        		}
            	
        	}
        }
    }
	@Override
	protected void finalize() throws Throwable {
		session.close();
	}

}
