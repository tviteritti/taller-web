package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

// Interface que define los metodos del Repositorio de Usuarios.
public interface RepositorioUsuario {
	
	Usuario consultarUsuario (Usuario usuario);
	List<Usuario> getUsuarios();
	void registrarOMOdificarUsuario (Usuario usuario);
	Usuario getUsuario(Long id);
	void eliminarUsuario(Long id);
	
	Boolean buscarUsuario(String usuario, String password);
	Boolean validarPassRePass(String pass, String repass);
}
