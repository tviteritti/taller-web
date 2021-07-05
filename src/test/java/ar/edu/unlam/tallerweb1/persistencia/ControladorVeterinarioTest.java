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
import ar.edu.unlam.tallerweb1.controladores.ControladorDuenio;
import ar.edu.unlam.tallerweb1.controladores.ControladorVeterinario;
import ar.edu.unlam.tallerweb1.modelo.Consulta;
import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioConsulta;
import ar.edu.unlam.tallerweb1.servicios.ServicioHistoriaClinica;
import ar.edu.unlam.tallerweb1.servicios.ServicioMascotas;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificaciones;
import ar.edu.unlam.tallerweb1.servicios.ServicioPlanes;
import ar.edu.unlam.tallerweb1.servicios.ServicioTurno;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

public class ControladorVeterinarioTest extends SpringTest{

	private ServicioMascotas servicioMascotas;
	private ServicioHistoriaClinica servicioHistoriaClinica;
	private ServicioTurno servicioTurno;
	private ServicioUsuario servicioUsuario;
	private ServicioConsulta servicioConsulta;
	private ServicioNotificaciones servicioNotificaciones;
	private ControladorVeterinario controladorVeterinario = new ControladorVeterinario(servicioMascotas, servicioHistoriaClinica, servicioTurno, servicioUsuario, servicioConsulta, servicioNotificaciones);
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
	private ServicioTurno servicioTurnoMock;



	@Before
	public void init(){
		usuarioMock = mock(Usuario.class);
		turnoMock = mock(Turno.class);
		mascotaMock = mock(Mascota.class);
		notificacionesMock = mock(Notificacion.class);
		consultaMock = mock(Consulta.class);
		turnoMock = mock(Turno.class);
		requestMock = mock(HttpServletRequest.class);
		sessionMock = mock(HttpSession.class);
		servicioUsuarioMock = mock(ServicioUsuario.class);
		controladorVeterinario.setServicioUsuario(servicioUsuarioMock);
		servicioPlanesMock = mock(ServicioPlanes.class);
		servicioNotificacionesMock = mock(ServicioNotificaciones.class);
		controladorVeterinario.setServicioNotificaciones(servicioNotificacionesMock);
		servicioConsultaMock = mock(ServicioConsulta.class);
		controladorVeterinario.setServicioConsulta(servicioConsultaMock);
		servicioTurnoMock = mock(ServicioTurno.class);
		controladorVeterinario.setServicioTurno(servicioTurnoMock);
	}
	
	
	@Test
	@Rollback(true)
	@Transactional
	public void validarMostrarListadoPacientes(){
		
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("usuario")).thenReturn(usuarioMock);
		when(usuarioMock.getRol()).thenReturn("veterinario");
		
		ModelAndView modelAndView = controladorVeterinario.mostrarListadoPacientes(1L, requestMock);
		
		assertThat(modelAndView.getViewName()).isEqualTo("pacientes");
	}
}
