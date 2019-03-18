package oeir2161MV.note.repository.interfaces;

import java.util.List;

import oeir2161MV.note.utils.exceptions.ClasaException;

import oeir2161MV.note.model.Nota;

public interface NoteRepository extends  Repository {
	
	void addNota(Nota nota) throws ClasaException;
	void readNote(String fisier);
	List<Nota> getNote();
}
