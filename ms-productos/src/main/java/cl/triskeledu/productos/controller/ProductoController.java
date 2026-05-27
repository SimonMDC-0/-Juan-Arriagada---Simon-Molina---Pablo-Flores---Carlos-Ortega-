package cl.triskeledu.productos.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping("/categorias")
    public ResponseEntity<CategoriaResponseDTO> crearCategoria(@Valid @RequestBody CategoriaRequestDTO request) {
        log.info("Ejecutando método en el controlador");
        return new ResponseEntity<>(productoService.crearCategoria(request), HttpStatus.CREATED);
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<CategoriaResponseDTO>> listarCategorias() {
        log.info("Ejecutando método en el controlador");
        return ResponseEntity.ok(productoService.listarCategorias());
    }

    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crearProducto(@Valid @RequestBody ProductoRequestDTO request) {
        log.info("Ejecutando método en el controlador");
        return new ResponseEntity<>(productoService.crearProducto(request), HttpStatus.CREATED);
    }

    @GetMapping("/{sku}")
    public ResponseEntity<ProductoResponseDTO> obtenerProducto(@PathVariable String sku) {
        log.info("Ejecutando método en el controlador");
        return ResponseEntity.ok(productoService.obtenerProductoPorSku(sku));
    }

    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> listarProductos() {
        log.info("Ejecutando método en el controlador");
        return ResponseEntity.ok(productoService.listarProductos());
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<ProductoResponseDTO>> listarProductosPorCategoria(@PathVariable Integer categoriaId) {
        log.info("Ejecutando método en el controlador");
        return ResponseEntity.ok(productoService.listarProductosPorCategoria(categoriaId));
    }

    @PostMapping("/{sku}/imagenes")
    public ResponseEntity<ImagenResponseDTO> agregarImagen(@PathVariable String sku, @Valid @RequestBody ImagenRequestDTO request) {
        log.info("Ejecutando método en el controlador");
        ImagenRequestDTO dto = new ImagenRequestDTO(sku, request.urlImagen(), request.esPrincipal());
        return new ResponseEntity<>(productoService.agregarImagen(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{sku}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable String sku) {
        productoService.eliminarProducto(sku);
        return ResponseEntity.noContent().build();
    }
}
