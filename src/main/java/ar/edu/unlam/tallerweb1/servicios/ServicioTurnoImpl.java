package ar.edu.unlam.tallerweb1.servicios;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Horarios;
import ar.edu.unlam.tallerweb1.modelo.Turno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import ar.edu.unlam.tallerweb1.modelo.Zona;

import ar.edu.unlam.tallerweb1.repositorios.RepositorioTurno;


@Service
@Transactional
public class ServicioTurnoImpl implements ServicioTurno {
	
	
	private RepositorioTurno repositorioTurno;


	@Autowired
	public ServicioTurnoImpl( RepositorioTurno repositorioTurno){
		
		this.repositorioTurno = repositorioTurno;
	}


//	@Override
//	public List<Localidad> obtenerLocalidades(String zona) {
//		
//		return repositorio.obtenerLocalidades(zona);
//	}


//	@Override
//	public List<Veterinario> obtenerVeterinariosPorZona(String zona) {
//		
//		return repositorioTurno.obtenerVeterinariosPorZona(zona);
//	}


	@Override
	public void cancelarTurno(Long idTurno) {
		
		repositorioTurno.cancelarTurno(idTurno);
	}


//	@Override
//	public List<Turno> obtenerTurnos(Veterinario veterinario) {
//		
//		return repositorioTurno.obtenerTurnos(veterinario);
//	}


	@Override
	public List<Turno> obtenerTurnos(String servicio) {
		
		return repositorioTurno.obtenerTurnos(servicio);
	}


	@Override
	public List<Turno> listarTurnos() {
		
		return repositorioTurno.listarTurnos();
	}


	
	



	@Override
	public Usuario devolverVeterinarioDeunDia(Long id_dia) {
		return repositorioTurno.devolverVeterinarioDeunDia(id_dia);
	}


	@Override
	public Horarios devolverDialunes(Long id_dia) {
		return repositorioTurno.devolverDialunes(id_dia);
	}


	@Override
	public void generarTurnoPorIdDia(Long id) {
		Horarios lunes =repositorioTurno.devolverDialunes(id);
		Usuario veterinario = repositorioTurno.devolverVeterinarioDeunDia(id);
		Integer h_inicio = (int) lunes.getHora_inicio().getTime();
		Integer h_fin = (int) lunes.getHora_fin().getTime();
		Integer duracion = lunes.getDuracion_sesion() * 60000;
		
		
		Integer cont=h_inicio;
		
		LocalDate fechaActual =  LocalDate.now();
		
		LocalDate localDate = LocalDate.of(2016, 8, 19);	
		
		LocalDate fecha=LocalDate.now();
		
	
		switch (fechaActual.getDayOfWeek()) {
		case MONDAY:
			fecha =  LocalDate.now().plusDays(0);
			break;
		case FRIDAY:
			fecha =  LocalDate.now().plusDays(3);
			break;
		case SATURDAY:
			fecha =  LocalDate.now().plusDays(1);
			break;
		case SUNDAY:
			fecha =  LocalDate.now().plusDays(2);
			break;
		case THURSDAY:
			fecha =  LocalDate.now().plusDays(4);
			break;
		case TUESDAY:
			fecha =  LocalDate.now().plusDays(6);
			break;
		case WEDNESDAY:
			fecha =  LocalDate.now().plusDays(5);
			break;

		default:
			break;
		}
	
		
		
		
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(fecha.atStartOfDay(defaultZoneId).toInstant());
		
		do {
			Turno turno = new Turno();
			turno.setVeterinario(veterinario);
			Date d= new Date();
			d.setTime(cont);
			turno.setHorario(d);
			turno.setEstado(false);
			turno.setFecha(date);
			repositorioTurno.generarTurno(turno);
			cont+=duracion;
			
		}while(cont<=h_fin);
		
	}


	@Override
	public void tomarTurno(Long id, Usuario duenio) {
		repositorioTurno.tomarTurno(id, duenio);
		
	}

}
