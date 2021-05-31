package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.Horarios;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Zona;

public interface ServicioTurno {
	
	
	List<Direccion> obtenerLocalidades(String zona);
	List<Usuario> obtenerVeterinariosPorZona(String zona);
	List<Turno> obtenerTurnos(Usuario veterinario);
	List<Turno> obtenerTurnos(String servicio);
	List<Turno> listarTurnos();
	void cancelarTurno(Long idTurno);
	
	List<Turno> listarTurnosSinTomar();
	Usuario devolverVeterinarioDeunDia(Long id_dia);
	Horarios devolverDialunes(Long id_dia);
	void tomarTurno(Long id, Usuario duenio);
	
	void generarTurnoPorIdDiaLunes(Long id);
	void generarTurnoPorIdDiaMartes(Long id);
	void generarTurnoPorIdDiaMiercoles(Long id);
	void generarTurnoPorIdDiaJueves(Long id);
	void generarTurnoPorIdDiaViernes(Long id);
	void generarTurnoPorIdDiaSabado(Long id);
	void generarTurnoPorIdDiaDomingo(Long id);
	

}
