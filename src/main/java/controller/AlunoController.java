package controller;

import model.AlunoModel;
import repository.AlunoRepository;

import java.sql.SQLException;
import java.util.List;

public class AlunoController {
    private AlunoRepository alunoRepository = new AlunoRepository();
    public String salvar (AlunoModel aluno) throws SQLException {
        return alunoRepository.salvar(aluno);
    }
    public List<AlunoModel> buscarTodos () throws SQLException {
        return alunoRepository.buscarTodos();
    }

    public String remover (Long idAlunoSelecionado) throws SQLException {
        AlunoModel aluno = alunoRepository.buscarPorId(idAlunoSelecionado);
        return alunoRepository.remover(aluno);
    }
}
