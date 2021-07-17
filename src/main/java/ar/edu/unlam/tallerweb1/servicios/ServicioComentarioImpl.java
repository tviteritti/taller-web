package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Comentario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioComentario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioConsulta;

@Service
@Transactional
public class ServicioComentarioImpl implements ServicioComentario{
	
	private RepositorioComentario repositorioComentario;

	@Autowired
	public ServicioComentarioImpl(RepositorioComentario repositorioComentario){
		this.repositorioComentario = repositorioComentario;
	}

	@Override
	public List<Comentario> listarComentariosPorConsulta(Long idConsulta) {
		
		return repositorioComentario.listarComentariosPorConsulta(idConsulta);
	}

}
