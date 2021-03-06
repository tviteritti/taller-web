package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;

@Controller
public class ControladorIndex {
	
	private ServicioLogin servicioLogin;
	
	@Autowired
	public ControladorIndex(ServicioLogin servicioLogin) {
		
		this.servicioLogin = servicioLogin;	
	}
	
	
	@RequestMapping("/index")
	public ModelAndView mostrarCuentaUsuario() {
		List<Usuario> listaUsuarios=servicioLogin.getUsuarios();
		ModelMap modelo = new ModelMap();
		modelo.put("listaUsuarios", listaUsuarios);
		return new ModelAndView("index", modelo);
	}
	
	@RequestMapping("/formRegistrar")
	public ModelAndView mostrarFormRegistrar() {
		ModelMap modelo = new ModelMap();
		Usuario usuario = new Usuario();
		modelo.put("usuario", usuario);
		return new ModelAndView("formRegistrar", modelo);
	}
	
	@RequestMapping(path = "/insertarUsuario", method = RequestMethod.POST)
	public ModelAndView ingresarUsuario(@ModelAttribute("usuario") Usuario usuario) {
		ModelMap modelo = new ModelMap();
		if(usuario!=null) {
			servicioLogin.ingresarUsuario(usuario);
			return new ModelAndView("redirect:/index");
		}else {
			modelo.put("error", "registro vacio");
		}
		return new ModelAndView("formRegistro");
	}
	
	@RequestMapping("/formActualizar")
	public ModelAndView formActualizar(@RequestParam("usuarioId") Long id) {
		ModelMap modelo = new ModelMap();
		Usuario usuario = servicioLogin.getUsuario(id);
		modelo.put("usuario",usuario);
		return new ModelAndView("formRegistrar", modelo);
	}
	
	@RequestMapping("/eliminar")
	public ModelAndView eliminarUsuario(@RequestParam("usuarioId") Long id) {
		servicioLogin.eliminarUsuario(id);
		return new ModelAndView("redirect:/index");
		
	}
	
}
