package ar.edu.unlam.tallerweb1.persistencia;

import  static org.mockito.Mockito.*;


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
	private HttpServletRequest requestMock;
	private HttpSession sessionMock;
	private ServicioUsuario servicioUsuarioMock;
	private ServicioTurno servicioTurnoMock;
	private ServicioDias servicioDiasMock;

	
	
	@Before
	public void init(){
		usuarioMock = mock(Usuario.class);
		requestMock = mock(HttpServletRequest.class);
		sessionMock = mock(HttpSession.class);
		servicioUsuarioMock = mock(ServicioUsuario.class);
		controladorLoginVeterinaria.setServicioUsuario(servicioUsuarioMock);
		servicioTurnoMock = mock(ServicioTurno.class);
		controladorLoginVeterinaria.setServicioTurno(servicioTurnoMock);
		servicioDiasMock = mock(ServicioDias.class);
		controladorLoginVeterinaria.setServicioDias(servicioDiasMock);
		
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
	
	

	
	
	
	
	
}
