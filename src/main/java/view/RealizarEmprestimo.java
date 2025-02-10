package view;

import controller.EmprestimoController;
import controller.LivroController;
import controller.UsuarioController;
import model.LivroModel;
import model.UsuarioModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RealizarEmprestimo extends JFrame {
    private JComboBox<String> comboBoxUsuarios;
    private JComboBox<String> comboBoxLivros;
    private JTextField textFieldDataEmprestimo;
    private JButton buttonRealizarEmprestimo;
    private JPanel jPanelPrincipal;
    private javax.swing.JLabel JLabel;

    // Listas para armazenar os objetos
    private List<UsuarioModel> listaDeUsuarios = new ArrayList<>();
    private List<LivroModel> listaLivrosDisponiveis = new ArrayList<>();

    public RealizarEmprestimo() {
        this.setTitle("Realizar Empréstimo");
        this.setContentPane(jPanelPrincipal);
        this.setSize(640, 480);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);

        // Carrega os usuários e livros disponíveis
        UsuarioController usuarioController = new UsuarioController();
        LivroController livroController = new LivroController();

        // Preenche o ComboBox de usuários
        listaDeUsuarios = usuarioController.buscarTodos();
        for (UsuarioModel usuario : listaDeUsuarios) {
            comboBoxUsuarios.addItem(usuario.getId() + " - " + usuario.getNome());
        }

        // Preenche o ComboBox de livros disponíveis
        try {
            List<LivroModel> todosLivros = livroController.buscarTodos();
            for (LivroModel livro : todosLivros) {
                if (livro.getQuantidadeDisponivel() > 0) {
                    listaLivrosDisponiveis.add(livro); // Adiciona à lista de livros disponíveis
                    comboBoxLivros.addItem(livro.getId() + " - " + livro.getTitulo());
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar livros: " + ex.getMessage());
        }

        // Ação do botão "Realizar Empréstimo"
        buttonRealizarEmprestimo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Recupera o índice selecionado no ComboBox de usuários
                int indiceUsuarioSelecionado = comboBoxUsuarios.getSelectedIndex();
                UsuarioModel usuario = listaDeUsuarios.get(indiceUsuarioSelecionado);

                // Recupera o índice selecionado no ComboBox de livros
                int indiceLivroSelecionado = comboBoxLivros.getSelectedIndex();
                LivroModel livro = listaLivrosDisponiveis.get(indiceLivroSelecionado);

                // Valida a data de empréstimo
                Date dataEmprestimo;
                try {
                    dataEmprestimo = new SimpleDateFormat("dd/MM/yyyy").parse(textFieldDataEmprestimo.getText());
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Formato de data inválido! Use dd/MM/yyyy.");
                    return;
                }

                // Realiza o empréstimo
                EmprestimoController emprestimoController = new EmprestimoController();
                JOptionPane.showMessageDialog(null, emprestimoController.realizarEmprestimo(usuario, livro, dataEmprestimo));
            }
        });
    }
}