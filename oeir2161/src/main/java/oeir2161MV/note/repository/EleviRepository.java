package oeir2161MV.note.repository;

import java.util.List;

import oeir2161MV.note.model.Elev;

public interface EleviRepository {
	public void addElev(Elev e);
	public List<Elev> getElevi();
	public void readElevi(String fisier);
}
