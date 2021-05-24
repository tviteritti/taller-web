package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;
import ar.edu.unlam.tallerweb1.modelo.Veterinario;

public interface RepositorioVeterinario {
	
	Veterinario consultarVeterinario (Veterinario veterinario);
	List<Veterinario> getVeterinarios();
	void registrarOMOdificarVeterinario (Veterinario veterinario);
	Veterinario getVeterinario(Long id);
	void eliminarVeterinario(Long id);

}
