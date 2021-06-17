package ar.edu.unlam.tallerweb1.controladores;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioDias;
import ar.edu.unlam.tallerweb1.servicios.ServicioHorarios;
import ar.edu.unlam.tallerweb1.servicios.ServicioMascotas;
import ar.edu.unlam.tallerweb1.servicios.ServicioTurno;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorDuenio {
	
	private ServicioUsuario servicioDuenio;
	private ServicioMascotas servicioMascota;

	
	@Autowired
	public ControladorDuenio( ServicioUsuario servicioDuenio, ServicioMascotas servicioMascota) {
		
		this.servicioDuenio = servicioDuenio;	
		this.servicioMascota = servicioMascota;
	}
	
	@RequestMapping("/verPerfil")
	public ModelAndView verPerfil(
			@RequestParam(value="duenioId",required=false) Long idDuenio) {
			ModelMap modelo = new ModelMap();
			Usuario duenio = servicioDuenio.getDuenio(idDuenio);
			modelo.put("duenio", duenio);

		return new ModelAndView("perfil", modelo);
	}
	
	@RequestMapping("/cargarMascota")
	public ModelAndView cargarMascota() {
			ModelMap modelo = new ModelMap();
			List<TipoAnimal> listadoTipos=servicioMascota.listarTipoAnimal();
			modelo.put("listadoTipos", listadoTipos);

		return new ModelAndView("FormCargarMascota", modelo);
	}
	
	@RequestMapping(path="procesarDatosMascota", method= RequestMethod.POST)
	public ModelAndView validarDatosPaciente(
			@RequestParam(value="id_tipo",required=false) Long id_tipo,
			@RequestParam(value="id_duenio",required=false) Long id_duenio,
			@RequestParam(value="fecha_nac",required=false) String fecha_nac,
			@RequestParam(value="nombre",required=false) String nombre) throws ParseException {
		
		
		servicioMascota.cargarMascota(id_tipo, id_duenio, fecha_nac, nombre);
		
		ModelMap modelo = new ModelMap();
		
		
		
		return new ModelAndView("cuentaDuenio", modelo);
	}
}
