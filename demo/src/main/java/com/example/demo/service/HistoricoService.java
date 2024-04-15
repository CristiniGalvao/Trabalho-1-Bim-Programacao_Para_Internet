package com.example.demo.service;
import com.example.demo.model.Historico;
import com.example.demo.repository.HistoricoRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class HistoricoService {
    @Inject
    private HistoricoRepository historicoRepository;

    public List<Historico> listar() {
        return historicoRepository.listarTodos();
    }

    public void cadastrar(Historico historico) {
        historicoRepository.cadastrar(historico);
    }

    public void atualizar(Historico historico) {
        historicoRepository.atualizar(historico);
    }

    public void excluir(Historico historico) {
        historicoRepository.excluir(historico);
    }

    public Historico buscarPorID(Integer id) {
        return historicoRepository.buscarPorID(id);
    }
    @PersistenceContext(unitName = "HibernateMaven")
    private EntityManager em;
    public void salvarHistorico(Historico historico) throws Exception {
        try {
            em.persist(historico);
        } catch (Exception ex) {
            throw new Exception("Não foi possível salvar o histórico da tarefa");
        }
    }
}
