package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

// Interface que define los metodos del Repositorio de Usuarios.
public interface RepositorioUsuario {
	
	Usuario consultarUsuario (Usuario usuario);
	List<Usuario> getUsuarios();
	List<Usuario> getVeterinarios();
	Usuario getUsuario(Long id);
	List<Usuario> listarVeterinarios();
	
	void registrarOMOdificarUsuario (Usuario usuario);
	void eliminarUsuario(Long id);
	void ingresarEspecialidad(Long id, Long id_especialidad);
	
	List<Especialidad> getEspecialidades();
}
