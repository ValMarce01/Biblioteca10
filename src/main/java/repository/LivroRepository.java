package repository;

import model.LivroModel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroRepository {
    private static LivroRepository instance;
    protected EntityManager entityManager;

    public LivroRepository() {
        entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("crudHibernatePU");
        if (entityManager == null) {
            entityManager = factory.createEntityManager();
        }
        return entityManager;
    }

    public static LivroRepository getInstance() {
        if (instance == null) {
            instance = new LivroRepository();
        }
        return instance;
    }

    public String salvar(LivroModel livro) throws SQLException {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(livro);
            entityManager.getTransaction().commit();
            return "Salvo com Sucesso.";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public List<LivroModel> buscarTodos() {
        try {
            List<LivroModel> livros = entityManager.createQuery("from LivroModel").getResultList();
            return livros;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public String remover(LivroModel livro) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(livro);
            entityManager.getTransaction().commit();
            return "Removido com sucesso!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public LivroModel buscarPorId(Long id) {
        LivroModel livro = new LivroModel();
        try {
            livro = entityManager.find(LivroModel.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return livro;
    }

    public String atualizar(LivroModel livro) throws SQLException {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(livro);
            entityManager.getTransaction().commit();
            return "Livro atualizado com sucesso!";
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return "Erro ao atualizar livro: " + e.getMessage();
        }
    }
}
