package com.example.demo.repository;
import com.example.demo.model.Historico;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class HistoricoRepository {
    @PersistenceContext(unitName = "HibernateMaven")
    private EntityManager em;

    public List<Historico> listarTodos() {
        return em.createQuery("SELECT h FROM Historico h", Historico.class).getResultList();
    }

    public Historico buscarPorID(Integer id) {
        return em.find(Historico.class, id);
    }

    public void cadastrar(Historico historico) {
        em.persist(historico);
    }

    public void atualizar(Historico historico) {
        em.merge(historico);
    }

    public void excluir(Historico historico) {
        em.remove(historico);
    }
}
