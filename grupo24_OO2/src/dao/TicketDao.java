package dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import datos.Ticket;
import datos.UsuarioFinal;

public class TicketDao {
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

	public long agregar(Ticket objeto) {
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

	public void actualizar(Ticket objeto) {
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

	public void eliminar(Ticket objeto) {
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

	public Ticket traer(long idTicket) {
		Ticket objeto = null;
		try {
			iniciaOperacion();
			objeto = session.get(Ticket.class, idTicket);
		} finally {
			session.close();
		}
		return objeto;
	}

	public List<Ticket> traer() {
		List<Ticket> lista = new ArrayList<Ticket>();
		try {
			iniciaOperacion();
			Query<Ticket> query = session.createQuery("from Ticket t order by t.fechaDeCreacion desc", Ticket.class);
			lista = query.getResultList();
		} finally {
			session.close();
		}
		return lista;
	}
	
	public List<Ticket> traerDesde(UsuarioFinal usuarioFinal, LocalDateTime desdeCreacion){
		List<Ticket> lista = null;
		try {
			iniciaOperacion();
			Query<Ticket> query = session.createQuery("from Ticket t where usuarioFinal = :usuario and"
					+ " fechaDeCreacion >= :fecha", Ticket.class);
			query.setParameter("usuario", usuarioFinal.getIdUsuario());
			query.setParameter("fecha", desdeCreacion);
			lista = query.getResultList();
		} finally {
			session.close();
		}
		return lista;
	}
	
	public List<Ticket> traerHasta(UsuarioFinal usuarioFinal, LocalDateTime hastaCreacion){
		List<Ticket> lista = null;
		try {
			iniciaOperacion();
			Query<Ticket> query = session.createQuery("from Ticket t where usuarioFinal = :usuario and"
					+ " fechaDeCreacion < :fecha", Ticket.class);
			query.setParameter("usuario", usuarioFinal.getIdUsuario());
			query.setParameter("fecha", hastaCreacion);
			lista = query.getResultList();
		} finally {
			session.close();
		}
		return lista;
	}
	
	public List<Ticket> traerPorUsuarioFinal(UsuarioFinal usuarioFinal){
		
		List<Ticket> lista = null;
		try {
			iniciaOperacion();
			Query<Ticket> query = session.createQuery("from Ticket t where t.usuario = :usuario " , Ticket.class);
	        query.setParameter("usuario", usuarioFinal);
			lista = query.getResultList();
		} finally {
			session.close();
		}
		return lista;
		
	}
	
	
	
	
}
