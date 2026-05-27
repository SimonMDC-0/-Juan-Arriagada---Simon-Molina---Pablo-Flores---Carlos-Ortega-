package cl.triskeledu.productos.config;

import cl.triskeledu.productos.model.Categoria;
import cl.triskeledu.productos.model.Producto;
import cl.triskeledu.productos.repository.CategoriaRepository;
import cl.triskeledu.productos.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final CategoriaRepository categoriaRepository;
    private final ProductoRepository productoRepository;

    public DataInitializer(CategoriaRepository categoriaRepository, ProductoRepository productoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (categoriaRepository.count() == 0) {
            Categoria electro = categoriaRepository.save(new Categoria(null, "Electrodomésticos", "Artículos para el hogar"));
            Categoria tec = categoriaRepository.save(new Categoria(null, "Tecnología", "Computadores y celulares"));
            
            productoRepository.save(new Producto("SKU-1001", "Televisor LED 55", 350000, "Smart TV 4K", electro));
            productoRepository.save(new Producto("SKU-1002", "Notebook Gamer", 850000, "16GB RAM, RTX 4060", tec));
            productoRepository.save(new Producto("SKU-1003", "Smartphone Pro", 600000, "128GB, 5G", tec));
        }
    }
}
