package ar.edu.huergo.jsanchezortega.gymness.config;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import ar.edu.huergo.jsanchezortega.gymness.entity.security.Rol;
import ar.edu.huergo.jsanchezortega.gymness.entity.security.Usuario;
import ar.edu.huergo.jsanchezortega.gymness.repository.security.RolRepository;
import ar.edu.huergo.jsanchezortega.gymness.repository.security.UsuarioRepository;
import ar.edu.huergo.jsanchezortega.gymness.util.PasswordValidator;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(RolRepository rolRepository, UsuarioRepository usuarioRepository, PasswordEncoder encoder) {
        return args -> {
            Rol admin = rolRepository.findByNombre("ADMIN").orElseGet(() -> rolRepository.save(new Rol("ADMIN")));
            Rol cliente = rolRepository.findByNombre("CLIENTE").orElseGet(() -> rolRepository.save(new Rol("CLIENTE")));

            if (usuarioRepository.findByUsername("admin@gymness.jsanchezortega.edu.ar").isEmpty()) {
                String adminPassword = "AdminSuperSegura@123";
                PasswordValidator.validate(adminPassword);
                Usuario u = new Usuario("admin@gymness.jsanchezortega.edu.ar", encoder.encode(adminPassword));
                u.setRoles(Set.of(admin));
                usuarioRepository.save(u);
            }

            if (usuarioRepository.findByUsername("cliente@gymness.jsanchezortega.edu.ar").isEmpty()) {
                String clientePassword = "ClienteSuperSegura@123";
                PasswordValidator.validate(clientePassword);
                Usuario u = new Usuario("cliente@gymness.jsanchezortega.edu.ar", encoder.encode(clientePassword));
                u.setRoles(Set.of(cliente));
                usuarioRepository.save(u);
            }
        };
    }
}