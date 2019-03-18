package oeir2161MV.note.model;

public class Medie {

	/**
	 * @return the elev
	 */
	public Elev getElev() {
		return elev;
	}

	/**
	 * @param elev the elev to set
	 */
	public void setElev(Elev elev) {
		this.elev = elev;
	}

	/**
	 * @return the medie
	 */
	public double getMedie() {
		return medie;
	}

	/**
	 * @param medie the medie to set
	 */
	public void setMedie(double medie) {
		this.medie = medie;
	}
	
	public String toString() {
		return this.elev.getNume() + " -> " + this.medie;
	}

	private Elev elev;
	private double medie ;
}
