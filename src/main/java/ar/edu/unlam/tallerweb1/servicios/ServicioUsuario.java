package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Veterinario;

public interface ServicioUsuario {
	
	
	List<Usuario> getUsuarios();
	List<Usuario> getVeterinarios();
	void registrarOMOdificarUsuario (Usuario usuario);
	Usuario getUsuario(Long id);
	void eliminarUsuario(Long id);
	
	Boolean buscarUsuario(String usuario, String password);
	Boolean validarPassRePass(String pass, String repass);

}
