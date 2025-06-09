package backend.spring.configuracion;

import javax.sql.DataSource;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired 
    DataSource dataSource;

    @Autowired
    public void configure(AuthenticationManagerBuilder amb) throws Exception {
        amb.jdbcAuthentication()
            .dataSource(dataSource)
            .usersByUsernameQuery("select username, password, enabled from usuario where username = ?")
            .authoritiesByUsernameQuery(
                "select u.username, r.rol as authority from usuario u, rol_usuario r " +
                "where u.id = r.usuario_id and username = ?"
            );
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filter(HttpSecurity http) throws Exception {
        return http
                .cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((requests) -> requests
                                .requestMatchers("/api/**").permitAll() // 👈 API pública para pruebas
                                .requestMatchers("/webjars/**", "/img/**", "/js/**",
                                        "/register/**", "/ayuda/**", "/login", "/codpos/**",
                                        "/denegado", "/error", "/acerca", "/categoria", "/productos")
                                .permitAll()
                                .requestMatchers("/admin/**", "/admin/*/**", "/admin/*/*/**", "/admin/*/*/*/*/*/**")
                                .hasAuthority("GESTOR")
                                .requestMatchers("/pedidos/**", "/pedidos/*/**", "/pedidos/*/*/**", "/pedidos/*/*/*/**", "/pedidos/*/*/*/*/**")
                                .hasAuthority("OPERARIO")
                                .requestMatchers("/mis-pedidos/**", "/mis-pedidos/*/**",
                                        "/productos/**", "/productos/*/**",
                                        "/mis-datos/**", "/mis-datos/*/**", "/mis-datos/*/*/**", "/mis-datos/*/*/*/**",
                                        "/carro/**", "/carro/*/**")
                                .hasAuthority("CLIENTE")
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling((exception) -> exception
                                .accessDeniedPage("/denegado")
                )
                .rememberMe(Customizer.withDefaults())
                .logout((logout) -> logout
                                .invalidateHttpSession(true)
                                .logoutSuccessUrl("/")
                                .permitAll()
                )
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200")); // Dominio Angular
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder)  throws Exception {
        return builder.getAuthenticationManager();
    }

    @Bean
    UserDetailsService detailsService() {
        return new JdbcUserDetailsManager(dataSource);
    }
}
