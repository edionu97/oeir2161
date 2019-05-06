package oeir2161MV.note.service;

import oeir2161MV.note.model.Corigent;
import oeir2161MV.note.model.Elev;
import oeir2161MV.note.model.Medie;
import oeir2161MV.note.model.Nota;
import oeir2161MV.note.repository.interfaces.ClasaRepository;
import oeir2161MV.note.repository.interfaces.EleviRepository;
import oeir2161MV.note.repository.interfaces.NoteRepository;
import oeir2161MV.note.utils.Constants;
import oeir2161MV.note.utils.exceptions.ClasaException;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class NoteService {

    public NoteService(NoteRepository note, ClasaRepository clasa, EleviRepository elevi) {
        this.note = note;
        this.clasa = clasa;
        this.elevi = elevi;
    }

    public void readNote(String fisier) {
        note.readNote((this.fisier = fisier));
    }

    public void readElevi(String fisier) {
        elevi.readElevi(fisier);
    }

    public void addElev(Elev elev) {
        elevi.addElev(elev);
    }

    public void addNota(Nota nota) throws ClasaException {
        note.addNota(nota);
    }

    public List<Nota> getNote() {
        return note.getNote();
    }

    public List<Elev> getElevi() {
        return elevi.getElevi();
    }

    public void creeazaClasa(List<Elev> elevi, List<Nota> note) {
        clasa.creazaClasa(elevi, note);
    }

    public HashMap<Elev, HashMap<String, List<Double>>> getClasa() {
        return clasa.getClasa();
    }

    public List<Corigent> getCorigenti() throws ClasaException {
        List<Corigent> corigenti = new ArrayList<>();

        if (clasa.getClasa().isEmpty()) {
            throw new  ClasaException("Clasa invalida!");
        }

        for (Elev elev : clasa.getClasa().keySet()) {
            Corigent corigent = new Corigent(elev.getNume(), 0);
            for (String materie : clasa.getClasa().get(elev).keySet()) {
                List<Double> noteElev = clasa.getClasa().get(elev).get(materie);
                int nrNote = noteElev.size();
                double suma = 0;
                int i = 0;
                if (nrNote > 0) {
                    while (i < nrNote) {
                        double nota = noteElev.get(i);
                        suma += nota;
                        i++;
                    }
                    double media = suma / i;
                    if (media < 4.5) {
                        corigent.setNrMaterii(corigent.getNrMaterii() + 1);
                    }
                }
            }


            if (corigent.getNrMaterii() > 0) {
                int i = 0;
                while (i < corigenti.size() && corigenti.get(i).getNrMaterii() < corigent.getNrMaterii())
                    i++;
                if (i != corigenti.size() && corigenti.get(i).getNrMaterii() == corigent.getNrMaterii()) {
                    while (i < corigenti.size() && corigenti.get(i).getNrMaterii() == corigent.getNrMaterii() && corigenti.get(i).getNumeElev().compareTo(corigent.getNumeElev()) < 0)
                        i++;
                    corigenti.add(i, corigent);
                } else
                    corigenti.add(i, corigent);
            }
        }


        return corigenti.stream().sorted((x,y)->
            y.getNrMaterii() - x.getNrMaterii() == 0 ? x.getNumeElev().compareTo(y.getNumeElev()) : y.getNrMaterii() - x.getNrMaterii()
        ).collect(Collectors.toList());
    }

    public List<Medie> calculeazaMedii() throws ClasaException {

        List<Medie> medii = new LinkedList<>();

        if (clasa.getClasa() == null || clasa.getClasa().isEmpty()) {
            throw new ClasaException(Constants.emptyRepository);
        }

        for (Elev elev : clasa.getClasa().keySet()) {

            Medie medie = new Medie();
            medie.setElev(elev);

           medie.setMedie(
                   calculateMedie(elev)
           );

            medii.add(medie);
        }

        return medii;
    }


    public int calculateMedie(final Elev elev){

        int nrMaterii = 0; // (1)
        double sumaMedii = 0;

        for (String materie : clasa.getClasa().get(elev).keySet()) {// <2>
            nrMaterii++; //3
            List<Double> noteElev = clasa.getClasa().get(elev).get(materie);
            int nrNote = noteElev.size();
            int i = 0;
            double suma = 0;
            if (nrNote > 0) { // <4>
                while (i < nrNote) { // <5>
                    double nota = noteElev.get(i); // (6)
                    suma += nota;
                    i++;
                }

                if(suma / i < 4.5){ // <7>
                    nrMaterii  = 0; // (8)
                    break;
                }
                sumaMedii = sumaMedii + suma / i; // (9)
            }
        }


            if (nrMaterii > 0 && sumaMedii / nrMaterii >= 4.5) { // <10>
           return (int)sumaMedii / nrMaterii; // (11)
        }

        return 0; // (12)
    } // (13)

    public void afiseazaClasa() {
        for (Elev elev : clasa.getClasa().keySet()) {
            System.out.println(elev);
            for (String materie : clasa.getClasa().get(elev).keySet()) {
                System.out.println(materie);
                for (double nota : clasa.getClasa().get(elev).get(materie))
                    System.out.print(nota + " ");
            }
        }
    }

    public void saveInFile(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fisier, false))){
            note.getNote().forEach(mark->{
                try {
                    writer.write(
                            String.format(
                                    "%s;%s;%s\n",
                                    mark.getNrmatricol()+"",
                                    mark.getMaterie(),
                                    mark.getNota()+""
                            )
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private String fisier;
    private NoteRepository note;
    private ClasaRepository clasa;
    private EleviRepository elevi;
}
