package ar.edu.unlam.tallerweb1.servicios;

import java.util.Date;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.Horarios;
import ar.edu.unlam.tallerweb1.modelo.Localidad;
import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Voto;
import ar.edu.unlam.tallerweb1.modelo.Zona;

public interface ServicioTurno {
	
	
	List<Localidad> obtenerLocalidadesPorZona(String zona);
	List<Usuario> obtenerVeterinariosPorZona(String zona);
	
	Turno obtenerTurno(Long id);
	List<Turno> buscarTurnoPorDuenio(Long id);
	List<Turno> buscarTurnoPorVeterinario(Long id);
	List<Turno> obtenerTurnosPorVeterinario(Usuario veterinario);
	List<Turno> obtenerTurnosPorEspecialidad(String servicio);
	List<Turno> obtenerTurnosPorEspecialidadZonaYVeterinario(String servicio, String zona, Usuario veterinario);
	List<Turno> buscarTurnoPorUsuario(Long id);
	
	
	List<Mascota> obtenerMascotasPorTurno(Long idVeterinario);
	Usuario devolverVeterinarioDeunTurno(Long id_turno);

	
	Boolean comprobarTurnoExistente(Usuario duenio,Mascota mascota, Usuario veterinario);
	Boolean comprobarTurnoExistente(Mascota mascota, Usuario veterinario);
	//List<Usuario> obtenerVeterinarioPorTurno(Turno turno);
	
	List<Turno> listarTurnos();
	void cancelarTurno(Long idTurno);
	void asignarTurno(Long idTurno, Mascota mascota, Usuario duenio);
	void cargarTurno(Turno turno);
	
	
	List<Turno> listarTurnosSinTomar();
	Usuario devolverVeterinarioDeunDia(Long id_dia);
	Horarios devolverDialunes(Long id_dia);
	void tomarTurno(Long id_turno, Long id_mascota);
	
	void generarTurnoPorIdDiaLunes(Long id);
	void generarTurnoPorIdDiaMartes(Long id);
	void generarTurnoPorIdDiaMiercoles(Long id);
	void generarTurnoPorIdDiaJueves(Long id);
	void generarTurnoPorIdDiaViernes(Long id);
	void generarTurnoPorIdDiaSabado(Long id);
	void generarTurnoPorIdDiaDomingo(Long id);
	
	Date devolverFechaDeUnTurno(Long id);
	Date devolverHorarioaDeUnTurno(Long id);
	
	List<Turno> getTurnosSinVotosDuenio(Long id_duenio);
	List<Turno> getTurnosConVotosDuenio(Long id_duenio);
	
	List<Turno> buscarTurnoTomadoPorVeterinario(Long id);

}
