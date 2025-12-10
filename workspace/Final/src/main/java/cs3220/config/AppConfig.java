package cs3220.config;

import no.api.freemarker.java8.Java8ObjectWrapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

/**
 * Application configuration for password encoding and FreeMarker java.time support.
 */
@Configuration
public class AppConfig {

    /**
     * BCrypt password encoder for secure password hashing.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Post-processor to add Java 8 date/time support to FreeMarker.
     * This allows proper formatting of LocalDateTime, OffsetDateTime, etc.
     */
    @Bean
    public BeanPostProcessor freeMarkerJava8PostProcessor() {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                if (bean instanceof FreeMarkerConfigurer configurer) {
                    configurer.getConfiguration().setObjectWrapper(
                            new Java8ObjectWrapper(freemarker.template.Configuration.VERSION_2_3_32)
                    );
                }
                return bean;
            }
        };
    }
}
