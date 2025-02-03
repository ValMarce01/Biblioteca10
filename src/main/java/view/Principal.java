package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principal extends JFrame {
    private JMenuBar menuBar = new JMenuBar();
    private JPanel jpainelPrincipal;

    public Principal() {
        this.setTitle("Sistema - Biblioteca");
        this.setContentPane(jpainelPrincipal);
        this.setSize(640, 480);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        criacaoDoMenu();
        this.setVisible(true);
    }

    public void criacaoDoMenu() {
        this.setJMenuBar(menuBar);
        JMenu manterLivro = new JMenu("Menu Livros");
        JMenuItem cadastroLivro = new JMenuItem("Cadastrar");
        JMenuItem buscarLivro = new JMenuItem("Buscar");
        manterLivro.add(cadastroLivro);
        manterLivro.add(buscarLivro);

        JMenu manterUsuario = new JMenu("Menu Usuarios");
        JMenuItem cadastroUsuario = new JMenuItem("Cadastrar");
        JMenuItem buscarUsuario = new JMenuItem("Buscar");
        manterUsuario.add(cadastroUsuario);
        manterUsuario.add(buscarUsuario);

        menuBar.add(manterLivro);
        menuBar.add(manterUsuario);

        cadastroLivro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CadastroDeLivro();
            }
        });

        buscarLivro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BuscaDeLivro();
            }
        });

        cadastroUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CadastroDeUsuario();
            }
        });

        buscarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BuscaDeUsuario();
            }
        });
    }
}