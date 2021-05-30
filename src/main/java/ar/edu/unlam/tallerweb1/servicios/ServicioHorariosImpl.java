package ar.edu.unlam.tallerweb1.servicios;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Horarios;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioHorarios;

@Service("ServicioHorarios")
@Transactional
public class ServicioHorariosImpl implements ServicioHorarios{

	private RepositorioHorarios servicioHorariosDao;

	@Autowired
	public ServicioHorariosImpl(RepositorioHorarios servicioHorariosDao){
		this.servicioHorariosDao = servicioHorariosDao;
	}
	
	@Override
	public void registrarOMOdificarHorarios(Horarios horarios) {
		servicioHorariosDao.registrarOMOdificarHorarios(horarios);
		
	}
	
	@Override
	public Integer restarHorafinalInicial(Horarios horarios) {
		Integer horaFin = (int) horarios.getHora_fin().getTime();
		Integer horaInicio = (int) horarios.getHora_inicio().getTime();
		Integer horasTrabajadas = horaFin - horaInicio;
		horasTrabajadas = horasTrabajadas/(1000*60);
		
		return horasTrabajadas;
	}

	@Override
	public Horarios registrarOMOdificarHorarios(String duracion_sesion, String hora_fin, String hora_inicio) throws ParseException{
		return servicioHorariosDao.registrarOMOdificarHorarios(duracion_sesion, hora_fin, hora_inicio);
		
	}
	
}
