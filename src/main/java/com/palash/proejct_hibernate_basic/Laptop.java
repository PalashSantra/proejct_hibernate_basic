package com.palash.proejct_hibernate_basic;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "laptop")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Laptop {
	@Id
	@Column(name = "LId")
	@GeneratedValue
	private int LId;
	private String Name;
	private long Price;
	@ManyToMany(mappedBy = "laptop", targetEntity = Student.class)
	private List<Student> student = new ArrayList<Student>();
	public Laptop() {
		// TODO Auto-generated constructor stub
	}
	public Laptop(int lId, String name, long price, ArrayList<Student> strudent) {
		super();
		LId = lId;
		Name = name;
		Price = price;
		this.student = strudent;
	}
	public int getLId() {
		return LId;
	}
	public void setLId(int lId) {
		LId = lId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public long getPrice() {
		return Price;
	}
	public void setPrice(long price) {
		Price = price;
	}
	public List<Student> getStrudent() {
		return student;
	}
	public void setStrudent(List<Student> strudent) {
		this.student = strudent;
	}
	@Override
	public String toString() {
		return "Laptop [LId=" + LId + ", Name=" + Name + ", Price=" + Price + ", strudent=" + student + "]";
	}
	

}
