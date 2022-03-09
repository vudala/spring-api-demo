package astus.activity.contact;

import java.util.List;
import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/contacts"})
public class ContactController {

    private ContactRepository repository;

    ContactController(ContactRepository contactRepository) {
        this.repository = contactRepository;
    }


    /* Endpoint que retorna uma lista de contatos já cadastrados */
    @GetMapping(produces = "application/json")
    public List<Contact> findAll(){
        return repository.findAll();
    }


    /* Endpoint que cadastra um novo contato */
    @PostMapping(consumes = "application/json")
    public Contact create(@Valid @RequestBody(required = true) Contact contact){
        return repository.save(contact);
    }


    /* Trata a possivel MethodArgumentNotValidException disparada pelo validator, retorna um array contendo as mensagens das exceptions */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<String> handleValidationExceptions(MethodArgumentNotValidException exc) {
        List<String> errors = new ArrayList<String>();
        exc.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            errors.add(errorMessage);
        });
        return errors;
    }
}