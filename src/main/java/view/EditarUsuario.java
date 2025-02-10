package view;

import controller.UsuarioController;
import model.UsuarioModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditarUsuario extends JFrame {
    private JTextField textFieldNome;
    private JTextField textFieldSexo;
    private JTextField textFieldCelular;
    private JTextField textFieldEmail;
    private JButton buttonSalvar;
    private JPanel jPanelPrincipal;
    private UsuarioModel usuario;

    public EditarUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
        this.setTitle("Editar Usuário");
        this.setContentPane(jPanelPrincipal);
        this.setSize(640, 480);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);

        // Preenche os campos com os dados atuais do usuário
        textFieldNome.setText(usuario.getNome());
        textFieldSexo.setText(usuario.getSexo());
        textFieldCelular.setText(usuario.getCelular());
        textFieldEmail.setText(usuario.getEmail());

        buttonSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Atualiza os dados do usuário
                usuario.setNome(textFieldNome.getText());
                usuario.setSexo(textFieldSexo.getText());
                usuario.setCelular(textFieldCelular.getText());
                usuario.setEmail(textFieldEmail.getText());

                // Atualiza o usuário no banco de dados
                UsuarioController usuarioController = new UsuarioController();
                JOptionPane.showMessageDialog(null, usuarioController.atualizar(usuario));
                dispose(); // Fecha a janela de edição após salvar
            }
        });
    }
}