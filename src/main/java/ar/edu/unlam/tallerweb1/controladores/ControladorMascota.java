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
			Long id = (Long) request.getSession().getAttribute("id");
			Usuario duenio = servicioDuenio.getDuenio(id);
			Mascota  mascota = servicioMascota.buscarMascotaPorDuenio(duenio);
			modelo.put("duenio", duenio);
			modelo.put("duenioId", idDuenio);
			modelo.put("mascota", mascota);
		return new ModelAndView("perfilMascota", modelo);
	}
	
	@RequestMapping("/modificarPerfilMascota")
	public ModelAndView modificarPerfilMascota(
			@RequestParam(value="duenioId",required=false) Long idDuenio,
			@RequestParam(value="mascotaNombre",required=false)String mascotaNombre,
			@RequestParam(value="mascotaFN",required=false) Date fechaNacimiento,
			@RequestParam(value="tipo",required=false) Long idTipo) {
		
			ModelMap modelo = new ModelMap();
			
			Usuario duenio = servicioDuenio.getDuenio(idDuenio);
			Mascota  mascota = servicioMascota.buscarMascotaPorDuenio(duenio);
			TipoAnimal tipo = servicioMascota.obtenerTipoAnimal(idTipo);
			
			mascota.setFecha_nacimiento(fechaNacimiento);
			mascota.setNombre(mascotaNombre);
			mascota.setFecha_nacimiento(fechaNacimiento);
			mascota.setTipo(tipo);
			
			modelo.put("duenio", duenio);
			modelo.put("mascota", mascota);
			
		return new ModelAndView("perfilMascota", modelo);
	}
	
	
	

}
