package ar.edu.unlam.tallerweb1.controladores;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@RequestMapping("/perfilMascota")
	public ModelAndView verPerfilMascota(
			@RequestParam(value="duenioId",required=false) Long idDuenio,
			HttpServletRequest request) {
		
			ModelMap modelo = new ModelMap();
			
			Usuario usuarioLogueado = (Usuario) request.getSession().getAttribute("usuario");
			
			Usuario duenio = servicioDuenio.getDuenio(usuarioLogueado.getId());
			
			Mascota  mascota = servicioMascota.buscarMascotaPorDuenio(usuarioLogueado.getId());
			
			modelo.put("duenio", duenio);
			modelo.put("duenioId", idDuenio);
			modelo.put("mascota", mascota);
			
		return new ModelAndView("perfilMascota", modelo);
	}
	
	@RequestMapping("/modificarPerfilMascota")
	public ModelAndView modificarPerfilMascota(
			@RequestParam(value="duenioId",required=false) Long idDuenio,
			@RequestParam(value="mascotaId",required=false) Long mascotaId,
			@RequestParam(value="mascotaNombre",required=false)String mascotaNombre,
			@RequestParam(value="mascotaFN",required=false) Date fechaNacimiento,
			@RequestParam(value="tipo",required=false) Long idTipo,
			HttpServletRequest request) {
		
			ModelMap modelo = new ModelMap();
			
			Usuario usuarioLogueado = (Usuario) request.getSession().getAttribute("usuario");
			
			Mascota  mascota = servicioMascota.buscarMascotaPorDuenio(usuarioLogueado.getId());
			
			TipoAnimal tipo = servicioMascota.obtenerTipoAnimal(idTipo);
			
			modelo.put("duenio", usuarioLogueado);
			modelo.put("mascota", mascota);
			servicioMascota.modificarPerfilMascota(mascotaId, mascotaNombre, fechaNacimiento, tipo);
			
		return new ModelAndView("redirect:/perfilMascota", modelo);
	}

	public void setServicioDuenio(ServicioUsuario servicioDuenio) {
		this.servicioDuenio = servicioDuenio;
	}

	public void setServicioMascota(ServicioMascotas servicioMascota) {
		this.servicioMascota = servicioMascota;
	}
	
	
	
	

}
