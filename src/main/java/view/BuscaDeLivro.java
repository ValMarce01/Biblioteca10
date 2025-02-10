package view;

import controller.LivroController;
import model.LivroModel;
import repository.LivroRepository;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class BuscaDeLivro extends JFrame {
    private JTextField pesquisa;
    private JButton busca;
    private JTable tableBuscaLivro;
    private JPanel jPanelPrincipal;
    private JButton removerButton;
    private JScrollPane tabela;
    private JButton Editar;
    private LivroController livroController = new LivroController();

    public BuscaDeLivro() {
        this.setTitle("Sistema - Biblioteca");
        LivroModeloDeTabela livroModeloDeTabela = new LivroModeloDeTabela();
        tableBuscaLivro.setModel(livroModeloDeTabela);
        tableBuscaLivro.setAutoCreateRowSorter(true);
        this.setContentPane(jPanelPrincipal);
        this.setSize(640, 480);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);

        removerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = tableBuscaLivro.getSelectedRow();
                if (linhaSelecionada != -1) {
                    Long idDoLivroSelecionado = Long.parseLong(tableBuscaLivro.getValueAt(linhaSelecionada, 0).toString());
                    try {
                        JOptionPane.showMessageDialog(null, livroController.remover(idDoLivroSelecionado));
                        LivroModeloDeTabela livroModeloDeTabela = new LivroModeloDeTabela();
                        tableBuscaLivro.setModel(livroModeloDeTabela);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao remover livro: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione o registro que deseja remover");
                }
            }
        });

        busca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String termoDeBusca = pesquisa.getText();
                LivroModeloDeTabela livroModeloDeTabela = new LivroModeloDeTabela(termoDeBusca);
                tableBuscaLivro.setModel(livroModeloDeTabela);
            }
        });
        Editar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = tableBuscaLivro.getSelectedRow();
                if (linhaSelecionada != -1) {
                    Long idDoLivroSelecionado = Long.parseLong(tableBuscaLivro.getValueAt(linhaSelecionada, 0).toString());
                    try {
                        LivroModel livro = livroController.buscarPorId(idDoLivroSelecionado);
                        new EditarLivro(livro);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao buscar livro: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione o registro que deseja editar");
                }
            }
        });
    }

    private static class LivroModeloDeTabela extends AbstractTableModel {
        private LivroRepository livroRepository = new LivroRepository();
        private final String[] COLUMNS = new String[]{"Id", "Título", "Autor", "Exemplares Disponíveis"};
        private List<LivroModel> listaDeLivros;

        public LivroModeloDeTabela() {
            listaDeLivros = livroRepository.buscarTodos();
        }

        public LivroModeloDeTabela(String termoDeBusca) {
            listaDeLivros = livroRepository.buscarTodos(); // Implementar busca por termo
        }

        @Override
        public int getRowCount() {
            return listaDeLivros.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMNS.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            LivroModel livro = listaDeLivros.get(rowIndex);
            return switch (columnIndex) {
                case 0 -> livro.getId();
                case 1 -> livro.getTitulo();
                case 2 -> livro.getAutor();
                case 3 -> livro.getQuantidadeDisponivel();
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
