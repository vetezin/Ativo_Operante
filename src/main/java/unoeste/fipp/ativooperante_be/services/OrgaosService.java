package unoeste.fipp.ativooperante_be.services;
import unoeste.fipp.ativooperante_be.repositories.FeedbackRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.ativooperante_be.entities.Orgaos;
import unoeste.fipp.ativooperante_be.repositories.OrgaosRepository;

import java.util.List;

@Service
public class OrgaosService {

    @Autowired
    private OrgaosRepository orgaosRepository;
    @Autowired
    private DenunciaService denunciaService;
    @Autowired
    private FeedbackRepository feedbackRepository;


    public List<Orgaos> getAll(){
        return orgaosRepository.findAll();

    }

    public Orgaos buscaPorId(long id){
        return orgaosRepository.findById(id).orElse(null);
    }

    public Orgaos atualizar(Orgaos orgaos){

        if(orgaosRepository.existsById(orgaos.getId())){
            Orgaos orgaoAtt = orgaosRepository.findById(orgaos.getId()).orElse(null);
            if(orgaoAtt != null){
                orgaoAtt.setNome(orgaos.getNome());

                return orgaosRepository.save(orgaoAtt);
            }
        }
        return null;
    }

    public boolean deletar( Long id){

        if(orgaosRepository.existsById(id)){
//            denunciaService.deleteByTipoId(id);
            orgaosRepository.deleteById(id);
            return true;

        }
        return false;
    }

    public Orgaos inserir(Orgaos orgaos){

        return orgaosRepository.save(orgaos);

    }
}
