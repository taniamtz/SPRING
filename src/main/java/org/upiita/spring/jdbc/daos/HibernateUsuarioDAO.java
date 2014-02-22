package org.upiita.spring.jdbc.daos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.upiita.spring.jdbc.entidades.Usuario;

//un nuevo comentario muy importante!!

@Component("usuarioDAO")
public class HibernateUsuarioDAO implements UsuarioDAO {

	//Para inyectar la fabrica de sesion de hibernate
	@Autowired
	private SessionFactory  sessionFactory;
	
	public Usuario buscaUsuarioPorId(Integer usuarioId) {
		Session sesion = sessionFactory.openSession();
		sesion.beginTransaction();
		//INICIA TRANSACCION 
		
		Usuario usuario = (Usuario) sesion.get(Usuario.class,usuarioId);
		
		
		//OBTENIENDO LA TRANSACCION ACTUAL Y GUARDADNDO TODOS LOS 
		//CAMBIOS EN LA BASE
		sesion.getTransaction().commit();
		
		// TERMINA TRANSACCION
		sesion.close();
		return usuario;
	}

	public void creaUsuario(Usuario usuario) {
		Session sesion = sessionFactory.openSession();
		sesion.beginTransaction();
		//INICIA TRANSACCION 
		
		//sesion.save(usuario);
		
		sesion.saveOrUpdate(usuario);
		//OBTENIENDO LA TRANSACCION ACTUAL Y GUARDADNDO TODOS LOS 
		//CAMBIOS EN LA BASE
		sesion.getTransaction().commit();
		
		// TERMINA TRANSACCION
		sesion.close();
	}

	public Usuario buscaPorEmailYPassword(String email, String password) {
		Session sesion =sessionFactory.openSession();
		sesion.beginTransaction();
		
		Criteria criterio = sesion.createCriteria(Usuario.class);
		
		/*
		 * criterio.add(Restrictions.or (Restrictions.eq("email", email),
				     Restrictions.eq("password", password)));
		criterio.add(Restrictions.and (Restrictions.eq("email", email),
					 Restrictions.eq("password", password)));
		
		*/
		criterio.add(Restrictions.eq("email", email));
		criterio.add(Restrictions.eq("password", password));

		//Obteniendo un solo resultado
		//Si no encuentra nada regresa null
		Usuario usuario = (Usuario) criterio.uniqueResult();
		
		sesion.getTransaction().commit();
		sesion.close();
		
		return usuario;
	}

	public List<Usuario> buscaPorNombre(String nombre) {
		Session sesion =sessionFactory.openSession();
		sesion.beginTransaction();
		
		Criteria criterio = sesion.createCriteria(Usuario.class);
		criterio.add(Restrictions.like("nombre",nombre));
		
		List<Usuario> usuarios = criterio.list();
		
		
		sesion.getTransaction().commit();
		sesion.close();
			
		return usuarios;
	}

}
