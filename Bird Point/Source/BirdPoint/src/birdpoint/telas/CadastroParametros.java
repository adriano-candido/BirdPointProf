/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.telas;

import birdpoint.parametrizacao.Parametrizacao;
import birdpoint.parametrizacao.ParametrizacaoDAO;
import birdpoint.util.Util;
import javax.swing.JOptionPane;

public class CadastroParametros extends javax.swing.JDialog {

    Parametrizacao parametros = new Parametrizacao();
    ParametrizacaoDAO parametrosDAO = new ParametrizacaoDAO();

    public CadastroParametros(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.getRootPane().setDefaultButton(btSalvar);
        if (parametrosDAO.listar() == null || parametrosDAO.listar().isEmpty()) {
            cadastroInicial();
        }
        parametros = parametrosDAO.listar().get(0);
        tfTolerancia.setText(String.valueOf(parametros.getTempoToleranciaEntrada()));
        tfToleranciaSaida.setText(String.valueOf(parametros.getTempoToleranciaSaida()));
        jcVerificarTolerância.setSelected(parametros.isIgnorarTolerancia());
        jcGerarPonto.setSelected(parametros.isGerarHorarioAutomatico());
    }

    //Este método é disparado uma única vez, somente quando ainda não há parâmetros cadastrados no banco.
    public void cadastroInicial() {
        Parametrizacao param = new Parametrizacao();
        param.setGerarHorarioAutomatico(false);
        param.setTempoToleranciaEntrada(10);
        param.setTempoToleranciaSaida(10);
        param.setIgnorarTolerancia(false);
        parametrosDAO.adicionar(param);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        selecionarFoto = new javax.swing.JFileChooser();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btVoltar = new javax.swing.JButton();
        btSalvar = new javax.swing.JButton();
        tfTolerancia = new javax.swing.JTextField();
        jLObrigatorioNome = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jcGerarPonto = new javax.swing.JCheckBox();
        tfToleranciaSaida = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLObrigatorioNome1 = new javax.swing.JLabel();
        jcVerificarTolerância = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        jlCadProfessores = new javax.swing.JLabel();

        selecionarFoto.setMaximumSize(new java.awt.Dimension(580, 245));
        selecionarFoto.setMinimumSize(new java.awt.Dimension(550, 245));
        selecionarFoto.setPreferredSize(new java.awt.Dimension(520, 320));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 421));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel8.setText("Parametrização");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(250, 20, 190, 40);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Tolerância em Minutos (Entrada).:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(60, 100, 240, 20);

        btVoltar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/voltar.png"))); // NOI18N
        btVoltar.setText("Voltar");
        btVoltar.setContentAreaFilled(false);
        btVoltar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btVoltar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btVoltar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVoltarActionPerformed(evt);
            }
        });
        getContentPane().add(btVoltar);
        btVoltar.setBounds(20, 340, 90, 70);

        btSalvar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/Salvar.png"))); // NOI18N
        btSalvar.setText("Salvar");
        btSalvar.setContentAreaFilled(false);
        btSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btSalvar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btSalvar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });
        getContentPane().add(btSalvar);
        btSalvar.setBounds(270, 340, 80, 70);

        tfTolerancia.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 0), 1, true));
        tfTolerancia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfToleranciaActionPerformed(evt);
            }
        });
        getContentPane().add(tfTolerancia);
        tfTolerancia.setBounds(300, 100, 140, 23);

        jLObrigatorioNome.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLObrigatorioNome.setForeground(new java.awt.Color(204, 0, 0));
        jLObrigatorioNome.setText("*");
        getContentPane().add(jLObrigatorioNome);
        jLObrigatorioNome.setBounds(440, 90, 20, 30);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Gerar Ponto Automático.:");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(60, 190, 180, 20);

        jcGerarPonto.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jcGerarPonto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jcGerarPonto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcGerarPontoActionPerformed(evt);
            }
        });
        getContentPane().add(jcGerarPonto);
        jcGerarPonto.setBounds(240, 190, 20, 20);

        tfToleranciaSaida.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 0), 1, true));
        tfToleranciaSaida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfToleranciaSaidaActionPerformed(evt);
            }
        });
        getContentPane().add(tfToleranciaSaida);
        tfToleranciaSaida.setBounds(300, 130, 140, 23);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Tolerância em Minutos (Saída).:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(60, 130, 240, 20);

        jLObrigatorioNome1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLObrigatorioNome1.setForeground(new java.awt.Color(204, 0, 0));
        jLObrigatorioNome1.setText("*");
        getContentPane().add(jLObrigatorioNome1);
        jLObrigatorioNome1.setBounds(440, 120, 20, 30);

        jcVerificarTolerância.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jcVerificarTolerância.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jcVerificarTolerância.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jcVerificarTolerânciaMouseClicked(evt);
            }
        });
        jcVerificarTolerância.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcVerificarTolerânciaActionPerformed(evt);
            }
        });
        getContentPane().add(jcVerificarTolerância);
        jcVerificarTolerância.setBounds(240, 160, 20, 20);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Não Verificar Tolerância.:");
        jLabel10.setToolTipText("qweqwe");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(60, 160, 180, 20);

        jlCadProfessores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/cadGenerico.png"))); // NOI18N
        jlCadProfessores.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(jlCadProfessores);
        jlCadProfessores.setBounds(0, 0, 600, 420);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltarActionPerformed
        dispose();
    }//GEN-LAST:event_btVoltarActionPerformed

    private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
        if (Util.chkVazio(tfTolerancia.getText())) {
            if (Integer.parseInt(tfToleranciaSaida.getText()) <= 20 && Integer.parseInt(tfTolerancia.getText()) <= 20) {
                parametros.setGerarHorarioAutomatico(jcGerarPonto.isSelected());
                parametros.setTempoToleranciaEntrada(Integer.parseInt(tfTolerancia.getText()));
                parametros.setTempoToleranciaSaida(Integer.parseInt(tfToleranciaSaida.getText()));
                parametros.setIgnorarTolerancia(jcVerificarTolerância.isSelected());
                parametrosDAO.atualizar(parametros);
            } else {
                JOptionPane.showMessageDialog(null, "Não é possível cadastrar tolerâncias acima de 20min");
            }

        }
    }//GEN-LAST:event_btSalvarActionPerformed

    private void tfToleranciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfToleranciaActionPerformed

    }//GEN-LAST:event_tfToleranciaActionPerformed

    private void tfToleranciaSaidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfToleranciaSaidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfToleranciaSaidaActionPerformed

    private void jcVerificarTolerânciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcVerificarTolerânciaActionPerformed
        if (jcGerarPonto.isSelected()) {
            jcGerarPonto.setSelected(false);
        }
    }//GEN-LAST:event_jcVerificarTolerânciaActionPerformed

    private void jcGerarPontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcGerarPontoActionPerformed
        if (jcVerificarTolerância.isSelected()) {
            jcVerificarTolerância.setSelected(false);
        }

    }//GEN-LAST:event_jcGerarPontoActionPerformed

    private void jcVerificarTolerânciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcVerificarTolerânciaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jcVerificarTolerânciaMouseClicked

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
            java.util.logging.Logger.getLogger(CadastroParametros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroParametros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroParametros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroParametros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CadastroParametros dialog = new CadastroParametros(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btSalvar;
    private javax.swing.JButton btVoltar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLObrigatorioNome;
    private javax.swing.JLabel jLObrigatorioNome1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JCheckBox jcGerarPonto;
    private javax.swing.JCheckBox jcVerificarTolerância;
    private javax.swing.JLabel jlCadProfessores;
    private javax.swing.JFileChooser selecionarFoto;
    private javax.swing.JTextField tfTolerancia;
    private javax.swing.JTextField tfToleranciaSaida;
    // End of variables declaration//GEN-END:variables
}
