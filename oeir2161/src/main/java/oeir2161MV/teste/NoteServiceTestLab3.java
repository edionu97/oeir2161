package oeir2161MV.teste;

import oeir2161MV.note.model.Elev;
import oeir2161MV.note.model.Nota;
import oeir2161MV.note.repository.implementations.ClasaRepositoryMock;
import oeir2161MV.note.repository.implementations.EleviRepositoryMock;
import oeir2161MV.note.repository.implementations.NoteRepositoryMock;
import oeir2161MV.note.repository.interfaces.ClasaRepository;
import oeir2161MV.note.repository.interfaces.EleviRepository;
import oeir2161MV.note.repository.interfaces.NoteRepository;
import oeir2161MV.note.service.NoteService;
import oeir2161MV.note.utils.exceptions.ClasaException;
import oeir2161MV.note.utils.validators.MarkValidator;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class NoteServiceTestLab3 {


    @Before
    public void initRepo(){

        noteRepository = new NoteRepositoryMock(MarkValidator.getInstance());
        clasaRepository = new ClasaRepositoryMock();
        eleviRepository = new EleviRepositoryMock();

        noteService = new NoteService(
                noteRepository, clasaRepository, eleviRepository
        );



        elevi.add(new Elev(1, "Eduard"));
        elevi.add(new Elev(2, "Sorana"));
        elevi.add(new Elev(3, "Ionut"));
        elevi.add(new Elev(4, "George"));

        //Eduard
        marks.add(new Nota(1, "Romana", 5));
        marks.add(new Nota(1, "Informatica", 10));
        marks.add(new Nota(1, "Fizica", 10));

        //Sorana
        marks.add(new Nota(2, "Romana", 10));
        marks.add(new Nota(2, "Fizica", 10));

        // George
        marks.add(new Nota(4, "Informatica", 5));

        noteService.creeazaClasa(
                elevi, marks
        );
    }


    //valid
    @Test
    public void testElev(){

        int medie = noteService.calculateMedie(elevi.get(0));

        assertEquals(medie, 8);
    }

    @Test
    public void testElev2(){

        int medie = noteService.calculateMedie(elevi.get(1));

        assertEquals(medie, 6);
    }

    // non valid
    @Test
    public void testElev3(){

        int medie = noteService.calculateMedie(elevi.get(2));

        assertEquals(medie, 0);
    }






    private NoteService noteService;
    private NoteRepository noteRepository;
    private ClasaRepository clasaRepository;
    private EleviRepository eleviRepository;
    private List<Elev> elevi = new ArrayList<>();
    private List<Nota> marks = new ArrayList<>();
}