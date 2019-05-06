package oeir2161MV.note.repository.implementations;

import oeir2161MV.note.model.Nota;
import oeir2161MV.note.utils.Constants;
import oeir2161MV.note.utils.exceptions.ClasaException;
import oeir2161MV.note.utils.validators.MarkValidator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NoteRepositoryMockTest {

    @Before
    public void setUp() throws Exception {
        this.noteRepositoryMock = new NoteRepositoryMock(
                MarkValidator.getInstance()
        );

        this.materie = "informatica";
    }

    @After
    public void tearDown() {
        this.noteRepositoryMock.getNote().clear();
    }

    //ecp
    @Test
    public void testCheckMarkIsSuccessfullyAdded() throws ClasaException {
        final Nota nota = new Nota(1, materie, 10);
        this.noteRepositoryMock.addNota(nota);
        assertEquals(this.noteRepositoryMock.getNote().size(),1);
    }

    //ecp non valid
    @Test(expected = ClasaException.class)
    public  void testCheckExceptionIsThrownIfNrMatricolNotFound() throws ClasaException{
        final Nota nota = new Nota(-1, materie, 10);
        this.noteRepositoryMock.addNota(nota);
    }

    @Test(expected = ClasaException.class)
    public void testCaseMarkIsLessThan1() throws ClasaException{
        final Nota nota = new Nota(1, materie, 0);
        this.noteRepositoryMock.addNota(nota);
    }

    @Test(expected = ClasaException.class)
    public void testCaseMarkIsGreaterThan10() throws ClasaException{
        final Nota nota = new Nota(1, materie, 11);
        this.noteRepositoryMock.addNota(nota);
    }

    @Test()
    public void testCaseMarkIsOK() throws ClasaException{
        final Nota nota = new Nota(1, materie, 9);
        this.noteRepositoryMock.addNota(nota);
        assertEquals(noteRepositoryMock.getNote().size(), 1);
    }

    //bva
    @Test()
    public void testCaseMarkIsGreaterThanOK1() throws ClasaException {
        final Nota nota = new Nota(1, materie, 1);
        this.noteRepositoryMock.addNota(nota);
        assertEquals(noteRepositoryMock.getNote().size(), 1);
    }

    //bva non valid
    @Test(expected = ClasaException.class)
    public void testCaseNrMatricolNotOK () throws  ClasaException{
        final Nota nota = new Nota(Constants.maxNrmatricol + 1, materie, 1);
        this.noteRepositoryMock.addNota(nota);
    }

    @Test(expected = ClasaException.class)
    public void testCaseNrMatricolNotOK1 () throws  ClasaException{
        final Nota nota = new Nota(Constants.minNrmatricol - 1, materie, 10);
        this.noteRepositoryMock.addNota(nota);
    }

    private String materie;
    private NoteRepositoryMock noteRepositoryMock;
}