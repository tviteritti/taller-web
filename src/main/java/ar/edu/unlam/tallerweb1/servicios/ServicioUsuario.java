package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;


public interface ServicioUsuario {
	
	
	List<Usuario> getUsuarios();
	List<Usuario> getVeterinarios();
	Usuario getUsuario(Long id);
	Boolean buscarUsuario(String usuario, String password);
	Usuario devolverUsuario(String usuario, String password);
	
	
	void registrarOMOdificarUsuario (Usuario usuario);
	void eliminarUsuario(Long id);
	void ingresarEspecialidad(Long id, Long id_especialidad);
	
	Boolean validarPassRePass(String pass, String repass);
	
	List<Especialidad> getEspecialidades();

}
