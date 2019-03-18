package oeir2161MV.note.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//import oeir2161MV.note.utils.ClasaException;

import oeir2161MV.note.model.Corigent;
import oeir2161MV.note.model.Medie;

import oeir2161MV.note.controller.NoteController;

//functionalitati
//F01.	 adaugarea unei oeir2161MV.note la o anumita materie (nr. matricol, materie, nota acordata);
//F02.	 calcularea mediilor semestriale pentru fiecare elev (nume, nr. matricol),
//F03.	 afisarea elevilor coringenti, ordonati descrescator dupa numarul de materii la care nu au promovat ?i alfabetic dupa nume.


public class StartApp {


    public static void main(String[] args) throws ClasaException {
        NoteController ctrl = new NoteController();

        List<Medie> medii;
        List<Corigent> corigenti;

        ctrl.readElevi(args[0]);
        ctrl.readNote(args[1]);
        ctrl.creeazaClasa(ctrl.getElevi(), ctrl.getNote());

        boolean gasit = false;
        while (!gasit) {
            System.out.println("1. Adaugare Nota");
            System.out.println("2. Calculeaza medii");
            System.out.println("3. Elevi corigenti");
            System.out.println("4. Iesire");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            try {
                int option = Integer.parseInt(br.readLine());

                switch (option) {
                    case 1: {
                        break;
                    }
                    case 2: {
                        medii = ctrl.calculeazaMedii();
                        for (Medie medie : medii)
                            System.out.println(medie);
                        break;
                    }

                    case 3: {
                        corigenti = ctrl.getCorigenti();
                        for (Corigent corigent : corigenti)
                            System.out.println(corigent);
                        break;
                    }

                    case 4: {
                        gasit = true;
                        break;
                    }

                    default: {
                        System.out.println("Introduceti o optiune valida!");
                    }
                }

            } catch (NumberFormatException | IOException e) {
                e.printStackTrace();
            }
        }
    }

}
