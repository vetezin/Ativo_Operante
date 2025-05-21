package unoeste.fipp.ativooperante_be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.ativooperante_be.entities.Orgaos;
import unoeste.fipp.ativooperante_be.repositories.OrgaosRepository;

import java.util.List;

@Service
public class OrgaosService {

    @Autowired
    private OrgaosRepository orgaosRepository;

    public List<Orgaos> getAll(){
        return orgaosRepository.findAll();

    }

    public Orgaos buscaPorId(long id){
        return orgaosRepository.findById(id).orElse(null);
    }

    public Orgaos atualizar(Orgaos orgaos,long id){

        if(orgaosRepository.existsById(id)){
            Orgaos orgaoAtt = orgaosRepository.findById(id).orElse(null);
            if(orgaoAtt != null){
                orgaoAtt.setNome(orgaos.getNome());

                return orgaosRepository.save(orgaoAtt);
            }
        }
        return null;
    }


    public Orgaos deletar(Orgaos orgao, long id){

        if(orgaosRepository.existsById(id)){
            orgaosRepository.deleteById(id);
            return orgao;

        }
        return null;
    }

    public Orgaos inserir(Orgaos orgaos){

        return orgaosRepository.save(orgaos);

    }
}
