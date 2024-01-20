package com.webservices.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/query")
@Log4j2
public class QueryController {
	
	@Autowired
	private SessionFactory factory;
	@GetMapping
	public String nameQuery() {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		log.info(session.getNamedNativeQuery("findCustomerByLoan")
				.setParameter(1, "vivek pandey").getSingleResult());
		log.info(session.getNamedNativeQuery("findLoanByCustomerId")
				.setParameter(1, "vivek pandey").getSingleResult());
		log.info(session.getNamedNativeQuery("findDateByCustomerId")
				.setParameter(1, "vivek pandey").getSingleResult());
		log.info(session.getNamedQuery("findDetails").getResultList());
		log.info(session.getNamedNativeQuery("findByCompany")
				.setParameter(1, "company").getResultList());
		log.info(session.getNamedNativeQuery("findByAge").getResultList());
		log.info(session.getNamedQuery("findLoanStatus").getSingleResult());
		session.getTransaction().commit();
		session.close();
		return "done";
	}
}
