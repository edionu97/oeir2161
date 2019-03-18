package oeir2161MV.note.repository.interfaces;

import java.util.List;

import oeir2161MV.note.model.Elev;

public interface EleviRepository extends  Repository{
	void addElev(Elev e);
	void readElevi(String fisier);
	List<Elev> getElevi();
}
