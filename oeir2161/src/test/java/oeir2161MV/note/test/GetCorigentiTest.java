package oeir2161MV.note.test;

import static org.junit.Assert.*;

import oeir2161MV.note.controller.NoteController;
import oeir2161MV.note.model.Corigent;
import oeir2161MV.note.model.Elev;
import oeir2161MV.note.model.Nota;
import oeir2161MV.note.utils.config.Configuration;
import oeir2161MV.note.utils.exceptions.ClasaException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;


public class GetCorigentiTest {
	
	private NoteController ctrl;

	@Before
	public void setUp() throws Exception {
		ctrl = new AnnotationConfigApplicationContext(Configuration.class).getBean(NoteController.class);
	}

	@Test
	public void test1() throws ClasaException {
		Elev e1 = new Elev(1, "Elev1");
		Elev e2 = new Elev(2, "Elev2");
		ctrl.addElev(e1);
		ctrl.addElev(e2);
		Nota n1 = new Nota(1,"Materie1", 10);
		Nota n2 = new Nota(1,"Materie1", 7);
		Nota n3 = new Nota(1,"Materie2", 10);
		Nota n4 = new Nota(1,"Materie2", 10);
		Nota n5 = new Nota(2,"Materie2", 4);
		Nota n6 = new Nota(2,"Materie2", 5);
		Nota n7 = new Nota(2,"Materie2", 6);
		Nota n8 = new Nota(2,"Materie1", 7);
		ctrl.addNota(n1);
		ctrl.addNota(n2);
		ctrl.addNota(n3);
		ctrl.addNota(n4);
		ctrl.addNota(n5);
		ctrl.addNota(n6);
		ctrl.addNota(n7);
		ctrl.addNota(n8);
		ctrl.creeazaClasa(ctrl.getElevi(), ctrl.getNote());
		List<Corigent> corigenti = ctrl.getCorigenti();
		assertEquals(corigenti.size(),0);
	}

	
	@Test
	public void test2() throws ClasaException {
		Elev e1 = new Elev(1, "Elev1");
		Elev e2 = new Elev(2, "Elev2");
		ctrl.addElev(e1);
		ctrl.addElev(e2);
		Nota n1 = new Nota(1,"Materie1", 10);
		Nota n2 = new Nota(1,"Materie1", 7);
		Nota n3 = new Nota(1,"Materie2", 10);
		Nota n4 = new Nota(1,"Materie2", 10);
		Nota n5 = new Nota(2,"Materie2", 4);
		Nota n6 = new Nota(2,"Materie2", 5);
		Nota n7 = new Nota(2,"Materie2", 3);
		Nota n8 = new Nota(2,"Materie1", 7);
		ctrl.addNota(n1);
		ctrl.addNota(n2);
		ctrl.addNota(n3);
		ctrl.addNota(n4);
		ctrl.addNota(n5);
		ctrl.addNota(n6);
		ctrl.addNota(n7);
		ctrl.addNota(n8);
		ctrl.creeazaClasa(ctrl.getElevi(), ctrl.getNote());
		List<Corigent> corigenti = ctrl.getCorigenti();
		assertEquals(corigenti.size(),1);
	}
	
	@Test
	public void test3() throws ClasaException {
		Elev e1 = new Elev(1, "Elev1");
		Elev e2 = new Elev(2, "Elev2");
		ctrl.addElev(e1);
		ctrl.addElev(e2);
		Nota n1 = new Nota(1,"Materie1", 1);
		Nota n2 = new Nota(1,"Materie1", 7);
		Nota n3 = new Nota(1,"Materie2", 10);
		Nota n4 = new Nota(1,"Materie2", 10);
		Nota n5 = new Nota(2,"Materie2", 4);
		Nota n6 = new Nota(2,"Materie2", 5);
		Nota n7 = new Nota(2,"Materie2", 3);
		Nota n8 = new Nota(2,"Materie1", 7);
		ctrl.addNota(n1);
		ctrl.addNota(n2);
		ctrl.addNota(n3);
		ctrl.addNota(n4);
		ctrl.addNota(n5);
		ctrl.addNota(n6);
		ctrl.addNota(n7);
		ctrl.addNota(n8);
		ctrl.creeazaClasa(ctrl.getElevi(), ctrl.getNote());
		List<Corigent> corigenti = ctrl.getCorigenti();
		assertEquals(corigenti.get(1).getNrMaterii(), corigenti.get(0).getNrMaterii());
	}
	
	@Test
	public void test4() throws ClasaException {
		Elev e1 = new Elev(1, "Elev1");
		Elev e2 = new Elev(2, "Elev2");
		ctrl.addElev(e1);
		ctrl.addElev(e2);
		Nota n1 = new Nota(1,"Materie1", 1);
		Nota n2 = new Nota(1,"Materie1", 7);
		Nota n3 = new Nota(1,"Materie2", 10);
		Nota n4 = new Nota(1,"Materie2", 10);
		Nota n5 = new Nota(2,"Materie2", 4);
		Nota n6 = new Nota(2,"Materie2", 5);
		Nota n7 = new Nota(2,"Materie2", 3);
		Nota n8 = new Nota(2,"Materie1", 4);
		ctrl.addNota(n1);
		ctrl.addNota(n2);
		ctrl.addNota(n3);
		ctrl.addNota(n4);
		ctrl.addNota(n5);
		ctrl.addNota(n6);
		ctrl.addNota(n7);
		ctrl.addNota(n8);
		ctrl.creeazaClasa(ctrl.getElevi(), ctrl.getNote());
		List<Corigent> corigenti = ctrl.getCorigenti();
		assertEquals(corigenti.get(1).getNrMaterii()+1, corigenti.get(0).getNrMaterii());
	}
	
	@Test
	public void test5() throws ClasaException {
		ctrl.creeazaClasa(ctrl.getElevi(), ctrl.getNote());
		List<Corigent> corigenti = ctrl.getCorigenti();
		assertEquals(corigenti.size(),0);
	}
	
	@Test
	public void test6() throws ClasaException {
		//Elev e1 = new Elev(1, "Elev1");
		ctrl.creeazaClasa(ctrl.getElevi(), ctrl.getNote());
		List<Corigent> corigenti = ctrl.getCorigenti();
		assertEquals(corigenti.size(),0);
	}
}
