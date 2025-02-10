package view;

import controller.UsuarioController;
import model.UsuarioModel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BuscaDeUsuario extends JFrame {
    private JTextField pesquisa;
    private JButton busca;
    private JTable tableBuscaUsuario;
    private JPanel jPanelPrincipal;
    private JButton removerButton;
    private JScrollPane tabela;
    private JButton Editar;
    private UsuarioController usuarioController = new UsuarioController();

    public BuscaDeUsuario() {
        this.setTitle("Sistema - Biblioteca");
        UsuarioModeloDeTabela usuarioModeloDeTabela = new UsuarioModeloDeTabela();
        tableBuscaUsuario.setModel(usuarioModeloDeTabela);
        tableBuscaUsuario.setAutoCreateRowSorter(true);
        this.setContentPane(jPanelPrincipal);
        this.setSize(640, 480);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);

        removerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = tableBuscaUsuario.getSelectedRow();
                if (linhaSelecionada != -1) {
                    Long idDoUsuarioSelecionado = Long.parseLong(tableBuscaUsuario.getValueAt(linhaSelecionada, 0).toString());
                    JOptionPane.showMessageDialog(null, usuarioController.remover(idDoUsuarioSelecionado));
                    UsuarioModeloDeTabela usuarioModeloDeTabela = new UsuarioModeloDeTabela();
                    tableBuscaUsuario.setModel(usuarioModeloDeTabela);
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione o registro que deseja remover");
                }
            }
        });

        busca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String termoDeBusca = pesquisa.getText();
                UsuarioModeloDeTabela usuarioModeloDeTabela = new UsuarioModeloDeTabela(termoDeBusca);
                tableBuscaUsuario.setModel(usuarioModeloDeTabela);
            }
        });
        Editar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = tableBuscaUsuario.getSelectedRow();
                if (linhaSelecionada != -1) {
                    Long idDoUsuarioSelecionado = Long.parseLong(tableBuscaUsuario.getValueAt(linhaSelecionada, 0).toString());
                    UsuarioModel usuario = usuarioController.buscarPorId(idDoUsuarioSelecionado);
                    new EditarUsuario(usuario);
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione o registro que deseja editar");
                }
            }
        });
    }

    private static class UsuarioModeloDeTabela extends AbstractTableModel {
        private UsuarioController usuarioController = new UsuarioController();
        private final String[] COLUMNS = new String[]{"Id", "Nome", "Sexo", "Celular", "Email"};
        private List<UsuarioModel> listaDeUsuarios;

        public UsuarioModeloDeTabela() {
            listaDeUsuarios = usuarioController.buscarTodos();
        }

        public UsuarioModeloDeTabela(String termoDeBusca) {
            listaDeUsuarios = usuarioController.buscarTodos(); // Implementar busca por termo
        }

        @Override
        public int getRowCount() {
            return listaDeUsuarios.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMNS.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            UsuarioModel usuario = listaDeUsuarios.get(rowIndex);
            return switch (columnIndex) {
                case 0 -> usuario.getId();
                case 1 -> usuario.getNome();
                case 2 -> usuario.getSexo();
                case 3 -> usuario.getCelular();
                case 4 -> usuario.getEmail();
                default -> "-";
            };
        }

        @Override
        public String getColumnName(int columnIndex) {
            return COLUMNS[columnIndex];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (getValueAt(0, columnIndex) != null) {
                return getValueAt(0, columnIndex).getClass();
            } else {
                return Object.class;
            }
        }
    }
}