package com.med.firstapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.med.firstapp.dao.OrderDao;
import com.med.firstapp.dao.ProductDao;
import com.med.firstapp.model.Order;
import com.med.firstapp.model.Product;

@Controller
@RequestMapping("/test")
public class TestController {

	//http://stackoverflow.com/questions/4396284/does-spring-transactional-attribute-work-on-a-private-method
	//http://blog.jhades.org/how-does-spring-transactional-really-work/
	//http://blog.jhades.org/setup-and-gotchas-of-the-hibernate-second-level-and-query-caches/
	//http://www.baeldung.com/spring-requestmapping
	

	//@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private ProductDao productDao;
	
	
	//@Transactional
	@RequestMapping(value = "find1", method = RequestMethod.GET)
	public void find1(HttpServletResponse resp) throws IOException{
		
		System.out.println("------------- find 1 - 1 productDao.findById(1)");
		Product product = productDao.findById(1);
		
		System.out.println("product.getQuantityInStock before: " + product.getQuantityInStock());
		product.setQuantityInStock(100);
		System.out.println("product.getQuantityInStock after: " + product.getQuantityInStock());

		System.out.println("------------- find 1 - 2 productDao.findById(1)");
		product = productDao.findById(1);
		
		System.out.println("product.getQuantityInStock before: " + product.getQuantityInStock());
		product.setQuantityInStock(101);
		System.out.println("product.getQuantityInStock after: " + product.getQuantityInStock());

//		System.out.println("------------- find 1 orderDao.findById(24)");
//		Order order = orderDao.findById(24);
		
//		System.out.println("------------- find 1 orderDao.getOrdersByCustomerId(1)");
//		List<Order> list = orderDao.getOrdersByCustomerId(1);

		
		PrintWriter writer = resp.getWriter();
		writer.println("HaK test");
		
		
	}

	//7933
	
	//@Transactional
	@RequestMapping(value = "find2", method = RequestMethod.GET)
	public void find2(HttpServletResponse resp) throws IOException{
		
		System.out.println("------------- find 2 productDao.findById(1)");
		Product product = productDao.findById(1);
		
		System.out.println("------------- find 2 productDao.findById(1)");
		product = productDao.findById(1);
		
//		System.out.println("------------- find 2 orderDao.findById(24)");
//		Order order = orderDao.findById(24);
		
//		System.out.println("------------- find 2 orderDao.getOrdersByCustomerId(1)");
//		List<Order> list = orderDao.getOrdersByCustomerId(1);

		PrintWriter writer = resp.getWriter();
		writer.println("HaK test");
	}

	@Transactional
	@RequestMapping(value = "test", method = RequestMethod.GET)
	public void test(HttpServletRequest req, HttpServletResponse resp) throws IOException{
	
		PrintWriter writer = resp.getWriter();
		writer.println("HaK test");
		
		find1(resp);
		find2(resp);
		
		writer.println("HaK test END.");
		
		
		

	}

	@RequestMapping(value = "test2", method = RequestMethod.GET)
	public void test2(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		
		PrintWriter writer = resp.getWriter();
		writer.println("HaK test2");
		Session session;

		try {
		    session = sessionFactory.getCurrentSession();
		    System.out.println("----------------- tr 1 getCurrentSession");
		} catch (HibernateException e) {
		    session = sessionFactory.openSession();
		    System.out.println("----------------- tr 1 openSession");
		}
		System.out.println("session.hashCode " + session.hashCode());
		
		Transaction transaction = session.beginTransaction();

		//Order order = orderDao.findById(24);
		
		System.out.println("----------------- tr 1");
		Order order = session.get(Order.class, 24);
		writer.println("order.getNumber: " + order.getNumber());
		
		System.out.println("----------------- tr 1");
		Order order2 = session.get(Order.class, 24);
		writer.println("order.getNumber: " + order2.getNumber());

		transaction.commit();
		
		session.close();
		
		//-----------------------------
		
		try {
		    session = sessionFactory.getCurrentSession();
		    System.out.println("----------------- tr 2 getCurrentSession");
		} catch (HibernateException e) {
		    session = sessionFactory.openSession();
		    System.out.println("----------------- tr 2 openSession");
		}
		System.out.println("session.hashCode " + session.hashCode());
		
		transaction = session.beginTransaction();
		
		
		System.out.println("----------------- tr 2");
		order = session.get(Order.class, 24);
		writer.println("order.getNumber: " + order.getNumber());
		
		System.out.println("----------------- tr 2");
		order2 = session.get(Order.class, 24);
		writer.println("order.getNumber: " + order2.getNumber());

		transaction.commit();
		
		session.close();
		// Cache is used inside a transaction
		// Cache is used inside two transactions within a single session.
		
		
	}

	
}
