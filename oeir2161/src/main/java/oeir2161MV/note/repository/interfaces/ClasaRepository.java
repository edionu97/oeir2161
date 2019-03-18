package oeir2161MV.note.repository.interfaces;

import oeir2161MV.note.model.Corigent;
import oeir2161MV.note.model.Elev;
import oeir2161MV.note.model.Medie;
import oeir2161MV.note.model.Nota;
import oeir2161MV.note.utils.exceptions.ClasaException;

import java.util.HashMap;
import java.util.List;

public interface ClasaRepository  extends  Repository{
	
	void creazaClasa(List<Elev> elevi, List<Nota> note);

	HashMap<Elev, HashMap<String, List<Double>>> getClasa();
}
