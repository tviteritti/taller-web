package ar.edu.unlam.tallerweb1.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLoginVeterinaria;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorLoginVeterinaria {
	
	/*private ServicioLoginVeterinaria servicio;

	
	@Autowired
	public ControladorLoginVeterinaria(ServicioLoginVeterinaria servicio) {
		
		this.servicio = servicio;	
	}
	*/
	
	private ServicioUsuario servicio;

	
	@Autowired
	public ControladorLoginVeterinaria(ServicioUsuario servicio) {
		
		this.servicio = servicio;	
	}
	
	@RequestMapping("/loginVeterinaria")
	public ModelAndView mostrarLoginVeterinaria() {
		return new ModelAndView("ingresoVeterinaria");
	}
	
	@RequestMapping("/cuentaUsuario")
	public ModelAndView mostrarCuentaUsuario() {
		return new ModelAndView("cuentaUsuario");
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
	
	@RequestMapping("/registrarVeterinario")
	public ModelAndView registrarVeterinario() {
			ModelMap modelo = new ModelMap();
			Usuario usuario = new Usuario();
			modelo.put("usuario", usuario);
		return new ModelAndView("registroVeterinario", modelo);
	}
	
	@RequestMapping("/registrarPaciente")
	public ModelAndView registrarPaciente() {
			ModelMap modelo = new ModelMap();
			Usuario usuario = new Usuario();
			modelo.put("usuario", usuario);
		return new ModelAndView("registroPaciente", modelo);
	}
	
	@RequestMapping(path="procesarDatosVeterinario", method= RequestMethod.POST)
	public ModelAndView validarDatosVeterinario(
			@ModelAttribute("usuario") Usuario user,
			@RequestParam(value="re-password",required=false) String repass
			
			) {
		
			ModelMap modelo = new ModelMap();
		
		if(servicio.validarPassRePass(user.getPassword(), repass)) { 
			servicio.registrarOMOdificarUsuario(user);
			modelo.put("usuario", user);
			modelo.put("mensaje", "registro exitoso");
			return new ModelAndView("redirect:/iniciarSesion");
		}else {
			modelo.put("error", "las password no coinciden");
		}
		
		
		return new ModelAndView("registroVeterinario", modelo);
	}
	
	@RequestMapping(path="procesarDatosPaciente", method= RequestMethod.POST)
	public ModelAndView validarDatosPaciente(
			
			@ModelAttribute("usuario") Usuario user,
			@RequestParam(value="re-password",required=false) String repass
			
			) {
		
		ModelMap modelo = new ModelMap();
		
		if(servicio.validarPassRePass(user.getPassword(), repass)) {
			modelo.put("usuario", user);
			modelo.put("mensaje", "registro exitoso");
		}
		
		return new ModelAndView("resultadoRegistro", modelo);
	}
	

	@RequestMapping(path="validarLoginUsuario", method= RequestMethod.POST)
	public ModelAndView validarDatosUsuario(
		
	@ModelAttribute("usuario") Usuario user, HttpServletRequest request ) {
		
		ModelMap modelo = new ModelMap();
		
		if(servicio.buscarUsuario(user.getUser(), user.getPassword())) {
			request.getSession().setAttribute("usuario", user.getUser());
			return new ModelAndView("redirect:/cuentaUsuario");
		}else {
			modelo.put("error", "Usuario o clave incorrecta");
		}
		
		return new ModelAndView("formUser", modelo);
		
	}
	
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView inicio() {
		return new ModelAndView("redirect:/loginVeterinaria");
	}

}
