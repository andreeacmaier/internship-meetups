package com.arobs.internship.demointernship;

import com.arobs.internship.demointernship.entity.User;
import com.arobs.internship.demointernship.service.user.UserServiceImpl;
import com.arobs.internship.demointernship.utils.HibernateUtil;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DemoInternshipApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);


	public static void main(String[] args) {
        SpringApplication.run(DemoInternshipApplication.class, args);

       /* Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        String sql1 = "INSERT INTO proposal(user_id, title, description, type, difficulty," +
                "language, votes, duration_min, max_people) VALUES (1, 'titlee', 'desc', 'technic', 'easy'," +
                "'ro', 0, 60, 15)";
        String sql2 = "INSERT INTO vote(user_id, propsal_id) values (2,1)";
        String sql3 = "INSERT INTO vote(user_id, propsal_id) values (1,1)";

		*//*String sql = "select * from users";

		List<String> result = (List<String>) session.createNativeQuery(sql).getResultList();
		System.out.println(result);*//*

		List users = session.createQuery("FROM com.arobs.internship.demointernship.entity.User").list();
		System.out.println(users.get(1));
        *//*String result = (String) session.createNativeQuery(sql1).getSingleResult();*//*
		*//*LOGGER.info(result);
		result = (String) session.createNativeQuery(sql2).getSingleResult();
		LOGGER.info(result);
		result = (String) session.createNativeQuery(sql3).getSingleResult();
		LOGGER.info(result);*//*

		session.getTransaction().commit();
		session.close();

		HibernateUtil.shutdown();*/
    }

}
