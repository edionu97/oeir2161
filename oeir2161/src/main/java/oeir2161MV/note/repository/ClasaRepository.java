package oeir2161MV.note.repository;

import oeir2161MV.note.model.Corigent;
import oeir2161MV.note.model.Elev;
import oeir2161MV.note.model.Medie;
import oeir2161MV.note.model.Nota;
import oeir2161MV.note.main.ClasaException;

import java.util.HashMap;
import java.util.List;

public interface ClasaRepository {
	
	public void creazaClasa(List<Elev> elevi, List<Nota> note);
	public HashMap<Elev, HashMap<String, List<Double>>> getClasa();
	public List<Medie> calculeazaMedii() throws ClasaException;
	public void afiseazaClasa();
	public List<Corigent> getCorigenti();
}
