package cl.triskeledu.productos.service;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import cl.triskeledu.productos.dto.CategoriaRequestDTO;
import cl.triskeledu.productos.dto.CategoriaResponseDTO;
import cl.triskeledu.productos.dto.ImagenRequestDTO;
import cl.triskeledu.productos.dto.ImagenResponseDTO;
import cl.triskeledu.productos.dto.ProductoRequestDTO;
import cl.triskeledu.productos.dto.ProductoResponseDTO;
import cl.triskeledu.productos.exceptions.BadRequestException;
import cl.triskeledu.productos.exceptions.ResourceNotFoundException;
import cl.triskeledu.productos.model.Categoria;
import cl.triskeledu.productos.model.Imagen;
import cl.triskeledu.productos.model.Producto;
import cl.triskeledu.productos.repository.CategoriaRepository;
import cl.triskeledu.productos.repository.ImagenRepository;
import cl.triskeledu.productos.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductoServiceImpl implements ProductoService {

    private final CategoriaRepository categoriaRepository;
    private final ProductoRepository productoRepository;
    private final ImagenRepository imagenRepository;

    public ProductoServiceImpl(
            CategoriaRepository categoriaRepository,
            ProductoRepository productoRepository,
            ImagenRepository imagenRepository) {
        this.categoriaRepository = categoriaRepository;
        this.productoRepository = productoRepository;
        this.imagenRepository = imagenRepository;
    }

    @Override
    @Transactional
    public CategoriaResponseDTO crearCategoria(CategoriaRequestDTO request) {
        log.info("Ejecutando lógica de servicio");
        if (categoriaRepository.findByNombreCategoria(request.nombreCategoria()).isPresent()) {
            throw new BadRequestException("La categoría " + request.nombreCategoria() + " ya existe.");
        }
        Categoria categoria = new Categoria(null, request.nombreCategoria(), request.descripcion());
        Categoria saved = categoriaRepository.save(categoria);
        return new CategoriaResponseDTO(saved.getId(), saved.getNombreCategoria(), saved.getDescripcion());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaResponseDTO> listarCategorias() {
        log.info("Ejecutando lógica de servicio");
        return categoriaRepository.findAll().stream()
                .map(c -> new CategoriaResponseDTO(c.getId(), c.getNombreCategoria(), c.getDescripcion()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductoResponseDTO crearProducto(ProductoRequestDTO request) {
        log.info("Ejecutando lógica de servicio");
        if (productoRepository.existsById(request.sku())) {
            throw new BadRequestException("El producto con SKU " + request.sku() + " ya existe.");
        }
        Categoria categoria = categoriaRepository.findByNombreCategoria(request.nombreCategoria())
                .orElseThrow(() -> new ResourceNotFoundException("Categoría " + request.nombreCategoria() + " no encontrada."));

        Producto producto = new Producto(
                request.sku(),
                request.nombre(),
                request.precio(),
                request.descripcion(),
                categoria
        );
        Producto saved = productoRepository.save(producto);
        return mapToProductoResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoResponseDTO obtenerProductoPorSku(String sku) {
        log.info("Ejecutando lógica de servicio");
        Producto producto = productoRepository.findById(sku)
                .orElseThrow(() -> new ResourceNotFoundException("Producto con SKU " + sku + " no encontrado."));
        return mapToProductoResponse(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> listarProductos() {
        log.info("Ejecutando lógica de servicio");
        return productoRepository.findAll().stream()
                .map(this::mapToProductoResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> listarProductosPorCategoria(Integer categoriaId) {
        log.info("Ejecutando lógica de servicio");
        if (!categoriaRepository.existsById(categoriaId)) {
            throw new ResourceNotFoundException("Categoría " + categoriaId + " no encontrada.");
        }
        return productoRepository.findByCategoriaId(categoriaId).stream()
                .map(this::mapToProductoResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ImagenResponseDTO agregarImagen(ImagenRequestDTO request) {
        log.info("Ejecutando lógica de servicio");
        if (!productoRepository.existsById(request.productoSku())) {
            throw new ResourceNotFoundException("Producto con SKU " + request.productoSku() + " no encontrado.");
        }
        Imagen imagen = new Imagen(
                null,
                request.productoSku(),
                request.urlImagen(),
                request.esPrincipal() != null ? request.esPrincipal() : false
        );
        Imagen saved = imagenRepository.save(imagen);
        return new ImagenResponseDTO(saved.getId(), saved.getProductoSku(), saved.getUrlImagen(), saved.getEsPrincipal());
    }

    @Override
    @Transactional
    public void eliminarProducto(String sku) {
        log.info("Ejecutando lógica de servicio");
        if (!productoRepository.existsById(sku)) {
            throw new ResourceNotFoundException("Producto con SKU " + sku + " no encontrado.");
        }
        
        List<Imagen> imagenes = imagenRepository.findByProductoSku(sku);
        if (!imagenes.isEmpty()) {
            imagenRepository.deleteAll(imagenes);
        }
        
        productoRepository.deleteById(sku);
    }

    private ProductoResponseDTO mapToProductoResponse(Producto producto) {
        List<String> imagenes = imagenRepository.findByProductoSku(producto.getSku()).stream()
                .map(Imagen::getUrlImagen)
                .collect(Collectors.toList());

        return new ProductoResponseDTO(
                producto.getSku(),
                producto.getNombre(),
                producto.getPrecio(),
                producto.getDescripcion(),
                producto.getCategoria() != null ? producto.getCategoria().getNombreCategoria() : null,
                imagenes
        );
    }
}
