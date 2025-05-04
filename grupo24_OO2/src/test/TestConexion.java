package test;

import org.hibernate.Session;

import dao.HibernateUtil;

public class TestConexion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.close();
		System.out.println("Conexión OK!");
	}

}
	