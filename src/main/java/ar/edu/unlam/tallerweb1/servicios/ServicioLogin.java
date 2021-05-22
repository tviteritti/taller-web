package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

// Interface que define los metodos del Servicio de Usuarios.
public interface ServicioLogin {

	Usuario consultarUsuario(Usuario usuario);
	Usuario registrarUsuario (Usuario usuario);
	List<Usuario> getUsuarios();
	void ingresarUsuario (Usuario usuario);
}
