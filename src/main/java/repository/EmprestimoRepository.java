package repository;

import model.EmprestimoModel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmprestimoRepository {
    private EntityManager entityManager;

    public EmprestimoRepository() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("crudHibernatePU");
        this.entityManager = factory.createEntityManager();
    }

    public String salvar(EmprestimoModel emprestimo) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(emprestimo);
            entityManager.getTransaction().commit();
            return "Empréstimo salvo com sucesso!";
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return "Erro ao salvar empréstimo: " + e.getMessage();
        }
    }

    public List<EmprestimoModel> buscarTodos() {
        return entityManager.createQuery("from EmprestimoModel", EmprestimoModel.class).getResultList();
    }

    public EmprestimoModel buscarPorId(Long id) {
        return entityManager.find(EmprestimoModel.class, id);
    }

    public String atualizar(EmprestimoModel emprestimo) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(emprestimo);
            entityManager.getTransaction().commit();
            return "Empréstimo atualizado com sucesso!";
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return "Erro ao atualizar empréstimo: " + e.getMessage();
        }
    }

    public String remover(Long id) {
        try {
            entityManager.getTransaction().begin();
            EmprestimoModel emprestimo = entityManager.find(EmprestimoModel.class, id);
            if (emprestimo != null) {
                entityManager.remove(emprestimo);
                entityManager.getTransaction().commit();
                return "Empréstimo removido com sucesso!";
            } else {
                return "Empréstimo não encontrado!";
            }
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return "Erro ao remover empréstimo: " + e.getMessage();
        }
    }
}