package br.edu.eti.OSApiApplication.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany; 
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList; 
import java.util.List;      
import java.util.Objects;

@Entity
public class OrdemServico {

    @Schema(name = "OrdemServico ID", description = "ID único gerado pelo banco de dados", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;
    
    @Schema(name = "Descrição", description = "Detalhamento da Ordem de Serviço", example = "Formatação de computador e backup")
    private String descricao;

    @Schema(name = "Preço", description = "Valor cobrado pelo serviço prestado", example = "150.00")
    private BigDecimal preco;

    @Schema(name = "Status", description = "Estado atual da ordem de serviço", example = "ABERTA")
    @Enumerated(EnumType.STRING)
    private StatusOrdemServico status;

    @Schema(name = "Data de Abertura", description = "Data e hora em que a OS foi criada", example = "2026-06-01T09:00:00")
    private LocalDateTime dataAbertura;

    @Schema(name = "Data de Finalização", description = "Data e hora do encerramento da OS", example = "2026-06-02T18:30:00")
    private LocalDateTime dataFinalizacao;

    @OneToMany(mappedBy = "ordemServico")
    private List<Comentario> comentarios = new ArrayList<>();

    public OrdemServico() {
    }

    public OrdemServico(Cliente cliente, String descricao, BigDecimal preco) {
        this.cliente = cliente;
        this.descricao = descricao;
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public StatusOrdemServico getStatus() {
        return status;
    }

    public void setStatus(StatusOrdemServico status) {
        this.status = status;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDateTime getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(LocalDateTime dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrdemServico other = (OrdemServico) obj;
        return Objects.equals(this.id, other.id);
    }
}