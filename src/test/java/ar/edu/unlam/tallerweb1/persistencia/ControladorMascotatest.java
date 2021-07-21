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
import ar.edu.unlam.tallerweb1.controladores.ControladorMascota;
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

public class ControladorMascotatest extends SpringTest{

	private ServicioUsuario servicioDuenio;
	private ServicioMascotas servicioMascota;
	private ServicioNotificaciones servicioNotificaciones;
	private ControladorMascota controladorMascota = new ControladorMascota(servicioDuenio, servicioMascota);
	private Usuario usuarioMock;
	private Turno turnoMock;
	private Mascota mascotaMock;
	private HttpServletRequest requestMock;
	private HttpSession sessionMock;
	private ServicioUsuario servicioUsuarioMock;
	private ServicioMascotas servicioMascotasMock;
	
	


	@Before
	public void init(){
		usuarioMock = mock(Usuario.class);
		mascotaMock = mock(Mascota.class);
		requestMock = mock(HttpServletRequest.class);
		sessionMock = mock(HttpSession.class);
		servicioUsuarioMock = mock(ServicioUsuario.class);
		controladorMascota.setServicioDuenio(servicioUsuarioMock);
		servicioMascotasMock = mock(ServicioMascotas.class);
		controladorMascota.setServicioMascota(servicioMascotasMock);
	}
	
	
	@Test
	@Rollback(true)
	@Transactional
	public void validarVerPerfilMascota(){
		
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("usuario")).thenReturn(usuarioMock);
		when(usuarioMock.getRol()).thenReturn("duenio");
		
		ModelAndView modelAndView = controladorMascota.verPerfilMascota(1L,requestMock);
		
		assertThat(modelAndView.getViewName()).isEqualTo("perfilMascota");
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void validarModificarPerfilMascota(){
		
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("usuario")).thenReturn(usuarioMock);
		when(usuarioMock.getRol()).thenReturn("duenio");
		
		ModelAndView modelAndView = controladorMascota.modificarPerfilMascota(1L, 1L, "asd", null, "asd", requestMock);
		
		assertThat(modelAndView.getViewName()).isEqualTo("redirect:/perfilMiMascota");
	}
	
	
	
	
	
	
	
	
}
