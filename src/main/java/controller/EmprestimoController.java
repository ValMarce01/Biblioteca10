package controller;

import model.EmprestimoModel;
import model.LivroModel;
import model.UsuarioModel;
import repository.EmprestimoRepository;

import java.util.Date;
import java.util.List;

public class EmprestimoController {
    private EmprestimoRepository emprestimoRepository = new EmprestimoRepository();

    public String realizarEmprestimo(UsuarioModel usuario, LivroModel livro, Date dataEmprestimo) {
        if (livro.getQuantidadeDisponivel() <= 0) {
            return "Não há exemplares disponíveis para este livro.";
        }

        List<EmprestimoModel> emprestimosAtivos = emprestimoRepository.buscarTodos().stream()
                .filter(e -> e.getUsuario().getId().equals(usuario.getId()) && e.getDataDevolucao() == null)
                .toList();

        if (emprestimosAtivos.size() >= 5) {
            return "O usuário já atingiu o limite máximo de 5 empréstimos ativos.";
        }

        EmprestimoModel emprestimo = new EmprestimoModel();
        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);
        emprestimo.setDataEmprestimo(dataEmprestimo);

        // Calcula a data de devolução prevista (14 dias após o empréstimo)
        long time = dataEmprestimo.getTime();
        Date dataDevolucaoPrevista = new Date(time + (14L * 24 * 60 * 60 * 1000));
        emprestimo.setDataDevolucaoPrevista(dataDevolucaoPrevista);

        // Atualiza a quantidade disponível do livro
        livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() - 1);

        return emprestimoRepository.salvar(emprestimo);
    }

    public String registrarDevolucao(Long idEmprestimo, Date dataDevolucao) {
        EmprestimoModel emprestimo = emprestimoRepository.buscarPorId(idEmprestimo);
        if (emprestimo == null) {
            return "Empréstimo não encontrado.";
        }

        // Define a data de devolução
        emprestimo.setDataDevolucao(dataDevolucao);

        // Calcula a multa, se houver atraso
        long diasAtraso = (dataDevolucao.getTime() - emprestimo.getDataDevolucaoPrevista().getTime()) / (1000 * 60 * 60 * 24);
        double multa = 0.0;
        if (diasAtraso > 0) {
            multa = diasAtraso * 2.0; // Exemplo: R$ 2,00 por dia de atraso
        }

        // Atualiza a quantidade disponível do livro
        LivroModel livro = emprestimo.getLivro();
        livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() + 1);

        // Atualiza o empréstimo no banco de dados
        String resultado = emprestimoRepository.atualizar(emprestimo);

        // Retorna a mensagem com o resultado e a multa, se houver
        if (diasAtraso > 0) {
            return resultado + "\nDevolução atrasada em " + diasAtraso + " dias. Multa: R$ " + multa;
        } else {
            return resultado + "\nDevolução realizada dentro do prazo.";
        }
    }

    public List<EmprestimoModel> buscarTodos() {
        return emprestimoRepository.buscarTodos();
    }

    public EmprestimoModel buscarPorId(Long id) {
        return emprestimoRepository.buscarPorId(id);
    }

    }
