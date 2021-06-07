package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.HistoriaClinica;
import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.Usuario;


public interface ServicioHistoriaClinica {
	
	List<HistoriaClinica> listarHistoriaClinicaPorMascotaYVeterinario(Mascota mascota, Usuario veterinario);
	List<HistoriaClinica> buscarHCPorMascota(Long idMascota);
	List<HistoriaClinica> buscarHCPorMascotaYDuenio(Mascota mascota, Usuario duenio);
	void cargarHC(Usuario veterinario, Mascota mascota, String fecha, String diagnostico, String tratamiento);

}
