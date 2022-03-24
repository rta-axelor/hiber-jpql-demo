package com.axelor.hiberJpqlDemo.db;

import javax.persistence.*;



@Entity

@NamedQuery(query = "Select e from Employee e where e.eid = :id", name = "find employee by id")
public class Employee{
	@Id
	
	int eid;
	String ename;
	double salary;
	String des;
	
	public Employee(int eid, String ename, double salary, String des) {
		 super( );
	      this.eid = eid;
	      this.ename = ename;
	      this.salary = salary;
	      this.des = des;
		
	}
	public Employee( ) {}

	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	 @Override
	public String toString() {
	      return "Employee [eid=" + eid + ", ename=" + ename + ", salary=" + salary + ", des=" + des + "]";
	}
}
