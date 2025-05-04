package dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import datos.Estado;

public class EstadoDao {
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

	public long agregar(Estado objeto) {
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

	public void actualizar(Estado objeto) {
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

	public void eliminar(Estado objeto) {
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

	public Estado traer(long idEstado) {
		Estado objeto = null;
		try {
			iniciaOperacion();
			objeto = session.get(Estado.class, idEstado);
		} finally {
			session.close();
		}
		return objeto;
	}

	public List<Estado> traer() {
		List<Estado> lista = new ArrayList<>();
		try {
			iniciaOperacion();
			Query<Estado> query = session.createQuery("from Estado e order by e.ultimoCambioEstado desc", Estado.class);
			lista = query.getResultList();
		} finally {
			session.close();
		}
		return lista;
	}
	
	// CU "Cambiar estado de ticket"
	public void cambiarEstado(long idTicket, String tipoDeEstado, String descripcionDelEstado) {
		try {
			iniciaOperacion();
			Estado estado = session.get(Estado.class, idTicket);
			if (estado != null) {
				estado.setTipoDeEstado(tipoDeEstado);
				estado.setDescripcionDelEstado(descripcionDelEstado);
				estado.setUltimoCambioEstado(LocalDateTime.now());
				session.update(estado);
				tx.commit();
			} else {
				System.out.println("No se encontro un estado con idTicket: " + idTicket);
			}
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}
}
