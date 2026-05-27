package cl.triskeledu.productos.service;

import cl.triskeledu.productos.dto.CategoriaRequestDTO;
import cl.triskeledu.productos.dto.CategoriaResponseDTO;
import cl.triskeledu.productos.dto.ImagenRequestDTO;
import cl.triskeledu.productos.dto.ImagenResponseDTO;
import cl.triskeledu.productos.dto.ProductoRequestDTO;
import cl.triskeledu.productos.dto.ProductoResponseDTO;

import java.util.List;

public interface ProductoService {
    CategoriaResponseDTO crearCategoria(CategoriaRequestDTO request);
    List<CategoriaResponseDTO> listarCategorias();

    ProductoResponseDTO crearProducto(ProductoRequestDTO request);
    ProductoResponseDTO obtenerProductoPorSku(String sku);
    List<ProductoResponseDTO> listarProductos();
    List<ProductoResponseDTO> listarProductosPorCategoria(Integer categoriaId);

    ImagenResponseDTO agregarImagen(ImagenRequestDTO request);
}
