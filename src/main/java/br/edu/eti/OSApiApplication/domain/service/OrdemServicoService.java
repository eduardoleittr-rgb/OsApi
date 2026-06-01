package br.edu.eti.OSApiApplication.domain.service;

import br.edu.eti.OSApiApplication.domain.exception.DomainException;
import br.edu.eti.OSApiApplication.domain.model.OrdemServico;
import br.edu.eti.OSApiApplication.domain.model.StatusOrdemServico;
import br.edu.eti.OSApiApplication.domain.repository.OrdemServicoRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author digma
 */
@Service
public class OrdemServicoService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    public OrdemServico criar(OrdemServico ordemServico) {
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(LocalDateTime.now());
        return ordemServicoRepository.save(ordemServico);
    }

    public List<OrdemServico> listarPorCliente(Long clienteId){
        return ordemServicoRepository.findByClienteId(clienteId);
    }

   
    public List<OrdemServico> listarAbertasPorCliente(Long clienteId) {
        return ordemServicoRepository.findByClienteIdAndStatus(clienteId, StatusOrdemServico.ABERTA);
    }

    
    public List<OrdemServico> listarFechadasPorCliente(Long clienteId) {
        return ordemServicoRepository.findByClienteIdAndStatus(clienteId, StatusOrdemServico.FINALIZADA);
    }

   /*
    public List<OrdemServico> listarTodasPorComentario(boolean comComentarios) {
        if (comComentarios) {
            return ordemServicoRepository.findByComentariosIsNotEmpty();
        }
        return ordemServicoRepository.findByComentariosIsEmpty();
    }

    
    public List<OrdemServico> listarAbertasPorComentario(boolean comComentarios) {
        if (comComentarios) {
            return ordemServicoRepository.findByStatusAndComentariosIsNotEmpty(StatusOrdemServico.ABERTA);
        }
        return ordemServicoRepository.findByStatusAndComentariosIsEmpty(StatusOrdemServico.ABERTA);
    }

    
    public List<OrdemServico> listarFechadasPorComentario(boolean comComentarios) {
        StatusOrdemServico statusFechado = StatusOrdemServico.FINALIZADA; // Ajuste para o seu Enum (FECHADA ou FINALIZADA)
        if (comComentarios) {
            return ordemServicoRepository.findByStatusAndComentariosIsNotEmpty(statusFechado);
        }
        return ordemServicoRepository.findByStatusAndComentariosIsEmpty(statusFechado);
    }
*/
    public Optional<OrdemServico> atualizaStatus(Long ordemServicoID, StatusOrdemServico status) {
        Optional<OrdemServico> optOrdemServico = ordemServicoRepository.findById(ordemServicoID);

        if (optOrdemServico.isPresent()) {
            OrdemServico ordemServico = optOrdemServico.get();

            if (ordemServico.getStatus() == StatusOrdemServico.ABERTA
                    && status != StatusOrdemServico.ABERTA) {

                ordemServico.setStatus(status);
                ordemServico.setDataFinalizacao(LocalDateTime.now());
                ordemServicoRepository.save(ordemServico);
                return Optional.of(ordemServico);
            } else {
                return Optional.empty();
            }
        } else {
            throw new DomainException("Não existe OS com o id " + ordemServicoID);
        }
    }
}