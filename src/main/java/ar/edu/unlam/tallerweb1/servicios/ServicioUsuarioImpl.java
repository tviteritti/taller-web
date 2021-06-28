package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.modelo.Calificacion;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Voto;
import ar.edu.unlam.tallerweb1.modelo.Zona;



@Service("ServicioUsuario")
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuario {

	private RepositorioUsuario ServicioUsuarioDao;

	@Autowired
	public ServicioUsuarioImpl(RepositorioUsuario ServicioUsuarioDao){
		this.ServicioUsuarioDao = ServicioUsuarioDao;
	}

	
	@Override
	public List<Usuario> getUsuarios() {
		
		return ServicioUsuarioDao.getUsuarios();
	}
	
	@Override
	public List<Usuario> getVeterinarios() {
		return ServicioUsuarioDao.getVeterinarios();
	}

	@Override
	public void registrarOMOdificarUsuario(Usuario usuario) {
		ServicioUsuarioDao.registrarOMOdificarUsuario(usuario);
		
	}

	@Override
	public Usuario getUsuario(Long id) {
		return ServicioUsuarioDao.getUsuario(id);
	}

	@Override
	public void eliminarUsuario(Long id) {
		ServicioUsuarioDao.eliminarUsuario(id);
		
	}

	@Override
	public Boolean buscarUsuario(String usuario, String password) {
		List<Usuario> usuarios= ServicioUsuarioDao.getUsuarios();
	
		for (Usuario usuario2 : usuarios) {
			if(usuario2.getUser()!=null && usuario2.getPassword()!=null && usuario2.getUser().equals(usuario) && usuario2.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Usuario devolverUsuario(String usuario, String password) {
		
		List<Usuario> usuarios= ServicioUsuarioDao.getUsuarios();
		
		for (Usuario usuario2 : usuarios) {
			if(usuario2.getUser()!=null && usuario2.getPassword()!=null && usuario2.getUser().equals(usuario) && usuario2.getPassword().equals(password)) {
				return usuario2;
			}
		}
		return null;
	}

	@Override
	public Boolean validarPassRePass(String pass, String repass) {
		if(pass.equals(repass)) {
			return true;
		}
		return false;
	}

	@Override
	public List<Especialidad> getEspecialidades() {
		return ServicioUsuarioDao.getEspecialidades();
	}

	@Override
	public void ingresarEspecialidad(Long id, Long id_especialidad) {
		ServicioUsuarioDao.ingresarEspecialidad(id, id_especialidad);
		
	}

	@Override
	public List<Usuario> buscarVeterinariosPorEspecialidadYZona(Long especialidad, Long zona) {
		
		List<Usuario> veterinarios = ServicioUsuarioDao.listarVeterinarios();
		List<Usuario> veterinariosEncontrados = new ArrayList<>();
		Zona z = ServicioUsuarioDao.getZona(zona);
		Especialidad e = ServicioUsuarioDao.getEspecialidad(especialidad);
		 
		for(Usuario veterinario : veterinarios) {
			
			if(veterinario.getDireccion().getZona().getDescripcion().equals(z.getDescripcion())
			  && veterinario.getEspecialdad().getDescripcion().equals(e.getDescripcion())) {
				
				veterinariosEncontrados.add(veterinario);
				
			}
			
		}
		return veterinariosEncontrados;
	}

	@Override
	public Usuario getDuenio(Long id) {
		
		return ServicioUsuarioDao.getDuenio(id);
	}

	@Override
	public Usuario getVeterinario(Long id) {
		
		return ServicioUsuarioDao.getVeterinario(id);
	}

	@Override
	public ModelAndView verificarSesion(Usuario usuario) {
		if(usuario != null) {
			if(usuario.getRol().equals("duenio")) {
				return new ModelAndView("redirect:/cuentaDuenio");
			}
			if(usuario.getRol().equals("veterinario")) {
				return new ModelAndView("redirect:/cuentaVeterinario");
			}
		}
		return null;
		
	}

	@Override
	public List<Zona> getZonas() {
		return ServicioUsuarioDao.getZonas();
	}

	@Override
	public void ingresarDireccion(Long id, String calle, String piso, String departamento, String numero,
			Long id_zona) {
		if(calle==null) {
			calle="";
		}
		if(piso==null) {
			piso="";
		}
		if(departamento==null) {
			departamento="";
		}
		if(numero==null) {
			numero="";
		}
		
		ServicioUsuarioDao.ingresarDireccion(id, calle, piso, departamento, numero, id_zona);
	}

	@Override
	public Especialidad getEspecialidad(Long id) {
		return ServicioUsuarioDao.getEspecialidad(id);
	}

	@Override
	public Zona getZona(Long id) {
		return ServicioUsuarioDao.getZona(id);
	}


	@Override
	public void modificarPerfil(Long idUsuario, String nombre, String apellido, Long idDireccion, String calle, String nro,Long idLocalidad,
			Integer codPostal,String localidad, String telefono, String email,  String descripcion) {
		
		ServicioUsuarioDao.modificarPerfil(idUsuario, nombre, apellido, idDireccion, calle, nro, idLocalidad, codPostal, localidad, telefono, email, descripcion);
		
	}


	@Override
	public void calificarVeterinario(Long id_veterinario, Double calificacion) {
		
		Calificacion cal = ServicioUsuarioDao.devolverCalificarVeterinario(id_veterinario);
		Usuario vet = ServicioUsuarioDao.getVeterinario(id_veterinario);
		
		
		if(cal == null) {
			Calificacion nueva = new Calificacion();
			nueva.setCalificacion(calificacion);
			nueva.setCantidadDeVotos(1L);
			nueva.setVeterinario(vet);
			
			
			
			ServicioUsuarioDao.registrarCalificaion(nueva);
		}else {
			Double calificaionBD = cal.getCalificacion();
			Long cantidadBD = cal.getCantidadDeVotos();
			
			Double calificacionNueva = (calificaionBD * cantidadBD + calificacion) / (cantidadBD+1);
			cantidadBD ++;
			Long cantidadNueva = cantidadBD; 
			
			cal.setCalificacion(calificacionNueva);
			cal.setCantidadDeVotos(cantidadNueva);
			cal.setVeterinario(vet);
			
			
			
			ServicioUsuarioDao.registrarCalificaion(cal);
		}
		
	}


	@Override
	public Calificacion devolverCalificarVeterinario(Long id_veterinario) {
		return ServicioUsuarioDao.devolverCalificarVeterinario(id_veterinario);
	}


	@Override
	public void voto(Long id_vetrinario, Long id_duenio) {
		ServicioUsuarioDao.voto(id_vetrinario, id_duenio);
		
	}


	@Override
	public List<Voto> getVotos(Long id_duenio) {
		return ServicioUsuarioDao.getVotos(id_duenio);
	}


	@Override
	public Voto getVoto(Long id_veterinario) {
		return ServicioUsuarioDao.getVoto(id_veterinario);
	}


	@Override
	public List<Voto> getVotos() {
		return ServicioUsuarioDao.getVotos();
	}


	@Override
	public List<Calificacion> getCalificaciones() {
		return ServicioUsuarioDao.getCalificaciones();
	}


	
			
			





}
