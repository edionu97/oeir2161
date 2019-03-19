package oeir2161MV.note.controller.UI;

import oeir2161MV.note.controller.NoteController;
import oeir2161MV.note.controller.UI.utils.Cmd;
import oeir2161MV.note.model.Nota;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class UI {

    public UI(NoteController controller) {
        this.controller = controller;
        createCmdMenu();
    }

    public void showMenu() {

        while (true) {
            System.out.println("1. Adaugare Nota");
            System.out.println("2. Calculeaza medii");
            System.out.println("3. Elevi corigenti");
            System.out.println("4. Iesire");
            System.out.print("Optiunea este: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            try {
                Cmd cmd = cmdMenu.get(
                        Integer.parseInt(
                                br.readLine()
                        )
                );
                if (cmd == null) {
                    System.out.println("You must enter a valid command!");
                    continue;
                }
                cmd.execute(br);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void createCmdMenu(){

        cmdMenu.put(1, (reader) -> {

            try{

                System.out.print("Dati numarul matricol: ");
                int numar = Integer.parseInt(reader.readLine().trim());
                System.out.print("Dati materia: ");
                String materie = reader.readLine();
                System.out.print("Dati nota: ");
                Double nota = Double.parseDouble(reader.readLine().trim());

                controller.addNota(new Nota(
                        numar,
                        materie,
                        nota
                ));

                controller.saveInFile();
                System.out.println(controller.getNote().size());
                controller.creeazaClasa(controller.getElevi(), controller.getNote());

            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        });

        cmdMenu.put(2, (reader) ->
            controller.calculeazaMedii().forEach(System.out::println)
        );

        cmdMenu.put(3, (reader) -> controller.getCorigenti().forEach(System.out::println));

        cmdMenu.put(4, (reader) -> System.exit(0));
    }

    private NoteController controller;
    private Map<Integer, Cmd> cmdMenu = new HashMap<>();
}
