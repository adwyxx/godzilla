package adwyxx.godzilla.register;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RegisterApp {
    public static void main(String[] args) {
        new SpringApplicationBuilder(RegisterApp.class).web(true).run(args);
    }
}
