package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.controladores.ControladorDuenio;
import ar.edu.unlam.tallerweb1.controladores.ControladorLoginVeterinaria;
import ar.edu.unlam.tallerweb1.modelo.Consulta;
import ar.edu.unlam.tallerweb1.modelo.Dias;
import ar.edu.unlam.tallerweb1.modelo.Horarios;
import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Planes;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioConsulta;
import ar.edu.unlam.tallerweb1.servicios.ServicioDias;
import ar.edu.unlam.tallerweb1.servicios.ServicioHorarios;
import ar.edu.unlam.tallerweb1.servicios.ServicioMascotas;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificaciones;
import ar.edu.unlam.tallerweb1.servicios.ServicioPlanes;
import ar.edu.unlam.tallerweb1.servicios.ServicioTurno;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

public class ControladorDuenioTest extends SpringTest{

	private ServicioUsuario servicioUsuario;
	private ServicioMascotas servicioMascota;
	private ServicioPlanes servicioPlanes;
	private ServicioNotificaciones servicioNotificaciones;
	private ServicioConsulta servicioConsulta;
	private ControladorDuenio controladorDuenio = new ControladorDuenio(servicioUsuario, servicioMascota, servicioPlanes, servicioConsulta, servicioNotificaciones);
	private Usuario usuarioMock;
	private Turno turnoMock;
	private Mascota mascotaMock;
	private Notificacion notificacionesMock;
	private Consulta consultaMock;
	private HttpServletRequest requestMock;
	private HttpSession sessionMock;
	private ServicioUsuario servicioUsuarioMock;
	private ServicioPlanes servicioPlanesMock;
	private ServicioMascotas servicioMascotasMock;
	private ServicioNotificaciones servicioNotificacionesMock;
	private ServicioConsulta servicioConsultaMock;



	@Before
	public void init(){
		usuarioMock = mock(Usuario.class);
		turnoMock = mock(Turno.class);
		mascotaMock = mock(Mascota.class);
		notificacionesMock = mock(Notificacion.class);
		consultaMock = mock(Consulta.class);
		requestMock = mock(HttpServletRequest.class);
		sessionMock = mock(HttpSession.class);
		servicioUsuarioMock = mock(ServicioUsuario.class);
		controladorDuenio.setServicioDuenio(servicioUsuarioMock);
		servicioPlanesMock = mock(ServicioPlanes.class);
		controladorDuenio.setServicioPlanes(servicioPlanesMock);
		servicioMascotasMock = mock(ServicioMascotas.class);
		controladorDuenio.setServicioMascota(servicioMascotasMock);
		servicioNotificacionesMock = mock(ServicioNotificaciones.class);
		controladorDuenio.setServicioNotificaciones(servicioNotificacionesMock);
		servicioConsultaMock = mock(ServicioConsulta.class);
		controladorDuenio.setServicioConsulta(servicioConsultaMock);
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void validarIrAMiMascotaSinSesion(){
		
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("usuario")).thenReturn(null);
		
		ModelAndView modelAndView = controladorDuenio.irAMiMascota(1L, requestMock);
		
		assertThat(modelAndView.getViewName()).isEqualTo("redirect:/loginVeterinaria");
	}

	@Test
	@Rollback(true)
	@Transactional
	public void validarIrAMiTurnos(){
		
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("usuario")).thenReturn(usuarioMock);
		when(usuarioMock.getRol()).thenReturn("duenio");
		
		ModelAndView modelAndView = controladorDuenio.irAMiTurnos(1L, requestMock);
		
		assertThat(modelAndView.getViewName()).isEqualTo("turnos");
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void validarIrAConsultas(){
		
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("usuario")).thenReturn(usuarioMock);
		when(usuarioMock.getRol()).thenReturn("duenio");
		
		ModelAndView modelAndView = controladorDuenio.irAConsultas(1L, requestMock);
		
		assertThat(modelAndView.getViewName()).isEqualTo("consultas");
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void validarRealizarConsulta(){
		
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("usuario")).thenReturn(usuarioMock);
		when(usuarioMock.getRol()).thenReturn("duenio");
		when(servicioUsuarioMock.getDuenio(1L)).thenReturn(usuarioMock);
		when(servicioConsultaMock.buscarConsulta(1L)).thenReturn(consultaMock);
		
		ModelAndView modelAndView = controladorDuenio.realizarConsulta(1L, "asd", "asd", "asd", "asd", 1L, 1L, requestMock);
		
		assertThat(modelAndView.getViewName()).isEqualTo("miConsulta");
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void validarVerMisConsultas(){
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("usuario")).thenReturn(usuarioMock);
		when(usuarioMock.getRol()).thenReturn("duenio");
		when(servicioUsuarioMock.getDuenio(1L)).thenReturn(usuarioMock);
		
		ModelAndView modelAndView = controladorDuenio.verMisConsultas(1L);
		
		assertThat(modelAndView.getViewName()).isEqualTo("consultasDuenio");
	}
	
	
	@Test
	@Rollback(true)
	@Transactional
	public void validarIrAPlanes(){
		
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("usuario")).thenReturn(usuarioMock);
		when(usuarioMock.getRol()).thenReturn("duenio");
		
		ModelAndView modelAndView = controladorDuenio.irAPlanes(1L, requestMock);
		
		assertThat(modelAndView.getViewName()).isEqualTo("planes");
	}
	
	
	@Test
	@Rollback(true)
	@Transactional
	public void validarCargarMascota(){
		
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("usuario")).thenReturn(usuarioMock);
		when(usuarioMock.getRol()).thenReturn("duenio");
		
		ModelAndView modelAndView = controladorDuenio.cargarMascota(requestMock);
		
		assertThat(modelAndView.getViewName()).isEqualTo("FormCargarMascota");
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void validarDatosPaciente() throws ParseException{
		
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("usuario")).thenReturn(usuarioMock);
		when(usuarioMock.getRol()).thenReturn("duenio");
		
		ModelAndView modelAndView = controladorDuenio.validarDatosPaciente(1L,1L,"asd","asd");
		
		assertThat(modelAndView.getViewName()).isEqualTo("redirect:/cuentaDuenio");
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void validarCalificarVeterivario(){
		
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("usuario")).thenReturn(usuarioMock);
		when(usuarioMock.getRol()).thenReturn("duenio");
		
		ModelAndView modelAndView = controladorDuenio.calificarVeterivario(1L,1.0,requestMock);
		
		assertThat(modelAndView.getViewName()).isEqualTo("redirect:/cuentaDuenio");
	}
	
	
	
	
	
	
	
	
	
	

}