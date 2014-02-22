package org.upiita.spring.jdbc.tests;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.upiita.spring.jdbc.daos.UsuarioDAO;
import org.upiita.spring.jdbc.entidades.Usuario;

public class TestSpringHibernate {

	public static void main(String[] args) {
		//creamos el contexto de Spring
		ApplicationContext contexto = new ClassPathXmlApplicationContext("/contexto.xml");
		
		//Nos traemos el bean HibernateUsuarioDAO
		UsuarioDAO usuarioDAO =(UsuarioDAO) contexto.getBean("usuarioDAO");
		
		Usuario usuario = new Usuario();
		usuario.setUsuarioId(3);
		usuario.setNombre("Nombre");
		usuario.setEmail("nombre@gmail.com");
		usuario.setPassword("123");
		usuarioDAO.creaUsuario(usuario);
		
		usuario.setPassword("1234");
		usuarioDAO.creaUsuario(usuario);
		System.out.println("Datos Guardados" );
		
		Usuario usuarioBD = usuarioDAO.buscaUsuarioPorId(3);
		System.out.println("Usuario encontrado es: " + usuarioBD);
		
		//PROBANDO EL CRITERIO DE HIBERNATE
		Usuario usuarioCriterio= usuarioDAO.buscaPorEmailYPassword("nombre@gmail.com", "1234");
		System.out.println("Usuario encontrado con criterios " + usuarioCriterio);
		
		//PROBANDO EL CRITERIO LIKE
		List<Usuario> usuarios = usuarioDAO.buscaPorNombre("%z%");
		
		System.out.println("Usuarios por nombre: "+ usuarios);
	}

}
