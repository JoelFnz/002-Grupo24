package dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import datos.Empleado;
import datos.Ticket;

public class EmpleadoDao {
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

    public int agregar(Empleado objeto) {
        int id = 0;
        try {
            iniciaOperacion();
            id = Integer.parseInt(session.save(objeto).toString());
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            session.close();
        }
        return id;
    }

    public void actualizar(Empleado objeto) {
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

    public void eliminar(Empleado objeto) {
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

    public Empleado traer(long idEmpleado) {
        Empleado objeto = null;
        try {
            iniciaOperacion();
            objeto = (Empleado) session.get(Empleado.class, idEmpleado);
        } finally {
            session.close();
        }
        return objeto;
    }

    public Empleado traerPorDni(int dni) {
        Empleado empleado = null;
        try {
            iniciaOperacion();
            empleado = (Empleado) session.createQuery("from Empleado e where e.dni = :dni")
                    .setParameter("dni", dni)
                    .uniqueResult();
        } finally {
            session.close();
        }
        return empleado;
    }

    public List<Empleado> traer() {
        List<Empleado> lista = new ArrayList<>();
        try {
            iniciaOperacion();
            Query<Empleado> query = session.createQuery("from Empleado e order by e.apellido asc, e.nombre asc", Empleado.class);
            lista = query.getResultList();
        } finally {
            session.close();
        }
        return lista;
    }
    
    public List<Empleado> traerPorFechaDeIngreso(LocalDate fechaDeIngreso){
    	List<Empleado> lista = null;
    	try {
    		iniciaOperacion();
    		Query<Empleado> query = session.createQuery("from Empleado e where e.fechaDeIngreso = :fecha",
    				Empleado.class);
    		query.setParameter("fecha", fechaDeIngreso);
    		lista = query.getResultList();
    	} catch(HibernateException he) {
    		manejaExcepcion(he);
    	} finally {
    		session.close();
    	}
    	return lista;
    }
    
    public List<Empleado> traerPorIntervaloDeIngreso(LocalDate desde, LocalDate hasta){
    	List<Empleado> lista = null;
    	try {
    		iniciaOperacion();
    		Query<Empleado> query = session.createQuery("from Empleado e where e.fechaDeIngreso < :hasta and"
    				+ " e.fechaDeIngreso >= :desde", Empleado.class);
    		query.setParameter("hasta", hasta);
    		query.setParameter("desde", desde);
    		lista = query.getResultList();
    	} catch(HibernateException he) {
    		manejaExcepcion(he);
    	} finally {
    		session.close();
    	}
    	return lista;
    }
    
    public List<Empleado> traerPorTicketAsociado(Ticket ticketAsociado){
    	List<Empleado> lista = null;
    	try {
    		iniciaOperacion();
    		Query<Empleado> query = session.createQuery("select e from Empleado e "
    				+ "join e.ticketAsignado ta where ta.idTicket = :ticket", Empleado.class);
    		query.setParameter("ticket", ticketAsociado.getIdTicket());
    		lista = query.getResultList();
    	} catch(HibernateException he) {
    		manejaExcepcion(he);
    	} finally {
    		session.close();
    	}
    	return lista;
    }
    
    public List<Empleado> traerDesdeFechaDeIngreso(LocalDate desde){
    	List<Empleado> lista = null;
    	try {
    		iniciaOperacion();
    		Query<Empleado> query = session.createQuery("from Empleado e where e.fechaDeIngreso >= :desde", Empleado.class);
    		query.setParameter("desde", desde);
    		lista = query.getResultList();
    	} catch(HibernateException he) {
    		manejaExcepcion(he);
    	} finally {
    		session.close();
    	}
    	return lista;
    }
    
    public List<Empleado> traerhastaFechaDeIngreso(LocalDate hasta){
    	List<Empleado> lista = null;
    	try {
    		iniciaOperacion();
    		Query<Empleado> query = session.createQuery("from Empleado e where e.fechaDeIngreso < :hasta", Empleado.class);
    		query.setParameter("hasta", hasta);
    		lista = query.getResultList();
    	} catch(HibernateException he) {
    		manejaExcepcion(he);
    	} finally {
    		session.close();
    	}
    	return lista;
    }
}
