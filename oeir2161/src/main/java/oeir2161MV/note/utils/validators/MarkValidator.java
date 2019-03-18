package oeir2161MV.note.utils.validators;

import oeir2161MV.note.model.Nota;
import oeir2161MV.note.utils.Constants;
import oeir2161MV.note.utils.exceptions.ClasaException;

public class MarkValidator {

    public void validate(Nota nota) throws  ClasaException{

        if(nota.getMaterie().length() < 5 || nota.getMaterie().length() > 20){
            throw new ClasaException(Constants.invalidMateria);
        }

        if(nota.getNrmatricol() < Constants.minNrmatricol || nota.getNrmatricol() > Constants.maxNrmatricol){
            throw new ClasaException(Constants.invalidNrmatricol);
        }

        if(nota.getNota() < Constants.minNota || nota.getNota() > Constants.maxNota){
            throw new ClasaException(Constants.invalidNota);
        }
        if(nota.getNota() != (int)nota.getNota()){
            throw new ClasaException(Constants.invalidNota);
        }
        if(nota.getNrmatricol() != (int)nota.getNrmatricol()){
            throw new ClasaException(Constants.invalidNrmatricol);
        }
    }

    public static MarkValidator getInstance(){

        if(instance == null){
            synchronized (MarkValidator.class){
                if(instance == null){
                    instance = new MarkValidator();
                }
            }
        }

        return instance;
    }

    private  MarkValidator(){

    }


    public static  MarkValidator instance;

}
