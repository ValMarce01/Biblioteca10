package controller;

import model.LivroModel;
import repository.LivroRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;


public class LivroController {
    private LivroRepository livroRepository = new LivroRepository();

    public String salvar(LivroModel livro) throws SQLException {
        return livroRepository.salvar(livro);
    }

    public List<LivroModel> buscarTodos() throws SQLException {
        return livroRepository.buscarTodos();
    }

    public String remover(Long idLivroSelecionado) throws SQLException {
        LivroModel livro = livroRepository.buscarPorId(idLivroSelecionado);
        return livroRepository.remover(livro);
    }

    public String atualizar(LivroModel livro) throws SQLException {
        return livroRepository.atualizar(livro);
    }

    public LivroModel buscarPorId(Long id) throws SQLException {
        return livroRepository.buscarPorId(id);
    }

    public List<LivroModel> listarLivrosDisponiveis() throws SQLException {
        return livroRepository.buscarTodos().stream()
                .filter(livro -> livro.getQuantidadeDisponivel() > 0)
                .collect(Collectors.toList());
    }
}