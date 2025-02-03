package repository;

import model.UsuarioModel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class UsuarioRepository {
    private EntityManager entityManager;

    public UsuarioRepository() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("crudHibernatePU");
        this.entityManager = factory.createEntityManager();
    }

    public String salvar(UsuarioModel usuario) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(usuario);
            entityManager.getTransaction().commit();
            return "Usuário salvo com sucesso!";
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return "Erro ao salvar usuário: " + e.getMessage();
        }
    }

    public List<UsuarioModel> buscarTodos() {
        return entityManager.createQuery("from UsuarioModel", UsuarioModel.class).getResultList();
    }

    public UsuarioModel buscarPorId(Long id) {
        return entityManager.find(UsuarioModel.class, id);
    }

    public String atualizar(UsuarioModel usuario) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(usuario);
            entityManager.getTransaction().commit();
            return "Usuário atualizado com sucesso!";
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return "Erro ao atualizar usuário: " + e.getMessage();
        }
    }

    public String remover(Long id) {
        try {
            entityManager.getTransaction().begin();
            UsuarioModel usuario = entityManager.find(UsuarioModel.class, id);
            if (usuario != null) {
                entityManager.remove(usuario);
                entityManager.getTransaction().commit();
                return "Usuário removido com sucesso!";
            } else {
                return "Usuário não encontrado!";
            }
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return "Erro ao remover usuário: " + e.getMessage();
        }
    }
}