package API.GATEWAY.API.GATEWAY;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootApplication
public class ApiGatewayApplication {
	private static final Logger logger = LoggerFactory.getLogger(ApiGatewayApplication.class);
	public static void main(String[] args) {
		logger.info("Iniciando el API Gateway...");
		SpringApplication.run(ApiGatewayApplication.class, args);
		logger.info("API Gateway iniciado correctamente.");
	}
}