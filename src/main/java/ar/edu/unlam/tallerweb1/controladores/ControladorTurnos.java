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

import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Veterinario;
import ar.edu.unlam.tallerweb1.modelo.Zona;
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
	public ModelAndView buscarServicioVeterinario() {
		ModelMap modelo = new ModelMap();
		
		Zona zona = new Zona();
		modelo.put("zona", zona);
		return new ModelAndView("buscarTurno", modelo);
	}

	@RequestMapping(path="buscarServicioVeterinario", method= RequestMethod.POST)
	public ModelAndView mostrarServicioVeterinario(
			
			@RequestParam(value="servicio",required=false) String servicioSolicitado,
			@ModelAttribute("zona") Zona zona) {
		
		ModelMap modelo = new ModelMap();
		modelo.put("servicio", servicioSolicitado);
		modelo.put("zona", zona);
		List<Veterinario>veterinariosEncontrados =servicio.obtenerVeterinariosPorZona(zona.getDescripcion());
		modelo.put("veterinarios", veterinariosEncontrados);
		Veterinario vt = new Veterinario ();
		modelo.put("vt", vt);
		return new ModelAndView("servicioVeterinario", modelo);
	}
	
	@RequestMapping(path="generarTurno", method= RequestMethod.POST)
	public ModelAndView mostrarTurnoSolicitado(
	@ModelAttribute("veterinario") Veterinario veterinario,
	@RequestParam(value="servicio",required=false) String servicioSolicitado,
	@RequestParam(value="direccion",required=false) String direccion,
	/*@RequestParam(value="localidad",required=false) String localidad,*/
	@RequestParam(value="fecha",required=false) String dia,
	@RequestParam(value="hora",required=false) String hora
	
			) {
		
		ModelMap modelo = new ModelMap();
		
		modelo.put("veterinarioNom", veterinario.getNombre());
		modelo.put("veterinarioAp", veterinario.getApellido());
		modelo.put("especialidad", servicioSolicitado);
		/*modelo.put("localidad", localidad);*/
		modelo.put("direccion", direccion);
		modelo.put("fecha", dia);
		modelo.put("hora", hora);
		
	
		return new ModelAndView("turnoSolicitado", modelo);
	}
	
	@RequestMapping("verTurnos")
	public ModelAndView mostrarTurnoSolicitado() {
		
		ModelMap modelo = new ModelMap();
		List<Turno> turnos = servicio.listarTurnos();
		modelo.put("turnos", turnos);
		return new ModelAndView("misTurnos", modelo);
	}
	
	@RequestMapping("cancelarTurno")
	public ModelAndView mostrarTurnoSolicitado(
	@RequestParam(value="fecha",required=false) Turno turno
			) {
		
		ModelMap modelo = new ModelMap();
		List<Turno> turnos = servicio.listarTurnos();
		modelo.put("turnos", turnos);
		if(servicio.cancelarTurno(turno)) {
			modelo.put("mensaje","Turno cancelado con exito!");	
		}
		
		return new ModelAndView("cancelarTurno", modelo);
	}
	
	
	
	@RequestMapping("volverACuenta")
	public ModelAndView irACuentaUsuario() {
		
		return new ModelAndView("cuentaUsuario");
	}
	
	

}
