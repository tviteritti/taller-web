package ar.edu.unlam.tallerweb1.persistencia;

import  static org.mockito.Mockito.*;

import java.text.ParseException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertNull;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.controladores.ControladorLogin;
import ar.edu.unlam.tallerweb1.controladores.ControladorLoginVeterinaria;
import ar.edu.unlam.tallerweb1.modelo.Dias;
import ar.edu.unlam.tallerweb1.modelo.Horarios;
import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.Planes;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioConsulta;
import ar.edu.unlam.tallerweb1.servicios.ServicioDias;
import ar.edu.unlam.tallerweb1.servicios.ServicioHorarios;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;
import ar.edu.unlam.tallerweb1.servicios.ServicioMascotas;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificaciones;
import ar.edu.unlam.tallerweb1.servicios.ServicioPlanes;
import ar.edu.unlam.tallerweb1.servicios.ServicioTurno;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

public class PruebaTest extends SpringTest{
	
	private ServicioUsuario servicioUsuario;
	private ServicioHorarios servicioHorarios;
	private ServicioDias servicioDias;
	private ServicioTurno servicioTurno;
	private ServicioMascotas servicioMascota;
	private ServicioPlanes servicioPlanes;
	private ServicioNotificaciones servicioNotificaciones;
	private ServicioConsulta servicioConsulta;
	private ControladorLoginVeterinaria controladorLoginVeterinaria = new ControladorLoginVeterinaria(servicioUsuario, servicioHorarios, servicioDias, servicioTurno, servicioMascota, servicioPlanes, servicioNotificaciones, servicioConsulta);
	private Usuario usuarioMock;
	private Dias diaMock;
	private Horarios horariosMock;
	private Turno turnoMock;
	private Planes planesMock;
	private Mascota mascotaMock;
	private HttpServletRequest requestMock;
	private HttpSession sessionMock;
	private ServicioUsuario servicioUsuarioMock;
	private ServicioTurno servicioTurnoMock;
	private ServicioDias servicioDiasMock;
	private ServicioHorarios servicioHorariosMock;
	private ServicioPlanes servicioPlanesMock;
	private ServicioMascotas servicioMascotasMock;
	
	
	@Before
	public void init(){
		usuarioMock = mock(Usuario.class);
		diaMock = mock(Dias.class);
		horariosMock = mock(Horarios.class);
		turnoMock = mock(Turno.class);
		planesMock = mock(Planes.class);
		mascotaMock = mock(Mascota.class);
		requestMock = mock(HttpServletRequest.class);
		sessionMock = mock(HttpSession.class);
		servicioUsuarioMock = mock(ServicioUsuario.class);
		controladorLoginVeterinaria.setServicioUsuario(servicioUsuarioMock);
		servicioTurnoMock = mock(ServicioTurno.class);
		controladorLoginVeterinaria.setServicioTurno(servicioTurnoMock);
		servicioDiasMock = mock(ServicioDias.class);
		controladorLoginVeterinaria.setServicioDias(servicioDiasMock);
		servicioHorariosMock = mock(ServicioHorarios.class);
		controladorLoginVeterinaria.setServicioHorarios(servicioHorariosMock);
		servicioPlanesMock = mock(ServicioPlanes.class);
		controladorLoginVeterinaria.setServicioPlanes(servicioPlanesMock);
		servicioMascotasMock = mock(ServicioMascotas.class);
		controladorLoginVeterinaria.setServicioMascota(servicioMascotasMock);
		
	}
	

	@Test
	@Rollback(true)
	@Transactional
	public void validarDatosUsuarioSiVaALaCuentaDelDuenio(){
		
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(usuarioMock.getRol()).thenReturn("duenio");
		
		when(servicioUsuarioMock.devolverUsuario(anyString(),anyString())).thenReturn(usuarioMock);
		when(servicioUsuarioMock.buscarUsuario(anyString(),anyString())).thenReturn(true);
		
		ModelAndView modelAndView = controladorLoginVeterinaria.validarDatosUsuario(anyString(),anyString(), requestMock);
		
		assertThat(modelAndView.getViewName()).isEqualTo("redirect:/cuentaDuenio");
	
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void validarDatosUsuarioSiVaALaCuentaDelVeterinario(){
		
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(usuarioMock.getRol()).thenReturn("veterinario");
		
		when(servicioUsuarioMock.devolverUsuario(anyString(),anyString())).thenReturn(usuarioMock);
		when(servicioUsuarioMock.buscarUsuario(anyString(),anyString())).thenReturn(true);
		
		ModelAndView modelAndView = controladorLoginVeterinaria.validarDatosUsuario(anyString(),anyString(), requestMock);
		
		assertThat(modelAndView.getViewName()).isEqualTo("redirect:/cuentaVeterinario");
	
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void validarQueElInicioSeaEnLoginVeterinaria(){
		
		ModelAndView modelAndView = controladorLoginVeterinaria.inicio();
		
		assertThat(modelAndView.getViewName()).isEqualTo("redirect:/loginVeterinaria");
	
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void validarRedirectSiHaySesionIniciadaDuenio(){
		
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("usuario")).thenReturn(usuarioMock);
		when(usuarioMock.getRol()).thenReturn("duenio");
		
		
		
		ModelAndView modelAndView = controladorLoginVeterinaria.mostrarLoginVeterinaria(requestMock);
		
		when(servicioTurnoMock.listarTurnosSinTomar()).thenReturn(anyList());
		
		assertThat(modelAndView.getViewName()).isEqualTo("redirect:/cuentaDuenio");
	
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void validarRedirectSiHaySesionIniciadaVeterinario(){
		
		
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("usuario")).thenReturn(usuarioMock);
		when(usuarioMock.getRol()).thenReturn("veterinario");
		
		
		
		ModelAndView modelAndView = controladorLoginVeterinaria.mostrarLoginVeterinaria(requestMock);
		
		when(servicioTurnoMock.listarTurnosSinTomar()).thenReturn(anyList());
		
		assertThat(modelAndView.getViewName()).isEqualTo("redirect:/cuentaVeterinario");
	
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void validarQueIniciarSesionLleveAlForm(){
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("usuario")).thenReturn(null);
		ModelAndView modelAndView = controladorLoginVeterinaria.iniciarSesion(requestMock);
		
		assertThat(modelAndView.getViewName()).isEqualTo("formUser");
	
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void validarQueSinSesionTeRedirijaAlInicio(){
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("usuario")).thenReturn(null);
		ModelAndView modelAndView = controladorLoginVeterinaria.mostrarCuentaUsuario(requestMock);
		
		assertThat(modelAndView.getViewName()).isEqualTo("redirect:/loginVeterinaria");
	
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void validarQueCerrarSesionFuncione(){
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("usuario")).thenReturn(usuarioMock);
		when(usuarioMock.getRol()).thenReturn("veterinario");
		ModelAndView modelAndView = controladorLoginVeterinaria.cerrarSesion(requestMock);
		controladorLoginVeterinaria.mostrarCuentaUsuario(requestMock);
		
		assertThat(modelAndView.getViewName()).isEqualTo("redirect:/loginVeterinaria");
		
	
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void validarVistaRegistroDuenio(){
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("usuario")).thenReturn(null);
		
		ModelAndView modelAndView = controladorLoginVeterinaria.mostrarFormRegistroUsuario(requestMock);
		
		assertThat(modelAndView.getViewName()).isEqualTo("registroUsuario");
		
		
		ModelAndView modelAndViewa = controladorLoginVeterinaria.registrarDueño(requestMock);
		
		assertThat(modelAndViewa.getViewName()).isEqualTo("registroDuenio");
		
	
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void validarVistaRegistroVeterinario(){
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("usuario")).thenReturn(null);
		
		ModelAndView modelAndView = controladorLoginVeterinaria.registrarVeterinario(requestMock);
		
		assertThat(modelAndView.getViewName()).isEqualTo("registroVeterinario");	
	}
	
	
	@Test
	@Rollback(true)
	@Transactional
	public void validarDatosVeterinarioRepassword(){
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(servicioUsuarioMock.validarPassRePass(anyString(), anyString())).thenReturn(true);
	
		ModelAndView modelAndView2 = controladorLoginVeterinaria.validarDatosVeterinario(usuarioMock, requestMock, 1L, anyString(), "asd", "asd", "asd", "asd",1L);
	
	assertThat(modelAndView2.getViewName()).isEqualTo("redirect:/horariosLunes");	
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void validarVistaRegistrarHorariosLunes(){
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("usuario")).thenReturn(null);
	
		ModelAndView modelAndView2 = controladorLoginVeterinaria.registrarHorariosLunes(requestMock);
	
	assertThat(modelAndView2.getViewName()).isEqualTo("horariosLunes");	
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void validarProcesarHorariosLunes() throws ParseException{
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("usuario")).thenReturn(null);
	
		ModelAndView modelAndView2 = controladorLoginVeterinaria.procesarHorariosLunes(1L, 1L, "asd", "as", "asd");
	
	assertThat(modelAndView2.getViewName()).isEqualTo("redirect:/horariosMartes");	
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void validarVistaGenerarTurnos() {
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("usuario")).thenReturn(null);
	
		ModelAndView modelAndView2 = controladorLoginVeterinaria.generarTurnos(requestMock);
	
	assertThat(modelAndView2.getViewName()).isEqualTo("generarTurnos");	
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void validarprocesarDatosGenerarTurno(){
		
	
		ModelAndView modelAndView2 = controladorLoginVeterinaria.procesarDatosGenerarTurno(1L,1L);
	
	assertThat(modelAndView2.getViewName()).isEqualTo("redirect:/loginVeterinaria");	
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void validarTomarUnTurno(){
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("usuario")).thenReturn(null);
	
		ModelAndView modelAndView2 = controladorLoginVeterinaria.tomarUnTurno(1L,requestMock);
	
	assertThat(modelAndView2.getViewName()).isEqualTo("formVerificarSesion");	
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void validarProcesarDatosSesion(){
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("usuario")).thenReturn(null);
		when(servicioUsuarioMock.buscarUsuario(anyString(), anyString())).thenReturn(true);
		when(servicioPlanesMock.verificarSiTienePlanVigente(usuarioMock)).thenReturn(true);
		
		ModelAndView modelAndView2 = controladorLoginVeterinaria.procesarDatosSesion(1L,"asd","asd", requestMock);
	
	assertThat(modelAndView2.getViewName()).isEqualTo("redirect:/mascotaAEligir");	
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void validarMascotaAEligir(){
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("usuario")).thenReturn(null);
		
		ModelAndView modelAndView2 = controladorLoginVeterinaria.mascotaAEligir(requestMock);
	
	assertThat(modelAndView2.getViewName()).isEqualTo("listarMascotas");	
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void validarProcesarMascota(){
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(requestMock.getSession().getAttribute("usuario")).thenReturn(usuarioMock);
		
		ModelAndView modelAndView2 = controladorLoginVeterinaria.procesarMascota(1L,1L,requestMock);
	
	assertThat(modelAndView2.getViewName()).isEqualTo("redirect:/loginVeterinaria");	
	}
	

	
	
	
	
	
	
	
}
