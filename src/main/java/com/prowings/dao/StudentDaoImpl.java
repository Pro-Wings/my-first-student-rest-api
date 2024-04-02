package com.prowings.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prowings.entity.Student;

@Repository
public class StudentDaoImpl implements StudentDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public boolean saveStudent(Student student) {
		System.out.println("inside StudentRepository :: saveStudent()");

		try {
			Session session = sessionFactory.openSession();
			Transaction txn = session.beginTransaction();
			session.save(student);
			txn.commit();
			session.close();
			System.out.println("student saved successfully!!");
			return true;
		}catch (Exception e) {
			System.out.println("error while saving the student to DB!!");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Student getStudentById(int id) {
		System.out.println("inside StudentRepository :: getStudentById()");
		
		Session session = null;
		Transaction txn = null;
		Student res = new Student();
		try {
			session = sessionFactory.openSession();
			txn = session.beginTransaction();
			res = session.load(Student.class, id);
			txn.commit();
		}catch (Exception e) {
			System.out.println("Error while fetching the student!!");
			e.printStackTrace();
			if(txn != null)
				txn.rollback();
		}
		return res;
		
	}

	@Override
	public List<Student> getAllStudents() {
		System.out.println("inside StudentRepository :: getAllStudents()");
		
		Session session = null;
		Transaction txn = null;
		List<Student> res = new ArrayList<>();
		try {
			session = sessionFactory.openSession();
			txn = session.beginTransaction();
			String hql = "FROM Student";
			Query query = session.createQuery(hql);
			res = query.getResultList();
			txn.commit();
		}catch (Exception e) {
			System.out.println("Error while fetching the student list!!");
			e.printStackTrace();
			if(txn != null)
				txn.rollback();
		}
		return res;
	}

	@Override
	public List<Student> findByCity(String address) {
		try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Student> criteriaQuery = builder.createQuery(Student.class);
            Root<Student> root = criteriaQuery.from(Student.class);
            criteriaQuery.select(root).where(builder.equal(root.get("address"), address));
            return session.createQuery(criteriaQuery).getResultList();
        }
	}

	@Override
	public List<Student> findAllSortedByField(String field) {
		 try (Session session = sessionFactory.openSession()) {
	            CriteriaBuilder builder = session.getCriteriaBuilder();
	            CriteriaQuery<Student> criteriaQuery = builder.createQuery(Student.class);
	            Root<Student> root = criteriaQuery.from(Student.class);
	            criteriaQuery.select(root).orderBy(builder.asc(root.get(field))); // Assuming 'field' is the name of the field by which you want to sort
	            return session.createQuery(criteriaQuery).getResultList();
	        }
	}
}
