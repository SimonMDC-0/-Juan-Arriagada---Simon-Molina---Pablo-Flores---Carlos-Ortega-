package cl.triskeledu.tiendas.config;

import cl.triskeledu.tiendas.model.Tienda;
import cl.triskeledu.tiendas.repository.TiendaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final TiendaRepository tiendaRepository;

    public DataInitializer(TiendaRepository tiendaRepository) {
        this.tiendaRepository = tiendaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (tiendaRepository.count() == 0) {
            tiendaRepository.save(new Tienda(null, "Tienda Central", "Av. Principal 123", "+56911112222", "09:00 - 18:00"));
            tiendaRepository.save(new Tienda(null, "Sucursal Norte", "Calle Norte 456", "+56933334444", "10:00 - 19:00"));
        }
    }
}
