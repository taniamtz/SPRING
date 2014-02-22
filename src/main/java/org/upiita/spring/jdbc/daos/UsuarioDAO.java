package org.upiita.spring.jdbc.daos;

import java.util.List;

import org.upiita.spring.jdbc.entidades.Usuario;

public interface UsuarioDAO {

	public Usuario buscaUsuarioPorId(Integer usuarioId);

	public void creaUsuario(Usuario usuario);
	
	//M�todo para realizar b�squedas por criterios
	
	public Usuario buscaPorEmailYPassword(String email, String password);
	
	public List<Usuario> buscaPorNombre(String nombre);

}
