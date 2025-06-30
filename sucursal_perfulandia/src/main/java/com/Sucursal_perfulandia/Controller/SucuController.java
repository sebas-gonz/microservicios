package com.Sucursal_perfulandia.Controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Sucursal_perfulandia.Services.ServiceSucursal;
import com.Sucursal_perfulandia.configuracion.SucursalAssembler;
import com.Sucursal_perfulandia.dto.BoletaDTO;
import com.Sucursal_perfulandia.dto.EmpleadoDTO;
import com.Sucursal_perfulandia.dto.InventarioDTO;
import com.Sucursal_perfulandia.dto.PedidoDTO;
import com.Sucursal_perfulandia.entidad.Sucursal;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/sucursal")
@Tag(name = "sucursales", description = "Operaciones relacionados con las sucursales, con la informacion de sus inventarios, boletas emitidas y empleados de la sucursal")
public class SucuController {
    @Autowired
    private ServiceSucursal serviceSucursal;
    
    @Autowired
    private SucursalAssembler assembler;

    @PostMapping("/")
    @Operation(summary = "Crear una sucursal", description = "Crea una entidad sucursal")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Sucursal creada"),
    		@ApiResponse(responseCode = "400", description = "Datos de la sucursal erroneos")
    })
    public ResponseEntity<EntityModel<Sucursal>> crearSucu(@RequestBody Sucursal sucursal){
        Sucursal sucursalAgregar = serviceSucursal.agregarsuSucursal(sucursal);
        return ResponseEntity.ok(assembler.toModel(sucursalAgregar));
    }
    
    @GetMapping("/")
    @Operation(summary = "Obtener una lista de todas las sucursales", description = "Obtiene un arreglo con todas las sucursales registradas en el sistema.")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Lista de sucursales obtenidas"),
    		@ApiResponse(responseCode = "204", description = "No existen sucursales en el sistema")
    })
    public ResponseEntity<CollectionModel<EntityModel<Sucursal>>> cargarSucursales(){
        List<Sucursal> sucursales = serviceSucursal.GetSucursal();
        if (sucursales == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(assembler.modelToCollection(sucursales));    
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Cargar una sucursal en especifico", description = "Obtiene una sucursal en especifico mediante el id")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "200", description = "Sucursal cargada correctamente"),
    		@ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @Parameter(name = "id", description = "Identificador primario de la sucursal",
    			required = true,
    			example = "1")
    public ResponseEntity<EntityModel<Sucursal>> CargarSucuId(@PathVariable("id") int id){
        Sucursal sucursal = serviceSucursal.buscarIdSucu(id);
        if (sucursal == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(sucursal));
        
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una sucursal ", description = "Eliminar una sucursal en especifico mediante el id")
    @ApiResponses(value = {
    		@ApiResponse(responseCode = "204", description = "Sucursal eliminada correctamente"),
    		@ApiResponse(responseCode = "404", description = "")
    })
    @Parameter(name = "id", description = "Identificador primario de la sucursal",
	required = true,
	example = "1")
    public ResponseEntity<Sucursal> EliminarSucuId(@PathVariable("id") int id){
        Sucursal encontrarSucu = serviceSucursal.buscarIdSucu(id);
        if(encontrarSucu == null){
            return ResponseEntity.notFound().build(); 
        }
        serviceSucursal.eliminarSucursal(id);
        return ResponseEntity.noContent().build();
    }
    
    
    @PutMapping("/{id}")
    @Operation(
        summary = "Actualizar una sucursal",
        description = "Actualiza los datos de una sucursal existente usando su ID."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucursal actualizada exitosamente."),
        @ApiResponse(responseCode = "404", description = "Sucursal no encontrada con el ID proporcionado.")
    })
    @Parameter(name = "id", description = "Identificador primario de la sucursal",
	required = true,
	example = "1")
    public ResponseEntity<EntityModel<Sucursal>> actualizarSucursal(@PathVariable("id") int id, @RequestBody Sucursal sucursal){

        serviceSucursal.actualizarSucursal(sucursal, id);
        return ResponseEntity.ok(assembler.toModel(sucursal));
    }
    
    @GetMapping("/{sucursalid}/boletas")
    @Operation(
        summary = "Obtener boletas por sucursal",
        description = "Retorna una lista de boletas asociadas a una sucursal específica mediante su ID."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de boletas obtenida exitosamente."),
        @ApiResponse(responseCode = "204", description = "No hay boletas asociadas a la sucursal."),
        @ApiResponse(responseCode = "400", description = "ID de sucursal inválido.")
    })
    @Parameter(name = "sucursalid", description = "Identificador primario de la sucursal",
	required = true,
	example = "1")
    public ResponseEntity<List<BoletaDTO>> boletasSurcursal(@PathVariable("sucursalid")int sucursalId){
    	List<BoletaDTO> boletas = serviceSucursal.boletasSurcursal(sucursalId);
    	return boletas == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(boletas);
    }
    
    @GetMapping("/{sucursalid}/empleados")
    @Operation(
        summary = "Obtener empleados de una sucursal",
        description = "Devuelve una lista de empleados asociados a una sucursal específica identificada por su ID."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de empleados obtenida exitosamente."),
        @ApiResponse(responseCode = "204", description = "No se encontraron empleados para la sucursal indicada."),
    })
    @Parameter(name = "sucursalid", description = "Identificador primario de la sucursal",
	required = true,
	example = "1")
    public ResponseEntity<List<EmpleadoDTO>> empleadosSucursal(@PathVariable("sucursalid")int sucursalId){
    	List<EmpleadoDTO> empleados = serviceSucursal.empleadosSucursal(sucursalId);
    	return empleados == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(empleados);
    }
    
    @GetMapping("/{sucursalid}/inventario")
    @Operation(
        summary = "Obtener inventario de una sucursal",
        description = "Devuelve la lista de productos disponibles en el inventario de una sucursal específica."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inventarios obtenidos"),
        @ApiResponse(responseCode = "204", description = "La sucursal no tiene productos en inventario"),
        @ApiResponse(responseCode = "404", description = "Sucursal no encontrada"),
    })
    @Parameter(name = "sucursalid", description = "Identificador primario de la sucursal",
	required = true,
	example = "1")
    public ResponseEntity<List<InventarioDTO>> inventarioSucursal(@PathVariable("sucursalid")int sucursalId){
    	List<InventarioDTO> inventarios = serviceSucursal.inventariosSucursal(sucursalId);
    	return inventarios != null ? ResponseEntity.ok(inventarios) : ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{sucursalid}/pedido")
    @Operation(
            summary = "Obtener pedidos de una sucursal",
            description = "Devuelve la lista de los pedidos realizados en una sucursal específica."
        )
        @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedidos encontrados"),
            @ApiResponse(responseCode = "204", description = "La sucursal no tiene pedidos"),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada"),
        })
    @Parameter(name = "sucursalid", description = "Identificador primario de la sucursal",
	required = true,
	example = "1")
    public ResponseEntity<List<PedidoDTO>> pedidosPorSucursal(@PathVariable("sucursalid")int sucursalId){
    	List<PedidoDTO> pedidos = serviceSucursal.pedidosPorSucursal(sucursalId);
    	return pedidos != null ? ResponseEntity.ok(pedidos) : ResponseEntity.noContent().build();
    }
}
