package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Consulta;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioConsulta;

@Service
@Transactional
public class ServicioConsultaImpl implements ServicioConsulta {
	
	private RepositorioConsulta repositorioConsulta;

	@Autowired
	public ServicioConsultaImpl(RepositorioConsulta repositorioConsulta){
		this.repositorioConsulta = repositorioConsulta;
	}

	@Override
	public void cargarConsulta(Consulta consulta) {
		repositorioConsulta.cargarConsulta(consulta);
	}

	@Override
	public List<Consulta> listarConsultaPorDuenio(Long idDuenio) {
		
		List<Consulta> consultas=  repositorioConsulta.listarConsultaPorDuenio(idDuenio);
		
		for(Consulta consulta: consultas) {
				
				if(  (consulta.getAsunto())==null && (consulta.getDescripcion())==null ) {
					
					repositorioConsulta.eliminarConsultasNulas(consulta);
					
				}
				
			}
		
		
		return consultas;
	}

	@Override
	public List<Consulta> listarConsultas() {
		
		List<Consulta> consultas=  repositorioConsulta.listarConsultas();
		
		for(Consulta consulta: consultas) {
				
				if(  (consulta.getAsunto())==null && (consulta.getDescripcion())==null ){
					
					repositorioConsulta.eliminarConsultasNulas(consulta);
					
				}
				
			}
		
		return consultas;
	}



	@Override
	public Consulta buscarConsulta(Long idConsulta) {
		
		return repositorioConsulta.buscarConsulta(idConsulta);
	}

	@Override
	public void agregarComentario(Long idConsulta , String comentario, String userRespuesta) {
		
		repositorioConsulta.agregarComentario(idConsulta, comentario, userRespuesta);
		
	}

	@Override
	public void eliminarConsultasNulas(Consulta consulta) {
		
		repositorioConsulta.eliminarConsultasNulas(consulta);
		
	}

	@Override
	public void cargarNotificacion(Notificacion notificacion) {
		
		repositorioConsulta.cargarNotificacion(notificacion);
		
	}

	@Override
	public List<Notificacion> listarNotificacionesPorDuenio(Long idDuenio) {
		
		return repositorioConsulta.listarNotificacionesPorDuenio(idDuenio);
	}
	
	
	
	

}
