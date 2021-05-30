package ar.edu.unlam.tallerweb1.servicios;

import java.text.ParseException;
import java.util.Date;

import ar.edu.unlam.tallerweb1.modelo.Horarios;

public interface ServicioHorarios {

	void registrarOMOdificarHorarios (Horarios horarios);
	Integer restarHorafinalInicial (Horarios horarios);
	Horarios registrarOMOdificarHorarios (String duracion_sesion, String hora_fin, String hora_inicio)throws ParseException;
}
