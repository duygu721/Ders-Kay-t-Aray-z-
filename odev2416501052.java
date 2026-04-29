/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.awt.FlowLayout;
/**
 *
 * @author PC
 */
public class NewJFrame2 extends javax.swing.JFrame {
    DefaultTableModel model;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(NewJFrame2.class.getName());

    /**
     * Creates new form NewJFrame2
     */
    public NewJFrame2() {
        initComponents();
        
        setTitle("Yaz Okulu Ders Kayıt");
        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout()); // Elemanları yan yana ve alt alta dizer

        JPanel panel1 = new JPanel();
        
        String[] dersler = {"Fizik 1", "Fizik 2", "Mat 1", "Mat 2", "Kimya"};
        JComboBox<String> comboDers = new JComboBox<>(dersler);
        
        String[] bolumler = {"Bilgisayar", "Makine", "İnşaat", "Elektrik"};
        JComboBox<String> comboBolum = new JComboBox<>(bolumler);
        
        JButton btnKaydet = new JButton("Kaydet");
        
        panel1.add(new JLabel("Ders:"));
        panel1.add(comboDers);
        panel1.add(new JLabel("Bölüm:"));
        panel1.add(comboBolum);
        panel1.add(btnKaydet);

        JPanel panel2 = new JPanel();
        
        String[] basliklar = {"Ders Adı", "Bölüm"};
        model = new DefaultTableModel(basliklar, 0); // Tabloyu 0 satırla başlatır
        JTable tablo = new JTable(model);
        JScrollPane sp = new JScrollPane(tablo);
        
        JButton btnSil = new JButton("Sil");
        JButton btnOnayla = new JButton("Onayla");
        
        panel2.add(sp);
        panel2.add(btnSil);
        panel2.add(btnOnayla);

        add(panel1);
        add(panel2);

        btnKaydet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String secilenDers = comboDers.getSelectedItem().toString();
                String secilenBolum = comboBolum.getSelectedItem().toString();

                if (model.getRowCount() >= 4) {
                    JOptionPane.showMessageDialog(null, "En fazla 4 ders seçebilirsiniz!");
                    return;
                }

                for (int i = 0; i < model.getRowCount(); i++) {
                    if (model.getValueAt(i, 0).toString().equals(secilenDers)) {
                        JOptionPane.showMessageDialog(null, "Bu dersi zaten seçtiniz!");
                        return;
                    }
                }

                int soru = JOptionPane.showConfirmDialog(null, "Dersi eklemek istiyor musunuz?", "Onay", JOptionPane.YES_NO_OPTION);
                if (soru == JOptionPane.YES_OPTION) {
                    model.addRow(new Object[]{secilenDers, secilenBolum});
                }
            }
        });

        btnSil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                int satir = tablo.getSelectedRow();
                if (satir == -1) {
                    JOptionPane.showMessageDialog(null, "Lütfen silmek için tablodan bir satır seçin!");
                } else {
                    int soru = JOptionPane.showConfirmDialog(null, "Seçilen ders silinsin mi?", "Silme Onayı", JOptionPane.YES_NO_OPTION);
                    if (soru == JOptionPane.YES_OPTION) {
                        model.removeRow(satir);
                    }
                }
            }
        });

        btnOnayla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (model.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Listede ders yok!");
                    return;
                }
                
                try {
                    FileWriter fw = new FileWriter("dersler.txt");
                    PrintWriter pw = new PrintWriter(fw);
                    
                    for (int i = 0; i < model.getRowCount(); i++) {
                        pw.println("Ders: " + model.getValueAt(i, 0) + " - Bölüm: " + model.getValueAt(i, 1));
                    }
                    
                    pw.close();
                    JOptionPane.showMessageDialog(null, "Dersler dersler.txt dosyasına başarıyla kaydedildi!");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Dosya yazılırken hata oluştu!");
                }
                System.out.println("Dosya şuraya kaydedildi: " + new java.io.File("dersler.txt").getAbsolutePath());
            }
        });
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new NewJFrame2().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
