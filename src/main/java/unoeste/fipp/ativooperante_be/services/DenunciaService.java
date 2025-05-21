package unoeste.fipp.ativooperante_be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.ativooperante_be.entities.Denuncia;
import unoeste.fipp.ativooperante_be.entities.FeedBack;
import unoeste.fipp.ativooperante_be.entities.Usuario;
import unoeste.fipp.ativooperante_be.repositories.DenunciaRepository;

import java.util.List;

@Service
public class DenunciaService {
    @Autowired
    private DenunciaRepository denunciaRepository;
    public List<Denuncia> getAll()
    {
        return denunciaRepository.findAll();
    }

    public boolean addFeedBack(FeedBack feedBack){
        try {
            denunciaRepository.addFeedBack(feedBack.getId(), feedBack.getTexto());
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public List<Denuncia> getAllByUsuario(Long id) {
        return denunciaRepository.findAllByUsuario(new Usuario(id,0L));
    }

    public Denuncia getDenunciaById(Long id) {
        return denunciaRepository.findById(id).get();
    }


    public Denuncia salvar(Denuncia denuncia) {

        if(denuncia.getTexto() != null)
            return denunciaRepository.save(denuncia);
        else
            return null;

    }

    public boolean excluirDenuncia( Long id){

        if(denunciaRepository.existsById(id)){
            denunciaRepository.deleteById(id);
            return true;
        }
        return false;

    }


    public Denuncia update(Denuncia denuncia) {

        long id = denuncia.getId();
        if(denunciaRepository.existsById(id)){
            Denuncia attDen = denunciaRepository.findById(id).get();
            if(attDen != null){
                attDen.setTexto(denuncia.getTexto());
                return denunciaRepository.save(attDen);
            }
        }
        return null;
    }
}
