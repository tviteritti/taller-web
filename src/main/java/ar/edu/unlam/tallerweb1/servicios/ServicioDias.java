package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Dias;
import ar.edu.unlam.tallerweb1.modelo.Horarios;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioDias {

	void registrarOModificarDiasLunes(Horarios horarios, Long id_dia);
	void registrarOModificarDiasMartes(Horarios horarios, Long id_dia);
	void registrarOModificarDiasMiercoles(Horarios horarios, Long id_dia);
	void registrarOModificarDiasJueves(Horarios horarios, Long id_dia);
	void registrarOModificarDiasViernes(Horarios horarios, Long id_dia);
	void registrarOModificarDiasSabado(Horarios horarios, Long id_dia);
	void registrarOModificarDiasDomingo(Horarios horarios, Long id_dia);
	void registrarOModificarDiasVeterinario(Usuario veterinario, Long id_dia);
	void registrarOModificarDias(Dias dia);
}
