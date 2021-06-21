package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
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
	
	
	void registrarOMOdificarUsuario (Usuario usuario);
	void eliminarUsuario(Long id);
	void ingresarEspecialidad(Long id, Long id_especialidad);
	
	Boolean validarPassRePass(String pass, String repass);
	
	List<Especialidad> getEspecialidades();
	List<Zona> getZonas();
	
	ModelAndView verificarSesion(Usuario usuario);
	void ingresarDireccion(Long id, String calle, String piso, String departamento, String numero, Long id_zona);

}
