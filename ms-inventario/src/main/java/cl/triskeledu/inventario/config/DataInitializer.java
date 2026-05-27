package cl.triskeledu.inventario.config;

import cl.triskeledu.inventario.model.Inventario;
import cl.triskeledu.inventario.repository.InventarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final InventarioRepository inventarioRepository;

    public DataInitializer(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (inventarioRepository.count() == 0) {
            inventarioRepository.save(new Inventario(null, "SKU-1001", 1, 50));
            inventarioRepository.save(new Inventario(null, "SKU-1002", 1, 20));
            inventarioRepository.save(new Inventario(null, "SKU-1003", 2, 100));
        }
    }
}
