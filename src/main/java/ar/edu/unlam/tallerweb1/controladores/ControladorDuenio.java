package ar.edu.unlam.tallerweb1.controladores;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Consulta;
import ar.edu.unlam.tallerweb1.modelo.ContratacionPlanes;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.Planes;
import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioConsulta;
import ar.edu.unlam.tallerweb1.servicios.ServicioDias;
import ar.edu.unlam.tallerweb1.servicios.ServicioHorarios;
import ar.edu.unlam.tallerweb1.servicios.ServicioMascotas;
import ar.edu.unlam.tallerweb1.servicios.ServicioPlanes;
import ar.edu.unlam.tallerweb1.servicios.ServicioTurno;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorDuenio {
	
	private ServicioUsuario servicioDuenio;
	private ServicioMascotas servicioMascota;
	private ServicioPlanes servicioPlanes;
	private ServicioConsulta servicioConsulta;

	
	@Autowired
	public ControladorDuenio( ServicioUsuario servicioDuenio, ServicioMascotas servicioMascota,ServicioPlanes servicioPlanes,  ServicioConsulta servicioConsulta) {
		
		this.servicioDuenio = servicioDuenio;	
		this.servicioMascota = servicioMascota;
		this.servicioPlanes = servicioPlanes;
		this.servicioConsulta = servicioConsulta;
	}
	
	
	@RequestMapping("/mascota")
	public ModelAndView irAMiMascota(
	@RequestParam(value="duenioId",required=false) Long idDuenio, HttpServletRequest request) {
		Usuario usuarioa = (Usuario) request.getSession().getAttribute("usuario");
		if(usuarioa == null) {
			return new ModelAndView("redirect:/loginVeterinaria");
		}
		if(usuarioa.getRol().equals("veterinario")) {
			return new ModelAndView("redirect:/cuentaVeterinario");
		}
		ModelMap modelo = new ModelMap();
		Usuario duenio = servicioDuenio.getDuenio(idDuenio);
		modelo.put("duenio", duenio);
	 return new ModelAndView("mascota",modelo);
	}
	
	@RequestMapping("/turnos")
	public ModelAndView irAMiTurnos(
	@RequestParam(value="duenioId",required=false) Long idDuenio, HttpServletRequest request) {
		Usuario usuarioa = (Usuario) request.getSession().getAttribute("usuario");
		if(usuarioa == null) {
			return new ModelAndView("redirect:/loginVeterinaria");
		}
		if(usuarioa.getRol().equals("veterinario")) {
			return new ModelAndView("redirect:/cuentaVeterinario");
		}
		ModelMap modelo = new ModelMap();
		Usuario duenio = servicioDuenio.getDuenio(idDuenio);
		modelo.put("duenio", duenio);
	 return new ModelAndView("turnos",modelo);
	}
	
	@RequestMapping("/consultas")
	public ModelAndView irAConsultas(
	@RequestParam(value="duenioId",required=false) Long idDuenio, HttpServletRequest request) {
		Usuario usuarioa = (Usuario) request.getSession().getAttribute("usuario");
		if(usuarioa == null) {
			return new ModelAndView("redirect:/loginVeterinaria");
		}
		if(usuarioa.getRol().equals("veterinario")) {
			return new ModelAndView("redirect:/cuentaVeterinario");
		}
		ModelMap modelo = new ModelMap();
		Usuario duenio = servicioDuenio.getDuenio(idDuenio);
		List <Consulta> consultas = servicioConsulta.listarConsultaPorDuenio(idDuenio);
		
		modelo.put("duenio", duenio);
		modelo.put("consultas", consultas);
		
	 return new ModelAndView("consultas",modelo);
	}
	
	@RequestMapping("/miConsulta")
	public ModelAndView realizarConsulta(
	@RequestParam(value="duenioId",required=false) Long idDuenio,
	@RequestParam(value="asunto",required=false) String asunto,
	@RequestParam(value="consulta",required=false) String consulta,
	@RequestParam(value="comentario",required=false) String comentario,
	@RequestParam(value="idConsulta",required=false) Long idConsulta,
	HttpServletRequest request
	) {
		
		ModelMap modelo = new ModelMap();
		
		Usuario usuarioLogueado = (Usuario) request.getSession().getAttribute("usuario");
		
		Usuario duenio = servicioDuenio.getDuenio(idDuenio);
		
		Consulta miConsulta = new Consulta();
		miConsulta.setAsunto(asunto);
		miConsulta.setDescripcion(consulta);
		miConsulta.setDuenio(duenio);

		if(idConsulta!=null && usuarioLogueado!=null) {
			
			servicioConsulta.agregarComentario(idConsulta, comentario, usuarioLogueado.getUser());
		}


		servicioConsulta.cargarConsulta(miConsulta);
		List <Consulta> consultas = servicioConsulta.listarConsultaPorDuenio(idDuenio);
		List <Consulta> consultasDeTodosLosUsuarios = servicioConsulta.listarConsultas();
		
		modelo.put("duenio", duenio);
		modelo.put("consultas", consultas);
		modelo.put("todasLasConsultas", consultasDeTodosLosUsuarios);
		modelo.put("comentario", comentario);
		modelo.put("usuario", usuarioLogueado);
		
	 return new ModelAndView("miConsulta",modelo);
	 
	}
	
	
	@RequestMapping("/verMisConsultas")
	public ModelAndView verMisConsultas(
	@RequestParam(value="duenioId",required=false) Long idDuenio) {
		
		ModelMap modelo = new ModelMap();
		
		Usuario duenio = servicioDuenio.getDuenio(idDuenio);
		
		List <Consulta> consultas = servicioConsulta.listarConsultaPorDuenio(idDuenio);
		
		modelo.put("duenio", duenio);
		modelo.put("consultas", consultas);
		
	 return new ModelAndView("consultasDuenio",modelo);
	 
	}
	
	@RequestMapping("/planes")
	public ModelAndView irAPlanes(
	@RequestParam(value="duenioId",required=false) Long idDuenio,HttpServletRequest request) {
		Usuario usuarioa = (Usuario) request.getSession().getAttribute("usuario");
		if(usuarioa == null) {
			return new ModelAndView("redirect:/loginVeterinaria");
		}
		if(usuarioa.getRol().equals("veterinario")) {
			return new ModelAndView("redirect:/cuentaVeterinario");
		}
		ModelMap modelo = new ModelMap();
		
		Usuario duenio1 = (Usuario) request.getSession().getAttribute("usuario");
		if(!servicioPlanes.mostrarPlanesOContrataciones(duenio1)) {
			List<ContratacionPlanes> listaContrataciones=servicioPlanes.listarContrataciones();
			modelo.put("listaContrataciones", listaContrataciones);
		}else {
			List<ContratacionPlanes> listaContrataciones=servicioPlanes.listarContrataciones();
			modelo.put("listaContrataciones", listaContrataciones);
			List<Planes> listaPlanes=servicioPlanes.listarPlanes();			
			modelo.put("listaPlanes", listaPlanes);
		}
		Usuario duenio = servicioDuenio.getDuenio(idDuenio);
		modelo.put("duenio", duenio);
	 return new ModelAndView("planes",modelo);
	}
	
	
	@RequestMapping("/cargarMascota")
	public ModelAndView cargarMascota(HttpServletRequest request) {
		Usuario usuarioa = (Usuario) request.getSession().getAttribute("usuario");
		if(usuarioa == null) {
			return new ModelAndView("redirect:/loginVeterinaria");
		}
		if(usuarioa.getRol().equals("veterinario")) {
			return new ModelAndView("redirect:/cuentaVeterinario");
		}
			ModelMap modelo = new ModelMap();
			List<TipoAnimal> listadoTipos=servicioMascota.listarTipoAnimal();
			modelo.put("listadoTipos", listadoTipos);

		return new ModelAndView("FormCargarMascota", modelo);
	}
	
	@RequestMapping(path="procesarDatosMascota", method= RequestMethod.POST)
	public ModelAndView validarDatosPaciente(
			@RequestParam(value="id_tipo",required=false) Long id_tipo,
			@RequestParam(value="id_duenio",required=false) Long id_duenio,
			@RequestParam(value="fecha_nac",required=false) String fecha_nac,
			@RequestParam(value="nombre",required=false) String nombre) throws ParseException {
		
		
		servicioMascota.cargarMascota(id_tipo, id_duenio, fecha_nac, nombre);
		
		ModelMap modelo = new ModelMap();
	
		return new ModelAndView("cuentaDuenio", modelo);
	}
}
