package oeir2161MV.note.repository.implementations;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import oeir2161MV.note.model.Elev;
import oeir2161MV.note.repository.interfaces.EleviRepository;

public class EleviRepositoryMock implements EleviRepository {

	public EleviRepositoryMock() {
		elevi = new LinkedList<>();
	}
	
	@Override
	public void addElev(Elev e) {
		elevi.add(e);
	}

	@Override
	public List<Elev> getElevi() {
		return elevi;
	}
	
	@Override
	public void readElevi(String fisier) {

		String line;
		try (BufferedReader br = openFile(fisier);){
			while ((line = br.readLine()) != null) {
				String[] values = line.split(";");
				Elev elev = new Elev(
						Integer.parseInt(values[0]),
						values[1]
				);
				elevi.add(elev);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<Elev> elevi;

}
