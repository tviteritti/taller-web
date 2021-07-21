package ar.edu.unlam.tallerweb1.controladores;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioMascotas;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorMascota {
	
	private ServicioUsuario servicioDuenio;
	private ServicioMascotas servicioMascota;
	
	@Autowired
	public ControladorMascota( ServicioUsuario servicioDuenio, ServicioMascotas servicioMascota) {
		
		this.servicioDuenio = servicioDuenio;	
		this.servicioMascota = servicioMascota;
	}
	
	@RequestMapping("/perfilMiMascota")
	public ModelAndView verPerfilMascota(
			@RequestParam(value="duenioId",required=false) Long idDuenio,
			HttpServletRequest request) {
		
			ModelMap modelo = new ModelMap();
			
			Usuario usuarioLogueado = (Usuario) request.getSession().getAttribute("usuario");
			
			if(usuarioLogueado == null) {
				
				return new ModelAndView("redirect:/loginVeterinaria");
				
			}
			
			Usuario duenio = servicioDuenio.getDuenio(usuarioLogueado.getId());
			
			List<Mascota>  mascota = servicioMascota.listarMascotasPorDuenio(usuarioLogueado.getId());
			List<TipoAnimal> tipos = servicioMascota.listarTipoAnimal();
			
			modelo.put("duenio", duenio);
			modelo.put("duenioId", idDuenio);
			modelo.put("mascota", mascota);
			modelo.put("tipos", tipos);
			
		return new ModelAndView("perfilMascota", modelo);
	}
	
	@RequestMapping("/modificarMascota")
	public ModelAndView modificarPerfilMascota(
			@RequestParam(value="duenioId",required=false) Long idDuenio,
			@RequestParam(value="mascotaId",required=false) Long mascotaId,
			@RequestParam(value="mascotaNombre",required=false)String mascotaNombre,
			@RequestParam(value="mascotaFN",required=false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaNacimiento,
			@RequestParam(value="tipoMascota",required=false) String tipo,
			HttpServletRequest request) {
		
			ModelMap modelo = new ModelMap();
			
			Usuario usuarioLogueado = (Usuario) request.getSession().getAttribute("usuario");
			
			if(usuarioLogueado == null) {
				
				return new ModelAndView("redirect:/loginVeterinaria");
				
			}
			
			List<Mascota>  mascota = servicioMascota.listarMascotasPorDuenio(usuarioLogueado.getId());
			
			
			List<TipoAnimal> tipos = servicioMascota.listarTipoAnimal();
			
			TipoAnimal tipoAnimal = servicioMascota.obtenerTipoAnimal(tipo);
	
			modelo.put("duenio", usuarioLogueado);
			modelo.put("mascota", mascota);
			modelo.put("tipos", tipos);
			
			servicioMascota.modificarPerfilMascota(mascotaId, mascotaNombre, fechaNacimiento, tipoAnimal);
			
		return new ModelAndView("redirect:/perfilMiMascota", modelo);
	}

	public void setServicioDuenio(ServicioUsuario servicioDuenio) {
		this.servicioDuenio = servicioDuenio;
	}

	public void setServicioMascota(ServicioMascotas servicioMascota) {
		this.servicioMascota = servicioMascota;
	}
	
	
	
	

}
