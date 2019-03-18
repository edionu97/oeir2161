package oeir2161MV.note.repository;

import java.util.List;

import oeir2161MV.note.main.ClasaException;

import oeir2161MV.note.model.Nota;

public interface NoteRepository {
	
	public void addNota(Nota nota) throws ClasaException;
	public List<Nota> getNote(); 
	public void readNote(String fisier);
	
}
