package oeir2161MV.note.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import oeir2161MV.note.controller.UI.UI;
import oeir2161MV.note.controller.NoteController;
import oeir2161MV.note.utils.config.Configuration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//functionalitati
//F01.	 adaugarea unei oeir2161MV.note la o anumita materie (nr. matricol, materie, nota acordata);
//F02.	 calcularea mediilor semestriale pentru fiecare elev (nume, nr. matricol),
//F03.	 afisarea elevilor coringenti, ordonati descrescator dupa numarul de materii la care nu au promovat ?i alfabetic dupa nume.


public class StartApp {

    public static void main(String[] args) throws Exception {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Configuration.class);

        NoteController ctrl = context.getBean(NoteController.class);

        if(args.length != 2){
            throw  new Exception("You must provide both files: student file and mark file path!");
        }

        ctrl.readElevi(args[0]);
        ctrl.readNote(args[1]);
        ctrl.creeazaClasa(ctrl.getElevi(), ctrl.getNote());

        UI ui = context.getBean(UI.class);
        ui.showMenu();
    }

}
