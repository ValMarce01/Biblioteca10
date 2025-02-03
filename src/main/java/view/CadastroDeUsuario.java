package view;

import controller.UsuarioController;
import model.UsuarioModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroDeUsuario extends JFrame {
    private JPanel jpanelPrincipal;
    private JButton Enviar;
    private JTextField textFieldNome;
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

        Enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsuarioModel usuario = new UsuarioModel();
                usuario.setNome(textFieldNome.getText());
                usuario.setSexo(textFieldSexo.getText());
                usuario.setCelular(textFieldCelular.getText());
                usuario.setEmail(textFieldEmail.getText());
                JOptionPane.showMessageDialog(null, usuarioController.salvar(usuario));
            }
        });
    }
}