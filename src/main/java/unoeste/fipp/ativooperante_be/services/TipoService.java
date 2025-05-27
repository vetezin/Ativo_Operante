package unoeste.fipp.ativooperante_be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.ativooperante_be.entities.Tipo;
import unoeste.fipp.ativooperante_be.repositories.TipoRepository;
import unoeste.fipp.ativooperante_be.repositories.DenunciaRepository;
import unoeste.fipp.ativooperante_be.repositories.FeedbackRepository;

import java.util.List;

@Service
public class TipoService {
    @Autowired
    private TipoRepository tipoRepository;
    @Autowired
    private DenunciaRepository denunciaRepository;
    @Autowired
    private FeedbackRepository feedbackRepository;


    public List<Tipo> getAll()
    {
        return tipoRepository.findAll();
    }

    public Tipo salvar(Tipo tipo){
        return tipoRepository.save(tipo);
    }



    public Tipo atualizar(Tipo tipo) {
        return tipoRepository.findById(tipo.getId()).map(tipoExistente -> {
            tipoExistente.setNome(tipo.getNome());
            return tipoRepository.save(tipoExistente);
        }).orElse(null);
    }

    public boolean deletar(Long id){

        if(tipoRepository.existsById(id)){
            denunciaRepository.deleteByTipoId(id);
            tipoRepository.deleteById(id);
             return true;
        }
        else return false;
    }

    public Tipo buscarPorId(Long id) {
        return tipoRepository.findById(id).orElse(null);
    }

}
