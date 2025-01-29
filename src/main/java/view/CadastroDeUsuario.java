package view;

import controller.UsuarioController;
import model.UsuarioModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CadastroDeUsuario extends JFrame {
    private JPanel jpanelPrincipal;
    private JButton enviarButton;
    private JTextField textFieldNome;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton editarButton;
    private JTextField textFieldSexo;
    private JTextField textFieldCelular;
    private JTextField textFieldEmail;

    public CadastroDeUsuario() {
        this.setTitle("Sistema - Biblioteca");
        this.setContentPane(jpanelPrincipal);
        this.setSize(640, 480);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);

        UsuarioController usuarioController = new UsuarioController();

        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsuarioModel usuario = new UsuarioModel();
                usuario.setNome(textFieldNome.getText());
                usuario.setSexo(textFieldSexo.getText());
                usuario.setCelular(textFieldCelular.getText());
                usuario.setEmail(textFieldEmail.getText());
                try {
                    JOptionPane.showMessageDialog(null, usuarioController.salvar(usuario));
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao salvar usuário: " + ex.getMessage());
                }
            }
        });
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
