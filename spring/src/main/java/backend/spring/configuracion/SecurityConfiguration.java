package backend.spring.configuracion;

import javax.sql.DataSource;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import backend.spring.jwt.AuthEntryPointJwt;
import backend.spring.jwt.AuthTokenFilter;

    @Configuration
    @EnableWebSecurity
    public class SecurityConfiguration {

        @Autowired 
        DataSource dataSource;

        @Autowired
        private AuthEntryPointJwt unauthorizedHandler;

        @Bean
        public AuthTokenFilter authenticationJwtTokenFilter() {
            return new AuthTokenFilter();
        }
    /* 
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
    */
        @Bean
        BCryptPasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public SecurityFilterChain filter(HttpSecurity http) throws Exception {
            return http
                    .cors(Customizer.withDefaults())
                    .csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests((requests) -> requests
                                    .requestMatchers("/webjars/**", "/img/**", "/js/**",
                                           "/auth/**", "/error", "/api/productos")
                                    .permitAll()
                                    .requestMatchers("/api/carro/", "/api/carro/**")
                                    .authenticated()
                                    .requestMatchers("/admin/**", "/admin", "/api/pedidos", "/api/pedidos/**", 
                                        "/api/usuarios", "/api/usuarios/**", "/api/productos/**")
                                    .hasAuthority("GESTOR")
                                    .requestMatchers("/api/likes/**", "/api/likes")
                                    .hasAuthority("CLIENTE")
                    )
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                    .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
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
            JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
            manager.setUsersByUsernameQuery("select username, password, enabled from usuario where username = ?");
            manager.setAuthoritiesByUsernameQuery("select u.username, r.rol as authority from usuario u, rol_usuario r where u.id = r.usuario_id and username = ?");
            return manager;
        }

    }
