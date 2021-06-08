package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
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

import ar.edu.unlam.tallerweb1.modelo.Zona;

import ar.edu.unlam.tallerweb1.servicios.ServicioMascotas;
import ar.edu.unlam.tallerweb1.servicios.ServicioTurno;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorTurnos {
	
private ServicioTurno servicioTurno;
private ServicioMascotas servicioMascotas;
private ServicioUsuario servicioUsuario;

	
	@Autowired
	public ControladorTurnos(ServicioTurno servicioTurno, 
							 ServicioMascotas servicioMascotas, 
							 ServicioUsuario servicioUsuario) {
		
		this.servicioTurno = servicioTurno;	
		this.servicioMascotas=servicioMascotas;
		this.servicioUsuario =servicioUsuario;
		
	}
	
	@RequestMapping("buscarTurno")
	public ModelAndView buscarServicioVeterinario(
		@RequestParam(value="duenioId",required=false) Long duenioId) {
		ModelMap modelo = new ModelMap();
		
		Zona zona = new Zona();
		Usuario duenio = servicioUsuario.getUsuario(duenioId);
		
		modelo.put("zona", zona);
		modelo.put("duenio", duenio);
		
		return new ModelAndView("buscarTurno", modelo);
	}
	
	@RequestMapping(path="buscarServicioVeterinario", method= RequestMethod.POST)
	public ModelAndView mostrarServicioVeterinario(
			@RequestParam(value="servicio",required=false) String servicioSolicitado,
			@RequestParam(value="duenioId",required=false) Long duenioId,
			@ModelAttribute("zona") Zona zona) {
		
		ModelMap modelo = new ModelMap();
		modelo.put("servicio", servicioSolicitado);
		modelo.put("zona", zona);
		
		Usuario duenio = servicioUsuario.getUsuario(duenioId);
		modelo.put("duenio", duenio);
		
		List<Usuario>veterinariosEncontrados = servicioUsuario.buscarVeterinariosPorEspecialidadYZona(servicioSolicitado, zona.getDescripcion());
		
		modelo.put("veterinarios", veterinariosEncontrados);
		
		List<Turno>turnosVeterinario=new ArrayList<>();
		
		for(Usuario v : veterinariosEncontrados) {
			
			for(Turno turno :servicioTurno.obtenerTurnosPorVeterinario(v) ) {
				
				turnosVeterinario.add(turno);
				
			}
			
		}
		
		modelo.put("turnosPorVT", turnosVeterinario);
		
		return new ModelAndView("servicioVeterinario", modelo);
	}

	@RequestMapping(path="generarTurno", method= RequestMethod.POST)
	public ModelAndView crearTurno(
	@RequestParam(value="veterinarioId",required=false) Long veterinarioId,
	@RequestParam(value="servicio",required=false) String servicioSolicitado,
	@RequestParam(value="direccion",required=false) String direccion,
	@RequestParam(value="localidad",required=false) String localidad,
	@RequestParam(value="fecha",required=false) String dia,
	@RequestParam(value="hora",required=false) String hora,
	@RequestParam(value="idTurno",required=false) Long idTurno,
	@RequestParam(value="idDuenio",required=false) Long duenioId
	) {
		
		ModelMap modelo = new ModelMap();
		
		Usuario veterinario = servicioUsuario.getUsuario(veterinarioId);
		modelo.put("veterinario", veterinario);
		
		Usuario duenio = servicioUsuario.getUsuario(duenioId);
		modelo.put("duenio", duenio);
		
		Mascota mascota = servicioMascotas.buscarMascotaPorDuenio(duenio);
		modelo.put("mascota", mascota);
		
		modelo.put("especialidad", servicioSolicitado);
		modelo.put("localidad", localidad);
		modelo.put("direccion", direccion);
		modelo.put("fecha", dia);
		modelo.put("hora", hora);
		
		servicioTurno.asignarTurno(idTurno, mascota , duenio);
		
	
		return new ModelAndView("turnoSolicitado", modelo);
	}
	
	@RequestMapping(path = "verTurnos")
	public ModelAndView mostrarTurnos(
		@RequestParam(value="duenioId",required=false) Long duenioId) {
		ModelMap modelo = new ModelMap();
		
		Usuario duenio = servicioUsuario.getUsuario(duenioId);
		List<Turno> turnos = servicioTurno.buscarTurnoPorUsuario(duenio);
		
		modelo.put("turnos", turnos);
		modelo.put("idUsuario", duenioId);
		return new ModelAndView("misTurnos", modelo);
	}
	
	@RequestMapping(path = "verTurnosPacientes")
	public ModelAndView mostrarTurnosPacientes() {
		ModelMap modelo = new ModelMap();
		List<Turno> turnos = servicioTurno.listarTurnos();
		modelo.put("turnos", turnos);
		return new ModelAndView("turnosPacientes", modelo);
	}
	
	
	@RequestMapping(path="cancelarTurno", method= RequestMethod.POST)
	public ModelAndView mostrarTurnosSolicitados(
	@RequestParam(value="id_turno",required=false) Long idTurno	
			) {
		
		servicioTurno.cancelarTurno(idTurno);
		
		return new ModelAndView("misTurnos");
	}
	
	
	
	@RequestMapping("volverACuenta")
	public ModelAndView irACuentaUsuario() {
		
		return new ModelAndView("cuentaUsuario");
	}
	
	

}
