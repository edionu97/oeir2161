package oeir2161MV.note.controller.UI.utils;

import oeir2161MV.note.utils.exceptions.ClasaException;

import java.io.BufferedReader;

@FunctionalInterface
public interface Cmd {
    void execute(BufferedReader reader) throws ClasaException;
}
