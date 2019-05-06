package oeir2161MV.teste.lab4;

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

import static org.junit.Assert.assertEquals;

public class TestCorigenti {


    @Before
    public void initRepo() {

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
        marks.add(new Nota(4, "Informatica", 1));

        noteService.creeazaClasa(
                elevi, marks
        );
    }

    @Test
    public void studentValid() throws ClasaException {
        assertEquals(
                noteService.getCorigenti().size(), 1
        );
    }

    @Test(expected = ClasaException.class)
    public void studentInvalid() throws ClasaException {

        elevi = new ArrayList<>();
        marks = new ArrayList<>();

        noteService.creeazaClasa(elevi, marks);
        noteService.getCorigenti();
    }


    private NoteService noteService;
    private NoteRepository noteRepository;
    private ClasaRepository clasaRepository;
    private EleviRepository eleviRepository;
    private List<Elev> elevi = new ArrayList<>();
    private List<Nota> marks = new ArrayList<>();
}
