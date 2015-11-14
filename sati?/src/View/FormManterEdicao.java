/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

/**
 *
 * @author Windows7
 */
public final class FormManterEdicao extends FormTemplate {

    /**
     * Creates new form FormManterEvento
     */
    private FormManterEdicao() {
        this.setTitle("Gerenciar Edição");
        initComponents();
        iniciarComponentes();
        this.setLocationRelativeTo(null);
        
        
        // Resetar todos os componentes
        bloquearComponentes();
        limparComponentes();
    }
    
    private static FormManterEdicao manterEvento= null;
    
    public static FormManterEdicao getFormManterEvento()
    {
        if(manterEvento == null)
            manterEvento = new FormManterEdicao();
        
        return manterEvento;
            
    }
    
    public void iniciarComponentes ()
    {        
        super.jTBBuscaRapida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "Titulo", "Tema", "Data de Inicio",
                "Data de Fim", "Data Prazo das Inscrições", "Agenda Finalizada"
            }
        ) {
            // Quatidade de Colunas
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, 
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        super.jScrollPane1.setViewportView(jTBBuscaRapida);
        
        jTBBuscaRapida.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTBBuscaRapidaMouseClicked(evt);
            }
        });
                
        jBTAlterar.setText("Alterar");
        jBTAlterar.setEnabled(false);
        jBTAlterar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTAlterarActionPerformed(evt);
            }
        });

        jBTSalvar.setText("Salvar");
        jBTSalvar.setEnabled(false);
        jBTSalvar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTSalvarActionPerformed(evt);
            }
        });

        jBTExcluir.setText("Excluir");
        jBTExcluir.setEnabled(false);
        jBTExcluir.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTExcluirActionPerformed(evt);
            }
        });

        jBTCadastrar.setText("Cadastrar");
        jBTCadastrar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTCadastrarActionPerformed(evt);
            }
        });

        jBTConfirmar.setText("Confirmar");
        jBTConfirmar.setEnabled(false);
        jBTConfirmar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTConfirmarActionPerformed(evt);
            }
        });

        jBTCancelar.setText("Cancelar");
        jBTCancelar.setEnabled(false);
        jBTCancelar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBTCancelarActionPerformed(evt);
            }
        });        
    }
    
    public void liberarComponentes()
    {
        this.jTFTema.setEnabled(true);
        this.jTFTitulo.setEnabled(true);
        this.jDCDataInicio.setEnabled(true);
        this.jDCDataFim.setEnabled(true);
        
        this.jRBSim.setEnabled(true);
        this.jRBNao.setEnabled(true);
                    
        this.jDCDataVencimento.setEnabled(true);
        
        this.jTFAgencia.setEnabled(true);
        this.jTFConta.setEnabled(true);
        this.jCBTipo.setEnabled(true);
        this.jTFCarteira.setEnabled(true);
    }
    
    public void bloquearComponentes()
    {
        this.jTFTema.setEnabled(false);
        this.jTFTitulo.setEnabled(false);
        this.jDCDataInicio.setEnabled(false);
        this.jDCDataFim.setEnabled(false);
        
        this.jRBSim.setEnabled(false);
        this.jRBNao.setEnabled(false);
                    
        this.jDCDataVencimento.setEnabled(false);
        
        this.jTFAgencia.setEnabled(false);
        this.jTFConta.setEnabled(false);
        this.jCBTipo.setEnabled(false);
        this.jTFCarteira.setEnabled(false);
    }
    // Define valores nulos para todos os componentes
    public void limparComponentes()
    { 
        this.jTFTema.setText(null);
        this.jTFTitulo.setText(null);
        this.jDCDataInicio.setDate(null);
        this.jDCDataFim.setDate(null);
        
        this.jRBSim.setSelected(false);
        this.jRBNao.setSelected(true);
                    
        this.jDCDataVencimento.setDate(null);
        
        this.jTFAgencia.setText(null);
        this.jTFConta.setText(null);
        this.jCBTipo.setSelectedIndex(0);
        this.jTFCarteira.setText(null);
    }
    
        @Override
    protected void jBTCadastrarActionPerformed(java.awt.event.ActionEvent evt) {                                             
        super.jBTCadastrarActionPerformed(evt);
        
        liberarComponentes();
    }                                            

    @Override
    protected void jBTConfirmarActionPerformed(java.awt.event.ActionEvent evt) {                                             
        super.jBTConfirmarActionPerformed(evt);
        
        bloquearComponentes();
        limparComponentes();
    }                                            

    @Override
    protected void jBTAlterarActionPerformed(java.awt.event.ActionEvent evt) {                                           
        super.jBTAlterarActionPerformed(evt);
        
        liberarComponentes();
    }                                          

    @Override
    protected void jBTSalvarActionPerformed(java.awt.event.ActionEvent evt) {                                          
        super.jBTSalvarActionPerformed(evt);
        
        bloquearComponentes();
        limparComponentes();
    }                                         

    @Override
    protected void jBTExcluirActionPerformed(java.awt.event.ActionEvent evt) {                                           
        super.jBTExcluirActionPerformed(evt);
        
        bloquearComponentes();
        limparComponentes();
    }                                          

    @Override
    protected void jTBBuscaRapidaMouseClicked(java.awt.event.MouseEvent evt) {  
        super.jTBBuscaRapidaMouseClicked(evt);
        
        bloquearComponentes();
    }                                           

    @Override
    protected void jBTCancelarActionPerformed(java.awt.event.ActionEvent evt) {                                            
        super.jBTCancelarActionPerformed(evt);
        
        bloquearComponentes();
        limparComponentes();
    } 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bGAgenda = new javax.swing.ButtonGroup();
        jPManterEdicao = new javax.swing.JPanel();
        jPEdicao = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTFTema = new javax.swing.JTextField();
        jTFTitulo = new javax.swing.JTextField();
        jDCDataInicio = new com.toedter.calendar.JDateChooser();
        jRBSim = new javax.swing.JRadioButton();
        jRBNao = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jDCDataFim = new com.toedter.calendar.JDateChooser();
        jDCDataVencimento = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jPDadosBancarios = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTFAgencia = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTFConta = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTFCarteira = new javax.swing.JTextField();
        jCBTipo = new javax.swing.JComboBox();

        setTitle("Gerenciar Edição");

        jPManterEdicao.setPreferredSize(new java.awt.Dimension(670, 260));

        jPEdicao.setBorder(javax.swing.BorderFactory.createTitledBorder("Edição"));

        jLabel1.setText("Tema");

        jLabel6.setText("Título");

        jTFTema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFTemaActionPerformed(evt);
            }
        });

        bGAgenda.add(jRBSim);
        jRBSim.setText("Sim");
        jRBSim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRBSimActionPerformed(evt);
            }
        });

        bGAgenda.add(jRBNao);
        jRBNao.setText("Não");

        jLabel2.setText("Data do Fim");

        jLabel3.setText("Data do Vencimento das Inscrições");

        jLabel4.setText("Data do Inicío");

        jLabel5.setText("Agenda Confirmada");

        javax.swing.GroupLayout jPEdicaoLayout = new javax.swing.GroupLayout(jPEdicao);
        jPEdicao.setLayout(jPEdicaoLayout);
        jPEdicaoLayout.setHorizontalGroup(
            jPEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPEdicaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDCDataInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDCDataFim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPEdicaoLayout.createSequentialGroup()
                        .addGroup(jPEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPEdicaoLayout.createSequentialGroup()
                                .addComponent(jRBSim)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRBNao))
                            .addComponent(jTFTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(jTFTema))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPEdicaoLayout.createSequentialGroup()
                .addGap(0, 77, Short.MAX_VALUE)
                .addGroup(jPEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jDCDataVencimento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(47, 47, 47))
        );
        jPEdicaoLayout.setVerticalGroup(
            jPEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPEdicaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFTema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGroup(jPEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTFTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDCDataInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDCDataFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRBSim)
                    .addComponent(jRBNao)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDCDataVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPDadosBancarios.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Bancários"));

        jLabel7.setText("Agência");

        jLabel8.setText("Conta");

        jTFConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFContaActionPerformed(evt);
            }
        });

        jLabel9.setText("Tipo");

        jLabel10.setText("Carteira");

        jCBTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Corrente", "Poupança" }));

        javax.swing.GroupLayout jPDadosBancariosLayout = new javax.swing.GroupLayout(jPDadosBancarios);
        jPDadosBancarios.setLayout(jPDadosBancariosLayout);
        jPDadosBancariosLayout.setHorizontalGroup(
            jPDadosBancariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPDadosBancariosLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPDadosBancariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPDadosBancariosLayout.createSequentialGroup()
                        .addGroup(jPDadosBancariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPDadosBancariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCBTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTFCarteira)))
                    .addGroup(jPDadosBancariosLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPDadosBancariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPDadosBancariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTFConta, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                            .addComponent(jTFAgencia))))
                .addGap(17, 17, 17))
        );
        jPDadosBancariosLayout.setVerticalGroup(
            jPDadosBancariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPDadosBancariosLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPDadosBancariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTFAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDadosBancariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTFConta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDadosBancariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jCBTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDadosBancariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTFCarteira, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPManterEdicaoLayout = new javax.swing.GroupLayout(jPManterEdicao);
        jPManterEdicao.setLayout(jPManterEdicaoLayout);
        jPManterEdicaoLayout.setHorizontalGroup(
            jPManterEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPManterEdicaoLayout.createSequentialGroup()
                .addComponent(jPEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPDadosBancarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPManterEdicaoLayout.setVerticalGroup(
            jPManterEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPManterEdicaoLayout.createSequentialGroup()
                .addGroup(jPManterEdicaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPDadosBancarios, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPEdicao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(jPManterEdicao, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRBSimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRBSimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRBSimActionPerformed

    private void jTFTemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFTemaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFTemaActionPerformed

    private void jTFContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFContaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTFContaActionPerformed

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
            java.util.logging.Logger.getLogger(FormManterEdicao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormManterEdicao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormManterEdicao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormManterEdicao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FormManterEdicao().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bGAgenda;
    private javax.swing.JComboBox jCBTipo;
    private com.toedter.calendar.JDateChooser jDCDataFim;
    private com.toedter.calendar.JDateChooser jDCDataInicio;
    private com.toedter.calendar.JDateChooser jDCDataVencimento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPDadosBancarios;
    private javax.swing.JPanel jPEdicao;
    private javax.swing.JPanel jPManterEdicao;
    private javax.swing.JRadioButton jRBNao;
    private javax.swing.JRadioButton jRBSim;
    private javax.swing.JTextField jTFAgencia;
    private javax.swing.JTextField jTFCarteira;
    private javax.swing.JTextField jTFConta;
    private javax.swing.JTextField jTFTema;
    private javax.swing.JTextField jTFTitulo;
    // End of variables declaration//GEN-END:variables
}
