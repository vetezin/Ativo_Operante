package unoeste.fipp.ativooperante_be.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unoeste.fipp.ativooperante_be.entities.Erro;
import unoeste.fipp.ativooperante_be.entities.Usuario;
import unoeste.fipp.ativooperante_be.services.UsuarioService;

@RestController
@CrossOrigin
@RequestMapping("apis/usuario")
public class UsuarioRestController {
    @Autowired
    private UsuarioService usuarioService;



    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody Usuario usuario) {

        Usuario novo = usuarioService.addUser(usuario);
        if (novo != null) {
            return ResponseEntity.ok(novo);
        }
        else
            return ResponseEntity.badRequest().body(new
                    Erro("Usuario nao salvo"));
    }
}
