package cl.triskeledu.promociones.config;

import cl.triskeledu.promociones.model.Promocion;
import cl.triskeledu.promociones.repository.PromocionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {
    private final PromocionRepository promocionRepository;

    public DataInitializer(PromocionRepository promocionRepository) {
        this.promocionRepository = promocionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (promocionRepository.count() == 0) {
            promocionRepository.save(new Promocion(null, "VERANO2026", "Descuento de Verano", 15, LocalDateTime.now(), LocalDateTime.now().plusMonths(3), true));
            promocionRepository.save(new Promocion(null, "CYBER", "Cyber Monday", 30, LocalDateTime.now(), LocalDateTime.now().plusDays(3), true));
        }
    }
}
