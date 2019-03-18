package oeir2161MV.note.utils.config;
import oeir2161MV.note.controller.NoteController;
import oeir2161MV.note.controller.UI.UI;
import oeir2161MV.note.repository.implementations.ClasaRepositoryMock;
import oeir2161MV.note.repository.implementations.EleviRepositoryMock;
import oeir2161MV.note.repository.implementations.NoteRepositoryMock;
import oeir2161MV.note.repository.interfaces.ClasaRepository;
import oeir2161MV.note.repository.interfaces.EleviRepository;
import oeir2161MV.note.repository.interfaces.NoteRepository;
import oeir2161MV.note.service.NoteService;
import oeir2161MV.note.utils.validators.MarkValidator;
import org.springframework.context.annotation.Bean;


@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    MarkValidator validator(){
        return MarkValidator.getInstance();
    }

    @Bean
    NoteRepository noteRepository(){
        return new NoteRepositoryMock(validator());
    }

    @Bean
    EleviRepository eleviRepository(){
        return new EleviRepositoryMock();
    }

    @Bean
    ClasaRepository clasaRepository(){
        return new ClasaRepositoryMock();
    }

    @Bean
    NoteService noteService(){
        return new NoteService(
                noteRepository(),
                clasaRepository(),
                eleviRepository()
        );
    }

    @Bean
    NoteController noteController(){
        return new NoteController(
                noteService()
        );
    }

    @Bean
    UI ui(){
        return new UI(noteController());
    }
}
