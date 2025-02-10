package view;

import controller.LivroController;
import model.LivroModel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.List;

public class ListarLivrosDisponiveis extends JFrame {
    private JTable tableLivrosDisponiveis;
    private JPanel jPanelPrincipal;

    public ListarLivrosDisponiveis() {
        this.setTitle("Listar Livros Disponíveis");
        this.setContentPane(jPanelPrincipal);
        this.setSize(640, 480);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);

        // Carrega os livros disponíveis
        LivroController livroController = new LivroController();
        try {
            List<LivroModel> livrosDisponiveis = livroController.listarLivrosDisponiveis();
            LivroTableModel livroTableModel = new LivroTableModel(livrosDisponiveis);
            tableLivrosDisponiveis.setModel(livroTableModel);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar livros disponíveis: " + ex.getMessage());
        }
    }

    // Modelo de tabela para exibir os livros
    private static class LivroTableModel extends AbstractTableModel {
        private final String[] COLUMNS = new String[]{"ID", "Título", "Autor", "Tema", "Quantidade Disponível"};
        private List<LivroModel> livros;

        public LivroTableModel(List<LivroModel> livros) {
            this.livros = livros;
        }

        @Override
        public int getRowCount() {
            return livros.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMNS.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            LivroModel livro = livros.get(rowIndex);
            return switch (columnIndex) {
                case 0 -> livro.getId();
                case 1 -> livro.getTitulo();
                case 2 -> livro.getAutor();
                case 3 -> livro.getTema();
                case 4 -> livro.getQuantidadeDisponivel();
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