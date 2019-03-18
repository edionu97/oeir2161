package oeir2161MV.note.repository.interfaces;

import java.io.*;

public interface Repository {

    default BufferedReader openFile(String filename) throws IOException{
        FileInputStream fstream = new FileInputStream(filename);
        DataInputStream in = new DataInputStream(fstream);
        return new BufferedReader(new InputStreamReader(in));
    }
}
