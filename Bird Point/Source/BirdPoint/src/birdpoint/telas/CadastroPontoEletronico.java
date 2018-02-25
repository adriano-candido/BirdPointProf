/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.telas;

import birdpoint.email.Email;
import birdpoint.funcionario.Funcionario;
import birdpoint.funcionario.FuncionarioDAO;
import birdpoint.registro.ponto.Ponto;
import birdpoint.registro.ponto.PontoDAO;
import birdpoint.registro.ponto.PontoTableModelRegistro;
import birdpoint.util.LeitorBiometrico;
import birdpoint.util.Relogio;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPTemplate;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CadastroPontoEletronico extends javax.swing.JDialog {

    Email email = new Email();
    boolean enviouEmail = false;

    List<Funcionario> listaFuncionarioes;
    Funcionario funcionario = new Funcionario();
    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    List<Ponto> listaPontoLocal = new ArrayList<>();
    List<Ponto> listaPontosDiario = new ArrayList<>();
    Ponto ponto = new Ponto();
    PontoDAO pontoDAO = new PontoDAO();

    SimpleDateFormat formatarData = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat formatarHora = new SimpleDateFormat("HH");
    SimpleDateFormat formatarMinuto = new SimpleDateFormat("mm");
    SimpleDateFormat formatarDiaSemana = new SimpleDateFormat("E");
    SimpleDateFormat formatarHoraCompleta = new SimpleDateFormat("HH:mm:ss");
    Date dataHoraSistema;

    LeitorBiometrico digital = new LeitorBiometrico();
    DPFPTemplate templateDigital = DPFPGlobal.getTemplateFactory().createTemplate();

    List<Funcionario> listaFuncionarioesManha = new ArrayList<>();
    List<Funcionario> listaFuncionarioesTarde = new ArrayList<>();
    List<Funcionario> listaFuncionarioesNoite = new ArrayList<>();

    List<Ponto> listaPontoTabela = new ArrayList<>();

    public CadastroPontoEletronico(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        dataHoraSistema = new Date();
        listaPontosDiario = pontoDAO.checkExistseq("dataPonto", formatarData.format(dataHoraSistema));
        listaFuncionarioes = funcionarioDAO.listar();
        mostrarHora();
        atualizarTabela();

        new Thread() {
            @Override
            public void run() {
                compararDigital();
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
//                            dataHoraSistema = new Date();
//                            int hora = Integer.parseInt(formatarHora.format(dataHoraSistema));
//                            if (hora == 23) {
//                                System.exit(0);
//                            }
                            sleep(900000);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(CadastroPontoEletronico.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();

    }


    public void atualizarTabela() {
        PontoTableModelRegistro modeloTabela = new PontoTableModelRegistro(listaPontoTabela);
        tbFuncionariosPonto.setModel(modeloTabela);
        tbFuncionariosPonto.getColumnModel().getColumn(0).setPreferredWidth(300);
    }

    // Este método apagará todas as duplicidades de ponto
    public void apagarDuplicidadePonto(Funcionario funcionario) {
        List<Ponto> listaPontosFuncionario = pontoDAO.checkExistsPontoFuncionario("dataPonto", formatarData.format(dataHoraSistema),
                "funcionario.idFuncionario", funcionario.getIdFuncionario(),
                "turnoPonto", carregarTurno());

        for (int i = 1; i < listaPontosFuncionario.size(); i++) {
            pontoDAO.remover(listaPontosFuncionario.get(i));
        }

    }

    // Este método registrará o ponto do funcionario
    public void registrarPresentePonto(Funcionario funcionario) {
        apagarDuplicidadePonto(funcionario);
        ponto = new Ponto();
        List<Ponto> listaPontosFuncionario = pontoDAO.checkExistsPontoFuncionario("dataPonto", formatarData.format(dataHoraSistema),
                "funcionario.idFuncionario", funcionario.getIdFuncionario(),
                "turnoPonto", carregarTurno());
        dataHoraSistema = new Date();
        if (!listaPontosFuncionario.isEmpty()) {
            ponto = listaPontosFuncionario.get(0);
            if (ponto.getHoraEntradaPonto() == null) {
                ponto.setHoraEntradaPonto(Time.valueOf(formatarHoraCompleta.format(dataHoraSistema)));
            } else if (ponto.getHoraEntradaPonto() != null && ponto.getHoraSaidaPonto() == null) {
                ponto.setHoraSaidaPonto(Time.valueOf(formatarHoraCompleta.format(dataHoraSistema)));
            } else {
                ponto.setHoraEntradaPonto(Time.valueOf(formatarHoraCompleta.format(dataHoraSistema)));
                ponto.setHoraSaidaPonto(null);
            }
            listaPontoTabela.add(0, ponto);
            atualizarTabela();
            pontoDAO.atualizar(ponto);
            abrirTelaMensagemPonto(ponto);

        } else {
            ponto.setDataPontoCompleta(dataHoraSistema);
            ponto.setDataPonto(formatarData.format(dataHoraSistema));
            ponto.setDiaDaSemana(formatarDiaSemana.format(dataHoraSistema));
            ponto.setFuncionario(funcionario);
            ponto.setTurnoPonto(carregarTurno());
            ponto.setHoraEntradaPonto(Time.valueOf(formatarHoraCompleta.format(dataHoraSistema)));
            listaPontoTabela.add(0, ponto);
            atualizarTabela();
            pontoDAO.adicionar(ponto);
            abrirTelaMensagemPonto(ponto);
        }

    }

    public void abrirTelaMensagemPonto(Ponto ponto) {
        String entradaOuSaida = "";
        if (ponto.getHoraSaidaPonto() == null) {
            entradaOuSaida = "Entrada";
        } else {
            entradaOuSaida = "Saída";
        }
        new MensagemPonto(null, true, ponto.getFuncionario(), entradaOuSaida, carregarTurno()).setVisible(true);
    }

// Este método retorna o turno baseado no horário atual
    public String carregarTurno() {
        dataHoraSistema = new Date();
        int hora = Integer.parseInt(formatarHora.format(dataHoraSistema));
        int minuto = Integer.parseInt(formatarMinuto.format(dataHoraSistema));
        if ((hora >= 5 && (hora <= 12 )) || ((hora == 12) && minuto <= 59)) {
            return "Manhã";
        } else if ((hora >= 13 && hora < 18) || ((hora == 17) && minuto <= 59)) {
            return "Tarde";
        } else {
            return "Noite";
        }
    }


    public void cadastrarPontoDiario() {
        dataHoraSistema = new Date();
        for (Funcionario funcManha : listaFuncionarioesManha) {
            ponto.setDataPonto(formatarData.format(dataHoraSistema));
            ponto.setFuncionario(funcManha);
            ponto.setTurnoPonto("Manhã");
            ponto.setDataPontoCompleta(dataHoraSistema);
            ponto.setDiaDaSemana(formatarDiaSemana.format(dataHoraSistema));
            pontoDAO.adicionar(ponto);
            ponto = new Ponto();
        }
        for (Funcionario funcTarde : listaFuncionarioesTarde) {
            ponto.setDataPonto(formatarData.format(dataHoraSistema));
            ponto.setFuncionario(funcTarde);
            ponto.setTurnoPonto("Tarde");
            ponto.setDataPontoCompleta(dataHoraSistema);
            ponto.setDiaDaSemana(formatarDiaSemana.format(dataHoraSistema));
            pontoDAO.adicionar(ponto);
            ponto = new Ponto();
        }
        for (Funcionario funcNoite : listaFuncionarioesNoite) {
            ponto.setDataPonto(formatarData.format(dataHoraSistema));
            ponto.setFuncionario(funcNoite);
            ponto.setTurnoPonto("Noite");
            ponto.setDataPontoCompleta(dataHoraSistema);
            ponto.setDiaDaSemana(formatarDiaSemana.format(dataHoraSistema));
            pontoDAO.adicionar(ponto);
            ponto = new Ponto();
        }
    }



    public Funcionario carregarFuncionario(int id) {
        for (Funcionario funcionario : listaFuncionarioes) {
            if (funcionario.getIdFuncionario() == id) {
                return funcionario;
            }
        }
        return null;
    }


    public void mostrarHora() {
        Relogio ah = new Relogio(tfHora);
        ah.mostrarData(true);
        Thread thHora = ah;
        thHora.start();
    }

    private void telaMensagemPonto(Funcionario funcionario, String verificarEntradaOuSaida) {
        new MensagemPonto(null, rootPaneCheckingEnabled, funcionario, verificarEntradaOuSaida, carregarTurno()).setVisible(true);
    }

    // Este método compara a digital inserida no leitor
    private void compararDigital() {
        funcionario = new Funcionario();
        funcionario = digital.verificarSeCadastrado(null, listaFuncionarioes);
        if (funcionario != null) {
            registrarPresentePonto(funcionario);
            jlFuncionarioNaoLocalizado.setText("");

        } else {
            jlFuncionarioNaoLocalizado.setText("Funcionario não localizado!");
        }
        compararDigital();
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
        jInternalFrame1 = new javax.swing.JInternalFrame();
        btVoltar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbFuncionariosPonto = new javax.swing.JTable();
        jlFuncionarioNaoLocalizado = new javax.swing.JLabel();
        tfHora = new javax.swing.JLabel();
        tfHora1 = new javax.swing.JLabel();
        tfHora2 = new javax.swing.JLabel();
        tfHora3 = new javax.swing.JLabel();
        jlCadProfessores = new javax.swing.JLabel();

        selecionarFoto.setMaximumSize(new java.awt.Dimension(580, 245));
        selecionarFoto.setMinimumSize(new java.awt.Dimension(550, 245));
        selecionarFoto.setPreferredSize(new java.awt.Dimension(520, 320));

        jInternalFrame1.setVisible(true);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 410));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(null);

        btVoltar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/voltar.png"))); // NOI18N
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
        btVoltar.setBounds(60, 350, 90, 50);

        tbFuncionariosPonto.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        tbFuncionariosPonto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbFuncionariosPonto);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(20, 120, 560, 230);

        jlFuncionarioNaoLocalizado.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(jlFuncionarioNaoLocalizado);
        jlFuncionarioNaoLocalizado.setBounds(210, 100, 190, 20);

        tfHora.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        tfHora.setText("Hora.:");
        getContentPane().add(tfHora);
        tfHora.setBounds(400, 100, 180, 19);

        tfHora1.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        tfHora1.setText("Noite: 18:00 ás 23:00");
        getContentPane().add(tfHora1);
        tfHora1.setBounds(20, 100, 210, 19);

        tfHora2.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        tfHora2.setText("Manhã: 5:00 ás 12:59");
        getContentPane().add(tfHora2);
        tfHora2.setBounds(20, 60, 190, 19);

        tfHora3.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        tfHora3.setText("Tarde: 13:00 ás 17:59");
        getContentPane().add(tfHora3);
        tfHora3.setBounds(20, 80, 200, 19);

        jlCadProfessores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/CadastroDePonto.png"))); // NOI18N
        jlCadProfessores.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(jlCadProfessores);
        jlCadProfessores.setBounds(0, 0, 600, 410);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltarActionPerformed
        dispose();
    }//GEN-LAST:event_btVoltarActionPerformed

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
            java.util.logging.Logger.getLogger(CadastroPontoEletronico.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroPontoEletronico.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroPontoEletronico.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroPontoEletronico.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>


        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CadastroPontoEletronico dialog = new CadastroPontoEletronico(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btVoltar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlCadProfessores;
    private javax.swing.JLabel jlFuncionarioNaoLocalizado;
    private javax.swing.JFileChooser selecionarFoto;
    private javax.swing.JTable tbFuncionariosPonto;
    private javax.swing.JLabel tfHora;
    private javax.swing.JLabel tfHora1;
    private javax.swing.JLabel tfHora2;
    private javax.swing.JLabel tfHora3;
    // End of variables declaration//GEN-END:variables
}
