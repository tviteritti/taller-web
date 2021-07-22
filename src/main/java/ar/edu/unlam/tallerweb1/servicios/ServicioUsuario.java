package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Calificacion;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Voto;
import ar.edu.unlam.tallerweb1.modelo.Zona;


public interface ServicioUsuario {
	
	
	List<Usuario> getUsuarios();
	List<Usuario> getVeterinarios();
	List<Usuario> buscarVeterinariosPorEspecialidadYZona(Long especialidad, Long zona);
	Usuario getUsuario(Long id);
	Usuario getDuenio(Long id);
	Usuario getVeterinario(Long id);
	Boolean buscarUsuario(String usuario, String password);
	Usuario devolverUsuario(String usuario, String password);
	
	void cargarZona(Zona zona);
	void cargarEspecialidad(Especialidad especialidad);
	
	
	void registrarOMOdificarUsuario (Usuario usuario);
	void eliminarUsuario(Long id);
	void ingresarEspecialidad(Long id, Long id_especialidad);
	void modificarPerfil(Long idUsuario, String nombre, String apellido, Long idDireccion, String calle, String nro,Long idLocalidad,
			Integer codPostal,String localidad, String telefono, String email,  String descripcion);
	
	Boolean validarPassRePass(String pass, String repass);
	
	List<Especialidad> getEspecialidades();
	List<Zona> getZonas();
	
	Especialidad getEspecialidad(Long id);
	Zona getZona(Long id);
	
	ModelAndView verificarSesion(Usuario usuario);
	void ingresarDireccion(Long id, String calle, String piso, String departamento, String numero, Long id_zona);
	
	void calificarVeterinario(Long id_veterinario, Double calificacion);
	Calificacion devolverCalificarVeterinario(Long id_veterinario);
	List<Calificacion> getCalificaciones();
	
	void voto(Long id_vetrinario, Long id_duenio);
	List<Voto> getVotos(Long id_duenio);
	List<Voto> getVotos();
	Voto getVoto(Long id_veterinario);
	


}
