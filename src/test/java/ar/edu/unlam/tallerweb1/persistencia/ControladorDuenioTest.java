package ar.edu.unlam.tallerweb1.persistencia;

import static org.mockito.Mockito.mock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.controladores.ControladorDuenio;
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
	private HttpServletRequest requestMock;
	private HttpSession sessionMock;
	private ServicioUsuario servicioUsuarioMock;
	private ServicioPlanes servicioPlanesMock;
	private ServicioMascotas servicioMascotasMock;



	@Before
	public void init(){
		usuarioMock = mock(Usuario.class);
		turnoMock = mock(Turno.class);
		mascotaMock = mock(Mascota.class);
		requestMock = mock(HttpServletRequest.class);
		sessionMock = mock(HttpSession.class);
		servicioUsuarioMock = mock(ServicioUsuario.class);
		controladorDuenio.setServicioDuenio(servicioUsuarioMock);
		servicioPlanesMock = mock(ServicioPlanes.class);
		controladorDuenio.setServicioPlanes(servicioPlanesMock);
		servicioMascotasMock = mock(ServicioMascotas.class);
		controladorDuenio.setServicioMascota(servicioMascotasMock);
		
	}


}