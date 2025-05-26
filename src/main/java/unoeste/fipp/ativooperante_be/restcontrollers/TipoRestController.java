package unoeste.fipp.ativooperante_be.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unoeste.fipp.ativooperante_be.entities.Erro;
import unoeste.fipp.ativooperante_be.entities.Tipo;
import unoeste.fipp.ativooperante_be.services.TipoService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("apis/tipo")
public class TipoRestController {
    @Autowired
    private TipoService tipoService;

    @GetMapping
    public ResponseEntity<Object> getAll(){
        List<Tipo> tipoList;
        tipoList=tipoService.getAll();
        if (!tipoList.isEmpty())
            return ResponseEntity.ok(tipoList);
        else
            return ResponseEntity.badRequest().body(
                    new Erro("Nenhum tipo cadastrado"));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable long id){
        Tipo tipo;

        tipo= tipoService.buscarPorId(id);

        if(tipo!=null)
            return ResponseEntity.ok(tipo);
        else
            return ResponseEntity.badRequest().body(new Erro("Nenhum tipo de id: "+ id+" cadastrado"));
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Tipo tipo){
        Tipo tipoAux=tipoService.salvar(tipo);
        if(tipoAux!=null)
            return ResponseEntity.ok(tipoAux);
        else
            return ResponseEntity.badRequest().body(
                    new Erro("Erro ao gravar o tipo"));
    }


    @PutMapping
    public ResponseEntity<Object> update(@RequestBody Tipo tipo) {
        Tipo tipoAtualizado = tipoService.atualizar(tipo);
        if (tipoAtualizado != null)
            return ResponseEntity.ok(tipoAtualizado);
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao atualizar o tipo"));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){

        boolean tipoDeletar = tipoService.deletar(id);

        if (tipoDeletar == true)
            return ResponseEntity.ok("Tipo Deletado com sucesso");
        else
            return ResponseEntity.badRequest().body(new Erro("Erro ao deletar o tipo"));
    }

    //demais endpoints para CRUD de tipo
}
