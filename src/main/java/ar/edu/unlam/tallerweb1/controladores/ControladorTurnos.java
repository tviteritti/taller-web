package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Calificacion;
import ar.edu.unlam.tallerweb1.modelo.ContratacionPlanes;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Planes;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Voto;
import ar.edu.unlam.tallerweb1.modelo.Zona;

import ar.edu.unlam.tallerweb1.servicios.ServicioMascotas;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificaciones;
import ar.edu.unlam.tallerweb1.servicios.ServicioPlanes;
import ar.edu.unlam.tallerweb1.servicios.ServicioTurno;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorTurnos {
	
private ServicioTurno servicioTurno;
private ServicioMascotas servicioMascotas;
private ServicioUsuario servicioUsuario;
private ServicioPlanes servicioPlanes;
private ServicioNotificaciones servicioNotificaciones;


	
	@Autowired
	public ControladorTurnos(ServicioTurno servicioTurno, 
							 ServicioMascotas servicioMascotas, 
							 ServicioUsuario servicioUsuario, 
							 ServicioPlanes servicioPlanes,
							 ServicioNotificaciones servicioNotificaciones) {
		
		this.servicioTurno = servicioTurno;	
		this.servicioMascotas=servicioMascotas;
		this.servicioUsuario =servicioUsuario;
		this.servicioPlanes =servicioPlanes;
		this.servicioNotificaciones = servicioNotificaciones;
	}
	
	@RequestMapping("buscarTurno")
	public ModelAndView buscarServicioVeterinario(
		@RequestParam(value="duenioId",required=false) Long duenioId) {
		ModelMap modelo = new ModelMap();
		
		Zona zona = new Zona();
		Usuario duenio = servicioUsuario.getUsuario(duenioId);
		List<Especialidad> listadoEspecialidad=servicioUsuario.getEspecialidades();
		List<Zona> listadoZona=servicioUsuario.getZonas();
		
		modelo.put("listadoZonas", listadoZona);
		modelo.put("listadoEspecialidad", listadoEspecialidad);
		modelo.put("zona", zona);
		modelo.put("duenio", duenio);
		modelo.put("duenioId", duenioId);
		
		return new ModelAndView("buscarTurno", modelo);
	}
	
	@RequestMapping(path="buscarServicioVeterinario", method={ RequestMethod.POST, RequestMethod.GET})
	public ModelAndView mostrarServicioVeterinario(
			@RequestParam(value="id_zona",required=false) Long id_zona,
			@RequestParam(value="id_especialidad",required=false) Long id_especialidad,
			@RequestParam(value="idDuenio",required=false) Long duenioId, HttpServletRequest request) {
		
		ModelMap modelo = new ModelMap();
		modelo.put("id_zona",id_zona);
		modelo.put("id_especialidad",id_especialidad);
		modelo.put("duenioId",duenioId);
		
		Usuario usuarioLogueado = (Usuario) request.getSession().getAttribute("usuario");
		
		modelo.put("servicio", servicioUsuario.getEspecialidad(id_especialidad).getDescripcion());
		modelo.put("zona",servicioUsuario.getZona(id_zona).getDescripcion());
		
		Usuario duenio = servicioUsuario.getDuenio(duenioId);
		modelo.put("duenio", duenio);
		
		List<Usuario>veterinariosEncontrados = servicioUsuario.buscarVeterinariosPorEspecialidadYZona(id_especialidad, id_zona);
		
		modelo.put("veterinarios", veterinariosEncontrados);
		
		List<Calificacion> calificacion = servicioUsuario.getCalificaciones();
		modelo.put("calificacion", calificacion);
		
		List<Turno>turnosVeterinario=new ArrayList<>();
		
		for(Usuario v : veterinariosEncontrados) {
			
			for(Turno turno :servicioTurno.obtenerTurnosPorVeterinario(v) ) {
				
				turnosVeterinario.add(turno);
				
			}
			
		}
		modelo.put("turnosPorVT", turnosVeterinario);
		
		
		List<Mascota> listaDeMascotas = servicioMascotas.listarMascotasPorDuenio(duenio);
		
		modelo.put("listaDeMascotas", listaDeMascotas);
		
		if(!servicioPlanes.verificarSiTienePlanVigente(duenio)) {
			String errorSinTurno = "No tiene plan vigente";
			request.getSession().setAttribute("errorSinPlan", errorSinTurno);
			return new ModelAndView("servicioVeterinario", modelo);
		}
		Planes plan = servicioPlanes.devolverPlanDeDuenio(duenio);
		ContratacionPlanes cotratacion = servicioPlanes.devolverContratacionDeDuenio(duenio);
		request.getSession().setAttribute("idcotratacion", cotratacion.getId());
		if(cotratacion.getCantidadTurnosTomados()<plan.getCantidadTurnos()) {
			return new ModelAndView("servicioVeterinario", modelo);
		}else {
			String error = "Excede el limite de turnos permitidos por el plan";
			request.getSession().setAttribute("errorExede", error);
			
		}
		
		
		
		return new ModelAndView("servicioVeterinario", modelo);
	}

	@RequestMapping(path="generarTurno", method= RequestMethod.POST)
	public ModelAndView crearTurno(
	@RequestParam(value="veterinarioId",required=false) Long veterinarioId,
	@RequestParam(value="servicio",required=false) String servicioSolicitado,
	@RequestParam(value="direccion",required=false) String direccion,
	@RequestParam(value="localidad",required=false) String localidad,
	@RequestParam(value="fecha",required=false) String dia,
	@RequestParam(value="hora",required=false) String hora,
	@RequestParam(value="idTurno",required=false) Long idTurno,
	@RequestParam(value="id_mascotas",required=false) Long id_mascotas,
	@RequestParam(value="idDuenio",required=false) Long duenioId,
	@RequestParam(value="notificacion",required=false) String notificacion, HttpServletRequest request
	) {
		
		ModelMap modelo = new ModelMap();
		
		Usuario usuarioLogueado = (Usuario) request.getSession().getAttribute("usuario");
		
		Usuario veterinario = servicioUsuario.getVeterinario(veterinarioId);
		
		modelo.put("veterinario", veterinario);
		
		Usuario duenio = servicioUsuario.getUsuario(duenioId);
		
		Mascota mascota = servicioMascotas.obtenerMascota(id_mascotas);
		
		
		modelo.put("especialidad", servicioSolicitado);
		modelo.put("localidad", localidad);
		modelo.put("direccion", direccion);
		modelo.put("fecha", servicioTurno.devolverFechaDeUnTurno(idTurno));
		modelo.put("hora", servicioTurno.devolverHorarioaDeUnTurno(idTurno));
		
		Turno turno = servicioTurno.obtenerTurno(idTurno);
		turno.setMascota(mascota);
		turno.setDuenio(usuarioLogueado);
		turno.setEstado(true);
		servicioTurno.cargarTurno(turno);
		servicioTurno.tomarTurno(turno.getId(), id_mascotas);
		
		modelo.put("duenio", duenio);
		modelo.put("mascota", mascota);
		
		if(notificacion!=null){
			
			Notificacion notificacionTurno = new Notificacion();
			
			notificacionTurno.setUsuario(veterinario);
			notificacionTurno.setEstado(true);
			String mensajeNotificacion = usuarioLogueado.getUser()+" solicito turno";
			notificacionTurno.setMensaje(mensajeNotificacion);
			notificacionTurno.setTurno(turno);
			servicioNotificaciones.cargarNotificacion(notificacionTurno);
			
		}
		
		
		if(request.getSession().getAttribute("errorExede") != null) {
			servicioPlanes.aumentarValorExtra((Long)request.getSession().getAttribute("idcotratacion"),veterinario.getPrecioSesion());
		}else {
			if(request.getSession().getAttribute("errorSinPlan") != null) {
				servicioPlanes.aumentarValorExtraSinPLan(duenio,veterinario.getPrecioSesion());
			}else {
				servicioPlanes.aumentarTurnosTomados((Long)request.getSession().getAttribute("idcotratacion"));
			}
		}
		
		return new ModelAndView("turnoSolicitado", modelo);
	}
	
	//cuenta Duenio
	
	@RequestMapping("misTurnos")
	public ModelAndView mostrarTurnos(
		@RequestParam(value="duenioId",required=false) Long duenioId) {
		ModelMap modelo = new ModelMap();
		
		Usuario duenio = servicioUsuario.getUsuario(duenioId);
		List<Turno> turnos = servicioTurno.buscarTurnoPorDuenio(duenioId);
		List<Voto> votos = servicioUsuario.getVotos(duenioId);
		
		List<Turno> conVoto = servicioTurno.getTurnosConVotosDuenio(duenioId);
		List<Turno> sinVoto = servicioTurno.getTurnosSinVotosDuenio(duenioId);
		
		modelo.put("conVoto", conVoto);
		modelo.put("sinVoto", sinVoto);
		modelo.put("votos", votos);
		modelo.put("turnos", turnos);
		modelo.put("duenio", duenio);
		return new ModelAndView("misTurnos", modelo);
	}
	
	//cuenta Veterinario
	
	@RequestMapping(path = "verTurnosPacientes")
	public ModelAndView mostrarTurnosPacientes(
	  @RequestParam(value="veterinarioId",required=false) Long veterinarioId,
	  HttpServletRequest request
			) {
		
		Usuario usuarioa = (Usuario) request.getSession().getAttribute("usuario");
		
		ModelMap modelo = new ModelMap();
		
		List<Turno> turnos = servicioTurno.buscarTurnoPorVeterinario(veterinarioId);
		List <Notificacion> misNotificaciones = servicioNotificaciones.listarNotificacionesPorUsuario(usuarioa.getId());
		Integer cantidadTotalNotificaciones = servicioNotificaciones.cantidadNotificaciones(usuarioa.getId());
		
		modelo.put("cantidadNotificaciones", cantidadTotalNotificaciones);
		modelo.put("notificacion",misNotificaciones);
		modelo.put("turnos", turnos);
		return new ModelAndView("turnosPacientes", modelo);
	}
	
	@RequestMapping(path = "contactarPaciente")
	public ModelAndView contactarPaciente(
	  @RequestParam(value="duenio",required=false) Long duenio
			) {
		
		ModelMap modelo = new ModelMap();
		Usuario duenioMascota = servicioUsuario.getDuenio(duenio);
		
		modelo.put("nombre", duenioMascota.getNombre());
		modelo.put("apellido", duenioMascota.getApellido());
		modelo.put("telefono", duenioMascota.getTelefono());
		modelo.put("email", duenioMascota.getEmail());
		modelo.put("direccion", duenioMascota.getDireccion());
		
		return new ModelAndView("datosContactoDuenio", modelo);
	}
	
	
	@RequestMapping(path="cancelarTurno", method= RequestMethod.POST)
	public ModelAndView mostrarTurnosSolicitados(
	@RequestParam(value="id_turno",required=false) Long idTurno	
			) {
		
		servicioTurno.cancelarTurno(idTurno);
		
		return new ModelAndView("misTurnos");
	}
	
	
	
	@RequestMapping("volverACuenta")
	public ModelAndView irACuentaUsuario() {
		
		return new ModelAndView("redirect:/cuentaDuenio");
	}
	
	@RequestMapping("formVerPerfilVerterinario")
	public ModelAndView formVerPerfilVerterinario( @RequestParam(value="veterinarioId",required=false) Long veterinarioId,
			 @RequestParam(value="id_zona",required=false) Long id_zona,
			 @RequestParam(value="id_especialidad",required=false) Long id_especialidad,
			 @RequestParam(value="duenioId",required=false) Long duenioId) {
		ModelMap modelo = new ModelMap();
		Usuario veterinario = servicioUsuario.getUsuario(veterinarioId);
		modelo.put("veterinario", veterinario);
		modelo.put("id_zona", id_zona);
		modelo.put("id_especialidad", id_especialidad);
		modelo.put("duenioId", duenioId);
		return new ModelAndView("perfilVeterinario", modelo);
	}
	
	@RequestMapping("volverCardVeterinario")
	public ModelAndView volverCardVeterinario(
			 @RequestParam(value="id_zona",required=false) Long id_zona,
			 @RequestParam(value="id_especialidad",required=false) Long id_especialidad,
			 @RequestParam(value="duenioId",required=false) Long duenioId) {
		ModelMap modelo = new ModelMap();
		modelo.put("id_zona", id_zona);
		modelo.put("id_especialidad", id_especialidad);
		modelo.put("duenioId", duenioId);
		return new ModelAndView("redirect:/buscarServicioVeterinario",modelo);
	}

}
