package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;

@Controller
public class ControladorIndex {
	
	private ServicioLogin servicio;
	
	@Autowired
	public ControladorIndex(ServicioLogin servicio) {
		
		this.servicio = servicio;	
	}
	
	
	@RequestMapping("/index")
	public ModelAndView mostrarCuentaUsuario() {
		List<Usuario> listaUsuarios=servicio.getUsuarios();
		ModelMap modelo = new ModelMap();
		modelo.put("listaUsuarios", listaUsuarios);
		return new ModelAndView("index", modelo);
	}
}
