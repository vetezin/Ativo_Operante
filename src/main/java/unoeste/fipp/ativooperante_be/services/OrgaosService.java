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
}
