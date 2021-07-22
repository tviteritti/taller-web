package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Calificacion;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Voto;
import ar.edu.unlam.tallerweb1.modelo.Zona;

// Interface que define los metodos del Repositorio de Usuarios.
public interface RepositorioUsuario {
	
	Usuario consultarUsuario (Usuario usuario);
	List<Usuario> getUsuarios();
	List<Usuario> getVeterinarios();
	Usuario getUsuario(Long id);
	Usuario getDuenio(Long id);
	Usuario getVeterinario(Long id);
	List<Usuario> listarVeterinarios();
	
	void cargarZona(Zona zona);
	void cargarEspecialidad(Especialidad especialidad);
	
	void registrarOMOdificarUsuario (Usuario usuario);
	void eliminarUsuario(Long id);
	void ingresarEspecialidad(Long id, Long id_especialidad);
	void modificarPerfil(Long idUsuario, String nombre, String apellido, Long idDireccion, String calle, String nro,Long idLocalidad,
	Integer codPostal,String localidad, String telefono, String email,  String descripcion);
	
	List<Especialidad> getEspecialidades();
	List<Zona> getZonas();
	
	Especialidad getEspecialidad(Long id);
	Zona getZona(Long id);
	
	void ingresarDireccion(Long id, String calle, String piso, String departamento, String numero, Long id_zona);
	
	Calificacion devolverCalificarVeterinario(Long id_veterinario);
	void registrarCalificaion(Calificacion cal);
	List<Calificacion> getCalificaciones();
	
	void voto(Long id_vetrinario, Long id_duenio);
	List<Voto> getVotos(Long id_duenio);
	List<Voto> getVotos();
	Voto getVoto(Long id_veterinario);
}
