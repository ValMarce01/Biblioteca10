package view;

import controller.AlunoController;
import model.AlunoModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CadastroDeAluno extends JFrame {
    private JPanel jpanelPrincipal;
    private JButton enviarButton;
    private JTextField textFieldIdade;
    private JTextField textFieldNome;
    private JTextField textFieldTurma;

    public CadastroDeAluno() {
        this.setTitle("Sistema - Escola nova CB");
        this.setContentPane(jpanelPrincipal);
        this.setSize(640, 480);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);

        AlunoController alunoController = new AlunoController();

        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AlunoModel aluno = new AlunoModel();
                aluno.setNome(textFieldNome.getText());
                aluno.setIdade(Integer.parseInt(textFieldIdade.getText()));
                aluno.setTurma(textFieldTurma.getText());
                try {
                    JOptionPane.showMessageDialog(null, alunoController.salvar(aluno));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}


