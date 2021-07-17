package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Comentario;


public interface RepositorioComentario {
	
	List<Comentario> listarComentariosPorConsulta(Long idConsulta);

}
