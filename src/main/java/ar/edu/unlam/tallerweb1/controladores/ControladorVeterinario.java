package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.HistoriaClinica;
import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioHistoriaClinica;
import ar.edu.unlam.tallerweb1.servicios.ServicioMascotas;
import ar.edu.unlam.tallerweb1.servicios.ServicioTurno;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorVeterinario {
	
	private ServicioMascotas servicioMascotas;
	private ServicioHistoriaClinica servicioHistoriaClinica;
	private ServicioTurno servicioTurno;
	private ServicioUsuario servicioUsuario;

		@Autowired
		public ControladorVeterinario(
				ServicioMascotas servicioMascotas, 
				ServicioHistoriaClinica servicioHistoriaClinica,
				ServicioTurno servicioTurno,
				ServicioUsuario servicioUsuario) {
			
			this.servicioMascotas=servicioMascotas;	
			this.servicioHistoriaClinica=servicioHistoriaClinica;
			this.servicioTurno=servicioTurno;
			this.servicioUsuario = servicioUsuario;
		}
		
	
		@RequestMapping(path="verPacientes", method= RequestMethod.POST)
		public ModelAndView mostrarListadoPacientes(
		@RequestParam(value="veterinarioId",required=false) Long idVeterinario) {
		
		ModelMap modelo = new ModelMap();
		
		List<Mascota> pacientes =servicioTurno.obtenerMascotasPorTurno(idVeterinario);
		
		modelo.put("pacientes", pacientes);
		
		return new ModelAndView("pacientes",modelo);
	}
		
		@RequestMapping("/verHistoriaClinica")
		public ModelAndView mostrarHistoriaClinica(
		@RequestParam(value="mascotaId",required=false) Long idMascota,
		@RequestParam(value="duenioId",required=false) Long idDuenio,
		@RequestParam(value="veterinarioId",required=false) Long idVeterinario) {
		
		ModelMap modelo = new ModelMap();
		
		List<HistoriaClinica> hc = servicioHistoriaClinica.buscarHCPorMascota(idMascota);
		Usuario duenio = servicioUsuario.getUsuario(idDuenio);
		Mascota mascota = servicioMascotas.obtenerMascota(idMascota);
		Usuario veterinario = servicioUsuario.getUsuario(idVeterinario);
		
		modelo.put("duenio", duenio);
		modelo.put("mascota", mascota);
		modelo.put("veterinario", veterinario);
		modelo.put("hc", hc);
		
		return new ModelAndView("historiaClinica",modelo);
	}
		
	
		@RequestMapping("/cargarHistoriaClinica")
		public ModelAndView cargarHistoriaClinica(
				
		@RequestParam(value="mascota",required=false) Long idMascota,
		@RequestParam(value="duenio",required=false) Long idDuenio,
		@RequestParam(value="veterinario",required=false) Long idVeterinario		
				) {
		
			
		ModelMap modelo = new ModelMap();
		Usuario duenio = servicioUsuario.getUsuario(idDuenio);
		Mascota mascota = servicioMascotas.obtenerMascota(idMascota);
		Usuario veterinario = servicioUsuario.getUsuario(idVeterinario);
		modelo.put("duenio", duenio);
		modelo.put("mascota", mascota);
		modelo.put("veterinario", veterinario);
		
		return new ModelAndView("cargarHC",modelo);
	}
		
		@RequestMapping("/guardarHistoriaClinica")
		public ModelAndView guardarHistoriaClinica(
		@RequestParam(value="idDuenio",required=false) Long idDuenio,	
		@RequestParam(value="idMascota",required=false) Long idMascota,
		@RequestParam(value="idVeterinario",required=false) Long idVeterinario,
		@RequestParam(value="fecha",required=false) String fecha,
		@RequestParam(value="diagnostico",required=false) String diagnostico,	
		@RequestParam(value="tratamiento",required=false) String tratamiento	
				) {
			
			ModelMap modelo = new ModelMap();
			Mascota mascota = servicioMascotas.obtenerMascota(idMascota);
			Usuario veterinario = servicioUsuario.getUsuario(idVeterinario);
			Usuario duenio = servicioUsuario.getUsuario(idDuenio);
			
			servicioHistoriaClinica.cargarHC(veterinario, mascota, fecha, diagnostico, tratamiento);
			
			modelo.put("duenio", duenio);
			modelo.put("mascota", mascota);
			modelo.put("veterinario", veterinario);	
			List<HistoriaClinica> hc = servicioHistoriaClinica.buscarHCPorMascota(idMascota);
			modelo.put("hc", hc);
			
			return new ModelAndView("historiaClinica", modelo);
	}	
		

}
