package com.example.demo.repository;
import com.example.demo.model.Historico;
import com.example.demo.model.Task;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class TaskRepository {
    @PersistenceContext(unitName = "HibernateMaven")
    private EntityManager em;

    public List<Task> listarTodos() {
        return em.createQuery("SELECT t FROM Task t", Task.class).getResultList();
    }

    public Task buscarPorID(Integer id) {
        return em.find(Task.class, id);
    }

    public void cadastrar(Task task) {
        em.persist(task);
    }

    public void atualizar(Task task) {
        em.merge(task);
    }

    public void excluir(Task task) {
        em.remove(task);
    }
    @PersistenceContext(unitName = "HibernateMaven")

    public void salvarHistorico(Historico historico) throws Exception {
        try {
            em.persist(historico);
        } catch (Exception ex) {
            throw new Exception("Não foi possível salvar o histórico da tarefa");
        }
    }
}
