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

        // Menu Livros
        JMenu manterLivro = new JMenu("Menu Livros");
        JMenuItem cadastroLivro = new JMenuItem("Cadastrar");
        JMenuItem buscarLivro = new JMenuItem("Buscar");
        JMenuItem listarLivrosDisponiveis = new JMenuItem("Listar Livros Disponíveis");
        manterLivro.add(cadastroLivro);
        manterLivro.add(buscarLivro);
        manterLivro.add(listarLivrosDisponiveis);

        // Menu Usuários
        JMenu manterUsuario = new JMenu("Menu Usuários");
        JMenuItem cadastroUsuario = new JMenuItem("Cadastrar");
        JMenuItem buscarUsuario = new JMenuItem("Buscar");
        manterUsuario.add(cadastroUsuario);
        manterUsuario.add(buscarUsuario);

        // Menu Empréstimos
        JMenu manterEmprestimo = new JMenu("Menu Empréstimos");
        JMenuItem realizarEmprestimo = new JMenuItem("Realizar Empréstimo");
        JMenuItem registrarDevolucao = new JMenuItem("Registrar Devolução");
        manterEmprestimo.add(realizarEmprestimo);
        manterEmprestimo.add(registrarDevolucao);

        // Adiciona menus à barra de menus
        menuBar.add(manterLivro);
        menuBar.add(manterUsuario);
        menuBar.add(manterEmprestimo);

        // Ações dos menus
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

        listarLivrosDisponiveis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListarLivrosDisponiveis();
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

        realizarEmprestimo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RealizarEmprestimo();
            }
        });

        registrarDevolucao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegistrarDevolucao();
            }
        });
    }
}