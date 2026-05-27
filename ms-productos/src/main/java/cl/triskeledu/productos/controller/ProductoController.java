package cl.triskeledu.productos.controller;

import cl.triskeledu.productos.dto.CategoriaRequestDTO;
import cl.triskeledu.productos.dto.CategoriaResponseDTO;
import cl.triskeledu.productos.dto.ImagenRequestDTO;
import cl.triskeledu.productos.dto.ImagenResponseDTO;
import cl.triskeledu.productos.dto.ProductoRequestDTO;
import cl.triskeledu.productos.dto.ProductoResponseDTO;
import cl.triskeledu.productos.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping("/categorias")
    public ResponseEntity<CategoriaResponseDTO> crearCategoria(@RequestBody CategoriaRequestDTO request) {
        return new ResponseEntity<>(productoService.crearCategoria(request), HttpStatus.CREATED);
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<CategoriaResponseDTO>> listarCategorias() {
        return ResponseEntity.ok(productoService.listarCategorias());
    }

    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crearProducto(@RequestBody ProductoRequestDTO request) {
        return new ResponseEntity<>(productoService.crearProducto(request), HttpStatus.CREATED);
    }

    @GetMapping("/{sku}")
    public ResponseEntity<ProductoResponseDTO> obtenerProducto(@PathVariable String sku) {
        return ResponseEntity.ok(productoService.obtenerProductoPorSku(sku));
    }

    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> listarProductos() {
        return ResponseEntity.ok(productoService.listarProductos());
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<ProductoResponseDTO>> listarProductosPorCategoria(@PathVariable Integer categoriaId) {
        return ResponseEntity.ok(productoService.listarProductosPorCategoria(categoriaId));
    }

    @PostMapping("/{sku}/imagenes")
    public ResponseEntity<ImagenResponseDTO> agregarImagen(@PathVariable String sku, @RequestBody ImagenRequestDTO request) {
        ImagenRequestDTO dto = new ImagenRequestDTO(sku, request.urlImagen(), request.esPrincipal());
        return new ResponseEntity<>(productoService.agregarImagen(dto), HttpStatus.CREATED);
    }
}
