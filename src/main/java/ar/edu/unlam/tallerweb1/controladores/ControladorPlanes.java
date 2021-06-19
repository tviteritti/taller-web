package ar.edu.unlam.tallerweb1.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioPlanes;

@Controller
public class ControladorPlanes {

	private ServicioPlanes servicioPlanes;
	
	@Autowired
	public ControladorPlanes(ServicioPlanes servicioPlanes) {
		
		this.servicioPlanes = servicioPlanes;
	}
	
	@RequestMapping("/tomarUnPlan")
	public ModelAndView tomarUnPlan(@RequestParam("planId") Long planId, @RequestParam("duenioId") Long duenioId, HttpServletRequest request) {
		//request.getSession().setAttribute("id_turno", planId);
		servicioPlanes.accederPlan(planId, duenioId);
		
	return new ModelAndView("redirect:/cuentaDuenio");
	}
	
	@RequestMapping("/pagarPlan")
	public ModelAndView pagarPlan(@RequestParam("contratacionId") Long contratacionId) {
		
		servicioPlanes.pagarPlan(contratacionId);
		
	return new ModelAndView("redirect:/cuentaDuenio");
	}
}
