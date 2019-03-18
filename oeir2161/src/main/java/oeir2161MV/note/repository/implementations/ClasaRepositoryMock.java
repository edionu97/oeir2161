package oeir2161MV.note.repository.implementations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import oeir2161MV.note.repository.interfaces.ClasaRepository;
import oeir2161MV.note.utils.exceptions.ClasaException;
import oeir2161MV.note.utils.Constants;

import oeir2161MV.note.model.Corigent;
import oeir2161MV.note.model.Elev;
import oeir2161MV.note.model.Medie;
import oeir2161MV.note.model.Nota;

public class ClasaRepositoryMock implements ClasaRepository {

	public ClasaRepositoryMock() {
	}

	@Override
	public void creazaClasa(List<Elev> elevi, List<Nota> note) {
		clasa = new HashMap<>();
		List<String> materii = new LinkedList<>();
		for(Nota nota : note) {
			if(materii.contains(nota.getMaterie())) {
				continue;
			}
			materii.add(nota.getMaterie());
		}

		for (Elev elev : elevi) {
			HashMap<String, List<Double>> situatie = new HashMap<>();
			for(String materie : materii) {
				List<Double> noteMaterie = new LinkedList<>();
				for(Nota nota : note) {
					if (nota.getMaterie().equals(materie) && nota.getNrmatricol() == elev.getNrmatricol()) {
						noteMaterie.add(nota.getNota());
					}
				}
				situatie.put(materie, noteMaterie);
			}

			clasa.put(elev, situatie);
		}
		
	}

	@Override
	public HashMap<Elev, HashMap<String, List<Double>>> getClasa() {
		return clasa;
	}

	private HashMap<Elev, HashMap<String, List<Double>>> clasa;
}
