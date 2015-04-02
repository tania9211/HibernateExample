package org.school.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.school.hibernate.entity.Cabinet;
import org.school.hibernate.entity.Student;
import org.school.hibernate.entity.Subject;
import org.school.hibernate.entity.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

	public static void main(String[] args) {
		final Logger logger = LoggerFactory.getLogger(Main.class);
		Configuration configuration = new Configuration().configure();
		
		SessionFactory factory = configuration.buildSessionFactory();
		
		Session session = factory.openSession();
		
		Teacher teacher = new Teacher();
		teacher.setName("Garrison");
		session.save(teacher);
		
		Cabinet cabinet = new Cabinet();
		cabinet.setNumber(23);
		session.save(cabinet);
		
		Subject subject = new Subject();
		subject.setCabinet(cabinet);
		subject.setClassId(34);
		subject.setSubjectName("Painting");
		subject.getTeachers().add(teacher);
		teacher.getSubjects().add(subject);
		
		session.save(subject);
		
	//	session.save(teacher);
		
		session.beginTransaction().commit();
		
		Session session2 = factory.openSession();
		String hql = "select teach from Subject subj join subj.teachers teach";
		
		String hql1 = "select cab.number from Subject subj join subj.cabinet cab";
		
		
		
		Query query = session2.createQuery(hql);
		List <Teacher> results = query.list();
		
		for (int i = 0; i < results.size(); i++) {
			System.out.println(results.get(i).toString());
		}
	}
}
