package com.axelor.hiberJpqlDemo.app;  

import javax.persistence.*;

import com.axelor.hiberJpqlDemo.db.Employee;

import java.util.*;

public class App {
	public static void main(String[] args) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("myJpaUnit");
		EntityManager em = emfactory.createEntityManager();
		
		create(em);
		//update(em);
		find(em);
		//delete(em);
		ScalarandAggregate(em);
		BetweenAndLike(em);
		OrderBy(em);
		NamedQuery(em);
		em.close();
		emfactory.close();
		
	}
	
	private static void NamedQuery(EntityManager em) {
		Query query = em.createNamedQuery("find employee by id");
	      
	      query.setParameter("id", 102);
	      List<Employee> list = query.getResultList( );
	      
	      for( Employee e:list ){
	         System.out.print("Employee ID :" + e.getEid( ));
	         System.out.println("\t Employee Name :" + e.getEname( ));
	      }
	}

	private static void OrderBy(EntityManager em) {
	      //Between
	      Query query = em.createQuery( "Select e from Employee e ORDER BY e.ename DESC" );

	      List<Employee> list = (List<Employee>)query.getResultList( );

	      for( Employee e:list ) {
	         System.out.print("Employee ID :" + e.getEid( ));
	         System.out.println("\t Employee Name :" + e.getEname( ));
	      }

	}

	private static void BetweenAndLike(EntityManager em) {
		 //Between
	      Query query = em.createQuery( "Select e from Employee e where e.salary Between 10000 and 20000" );
	      
	      List<Employee> list=(List<Employee>)query.getResultList( );

	      for( Employee e:list ){
	         System.out.print("Employee ID :" + e.getEid( ));
	         System.out.println("\t Employee salary :" + e.getSalary( ));
	      }

	      //Like
	      Query query1 = em.createQuery("Select e from Employee e where e.ename LIKE 'R%'");
	      
	      List<Employee> list1=(List<Employee>)query1.getResultList( );
	      
	      for( Employee e:list1 ) {
	         System.out.print("Employee ID :"+e.getEid( ));
	         System.out.println("\t Employee name :"+e.getEname( ));
	      }

		
	}

	private static void ScalarandAggregate(EntityManager em) {
		
		//Scalar function
		 Query query = em.createQuery("Select LOWER(e.ename) from Employee e");
		List<String> list = query.getResultList();
		
		for(String e:list) {
	         System.out.println("Employee NAME :"+e);
		}
		 //Aggregate function
	      Query query1 = em.createQuery("Select MAX(e.salary) from Employee e");
	      Double result = (Double) query1.getSingleResult();
	      System.out.println("Max Employee Salary :" + result);
	}

	private static void create(EntityManager em) {
		System.out.println("Creating Student records");
		
		em.getTransaction().begin();

		Employee e1 = new Employee( ); 
		e1.setEid( 101 );
		e1.setEname( "Rajvi" );
		e1.setSalary( 10000 );
		e1.setDes( "Trainee" );
		
		 
		Employee e2 = new Employee( ); 
		e2.setEid( 102 );
		e2.setEname( "Payal" );
		e2.setSalary( 9000 );
		e2.setDes( "Trainee" );
		
		em.persist(e1); 
		em.persist(e2); 
	   
		
		em.getTransaction().commit();
		
		
	}
	private static void find(EntityManager em) {
		
	
		Employee e1 = em.find( Employee.class, 101 );

	      System.out.println("employee ID = " + e1.getEid( ));
	      System.out.println("employee NAME = " + e1.getEname( ));
	      System.out.println("employee SALARY = " + e1.getSalary( ));
	      System.out.println("employee DESIGNATION = " + e1.getDes( ));
			
		}
		
	
	private static void update(EntityManager em) {
		System.out.println("Updating record");
		
		em.getTransaction().begin();
		
		Employee e1 = em.find( Employee.class, 101 );
	      
	      //before update
	      System.out.println( e1 );
	      e1.setSalary( 15000 );
	      em.getTransaction( ).commit( );
	      
	      //after update
	      System.out.println( e1 );
		
	}
	private static void delete(EntityManager em) {
		em.getTransaction( ).begin( );
		Employee e1 = em.find( Employee.class, 101 );
	    em.remove( e1 );
		em.getTransaction().commit();
		
	}
	
}
