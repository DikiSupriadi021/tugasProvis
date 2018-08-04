/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kemahasiswaan_10116331_10116352;
   
import javax.swing.*;

//fungsi Import yang digunakna untuk SQL
import java.sql.*;
import java.text.ParseException;

//fungsi import yang digunaka untuk tanggal
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author my
 */
public class frmMahasiswa extends javax.swing.JFrame {

    /**
     * Creates new form frmMahasiswa
     */
    koneksi dbsetting;
    String driver, database, user, pass;
    Object tabel;
    
    public frmMahasiswa() {
        initComponents();
        
        dbsetting = new koneksi();
        driver = dbsetting.SettingPanel("DBDriver");
        database = dbsetting.SettingPanel("DBDatabase");
        user = dbsetting.SettingPanel("DBUsername");
        pass = dbsetting.SettingPanel("DBPassword");
        
        tableMahasiswa.setModel(tableModel);
        Date date = new Date();
        txtTanggallahir.setDate(date);
        
        settableload();
        nonaktif_teks();
        buttonAwal();
        
        
    }

    private javax.swing.table.DefaultTableModel tableModel = getDefaultTableModel();
    
    private javax.swing.table.DefaultTableModel getDefaultTableModel() {
        
        //menambahkan judul header
        return new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String [] {"NIM", "Nama Mahasiswa", "Tempat Lahir", "Tanggal Lahir", "Alamat"}
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
        try {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            
            Statement stt= kon.createStatement();
            String SQL = "select * from t_mahasiswa";
            ResultSet res = stt.executeQuery(SQL);
            while (res.next()){
                data[0] = res.getString(1);
                data[1] = res.getString(2);
                data[2] = res.getString(3);
                data[3] = res.getString(4);
                data[4] = res.getString(5);
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
        txtNIM.setText("");
        txtNama.setText("");
        txtTempatlahir.setText("");
        txtAlamat.setText("");
    }
    
    public void nonaktif_teks() {
        txtNIM.setEnabled(false);
        txtNama.setEnabled(false);
        txtTanggallahir.setEnabled(false);
        txtTempatlahir.setEnabled(false);
        txtAlamat.setEnabled(false);
    }
    
    public void aktif_teks() {
        txtNIM.setEnabled(true);
        txtNama.setEnabled(true);
        txtTanggallahir.setEnabled(true);
        txtTempatlahir.setEnabled(true);
        txtAlamat.setEnabled(true);
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
    public void tampil_field() throws ParseException{
        String tampilan = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(tampilan);
        
        row = tableMahasiswa.getSelectedRow();
        txtNIM.setText(tableModel.getValueAt(row, 0).toString());
        txtNama.setText(tableModel.getValueAt(row, 1).toString());
        txtTempatlahir.setText(tableModel.getValueAt(row, 2).toString());
        Date tglLahirSet = format.parse(tableModel.getValueAt(row, 3).toString());
        txtTanggallahir.setDate(tglLahirSet);
        txtAlamat.setText(tableModel.getValueAt(row, 4).toString());
        aktif_teks();
        btnTambah.setEnabled(false);
        btnSimpan.setEnabled(false);
        btnBatal.setEnabled(true);
        btnUbah.setEnabled(true);
        btnHapus.setEnabled(true);
        btnKeluar.setEnabled(true);
    }
    
    private void tabel_mahasiswaMouseClicked(java.awt.event.MouseEvent evt) throws ParseException {                                             
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

        dateCheckIn = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCariData = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNIM = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTempatlahir = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAlamat = new javax.swing.JTextArea();
        btnCariData = new javax.swing.JButton();
        btnTampil = new javax.swing.JButton();
        cbxFilter = new javax.swing.JComboBox<>();
        txtTanggallahir = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        btnUbah = new javax.swing.JButton();
        btnTambah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableMahasiswa = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel2.setText("Pencarian Data Mahasiswa");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, -1, -1));

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel3.setText("Masukan Data");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 430, -1, -1));
        getContentPane().add(txtCariData, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 420, 145, -1));

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel4.setText("NIM");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 228, -1, -1));

        txtNIM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNIMActionPerformed(evt);
            }
        });
        txtNIM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNIMKeyTyped(evt);
            }
        });
        getContentPane().add(txtNIM, new org.netbeans.lib.awtextra.AbsoluteConstraints(188, 223, 164, -1));

        txtNama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNamaKeyTyped(evt);
            }
        });
        getContentPane().add(txtNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(188, 262, 164, -1));

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel5.setText("Nama");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 267, -1, -1));

        txtTempatlahir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTempatlahirKeyTyped(evt);
            }
        });
        getContentPane().add(txtTempatlahir, new org.netbeans.lib.awtextra.AbsoluteConstraints(188, 326, 164, -1));

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel6.setText("Tempat Lahir");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 331, -1, -1));

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel7.setText("Tanggal Lahir");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(438, 228, -1, -1));

        jLabel8.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel8.setText("Alamat");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(438, 262, -1, -1));

        txtAlamat.setColumns(20);
        txtAlamat.setRows(5);
        txtAlamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAlamatKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(txtAlamat);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(574, 262, 272, 58));

        btnCariData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/musica-searcher.png"))); // NOI18N
        btnCariData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariDataActionPerformed(evt);
            }
        });
        getContentPane().add(btnCariData, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 420, -1, -1));

        btnTampil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/list.png"))); // NOI18N
        btnTampil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTampilActionPerformed(evt);
            }
        });
        getContentPane().add(btnTampil, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 420, -1, -1));

        cbxFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "nim", "nama", "ttl", "tgl_lahir", "alamat" }));
        cbxFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxFilterActionPerformed(evt);
            }
        });
        getContentPane().add(cbxFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 420, -1, -1));
        getContentPane().add(txtTanggallahir, new org.netbeans.lib.awtextra.AbsoluteConstraints(574, 223, 162, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/student.png"))); // NOI18N
        jLabel1.setText("       FORM MAHASISWA");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(138, 0, 710, 77));

        btnUbah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit.png"))); // NOI18N
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });
        getContentPane().add(btnUbah, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, -1, -1));

        btnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add(1).png"))); // NOI18N
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });
        getContentPane().add(btnTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));

        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/rubbish-bin.png"))); // NOI18N
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        getContentPane().add(btnHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 160, -1, -1));

        btnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete.png"))); // NOI18N
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });
        getContentPane().add(btnBatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 160, -1, -1));

        btnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save-button.png"))); // NOI18N
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        getContentPane().add(btnSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 160, -1, -1));

        btnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/home.png"))); // NOI18N
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });
        getContentPane().add(btnKeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 60, 60));

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

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, 811, 128));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/simple-art-cubes-abstract.jpg"))); // NOI18N
        jLabel9.setText("jLabel9");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFilterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxFilterActionPerformed

    private void txtNIMKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNIMKeyTyped
        // TODO add your handling code here:
        if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
             JOptionPane.showMessageDialog(null,"Pada Kolom Jumlah Hanya Bisa Memasukan Karakter Angka");
        }
    }//GEN-LAST:event_txtNIMKeyTyped

    private void txtNamaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNamaKeyTyped
        // TODO add your handling code here:
        if(Character.isDigit(evt.getKeyChar())){
            evt.consume();
             JOptionPane.showMessageDialog(null,"Pada Kolom Jumlah Hanya Bisa Memasukan Karakter Huruf");
        }
    }//GEN-LAST:event_txtNamaKeyTyped

    private void txtTempatlahirKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTempatlahirKeyTyped
        // TODO add your handling code here:
        if(Character.isDigit(evt.getKeyChar())){
            evt.consume();
             JOptionPane.showMessageDialog(null,"Pada Kolom Jumlah Hanya Bisa Memasukan Karakter Huruf");
        }
    }//GEN-LAST:event_txtTempatlahirKeyTyped

    private void txtNIMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNIMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNIMActionPerformed

    private void tableMahasiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMahasiswaMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount()==1) {
            try {
                tampil_field();
            } catch (ParseException ex) {
                Logger.getLogger(frmMahasiswa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tableMahasiswaMouseClicked

    private void btnTampilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTampilActionPerformed
        // TODO add your handling code here:
        tableModel.setRowCount(0);
        settableload();
    }//GEN-LAST:event_btnTampilActionPerformed

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
            if (Filter == "nim"){
                SQL = "select * from t_mahasiswa where "+Filter+" = "
                + "'%"+ txtCariData.getText()+"%'";
            } else {
                SQL = "select * from t_mahasiswa where "+Filter+" like "
                + "'%"+ txtCariData.getText()+"%'";
            }
            ResultSet res = stt.executeQuery(SQL);

            while (res.next()){
                data[0] = res.getString(1);
                data[1] = res.getString(2);
                data[2] = res.getString(3);
                data[3] = res.getString(4);
                data[4] = res.getString(5);
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

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        buttonAwal();
        membersihkan_teks();
        nonaktif_teks();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        try {
            String data[] = new String[5];
            String tampilan = "yyyy-MM-dd";
            SimpleDateFormat format = new SimpleDateFormat(tampilan);
            String tglLahir = String.valueOf(format.format(txtTanggallahir.getDate()));

            if ((txtNIM.getText().isEmpty()) || (tglLahir.isEmpty()) || (txtNama.getText().isEmpty()) || (txtTempatlahir.getText().isEmpty())){
                JOptionPane.showMessageDialog(null, "Data tidak boleh kosong, silahkan dilengkapi");
                txtNIM.requestFocus();
            } else {
                try {
                    int dialogResult = JOptionPane.showConfirmDialog (null,
                        "Simpan Data "+txtNama.getText()+" ("+txtNIM.getText()+")",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(dialogResult == JOptionPane.YES_OPTION){
                        Class.forName(driver);
                        Connection kon = DriverManager.getConnection(database, user, pass);

                        Statement stt= kon.createStatement();
                        String SQL = "INSERT INTO t_mahasiswa("
                        + "nim,"
                        + "nama,"
                        + "ttl,"
                        + "tgl_lahir,"
                        + "alamat)"
                        + "VALUES "
                        + "( '"+txtNIM.getText()+"',"
                        + " ' "+txtNama.getText()+"',"
                        + " ' "+txtTempatlahir.getText()+"',"
                        + " ' "+tglLahir+"',"
                        + " ' "+txtAlamat.getText()+"')";

                        stt.executeUpdate(SQL);

                        data[0] = txtNIM.getText();
                        data[1] = txtNama.getText();
                        data[2] = txtTempatlahir.getText();
                        data[3] = tglLahir;
                        data[4] = txtAlamat.getText();
                        tableModel.insertRow(0, data);

                        stt.close();
                        kon.close();
                        membersihkan_teks();
                        buttonAwal();
                        nonaktif_teks();
                    }
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Pilih Tanggal yang akan dimasukan dengan benar");
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

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
                String SQL = "delete from t_mahasiswa "
                + "where "
                + "nim ='"+tableModel.getValueAt(row, 0).toString()+"'";
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

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        // TODO add your handling code here
        try {
            String tampilan = "yyyy-MM-dd";
            SimpleDateFormat format = new SimpleDateFormat(tampilan);

            String tglLahir = String.valueOf(format.format(txtTanggallahir.getDate()));

            String nim = txtNIM.getText();
            String nama = txtNama.getText();
            String tempat_lahir = txtTempatlahir.getText();
            String tgl_lahir = tglLahir;
            String alamat = txtAlamat.getText();

            if ((nim.isEmpty()) | (nama.isEmpty()) | (tempat_lahir.isEmpty()) | (tgl_lahir.isEmpty()) | (alamat.isEmpty())){
                JOptionPane.showMessageDialog(null, "Pilih data yang akan diubah");
                txtNIM.requestFocus();
            } else {
                try {
                    int dialogResult = JOptionPane.showConfirmDialog (null,
                        "Ubah Data "+tableModel.getValueAt(row,1).toString()+" ("+tableModel.getValueAt(row,0).toString()+")",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(dialogResult == JOptionPane.YES_OPTION){
                        Class.forName(driver);
                        Connection kon = DriverManager.getConnection(database, user, pass);

                        Statement stt= kon.createStatement();
                        String SQL = "UPDATE `t_mahasiswa` set"
                        + "`nim` = '"+nim+"',"
                        + "`nama` = '"+nama+"',"
                        + "`ttl` = '"+tempat_lahir+"',"
                        + "`tgl_lahir` = '"+tgl_lahir+"',"
                        + "`alamat` = '"+alamat+"'"
                        + "WHERE "
                        + " `nim`='"+tableModel.getValueAt(row,0).toString()+"';";
                        stt.executeUpdate(SQL);
                        data[0] = nim;
                        data[1] = nama;
                        data[2] = tempat_lahir;
                        data[3] = tgl_lahir;
                        data[4] = alamat;
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
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Pilih Tanggal yang akan diubah dengan benar");
        }

    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        // TODO add your handling code here:
        membersihkan_teks();
        aktif_teks();
        txtNIM.requestFocus();
        btnTambah.setEnabled(false);
        btnSimpan.setEnabled(true);
        btnBatal.setEnabled(true);
        btnUbah.setEnabled(false);
        btnHapus.setEnabled(false);
        btnKeluar.setEnabled(false);
    }//GEN-LAST:event_btnTambahActionPerformed

    private void txtAlamatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAlamatKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAlamatKeyTyped
                                                                                         

    private void btn_tampilActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        tableModel.setRowCount(0);
        settableload();
    }                                          

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        //menghapus seluruh isi data pada JTable
        tableModel.setRowCount(0);
        //gunakan quary untuk mencari
        try {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);

            Statement stt= kon.createStatement(); 
            String SQL = "select * from t_mahasiswa where nim= "
                    + txtCariData.getText();
            ResultSet res = stt.executeQuery(SQL);
            
            while (res.next()){
                data[0] = res.getString(1);
                data[1] = res.getString(2);
                data[2] = res.getString(3);
                data[3] = res.getString(4);
                data[4] = res.getString(5);
                tableModel.addRow(data);
            }
            res.close();
            stt.close();
            kon.close();
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage()); 
        }
    }
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
            java.util.logging.Logger.getLogger(frmMahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMahasiswa().setVisible(true); 
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
    private com.toedter.calendar.JDateChooser dateCheckIn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableMahasiswa;
    private javax.swing.JTextArea txtAlamat;
    private javax.swing.JTextField txtCariData;
    private javax.swing.JTextField txtNIM;
    private javax.swing.JTextField txtNama;
    private com.toedter.calendar.JDateChooser txtTanggallahir;
    private javax.swing.JTextField txtTempatlahir;
    // End of variables declaration//GEN-END:variables
}
