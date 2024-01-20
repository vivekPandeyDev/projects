package v3.spring.profile.annotation;

import org.springframework.context.annotation.Profile;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Profile("prod")
@Retention(RetentionPolicy.RUNTIME)
public @interface RazorPayProfile {
}
