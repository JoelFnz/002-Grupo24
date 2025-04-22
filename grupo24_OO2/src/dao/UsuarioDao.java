package dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import datos.Usuario;

public class UsuarioDao {
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

	public long agregar(Usuario objeto) {
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

	public void actualizar(Usuario objeto) {
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

	public void eliminar(Usuario objeto) {
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

	public Usuario traer(long idUsuario) {
		Usuario objeto = null;
		try {
			iniciaOperacion();
			objeto = session.get(Usuario.class, idUsuario);
		} finally {
			session.close();
		}
		return objeto;
	}

	public Usuario traerPorDni(int dni) {
		Usuario objeto = null;
		try {
			iniciaOperacion();
			Query<Usuario> query = session.createQuery("from Usuario u where u.dni = :dni", Usuario.class);
			query.setParameter("dni", dni);
			objeto = query.uniqueResult();
		} finally {
			session.close();
		}
		return objeto;
	}

	public List<Usuario> traer() {
		List<Usuario> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from Usuario", Usuario.class).getResultList();
		} finally {
			session.close();
		}
		return lista;
	}
	
	public List<Usuario> traerActivos() {
		List<Usuario> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from Usuario u where u.baja = false", Usuario.class).getResultList();
		} finally {
			session.close();
		}
		return lista;
	}

	public List<Usuario> traerPorTipo(Class<? extends Usuario> tipoClase) {
		List<Usuario> lista = null;
		try {
			iniciaOperacion();
			String hql = "from " + tipoClase.getSimpleName() + " u where u.baja = false";
			Query<Usuario> query = session.createQuery(hql, Usuario.class);
			lista = query.getResultList();
		} finally {
			session.close();
		}
		return lista;
	}

}
