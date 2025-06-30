package com.producto_perfulandia.controllador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.producto_perfulandia.configuracion.ProductoAssembler;
import com.producto_perfulandia.dto.DetalleBoletaDTO;
import com.producto_perfulandia.dto.InventarioDTO;
import com.producto_perfulandia.entidad.Producto;
import com.producto_perfulandia.servicio.ProductoServicio;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/producto")
@Tag(name = "Producto", description = "Operaciones relacionadas con productos")
public class ControladorProducto {
    @Autowired
    private ProductoServicio productoServicio;
    
    @Autowired
	private ProductoAssembler assembler;
    
    @GetMapping("/")
    @Operation(
            summary = "Listar todos los productos",
            description = "Retorna una lista con todos los productos registrados en el sistema"
        )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de productos obtenida correctamente"),
            @ApiResponse(responseCode = "204", description = "No hay productos disponibles"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
    public ResponseEntity<CollectionModel<EntityModel<Producto>>> listarProductos(){
        List<Producto> productos = productoServicio.GetProductos();
        if(productos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(assembler.modelToCollection(productos));

    }
    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener un producto por ID",
            description = "Retorna los datos del producto correspondiente al ID proporcionado"
        )
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado exitosamente"),
            @ApiResponse(responseCode = "204", description = "No se encontró ningún producto con ese ID"),
            @ApiResponse(responseCode = "400", description = "ID inválido"),
        })
    @Parameter(
    	    name = "id",
    	    description = "ID del producto",
    	    required = true,
    	    example = "1"
    	)
    public ResponseEntity<EntityModel<Producto>> CargarProducto(@PathVariable("id")int id){
        Producto producto = productoServicio.getProductoIdProducto(id);
        if(producto.equals(null)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(assembler.toModel(producto));
    }
    
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar un producto",
            description = "Elimina el producto identificado por su ID del sistema"
        )
        @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Producto eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
    @Parameter(
    	    name = "id",
    	    description = "ID del producto",
    	    required = true,
    	    example = "1"
    	)
    public ResponseEntity<Producto> BorrarProducto(@PathVariable("id")int id){
        Producto producto = productoServicio.getProductoIdProducto(id);
        if(producto == null){
            return ResponseEntity.notFound().build();

        }
        productoServicio.borrarProductoId(id);
        return ResponseEntity.noContent().build();
    }
    
    
    @PostMapping("/")
    @Operation(
            summary = "Agregar un nuevo producto",
            description = "Crea y guarda un nuevo producto en el sistema a partir de los datos enviados en el cuerpo de la solicitud"
        )
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o malformateados"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
    public ResponseEntity<EntityModel<Producto>> agregarProducto(@RequestBody Producto producto){
    	productoServicio.agregarProducto(producto);
        return ResponseEntity.ok(assembler.toModel(producto));  
    }
    
    @Operation(
            summary = "Actualizar un producto existente",
            description = "Actualiza los datos de un producto identificado por su ID con los nuevos valores enviados en el cuerpo de la solicitud"
        )
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida o datos malformateados"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
    @Parameter(
    	    name = "id",
    	    description = "ID del producto",
    	    required = true,
    	    example = "1"
    	)
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Producto>> ActualizarProducto(@RequestBody Producto productoAct, @PathVariable("id")int id){
    	Producto producto = productoServicio.actualizarProducto(productoAct, id);
    	return producto != null ? ResponseEntity.ok(assembler.toModel(producto)) : ResponseEntity.notFound().build();
    }
    
    
    @Operation(
    	    summary = "Listar boletas asociadas a un producto",
    	    description = "Obtiene una lista de todos los detalles de boleta donde aparece el producto indicado por su ID"
    	)
    	@ApiResponses(value = {
    	    @ApiResponse(responseCode = "200", description = "Detalles de boleta encontrados"),
    	    @ApiResponse(responseCode = "204", description = "El producto no tiene boletas asociadas"),
    	    @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
    	    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    	})
    @Parameter(
    	    name = "productoid",
    	    description = "ID del producto",
    	    required = true,
    	    example = "1"
    	)
    @GetMapping("/{productoid}/detalle")
    public ResponseEntity<List<DetalleBoletaDTO>> boletasProducto(@PathVariable("productoid")int productoId){
    	List<DetalleBoletaDTO> detalles = productoServicio.boletasProducto(productoId);
    	return detalles == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(detalles);
    }
    @Operation(
    	    summary = "Listar inventarios asociados a un producto",
    	    description = "Devuelve una lista de inventarios donde se encuentra registrado el producto especificado por su ID"
    	)
    	@ApiResponses(value = {
    	    @ApiResponse(responseCode = "200", description = "Inventarios encontrados para el producto"),
    	    @ApiResponse(responseCode = "204", description = "No hay inventarios asociados al producto"),
    	    @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
    	    @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    	})
    @Parameter(
    	    name = "productoid",
    	    description = "ID del producto",
    	    required = true,
    	    example = "1"
    	)
    @GetMapping("/{productoid}/inventario")
    public ResponseEntity<List<InventarioDTO>> inventariosPorProducto(@PathVariable("productoid")int productoId){
    	List<InventarioDTO> inventarios = productoServicio.inventarioPorProducto(productoId);
    	return inventarios != null ? ResponseEntity.ok(inventarios) : ResponseEntity.noContent().build();
    }
    
}


