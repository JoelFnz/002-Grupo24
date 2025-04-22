package dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import datos.Contacto;

public class ContactoDao {
	private static Session session;
	private Transaction tx;

	private void iniciaOperacion() throws HibernateException {
		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
	}

	private void manejaExcepcion(HibernateException he) throws HibernateException {
		tx.rollback();
		throw new HibernateException("ERROR en la capa de acceso a datos", he);
	}

	public long agregar(Contacto objeto) {
		long id = 0;
		try {
			iniciaOperacion();
			id = (long) session.save(objeto);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
		} finally {
			session.close();
		}
		return id;
	}

	public void actualizar(Contacto objeto) {
		try {
			iniciaOperacion();
			session.update(objeto);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
		} finally {
			session.close();
		}
	}

	public void eliminar(Contacto objeto) {
		try {
			iniciaOperacion();
			session.delete(objeto);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
		} finally {
			session.close();
		}
	}

	public Contacto traer(long idContacto) {
		Contacto objeto = null;
		try {
			iniciaOperacion();
			objeto = session.get(Contacto.class, idContacto);
		} finally {
			session.close();
		}
		return objeto;
	}

	public List<Contacto> traer() {
		List<Contacto> lista = new ArrayList<Contacto>();
		try {
			iniciaOperacion();
			Query<Contacto> query = session.createQuery("from Contacto c order by c.email asc", Contacto.class);
			lista = query.getResultList();
		} finally {
			session.close();
		}
		return lista;
	}
}
