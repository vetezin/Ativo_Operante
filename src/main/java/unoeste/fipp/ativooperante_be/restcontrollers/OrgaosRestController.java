package unoeste.fipp.ativooperante_be.restcontrollers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity getAll() {

         if(orgaosService.getAll() != null) {
             return ResponseEntity.ok(orgaosService.getAll());
         }
         else
             return ResponseEntity.badRequest().body(new Erro("Nao há orgaos"));

    }
}
