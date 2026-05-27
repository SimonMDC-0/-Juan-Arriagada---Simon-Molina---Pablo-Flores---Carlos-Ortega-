package cl.triskeledu.facturacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TiendaFacturacionApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiendaFacturacionApplication.class, args);
	}

}
