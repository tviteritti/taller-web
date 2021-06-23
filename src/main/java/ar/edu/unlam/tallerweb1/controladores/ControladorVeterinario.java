package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Consulta;
import ar.edu.unlam.tallerweb1.modelo.HistoriaClinica;
import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioConsulta;
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
	private ServicioConsulta servicioConsulta;

		@Autowired
		public ControladorVeterinario(
				ServicioMascotas servicioMascotas, 
				ServicioHistoriaClinica servicioHistoriaClinica,
				ServicioTurno servicioTurno,
				ServicioUsuario servicioUsuario,
				ServicioConsulta servicioConsulta) {
			
			this.servicioMascotas=servicioMascotas;	
			this.servicioHistoriaClinica=servicioHistoriaClinica;
			this.servicioTurno=servicioTurno;
			this.servicioUsuario = servicioUsuario;
			this.servicioConsulta = servicioConsulta;
		}
		
	
		@RequestMapping(path="verPacientes", method= RequestMethod.POST)
		public ModelAndView mostrarListadoPacientes(
		@RequestParam(value="veterinarioId",required=false) Long idVeterinario, HttpServletRequest request) {
			Usuario usuarioa = (Usuario) request.getSession().getAttribute("usuario");
			if(usuarioa == null) {
				return new ModelAndView("redirect:/loginVeterinaria");
			}
			if(usuarioa.getRol().equals("duenio")) {
				return new ModelAndView("redirect:/cuentaDuenio");
			}
		ModelMap modelo = new ModelMap();
		
		List<Mascota> pacientes =servicioTurno.obtenerMascotasPorTurno(idVeterinario);
	
		modelo.put("mascotas", pacientes);
		
		return new ModelAndView("pacientes",modelo);
	}
		
		@RequestMapping("/verHistoriaClinica")
		public ModelAndView mostrarHistoriaClinica(
		@RequestParam(value="mascotaId",required=false) Long idMascota,
		@RequestParam(value="duenioId",required=false) Long idDuenio,
		@RequestParam(value="veterinarioId",required=false) Long idVeterinario, HttpServletRequest request) {
			Usuario usuarioa = (Usuario) request.getSession().getAttribute("usuario");
			if(usuarioa == null) {
				return new ModelAndView("redirect:/loginVeterinaria");
			}
			if(usuarioa.getRol().equals("duenio")) {
				return new ModelAndView("redirect:/cuentaDuenio");
			}
		ModelMap modelo = new ModelMap();
		
		List<HistoriaClinica> hc = servicioHistoriaClinica.buscarHCPorMascota(idMascota);
		Usuario duenio = servicioUsuario.getDuenio(idDuenio);
		Mascota mascota = servicioMascotas.obtenerMascota(idMascota);
		Usuario veterinario = servicioUsuario.getVeterinario(idVeterinario);
		
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
		@RequestParam(value="veterinario",required=false) Long idVeterinario, HttpServletRequest request		
				) {
			Usuario usuarioa = (Usuario) request.getSession().getAttribute("usuario");
			if(usuarioa == null) {
				return new ModelAndView("redirect:/loginVeterinaria");
			}
			if(usuarioa.getRol().equals("duenio")) {
				return new ModelAndView("redirect:/cuentaDuenio");
			}
			
		ModelMap modelo = new ModelMap();
		Usuario duenio = servicioUsuario.getDuenio(idDuenio);
		Mascota mascota = servicioMascotas.obtenerMascota(idMascota);
		Usuario veterinario = servicioUsuario.getVeterinario(idVeterinario);
		modelo.put("duenio", duenio);
		modelo.put("mascota", mascota);
		modelo.put("veterinario", veterinario);
		List<TipoAnimal> tipos = servicioMascotas.listarTipoAnimal();
		modelo.put("tipos",tipos);
		
		
		return new ModelAndView("cargarHC",modelo);
	}
		
		@RequestMapping("/actualizarHistoriaClinica")
		public ModelAndView actualizarHistoriaClinica(
				
		@RequestParam(value="mascota",required=false) Long idMascota,
		@RequestParam(value="duenio",required=false) Long idDuenio,
		@RequestParam(value="veterinario",required=false) Long idVeterinario, HttpServletRequest request		
				) {
			Usuario usuarioa = (Usuario) request.getSession().getAttribute("usuario");
			if(usuarioa == null) {
				return new ModelAndView("redirect:/loginVeterinaria");
			}
			if(usuarioa.getRol().equals("duenio")) {
				return new ModelAndView("redirect:/cuentaDuenio");
			}
			
		ModelMap modelo = new ModelMap();
		Usuario duenio = servicioUsuario.getDuenio(idDuenio);
		Mascota mascota = servicioMascotas.obtenerMascota(idMascota);
		Usuario veterinario = servicioUsuario.getVeterinario(idVeterinario);
		List<TipoAnimal> tipos = servicioMascotas.listarTipoAnimal();
		List<HistoriaClinica> hc = servicioHistoriaClinica.buscarHCPorMascota(idMascota);
		modelo.put("duenio", duenio);
		modelo.put("mascota", mascota);
		modelo.put("veterinario", veterinario);
		modelo.put("historiaClinica", hc);
		modelo.put("tipos",tipos);
		
		return new ModelAndView("actualizarHC",modelo);
	}
		
		@RequestMapping("/guardarHistoriaClinica")
		public ModelAndView guardarHistoriaClinica(
		@RequestParam(value="idDuenio",required=false) Long idDuenio,	
		@RequestParam(value="idMascota",required=false) Long idMascota,
		@RequestParam(value="idVeterinario",required=false) Long idVeterinario,
		@RequestParam(value="nombreMascota",required=false) String nombreMascota,
		@RequestParam(value="fechaNacimiento",required=false) String fechaNacimientoMascota,
		@RequestParam(value="tipoMascota",required=false) String tipoMascota,
		@RequestParam(value="dNombre",required=false) String duenioNombre,
		@RequestParam(value="dApellido",required=false) String duenioApellido,
		@RequestParam(value="telefonoDuenio",required=false) String telefonoDuenio,
		@RequestParam(value="emailDuenio",required=false) String emailDuenio,
		@RequestParam(value="direccionDuenio",required=false) String direccionDuenio,
		@RequestParam(value="localidadDuenio",required=false) String localidadDuenio,
		@RequestParam(value="vNombre",required=false) String veterinarioNombre,
		@RequestParam(value="vApellido",required=false) String veterinarioApellido,
		@RequestParam(value="fechaHC",required=false) String fechaHC,
		@RequestParam(value="diagnostico",required=false) String diagnostico,
		@RequestParam(value="tratamiento",required=false) String tratamiento, HttpServletRequest request	
				) {
			Usuario usuarioa = (Usuario) request.getSession().getAttribute("usuario");
			if(usuarioa == null) {
				return new ModelAndView("redirect:/loginVeterinaria");
			}
			if(usuarioa.getRol().equals("duenio")) {
				return new ModelAndView("redirect:/cuentaDuenio");
			}
			
			ModelMap modelo = new ModelMap();
			Mascota mascota = servicioMascotas.obtenerMascota(idMascota);
			Usuario veterinario = servicioUsuario.getVeterinario(idVeterinario);
			Usuario duenio = servicioUsuario.getDuenio(idDuenio);
			
			HistoriaClinica hc = new HistoriaClinica();
			hc.setMascota(mascota);
			hc.setFecha(fechaHC);
			hc.setDiagnostico(diagnostico);
			hc.setMascota(mascota);
			hc.setTratamiento(tratamiento);
			hc.setVeterinario(veterinario);
		
			servicioHistoriaClinica.cargarHC(hc);
			
			modelo.put("duenio", duenio);
			modelo.put("mascota", mascota);
			modelo.put("veterinario", veterinario);	
			modelo.put("hc", hc);
			
			List<HistoriaClinica> hcMascota = servicioHistoriaClinica.buscarHCPorMascota(idMascota);
			modelo.put("hc", hcMascota);
			
			return new ModelAndView("historiaClinica", modelo);
	}	
		
		
		@RequestMapping("/consultasDeUsuarios")
		public ModelAndView verConsultas(
		@RequestParam(value="veterinarioId",required=false) Long idVeterinario) {
			
			ModelMap modelo = new ModelMap();
			
			Usuario vt = servicioUsuario.getVeterinario(idVeterinario);
			List <Consulta> consultas = servicioConsulta.listarConsultas();
			
			modelo.put("veterinario", vt);
			modelo.put("consultas", consultas);
			
		 return new ModelAndView("consultasUsuarios",modelo);
		 
		}	
		
		
		@RequestMapping("/responderConsultas")
		public ModelAndView responderConsultas(
		@RequestParam(value="veterinarioId",required=false) Long idVeterinario,
		@RequestParam(value="comentario",required=false) String comentario,
		@RequestParam(value="idConsulta",required=false) Long idConsulta,
		HttpServletRequest request) {
			
			ModelMap modelo = new ModelMap();
			Usuario usuarioLogueado = (Usuario) request.getSession().getAttribute("usuario");
			
			if(idConsulta!=null && usuarioLogueado!=null) {
				
				servicioConsulta.agregarComentario(idConsulta, comentario, usuarioLogueado.getUser());
			}
			
			Usuario vt = servicioUsuario.getVeterinario(idVeterinario);
			List <Consulta> consultas = servicioConsulta.listarConsultas();
			
			modelo.put("veterinario", vt);
			modelo.put("consultas", consultas);
			modelo.put("usuario", usuarioLogueado);
			
		 return new ModelAndView("consultasUsuarios",modelo);
		 
		}
		
		
		@RequestMapping("/verMiPerfil")
		public ModelAndView verPerfil(
				@RequestParam(value="veterinarioId",required=false) Long idVeterinario) {
			
				ModelMap modelo = new ModelMap();
				Usuario usuario = servicioUsuario.getVeterinario(idVeterinario);
				
				modelo.put("usuario", usuario);
				
			return new ModelAndView("perfil", modelo);
		}
		

}
