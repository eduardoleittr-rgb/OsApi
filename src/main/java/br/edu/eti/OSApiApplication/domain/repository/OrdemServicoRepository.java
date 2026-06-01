package br.edu.eti.OSApiApplication.domain.repository;

import br.edu.eti.OSApiApplication.domain.model.OrdemServico;
import br.edu.eti.OSApiApplication.domain.model.StatusOrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {

    // Busca buscando a propriedade 'id' de dentro do objeto 'cliente'
    List<OrdemServico> findByClienteId(Long clienteId);

    // 1 e 2. Listar OS por cliente e status
    List<OrdemServico> findByClienteIdAndStatus(Long clienteId, StatusOrdemServico status);

    // 3. Listar todas as OS Com e Sem Comentários
    List<OrdemServico> findByComentariosIsNotEmpty();
    List<OrdemServico> findByComentariosIsEmpty();

    // 4 e 5. Listar por Status Com e Sem Comentários
    List<OrdemServico> findByStatusAndComentariosIsNotEmpty(StatusOrdemServico status);
    List<OrdemServico> findByStatusAndComentariosIsEmpty(StatusOrdemServico status);
}