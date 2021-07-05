package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.controladores.ControladorPlanes;
import ar.edu.unlam.tallerweb1.controladores.ControladorVeterinario;
import ar.edu.unlam.tallerweb1.modelo.Consulta;
import ar.edu.unlam.tallerweb1.modelo.HistoriaClinica;
import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Planes;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioConsulta;
import ar.edu.unlam.tallerweb1.servicios.ServicioHistoriaClinica;
import ar.edu.unlam.tallerweb1.servicios.ServicioMascotas;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificaciones;
import ar.edu.unlam.tallerweb1.servicios.ServicioPlanes;
import ar.edu.unlam.tallerweb1.servicios.ServicioTurno;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

public class ControladorPlanesTest extends SpringTest{

	private ServicioUsuario servicioUsuario;
	private ServicioPlanes servicioPlanes;
	private ControladorPlanes controladorPlanes = new ControladorPlanes(servicioPlanes,servicioUsuario);
	private Planes planMock;
	private Usuario usuarioMock;
	private HttpServletRequest requestMock;
	private HttpSession sessionMock;
	private ServicioPlanes servicioPlanesMock;
	private ServicioUsuario servicioUsuarioMock;
	


	@Before
	public void init(){
		usuarioMock = mock(Usuario.class);
		planMock = mock(Planes.class);
		requestMock = mock(HttpServletRequest.class);
		sessionMock = mock(HttpSession.class);
		servicioPlanesMock = mock(ServicioPlanes.class);
		controladorPlanes.setServicioPlanes(servicioPlanesMock);
		servicioUsuarioMock = mock(ServicioUsuario.class);
		controladorPlanes.setServicioDuenio(servicioUsuarioMock);
	}
	
	
	@Test
	@Rollback(true)
	@Transactional
	public void validarTomarUnPlan(){
		
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("usuario")).thenReturn(usuarioMock);
		when(usuarioMock.getRol()).thenReturn("duenio");
		
		ModelAndView modelAndView = controladorPlanes.tomarUnPlan(1L, 1L,requestMock);
		
		assertThat(modelAndView.getViewName()).isEqualTo("redirect:/cuentaDuenio");
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void validarPagarPlan(){
		
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("usuario")).thenReturn(usuarioMock);
		when(usuarioMock.getRol()).thenReturn("duenio");
		
		ModelAndView modelAndView = controladorPlanes.pagarPlan(1L,requestMock);
		
		assertThat(modelAndView.getViewName()).isEqualTo("redirect:/cuentaDuenio");
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void validarPagarPlanExtra(){
		
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("usuario")).thenReturn(usuarioMock);
		when(usuarioMock.getRol()).thenReturn("duenio");
		
		ModelAndView modelAndView = controladorPlanes.pagarPlanExtra(1L,requestMock);
		
		assertThat(modelAndView.getViewName()).isEqualTo("redirect:/cuentaDuenio");
	}
	
}
