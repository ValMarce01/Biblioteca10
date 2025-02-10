package view;

import controller.EmprestimoController;
import model.EmprestimoModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RegistrarDevolucao extends JFrame {
    private JComboBox<String> comboBoxEmprestimos;
    private JTextField textFieldDataDevolucao;
    private JButton buttonRegistrarDevolucao;
    private JPanel jPanelPrincipal;

    private List<EmprestimoModel> listaDeEmprestimos;

    public RegistrarDevolucao() {
        this.setTitle("Registrar Devolução");
        this.setContentPane(jPanelPrincipal);
        this.setSize(640, 480);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);

        // Carrega os empréstimos ativos
        EmprestimoController emprestimoController = new EmprestimoController();
        listaDeEmprestimos = emprestimoController.buscarTodos();
        for (EmprestimoModel emprestimo : listaDeEmprestimos) {
            if (emprestimo.getDataDevolucao() == null) {
                comboBoxEmprestimos.addItem(emprestimo.getId() + "- " + emprestimo.getUsuario().getNome() + " - " + emprestimo.getLivro().getTitulo());
            }
        }

        buttonRegistrarDevolucao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Recupera o item selecionado no ComboBox de empréstimos
                    String selectedItem = (String) comboBoxEmprestimos.getSelectedItem();
                    if (selectedItem != null) {
                        // Divide a string usando "- " como delimitador
                        String[] parts = selectedItem.split("- ");
                        if (parts.length > 0) {
                            Long emprestimoId = Long.parseLong(parts[0].trim()); // Remove espaços em branco

                            // Busca o empréstimo correspondente na lista
                            EmprestimoModel emprestimo = null;
                            for (EmprestimoModel emp : listaDeEmprestimos) {
                                if (emp.getId().equals(emprestimoId)) {
                                    emprestimo = emp;
                                    break;
                                }
                            }

                            if (emprestimo != null) {
                                // Valida a data de devolução
                                Date dataDevolucao;
                                try {
                                    dataDevolucao = new SimpleDateFormat("dd/MM/yyyy").parse(textFieldDataDevolucao.getText());
                                } catch (ParseException ex) {
                                    JOptionPane.showMessageDialog(null, "Formato de data inválido! Use dd/MM/yyyy.");
                                    return;
                                }

                                // Registra a devolução e exibe a mensagem de atraso/multa
                                String resultado = emprestimoController.registrarDevolucao(emprestimo.getId(), dataDevolucao);
                                JOptionPane.showMessageDialog(null, resultado);
                            } else {
                                JOptionPane.showMessageDialog(null, "Empréstimo não encontrado!");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Formato inválido no ComboBox.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Selecione um empréstimo para registrar a devolução.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao processar a devolução: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });
    }
}