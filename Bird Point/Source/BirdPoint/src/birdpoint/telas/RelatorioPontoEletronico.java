/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.telas;

import birdpoint.funcionario.Funcionario;
import birdpoint.funcionario.FuncionarioDAO;
import birdpoint.funcionario.FuncionarioTableModel;
import birdpoint.util.ConnectionFactory;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Adriano Lima
 */
public class RelatorioPontoEletronico extends javax.swing.JDialog {

    Funcionario funcionario = new Funcionario();
    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    public RelatorioPontoEletronico(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setModal(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        tfMensagem1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btVoltar = new javax.swing.JButton();
        btVoltar1 = new javax.swing.JButton();
        tfDataFim = new javax.swing.JFormattedTextField();
        tfDataInicio = new javax.swing.JFormattedTextField();
        tfNome = new javax.swing.JTextField();
        btCurso18 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btCurso114 = new javax.swing.JButton();
        jlCadCidade = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(350, 328));
        setMinimumSize(new java.awt.Dimension(350, 328));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(350, 328));
        getContentPane().setLayout(null);

        tfMensagem1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        tfMensagem1.setText("Relatório de Ponto Eletrônico");
        getContentPane().add(tfMensagem1);
        tfMensagem1.setBounds(30, 30, 300, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Professor.:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(20, 170, 100, 30);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Data Início.:");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(50, 80, 120, 30);

        btVoltar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/imprimir - 40.png"))); // NOI18N
        btVoltar.setText("Gerar Relatório");
        btVoltar.setContentAreaFilled(false);
        btVoltar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btVoltar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btVoltar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVoltarActionPerformed(evt);
            }
        });
        getContentPane().add(btVoltar);
        btVoltar.setBounds(110, 250, 150, 70);

        btVoltar1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btVoltar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/voltar.png"))); // NOI18N
        btVoltar1.setText("Voltar");
        btVoltar1.setContentAreaFilled(false);
        btVoltar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btVoltar1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btVoltar1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btVoltar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVoltar1ActionPerformed(evt);
            }
        });
        getContentPane().add(btVoltar1);
        btVoltar1.setBounds(10, 250, 80, 70);

        tfDataFim.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 0), 1, true));
        try {
            tfDataFim.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfDataFim.setToolTipText("");
        tfDataFim.setMinimumSize(new java.awt.Dimension(2, 19));
        tfDataFim.setName(""); // NOI18N
        tfDataFim.setPreferredSize(new java.awt.Dimension(2, 19));
        getContentPane().add(tfDataFim);
        tfDataFim.setBounds(170, 120, 100, 30);

        tfDataInicio.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 0), 1, true));
        try {
            tfDataInicio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfDataInicio.setToolTipText("");
        tfDataInicio.setMinimumSize(new java.awt.Dimension(2, 19));
        tfDataInicio.setName(""); // NOI18N
        tfDataInicio.setPreferredSize(new java.awt.Dimension(2, 19));
        getContentPane().add(tfDataInicio);
        tfDataInicio.setBounds(170, 80, 100, 30);

        tfNome.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tfNome.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 121, 0), 1, true));
        tfNome.setEnabled(false);
        getContentPane().add(tfNome);
        tfNome.setBounds(20, 200, 270, 30);

        btCurso18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/pesquisar20.png"))); // NOI18N
        btCurso18.setContentAreaFilled(false);
        btCurso18.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso18.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso18.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso18ActionPerformed(evt);
            }
        });
        getContentPane().add(btCurso18);
        btCurso18.setBounds(290, 200, 30, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Data Fim.:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(70, 120, 100, 30);

        btCurso114.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btCurso114.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/limpar20.png"))); // NOI18N
        btCurso114.setContentAreaFilled(false);
        btCurso114.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btCurso114.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCurso114.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCurso114.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCurso114ActionPerformed(evt);
            }
        });
        getContentPane().add(btCurso114);
        btCurso114.setBounds(320, 200, 20, 29);

        jlCadCidade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/tela relatório.png"))); // NOI18N
        jlCadCidade.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jlCadCidade.setMaximumSize(new java.awt.Dimension(350, 328));
        jlCadCidade.setMinimumSize(new java.awt.Dimension(350, 328));
        jlCadCidade.setPreferredSize(new java.awt.Dimension(350, 328));
        getContentPane().add(jlCadCidade);
        jlCadCidade.setBounds(0, 0, 350, 328);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltarActionPerformed
        JasperReport pathjrxml;
        HashMap parametros = new HashMap();
        String sql = "", texto = "";
        String novo = "", novoTexto = "";

        if (!tfDataInicio.getText().equals("  /  /    ") && !tfDataFim.getText().equals("  /  /    ")) {
            String dataInicial = "", dataFinal = "";
            try {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                java.sql.Date data = new java.sql.Date(format.parse(tfDataInicio.getText()).getTime());
                dataInicial = String.valueOf(data);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            try {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                java.sql.Date data = new java.sql.Date(format.parse(tfDataFim.getText()).getTime());
                dataFinal = String.valueOf(data);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            sql += " dataPontoCompleta between '" + dataInicial + " 00:00:00'" + " and '" + dataFinal + " 23:59:59" + "'";

            novo += "De: " + tfDataInicio.getText() + " Até: " + tfDataFim.getText();
            
            if(funcionario.getIdFuncionario()!=0){
                sql += " and funcionario_idFuncionario="+funcionario.getIdFuncionario();
            }
            sql +=" ORDER BY nomeFuncionario ASC";

            parametros.put("texto", sql);
            parametros.put("novoTexto", novo);

            Connection connection = new ConnectionFactory().getConnection();

            try {
                pathjrxml = JasperCompileManager.compileReport("relatorios/RelatorioPendenciaPontoEletronico.jrxml");
                JasperPrint printReport = JasperFillManager.fillReport(pathjrxml, parametros, connection);
                JDialog viewer = new JDialog(new javax.swing.JFrame(), "Visualização do Relatório", true);
                viewer.setSize(1000, 600);
                viewer.setLocationRelativeTo(null);
                viewer.setModal(true);
                JasperViewer jv = new JasperViewer(printReport, false);
                viewer.getContentPane().add(jv.getContentPane());
                viewer.setVisible(true);
            } catch (JRException ex) {
                Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "É obrigatório informar as duas datas!");
        }
    }//GEN-LAST:event_btVoltarActionPerformed

    private void btVoltar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltar1ActionPerformed
        dispose();
    }//GEN-LAST:event_btVoltar1ActionPerformed

    private void btCurso18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso18ActionPerformed
        List<Funcionario> lista;
        lista = (funcionarioDAO.listar());
        FuncionarioTableModel itm = new FuncionarioTableModel(lista);
        Object objetoRetorno = PesquisaGenerica.exibeTela(itm, "Funcionario");
        if (objetoRetorno != null) {
            funcionario = funcionarioDAO.consultarObjetoId("idFuncionario", objetoRetorno);
            tfNome.setText(funcionario.getNomeFuncionario());
        }
    }//GEN-LAST:event_btCurso18ActionPerformed

    private void btCurso114ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCurso114ActionPerformed
       tfNome.setText("");
       funcionario = new Funcionario();
    }//GEN-LAST:event_btCurso114ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RelatorioPontoEletronico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RelatorioPontoEletronico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RelatorioPontoEletronico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RelatorioPontoEletronico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                RelatorioPontoEletronico dialog = new RelatorioPontoEletronico(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCurso114;
    private javax.swing.JButton btCurso18;
    private javax.swing.JButton btVoltar;
    private javax.swing.JButton btVoltar1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel jlCadCidade;
    private javax.swing.JFormattedTextField tfDataFim;
    private javax.swing.JFormattedTextField tfDataInicio;
    private javax.swing.JLabel tfMensagem1;
    private javax.swing.JTextField tfNome;
    // End of variables declaration//GEN-END:variables
}
