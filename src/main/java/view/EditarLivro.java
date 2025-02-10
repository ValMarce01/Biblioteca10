package view;

import controller.LivroController;
import model.LivroModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditarLivro extends JFrame {
    private JTextField textFieldTitulo;
    private JTextField textFieldTema;
    private JTextField textFieldAutor;
    private JTextField textFieldIsbn;
    private JTextField textFieldDataPublicacao;
    private JTextField textFieldQuantidadeDisponivel;
    private JButton buttonSalvar;
    private JPanel jPanelPrincipal;
    private LivroModel livro;

    public EditarLivro(LivroModel livro) {
        this.livro = livro;
        this.setTitle("Editar Livro");
        this.setContentPane(jPanelPrincipal);
        this.setSize(640, 480);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);

        // Preenche os campos com os dados atuais do livro
        textFieldTitulo.setText(livro.getTitulo());
        textFieldTema.setText(livro.getTema());
        textFieldAutor.setText(livro.getAutor());
        textFieldIsbn.setText(livro.getIsbn());
        textFieldDataPublicacao.setText(new SimpleDateFormat("dd/MM/yyyy").format(livro.getDataPublicacao()));
        textFieldQuantidadeDisponivel.setText(String.valueOf(livro.getQuantidadeDisponivel()));

        buttonSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Atualiza os dados do livro
                livro.setTitulo(textFieldTitulo.getText());
                livro.setTema(textFieldTema.getText());
                livro.setAutor(textFieldAutor.getText());
                livro.setIsbn(textFieldIsbn.getText());

                try {
                    Date dataPublicacao = new SimpleDateFormat("dd/MM/yyyy").parse(textFieldDataPublicacao.getText());
                    livro.setDataPublicacao(dataPublicacao);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Formato de data inválido! Use dd/MM/yyyy.");
                    return;
                }

                try {
                    livro.setQuantidadeDisponivel(Integer.parseInt(textFieldQuantidadeDisponivel.getText()));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Quantidade disponível deve ser um número válido.");
                    return;
                }

                // Atualiza o livro no banco de dados
                LivroController livroController = new LivroController();
                try {
                    JOptionPane.showMessageDialog(null, livroController.atualizar(livro));
                    dispose(); // Fecha a janela de edição após salvar
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao atualizar livro: " + ex.getMessage());
                }
            }
        });
    }
}