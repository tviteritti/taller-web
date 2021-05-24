package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Veterinario;

public interface ServicioVeterinario {
	
	Veterinario consultarVeterinario (Veterinario veterinario);
	List<Veterinario> getVeterinarios();
	void registrarOMOdificarVeterinario (Veterinario veterinario);
	Veterinario getVeterinario(Long id);
	void eliminarVeterinario(Long id);

}
