package com.smartlogix.serviciousuarios.controller;

import com.smartlogix.serviciousuarios.model.Usuario;
import com.smartlogix.serviciousuarios.security.JwtUtils;
import com.smartlogix.serviciousuarios.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody Usuario usuario) {

        if (usuarioService.existePorUsername(usuario.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("mensaje", "El username ya existe"));
        }

        if (usuario.getRol() == null || usuario.getRol().isBlank()) {
            usuario.setRol("USER");
        }

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        Usuario guardado = usuarioService.guardar(usuario);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Usuario registrado correctamente");
        respuesta.put("id", guardado.getId());
        respuesta.put("username", guardado.getUsername());
        respuesta.put("rol", guardado.getRol());

        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {

        Optional<Usuario> userOpt = usuarioService.buscarPorUsername(usuario.getUsername());

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("mensaje", "Credenciales inválidas"));
        }

        Usuario user = userOpt.get();

        if (!passwordEncoder.matches(usuario.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("mensaje", "Credenciales inválidas"));
        }

        String token = jwtUtils.generarToken(user.getId(), user.getUsername(), user.getRol());

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Login correcto");
        respuesta.put("token", token);
        respuesta.put("username", user.getUsername());
        respuesta.put("rol", user.getRol());

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> obtener(@PathVariable String username) {
        Optional<Usuario> userOpt = usuarioService.buscarPorUsername(username);

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("mensaje", "Usuario no encontrado"));
        }

        Usuario user = userOpt.get();

        return ResponseEntity.ok(Map.of(
                "id", user.getId(),
                "username", user.getUsername(),
                "rol", user.getRol()
        ));
    }
}