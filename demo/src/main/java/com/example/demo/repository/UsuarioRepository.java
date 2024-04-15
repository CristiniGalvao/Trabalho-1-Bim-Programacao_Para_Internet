package com.example.demo.repository;
import com.example.demo.model.Usuario;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
public class UsuarioRepository {
    @PersistenceContext(unitName = "HibernateMaven")
    private EntityManager em;

    public List<Usuario> listarTodos() {
        return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
    }

    public Usuario buscarPorID(Integer id) {
        return em.find(Usuario.class, id);
    }

    public void cadastrar(Usuario usuario) {
        em.persist(usuario);
    }

    public void atualizar(Usuario usuario) {
        em.merge(usuario);
    }

    public void excluir(Usuario usuario) {
        em.remove(usuario);
    }
}
