package view;

import controller.LivroController;
import model.LivroModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CadastroDeLivro extends JFrame {
    private JTextField textField1; // Título
    private JTextField textField2; // Tema
    private JTextField textField6; // Autor
    private JTextField textField3; // ISBN
    private JTextField textField4; // Data de Publicação
    private JTextField textField5; // Quantidade Disponível
    private JButton Enviar;
    private JPanel jPanelPrincipal;

    public CadastroDeLivro() {
        this.setTitle("Sistema - Biblioteca");
        this.setContentPane(jPanelPrincipal);
        this.setSize(640, 480);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);

        LivroController livroController = new LivroController();

        Enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LivroModel livro = new LivroModel();
                livro.setTitulo(textField1.getText());
                livro.setTema(textField2.getText());
                livro.setAutor(textField6.getText()); // Correção aqui
                livro.setIsbn(textField3.getText());

                try {
                    Date dataPublicacao = new SimpleDateFormat("dd/MM/yyyy").parse(textField4.getText());
                    livro.setDataPublicacao(dataPublicacao);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Formato de data inválido! Use dd/MM/yyyy.");
                    return;
                }

                try {
                    livro.setQuantidadeDisponivel(Integer.parseInt(textField5.getText()));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Quantidade disponível deve ser um número válido.");
                    return;
                }

                try {
                    JOptionPane.showMessageDialog(null, livroController.salvar(livro));
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao salvar livro: " + ex.getMessage());
                }
            }
        });
    }
}