package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface RepositorioVeterinario {
	
	List<Usuario> getVeterinarios();
	void registrarOMOdificarVeterinario (Usuario veterinario);
	Usuario getVeterinario(Long id);
	void eliminarVeterinario(Long id);
	

}
