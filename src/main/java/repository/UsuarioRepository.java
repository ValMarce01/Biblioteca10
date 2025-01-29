package repository;

import model.UsuarioModel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.SQLException;

public class UsuarioRepository {
    private EntityManager entityManager;

    public UsuarioRepository() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("bibliotecaPU");
        this.entityManager = factory.createEntityManager();
    }

    public String salvar(UsuarioModel usuario) throws SQLException {
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
}