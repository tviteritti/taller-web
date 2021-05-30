package ar.edu.unlam.tallerweb1.repositorios;

import java.text.ParseException;
import java.util.Date;

import ar.edu.unlam.tallerweb1.modelo.Horarios;


public interface RepositorioHorarios {

	void registrarOMOdificarHorarios (Horarios horarios);
	Horarios registrarOMOdificarHorarios (String duracion_sesion, String hora_fin, String hora_inicio)throws ParseException;
}
