package com.palash.proejct_hibernate_basic;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "department")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Department {
	@Id
	@Column(name = "DNo")
	@GeneratedValue
	private int DNo;
	private String DName;
	@OneToMany(mappedBy = "department",targetEntity = Student.class)
	private List<Student> student = new ArrayList<Student>();
	public Department() {
		// TODO Auto-generated constructor stub
	}
	

	public Department(int dNo, String dName, ArrayList<Student> student) {
		super();
		DNo = dNo;
		DName = dName;
		this.student = student;
	}


	public int getDNo() {
		return DNo;
	}
	public void setDNo(int dNo) {
		DNo = dNo;
	}
	public String getDName() {
		return DName;
	}
	public void setDName(String dName) {
		DName = dName;
	}
	
	public List<Student> getStudent() {
		return student;
	}
	public void setStudent(List<Student> student) {
		this.student = student;
	}
	@Override
	public String toString() {
		return "Department [DNo=" + DNo + ", DName=" + DName + "]";
	}
	
	
}
