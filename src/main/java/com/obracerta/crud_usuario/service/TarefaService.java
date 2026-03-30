package com.obracerta.crud_usuario.service;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.obracerta.crud_usuario.dto.TarefaRequestDTO;
import com.obracerta.crud_usuario.dto.TarefaResponseDTO;
import com.obracerta.crud_usuario.model.Prioridade;
import com.obracerta.crud_usuario.model.Projeto;
import com.obracerta.crud_usuario.model.Tarefa;
import com.obracerta.crud_usuario.repository.ProjetoRepository;
import com.obracerta.crud_usuario.repository.TarefaRepository;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private ProjetoService projetoService;

    public Tarefa criarTarefa(TarefaRequestDTO dto) {
        if (dto.getItensAFazer() <= 0) {
            throw new IllegalArgumentException("O total de itens a fazer deve ser maior que zero.");
        }

        Projeto projeto = projetoRepository.findById(dto.getProjetoId())
            .orElseThrow(() -> new RuntimeException("Projeto não encontrado com ID: " + dto.getProjetoId()));

        Tarefa novaTarefa = new Tarefa();
        novaTarefa.setProjeto(projeto);
        novaTarefa.setNome(dto.getNome());
        novaTarefa.setItensAFazer(dto.getItensAFazer());
        novaTarefa.setQuantidadeFeita(0);
        novaTarefa.setConcluida(false);
        novaTarefa.setPrioridade(Prioridade.valueOf(dto.getPrioridade().toUpperCase()));

        Tarefa tarefaSalva = tarefaRepository.save(novaTarefa);
        projetoService.recalcularProgresso(projeto.getId());

        return tarefaSalva;
    }

    public TarefaResponseDTO atualizarQuantidade(Long id, int novaQuantidade) {
        Tarefa tarefa = tarefaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tarefa não encontrada com ID: " + id));

        if (novaQuantidade < 0) {
            throw new IllegalArgumentException("A quantidade feita não pode ser negativa.");
        }
        if (novaQuantidade > tarefa.getItensAFazer()) {
            throw new IllegalArgumentException(
                "A quantidade feita (" + novaQuantidade +
                ") não pode exceder o total de itens (" + tarefa.getItensAFazer() + ")."
            );
        }

        tarefa.setQuantidadeFeita(novaQuantidade);
        tarefa.setConcluida(novaQuantidade >= tarefa.getItensAFazer());

        Tarefa tarefaAtualizada = tarefaRepository.save(tarefa);
        projetoService.recalcularProgresso(tarefaAtualizada.getProjeto().getId());

        return new TarefaResponseDTO(tarefaAtualizada);
    }

    public List<TarefaResponseDTO> listarTarefasPorProjeto(Long projetoId) {
        if (!projetoRepository.existsById(projetoId)) {
            throw new RuntimeException("Projeto não encontrado com ID: " + projetoId);
        }
        return tarefaRepository.findByProjetoId(projetoId).stream()
            .map(TarefaResponseDTO::new)
            .collect(Collectors.toList());
    }

    public TarefaResponseDTO buscarTarefaPorId(Long id) {
        Tarefa tarefa = tarefaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tarefa não encontrada com ID: " + id));
        return new TarefaResponseDTO(tarefa);
    }

    public void deletarTarefa(Long id) {
        Tarefa tarefa = tarefaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tarefa não encontrada com ID: " + id));

        Long projetoId = tarefa.getProjeto().getId();
        tarefaRepository.delete(tarefa);
        projetoService.recalcularProgresso(projetoId);
    }
}