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

import ar.edu.unlam.tallerweb1.modelo.Direccion;
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


	@Override
	public List<Direccion> obtenerLocalidades(String zona) {
		
		return repositorioTurno.obtenerLocalidades(zona);
	}


	@Override
	public List<Usuario> obtenerVeterinariosPorZona(String zona) {
		
		return repositorioTurno.obtenerVeterinariosPorZona(zona);
	}


	@Override
	public void cancelarTurno(Long idTurno) {
		
		repositorioTurno.cancelarTurno(idTurno);
	}


	@Override
	public List<Turno> obtenerTurnos(Usuario veterinario) {
		
		return repositorioTurno.obtenerTurnos(veterinario);
	}


	@Override
	public List<Turno> obtenerTurnos(String servicio) {
		
		return repositorioTurno.obtenerTurnos(servicio);
	}


	@Override
	public List<Turno> listarTurnos() {
		
		return repositorioTurno.listarTurnos();
	}


	
	
	
	

	@Override
	public List<Turno> listarTurnosSinTomar() {
		return repositorioTurno.listarTurnosSinTomar();
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
	public void generarTurnoPorIdDiaLunes(Long id) {
		Horarios lunes =repositorioTurno.devolverDialunes(id);
		Usuario veterinario = repositorioTurno.devolverVeterinarioDeunDia(id);
		Integer h_inicio = (int) lunes.getHora_inicio().getTime();	/*PASO A MILISEGUNDOS*/
		Integer h_fin = (int) lunes.getHora_fin().getTime();		/*PASO A MILISEGUNDOS*/
		Integer duracion = lunes.getDuracion_sesion() * 60000;		/*PASO A MILISEGUNDOS*/
		
		
		Integer cont=h_inicio;								/*SUMA LA DURACION DE LOS TURNOS, PARA CREAR LA HORA DEL TURNO*/
		
		LocalDate fechaActual =  LocalDate.now();			/*FECHA ACTUAL*/
		
		LocalDate localDate = LocalDate.of(2016, 8, 19);	/*NO SIRVE DE NADA*/
		
		LocalDate fecha=LocalDate.now();					/*FECHA QUE PASA DE DIAS*/
		
	
		switch (fechaActual.getDayOfWeek()) {				/*DEPENDIENDO QUE FECHA ES HOY, PASO DE DIAS PARA LLEGAR AL LUNES MAS CERCANO*/
		case MONDAY:
			fecha =  LocalDate.now().plusDays(0);
			break;
		case FRIDAY:
			fecha =  LocalDate.now().plusDays(3);
			break;
		case SATURDAY:
			fecha =  LocalDate.now().plusDays(2);
			break;
		case SUNDAY:
			fecha =  LocalDate.now().plusDays(1);
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
	
		
		
		
		ZoneId defaultZoneId = ZoneId.systemDefault();		/*PASO LOCALDATE DATE*/
		Date date = Date.from(fecha.atStartOfDay(defaultZoneId).toInstant());
		
		do {
			Turno turno = new Turno();
			Date d= new Date();
			d.setTime(cont);			/*PASO DE INTEGER DATE*/
			turno.setHorario(d);
			turno.setVeterinario(veterinario);
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


	@Override
	public void generarTurnoPorIdDiaMartes(Long id) {
		Horarios martes =repositorioTurno.devolverDiaMartes(id);
		Usuario veterinario = repositorioTurno.devolverVeterinarioDeunDia(id);
		Integer h_inicio = (int) martes.getHora_inicio().getTime();	
		Integer h_fin = (int) martes.getHora_fin().getTime();		
		Integer duracion = martes.getDuracion_sesion() * 60000;		
		
		
		Integer cont=h_inicio;								
		
		LocalDate fechaActual =  LocalDate.now();			
		
		LocalDate localDate = LocalDate.of(2016, 8, 19);	
		
		LocalDate fecha=LocalDate.now();					
		
	
		switch (fechaActual.getDayOfWeek()) {				
		case MONDAY:
			fecha =  LocalDate.now().plusDays(1);
			break;
		case FRIDAY:
			fecha =  LocalDate.now().plusDays(4);
			break;
		case SATURDAY:
			fecha =  LocalDate.now().plusDays(3);
			break;
		case SUNDAY:
			fecha =  LocalDate.now().plusDays(2);
			break;
		case THURSDAY:
			fecha =  LocalDate.now().plusDays(5);
			break;
		case TUESDAY:
			fecha =  LocalDate.now().plusDays(7);
			break;
		case WEDNESDAY:
			fecha =  LocalDate.now().plusDays(6);
			break;

		default:
			break;
		}
	
		
		
		
		ZoneId defaultZoneId = ZoneId.systemDefault();		
		Date date = Date.from(fecha.atStartOfDay(defaultZoneId).toInstant());
		
		do {
			Turno turno = new Turno();
			Date d= new Date();
			d.setTime(cont);			
			turno.setHorario(d);
			turno.setVeterinario(veterinario);
			turno.setEstado(false);
			turno.setFecha(date);
			repositorioTurno.generarTurno(turno);
			cont+=duracion;
			
		}while(cont<=h_fin);
		
	}


	@Override
	public void generarTurnoPorIdDiaMiercoles(Long id) {
		Horarios miercoles =repositorioTurno.devolverDiaMiercoles(id);
		Usuario veterinario = repositorioTurno.devolverVeterinarioDeunDia(id);
		Integer h_inicio = (int) miercoles.getHora_inicio().getTime();	
		Integer h_fin = (int) miercoles.getHora_fin().getTime();		
		Integer duracion = miercoles.getDuracion_sesion() * 60000;		
		
		
		Integer cont=h_inicio;								
		
		LocalDate fechaActual =  LocalDate.now();			
		
		LocalDate localDate = LocalDate.of(2016, 8, 19);	
		
		LocalDate fecha=LocalDate.now();					
		
	
		switch (fechaActual.getDayOfWeek()) {				
		case MONDAY:
			fecha =  LocalDate.now().plusDays(2);
			break;
		case FRIDAY:
			fecha =  LocalDate.now().plusDays(5);
			break;
		case SATURDAY:
			fecha =  LocalDate.now().plusDays(4);
			break;
		case SUNDAY:
			fecha =  LocalDate.now().plusDays(3);
			break;
		case THURSDAY:
			fecha =  LocalDate.now().plusDays(6);
			break;
		case TUESDAY:
			fecha =  LocalDate.now().plusDays(1);
			break;
		case WEDNESDAY:
			fecha =  LocalDate.now().plusDays(0);
			break;

		default:
			break;
		}
	
		
		
		
		ZoneId defaultZoneId = ZoneId.systemDefault();		
		Date date = Date.from(fecha.atStartOfDay(defaultZoneId).toInstant());
		
		do {
			Turno turno = new Turno();
			Date d= new Date();
			d.setTime(cont);			
			turno.setHorario(d);
			turno.setVeterinario(veterinario);
			turno.setEstado(false);
			turno.setFecha(date);
			repositorioTurno.generarTurno(turno);
			cont+=duracion;
			
		}while(cont<=h_fin);
		
	}


	@Override
	public void generarTurnoPorIdDiaJueves(Long id) {
		Horarios jueves =repositorioTurno.devolverDiaJueves(id);
		Usuario veterinario = repositorioTurno.devolverVeterinarioDeunDia(id);
		Integer h_inicio = (int) jueves.getHora_inicio().getTime();	
		Integer h_fin = (int) jueves.getHora_fin().getTime();		
		Integer duracion = jueves.getDuracion_sesion() * 60000;		
		
		
		Integer cont=h_inicio;								
		
		LocalDate fechaActual =  LocalDate.now();			
		
		LocalDate localDate = LocalDate.of(2016, 8, 19);	
		
		LocalDate fecha=LocalDate.now();					
		
	
		switch (fechaActual.getDayOfWeek()) {				
		case MONDAY:
			fecha =  LocalDate.now().plusDays(3);
			break;
		case FRIDAY:
			fecha =  LocalDate.now().plusDays(6);
			break;
		case SATURDAY:
			fecha =  LocalDate.now().plusDays(5);
			break;
		case SUNDAY:
			fecha =  LocalDate.now().plusDays(4);
			break;
		case THURSDAY:
			fecha =  LocalDate.now().plusDays(0);
			break;
		case TUESDAY:
			fecha =  LocalDate.now().plusDays(2);
			break;
		case WEDNESDAY:
			fecha =  LocalDate.now().plusDays(1);
			break;

		default:
			break;
		}
	
		
		
		
		ZoneId defaultZoneId = ZoneId.systemDefault();		
		Date date = Date.from(fecha.atStartOfDay(defaultZoneId).toInstant());
		
		do {
			Turno turno = new Turno();
			Date d= new Date();
			d.setTime(cont);			
			turno.setHorario(d);
			turno.setVeterinario(veterinario);
			turno.setEstado(false);
			turno.setFecha(date);
			repositorioTurno.generarTurno(turno);
			cont+=duracion;
			
		}while(cont<=h_fin);
		
	}


	@Override
	public void generarTurnoPorIdDiaViernes(Long id) {
		Horarios viernes =repositorioTurno.devolverDiaViernes(id);
		Usuario veterinario = repositorioTurno.devolverVeterinarioDeunDia(id);
		Integer h_inicio = (int) viernes.getHora_inicio().getTime();	
		Integer h_fin = (int) viernes.getHora_fin().getTime();		
		Integer duracion = viernes.getDuracion_sesion() * 60000;		
		
		
		Integer cont=h_inicio;								
		
		LocalDate fechaActual =  LocalDate.now();			
		
		LocalDate localDate = LocalDate.of(2016, 8, 19);	
		
		LocalDate fecha=LocalDate.now();					
		
	
		switch (fechaActual.getDayOfWeek()) {				
		case MONDAY:
			fecha =  LocalDate.now().plusDays(4);
			break;
		case FRIDAY:
			fecha =  LocalDate.now().plusDays(0);
			break;
		case SATURDAY:
			fecha =  LocalDate.now().plusDays(6);
			break;
		case SUNDAY:
			fecha =  LocalDate.now().plusDays(5);
			break;
		case THURSDAY:
			fecha =  LocalDate.now().plusDays(1);
			break;
		case TUESDAY:
			fecha =  LocalDate.now().plusDays(3);
			break;
		case WEDNESDAY:
			fecha =  LocalDate.now().plusDays(2);
			break;

		default:
			break;
		}
	
		
		
		
		ZoneId defaultZoneId = ZoneId.systemDefault();		
		Date date = Date.from(fecha.atStartOfDay(defaultZoneId).toInstant());
		
		do {
			Turno turno = new Turno();
			Date d= new Date();
			d.setTime(cont);			
			turno.setHorario(d);
			turno.setVeterinario(veterinario);
			turno.setEstado(false);
			turno.setFecha(date);
			repositorioTurno.generarTurno(turno);
			cont+=duracion;
			
		}while(cont<=h_fin);
		
	}


	@Override
	public void generarTurnoPorIdDiaSabado(Long id) {
		Horarios sabado =repositorioTurno.devolverDiaSabado(id);
		Usuario veterinario = repositorioTurno.devolverVeterinarioDeunDia(id);
		Integer h_inicio = (int) sabado.getHora_inicio().getTime();	
		Integer h_fin = (int) sabado.getHora_fin().getTime();		
		Integer duracion = sabado.getDuracion_sesion() * 60000;		
		
		
		Integer cont=h_inicio;								
		
		LocalDate fechaActual =  LocalDate.now();			
		
		LocalDate localDate = LocalDate.of(2016, 8, 19);	
		
		LocalDate fecha=LocalDate.now();					
		
	
		switch (fechaActual.getDayOfWeek()) {				
		case MONDAY:
			fecha =  LocalDate.now().plusDays(5);
			break;
		case FRIDAY:
			fecha =  LocalDate.now().plusDays(1);
			break;
		case SATURDAY:
			fecha =  LocalDate.now().plusDays(0);
			break;
		case SUNDAY:
			fecha =  LocalDate.now().plusDays(6);
			break;
		case THURSDAY:
			fecha =  LocalDate.now().plusDays(2);
			break;
		case TUESDAY:
			fecha =  LocalDate.now().plusDays(4);
			break;
		case WEDNESDAY:
			fecha =  LocalDate.now().plusDays(3);
			break;

		default:
			break;
		}
	
		
		
		
		ZoneId defaultZoneId = ZoneId.systemDefault();		
		Date date = Date.from(fecha.atStartOfDay(defaultZoneId).toInstant());
		
		do {
			Turno turno = new Turno();
			Date d= new Date();
			d.setTime(cont);			
			turno.setHorario(d);
			turno.setVeterinario(veterinario);
			turno.setEstado(false);
			turno.setFecha(date);
			repositorioTurno.generarTurno(turno);
			cont+=duracion;
			
		}while(cont<=h_fin);
		
	}


	@Override
	public void generarTurnoPorIdDiaDomingo(Long id) {
		Horarios domingo =repositorioTurno.devolverDiaDomingo(id);
		Usuario veterinario = repositorioTurno.devolverVeterinarioDeunDia(id);
		Integer h_inicio = (int) domingo.getHora_inicio().getTime();	
		Integer h_fin = (int) domingo.getHora_fin().getTime();		
		Integer duracion = domingo.getDuracion_sesion() * 60000;		
		
		
		Integer cont=h_inicio;								
		
		LocalDate fechaActual =  LocalDate.now();			
		
		LocalDate localDate = LocalDate.of(2016, 8, 19);	
		
		LocalDate fecha=LocalDate.now();					
		
	
		switch (fechaActual.getDayOfWeek()) {				
		case MONDAY:
			fecha =  LocalDate.now().plusDays(6);
			break;
		case FRIDAY:
			fecha =  LocalDate.now().plusDays(2);
			break;
		case SATURDAY:
			fecha =  LocalDate.now().plusDays(1);
			break;
		case SUNDAY:
			fecha =  LocalDate.now().plusDays(0);
			break;
		case THURSDAY:
			fecha =  LocalDate.now().plusDays(3);
			break;
		case TUESDAY:
			fecha =  LocalDate.now().plusDays(5);
			break;
		case WEDNESDAY:
			fecha =  LocalDate.now().plusDays(4);
			break;

		default:
			break;
		}
	
		
		
		
		ZoneId defaultZoneId = ZoneId.systemDefault();		
		Date date = Date.from(fecha.atStartOfDay(defaultZoneId).toInstant());
		
		do {
			Turno turno = new Turno();
			Date d= new Date();
			d.setTime(cont);			
			turno.setHorario(d);
			turno.setVeterinario(veterinario);
			turno.setEstado(false);
			turno.setFecha(date);
			repositorioTurno.generarTurno(turno);
			cont+=duracion;
			
		}while(cont<=h_fin);
		
	}




}
