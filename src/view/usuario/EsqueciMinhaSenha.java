/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.usuario;

import controller.UsuarioController;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import model.Usuario;

/**
 *
 * @author victor
 */
public class EsqueciMinhaSenha extends javax.swing.JInternalFrame {

    /**
     * Creates new form EsqueciMinhaSenha
     */
    public EsqueciMinhaSenha() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextFieldEmail = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setClosable(true);

        jTextFieldEmail.setText("Email");
        jTextFieldEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEmailActionPerformed(evt);
            }
        });

        jButton1.setText("Enviar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(117, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(126, 126, 126))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(166, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            enviaSenha(jTextFieldEmail.getText());
            JOptionPane.showMessageDialog(null, "Sua dica de senha foi enviado ao seu email");
            this.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextFieldEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEmailActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JTextField jTextFieldEmail;
    // End of variables declaration//GEN-END:variables

    public void enviaSenha(String email) throws Exception{
       Usuario usuario = verificarUsuario(email);
       Properties properties = System.getProperties();
       properties.setProperty("mail.smtp.host", "smtp.gmail.com");
       properties.setProperty("mail.smtp.port", "587");
       properties.setProperty("mail.smtp.auth", "true");
       properties.setProperty("mail.smtp.starttls.enable", "true");
       properties.setProperty("mail.user", "gerenciadorfinanceiroufcg@gmail.com");
       properties.setProperty("mail.password", "gerenciador123");
       Session session = Session.getInstance(properties, new javax.mail.Authenticator() { 
           @Override protected PasswordAuthentication getPasswordAuthentication() { 
               return new PasswordAuthentication("gerenciadorfinanceiroufcg@gmail.com", "gerenciador123"); 
           } 
       }); 
       
       MimeMessage mensagem = new MimeMessage(session); 
       mensagem.setFrom(new InternetAddress("gerenciadorfinanceiroufcg@gmail.com"));
       mensagem.addRecipient(Message.RecipientType.TO, new InternetAddress(usuario.email));
       mensagem.setSubject("Recuperação de senha - Gerenciador Financeiro");
       mensagem.setText("A dica de sua senha é: " + usuario.dicaSenha + " (sua senha tem tamanho de " + usuario.senha.length() + " caracteres");
       Transport.send(mensagem);
    }

  

    private Usuario verificarUsuario(String email) throws Exception {
        UsuarioController usuarioController;
        usuarioController = new UsuarioController();
        Usuario usuario = usuarioController.buscarUsuario(email);
        System.out.print(usuario.email);
        return usuario;

    }

}
