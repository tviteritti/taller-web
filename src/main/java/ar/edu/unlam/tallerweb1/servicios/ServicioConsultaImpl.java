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
	public List<Consulta> listarConsultaPorUsuario(Long idUsuario) {
		
		List<Consulta> consultas=  repositorioConsulta.listarConsultaPorUsuario(idUsuario);
		
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
	public void guardarRespuesta(Long idConsulta , Consulta respuesta, String userRespuesta) {
		
		repositorioConsulta.guardarRespuesta(idConsulta, respuesta, userRespuesta);
		
	}

	@Override
	public void eliminarConsultasNulas(Consulta consulta) {
		
		repositorioConsulta.eliminarConsultasNulas(consulta);
		
	}


}
