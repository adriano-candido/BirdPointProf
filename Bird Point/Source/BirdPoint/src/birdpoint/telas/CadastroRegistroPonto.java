/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.telas;

import birdpoint.email.Email;
import birdpoint.funcionario.Funcionario;
import birdpoint.funcionario.FuncionarioDAO;
import birdpoint.horariosemanal.Horario;
import birdpoint.horariosemanal.HorarioDAO;
import birdpoint.horariosemanal.HorarioSemanal;
import birdpoint.horariosemanal.HorarioSemanalDAO;
import birdpoint.parametrizacao.Parametrizacao;
import birdpoint.parametrizacao.ParametrizacaoDAO;
import birdpoint.registro.ponto.Ponto;
import birdpoint.registro.ponto.PontoDAO;
import birdpoint.registro.ponto.PontoTableModelRegistro;
import birdpoint.util.LeitorBiometrico;
import birdpoint.util.Relogio;
import birdpoint.util.Util;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPTemplate;
import static java.lang.Thread.sleep;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adrianolimacandido
 */
public class CadastroRegistroPonto extends javax.swing.JFrame {

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
    SimpleDateFormat formatarDiaSemana = new SimpleDateFormat("EEEE");
    SimpleDateFormat formatarHoraCompleta = new SimpleDateFormat("HH:mm:ss");
    Date dataHoraSistema;

    LeitorBiometrico digital = new LeitorBiometrico();
    DPFPTemplate templateDigital = DPFPGlobal.getTemplateFactory().createTemplate();

    List<Funcionario> listaFuncionarioesManha = new ArrayList<>();
    List<Funcionario> listaFuncionarioesTarde = new ArrayList<>();
    List<Funcionario> listaFuncionarioesNoite = new ArrayList<>();

    List<Ponto> listaPontoTabela = new ArrayList<>();

    //Horários funcionário
    ArrayList<HorarioSemanal> listaHorarioSemanal = new ArrayList<>();
    HorarioSemanalDAO horarioSemanalDAO = new HorarioSemanalDAO();
    Horario horario = new Horario();
    HorarioDAO horarioDAO = new HorarioDAO();

    Parametrizacao parametros;
    
    public CadastroRegistroPonto() {
        initComponents();
        dataHoraSistema = new Date();
        listaPontosDiario = pontoDAO.checkExistseq("dataPonto", formatarData.format(dataHoraSistema));
        listaFuncionarioes = funcionarioDAO.listar();
        mostrarHora();
        atualizarTabela();
        parametros = new ParametrizacaoDAO().listar().get(0);

        if (parametros.isIgnorarTolerancia()) {
            tfToleranciaEntrada.setText("");
            tfToleranciaSaida.setText("");
        } else {
            tfToleranciaEntrada.setText("Tolerância Entrada.: " + parametros.getTempoToleranciaEntrada() + "min.");
            tfToleranciaSaida.setText("Tolerância Saída.: " + parametros.getTempoToleranciaSaida() + "min.");
        }

        new Thread() {
            @Override
            public void run() {
                try {
                    compararDigital();
                } catch (ParseException ex) {
                    Logger.getLogger(CadastroPontoEletronico.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();

        //Gerar pontos automáticos
        try {
            if (parametros.isGerarHorarioAutomatico()) {
                gerarPontosAutomaticos();
            }
        } catch (Exception ex) {
            Logger.getLogger(CadastroPontoEletronico.class.getName()).log(Level.SEVERE, null, ex);
        }

        new Thread() {
            @Override
            public void run() {
//                if (!parametros.isIgnorarTolerancia()) {
//                    try {
//                        while (true) {
//                            atualizarSaidasSemRegistro();
//                            sleep(1000);
//                        }
//                    } catch (ParseException ex) {
//                        Logger.getLogger(CadastroPontoEletronico.class.getName()).log(Level.SEVERE, null, ex);
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(CadastroPontoEletronico.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
            }
        }.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btVoltar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbFuncionariosPonto = new javax.swing.JTable();
        tfAvisos = new javax.swing.JLabel();
        tfHora = new javax.swing.JLabel();
        tfAvisos2 = new javax.swing.JLabel();
        tfToleranciaSaida = new javax.swing.JLabel();
        tfToleranciaEntrada = new javax.swing.JLabel();
        jlCadProfessores = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(602, 409));
        setMinimumSize(new java.awt.Dimension(602, 409));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(602, 409));
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

        tfAvisos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        getContentPane().add(tfAvisos);
        tfAvisos.setBounds(20, 80, 560, 20);

        tfHora.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        tfHora.setText("Hora.:");
        getContentPane().add(tfHora);
        tfHora.setBounds(30, 60, 200, 20);

        tfAvisos2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tfAvisos2.setForeground(new java.awt.Color(255, 51, 51));
        getContentPane().add(tfAvisos2);
        tfAvisos2.setBounds(20, 100, 560, 20);

        tfToleranciaSaida.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tfToleranciaSaida.setText("Tolerância Saida.:");
        getContentPane().add(tfToleranciaSaida);
        tfToleranciaSaida.setBounds(410, 60, 170, 20);

        tfToleranciaEntrada.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tfToleranciaEntrada.setText("Tolerância Entrada.:");
        getContentPane().add(tfToleranciaEntrada);
        tfToleranciaEntrada.setBounds(230, 60, 180, 20);

        jlCadProfessores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/birdpoint/imagens/CadastroDePonto.png"))); // NOI18N
        jlCadProfessores.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        getContentPane().add(jlCadProfessores);
        jlCadProfessores.setBounds(0, 0, 600, 410);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void exibirAvisos(String texto, String texto2) {
        new Thread() {
            @Override
            public void run() {
                try {
                    tfAvisos.setText(texto);
                    tfAvisos2.setText(texto2);
                    sleep(5000);
                    tfAvisos.setText("");
                    tfAvisos2.setText("");
                } catch (Exception ex) {
                    Logger.getLogger(CadastroPontoEletronico.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();

    }

    //Este método atualiza todos os registros que passaram do tempo de saída permitido.
    public void atualizarSaidasSemRegistro() throws ParseException {
        Date horaSistema = new Date();
        List<Ponto> listaPonto = pontoDAO.checkExists("dataPonto", formatarData.format(horaSistema));
        //criada pq perde sobrescreve quando a tolerancia existe e é chamado o metodo verificarTolerancia
        Date dataSaidaTemp;

        for (Ponto pontoFor : listaPonto) {
            if (pontoFor.getHoraSaidaPonto() == null && pontoFor.getHoraEntradaPonto() != null) {
                //Pegando a hora de sair antes de atribuir os minutos para n perder a referência
                dataSaidaTemp = formatarHoraCompleta.parse(formatarHoraCompleta.format(pontoFor.getHoraPrevistaSaida()));
                //adicionando o tempo de tolerância a hora da saída
                pontoFor.getHoraPrevistaSaida().setMinutes(pontoFor.getHoraPrevistaSaida().getMinutes() + parametros.getTempoToleranciaSaida());
                if (Util.verificarTolerancia((pontoFor.getHoraPrevistaSaida()), new Date(), parametros.isIgnorarTolerancia())) {
                    String hora = Util.diferencaEntreHoras(formatarHoraCompleta.format(dataSaidaTemp), formatarHoraCompleta.format(new Date()));
                    Date horaSaida = formatarHoraCompleta.parse(hora);
                    int teste = horaSaida.getMinutes();

                    pontoFor.setHoraSaidaPonto(Time.valueOf("00:00:00"));
                    Date horasTrabalhadas = formatarHoraCompleta.parse(Util.diferencaEntreHoras(String.valueOf(pontoFor.getHoraEntradaPonto()), formatarHoraCompleta.format(dataSaidaTemp)));
                    pontoFor.setQtdHorasTrabalhadas(Time.valueOf(formatarHoraCompleta.format(horasTrabalhadas)));
                    pontoFor.setHoraPrevistaSaida(Time.valueOf(formatarHoraCompleta.format(dataSaidaTemp)));
                    pontoDAO.atualizar(pontoFor);
                }
            }
        }
    }

    //Este método vai gerar os pontos diários (uma única vez por dia )
    public void gerarPontosAutomaticos() throws ParseException {
        Date horaSistema = new Date();
        if (pontoDAO.checkExists("dataPonto", formatarData.format(horaSistema)).isEmpty()) {
            List<Horario> horariosFuncionarios = horarioDAO.listar();
            List<HorarioSemanal> horariosFuncionario;

            for (Horario horarioFor : horariosFuncionarios) {
                horariosFuncionario = horarioSemanalDAO.converterJsonEmLista(horarioFor.getListaHorario(), HorarioSemanal.class);
                for (HorarioSemanal horarioFunc : horariosFuncionario) {
                    if (horarioFunc.getNomeDiaSemana().equalsIgnoreCase(formatarDiaSemana.format(horaSistema))) {
                        ponto = new Ponto();
                        ponto.setDataPonto(formatarData.format(horaSistema));
                        ponto.setDiaDaSemana(formatarDiaSemana.format(horaSistema));
                        ponto.setFuncionario(horarioFor.getFuncionario());
                        Date dataNova = formatarHoraCompleta.parse("00:00:00");
                        ponto.setQtdHorasTrabalhadas(Time.valueOf(formatarHoraCompleta.format(dataNova)));
                        pontoDAO.adicionar(ponto);
                    }
                }
            }
        }
    }

    public void atualizarTabela() {
        PontoTableModelRegistro modeloTabela = new PontoTableModelRegistro(listaPontoTabela);
        tbFuncionariosPonto.setModel(modeloTabela);
        tbFuncionariosPonto.getColumnModel().getColumn(0).setPreferredWidth(300);
    }

    // Este método registrará o ponto do funcionario
    public void registrarPresentePonto(Funcionario funcionario) throws ParseException {
        if (!new ParametrizacaoDAO().listar().isEmpty()) {
            if (!horarioDAO.checkExistseq("funcionario.idFuncionario", funcionario.getIdFuncionario()).isEmpty()) {
                List<Ponto> listaPontosFuncionario;
                dataHoraSistema = new Date();

                horario = horarioDAO.checkExistseq("funcionario.idFuncionario", funcionario.getIdFuncionario()).get(0);
                listaHorarioSemanal = (ArrayList) horarioSemanalDAO.converterJsonEmLista(horario.getListaHorario(), HorarioSemanal.class);

                //esta variável verifica se o funcionário tem o dia atual no horário dele
                boolean verificarDiaPonto = false;
                //esta variável verifica se está registrando o ponto novamente
                boolean registrarPontoNovo = true;
                for (HorarioSemanal horarioFor : listaHorarioSemanal) {
                    if (horarioFor.getNomeDiaSemana().equalsIgnoreCase(formatarDiaSemana.format(dataHoraSistema)) || parametros.isIgnorarTolerancia()) {

                        //essa instrução ignora horários que já passaram do limite máximo ou não chegou no horário
                        if (!parametros.isIgnorarTolerancia()) {
                            //Verificando se o horário que tá percorrendo pode dar entrada no funcionário
                            if (!Util.verificarTolerancia(formatarHoraCompleta.parse(formatarHoraCompleta.format(horarioFor.getHoraEntrada())), dataHoraSistema, false)) {
                                continue;
                            }
                            Date horaSaidaMaxima = formatarHoraCompleta.parse(formatarHoraCompleta.format(horarioFor.getHoraSaida()));
                            //adicionando o tempo de tolerância a hora da saída
                            horaSaidaMaxima.setMinutes((horaSaidaMaxima.getMinutes() + 1) + parametros.getTempoToleranciaSaida());
                            if (Util.verificarTolerancia(horaSaidaMaxima, dataHoraSistema, false)) {
                                continue;
                            }
                        }

                        listaPontosFuncionario = pontoDAO.checkExistsPontoFuncionario("dataPonto", formatarData.format(dataHoraSistema),
                                "funcionario.idFuncionario", funcionario.getIdFuncionario());
                        if (!listaPontosFuncionario.isEmpty()) {
                            for (Ponto pontoCarregado : listaPontosFuncionario) {
                                //Ignorar os pontos que já tem entrada e saída
                                if (pontoCarregado.getHoraSaidaPonto() != null && pontoCarregado.getHoraEntradaPonto() != null) {
                                    continue;
                                }

                                if (pontoCarregado.getHoraEntradaPonto() == null) {

                                    //Hora com os minutos de tolerância
                                    Date horaEntradaTemp = formatarHoraCompleta.parse(formatarHoraCompleta.format(horarioFor.getHoraEntrada()));
                                    //adicionando o tempo de tolerância a hora da saída
                                    horaEntradaTemp.setMinutes(horaEntradaTemp.getMinutes() + parametros.getTempoToleranciaEntrada() + 1);
                                    if (Util.verificarTolerancia(dataHoraSistema, horaEntradaTemp, parametros.isIgnorarTolerancia())) {
                                        pontoCarregado.setHoraEntradaPonto(Time.valueOf(formatarHoraCompleta.format(dataHoraSistema)));
                                        pontoCarregado.setDataPontoCompleta(dataHoraSistema);
                                        pontoCarregado.setHoraPrevistaSaida(horarioFor.getHoraSaida());
                                        listaPontoTabela.add(0, pontoCarregado);
                                        atualizarTabela();
                                        pontoDAO.atualizar(pontoCarregado);
                                        abrirTelaMensagemPonto(pontoCarregado);
                                        registrarPontoNovo = false;
                                        return;
                                    } else {
                                        //fazer instrução pra hora de entrar não ser mais null
                                        //exibirAvisos("Você ultrapassou a tolerância.", "Não foi possível registrar sua entrada");
                                        return;
                                    }

                                }
                                if (pontoCarregado.getHoraEntradaPonto() != null && pontoCarregado.getHoraSaidaPonto() == null) {
                                    if (Util.verificarTolerancia(horarioFor.getHoraSaida(), dataHoraSistema, parametros.isIgnorarTolerancia())) {
                                        pontoCarregado.setHoraSaidaPonto(Time.valueOf(formatarHoraCompleta.format(dataHoraSistema)));
                                        Date dataNova = formatarHoraCompleta.parse(Util.diferencaEntreHoras(String.valueOf(pontoCarregado.getHoraEntradaPonto()), String.valueOf(pontoCarregado.getHoraSaidaPonto())));
                                        pontoCarregado.setQtdHorasTrabalhadas(Time.valueOf(formatarHoraCompleta.format(dataNova)));
                                        registrarPontoNovo = false;
                                        listaPontoTabela.add(0, pontoCarregado);
                                        atualizarTabela();
                                        pontoDAO.atualizar(pontoCarregado);
                                        abrirTelaMensagemPonto(pontoCarregado);
                                        return;
                                    } else {
                                        exibirAvisos("Não chegou o horário de você sair.", "Não foi possível registrar sua saída");
                                        return;
                                    }

                                }

                                listaPontoTabela.add(0, pontoCarregado);
                                atualizarTabela();
                                pontoDAO.atualizar(pontoCarregado);
                                abrirTelaMensagemPonto(pontoCarregado);

                            }
                        }

                        // SE NÃO TIVER PONTO REGISTRADO, SEMPRE ENTRA AQUI
                        if (registrarPontoNovo && !parametros.isGerarHorarioAutomatico()) {
                            //Hora com os minutos de tolerância
                            Date horaEntradaTemp = formatarHoraCompleta.parse(formatarHoraCompleta.format(horarioFor.getHoraEntrada()));
                            //adicionando o tempo de tolerância a hora da saída
                            horaEntradaTemp.setMinutes(horaEntradaTemp.getMinutes() + parametros.getTempoToleranciaEntrada() + 1);
                            if (Util.verificarTolerancia(dataHoraSistema, horaEntradaTemp, parametros.isIgnorarTolerancia())) {
                                ponto = new Ponto();
                                ponto.setDataPontoCompleta(dataHoraSistema);
                                ponto.setDataPonto(formatarData.format(dataHoraSistema));
                                ponto.setDiaDaSemana(formatarDiaSemana.format(dataHoraSistema));
                                ponto.setHoraPrevistaSaida(horarioFor.getHoraSaida());
                                ponto.setFuncionario(funcionario);
                                Date dataNova = formatarHoraCompleta.parse("00:00:00");
                                ponto.setQtdHorasTrabalhadas(Time.valueOf(formatarHoraCompleta.format(dataNova)));
                                ponto.setHoraEntradaPonto(Time.valueOf(formatarHoraCompleta.format(dataHoraSistema)));
                                listaPontoTabela.add(0, ponto);
                                atualizarTabela();
                                pontoDAO.adicionar(ponto);
                                abrirTelaMensagemPonto(ponto);
                                return;
                            } else {
                                exibirAvisos("Não chegou o horário de você entrar.", "Não foi possível registrar sua entrada");
                                return;
                            }
                        }

                    } else {
                        //exibirAvisos("Não chegou o horário de você entrar ou não possui entrada p/ hoje!", "");
                    }

                }
            } else {
                exibirAvisos("Você não possui horário cadastrado!", "");
            }
        } else {
            exibirAvisos("Dados da Parametrização não Cadastrados!", "Erro ao carregar parametrização.");
        }

    }

    public void abrirTelaMensagemPonto(Ponto ponto) {
        String entradaOuSaida = "";
        if (ponto.getHoraSaidaPonto() == null) {
            entradaOuSaida = "Entrada";
        } else {
            entradaOuSaida = "Saída";
        }
        new MensagemPonto(null, true, ponto.getFuncionario(), entradaOuSaida, String.valueOf(ponto.getQtdHorasTrabalhadas())).setVisible(true);
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
        new MensagemPonto(null, rootPaneCheckingEnabled, funcionario, verificarEntradaOuSaida, "").setVisible(true);
    }

    // Este método compara a digital inserida no leitor
    private void compararDigital() throws ParseException {
        funcionario = new Funcionario();
        funcionario = digital.verificarSeCadastrado(null, listaFuncionarioes);
        if (funcionario != null) {
            registrarPresentePonto(funcionario);
            tfAvisos.setText("");

        } else {
            exibirAvisos("", "Funcionário não localizado!");
        }
        compararDigital();
    }
    
    private void btVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVoltarActionPerformed
        System.exit(0);
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
            java.util.logging.Logger.getLogger(CadastroRegistroPonto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadastroRegistroPonto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadastroRegistroPonto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadastroRegistroPonto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroRegistroPonto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btVoltar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlCadProfessores;
    private javax.swing.JTable tbFuncionariosPonto;
    private javax.swing.JLabel tfAvisos;
    private javax.swing.JLabel tfAvisos2;
    private javax.swing.JLabel tfHora;
    private javax.swing.JLabel tfToleranciaEntrada;
    private javax.swing.JLabel tfToleranciaSaida;
    // End of variables declaration//GEN-END:variables
}
