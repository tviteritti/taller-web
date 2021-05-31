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

import ar.edu.unlam.tallerweb1.modelo.Dias;
import ar.edu.unlam.tallerweb1.modelo.Horarios;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioDias;
import ar.edu.unlam.tallerweb1.servicios.ServicioHorarios;
import ar.edu.unlam.tallerweb1.servicios.ServicioLoginVeterinaria;
import ar.edu.unlam.tallerweb1.servicios.ServicioTurno;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorLoginVeterinaria {
	
	/*private ServicioLoginVeterinaria servicio;

	
	@Autowired
	public ControladorLoginVeterinaria(ServicioLoginVeterinaria servicio) {
		
		this.servicio = servicio;	
	}
	*/
	
	private ServicioUsuario servicioUsuario;
	private ServicioHorarios servicioHorarios;
	private ServicioDias servicioDias;
	private ServicioTurno servicioTurno;

	
	@Autowired
	public ControladorLoginVeterinaria(ServicioUsuario servicioVeterinario, ServicioHorarios servicioHorarios, ServicioDias servicioDias, ServicioTurno servicioTurno) {
		
		this.servicioUsuario = servicioVeterinario;	
		this.servicioHorarios = servicioHorarios;
		this.servicioDias = servicioDias;
		this.servicioTurno = servicioTurno;
	}
	
	@RequestMapping("/loginVeterinaria")
	public ModelAndView mostrarLoginVeterinaria() {
		List<Turno> listaTurnos=servicioTurno.listarTurnos();
		ModelMap modelo = new ModelMap();
		modelo.put("listaTurnos", listaTurnos);
		return new ModelAndView("ingresoVeterinaria", modelo);
	}
	
	@RequestMapping("/cuentaDuenio")
	public ModelAndView mostrarCuentaUsuario() {
		return new ModelAndView("cuentaDuenio");
	}
	@RequestMapping("/cuentaVeterinario")
	public ModelAndView mostrarCuentaVeterinario() {
		return new ModelAndView("cuentaVeterinario");
	}
	
	@RequestMapping("/iniciarSesion")
	public ModelAndView iniciarSesion() {
		ModelMap modelo = new ModelMap();
		Usuario usuario = new Usuario();
		modelo.put("usuario", usuario);
		return new ModelAndView("formUser", modelo);
	}
	

	@RequestMapping("/registrarUsuario")
	public ModelAndView mostrarFormRegistroUsuario() {
		return new ModelAndView("registroUsuario");
	}
	
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
			return new ModelAndView("redirect:/iniciarSesion");
		}else {
			modelo.put("error", "las password no coinciden");
		}
		
		return new ModelAndView("registroDuenio", modelo);
	}
	

	@RequestMapping(path="validarLoginUsuario", method= RequestMethod.POST)
	public ModelAndView validarDatosUsuario(
		
	@ModelAttribute("usuario") Usuario user, HttpServletRequest request ) {
		
		ModelMap modelo = new ModelMap();
		
		if(servicioUsuario.buscarUsuario(user.getUser(), user.getPassword())) {
			request.getSession().setAttribute("usuario", user.getUser());
			/*if(user.getRol()=="Duenio") {
				//return new ModelAndView("redirect:/cuentaDuenio");    /*no funciona*/
			//}else{
				return new ModelAndView("redirect:/cuentaVeterinario");
			
		}else {
			modelo.put("error", "Usuario o clave incorrecta");
		}
		
		return new ModelAndView("formUser", modelo);
		
	}
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView inicio() {
		return new ModelAndView("redirect:/loginVeterinaria");
	}
	
	
	/*-------------------------------------- REGISTRO VETERINARIO -----------------------------------------------------------*/
	
	@RequestMapping("/registrarVeterinario")
	public ModelAndView registrarVeterinario() {
			ModelMap modelo = new ModelMap();
			Usuario usuario = new Usuario();
			modelo.put("usuario", usuario);

		return new ModelAndView("registroVeterinario", modelo);
	}
	
	
	@RequestMapping(path="procesarDatosVeterinario", method= RequestMethod.POST)
	public ModelAndView validarDatosVeterinario(
			@ModelAttribute("usuario") Usuario user,HttpServletRequest request,
			
			@RequestParam(value="re-password",required=false) String repass
			
			) {
		
			ModelMap modelo = new ModelMap();
			Dias dias = new Dias();
			servicioDias.registrarOModificarDias(dias);
			
		if(servicioUsuario.validarPassRePass(user.getPassword(), repass)) { 
			servicioUsuario.registrarOMOdificarUsuario(user);
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
			ModelMap modelo = new ModelMap();
			Horarios horario = new Horarios();
			modelo.put("horario", horario);

		return new ModelAndView("horariosLunes", modelo);
	}
	
	@RequestMapping(path="procesarDatosVeterinarioDiaLunes", method= RequestMethod.POST)
	public ModelAndView procesarHorariosLunes(
			@RequestParam(value="id",required=false) Long id,
			@RequestParam(value="id_dias",required=false) Long id_dias,
			@RequestParam(value="duracion_sesion",required=false) String duracion_sesion,
			@RequestParam(value="hora_fin",required=false) String hora_fin,
			@RequestParam(value="hora_inicio",required=false) String hora_inicio) throws ParseException {
		
			
			servicioDias.registrarOModificarDiasLunes(servicioHorarios.registrarOMOdificarHorarios(duracion_sesion, hora_fin, hora_inicio), id_dias);
			ModelMap modelo = new ModelMap();
			
			
		if(1==1) {
			return new ModelAndView("redirect:/horariosMartes");
		}else {
			
		}
		return new ModelAndView("horariosLunes", modelo);
	}
	
	@RequestMapping("/horariosMartes")
	public ModelAndView registrarHorariosMartes() {
			ModelMap modelo = new ModelMap();
			Horarios horario = new Horarios();
			modelo.put("horario", horario);

		return new ModelAndView("horariosMartes", modelo);
	}
	
	@RequestMapping(path="procesarDatosVeterinarioDiaMartes", method= RequestMethod.POST)
	public ModelAndView procesarHorariosMartes(
			@RequestParam(value="id",required=false) Long id,
			@RequestParam(value="id_dias",required=false) Long id_dias,
			@RequestParam(value="duracion_sesion",required=false) String duracion_sesion,
			@RequestParam(value="hora_fin",required=false) String hora_fin,
			@RequestParam(value="hora_inicio",required=false) String hora_inicio) throws ParseException {
		
			servicioDias.registrarOModificarDiasMartes(servicioHorarios.registrarOMOdificarHorarios(duracion_sesion, hora_fin, hora_inicio), id_dias);
			ModelMap modelo = new ModelMap();
			
		
		if(1==1) {
			return new ModelAndView("redirect:/horariosMiercoles");
		}else {
				
		}		
		
		return new ModelAndView("horariosMartes", modelo);
	}
	
	@RequestMapping("/horariosMiercoles")
	public ModelAndView registrarHorariosMiercoles() {
			ModelMap modelo = new ModelMap();
			Horarios horario = new Horarios();
			modelo.put("horario", horario);

		return new ModelAndView("horariosMiercoles", modelo);
	}
	
	@RequestMapping(path="procesarDatosVeterinarioDiaMiercoles", method= RequestMethod.POST)
	public ModelAndView procesarHorariosMiercoles(
			@RequestParam(value="id",required=false) Long id,
			@RequestParam(value="id_dias",required=false) Long id_dias,
			@RequestParam(value="duracion_sesion",required=false) String duracion_sesion,
			@RequestParam(value="hora_fin",required=false) String hora_fin,
			@RequestParam(value="hora_inicio",required=false) String hora_inicio) throws ParseException {
		

			servicioDias.registrarOModificarDiasMiercoles(servicioHorarios.registrarOMOdificarHorarios(duracion_sesion, hora_fin, hora_inicio), id_dias);
			ModelMap modelo = new ModelMap();
		
			if(1==1) {
				return new ModelAndView("redirect:/horariosJueves");
			}else {
				
			}
		return new ModelAndView("horariosMiercoles", modelo);
	}
	
	@RequestMapping("/horariosJueves")
	public ModelAndView registrarHorariosJueves() {
			ModelMap modelo = new ModelMap();
			Horarios horario = new Horarios();
			modelo.put("horario", horario);

		return new ModelAndView("horariosJueves", modelo);
	}
	
	@RequestMapping(path="procesarDatosVeterinarioDiaJueves", method= RequestMethod.POST)
	public ModelAndView procesarHorariosJueves(
			@RequestParam(value="id",required=false) Long id,
			@RequestParam(value="id_dias",required=false) Long id_dias,
			@RequestParam(value="duracion_sesion",required=false) String duracion_sesion,
			@RequestParam(value="hora_fin",required=false) String hora_fin,
			@RequestParam(value="hora_inicio",required=false) String hora_inicio) throws ParseException {
		

			servicioDias.registrarOModificarDiasJueves(servicioHorarios.registrarOMOdificarHorarios(duracion_sesion, hora_fin, hora_inicio), id_dias);
			ModelMap modelo = new ModelMap();
		
			if(1==1) {
				return new ModelAndView("redirect:/horariosViernes");
			}else {
				
			}
		
		return new ModelAndView("horariosJueves", modelo);
	}
	
	@RequestMapping("/horariosViernes")
	public ModelAndView registrarHorariosViernes() {
			ModelMap modelo = new ModelMap();
			Horarios horario = new Horarios();
			modelo.put("horario", horario);

		return new ModelAndView("horariosViernes", modelo);
	}
	
	@RequestMapping(path="procesarDatosVeterinarioDiaViernes", method= RequestMethod.POST)
	public ModelAndView procesarHorariosViernes(
			@RequestParam(value="id",required=false) Long id,
			@RequestParam(value="id_dias",required=false) Long id_dias,
			@RequestParam(value="duracion_sesion",required=false) String duracion_sesion,
			@RequestParam(value="hora_fin",required=false) String hora_fin,
			@RequestParam(value="hora_inicio",required=false) String hora_inicio) throws ParseException {
		

			servicioDias.registrarOModificarDiasViernes(servicioHorarios.registrarOMOdificarHorarios(duracion_sesion, hora_fin, hora_inicio), id_dias);
			ModelMap modelo = new ModelMap();
			
			if(1==1) {
				return new ModelAndView("redirect:/horariosSabado");
			}else {
				
			}
		
		
		return new ModelAndView("horariosViernes", modelo);
	}
	
	@RequestMapping("/horariosSabado")
	public ModelAndView registrarHorariosSabado() {
			ModelMap modelo = new ModelMap();
			Horarios horario = new Horarios();
			modelo.put("horario", horario);

		return new ModelAndView("horariosSabado", modelo);
	}
	
	@RequestMapping(path="procesarDatosVeterinarioDiaSabado", method= RequestMethod.POST)
	public ModelAndView procesarHorariosSabado(
			@RequestParam(value="id",required=false) Long id,
			@RequestParam(value="id_dias",required=false) Long id_dias,
			@RequestParam(value="duracion_sesion",required=false) String duracion_sesion,
			@RequestParam(value="hora_fin",required=false) String hora_fin,
			@RequestParam(value="hora_inicio",required=false) String hora_inicio) throws ParseException {
		

			servicioDias.registrarOModificarDiasSabado(servicioHorarios.registrarOMOdificarHorarios(duracion_sesion, hora_fin, hora_inicio), id_dias);
			ModelMap modelo = new ModelMap();
		
			if(1==1) {
				return new ModelAndView("redirect:/horariosDomingo");
			}else {
				
			}
			
		return new ModelAndView("horariosSabado", modelo);
	}
	
	@RequestMapping("/horariosDomingo")
	public ModelAndView registrarHorariosDomingo() {
			ModelMap modelo = new ModelMap();
			Horarios horario = new Horarios();
			modelo.put("horario", horario);

		return new ModelAndView("horariosDomingo", modelo);
	}
	
	@RequestMapping(path="procesarDatosVeterinarioDiaDomingo", method= RequestMethod.POST)
	public ModelAndView procesarHorariosDomingo(
			@RequestParam(value="id",required=false) Long id,
			@RequestParam(value="id_dias",required=false) Long id_dias,
			@RequestParam(value="duracion_sesion",required=false) String duracion_sesion,
			@RequestParam(value="hora_fin",required=false) String hora_fin,
			@RequestParam(value="hora_inicio",required=false) String hora_inicio) throws ParseException {
		

			servicioDias.registrarOModificarDiasDomingo(servicioHorarios.registrarOMOdificarHorarios(duracion_sesion, hora_fin, hora_inicio), id_dias);
			ModelMap modelo = new ModelMap();
		
			if(1==1) {
				return new ModelAndView("redirect:/generarTurnos");
			}else {
				
			}
		return new ModelAndView("horariosDomingo", modelo);
	}
	
	/*--------------------------------------------------------- TURNOS ------------------------------------------------------------------*/
	
	@RequestMapping("/generarTurnos")
	public ModelAndView generarTurnos() {
		ModelMap modelo = new ModelMap();
		Horarios horario = new Horarios();
		modelo.put("horario", horario);

	return new ModelAndView("generarTurnos", modelo);
	}

	
	@RequestMapping(path="procesarDatosGenerarTurno", method= RequestMethod.POST)
	public ModelAndView procesarDatosGenerarTurno(
			@RequestParam(value="id",required=false) Long id,
			@RequestParam(value="id_dias",required=false) Long id_dias){
		
		servicioTurno.generarTurnoPorIdDia(id_dias);
			if(1==1) {
				return new ModelAndView("redirect:/loginVeterinaria");
			}else {
				
			}
		return new ModelAndView("procesarDatosGenerarTurno");
	}
	
	@RequestMapping("/tomarUnTurno")
	public ModelAndView tomarUnTurno(@RequestParam("turnoId") Long id,HttpServletRequest request) {
		ModelMap modelo = new ModelMap();
		Horarios horario = new Horarios();
		modelo.put("horario", horario);
		
		request.getSession().setAttribute("id_turno", id);
		
	return new ModelAndView("formVerificarSesion", modelo);
	}
	
	@RequestMapping(path="procesarDatosSesion", method= RequestMethod.POST)
	public ModelAndView procesarDatosSesion(
			@RequestParam(value="id_turno",required=false) Long id,
			@RequestParam(value="user",required=false) String user,
			@RequestParam(value="password",required=false) String password){
			ModelMap modelo = new ModelMap();
		
			if(servicioUsuario.buscarUsuario(user, password)) {
				Usuario duenio = servicioUsuario.devolverUsuario(user, password);
				servicioTurno.tomarTurno(id, duenio);
				return new ModelAndView("redirect:/iniciarSesion");
			}else {
				modelo.put("error", "usuario no encontrado");
			}
		return new ModelAndView("formVerificarSesion",modelo);
	}
	
}
