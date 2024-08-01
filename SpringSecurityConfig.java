package com.springboot.automotora;

import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.slf4j.LoggerFactory;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        PasswordEncoder encoder = passwordEncoder();

        UserDetails user = User.builder()
                .passwordEncoder(encoder::encode)
                .username("user")
                .password("12345")
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .passwordEncoder(encoder::encode)
                .username("Administrador")
                .password("12345")
                .roles("USER", "Administrador")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // Configuración de autorización
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/css/**", "/js/**", "/images/**", "/listar").permitAll()
                        .requestMatchers("/index").permitAll() // Acceso permitido para todos
                        .requestMatchers("/vendedor/listar/**", "/vendedor/ver/**").hasRole("USER") // Acceso solo para usuarios con rol USER
                        .requestMatchers("/cliente/listar/**", "/cliente/ver/**").hasRole("USER") // Acceso solo para usuarios con rol USER
                        .requestMatchers("/deportivo/listar/**", "/deportivo/ver/**").hasRole("USER") // Acceso solo para usuarios con rol USER
                        .requestMatchers("/utilitario/listar/**", "/utilitario/ver/**").hasRole("USER") // Acceso solo para usuarios con rol USER
                        .requestMatchers("/compra/listar/**", "/compra/ver/**").hasRole("USER") // Acceso solo para usuarios con rol USER
                        .requestMatchers("/medio/listar/**", "/medio/ver/**").hasRole("USER") // Acceso solo para usuarios con rol USER

                        .requestMatchers("/vendedor/listar/**", "/vendedor/eliminar/**").hasRole("Administrador") // Acceso solo para usuarios con rol ADMIN
                        .requestMatchers("/cliente/listar/**", "/cliente/eliminar/**").hasRole("Administrador") // Acceso solo para usuarios con rol ADMIN
                        .requestMatchers("/utilitario/listar/**", "/utilitario/eliminar/**").hasRole("Administrador") // Acceso solo para usuarios con rol ADMIN
                        .requestMatchers("/deportivo/listar/**", "/deportivo/eliminar/**").hasRole("Administrador") // Acceso solo para usuarios con rol ADMIN
                        .requestMatchers("/compra/listar/**", "/compra/eliminar/**").hasRole("Administrador") // Acceso solo para usuarios con rol ADMIN
                        .requestMatchers("/medio/listar/**", "/medio/eliminar/**").hasRole("Administrador") // Acceso solo para usuarios con rol ADMIN
                        .requestMatchers("/aviso/listar/**", "/aviso/eliminar/**").hasRole("Administrador") // Acceso solo para usuarios con rol ADMIN

                        .requestMatchers("/vendedor/form/**", "/vendedor/eliminar/**").hasRole("Administrador") // Acceso solo para usuarios con rol ADMIN
                        .requestMatchers("/deportivo/form/**", "/deportivo/eliminar/**").hasRole("Administrador") // Acceso solo para usuarios con rol ADMIN
                        .requestMatchers("/cliente/form/**", "/cliente/eliminar/**", "/cliente/listar/**").hasRole("Administrador") // Acceso solo para usuarios con rol ADMIN
                        .requestMatchers("/utilitario/form/**", "/utilitario/eliminar/**").hasRole("Administrador") // Acceso solo para usuarios con rol ADMIN
                        .requestMatchers("/medio/form/**", "/medio/eliminar/**").hasRole("Administrador") // Acceso solo para usuarios con rol ADMIN
                        .requestMatchers("/aviso/form/**", "/aviso/eliminar/**").hasRole("Administrador") // Acceso solo para usuarios con rol ADMIN
                        .requestMatchers("/compra/form/**", "/compra/eliminar/**", "/compra/listar/**").hasRole("Administrador") // Acceso solo para usuarios con rol ADMIN
                        .requestMatchers("/compra/**").hasRole("Administrador") // Acceso solo para usuarios con rol ADMIN
                )
                .formLogin((form) -> form
                        .loginPage("/login") // Especificar la página de inicio de sesión personalizada
                        .defaultSuccessUrl("/index", true) // Página a redireccionar después de un inicio de sesión exitoso
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll)
        ;

        return http.build();
    }

}
