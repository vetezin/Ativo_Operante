package unoeste.fipp.ativooperante_be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.ativooperante_be.entities.FeedBack;
import unoeste.fipp.ativooperante_be.repositories.FeedbackRepository;

import java.util.List;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    public List<FeedBack> getAll(){
        return feedbackRepository.findAll();
    }


}
