package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Consulta;
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
		
		return repositorioConsulta.listarConsultaPorDuenio(idDuenio);
	}

	@Override
	public List<Consulta> listarConsultas() {
		
		return repositorioConsulta.listarConsultas();
	}
	
	

}
