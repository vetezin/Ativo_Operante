package unoeste.fipp.ativooperante_be.restcontrollers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unoeste.fipp.ativooperante_be.entities.Erro;
import unoeste.fipp.ativooperante_be.entities.Orgaos;
import unoeste.fipp.ativooperante_be.services.OrgaosService;

import java.util.List;

@RestController
@RequestMapping("apis/orgaos")
public class OrgaosRestController {

    @Autowired
    private OrgaosService orgaosService;
    @GetMapping
    public ResponseEntity<Object> getAll() {

         if(orgaosService.getAll() != null) {
             return ResponseEntity.ok(orgaosService.getAll());
         }
         else
             return ResponseEntity.badRequest().body(new Erro("Nao há orgaos"));

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable long id) {
        Orgaos orgaos;

        orgaos = orgaosService.buscaPorId(id);

        if (orgaos != null)
            return ResponseEntity.ok(orgaos);
        else
            return ResponseEntity.badRequest().body(new Erro("Nao há orgaos com id: " + id ));
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody Orgaos novo,@PathVariable long id) {

        Orgaos orgao = orgaosService.atualizar(novo,id);

        if(orgao != null)
            return ResponseEntity.ok(orgao);
        else
            return ResponseEntity.badRequest().body(new Erro("Orgao nao existe"));
    }

    @DeleteMapping
    public ResponseEntity<Object> delete(@PathVariable long id,Orgaos orgao) {

        Orgaos orgDelete = orgaosService.deletar(orgao,id);

        if(orgDelete != null)
            return ResponseEntity.ok(orgDelete);
        else
            return ResponseEntity.badRequest().body(new Erro("Orgao nao existe"));
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Orgaos novo) {

        Orgaos att = orgaosService.inserir(novo);

        if(att != null)
            return ResponseEntity.ok(att);

        return ResponseEntity.badRequest().body(new Erro("Nao foi possivel adicionar"));
    }

}
