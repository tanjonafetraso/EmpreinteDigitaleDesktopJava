/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fenetre;

import DAO.HibernateDAO;
import Model.Inscriptions;
import Utils.ImageUtils;
import ZKFinger.ZKFinger;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Eqima
 */
public class Inscription extends javax.swing.JFrame {

    Integer idpersonne = null;
    private BufferedImage image;
    ZKFinger zkFinger;

    /**
     * Creates new form Inscription
     */
    private void openImage(JFrame frame) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose an image file");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif", "bmp"));

        int userSelection = fileChooser.showOpenDialog(frame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                image = ImageUtils.resizeImage(ImageIO.read(selectedFile), 200, 200);
                btnPhoto.setIcon(new ImageIcon(image));
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error reading the image file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public Inscription() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        zkFinger = new ZKFinger(btnEmpreinte);
        zkFinger.openScanner(this);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("N°");
        model.addColumn("Nom");
        model.addColumn("Contact");
        model.addColumn("Adresse");
        model.addColumn("Poste");
        model.addColumn("Statue");
        HibernateDAO dao = new HibernateDAO();
        List<Inscriptions> list = dao.findAll(new Inscriptions());
        for (Inscriptions inscriptions : list) {
            Object[] rowData = new Object[6];
            rowData[0] = inscriptions.getId();
            rowData[1] = inscriptions.getNom();
            rowData[2] = inscriptions.getContact();
            rowData[3] = inscriptions.getAdresse();
            rowData[4] = inscriptions.getPoste();
            rowData[5] = inscriptions.getStatus();
            model.addRow(rowData);
        }

// Appliquer le modèle à la JTable
        jTable1.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnPhoto = new javax.swing.JButton();
        btnEmpreinte = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        tcontacte = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tnom = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tadresse = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tposte = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tstatus = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        tDateEmbauche = new org.jdatepicker.JDatePicker();
        tdateFinContrat = new org.jdatepicker.JDatePicker();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 470));
        setPreferredSize(new java.awt.Dimension(1160, 470));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnPhoto.setBackground(new java.awt.Color(0, 102, 102));
        btnPhoto.setForeground(new java.awt.Color(255, 255, 255));
        btnPhoto.setText("Photos 4 X4");
        btnPhoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhotoActionPerformed(evt);
            }
        });
        getContentPane().add(btnPhoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 30, 200, 200));

        btnEmpreinte.setBackground(new java.awt.Color(0, 102, 102));
        btnEmpreinte.setForeground(new java.awt.Color(255, 255, 255));
        btnEmpreinte.setText("emprinte");
        getContentPane().add(btnEmpreinte, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 250, 200, 200));

        jLabel1.setText("Nom:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 60, -1, -1));
        getContentPane().add(tcontacte, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 90, 160, -1));

        jLabel2.setText("Contact :");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 90, -1, -1));
        getContentPane().add(tnom, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, 160, -1));

        jLabel3.setText("Adresse:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 120, -1, -1));
        getContentPane().add(tadresse, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 120, 160, -1));

        jLabel4.setText("Poste:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 150, -1, -1));
        getContentPane().add(tposte, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 150, 160, -1));

        jLabel5.setText("Status:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 180, -1, -1));

        tstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " CDI", "CDD", "Retraite", "Viré" }));
        getContentPane().add(tstatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 180, 160, -1));

        jLabel6.setText("Date Embauche:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 60, -1, -1));
        getContentPane().add(tDateEmbauche, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 60, 240, 30));
        getContentPane().add(tdateFinContrat, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 100, 240, 30));

        jLabel8.setText("Fin d'embauche:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 100, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "N°", "Nom", "Contact", "Adresse", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 250, 560, 200));

        jButton1.setBackground(new java.awt.Color(0, 102, 102));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/delete.png"))); // NOI18N
        jButton1.setText("Supprimer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 150, -1, -1));

        jButton2.setBackground(new java.awt.Color(0, 102, 102));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/back.jpg"))); // NOI18N
        jButton2.setText("Retour");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, -1, -1));

        jButton4.setBackground(new java.awt.Color(0, 102, 102));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/xanxel.png"))); // NOI18N
        jButton4.setText("Annuler");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 200, 100, -1));

        jButton6.setBackground(new java.awt.Color(0, 102, 102));
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/save.jpg"))); // NOI18N
        jButton6.setText("Ajouter");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 150, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ident.jpg"))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 290, 470));

        jButton7.setBackground(new java.awt.Color(0, 102, 102));
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/save.jpg"))); // NOI18N
        jButton7.setText("Modifier");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 150, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPhotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhotoActionPerformed
        this.openImage(this);          // TODO add your handling code here:
    }//GEN-LAST:event_btnPhotoActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            if (tnom.getText().equals("") || tadresse.getText().equals("") || tcontacte.getText().equals("") || tposte.getText().equals("") || tDateEmbauche.getModel().getValue() == null || tdateFinContrat.getModel().getValue() == null) {
                JOptionPane.showMessageDialog(this, "Remplir les champs");
            } else {

                String nom = tnom.getText();
                String contact = tcontacte.getText();
                String adresse = tadresse.getText();
                String post = tposte.getText();
                String status = (String) tstatus.getSelectedItem();
                Calendar calendar = (Calendar) tDateEmbauche.getModel().getValue();
                Date datembauche = calendar.getTime();
                calendar = (Calendar) tdateFinContrat.getModel().getValue();
                Date fincontrat = calendar.getTime();
                Inscriptions ins = new Inscriptions(ImageUtils.convertImageToByteArray(image), nom, contact, adresse, post, null, status, datembauche, fincontrat);
                zkFinger.save(ins);
                HibernateDAO dao = new HibernateDAO();
                List<Inscriptions> list = dao.findAll(new Inscriptions());
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("N°");
                model.addColumn("Nom");
                model.addColumn("Contact");
                model.addColumn("Adresse");
                model.addColumn("Poste");
                model.addColumn("Statue");

// Ajout des données au modèle
                for (Inscriptions inscriptions : list) {
                    Object[] rowData = new Object[6];
                    rowData[0] = inscriptions.getId();
                    rowData[1] = inscriptions.getNom();
                    rowData[2] = inscriptions.getContact();
                    rowData[3] = inscriptions.getAdresse();
                    rowData[4] = inscriptions.getPoste();
                    rowData[5] = inscriptions.getStatus();
                    model.addRow(rowData);
                }

// Appliquer le modèle à la JTable
                jTable1.setModel(model);

                JOptionPane.showMessageDialog(this, "OK");
                tnom.setText("");        // TODO add your handling code here:
                tposte.setText("");        // TODO add your handling code here:
                tadresse.setText("");        // TODO add your handling code here:
                tcontacte.setText("");
                tDateEmbauche.getModel().setValue(null);// TODO add your handling code here:
                tdateFinContrat.getModel().setValue(null);
                btnEmpreinte.setIcon(null);
                btnPhoto.setIcon(null);
            }
        } catch (IOException ex) {
            Logger.getLogger(Inscription.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "ERREUR");
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        zkFinger.FreeSensor();
        this.setVisible(false);
        Menu m = new Menu();
        m.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        try {
            idpersonne = (int) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
            String nom = (String) jTable1.getValueAt(jTable1.getSelectedRow(), 1);
            String adresse = (String) jTable1.getValueAt(jTable1.getSelectedRow(), 3);
            String Contacte = (String) jTable1.getValueAt(jTable1.getSelectedRow(), 2);
            String poste = (String) jTable1.getValueAt(jTable1.getSelectedRow(), 4);
            tnom.setText(nom);
            tadresse.setText(adresse);
            tcontacte.setText(Contacte);// TODO add your handling code here:
            tposte.setText(poste);
            HibernateDAO dao = new HibernateDAO();
            Inscriptions ins = (Inscriptions) dao.findAllByName(new Inscriptions(), tnom.getText());
            btnPhoto.setIcon(new ImageIcon(ImageUtils.convertByteArrayToImage(ins.getPhoto())));// TODO add your handling code here:

        } catch (IOException ex) {
            Logger.getLogger(Inscription.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (idpersonne == null) {
            JOptionPane.showMessageDialog(this, "Selectionner sur le tableau !");
        } else {
            HibernateDAO dao = new HibernateDAO();
            Inscriptions ins = (Inscriptions) dao.findAllByName(new Inscriptions(), tnom.getText());
            int a = dao.delete(ins);
            if (a > 0) {
                JOptionPane.showMessageDialog(this, "supprimer !");
                tnom.setText("");        // TODO add your handling code here:
                tposte.setText("");        // TODO add your handling code here:
                tadresse.setText("");        // TODO add your handling code here:
                tcontacte.setText("");
                tDateEmbauche.getModel().setValue(null);// TODO add your handling code here:
                tdateFinContrat.getModel().setValue(null);
                btnPhoto.setIcon(null);
                List<Inscriptions> list = dao.findAll(new Inscriptions());
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("N°");
                model.addColumn("Nom");
                model.addColumn("Contact");
                model.addColumn("Adresse");
                model.addColumn("Poste");
                model.addColumn("Statue");

// Ajout des données au modèle
                for (Inscriptions inscriptions : list) {
                    Object[] rowData = new Object[6];
                    rowData[0] = inscriptions.getId();
                    rowData[1] = inscriptions.getNom();
                    rowData[2] = inscriptions.getContact();
                    rowData[3] = inscriptions.getAdresse();
                    rowData[4] = inscriptions.getPoste();
                    rowData[5] = inscriptions.getStatus();
                    model.addRow(rowData);
                }

// Appliquer le modèle à la JTable
                jTable1.setModel(model);
            } else {
                JOptionPane.showMessageDialog(this, "nom inconnu !");
            }
            idpersonne = null;
        }
// TODO add your handling code here:;

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        HibernateDAO dao = new HibernateDAO();
        if (idpersonne == null) {
            JOptionPane.showMessageDialog(this, "Selectionner sur le tableau !");
        } else {

            Inscriptions ins = (Inscriptions) dao.findAllByID(new Inscriptions(), idpersonne);
            ins.setNom(tnom.getText());
            ins.setPoste(tposte.getText());
            ins.setAdresse(tadresse.getText());
            ins.setContact(tcontacte.getText());
            ins.setStatus((String) tstatus.getSelectedItem());
            Calendar calendar = (Calendar) tdateFinContrat.getModel().getValue();
            Date fincontrat = calendar.getTime();
            ins.setFinContrat(fincontrat);
            int a = dao.update(ins);
            if (a > 0) {
                JOptionPane.showMessageDialog(this, "modifier !");
                tnom.setText("");        // TODO add your handling code here:
                tposte.setText("");        // TODO add your handling code here:
                tadresse.setText("");        // TODO add your handling code here:
                tcontacte.setText("");
                tDateEmbauche.getModel().setValue(null);// TODO add your handling code here:
                tdateFinContrat.getModel().setValue(null);
                btnPhoto.setIcon(null);
                List<Inscriptions> list = dao.findAll(new Inscriptions());
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("N°");
                model.addColumn("Nom");
                model.addColumn("Contact");
                model.addColumn("Adresse");
                model.addColumn("Poste");
                model.addColumn("Statue");

// Ajout des données au modèle
                for (Inscriptions inscriptions : list) {
                    Object[] rowData = new Object[6];
                    rowData[0] = inscriptions.getId();
                    rowData[1] = inscriptions.getNom();
                    rowData[2] = inscriptions.getContact();
                    rowData[3] = inscriptions.getAdresse();
                    rowData[4] = inscriptions.getPoste();
                    rowData[5] = inscriptions.getStatus();
                    model.addRow(rowData);
                }

// Appliquer le modèle à la JTable
                jTable1.setModel(model);
            } else {
                JOptionPane.showMessageDialog(this, "nom inconnu !");
            }
            idpersonne = null;
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        tnom.setText("");        // TODO add your handling code here:
        tposte.setText("");        // TODO add your handling code here:
        tadresse.setText("");        // TODO add your handling code here:
        tcontacte.setText("");
        tDateEmbauche.getModel().setValue(null);// TODO add your handling code here:
        tdateFinContrat.getModel().setValue(null);
        btnPhoto.setIcon(null);// TODO add your handling code here:
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(Inscription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inscription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inscription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inscription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inscription().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEmpreinte;
    private javax.swing.JButton btnPhoto;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private org.jdatepicker.JDatePicker tDateEmbauche;
    private javax.swing.JTextField tadresse;
    private javax.swing.JTextField tcontacte;
    private org.jdatepicker.JDatePicker tdateFinContrat;
    private javax.swing.JTextField tnom;
    private javax.swing.JTextField tposte;
    private javax.swing.JComboBox<String> tstatus;
    // End of variables declaration//GEN-END:variables
}
