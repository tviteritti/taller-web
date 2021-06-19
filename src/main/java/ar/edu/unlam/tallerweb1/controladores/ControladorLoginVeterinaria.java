package ar.edu.unlam.tallerweb1.controladores;

import java.text.ParseException;
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

import ar.edu.unlam.tallerweb1.modelo.ContratacionPlanes;
import ar.edu.unlam.tallerweb1.modelo.Dias;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.Horarios;
import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.Planes;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioDias;
import ar.edu.unlam.tallerweb1.servicios.ServicioHorarios;
import ar.edu.unlam.tallerweb1.servicios.ServicioMascotas;
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

	
	@Autowired
	public ControladorLoginVeterinaria(ServicioUsuario servicioVeterinario, ServicioHorarios servicioHorarios, ServicioDias servicioDias, ServicioTurno servicioTurno, ServicioMascotas servicioMascota, ServicioPlanes servicioPlanes) {
		
		this.servicioUsuario = servicioVeterinario;	
		this.servicioHorarios = servicioHorarios;
		this.servicioDias = servicioDias;
		this.servicioTurno = servicioTurno;
		this.servicioMascota = servicioMascota;
		this.servicioPlanes = servicioPlanes;
	}
	
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView inicio() {
		return new ModelAndView("redirect:/loginVeterinaria");
	}
	
	@RequestMapping("/loginVeterinaria")
	public ModelAndView mostrarLoginVeterinaria() {
		List<Turno> listaTurnos=servicioTurno.listarTurnosSinTomar();
		ModelMap modelo = new ModelMap();
		modelo.put("listaTurnos", listaTurnos);
		return new ModelAndView("ingresoVeterinaria", modelo);
	}
	
	/*----------------------------- INICIO DE SESION -----------------------------------------------*/
	
	@RequestMapping("/iniciarSesion")
	public ModelAndView iniciarSesion() {
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
				
				return new ModelAndView("redirect:/cuentaDuenio");    /*no funciona*/
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
		Usuario duenio = (Usuario) request.getSession().getAttribute("usuario");
		if(servicioPlanes.mostrarPlanesOContrataciones(duenio)) {
			List<ContratacionPlanes> listaContrataciones=servicioPlanes.listarContrataciones();
			modelo.put("listaContrataciones", listaContrataciones);
		}else {
			List<Planes> listaPlanes=servicioPlanes.listarPlanes();			
			modelo.put("listaPlanes", listaPlanes);
		}
		
		return new ModelAndView("cuentaDuenio", modelo);
	}
	
	@RequestMapping("/cuentaVeterinario")
	public ModelAndView mostrarCuentaVeterinario() {
		return new ModelAndView("cuentaVeterinario");
	}
	
	@RequestMapping("/cerrarSesion")
	public ModelAndView cerrarSesion(HttpServletRequest request) {
		request.getSession().invalidate();
		return new ModelAndView("redirect:/loginVeterinaria");
	}

	/*---------------------------------------- REGISTRAR USUARIO ---------------------------------------------*/
	
	@RequestMapping("/registrarUsuario")
	public ModelAndView mostrarFormRegistroUsuario() {
		return new ModelAndView("registroUsuario");
	}
	
	/*---------------------------------------- REGISTRAR DUENIO ---------------------------------------------*/
	@RequestMapping("/registrarDuenio")
	public ModelAndView registrarDueño() {
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
	public ModelAndView registrarVeterinario() {
			ModelMap modelo = new ModelMap();
			Usuario usuario = new Usuario();
			List<Especialidad> listadoEspecialidad=servicioUsuario.getEspecialidades();
			modelo.put("listadoEspecialidad", listadoEspecialidad);
			modelo.put("usuario", usuario);

		return new ModelAndView("registroVeterinario", modelo);
	}
	
	
	@RequestMapping(path="procesarDatosVeterinario", method= RequestMethod.POST)
	public ModelAndView validarDatosVeterinario(
			@ModelAttribute("usuario") Usuario user,HttpServletRequest request,
			@RequestParam(value="id_especialidad",required=false) Long id_especialidad,
			@RequestParam(value="re-password",required=false) String repass) {
		
			ModelMap modelo = new ModelMap();
			Dias dias = new Dias();
			servicioDias.registrarOModificarDias(dias);
			
		if(servicioUsuario.validarPassRePass(user.getPassword(), repass)) { 
			servicioUsuario.registrarOMOdificarUsuario(user);
			servicioUsuario.ingresarEspecialidad(user.getId(), id_especialidad);
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
	public ModelAndView registrarHorariosLunes() {
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
	public ModelAndView registrarHorariosMartes() {
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
	public ModelAndView registrarHorariosMiercoles() {
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
	public ModelAndView registrarHorariosJueves() {
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
	public ModelAndView registrarHorariosViernes() {
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
	public ModelAndView registrarHorariosSabado() {
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
	public ModelAndView registrarHorariosDomingo() {
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
	public ModelAndView generarTurnos() {
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
				
				return new ModelAndView("redirect:/mascotaAEligir");
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
			@RequestParam("id_mascotas") Long id_mascota,
			@RequestParam(value="id_turno",required=false) Long id_turno) {
		
		servicioTurno.tomarTurno(id_turno, id_mascota);
		
	return new ModelAndView("redirect:/loginVeterinaria");
	}
}
