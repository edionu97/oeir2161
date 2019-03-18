package oeir2161MV.note.repository.implementations;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import oeir2161MV.note.repository.interfaces.NoteRepository;
import oeir2161MV.note.utils.exceptions.ClasaException;
import oeir2161MV.note.utils.Constants;

import oeir2161MV.note.model.Nota;
import oeir2161MV.note.utils.validators.MarkValidator;

public class NoteRepositoryMock implements NoteRepository {

	public NoteRepositoryMock(MarkValidator validator) {
		this.validator = validator;
	}

	@Override
	public void addNota(Nota nota) throws ClasaException {
		validator.validate(nota);
		note.add(nota);
	}

	@Override
	public List<Nota> getNote() {
		return note;
	}
	
	public void readNote(String fisier) {
		String line;
		try(BufferedReader br = openFile(fisier)){
			while ((line = br.readLine()) != null) {
				String[] values = line.split(";");
				Nota nota = new Nota(
						Double.parseDouble(values[0]),
						values[1],
						Double.parseDouble(values[2])
				);
				note.add(nota);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private MarkValidator validator;
	private List<Nota> note = new LinkedList<>();
}
