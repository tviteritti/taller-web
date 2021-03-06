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

import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Veterinario;
import ar.edu.unlam.tallerweb1.modelo.Zona;
import ar.edu.unlam.tallerweb1.servicios.ServicioLoginVeterinaria;
import ar.edu.unlam.tallerweb1.servicios.ServicioMascotas;
import ar.edu.unlam.tallerweb1.servicios.ServicioTurno;

@Controller
public class ControladorTurnos {
	
private ServicioTurno servicioTurno;
private ServicioMascotas servicioMascotas;

	
	@Autowired
	public ControladorTurnos(ServicioTurno servicioTurno, ServicioMascotas servicioMascotas) {
		
		this.servicioTurno = servicioTurno;	
		this.servicioMascotas=servicioMascotas;
		
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
		List<Usuario>veterinariosEncontrados =servicioTurno.obtenerVeterinariosPorZona(zona.getDescripcion());
		modelo.put("veterinarios", veterinariosEncontrados);
		
		List<Turno>turnosVeterinario=null;
		
		for(Usuario v : veterinariosEncontrados) {
			
			turnosVeterinario=servicioTurno.obtenerTurnosPorVeterinario(v);	
		}
		
		modelo.put("turnosPorVT", turnosVeterinario);
		Veterinario vt = new Veterinario ();
		modelo.put("vt", vt);
		return new ModelAndView("servicioVeterinario", modelo);
	}

	@RequestMapping(path="generarTurno", method= RequestMethod.POST)
	public ModelAndView crearTurno(
	@ModelAttribute("veterinario") Usuario veterinario,
	@RequestParam(value="servicio",required=false) String servicioSolicitado
	/*@RequestParam(value="direccion",required=false) String direccion,
	@RequestParam(value="localidad",required=false) String localidad,
	@RequestParam(value="fecha",required=false) String dia,
	@RequestParam(value="hora",required=false) String hora,
	@RequestParam(value="hora",required=false) Long idTurno,
	@ModelAttribute("mascota") Mascota mascota,
	
	*/
	
			) {
		
		ModelMap modelo = new ModelMap();
		
		modelo.put("veterinarioNom", veterinario.getNombre());
		modelo.put("veterinarioAp", veterinario.getApellido());
		modelo.put("especialidad", servicioSolicitado);
		/*modelo.put("localidad", localidad);
		modelo.put("direccion", direccion);
		modelo.put("fecha", dia);
		modelo.put("hora", hora);*/
		//servicioTurno.asignarTurno(Long idTurno, Mascota mascota);
	
		return new ModelAndView("turnoSolicitado", modelo);
	}
	
	@RequestMapping(path = "verTurnos")
	public ModelAndView mostrarTurnos() {
		ModelMap modelo = new ModelMap();
		List<Turno> turnos = servicioTurno.listarTurnos();
		modelo.put("turnos", turnos);
		return new ModelAndView("misTurnos", modelo);
	}
	
	@RequestMapping(path = "verTurnosPacientes")
	public ModelAndView mostrarTurnosPacientes() {
		ModelMap modelo = new ModelMap();
		List<Turno> turnos = servicioTurno.listarTurnos();
		modelo.put("turnos", turnos);
		return new ModelAndView("turnosPacientes", modelo);
	}
	
	/*@RequestMapping("verPacientes")
	public ModelAndView mostrarListadoPacientes() {
		ModelMap modelo = new ModelMap();
		List<Mascota> mascotas  = servicioM.listarMascotas();
		modelo.put("pacientes", mascotas);
		return new ModelAndView("pacientes", modelo);
	}*/
	
	
	
	@RequestMapping(path="cancelarTurno", method= RequestMethod.POST)
	public ModelAndView mostrarTurnosSolicitados(
	@RequestParam(value="id_turno",required=false) Long idTurno	
			) {
		
		//ModelMap modelo = new ModelMap();
		servicioTurno.cancelarTurno(idTurno);
		/*if(servicio.cancelarTurno(idTurno)) {
			modelo.put("mensaje","Turno cancelado con exito!");	
		}*/
		
		return new ModelAndView("misTurnos");
	}
	
	
	
	@RequestMapping("volverACuenta")
	public ModelAndView irACuentaUsuario() {
		
		return new ModelAndView("cuentaUsuario");
	}
	
	

}
