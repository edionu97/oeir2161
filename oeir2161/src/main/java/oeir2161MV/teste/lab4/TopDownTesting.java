package oeir2161MV.teste.lab4;

import oeir2161MV.note.controller.NoteController;
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

public class TopDownTesting {

    @Before
    public void setUp() throws Exception {
        this.noteRepository = new NoteRepositoryMock(
                MarkValidator.getInstance()
        );

        this.materie = "informatica";

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

    @Test()
    public void testCaseMarkIsGreaterThanOK1() throws ClasaException {
        final Nota nota = new Nota(1, materie, 1);
        this.noteRepository.addNota(nota);
        assertEquals(noteRepository.getNote().size(), 1);
    }

    @Test
    public void testElev(){

        int medie = noteService.calculateMedie(elevi.get(0));

        assertEquals(medie, 8);
    }

    @Test
    public void studentValid() throws ClasaException {
        assertEquals(
                noteService.getCorigenti().size(), 1
        );
    }

    @Test
    public void topDownIntegrationPA() throws  Exception{

        final NoteController controller = new NoteController(
                noteService
        );

        final int _size = controller.getNote().size();

        controller.addNota(
                new Nota(2, "Romana", 5)
        );

        assertEquals(controller.getNote().size(), _size + 1);

    }

    @Test
    public void topDownIntegrationPAB() throws  ClasaException{
        final NoteController controller = new NoteController(
                noteService
        );

        final int _size = controller.getNote().size();

        controller.addNota(
                new Nota(2, "Romana", 5)
        );

        //check if mark was successfully added
        assertEquals(controller.getNote().size(), _size + 1);

        //check medie
        int medie = noteService.calculateMedie(elevi.get(1));
        assertEquals(medie, 6);
    }

    @Test
    public void topDownIntegrationPABC() throws ClasaException{

        final NoteController controller = new NoteController(
                noteService
        );

        final int _size = controller.getNote().size();

        controller.addNota(
                new Nota(2, "Romana", 5)
        );

        //check if mark was successfully added
        assertEquals(controller.getNote().size(), _size + 1);

        //check medie
        int medie = noteService.calculateMedie(elevi.get(1));
        assertEquals(medie, 6);

        //check if corigent number of student is not influenced
        assertEquals(
                noteService.getCorigenti().size(), 1
        );
    }

    private String materie;
    private NoteService noteService;
    private NoteRepository noteRepository;
    private ClasaRepository clasaRepository;
    private EleviRepository eleviRepository;
    private List<Elev> elevi = new ArrayList<>();
    private List<Nota> marks = new ArrayList<>();
}
