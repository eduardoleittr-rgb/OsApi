/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.eti.OSApiApplication.api.controller;

import br.edu.eti.OSApiApplication.domain.model.Cliente;
import br.edu.eti.OSApiApplication.domain.model.OrdemServico;
import br.edu.eti.OSApiApplication.domain.service.OrdemServicoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
public class OrdemServicoController {
  

    @Autowired
    private OrdemServicoService ordemServicoService;
    
        @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServico criar(@RequestBody OrdemServico ordemServico) {
        return ordemServicoService.criar(ordemServico);
    }
//    @GetMapping("/cliente/{clienteId}")
//public List<OrdemServico> listarPorCliente(@PathVariable Long clienteId) {
//    return ordemServicoService.listarPorCliente(clienteId);
//}

}