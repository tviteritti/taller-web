package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Veterinario;
import ar.edu.unlam.tallerweb1.modelo.Zona;

public interface RepositorioTurno {
	
	List<Veterinario> obtenerVeterinariosPorZona(String zona);

}
