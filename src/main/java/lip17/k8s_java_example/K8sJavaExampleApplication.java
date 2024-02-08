package lip17.k8s_java_example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(
    basePackages = {
        "lip17.k8s_java_example"
    }
)
public class K8sJavaExampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(K8sJavaExampleApplication.class, args);
    }

}
