package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.servicios.ServicioLoginVeterinaria;
import ar.edu.unlam.tallerweb1.servicios.ServicioTurno;

@Controller
public class ControladorTurnos {
	
private ServicioTurno servicio;

	
	@Autowired
	public ControladorTurnos(ServicioTurno servicioTurno) {
		
		this.servicio = servicioTurno;	
	}
	
	@RequestMapping("buscarTurno")
	public ModelAndView buscarServicioVeterinario(
			@RequestParam(value="zona",required=false) String zona) {
		ModelMap modelo = new ModelMap();
		if(zona!=null)
		{
			modelo.put("localidades", servicio.obtenerLocalidades(zona));
			return new ModelAndView("buscarTurno" , modelo);
		}
		
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
