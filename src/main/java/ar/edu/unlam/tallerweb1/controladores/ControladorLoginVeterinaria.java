package ar.edu.unlam.tallerweb1.controladores;

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

@Controller
public class ControladorLoginVeterinaria {
	
	private ServicioLoginVeterinaria servicio;
	
	@Autowired
	public ControladorLoginVeterinaria(ServicioLoginVeterinaria servicio) {
		
		this.servicio = servicio;	
	}
	
	
	@RequestMapping("/loginVeterinaria")
	public ModelAndView mostrarLoginVeterinaria() {
		return new ModelAndView("ingresoVeterinaria");
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
			modelo.put("usuario", user);
			modelo.put("mensaje", "registro exitoso");
		}
		
		
		return new ModelAndView("resultadoRegistro", modelo);
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
		
	@ModelAttribute("usuario") Usuario user ) {
		
		ModelMap modelo = new ModelMap();
		
		if(servicio.buscarUsuario(user.getUser(), user.getPassword())) {
			
			modelo.put("usuario", user.getUser());
		}
		
		return new ModelAndView("cuentaUsuario", modelo);
		
	}

}
