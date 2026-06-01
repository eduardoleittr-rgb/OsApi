/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.eti.OSApiApplication.api.controller;

import br.edu.eti.OSApiApplication.domain.dto.AtualizaStatusDTO;
import br.edu.eti.OSApiApplication.domain.model.Cliente;
import br.edu.eti.OSApiApplication.domain.model.OrdemServico;
import br.edu.eti.OSApiApplication.domain.service.OrdemServicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author digma
 */
@RestController
@RequestMapping("/ordem-servico")
@SecurityRequirement(name = "ApiKeyAuth")
public class OrdemServicoController {
  

    @Autowired
    private OrdemServicoService ordemServicoService;
    
        @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServico criar(@RequestBody OrdemServico ordemServico) {
        return ordemServicoService.criar(ordemServico);
    }
    
@Operation(summary = "Get a product by id", description = "Returns aproduct as per the id")
@ApiResponses(value = {
 @ApiResponse(responseCode = "200", description = "Successfullyretrieved"),
 @ApiResponse(responseCode = "404", description = "Not found - Theproduct was not found")
 })

    
    @GetMapping("/cliente/{clienteId}")
public List<OrdemServico> listarPorCliente(@PathVariable
        @Parameter(name = "clienteId", description = "Product id", example = "1")Long clienteId) {
    return ordemServicoService.listarPorCliente(clienteId);
}
 @PutMapping("/atualiza-status/{ordemServicoID}")
public ResponseEntity<OrdemServico> atualizaStatus(
        @PathVariable Long ordemServicoID,
        @Valid @RequestBody AtualizaStatusDTO statusDTO) {

    Optional<OrdemServico> optOS = ordemServicoService.atualizaStatus(
            ordemServicoID,
            statusDTO.status());

    if (optOS.isPresent()) {
        return ResponseEntity.ok(optOS.get());

    } else {
        return ResponseEntity.notFound().build();

    }

}

}