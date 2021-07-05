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
import ar.edu.unlam.tallerweb1.modelo.HistoriaClinica;
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
	private HistoriaClinica historiaClinicaMock;
	private HttpServletRequest requestMock;
	private HttpSession sessionMock;
	private ServicioUsuario servicioUsuarioMock;
	private ServicioPlanes servicioPlanesMock;
	private ServicioMascotas servicioMascotasMock;
	private ServicioNotificaciones servicioNotificacionesMock;
	private ServicioConsulta servicioConsultaMock;
	private ServicioTurno servicioTurnoMock;
	private ServicioHistoriaClinica servicioHistoriaClinicaMock;
	
	


	@Before
	public void init(){
		usuarioMock = mock(Usuario.class);
		turnoMock = mock(Turno.class);
		mascotaMock = mock(Mascota.class);
		notificacionesMock = mock(Notificacion.class);
		consultaMock = mock(Consulta.class);
		turnoMock = mock(Turno.class);
		historiaClinicaMock = mock(HistoriaClinica.class);
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
		servicioHistoriaClinicaMock = mock(ServicioHistoriaClinica.class);
		controladorVeterinario.setServicioHistoriaClinica(servicioHistoriaClinicaMock);
		servicioMascotasMock = mock(ServicioMascotas.class);
		controladorVeterinario.setServicioMascotas(servicioMascotasMock);
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
	
	@Test
	@Rollback(true)
	@Transactional
	public void validarMostrarHistoriaClinica(){
		
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("usuario")).thenReturn(usuarioMock);
		when(usuarioMock.getRol()).thenReturn("veterinario");
		
		ModelAndView modelAndView = controladorVeterinario.mostrarHistoriaClinica(1L, 1L,1L,requestMock);
		
		assertThat(modelAndView.getViewName()).isEqualTo("historiaClinica");
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void validarCargarHistoriaClinica(){
		
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("usuario")).thenReturn(usuarioMock);
		when(usuarioMock.getRol()).thenReturn("veterinario");
		
		ModelAndView modelAndView = controladorVeterinario.cargarHistoriaClinica(1L, 1L,1L,requestMock);
		
		assertThat(modelAndView.getViewName()).isEqualTo("cargarHC");
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void validarActualizarHistoriaClinica(){
		
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("usuario")).thenReturn(usuarioMock);
		when(usuarioMock.getRol()).thenReturn("veterinario");
		
		ModelAndView modelAndView = controladorVeterinario.actualizarHistoriaClinica(1L, 1L,1L,requestMock);
		
		assertThat(modelAndView.getViewName()).isEqualTo("actualizarHC");
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void validarGuardarHistoriaClinica(){
		
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("usuario")).thenReturn(usuarioMock);
		when(usuarioMock.getRol()).thenReturn("veterinario");
		
		ModelAndView modelAndView = controladorVeterinario.guardarHistoriaClinica(1L, 1L, 1L, "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", "asd", requestMock);
		
		assertThat(modelAndView.getViewName()).isEqualTo("historiaClinica");
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void validarVerConsultas(){
		
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("usuario")).thenReturn(usuarioMock);
		when(usuarioMock.getRol()).thenReturn("veterinario");
		
		ModelAndView modelAndView = controladorVeterinario.verConsultas(1L,requestMock);
		
		assertThat(modelAndView.getViewName()).isEqualTo("consultasUsuarios");
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void validarResponderConsultas(){
		
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("usuario")).thenReturn(usuarioMock);
		when(usuarioMock.getRol()).thenReturn("veterinario");
		when(servicioConsultaMock.buscarConsulta(1L)).thenReturn(consultaMock);
		when(consultaMock.getUsuario()).thenReturn(usuarioMock);
		
		ModelAndView modelAndView = controladorVeterinario.responderConsultas(1L,"asd",1L,"asd",requestMock);
		
		assertThat(modelAndView.getViewName()).isEqualTo("consultasUsuarios");
	}
	
	
	
}
