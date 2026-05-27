package cl.triskeledu.pedidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TiendaPedidosApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiendaPedidosApplication.class, args);
	}

}
