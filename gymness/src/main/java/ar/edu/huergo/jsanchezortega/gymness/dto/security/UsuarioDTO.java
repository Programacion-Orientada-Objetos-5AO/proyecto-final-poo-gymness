package ar.edu.huergo.jsanchezortega.gymness.dto.security;

import java.util.List;


public record UsuarioDTO(String username, List<String> roles) {
    
}
