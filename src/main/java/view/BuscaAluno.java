package view;

import controller.AlunoController;
import model.AlunoModel;
import repository.AlunoRepository;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class BuscaAluno extends JFrame {

    private JTextField textField1;
    private JButton tableBuscarAluno;
    private JTable tableBuscaAluno;
    private JPanel JPanielPrincipal;
    private JButton removerButton;
    private AlunoController alunoController = new AlunoController();

    public BuscaAluno() {
        this.setTitle("Sistema - Escola nova CB");
        AlunoModeloDeTabela alunoModeloDeTabela = new AlunoModeloDeTabela();
        tableBuscaAluno.setModel(alunoModeloDeTabela);
        tableBuscaAluno.setAutoCreateRowSorter(true);
        this.setContentPane(JPanielPrincipal);
        this.setSize(640, 480);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
        removerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = tableBuscaAluno.getSelectedRow();
                if(linhaSelecionada != -1) {
                    Long idDaPessoaSelecionada = Long.parseLong(tableBuscaAluno.getValueAt(linhaSelecionada,0).toString());
                    try {
                        JOptionPane.showMessageDialog(null, alunoController.remover(idDaPessoaSelecionada));
                        AlunoModeloDeTabela alunoModeloDeTabela = new AlunoModeloDeTabela();
                        tableBuscaAluno.setModel(alunoModeloDeTabela);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Selecione o registro que deseja remover");
                }
            }
        });
    }

    private static class AlunoModeloDeTabela extends AbstractTableModel {
        private AlunoRepository alunoRepository = new AlunoRepository();
        private final String[] COLUMNS = new String[]{"Id", "Nome", "Idade", "Turma"};
        private List<AlunoModel> listaDeAlunos = alunoRepository.buscarTodos();

        @Override
        public int getRowCount() {
            return listaDeAlunos.size();
        }


        @Override
        public int getColumnCount() {
            return COLUMNS.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch (columnIndex) {
                case 0 -> listaDeAlunos.get(rowIndex).getIdPessoa();
                case 1 -> listaDeAlunos.get(rowIndex).getNome();
                case 2 -> listaDeAlunos.get(rowIndex).getIdade();
                case 3 -> listaDeAlunos.get(rowIndex).getTurma();
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

