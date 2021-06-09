package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.HistoriaClinica;
import ar.edu.unlam.tallerweb1.modelo.Mascota;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface RepositorioHistoriaClinica {
	
	List<HistoriaClinica> listarHistoriaClinicaPorMascotaYVeterinario(Mascota mascota, Usuario veterinario);
	List<HistoriaClinica> obtenerHistoriaClinica();
	void cargarHC(HistoriaClinica hc);
	void eliminarHC(HistoriaClinica hc);

}
