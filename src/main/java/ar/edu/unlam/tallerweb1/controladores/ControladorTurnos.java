package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.ContratacionPlanes;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.Planes;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import ar.edu.unlam.tallerweb1.modelo.Zona;

import ar.edu.unlam.tallerweb1.servicios.ServicioMascotas;
import ar.edu.unlam.tallerweb1.servicios.ServicioPlanes;
import ar.edu.unlam.tallerweb1.servicios.ServicioTurno;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorTurnos {
	
private ServicioTurno servicioTurno;
private ServicioMascotas servicioMascotas;
private ServicioUsuario servicioUsuario;
private ServicioPlanes servicioPlanes;


	
	@Autowired
	public ControladorTurnos(ServicioTurno servicioTurno, 
							 ServicioMascotas servicioMascotas, 
							 ServicioUsuario servicioUsuario, ServicioPlanes servicioPlanes) {
		
		this.servicioTurno = servicioTurno;	
		this.servicioMascotas=servicioMascotas;
		this.servicioUsuario =servicioUsuario;
		this.servicioPlanes =servicioPlanes;
		
		
	}
	
	@RequestMapping("buscarTurno")
	public ModelAndView buscarServicioVeterinario(
		@RequestParam(value="duenioId",required=false) Long duenioId) {
		ModelMap modelo = new ModelMap();
		
		Zona zona = new Zona();
		Usuario duenio = servicioUsuario.getUsuario(duenioId);
		List<Especialidad> listadoEspecialidad=servicioUsuario.getEspecialidades();
		List<Zona> listadoZona=servicioUsuario.getZonas();
		
		modelo.put("listadoZonas", listadoZona);
		modelo.put("listadoEspecialidad", listadoEspecialidad);
		modelo.put("zona", zona);
		modelo.put("duenio", duenio);
		modelo.put("duenioId", duenioId);
		
		return new ModelAndView("buscarTurno", modelo);
	}
	
	@RequestMapping(path="buscarServicioVeterinario", method= RequestMethod.POST)
	public ModelAndView mostrarServicioVeterinario(
			@RequestParam(value="id_zona",required=false) Long id_zona,
			@RequestParam(value="id_especialidad",required=false) Long id_especialidad,
			@RequestParam(value="idDuenio",required=false) Long duenioId, HttpServletRequest request) {
		
		ModelMap modelo = new ModelMap();
		
		modelo.put("servicio", servicioUsuario.getEspecialidad(id_especialidad).getDescripcion());
		modelo.put("zona",servicioUsuario.getZona(id_zona).getDescripcion());
		
		Usuario duenio = servicioUsuario.getDuenio(duenioId);
		modelo.put("duenio", duenio);
		
		List<Usuario>veterinariosEncontrados = servicioUsuario.buscarVeterinariosPorEspecialidadYZona(id_especialidad, id_zona);
		
		modelo.put("veterinarios", veterinariosEncontrados);
		
		List<Turno>turnosVeterinario=new ArrayList<>();
		
		for(Usuario v : veterinariosEncontrados) {
			request.getSession().setAttribute("veterinarioTurno", v);
			for(Turno turno :servicioTurno.obtenerTurnosPorVeterinario(v) ) {
				
				turnosVeterinario.add(turno);
				
			}
			
		}
		modelo.put("turnosPorVT", turnosVeterinario);
		
		
		List<Mascota> listaDeMascotas = servicioMascotas.listarMascotasPorDuenio(duenio);
		
		modelo.put("listaDeMascotas", listaDeMascotas);
		
		if(!servicioPlanes.verificarSiTienePlanVigente(duenio)) {
			String errorSinTurno = "No tiene plan vigente";
			request.getSession().setAttribute("errorSinPlan", errorSinTurno);
			return new ModelAndView("servicioVeterinario", modelo);
		}
		Planes plan = servicioPlanes.devolverPlanDeDuenio(duenio);
		ContratacionPlanes cotratacion = servicioPlanes.devolverContratacionDeDuenio(duenio);
		request.getSession().setAttribute("idcotratacion", cotratacion.getId());
		if(cotratacion.getCantidadTurnosTomados()<plan.getCantidadTurnos()) {
			return new ModelAndView("servicioVeterinario", modelo);
		}else {
			String error = "Excede el limite de turnos permitidos por el plan";
			request.getSession().setAttribute("errorExede", error);
			
		}
		
		
		
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
	@RequestParam(value="id_mascotas",required=false) Long id_mascotas,
	@RequestParam(value="idDuenio",required=false) Long duenioId, HttpServletRequest request
	) {
		
		ModelMap modelo = new ModelMap();
		
		Usuario veterinario = servicioUsuario.getVeterinario(veterinarioId);
		
		modelo.put("veterinario", veterinario);
		
		Usuario duenio = servicioUsuario.getUsuario(duenioId);
		
		Mascota mascota = servicioMascotas.buscarMascotaPorDuenio(duenio);
		
		
		modelo.put("especialidad", servicioSolicitado);
		modelo.put("localidad", localidad);
		modelo.put("direccion", direccion);
		modelo.put("fecha", dia);
		modelo.put("hora", hora);
		
		Turno turno = new Turno();
		
		
		turno.setMascota(mascota);
		turno.setDuenio(duenio);
		
		modelo.put("duenio", duenio);
		modelo.put("mascota", mascota);
		
		
		
		servicioTurno.tomarTurno(idTurno, id_mascotas);
		if(request.getSession().getAttribute("errorExede") != null) {
			servicioPlanes.aumentarValorExtra((Long)request.getSession().getAttribute("idcotratacion"),veterinario.getPrecioSesion());
		}else {
			if(request.getSession().getAttribute("errorSinPlan") != null) {
				servicioPlanes.aumentarValorExtraSinPLan(duenio,veterinario.getPrecioSesion());
			}else {
				servicioPlanes.aumentarTurnosTomados((Long)request.getSession().getAttribute("idcotratacion"));
			}
		}
		//servicioTurno.cargarTurno(turno);
		return new ModelAndView("turnoSolicitado", modelo);
	}
	
	//cuenta Duenio
	
	@RequestMapping("misTurnos")
	public ModelAndView mostrarTurnos(
		@RequestParam(value="duenioId",required=false) Long duenioId) {
		ModelMap modelo = new ModelMap();
		
		Usuario duenio = servicioUsuario.getUsuario(duenioId);
		List<Turno> turnos = servicioTurno.buscarTurnoPorDuenio(duenioId);
		
		modelo.put("turnos", turnos);
		modelo.put("duenio", duenio);
		return new ModelAndView("misTurnos", modelo);
	}
	
	//cuenta Veterinario
	
	@RequestMapping(path = "verTurnosPacientes")
	public ModelAndView mostrarTurnosPacientes(
	  @RequestParam(value="veterinarioId",required=false) Long veterinarioId
			) {
		ModelMap modelo = new ModelMap();
		List<Turno> turnos = servicioTurno.buscarTurnoPorVeterinario(veterinarioId);
		modelo.put("turnos", turnos);
		return new ModelAndView("turnosPacientes", modelo);
	}
	
	@RequestMapping(path = "contactarPaciente")
	public ModelAndView contactarPaciente(
	  @RequestParam(value="duenio",required=false) Long duenio
			) {
		
		ModelMap modelo = new ModelMap();
		Usuario duenioMascota = servicioUsuario.getDuenio(duenio);
		
		modelo.put("nombre", duenioMascota.getNombre());
		modelo.put("apellido", duenioMascota.getApellido());
		modelo.put("telefono", duenioMascota.getTelefono());
		modelo.put("email", duenioMascota.getEmail());
		modelo.put("direccion", duenioMascota.getDireccion());
		
		return new ModelAndView("datosContactoDuenio", modelo);
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
		
		return new ModelAndView("cuentaDuenio");
	}
	
	

}
