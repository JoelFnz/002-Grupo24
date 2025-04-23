package dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import datos.Ticket;
import datos.UsuarioFinal;

public class UsuarioFinalDao {
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

    public long agregar(UsuarioFinal objeto) {
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

    public void actualizar(UsuarioFinal objeto) {
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

    public void eliminar(UsuarioFinal objeto) {
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

    public UsuarioFinal traer(long idUsuarioFinal) {
        UsuarioFinal objeto = null;
        try {
            iniciaOperacion();
            objeto = session.get(UsuarioFinal.class, idUsuarioFinal);
        } finally {
            session.close();
        }
        return objeto;
    }

    public List<UsuarioFinal> traer() {
        List<UsuarioFinal> lista = new ArrayList<>();
        try {
            iniciaOperacion();
            Query<UsuarioFinal> query = session.createQuery(
                "from UsuarioFinal u order by u.apellido asc, u.nombre asc",
                UsuarioFinal.class
            );
            lista = query.getResultList();
        } finally {
            session.close();
        }
        return lista;
    }

    public UsuarioFinal traerPorDni(int dni) {
        UsuarioFinal u = null;
        try {
            iniciaOperacion();
            Query<UsuarioFinal> query = session.createQuery(
                "from UsuarioFinal u where u.dni = :dni",
                UsuarioFinal.class
            );
            query.setParameter("dni", dni);
            u = query.uniqueResult();
        } finally {
            session.close();
        }
        return u;
    }

    public List<UsuarioFinal> traerActivos() {
        List<UsuarioFinal> lista = new ArrayList<>();
        try {
            iniciaOperacion();
            Query<UsuarioFinal> query = session.createQuery(
                "from UsuarioFinal u where u.baja = false",
                UsuarioFinal.class
            );
            lista = query.getResultList();
        } finally {
            session.close();
        }
        return lista;
    }
    
    public UsuarioFinal traerPorTicket(Ticket ticket) {
    	UsuarioFinal retorno = null;
        try {
            iniciaOperacion();
            Query<UsuarioFinal> query = session.createQuery(
                "select u from UsuarioFinal u join u.lstTickets t where t.idTicket = :ticket",
                UsuarioFinal.class
            );
            query.setParameter("ticket", ticket.getIdTicket());
            retorno = query.uniqueResult();
        } finally {
            session.close();
        }
        return retorno;
    }
}
