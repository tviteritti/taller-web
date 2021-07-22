package ar.edu.unlam.tallerweb1.controladores;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Consulta;
import ar.edu.unlam.tallerweb1.modelo.ContratacionPlanes;
import ar.edu.unlam.tallerweb1.modelo.Dias;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.Horarios;
import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Planes;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Zona;
import ar.edu.unlam.tallerweb1.servicios.ServicioConsulta;
import ar.edu.unlam.tallerweb1.servicios.ServicioDias;
import ar.edu.unlam.tallerweb1.servicios.ServicioHorarios;
import ar.edu.unlam.tallerweb1.servicios.ServicioMascotas;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificaciones;
import ar.edu.unlam.tallerweb1.servicios.ServicioPlanes;
import ar.edu.unlam.tallerweb1.servicios.ServicioTurno;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorLoginVeterinaria {
	
	private ServicioUsuario servicioUsuario;
	private ServicioHorarios servicioHorarios;
	private ServicioDias servicioDias;
	private ServicioTurno servicioTurno;
	private ServicioMascotas servicioMascota;
	private ServicioPlanes servicioPlanes;
	private ServicioNotificaciones servicioNotificaciones;
	private ServicioConsulta servicioConsulta;

	
	@Autowired
	public ControladorLoginVeterinaria(ServicioUsuario servicioVeterinario, 
										ServicioHorarios servicioHorarios,
										ServicioDias servicioDias, 
										ServicioTurno servicioTurno, 
										ServicioMascotas servicioMascota,
										ServicioPlanes servicioPlanes,
										ServicioNotificaciones servicioNotificaciones,
										ServicioConsulta servicioConsulta) {
		
		this.servicioUsuario = servicioVeterinario;	
		this.servicioHorarios = servicioHorarios;
		this.servicioDias = servicioDias;
		this.servicioTurno = servicioTurno;
		this.servicioMascota = servicioMascota;
		this.servicioPlanes = servicioPlanes;
		this.servicioNotificaciones = servicioNotificaciones;
		this.servicioConsulta = servicioConsulta;
	}
	
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView inicio() {
		return new ModelAndView("redirect:/loginVeterinaria");
	}
	
	@RequestMapping("/loginVeterinaria")
	public ModelAndView mostrarLoginVeterinaria(HttpServletRequest request) {
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		if(usuario != null) {
			if(usuario.getRol().equals("duenio")) {
				return new ModelAndView("redirect:/cuentaDuenio");
			}
			if(usuario.getRol().equals("veterinario")) {
				return new ModelAndView("redirect:/cuentaVeterinario");
			}
		}
		List<Turno> listaTurnos=servicioTurno.listarTurnosSinTomar();
		ModelMap modelo = new ModelMap();
		modelo.put("listaTurnos", listaTurnos);
		return new ModelAndView("ingresoVeterinaria", modelo);
	}
	
	/*----------------------------- INICIO DE SESION -----------------------------------------------*/
	
	@RequestMapping("/iniciarSesion")
	public ModelAndView iniciarSesion(HttpServletRequest request) {
		Usuario usuarioa = (Usuario) request.getSession().getAttribute("usuario");
		if(usuarioa != null) {
			if(usuarioa.getRol().equals("duenio")) {
				return new ModelAndView("redirect:/cuentaDuenio");
			}
			if(usuarioa.getRol().equals("veterinario")) {
				return new ModelAndView("redirect:/cuentaVeterinario");
			}
		}
		ModelMap modelo = new ModelMap();
		Usuario usuario = new Usuario();
		modelo.put("usuario", usuario);
		return new ModelAndView("formUser", modelo);
	}
	
	@RequestMapping(path="validarLoginUsuario", method= RequestMethod.POST)
	public ModelAndView validarDatosUsuario(
			@RequestParam(value="user",required=false) String user, 
			@RequestParam(value="password",required=false) String password, HttpServletRequest request ) {
		
		Usuario usuario = servicioUsuario.devolverUsuario(user, password);
		
		ModelMap modelo = new ModelMap();
		
		if(servicioUsuario.buscarUsuario(user, password)) {
			request.getSession().setAttribute("usuario", usuario);
			
			if(usuario.getRol().equals("duenio")) {
				
				return new ModelAndView("redirect:/cuentaDuenio");   
			}else{
				return new ModelAndView("redirect:/cuentaVeterinario");
			}
		}else {
			modelo.put("error", "Usuario o clave incorrecta");
		}
		return new ModelAndView("formUser", modelo);
	}
	
	@RequestMapping("/cuentaDuenio")
	public ModelAndView mostrarCuentaUsuario(HttpServletRequest request) {
		ModelMap modelo = new ModelMap();
		Usuario usuarioLogueado = (Usuario) request.getSession().getAttribute("usuario");
		if(usuarioLogueado==null) {
			return new ModelAndView("redirect:/loginVeterinaria");
		}
		if(usuarioLogueado.getRol().equals("duenio")) {
			
			List <Notificacion> misNotificaciones = servicioNotificaciones.listarNotificacionesPorUsuario(usuarioLogueado.getId());
			modelo.put("notificacion",misNotificaciones);
			Integer cantidadTotalNotificaciones = servicioNotificaciones.cantidadNotificaciones(usuarioLogueado.getId());
			modelo.put("cantidadNotificaciones", cantidadTotalNotificaciones);
			
			if(servicioPlanes.mostrarPlanesOContrataciones(usuarioLogueado)) {
				
				List<ContratacionPlanes> listaContrataciones=servicioPlanes.listarContrataciones();
				modelo.put("listaContrataciones", listaContrataciones);
				
				List <Turno> misTurnos = servicioTurno.buscarTurnoPorDuenio(usuarioLogueado.getId());
				modelo.put("turnos",misTurnos);

			}else {
				List<Planes> listaPlanes=servicioPlanes.listarPlanes();			
				modelo.put("listaPlanes", listaPlanes);
				
				List <Turno> misTurnos = servicioTurno.buscarTurnoPorDuenio(usuarioLogueado.getId());
				modelo.put("turnos",misTurnos);
			}
		}else if(usuarioLogueado.getRol().equals("veterinario")) {
			
			return new ModelAndView("redirect:/cuentaVeterinario");
		}
			
		return new ModelAndView("cuentaDuenio", modelo);
	}
	
	@RequestMapping("/cuentaVeterinario")
	public ModelAndView mostrarCuentaVeterinario(HttpServletRequest request) {
		
		Usuario veterinario =(Usuario)request.getSession().getAttribute("usuario");
		
		ModelMap modelo = new ModelMap();
		
		if(veterinario==null) {
			return new ModelAndView("redirect:/loginVeterinaria");
		}
		
		if(veterinario.getRol().equals("duenio")) {
			return new ModelAndView("redirect:/cuentaDuenio");
		}
		
			List <Notificacion> misNotificaciones = servicioNotificaciones.listarNotificacionesPorUsuario(veterinario.getId());
			modelo.put("notificacion",misNotificaciones);
			
			Integer cantidadTotalNotificaciones = servicioNotificaciones.cantidadNotificaciones(veterinario.getId());
			modelo.put("cantidadNotificaciones", cantidadTotalNotificaciones);
			
			List <Turno> misTurnos = servicioTurno.buscarTurnoTomadoPorVeterinario(veterinario.getId());
			modelo.put("turnos",misTurnos);
		

		return new ModelAndView("cuentaVeterinario",modelo);
	}
	
	@RequestMapping("/cerrarSesion")
	public ModelAndView cerrarSesion(HttpServletRequest request) {
		request.getSession().invalidate();
		return new ModelAndView("redirect:/loginVeterinaria");
	}

	/*---------------------------------------- REGISTRAR USUARIO ---------------------------------------------*/
	
	@RequestMapping("/registrarUsuario")
	public ModelAndView mostrarFormRegistroUsuario(HttpServletRequest request) {
		Usuario usuarioa = (Usuario) request.getSession().getAttribute("usuario");
		if(usuarioa != null) {
			if(usuarioa.getRol().equals("duenio")) {
				return new ModelAndView("redirect:/cuentaDuenio");
			}
			if(usuarioa.getRol().equals("veterinario")) {
				return new ModelAndView("redirect:/cuentaVeterinario");
			}
		}
		return new ModelAndView("registroUsuario");
	}
	
	/*---------------------------------------- REGISTRAR DUENIO ---------------------------------------------*/
	@RequestMapping("/registrarDuenio")
	public ModelAndView registrarDueño(HttpServletRequest request) {
		Usuario usuarioa = (Usuario) request.getSession().getAttribute("usuario");
		if(usuarioa != null) {
			if(usuarioa.getRol().equals("duenio")) {
				return new ModelAndView("redirect:/cuentaDuenio");
			}
			if(usuarioa.getRol().equals("veterinario")) {
				return new ModelAndView("redirect:/cuentaVeterinario");
			}
		}
		ModelMap modelo = new ModelMap();
		Usuario usuario = new Usuario();
		modelo.put("usuario", usuario);
		return new ModelAndView("registroDuenio", modelo);
	}
	
	@RequestMapping(path="procesarDatosDueño", method= RequestMethod.POST)
	public ModelAndView validarDatosPaciente(
			
			@ModelAttribute("usuario") Usuario user,
			@RequestParam(value="re-password",required=false) String repass
			
			) {
		
		ModelMap modelo = new ModelMap();
		
		if(servicioUsuario.validarPassRePass(user.getPassword(), repass)) { 
			servicioUsuario.registrarOMOdificarUsuario(user);
			modelo.put("usuario", user);
			modelo.put("mensaje", "registro exitoso");
			return new ModelAndView("redirect:/loginVeterinaria");
		}else {
			modelo.put("error", "las password no coinciden");
		}
		
		return new ModelAndView("registroDuenio", modelo);
	}
	
	/*-------------------------------------- REGISTRO VETERINARIO -----------------------------------------------------------*/
	
	@RequestMapping("/registrarVeterinario")
	public ModelAndView registrarVeterinario(HttpServletRequest request) {
		Usuario usuarioa = (Usuario) request.getSession().getAttribute("usuario");
		
		if(usuarioa != null) {
			if(usuarioa.getRol().equals("duenio")) {
				return new ModelAndView("redirect:/cuentaDuenio");
			}
			if(usuarioa.getRol().equals("veterinario")) {
				return new ModelAndView("redirect:/cuentaVeterinario");
			}
		}
			ModelMap modelo = new ModelMap();
			Usuario usuario = new Usuario();
			
			Especialidad especialidad1 = new Especialidad();
			especialidad1.setDescripcion("Cirugia");
			
			Especialidad especialidad2 = new Especialidad();
			especialidad2.setDescripcion("Laboratorio");
			
			Especialidad especialidad3 = new Especialidad();
			especialidad3.setDescripcion("Rayos X");
			
			Especialidad especialidad4 = new Especialidad();
			especialidad4.setDescripcion("Ecografias");
			
			Especialidad especialidad5 = new Especialidad();
			especialidad5.setDescripcion("Castraciones");
			
			Especialidad especialidad6 = new Especialidad();
			especialidad6.setDescripcion("Cardiologia");
			
			Especialidad especialidad7 = new Especialidad();
			especialidad7.setDescripcion("Planes Nutricionales");
			
			servicioUsuario.cargarEspecialidad(especialidad1);
			servicioUsuario.cargarEspecialidad(especialidad2);
			servicioUsuario.cargarEspecialidad(especialidad3);
			servicioUsuario.cargarEspecialidad(especialidad4);
			servicioUsuario.cargarEspecialidad(especialidad5);
			servicioUsuario.cargarEspecialidad(especialidad6);
			servicioUsuario.cargarEspecialidad(especialidad7);
			
			Zona zona1 = new Zona();
			zona1.setDescripcion("Norte");
			
			Zona zona2 = new Zona();
			zona2.setDescripcion("Sur");
			
			Zona zona3 = new Zona();
			zona3.setDescripcion("Oeste");
			
			servicioUsuario.cargarZona(zona1);
			servicioUsuario.cargarZona(zona2);
			servicioUsuario.cargarZona(zona3);
			
			List<Especialidad> listadoEspecialidad=servicioUsuario.getEspecialidades();
			List<Zona> listadoZonas=servicioUsuario.getZonas();
			modelo.put("listadoZonas", listadoZonas);
			modelo.put("listadoEspecialidad", listadoEspecialidad);
			modelo.put("usuario", usuario);

		return new ModelAndView("registroVeterinario", modelo);
	}	
	
	@RequestMapping(path="procesarDatosVeterinario", method= RequestMethod.POST)
	public ModelAndView validarDatosVeterinario(
			@ModelAttribute("usuario") Usuario user,HttpServletRequest request,
			@RequestParam(value="id_especialidad",required=false) Long id_especialidad,
			@RequestParam(value="re-password",required=false) String repass,
			@RequestParam(value="calle",required=false) String calle,
			@RequestParam(value="piso",required=false) String piso,
			@RequestParam(value="departamento",required=false) String departamento,
			@RequestParam(value="numero",required=false) String numero,
			@RequestParam(value="id_zona",required=false) Long id_zona) {
		
			ModelMap modelo = new ModelMap();
			Dias dias = new Dias();
			servicioDias.registrarOModificarDias(dias);
			
		if(servicioUsuario.validarPassRePass(user.getPassword(), repass)) { 
			servicioUsuario.registrarOMOdificarUsuario(user);
			servicioUsuario.ingresarEspecialidad(user.getId(), id_especialidad);
			servicioUsuario.ingresarDireccion(user.getId(), calle, piso, departamento, numero, id_zona);
			servicioDias.registrarOModificarDiasVeterinario(user, dias.getId());
			modelo.put("usuario", user);
			modelo.put("mensaje", "registro exitoso");
			request.getSession().setAttribute("id_dias", dias.getId());
			return new ModelAndView("redirect:/horariosLunes");
		}else {
			modelo.put("error", "las password no coinciden");
		}
		
		return new ModelAndView("registroVeterinario", modelo);
	}
	
	@RequestMapping("/horariosLunes")
	public ModelAndView registrarHorariosLunes(HttpServletRequest request) {
		Usuario usuarioa = (Usuario) request.getSession().getAttribute("usuario");
		if(usuarioa != null) {
			if(usuarioa.getRol().equals("duenio")) {
				return new ModelAndView("redirect:/cuentaDuenio");
			}
			if(usuarioa.getRol().equals("veterinario")) {
				return new ModelAndView("redirect:/cuentaVeterinario");
			}
		}
		return new ModelAndView("horariosLunes");
	}
	
	@RequestMapping(path="procesarDatosVeterinarioDiaLunes", method= RequestMethod.POST)
	public ModelAndView procesarHorariosLunes(
			@RequestParam(value="id",required=false) Long id,
			@RequestParam(value="id_dias",required=false) Long id_dias,
			@RequestParam(value="duracion_sesion",required=false) String duracion_sesion,
			@RequestParam(value="hora_fin",required=false) String hora_fin,
			@RequestParam(value="hora_inicio",required=false) String hora_inicio) throws ParseException {
		
			
			servicioDias.registrarOModificarDiasLunes(servicioHorarios.registrarOMOdificarHorarios(duracion_sesion, hora_fin, hora_inicio), id_dias);
			
		return new ModelAndView("redirect:/horariosMartes");
	}
	
	@RequestMapping("/horariosMartes")
	public ModelAndView registrarHorariosMartes(HttpServletRequest request) {
		Usuario usuarioa = (Usuario) request.getSession().getAttribute("usuario");
		if(usuarioa != null) {
			if(usuarioa.getRol().equals("duenio")) {
				return new ModelAndView("redirect:/cuentaDuenio");
			}
			if(usuarioa.getRol().equals("veterinario")) {
				return new ModelAndView("redirect:/cuentaVeterinario");
			}
		}
		return new ModelAndView("horariosMartes");
	}
	
	@RequestMapping(path="procesarDatosVeterinarioDiaMartes", method= RequestMethod.POST)
	public ModelAndView procesarHorariosMartes(
			@RequestParam(value="id",required=false) Long id,
			@RequestParam(value="id_dias",required=false) Long id_dias,
			@RequestParam(value="duracion_sesion",required=false) String duracion_sesion,
			@RequestParam(value="hora_fin",required=false) String hora_fin,
			@RequestParam(value="hora_inicio",required=false) String hora_inicio) throws ParseException {
		
			servicioDias.registrarOModificarDiasMartes(servicioHorarios.registrarOMOdificarHorarios(duracion_sesion, hora_fin, hora_inicio), id_dias);

		return new ModelAndView("redirect:/horariosMiercoles");
	}
	
	@RequestMapping("/horariosMiercoles")
	public ModelAndView registrarHorariosMiercoles(HttpServletRequest request) {
		Usuario usuarioa = (Usuario) request.getSession().getAttribute("usuario");
		if(usuarioa != null) {
			if(usuarioa.getRol().equals("duenio")) {
				return new ModelAndView("redirect:/cuentaDuenio");
			}
			if(usuarioa.getRol().equals("veterinario")) {
				return new ModelAndView("redirect:/cuentaVeterinario");
			}
		}
		return new ModelAndView("horariosMiercoles");
	}
	
	@RequestMapping(path="procesarDatosVeterinarioDiaMiercoles", method= RequestMethod.POST)
	public ModelAndView procesarHorariosMiercoles(
			@RequestParam(value="id",required=false) Long id,
			@RequestParam(value="id_dias",required=false) Long id_dias,
			@RequestParam(value="duracion_sesion",required=false) String duracion_sesion,
			@RequestParam(value="hora_fin",required=false) String hora_fin,
			@RequestParam(value="hora_inicio",required=false) String hora_inicio) throws ParseException {		

			servicioDias.registrarOModificarDiasMiercoles(servicioHorarios.registrarOMOdificarHorarios(duracion_sesion, hora_fin, hora_inicio), id_dias);
			
		return new ModelAndView("redirect:/horariosJueves");
	}
	
	@RequestMapping("/horariosJueves")
	public ModelAndView registrarHorariosJueves(HttpServletRequest request) {
		Usuario usuarioa = (Usuario) request.getSession().getAttribute("usuario");
		if(usuarioa != null) {
			if(usuarioa.getRol().equals("duenio")) {
				return new ModelAndView("redirect:/cuentaDuenio");
			}
			if(usuarioa.getRol().equals("veterinario")) {
				return new ModelAndView("redirect:/cuentaVeterinario");
			}
		}
		return new ModelAndView("horariosJueves");
	}
	
	@RequestMapping(path="procesarDatosVeterinarioDiaJueves", method= RequestMethod.POST)
	public ModelAndView procesarHorariosJueves(
			@RequestParam(value="id",required=false) Long id,
			@RequestParam(value="id_dias",required=false) Long id_dias,
			@RequestParam(value="duracion_sesion",required=false) String duracion_sesion,
			@RequestParam(value="hora_fin",required=false) String hora_fin,
			@RequestParam(value="hora_inicio",required=false) String hora_inicio) throws ParseException {
		
			servicioDias.registrarOModificarDiasJueves(servicioHorarios.registrarOMOdificarHorarios(duracion_sesion, hora_fin, hora_inicio), id_dias);
		
		return new ModelAndView("redirect:/horariosViernes");
	}
	
	@RequestMapping("/horariosViernes")
	public ModelAndView registrarHorariosViernes(HttpServletRequest request) {
		Usuario usuarioa = (Usuario) request.getSession().getAttribute("usuario");
		if(usuarioa != null) {
			if(usuarioa.getRol().equals("duenio")) {
				return new ModelAndView("redirect:/cuentaDuenio");
			}
			if(usuarioa.getRol().equals("veterinario")) {
				return new ModelAndView("redirect:/cuentaVeterinario");
			}
		}
		return new ModelAndView("horariosViernes");
	}
	
	@RequestMapping(path="procesarDatosVeterinarioDiaViernes", method= RequestMethod.POST)
	public ModelAndView procesarHorariosViernes(
			@RequestParam(value="id",required=false) Long id,
			@RequestParam(value="id_dias",required=false) Long id_dias,
			@RequestParam(value="duracion_sesion",required=false) String duracion_sesion,
			@RequestParam(value="hora_fin",required=false) String hora_fin,
			@RequestParam(value="hora_inicio",required=false) String hora_inicio) throws ParseException {

			servicioDias.registrarOModificarDiasViernes(servicioHorarios.registrarOMOdificarHorarios(duracion_sesion, hora_fin, hora_inicio), id_dias);

		return new ModelAndView("redirect:/horariosSabado");
	}
	
	@RequestMapping("/horariosSabado")
	public ModelAndView registrarHorariosSabado(HttpServletRequest request) {
		Usuario usuarioa = (Usuario) request.getSession().getAttribute("usuario");
		if(usuarioa != null) {
			if(usuarioa.getRol().equals("duenio")) {
				return new ModelAndView("redirect:/cuentaDuenio");
			}
			if(usuarioa.getRol().equals("veterinario")) {
				return new ModelAndView("redirect:/cuentaVeterinario");
			}
		}
		return new ModelAndView("horariosSabado");
	}
	
	@RequestMapping(path="procesarDatosVeterinarioDiaSabado", method= RequestMethod.POST)
	public ModelAndView procesarHorariosSabado(
			@RequestParam(value="id",required=false) Long id,
			@RequestParam(value="id_dias",required=false) Long id_dias,
			@RequestParam(value="duracion_sesion",required=false) String duracion_sesion,
			@RequestParam(value="hora_fin",required=false) String hora_fin,
			@RequestParam(value="hora_inicio",required=false) String hora_inicio) throws ParseException {

			servicioDias.registrarOModificarDiasSabado(servicioHorarios.registrarOMOdificarHorarios(duracion_sesion, hora_fin, hora_inicio), id_dias);

		return new ModelAndView("redirect:/horariosDomingo");
	}
	
	@RequestMapping("/horariosDomingo")
	public ModelAndView registrarHorariosDomingo(HttpServletRequest request) {
		Usuario usuarioa = (Usuario) request.getSession().getAttribute("usuario");
		if(usuarioa != null) {
			if(usuarioa.getRol().equals("duenio")) {
				return new ModelAndView("redirect:/cuentaDuenio");
			}
			if(usuarioa.getRol().equals("veterinario")) {
				return new ModelAndView("redirect:/cuentaVeterinario");
			}
		}
		return new ModelAndView("horariosDomingo");
	}
	
	@RequestMapping(path="procesarDatosVeterinarioDiaDomingo", method= RequestMethod.POST)
	public ModelAndView procesarHorariosDomingo(
			@RequestParam(value="id",required=false) Long id,
			@RequestParam(value="id_dias",required=false) Long id_dias,
			@RequestParam(value="duracion_sesion",required=false) String duracion_sesion,
			@RequestParam(value="hora_fin",required=false) String hora_fin,
			@RequestParam(value="hora_inicio",required=false) String hora_inicio) throws ParseException {
		
			servicioDias.registrarOModificarDiasDomingo(servicioHorarios.registrarOMOdificarHorarios(duracion_sesion, hora_fin, hora_inicio), id_dias);

		return new ModelAndView("redirect:/generarTurnos");
	}
	
	/*--------------------------------------------------------- TURNOS ------------------------------------------------------------------*/
	
	@RequestMapping("/generarTurnos")
	public ModelAndView generarTurnos(HttpServletRequest request) {
		Usuario usuarioa = (Usuario) request.getSession().getAttribute("usuario");
		if(usuarioa != null) {
			if(usuarioa.getRol().equals("duenio")) {
				return new ModelAndView("redirect:/cuentaDuenio");
			}
			if(usuarioa.getRol().equals("veterinario")) {
				return new ModelAndView("redirect:/cuentaVeterinario");
			}
		}
	return new ModelAndView("generarTurnos");
	}

	
	@RequestMapping(path="procesarDatosGenerarTurno", method= RequestMethod.POST)
	public ModelAndView procesarDatosGenerarTurno(
			@RequestParam(value="id",required=false) Long id,
			@RequestParam(value="id_dias",required=false) Long id_dias){
		
			servicioTurno.generarTurnoPorIdDiaLunes(id_dias);
			servicioTurno.generarTurnoPorIdDiaMartes(id_dias);
			servicioTurno.generarTurnoPorIdDiaMiercoles(id_dias);
			servicioTurno.generarTurnoPorIdDiaJueves(id_dias);
			servicioTurno.generarTurnoPorIdDiaViernes(id_dias);
			servicioTurno.generarTurnoPorIdDiaSabado(id_dias);
			servicioTurno.generarTurnoPorIdDiaDomingo(id_dias);

		return new ModelAndView("redirect:/loginVeterinaria");

	}
	
	@RequestMapping("/tomarUnTurno")
	public ModelAndView tomarUnTurno(@RequestParam("turnoId") Long id,HttpServletRequest request) {
		request.getSession().setAttribute("id_turno", id);
		Usuario usuarioa = (Usuario) request.getSession().getAttribute("usuario");
		if(usuarioa != null) {
			if(usuarioa.getRol().equals("duenio")) {
				return new ModelAndView("redirect:/cuentaDuenio");
			}
			if(usuarioa.getRol().equals("veterinario")) {
				return new ModelAndView("redirect:/cuentaVeterinario");
			}
		}
	return new ModelAndView("formVerificarSesion");
	}
	
	@RequestMapping(path="procesarDatosSesion", method= RequestMethod.POST)
	public ModelAndView procesarDatosSesion(
			@RequestParam(value="id_turno",required=false) Long id,
			@RequestParam(value="user",required=false) String user,
			@RequestParam(value="password",required=false) String password,
			HttpServletRequest request){
			ModelMap modelo = new ModelMap();
		
			if(servicioUsuario.buscarUsuario(user, password)) {
				Usuario duenio = servicioUsuario.devolverUsuario(user, password);		
				request.getSession().setAttribute("idUsuarioTurno", duenio);
				request.getSession().setAttribute("veterinarioTurno", servicioTurno.devolverVeterinarioDeunTurno(id));
				if(!servicioPlanes.verificarSiTienePlanVigente(duenio)) {
					String errorSinTurno = "No tiene plan vigente";
					request.getSession().setAttribute("errorSinPlan", errorSinTurno);
					return new ModelAndView("redirect:/mascotaAEligir");
				}
				Planes plan = servicioPlanes.devolverPlanDeDuenio(duenio);
				ContratacionPlanes cotratacion = servicioPlanes.devolverContratacionDeDuenio(duenio);
				request.getSession().setAttribute("idcotratacion", cotratacion.getId());
				if(cotratacion.getCantidadTurnosTomados()<plan.getCantidadTurnos()) {
					return new ModelAndView("redirect:/mascotaAEligir");
				}else {
					String error = "Excede el limite de turnos permitidos por el plan";
					request.getSession().setAttribute("errorExede", error);
					return new ModelAndView("redirect:/mascotaAEligir");
				}
				
			}else {
				modelo.put("error", "usuario no encontrado");
			}
		return new ModelAndView("formVerificarSesion",modelo);
	}
	
	@RequestMapping("/mascotaAEligir")
	public ModelAndView mascotaAEligir(HttpServletRequest request) {
		
		ModelMap modelo = new ModelMap();
		
		List<Mascota> listaDeMascotas = servicioMascota.listarMascotasPorDuenio((Usuario)request.getSession().getAttribute("idUsuarioTurno"));
		
		modelo.put("listaDeMascotas", listaDeMascotas);
		
	return new ModelAndView("listarMascotas", modelo);
	
	}
	
	@RequestMapping("/procesarMascota")
	public ModelAndView procesarMascota(
			@RequestParam(value="id_mascotas",required=false) Long id_mascota,
			@RequestParam(value="id_turno",required=false) Long id_turno, HttpServletRequest request){
		
		Usuario vet = (Usuario) request.getSession().getAttribute("veterinarioTurno");
		Turno turno = servicioTurno.obtenerTurno(id_turno);
		
		if(id_mascota!=null){
			
			servicioTurno.tomarTurno(id_turno, id_mascota);
			Notificacion notificacionTurno = new Notificacion();
			Mascota mascota = servicioMascota.obtenerMascota(id_mascota);
			notificacionTurno.setUsuario(vet);
			notificacionTurno.setEstado(true);
			String mensajeNotificacion = mascota.getDuenio().getUser()+" solicito turno";
			notificacionTurno.setMensaje(mensajeNotificacion);
			notificacionTurno.setTurno(turno);
			servicioNotificaciones.cargarNotificacion(notificacionTurno);
		}
		
		if(id_mascota==null) {
			return new ModelAndView("mascotaNull");
		}
		
		if(request.getSession().getAttribute("errorExede") != null) {
			servicioPlanes.aumentarValorExtra((Long)request.getSession().getAttribute("idcotratacion"),vet.getPrecioSesion());
		}else {
			if(request.getSession().getAttribute("errorSinPlan") != null) {
				servicioPlanes.aumentarValorExtraSinPLan((Usuario) request.getSession().getAttribute("idUsuarioTurno"),vet.getPrecioSesion());
			}else {
				servicioPlanes.aumentarTurnosTomados((Long)request.getSession().getAttribute("idcotratacion"));
			}
		}
		
	return new ModelAndView("redirect:/loginVeterinaria");
	}
	
	/*--------------------------------------------------------- PERFIL DEL USUARIO --------------------------------------------*/
	
	
	@RequestMapping("/verPerfil")
	public ModelAndView verPerfil(
			@RequestParam(value="idUsuario",required=false) Long idUsuario,
			HttpServletRequest request) {
		
			Usuario usuarioa = (Usuario) request.getSession().getAttribute("usuario");
			
			ModelMap modelo = new ModelMap();
			
			if(usuarioa == null) {
				
				return new ModelAndView("redirect:/loginVeterinaria");
			}
			if(usuarioa.getRol().equals("veterinario")) {
				
				Usuario veterinario = servicioUsuario.getVeterinario(usuarioa.getId());
				modelo.put("usuario", veterinario);
				
			}
			
			if(usuarioa.getRol().equals("duenio")) {
			
				Usuario duenio = servicioUsuario.getDuenio(usuarioa.getId());
				modelo.put("usuario", duenio);
				
			}

			
		return new ModelAndView("perfil", modelo);
	}
	
	@RequestMapping("/modificarPerfil")
	public ModelAndView modificarPerfil(
			@RequestParam(value="idUsuario",required=false) Long idUsuario,
			@RequestParam(value="nombre",required=false) String nombre,
			@RequestParam(value="apellido",required=false) String apellido,
			@RequestParam(value="telefono",required=false) String telefono,
			@RequestParam(value="idDireccion",required=false) Long idDireccion,
			@RequestParam(value="calle",required=false) String calle,
			@RequestParam(value="numero",required=false) String numero,
			@RequestParam(value="idLocalidad",required=false) Long idLocalidad,
			@RequestParam(value="localidad",required=false) String localidad,
			@RequestParam(value="codPostal",required=false)Integer codPostal,
			@RequestParam(value="email",required=false) String email,
			@RequestParam(value="descripcion",required=false) String descripcion,
			HttpServletRequest request) {
		
			Usuario usuarioa = (Usuario) request.getSession().getAttribute("usuario");
			
			ModelMap modelo = new ModelMap();
			
			if(usuarioa == null) {
				
				return new ModelAndView("redirect:/loginVeterinaria");
				
			}
			
	
				
				Usuario usuario = servicioUsuario.getVeterinario(usuarioa.getId());
				modelo.put("usuario", usuario);
				servicioUsuario.modificarPerfil(idUsuario, nombre, apellido, idDireccion,calle, numero, 
												idLocalidad,codPostal,localidad, telefono, email, descripcion);
	
		return new ModelAndView("redirect:/verPerfil", modelo);
	}
	
	
	/*-------------------------------------NOTIFICACIONES------------------------------------------*/
	
	@RequestMapping("/notificaciones")
	public ModelAndView irANotificaciones(
	  @RequestParam(value="id", required=true) Long id,
	  HttpServletRequest request){
		
		Usuario usuarioLogueado = (Usuario) request.getSession().getAttribute("usuario");
		
		ModelMap modelo = new ModelMap();
		Notificacion notificacion =servicioNotificaciones.obtenerNotificacion(id);
		List<Consulta> consultas = servicioConsulta.listarConsultas();
		
		modelo.put("notificacion", notificacion);
		modelo.put("consultas", consultas);
		modelo.put("usuario", usuarioLogueado.getRol());
	
		return new ModelAndView("notificaciones", modelo);
		
	}


	public ServicioUsuario getServicioUsuario() {
		return servicioUsuario;
	}


	public void setServicioUsuario(ServicioUsuario servicioUsuario) {
		this.servicioUsuario = servicioUsuario;
	}


	public ServicioHorarios getServicioHorarios() {
		return servicioHorarios;
	}


	public void setServicioHorarios(ServicioHorarios servicioHorarios) {
		this.servicioHorarios = servicioHorarios;
	}


	public ServicioDias getServicioDias() {
		return servicioDias;
	}


	public void setServicioDias(ServicioDias servicioDias) {
		this.servicioDias = servicioDias;
	}


	public ServicioTurno getServicioTurno() {
		return servicioTurno;
	}


	public void setServicioTurno(ServicioTurno servicioTurno) {
		this.servicioTurno = servicioTurno;
	}


	public ServicioMascotas getServicioMascota() {
		return servicioMascota;
	}


	public void setServicioMascota(ServicioMascotas servicioMascota) {
		this.servicioMascota = servicioMascota;
	}


	public ServicioPlanes getServicioPlanes() {
		return servicioPlanes;
	}


	public void setServicioPlanes(ServicioPlanes servicioPlanes) {
		this.servicioPlanes = servicioPlanes;
	}


	public ServicioNotificaciones getServicioNotificaciones() {
		return servicioNotificaciones;
	}


	public void setServicioNotificaciones(ServicioNotificaciones servicioNotificaciones) {
		this.servicioNotificaciones = servicioNotificaciones;
	}
	
	
	
}
