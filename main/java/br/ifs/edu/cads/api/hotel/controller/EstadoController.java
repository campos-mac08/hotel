package br.ifs.edu.cads.api.hotel.controller;

import br.ifs.edu.cads.api.hotel.dto.EstadoDTO;
import br.ifs.edu.cads.api.hotel.service.EstadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/estado")
public class EstadoController {

    private final EstadoService estadoService;

    public EstadoController(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @GetMapping
    public List<EstadoDTO> listar() {
        return estadoService.listar();
    }

    @Operation(summary = "Criar um novo estado", description = "Cria um novo estado com os dados fornecidos no corpo da requisição.")
    @ApiResponse(responseCode = "201", description = "Estado criado com sucesso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Requisição inválida. Verifique os dados fornecidos (nome obrigatório e sigla única)."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })

    @PostMapping
    public ResponseEntity<EstadoDTO> criar(@Valid @RequestBody EstadoDTO estadoDTO){
        EstadoDTO estadoCriado = estadoService.salvar(estadoDTO);
        URI location = URI.create("/api/estados/" + estadoCriado.sigla());
        return ResponseEntity.created(location).body(estadoCriado);
    }

    @PutMapping("/{sigla}")
    public ResponseEntity<EstadoDTO> atualizar(@PathVariable String sigla, @RequestBody EstadoDTO estadoDTO){
        estadoDTO = estadoService.atualizar(sigla, estadoDTO);
        return ResponseEntity.ok(estadoDTO);
    }

    @DeleteMapping("/{sigla}")
    public ResponseEntity<Void> deletar(@PathVariable String sigla) {
        estadoService.delete(sigla);
        return ResponseEntity.noContent().build();
    }
}
