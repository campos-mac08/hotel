package br.ifs.edu.cads.api.hotel.controller;

import br.ifs.edu.cads.api.hotel.dto.CidadeDTO;
import br.ifs.edu.cads.api.hotel.service.CidadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    private final CidadeService cidadeService;

    public CidadeController(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CidadeDTO> buscarPorId(@PathVariable Long id){
        CidadeDTO cidadeDTO = cidadeService.buscarPorId(id);
        return ResponseEntity.ok(cidadeDTO);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<CidadeDTO> buscarPorNome(@PathVariable String nome){
        CidadeDTO cidadeDTO = cidadeService.buscarPorNome(nome);
        return ResponseEntity.ok(cidadeDTO);
    }

    @GetMapping
    public ResponseEntity<List<CidadeDTO>> listarTodos(){
        List<CidadeDTO> cidades = cidadeService.listarTodos();
        return ResponseEntity.ok(cidades);
    }

    @GetMapping("/estadoId/{sigla}")
    public ResponseEntity<List<CidadeDTO>> listarPorEstado(@PathVariable String sigla){
        List<CidadeDTO> cidadesPorEstado = cidadeService.listarPorEstado(sigla);
        return ResponseEntity.ok(cidadesPorEstado);
    }

    @Operation(summary = "Criar uma nova cidade", description = "Cria uma nova cidade com os dados fornecidos no corpo da requisição.")
    @ApiResponse(responseCode = "201", description = "Cidade criada com sucesso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Requisição inválida. Verifique os dados fornecidos."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public ResponseEntity<CidadeDTO> criar(@Valid @RequestBody CidadeDTO cidadeDTO){
        CidadeDTO cidadeCriada = cidadeService.criar(cidadeDTO);
        URI location = URI.create("/cidades/" + cidadeCriada.id());
        return ResponseEntity.created(location).body(cidadeCriada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CidadeDTO> atualizar(@PathVariable Long id, @RequestBody CidadeDTO cidadeDTO){
        cidadeDTO = cidadeService.atualizar(id, cidadeDTO);
        return ResponseEntity.ok(cidadeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CidadeDTO> deletar(@PathVariable Long id){
        cidadeService.deletar(id);
        return ResponseEntity.noContent().build();
    }




}
