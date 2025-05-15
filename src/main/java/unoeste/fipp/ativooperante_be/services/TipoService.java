package unoeste.fipp.ativooperante_be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unoeste.fipp.ativooperante_be.entities.Tipo;
import unoeste.fipp.ativooperante_be.repositories.TipoRepository;

import java.util.List;

@Service
public class TipoService {
    @Autowired
    private TipoRepository tipoRepository;
    public List<Tipo> getAll()
    {
        return tipoRepository.findAll();
    }
    public Tipo salvar(Tipo tipo){
        return tipoRepository.save(tipo);
    }



    public Tipo atualizar(Tipo tipo, Long id) {
        if (tipoRepository.existsById(id)) {
            Tipo tipoExistente = tipoRepository.findById(id).orElse(null);
            if (tipoExistente != null) {
                tipoExistente.setNome(tipo.getNome());


                return tipoRepository.save(tipoExistente);
            }
        }
        return null;
    }




    public Tipo deletar(Tipo tipo, Long id){

        if(tipoRepository.existsById(id)){
             tipoRepository.delete(tipo);
             return tipo;
        }
        else return null;
    }

}
