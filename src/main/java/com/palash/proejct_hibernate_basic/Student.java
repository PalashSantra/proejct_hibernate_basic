package com.palash.proejct_hibernate_basic;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.CacheMode;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "student")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Student {
	@Id
	@Column(name = "Rollno")
	@GeneratedValue
	private int Rollno;
	private String Name;
	private long marks;
	@ManyToOne(fetch = FetchType.EAGER,optional=false)
	@JoinColumn(name = "Dept_No")
	private Department department;
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Laptop> laptop = new ArrayList<Laptop>();
	public Student() {
		// TODO Auto-generated constructor stub
	}
	
	public Student(int rollno, String name, long marks, Department dept, ArrayList<Laptop> laptop) {
		super();
		Rollno = rollno;
		Name = name;
		this.marks = marks;
		department = dept;
		this.laptop = laptop;
	}

	public int getRollno() {
		return Rollno;
	}
	public void setRollno(int rollno) {
		Rollno = rollno;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public long getMarks() {
		return marks;
	}
	public void setMarks(long marks) {
		this.marks = marks;
	}
	public Department getDept() {
		return department;
	}
	public void setDept(Department dept) {
		department = dept;
	}

	public List<Laptop> getLaptop() {
		return laptop;
	}

	public void setLaptop(List<Laptop> laptop) {
		this.laptop = laptop;
	}

	@Override
	public String toString() {
		return "Student [Rollno=" + Rollno + ", Name=" + Name + ", marks=" + marks + ", Dept=" + department + ", laptop="
				+ laptop + "]";
	}
	
	

}
