package dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
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
		} catch (HibernateException he) {
			manejaExcepcion(he);
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
		} catch (HibernateException he) {
			manejaExcepcion(he);
		} finally {
			session.close();
		}
		return objeto;
	}
	
	public Usuario traerPorUsername(String username) {
	    Usuario usuario = null;
	    try {
	        iniciaOperacion();
	        Query<Usuario> query = session.createQuery("from Usuario u where u.username = :username", Usuario.class);
	        query.setParameter("username", username);
	        usuario = query.uniqueResult();
	    } catch (HibernateException he) {
	        manejaExcepcion(he);
	    } finally {
	        session.close();
	    }
	    return usuario;
	}

	
	public List<Usuario> traerPorIntervaloDeNacimiento(LocalDate desde, LocalDate hasta){
		List<Usuario> lista = null;
		try {
			iniciaOperacion();
			Query<Usuario> query = session.createQuery("from Usuario u where u.fechaDeNacimiento < :hasta "
					+ "and u.fechaDeNacimiento >= :desde", Usuario.class);
			query.setParameter("hasta", hasta);
			query.setParameter("desde", desde);
			lista = query.getResultList();
		} catch (HibernateException he) {
			manejaExcepcion(he);
		} finally {
			session.close();
		}
		return lista;
	}
	
	public List<Usuario> traerPorNacimiento(LocalDate fechaDeNacimiento){
		List<Usuario> lista = null;
		try {
			iniciaOperacion();
			Query<Usuario> query = session.createQuery("from Usuario u where u.fechaDeNacimiento = :fecha ",
					Usuario.class);
			query.setParameter("fecha", fechaDeNacimiento);
			lista = query.getResultList();
		} catch (HibernateException he) {
			manejaExcepcion(he);
		} finally {
			session.close();
		}
		return lista;
	}

	public List<Usuario> traer() {
		List<Usuario> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from Usuario", Usuario.class).getResultList();
			
		} catch (HibernateException he) {
			manejaExcepcion(he); 
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
		} catch (HibernateException he) {
			manejaExcepcion(he);
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
		} catch (HibernateException he) {
			manejaExcepcion(he);
		} finally {
			session.close();
		}
		return lista;
	}
	
	public List<Usuario> traerPorNombre(String nombre){
		List<Usuario> lista = null;
		try {
			iniciaOperacion();
			Query<Usuario> query = session.createQuery("from Usuario u Where u.nombre = :nombre",
					Usuario.class);
			query.setParameter("nombre", nombre);
			lista = query.getResultList();
		} catch (HibernateException he) {
			manejaExcepcion(he);
		} finally {
			session.close();
		}
		return lista;
	}

	public List<Usuario> traerPorApellido(String apellido){
		List<Usuario> lista = null;
		try {
			iniciaOperacion();
			Query<Usuario> query = session.createQuery("from Usuario u Where u.apellido = :apellido",
					Usuario.class);
			query.setParameter("apellido", apellido);
			lista = query.getResultList();
		} catch (HibernateException he) {
			manejaExcepcion(he);
		} finally {
			session.close();
		}
		return lista;
	}

	/**
	 * Busca un usuario por su nombre de usuario y contraseña.
	 * Se utiliza para autenticación (login) del sistema.
	 * Retorna el usuario si las credenciales coinciden, o null si no hay coincidencia.
	 */
	public Usuario traerPorUsernameYPassword(String username, String password) {
	    Usuario objeto = null;
	    try {
	        iniciaOperacion();
	        Query<Usuario> query = session.createQuery(
	            "from Usuario u where u.username = :username and u.password = :password", Usuario.class);
	        query.setParameter("username", username);
	        query.setParameter("password", password);
	        objeto = query.uniqueResult();
	    } catch (HibernateException he) {
	        manejaExcepcion(he);
	    } finally {
	        session.close();
	    }
	    return objeto;
	}

	public Usuario traerPorEmail(String email) {
		Usuario usuario = null;
		
		try {
			iniciaOperacion();
			Query<Usuario> query = session.createQuery("Select u from Usuario u join u.contacto c where"
					+ " c.email = :email", Usuario.class);
			query.setParameter("email", email);
			usuario = query.uniqueResult();
		} catch(HibernateException he) {
			manejaExcepcion(he);
		} finally {
			session.close();
		}
		
		return usuario;
	}
	
}
