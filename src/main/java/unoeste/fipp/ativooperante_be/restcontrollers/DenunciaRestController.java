package unoeste.fipp.ativooperante_be.restcontrollers;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unoeste.fipp.ativooperante_be.entities.Denuncia;
import unoeste.fipp.ativooperante_be.entities.Erro;
import unoeste.fipp.ativooperante_be.entities.FeedBack;
import unoeste.fipp.ativooperante_be.entities.Tipo;
import unoeste.fipp.ativooperante_be.services.DenunciaService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("apis/denuncia")
public class DenunciaRestController {

    @Autowired
    private DenunciaService denunciaService;

    @GetMapping
    public ResponseEntity<Object> getAll(){
        List<Denuncia> denunciaList;
        denunciaList=denunciaService.getAll();
        if (!denunciaList.isEmpty())
            return ResponseEntity.ok(denunciaList);
        else
            return ResponseEntity.badRequest().body(
                    new Erro("Nenhum tipo cadastrado"));
    }


    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Denuncia denuncia){
        denuncia.setData(LocalDate.now());
        System.out.println(denuncia.getData());
        Denuncia nova = denunciaService.salvar(denuncia);

        if (nova != null)
            return ResponseEntity.ok(nova);
        else
            return ResponseEntity.badRequest().body(new Erro("Nenhuma denuncia cadastrada"));
    }






    @GetMapping("add-feedback/{id}/{texto}")
    public ResponseEntity<Object> addFeedBack(@PathVariable Long id, @PathVariable String texto) {
        if(denunciaService.addFeedBack(new FeedBack(id,texto)))
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().body("Não foi possível adicionar o feebback");
    }

    @GetMapping("usuario/{id}")
    public ResponseEntity<Object> getAllByUsuario(@PathVariable Long id){
        List<Denuncia> denunciaList;
        denunciaList=denunciaService.getAllByUsuario(id);
        if (!denunciaList.isEmpty())
            return ResponseEntity.ok(denunciaList);
        else
            return ResponseEntity.badRequest().body(
                    new Erro("Nenhuma denuncia cadastrada para esse usuário"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id){

        Denuncia denuncia;
        denuncia = denunciaService.getDenunciaById(id);

        if(denuncia != null)
            return ResponseEntity.ok(denuncia);
        return ResponseEntity.badRequest().body(new Erro("Nenhuma denuncia cadastrada"));

    }


    @DeleteMapping("apagar/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id){

        boolean apaga;
        apaga = denunciaService.excluirDenuncia(id);
        if(apaga)
            return ResponseEntity.ok("Denuncia removida com sucesso");
        else
            return ResponseEntity.badRequest().body(new Erro("Denuncia nao encontrada"));


    }
    @PutMapping
    public ResponseEntity<Object> update(@RequestBody Denuncia denuncia){

        Denuncia denAtt = denunciaService.update(denuncia);

        if(denAtt != null)
            return ResponseEntity.ok(denAtt);
        return ResponseEntity.badRequest().body(new Erro("Não foi possivel atualizar denuncia: "+denuncia.getId()));
    }

}
