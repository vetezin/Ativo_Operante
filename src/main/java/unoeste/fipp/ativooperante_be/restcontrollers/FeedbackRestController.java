package unoeste.fipp.ativooperante_be.restcontrollers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unoeste.fipp.ativooperante_be.entities.Erro;
import unoeste.fipp.ativooperante_be.entities.FeedBack;
import unoeste.fipp.ativooperante_be.services.FeedbackService;

import java.util.List;

@RestController
@RequestMapping("apis/feedback")
public class FeedbackRestController {
    @Autowired
    private FeedbackService feedbackService;
    @GetMapping
    public ResponseEntity<Object> getAll(){

        List<FeedBack> lista = feedbackService.getAll();

        if(!lista.isEmpty())
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().body(new Erro("Nao há dados"));
    }
}
