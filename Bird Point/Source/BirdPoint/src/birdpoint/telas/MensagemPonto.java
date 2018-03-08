/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.telas;

import birdpoint.funcionario.Funcionario;
import birdpoint.util.Relogio;
import birdpoint.util.Util;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import static java.lang.Thread.sleep;

/**
 * @author Adriano Lima
 */
public class MensagemPonto extends javax.swing.JDialog {

    SimpleDateFormat formatarData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date dataHoraSistema;

    Funcionario funcionario = new Funcionario();
    String entradaOuSaida;
    String horasTrab;

    String turno;

    public MensagemPonto(java.awt.Frame parent, boolean modal, Funcionario funcionario, String entradaOuSaida, String turno, String horasTrab) {
        super(parent, modal);
        initComponents();
        tfNomeFuncionario.setText(funcionario.getNomeFuncionario());
        this.funcionario = funcionario;
        this.entradaOuSaida = entradaOuSaida;
        this.turno = turno;
        this.horasTrab = horasTrab;
        preencherTextoTela();
        carregarFoto();
        mostrarHora();
        encerrarTela();
    }

    public void encerrarTela() {
        new Thread() {
            public void run() {
                for (int i = 3; i >= 0; i--) {
                    try {
                        tfTempo.setText(String.valueOf(i));
                        sleep(1000);
                    } catch (Exception e) {
                    }
                }
                dispose();
            }

        }.start();
    }

    public void preencherTextoTela() {
        if (entradaOuSaida.equalsIgnoreCase("Entrada")) {
            tfMensagem1.setText("Seja Bem Vindo(a) .:");
            tfMensagem2.setText("Lhe desejamos um ótimo trabalho.");
            tfMensagem3.setText("Entrada");
            tfTurno.setText("Turno.: " + turno);
        } else {
            tfMensagem1.setText("Até Logo .:");
            tfMensagem2.setText("Agradecemos sua presença.");
            tfMensagem3.setText("Saída");
            tfTurno.setText("Turno.: " + turno);
            tfHorasTrab.setText("Horas Trab:. " + horasTrab);
        }
    }

    public void mostrarHora() {
        Relogio ah = new Relogio(tfHora);
        ah.mostrarData(true);
        Thread thHora = ah;
        thHora.start();
    }

    private void carregarFoto() {
        try {
            ImageIcon foto = new ImageIcon();
            foto.setImage(Util.byteToImage(funcionario.getFotoFunc()));
            btFoto.setIcon(foto);
        } catch (Exception e) {
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        selecionarFoto = new javax.swing.JFileChooser();
        btFoto = new javax.swing.JButton();
        tfHora = new javax.swing.JLabel();
        tfNomeFuncionario = new javax.swing.JLabel();
        tfMensagem2 = new javax.swing.JLabel();
        tfMensagem1 = new javax.swing.JLabel();
        tfHora1 = new javax.swing.JLabel();
        tfTempo = new javax.swing.JLabel();
        tfHorasTrab = new javax.swing.JLabel();
        tfMensagem3 = new javax.swing.JLabel();
        tfTurno = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();

        selecionarFoto.setMaximumSize(new java.awt.Dimension(580, 245));
        selecionarFoto.setMinimumSize(new java.awt.Dimension(550, 245));
        selecionarFoto.setPreferredSize(new java.awt.Dimension(520, 320));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(560, 270));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(null);

        btFoto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/default.jpg"))); // NOI18N
        btFoto.setContentAreaFilled(false);
        btFoto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btFoto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btFoto.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFotoActionPerformed(evt);
            }
        });
        getContentPane().add(btFoto);
        btFoto.setBounds(20, 30, 120, 150);

        tfHora.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        tfHora.setText("Hora.:");
        getContentPane().add(tfHora);
        tfHora.setBounds(370, 10, 180, 20);

        tfNomeFuncionario.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        tfNomeFuncionario.setText("nomeFuncionario");
        getContentPane().add(tfNomeFuncionario);
        tfNomeFuncionario.setBounds(150, 90, 390, 30);

        tfMensagem2.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        tfMensagem2.setText("Mensagem2");
        getContentPane().add(tfMensagem2);
        tfMensagem2.setBounds(30, 190, 480, 30);

        tfMensagem1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tfMensagem1.setText("Mensagem1");
        getContentPane().add(tfMensagem1);
        tfMensagem1.setBounds(150, 60, 300, 30);

        tfHora1.setFont(new java.awt.Font("Tahoma", 2, 15)); // NOI18N
        tfHora1.setText("Aguarde enquanto estamos registrando o horário de sua");
        getContentPane().add(tfHora1);
        tfHora1.setBounds(70, 230, 380, 30);

        tfTempo.setFont(new java.awt.Font("Tahoma", 3, 16)); // NOI18N
        getContentPane().add(tfTempo);
        tfTempo.setBounds(520, 230, 30, 30);

        tfHorasTrab.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        getContentPane().add(tfHorasTrab);
        tfHorasTrab.setBounds(370, 50, 180, 20);

        tfMensagem3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tfMensagem3.setText("Entrada");
        getContentPane().add(tfMensagem3);
        tfMensagem3.setBounds(450, 230, 60, 30);

        tfTurno.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        tfTurno.setText("Turno.: ");
        getContentPane().add(tfTurno);
        tfTurno.setBounds(370, 30, 180, 20);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/tela de alerta 3.png"))); // NOI18N
        jLabel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 560, 270);

        jPanel1.setBackground(new java.awt.Color(153, 255, 102));
        jPanel1.setForeground(new java.awt.Color(204, 255, 51));
        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 560, 270);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFotoActionPerformed

    }//GEN-LAST:event_btFotoActionPerformed

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
            java.util.logging.Logger.getLogger(MensagemPonto.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MensagemPonto.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MensagemPonto.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MensagemPonto.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                MensagemPonto dialog = new MensagemPonto(new javax.swing.JFrame(), true, null, null, null, null);
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
    private javax.swing.JButton btFoto;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JFileChooser selecionarFoto;
    private javax.swing.JLabel tfHora;
    private javax.swing.JLabel tfHora1;
    private javax.swing.JLabel tfHorasTrab;
    private javax.swing.JLabel tfMensagem1;
    private javax.swing.JLabel tfMensagem2;
    private javax.swing.JLabel tfMensagem3;
    private javax.swing.JLabel tfNomeFuncionario;
    private javax.swing.JLabel tfTempo;
    private javax.swing.JLabel tfTurno;
    // End of variables declaration//GEN-END:variables
}
