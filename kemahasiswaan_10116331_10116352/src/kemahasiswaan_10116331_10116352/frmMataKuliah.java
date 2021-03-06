/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kemahasiswaan_10116331_10116352;

    import javax.swing.*;

    //fungsi Import yang digunakna untuk SQL
    import java.sql.*;

    //fungsi import yang digunaka untuk tanggal
    import java.text.SimpleDateFormat;
    import java.util.Date;
    import java.util.logging.Level;
    import java.util.logging.Logger;
/**
 *
 * @author my
 */
public class frmMataKuliah extends javax.swing.JFrame {

    /**
     * Creates new form frmMataKuliah
     */
    koneksi dbsetting;
    String driver, database, user, pass;
    Object tabel;
    
    public frmMataKuliah() {
        initComponents();
        
        dbsetting = new koneksi();
        driver = dbsetting.SettingPanel("DBDriver");
        database = dbsetting.SettingPanel("DBDatabase");
        user = dbsetting.SettingPanel("DBUsername");
        pass = dbsetting.SettingPanel("DBPassword");
        
        tableMahasiswa.setModel(tableModel);
        settableload();
        nonaktif_teks();
        buttonAwal();
    }

    private javax.swing.table.DefaultTableModel tableModel = getDefaultTableModel();
    
    private javax.swing.table.DefaultTableModel getDefaultTableModel() {
        
        //menambahkan judul header
        return new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String [] {"NO Mata Kuliah","Nama Mata Kuliah"}
        )
        //disable perubahan pada grid
        {
            boolean[] canEdit = new boolean[] {
                false, false, false, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex){
                return canEdit[columnIndex];
            }
        };
    }
    
    String data[] = new String[5];
            
    private void settableload() {
        String stat = "";
        try {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            
            Statement stt= kon.createStatement();
            String SQL = "select * from t_mata_kuliah";
            ResultSet res = stt.executeQuery(SQL);
            while (res.next()){
                data[0] = res.getString(1);
                data[1] = res.getString(2);
                tableModel.addRow(data);
            }
            res.close();
            stt.close();
            kon.close();
        } 
        catch (Exception ex) {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }
    
    public void membersihkan_teks() {
        txtNoMK.setText("");
        txtNamaMK.setText("");
    }
    
    public void nonaktif_teks() {
        txtNoMK.setEnabled(false);
        txtNamaMK.setEnabled(false);
    }
    
    public void aktif_teks() {
        txtNoMK.setEnabled(true);
        txtNamaMK.setEnabled(true);
    }
    
    public void buttonAwal() {
        btnTambah.setEnabled(true);
        btnSimpan.setEnabled(false);
        btnBatal.setEnabled(false);
        btnUbah.setEnabled(false);
        btnHapus.setEnabled(false);
        btnKeluar.setEnabled(true);
    }
    
    int row = 0;
    public void tampil_field(){
        row = tableMahasiswa.getSelectedRow();
        txtNoMK.setText(tableModel.getValueAt(row, 0).toString());
        txtNamaMK.setText(tableModel.getValueAt(row, 1).toString());
        aktif_teks();
        btnTambah.setEnabled(false);
        btnSimpan.setEnabled(false);
        btnBatal.setEnabled(true);
        btnUbah.setEnabled(true);
        btnHapus.setEnabled(true);
        btnKeluar.setEnabled(true);
    }
    
    private void tabel_mahasiswaMouseClicked(java.awt.event.MouseEvent evt) {                                             
        // TODO add your handling code here:
        if (evt.getClickCount()==1) {
            tampil_field();
        }
    }
    
    String ComboBox() {
        String selectedItemStr = null;
        Object selectedItem = cbxFilter.getSelectedItem();
            if (selectedItem != null)
            {
                selectedItemStr = selectedItem.toString();
            }
        return selectedItemStr;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnTampil = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbxFilter = new javax.swing.JComboBox<>();
        txtCariData = new javax.swing.JTextField();
        btnCariData = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableMahasiswa = new javax.swing.JTable();
        btnTambah = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();
        txtNoMK = new javax.swing.JTextField();
        txtNamaMK = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(24, 14, 14));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/student.png"))); // NOI18N
        jLabel1.setText("    FORM MATA KULIAH");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 150, 580, 90));

        btnTampil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/list.png"))); // NOI18N
        btnTampil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTampilActionPerformed(evt);
            }
        });
        getContentPane().add(btnTampil, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 400, 30, 30));

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(24, 14, 14));
        jLabel3.setText("Masukan Data");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 400, -1, -1));

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(24, 14, 14));
        jLabel2.setText("Pencarian Data Mata Kuliah");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 360, -1, -1));

        cbxFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "kd_mk", "nama_mk" }));
        cbxFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxFilterActionPerformed(evt);
            }
        });
        getContentPane().add(cbxFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 400, -1, -1));
        getContentPane().add(txtCariData, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 400, 145, -1));

        btnCariData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/musica-searcher.png"))); // NOI18N
        btnCariData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariDataActionPerformed(evt);
            }
        });
        getContentPane().add(btnCariData, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 400, 30, 30));

        tableMahasiswa.setModel(new javax.swing.table.DefaultTableModel(
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
        tableMahasiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMahasiswaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableMahasiswa);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 440, 700, 150));

        btnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add(1).png"))); // NOI18N
        btnTambah.setBorder(javax.swing.BorderFactory.createBevelBorder(0));
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });
        getContentPane().add(btnTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 300, -1, -1));

        btnUbah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit.png"))); // NOI18N
        btnUbah.setBorder(javax.swing.BorderFactory.createBevelBorder(0));
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });
        getContentPane().add(btnUbah, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 300, -1, -1));

        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/rubbish-bin.png"))); // NOI18N
        btnHapus.setBorder(javax.swing.BorderFactory.createBevelBorder(0));
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        getContentPane().add(btnHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 300, -1, -1));

        btnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save-button.png"))); // NOI18N
        btnSimpan.setBorder(javax.swing.BorderFactory.createBevelBorder(0));
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        getContentPane().add(btnSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 300, -1, -1));

        btnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete.png"))); // NOI18N
        btnBatal.setBorder(javax.swing.BorderFactory.createBevelBorder(0));
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });
        getContentPane().add(btnBatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 300, -1, -1));

        btnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/home.png"))); // NOI18N
        btnKeluar.setBorder(javax.swing.BorderFactory.createBevelBorder(0));
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });
        getContentPane().add(btnKeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 170, 50, 50));
        getContentPane().add(txtNoMK, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 300, 130, -1));

        txtNamaMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaMKActionPerformed(evt);
            }
        });
        getContentPane().add(txtNamaMK, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 300, 140, -1));

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(24, 14, 14));
        jLabel4.setText("Kode M.K");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 300, -1, -1));

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(24, 14, 14));
        jLabel5.setText("Nama M.K");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 300, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/simple-art-cubes-abstract.jpg"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -240, 1280, 1220));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFilterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxFilterActionPerformed

    private void btnCariDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariDataActionPerformed
        // TODO add your handling code here:
        tableModel.setRowCount(0);
        String SQL;
        //gunakan quary untuk mencari
        try {
            String Filter = (String)cbxFilter.getSelectedItem();
            System.out.println(txtCariData.getText());
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);

            Statement stt= kon.createStatement();
            if (Filter == "kd_mk"){
                SQL = "select * from t_mata_kuliah where "+Filter+" = "
                + "'%"+ txtCariData.getText()+"%'";
            } else {
                SQL = "select * from t_mata_kuliah where "+Filter+" like "
                + "'%"+ txtCariData.getText()+"%'";
            }
            ResultSet res = stt.executeQuery(SQL);

            while (res.next()){
                data[0] = res.getString(1);
                data[1] = res.getString(2);
                tableModel.addRow(data);
            }
            res.close();
            stt.close();
            kon.close();
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }//GEN-LAST:event_btnCariDataActionPerformed

    private void btnTampilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTampilActionPerformed
        // TODO add your handling code here:
        tableModel.setRowCount(0);
        settableload();
    }//GEN-LAST:event_btnTampilActionPerformed

    private void tableMahasiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMahasiswaMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount()==1) {
            tampil_field();
        }
    }//GEN-LAST:event_tableMahasiswaMouseClicked

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        // TODO add your handling code here
        String kd_mk = txtNoMK.getText();
        String nama_mk = txtNamaMK.getText();

        if ((kd_mk.isEmpty() | (nama_mk.isEmpty()))){
            JOptionPane.showMessageDialog(null, "Pilih data yang akan diubah");
            txtNoMK.requestFocus();
        } else {
            try {
                int dialogResult = JOptionPane.showConfirmDialog (null, 
                    "Ubah Data "+txtNamaMK.getText()+" ("+txtNoMK.getText()+")",
                    "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(dialogResult == JOptionPane.YES_OPTION){
                    Class.forName(driver);
                    Connection kon = DriverManager.getConnection(database, user, pass);

                    Statement stt= kon.createStatement();
                    String SQL = "UPDATE `t_mata_kuliah` set"
                    + "`kd_mk` = '"+kd_mk+"',"
                    + "`nama_mk` = '"+nama_mk+"'"
                    + "WHERE "
                    + "`kd_mk`='"+tableModel.getValueAt(row,0).toString()+"';";
                    stt.executeUpdate(SQL);
                    data[0] = kd_mk;
                    data[1] = nama_mk;
                    tableModel.removeRow(row);
                    tableModel.insertRow(row, data);
                    stt.close();
                    kon.close();
                    membersihkan_teks();
                    btnSimpan.setEnabled(false);
                    nonaktif_teks();
                }
            }
            catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }

    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
        membersihkan_teks();
        txtNoMK.requestFocus();
        btnTambah.setEnabled(false);
        btnSimpan.setEnabled(true);
        btnBatal.setEnabled(true);
        btnUbah.setEnabled(false);
        btnHapus.setEnabled(false);
        btnKeluar.setEnabled(false);
        aktif_teks();
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        try {
            int dialogResult = JOptionPane.showConfirmDialog (null, 
                "Hapus "+tableModel.getValueAt(row,1).toString()+" ("+tableModel.getValueAt(row,0).toString()+")",
                "Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(dialogResult == JOptionPane.YES_OPTION){
                Class.forName(driver);
                Connection kon = DriverManager.getConnection(database, user, pass);

                Statement stt= kon.createStatement();
                String SQL = "delete from t_mata_kuliah "
                + "where "
                + "kd_mk ='"+tableModel.getValueAt(row, 0).toString()+"'";
                stt.executeUpdate(SQL);
                tableModel.removeRow(row);
                stt.close();
                kon.close();
                membersihkan_teks();
            }
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        String data[] = new String[5];

        if ((txtNoMK.getText().isEmpty()) || (txtNamaMK.getText().isEmpty())){
            JOptionPane.showMessageDialog(null, "Data tidak boleh kosong, silahkan dilengkapi");
            txtNoMK.requestFocus();
        } else {
            try {
                int dialogResult = JOptionPane.showConfirmDialog (null, 
                    "Simpan Data "+txtNamaMK.getText()+" ("+txtNoMK.getText()+")",
                    "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(dialogResult == JOptionPane.YES_OPTION){
                    Class.forName(driver);
                    Connection kon = DriverManager.getConnection(database, user, pass);

                    Statement stt= kon.createStatement();
                    String SQL = "INSERT INTO t_mata_kuliah(kd_mk,"
                    + "nama_mk)"
                    + "VALUES "
                    + "( '"+txtNoMK.getText()+"',"
                    + " ' "+txtNamaMK.getText()+"')";

                    stt.executeUpdate(SQL);
                    data[0] = txtNoMK.getText();
                    data[1] = txtNamaMK.getText();
                    tableModel.insertRow(0, data);

                    stt.close();
                    kon.close();
                    membersihkan_teks();
                    btnTambah.setEnabled(true);
                    btnSimpan.setEnabled(false);
                    btnBatal.setEnabled(false);
                    btnUbah.setEnabled(true);
                    btnHapus.setEnabled(false);
                    btnKeluar.setEnabled(true);
                    nonaktif_teks();
                }

            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        buttonAwal();
        membersihkan_teks();
        nonaktif_teks();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKeluarActionPerformed
        // TODO add your handling code here:
        int dialogResult = JOptionPane.showConfirmDialog (null, "Apakah anda ingin keluar","Confirm",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(dialogResult == JOptionPane.YES_OPTION){
            frmUtama utm = new frmUtama();
            utm.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnKeluarActionPerformed

    private void txtNamaMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaMKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaMKActionPerformed

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
            java.util.logging.Logger.getLogger(frmMataKuliah.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMataKuliah.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMataKuliah.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMataKuliah.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMataKuliah().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnCariData;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnTampil;
    private javax.swing.JButton btnUbah;
    private javax.swing.JComboBox<String> cbxFilter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableMahasiswa;
    private javax.swing.JTextField txtCariData;
    private javax.swing.JTextField txtNamaMK;
    private javax.swing.JTextField txtNoMK;
    // End of variables declaration//GEN-END:variables
}
