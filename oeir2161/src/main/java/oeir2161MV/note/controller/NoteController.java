package oeir2161MV.note.controller;

import java.util.HashMap;
import java.util.List;

import oeir2161MV.note.service.NoteService;
import oeir2161MV.note.utils.exceptions.ClasaException;
import oeir2161MV.note.model.Corigent;
import oeir2161MV.note.model.Elev;
import oeir2161MV.note.model.Medie;
import oeir2161MV.note.model.Nota;
import oeir2161MV.note.repository.interfaces.ClasaRepository;
import oeir2161MV.note.repository.implementations.ClasaRepositoryMock;
import oeir2161MV.note.repository.interfaces.EleviRepository;
import oeir2161MV.note.repository.implementations.EleviRepositoryMock;
import oeir2161MV.note.repository.interfaces.NoteRepository;
import oeir2161MV.note.repository.implementations.NoteRepositoryMock;

//import oeir2161MV.note.utils.ClasaException;

public class NoteController {

	public NoteController(NoteService service) {
		this.service = service;
	}
	
	public void addNota(Nota nota) throws ClasaException {
		service.addNota(nota);
	}
	
	public void addElev(Elev elev) {
		service.addElev(elev);
	}
	
	public void creeazaClasa(List<Elev> elevi, List<Nota> note) {
		service.creeazaClasa(elevi, note);
	}
	
	public List<Medie> calculeazaMedii() throws ClasaException {
		return  service.calculeazaMedii();
	}
	
	public List<Nota> getNote() {
		return service.getNote();
	}
	
	public List<Elev> getElevi() {
		return  service.getElevi();
	}
	
	public HashMap<Elev, HashMap<String, List<Double>>> getClasa() {
		return service.getClasa();
	}
	
	public void afiseazaClasa() {

	}
	
	public void readElevi(String fisier) {
		service.readElevi(fisier);
	}
	
	public void readNote(String fisier) {
		service.readNote(fisier);
	}
	
	public List<Corigent> getCorigenti() {
		return service.getCorigenti();
	}


	private NoteService service;
}
