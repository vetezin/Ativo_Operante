package unoeste.fipp.ativooperante_be;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unoeste.fipp.ativooperante_be.entities.Usuario;
import unoeste.fipp.ativooperante_be.repositories.UsuarioRepository;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class LoginController {

    @Autowired
    private UsuarioRepository usuariosRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Usuario usuario = usuariosRepository.findByEmail(request.getEmail());

        int senhaDigitada;
        try {
            senhaDigitada = Integer.parseInt(request.getSenha());
        } catch (NumberFormatException e) {
            return ResponseEntity.status(400).body("Senha inválida (deve ser numérica)");
        }

        if (usuario == null || usuario.getSenha() != senhaDigitada) {
            return ResponseEntity.status(401).body("Email ou senha inválidos");
        }

        String token = jwtUtil.generateToken(usuario.getEmail(), String.valueOf(usuario.getNivel()));
        return ResponseEntity.ok(new LoginResponse(token, usuario.getId(), String.valueOf(usuario.getNivel())));
    }
}
