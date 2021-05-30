package ar.edu.unlam.tallerweb1.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Dias;
import ar.edu.unlam.tallerweb1.modelo.Horarios;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDias;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioHorarios;

@Service("ServicioDias")
@Transactional
public class ServicioDiasImpl implements ServicioDias{

	private RepositorioDias servicioDiasDao;

	@Autowired
	public ServicioDiasImpl(RepositorioDias servicioDiasDao){
		this.servicioDiasDao = servicioDiasDao;
	}
	
	@Override
	public void registrarOModificarDiasLunes(Horarios horarios, Long id_dia) {
		servicioDiasDao.registrarOModificarDiasLunes(horarios, id_dia);
		
	}

	@Override
	public void registrarOModificarDiasMartes(Horarios horarios, Long id_dia) {
		servicioDiasDao.registrarOModificarDiasMartes(horarios, id_dia);
		
	}

	@Override
	public void registrarOModificarDiasMiercoles(Horarios horarios, Long id_dia) {
		servicioDiasDao.registrarOModificarDiasMiercoles(horarios, id_dia);
		
	}

	@Override
	public void registrarOModificarDiasJueves(Horarios horarios, Long id_dia) {
		servicioDiasDao.registrarOModificarDiasJueves(horarios, id_dia);
		
	}

	@Override
	public void registrarOModificarDiasViernes(Horarios horarios, Long id_dia) {
		servicioDiasDao.registrarOModificarDiasViernes(horarios, id_dia);
		
	}

	@Override
	public void registrarOModificarDiasSabado(Horarios horarios, Long id_dia) {
		servicioDiasDao.registrarOModificarDiasSabado(horarios, id_dia);
		
	}

	@Override
	public void registrarOModificarDiasDomingo(Horarios horarios, Long id_dia) {
		servicioDiasDao.registrarOModificarDiasDomingo(horarios, id_dia);
		
	}

	@Override
	public void registrarOModificarDiasVeterinario(Usuario veterinario, Long id_dia) {
		servicioDiasDao.registrarOModificarDiasVeterinario(veterinario, id_dia);
		
	}

	@Override
	public void registrarOModificarDias(Dias dia) {
		servicioDiasDao.registrarOModificarDias(dia);
		
	}

}
