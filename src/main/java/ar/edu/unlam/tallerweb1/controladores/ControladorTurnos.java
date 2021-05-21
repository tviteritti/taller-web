package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorTurnos {
	
	@RequestMapping("buscarTurno")
	public ModelAndView buscarServicioVeterinario() {
		
		return new ModelAndView("buscarTurno");
	}

	@RequestMapping("buscarServicioVeterinario")
	public ModelAndView mostrarServicioVeterinario() {
		
		return new ModelAndView("servicioVeterinario");
	}
	
	@RequestMapping("generarTurno")
	public ModelAndView mostrarTurnoSolicitado() {
		
		ModelMap modelo = new ModelMap();
		
		modelo.put("veterinario", "Nombre Veterinario");
		modelo.put("especialidad", "Especialidad Veterinaro");
		modelo.put("localidad", "Localidad Veterinaria");
		modelo.put("direccion", "Direccion Veterinaria");
		modelo.put("fecha", "fecha seleccionada");
		modelo.put("hora", "horario seleccionado");
		
		return new ModelAndView("turnoSolicitado", modelo);
	}
	
	
	
	@RequestMapping("volverACuenta")
	public ModelAndView irACuentaUsuario() {
		
		return new ModelAndView("cuentaUsuario");
	}
	
	

}
