/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kemahasiswaan_10116331_10116352;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author 
 */
public class frmSimulasiNilai extends javax.swing.JFrame {

    /**
     * Creates new form frmSimulasiNilai
     */
    koneksi dbsetting;
    String driver, database, user, pass;
    Object tabel;
    String nimMHS[] = new String[20];
    String kdMK[] = new String[20];
    String data[] = new String[20];
    int banyakData = 0;
    double presAbsen,presTugas,presUTS,presUAS;
    
    public frmSimulasiNilai() {
        dbsetting = new koneksi();
        driver = dbsetting.SettingPanel("DBDriver");
        database = dbsetting.SettingPanel("DBDatabase");
        user = dbsetting.SettingPanel("DBUsername");
        pass = dbsetting.SettingPanel("DBPassword");
        
        initComponents();
        
        getDataCbx();
        tableNilai.setModel(tableModel);
        settableload();
        nonaktif_teks();
        buttonAwal();
    }

    private void getDataCbx() {
        try {
            int i;
            Class.forName(driver);
            Connection kon  = DriverManager.getConnection(database, user, pass);
            String SQLMhs   = "SELECT * FROM t_mahasiswa";
            String SQLMK    = "SELECT * FROM t_mata_kuliah";

            Statement stt= kon.createStatement();
            Statement sttMK= kon.createStatement();
            
            ResultSet resMhs = stt.executeQuery(SQLMhs);
            ResultSet resMK  = sttMK.executeQuery(SQLMK);
            
            i=0;
            while (resMK.next()) {   
                i++;
                cbxMK.addItem(i+". "+resMK.getString("nama_mk"));
                kdMK[i] = resMK.getString("kd_mk");
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    private javax.swing.table.DefaultTableModel tableModel = getDefaultTableModel();
    
    private javax.swing.table.DefaultTableModel getDefaultTableModel() {
        
        //menambahkan judul header
        return new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String [] {"Kd Nilai","Nama MK","Pres Absen","Pres Tugas","Pres UTS","Pres UAS"
                        ,"Absensi","Tgs 1","Tgs 2","Tgs 3","UTS","UAS"
                        ,"Nilai Absen","Nilai Tugas","Nilai UTS","Nilai UAS","Niali Akhir","Index"
                        ,"Keterangan"}
        )
        //disable perubahan pada grid
        {
            boolean[] canEdit = new boolean[] {
                false, false, false, false, false,
                false, false, false, false, false,
                false, false, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex){
                return canEdit[columnIndex];
            }
        };
    }
    
    private void settableload() {
        try {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            
            Statement stt= kon.createStatement();
            String SQL = "select * from t_simulasi_nilai "
                    + "INNER JOIN t_mata_kuliah using (kd_mk) ";
            ResultSet res = stt.executeQuery(SQL);
            
            
            while (res.next()){
                data[0] = res.getString("kd_nilai");
                data[1] = res.getString("nama_mk");
                data[2] = res.getString("presAbsen");
                data[3] = res.getString("presTugas");
                data[4] = res.getString("presUTS");
                data[5] = res.getString("presUAS");
                data[6] = res.getString("absensi");
                data[7] = res.getString("tgs_1");
                data[8] = res.getString("tgs_2");
                data[9] = res.getString("tgs_3");
                data[10] = res.getString("UTS");
                data[11] = res.getString("UAS");
                data[12] = res.getString("nilai_absen");
                data[13] = res.getString("nilai_tugas");
                data[14] = res.getString("nilai_UTS");
                data[15] = res.getString("nilai_UAS");
                data[16] = res.getString("nilai_akhir");
                data[17] = res.getString("index");
                data[18] = res.getString("ket");
                banyakData++;
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
        txtKehadiran.setText("");
        txtPresAbsensi.setText("");
        txtPresTugas.setText("");
        txtPresUTS.setText("");
        txtPresUAS.setText("");
        txtKehadiran.setText("");
        txtTgs1.setText("");
        txtTgs2.setText("");
        txtTgs3.setText("");
        txtKDMK.setText("");
        txtUTS.setText("");
        txtUAS.setText("");
        
    }
    
    public void nonaktif_teks() {
        txtKehadiran.setEnabled(false);
        txtPresAbsensi.setEnabled(false);
        txtPresTugas.setEnabled(false);
        txtPresUTS.setEnabled(false);
        txtPresUAS.setEnabled(false);
        txtKehadiran.setEnabled(false);
        txtTgs1.setEnabled(false);
        txtTgs2.setEnabled(false);
        txtTgs3.setEnabled(false);
        txtKDMK.setEnabled(false);
        txtUTS.setEnabled(false);
        txtUAS.setEnabled(false);
        cbxMK.setEnabled(false);
    }
    
    public void aktif_teks() {
        txtKehadiran.setEnabled(true);
        txtPresAbsensi.setEnabled(true);
        txtPresTugas.setEnabled(true);
        txtPresUTS.setEnabled(true);
        txtPresUAS.setEnabled(true);
        txtKehadiran.setEnabled(true);
        txtTgs1.setEnabled(true);
        txtTgs2.setEnabled(true);
        txtTgs3.setEnabled(true);
        txtKDMK.setEnabled(false);
        txtUTS.setEnabled(true);
        txtUAS.setEnabled(true);
        cbxMK.setEnabled(true);
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
        try {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            
            row = tableNilai.getSelectedRow();
            
            Statement stt= kon.createStatement();
            String SQL = "SELECT * FROM `t_simulasi_nilai` WHERE `kd_nilai` = "+
                    tableModel.getValueAt(row, 0).toString();
            ResultSet res = stt.executeQuery(SQL);
            while (res.next()){
                data[0] = res.getString("kd_mk");
            }
            
            txtKDMK.setText(data[0]);
            
            txtPresAbsensi.setText(tableModel.getValueAt(row, 2).toString());
            txtKehadiran.setText(tableModel.getValueAt(row, 6).toString());
            
            txtPresUTS.setText(tableModel.getValueAt(row, 4).toString());
            txtUTS.setText(tableModel.getValueAt(row, 10).toString());
            
            txtPresUAS.setText(tableModel.getValueAt(row, 5).toString());
            txtUAS.setText(tableModel.getValueAt(row, 8).toString());
            
            txtPresTugas.setText(tableModel.getValueAt(row, 4).toString());
            txtTgs1.setText(tableModel.getValueAt(row, 7).toString());
            txtTgs2.setText(tableModel.getValueAt(row, 8).toString());
            txtTgs3.setText(tableModel.getValueAt(row, 9).toString());
            
            

            aktif_teks();
            btnTambah.setEnabled(false);
            btnSimpan.setEnabled(false);
            btnBatal.setEnabled(true);
            btnUbah.setEnabled(true);
            btnHapus.setEnabled(true);
            btnKeluar.setEnabled(true);
            
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }
    
    public boolean rangNilai(double x) {
        boolean stat;
        if (x > 100 | x < 0) {
            stat = true;
        } else {
            stat = false;
        }
        
        return stat;
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
        cbxMK = new javax.swing.JComboBox<>();
        txtKDMK = new javax.swing.JTextField();
        txtKehadiran = new javax.swing.JTextField();
        txtTgs3 = new javax.swing.JTextField();
        txtTgs2 = new javax.swing.JTextField();
        btnUbah = new javax.swing.JButton();
        btnTambah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();
        txtPresUTS = new javax.swing.JTextField();
        txtPresAbsensi = new javax.swing.JTextField();
        txtPresTugas = new javax.swing.JTextField();
        txtPresUAS = new javax.swing.JTextField();
        txtTgs1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtUTS = new javax.swing.JTextField();
        txtUAS = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableNilai = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/student.png"))); // NOI18N
        jLabel1.setText("          FORM SIMULASI NILAI MAHASISWA");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(128, 26, 1030, 77));

        cbxMK.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Nama MK -" }));
        cbxMK.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxMKItemStateChanged(evt);
            }
        });
        cbxMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxMKActionPerformed(evt);
            }
        });
        getContentPane().add(cbxMK, new org.netbeans.lib.awtextra.AbsoluteConstraints(197, 121, 172, -1));

        txtKDMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKDMKActionPerformed(evt);
            }
        });
        getContentPane().add(txtKDMK, new org.netbeans.lib.awtextra.AbsoluteConstraints(199, 166, 145, -1));

        txtKehadiran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKehadiranActionPerformed(evt);
            }
        });
        txtKehadiran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtKehadiranKeyTyped(evt);
            }
        });
        getContentPane().add(txtKehadiran, new org.netbeans.lib.awtextra.AbsoluteConstraints(359, 209, 48, -1));

        txtTgs3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTgs3ActionPerformed(evt);
            }
        });
        txtTgs3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTgs3KeyTyped(evt);
            }
        });
        getContentPane().add(txtTgs3, new org.netbeans.lib.awtextra.AbsoluteConstraints(916, 265, 48, -1));

        txtTgs2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTgs2KeyTyped(evt);
            }
        });
        getContentPane().add(txtTgs2, new org.netbeans.lib.awtextra.AbsoluteConstraints(916, 220, 48, -1));

        btnUbah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit.png"))); // NOI18N
        btnUbah.setToolTipText("");
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });
        getContentPane().add(btnUbah, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 410, -1, -1));

        btnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add(1).png"))); // NOI18N
        btnTambah.setToolTipText("");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });
        getContentPane().add(btnTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 410, -1, -1));

        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/rubbish-bin.png"))); // NOI18N
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        getContentPane().add(btnHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 410, -1, -1));

        btnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save-button.png"))); // NOI18N
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        getContentPane().add(btnSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 410, -1, -1));

        btnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete.png"))); // NOI18N
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });
        getContentPane().add(btnBatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 410, -1, -1));

        btnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/home.png"))); // NOI18N
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });
        getContentPane().add(btnKeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 60, 60));

        txtPresUTS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPresUTSKeyTyped(evt);
            }
        });
        getContentPane().add(txtPresUTS, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 298, 48, -1));

        txtPresAbsensi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPresAbsensiActionPerformed(evt);
            }
        });
        txtPresAbsensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPresAbsensiKeyTyped(evt);
            }
        });
        getContentPane().add(txtPresAbsensi, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 209, 48, -1));

        txtPresTugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPresTugasKeyTyped(evt);
            }
        });
        getContentPane().add(txtPresTugas, new org.netbeans.lib.awtextra.AbsoluteConstraints(746, 166, 48, -1));

        txtPresUAS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPresUASKeyTyped(evt);
            }
        });
        getContentPane().add(txtPresUAS, new org.netbeans.lib.awtextra.AbsoluteConstraints(205, 343, 48, -1));

        txtTgs1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTgs1KeyTyped(evt);
            }
        });
        getContentPane().add(txtTgs1, new org.netbeans.lib.awtextra.AbsoluteConstraints(916, 166, 48, -1));

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel2.setText("Kode MK");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 174, -1, -1));

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel3.setText("Nama Mata Kuliah");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 126, -1, -1));

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel4.setText("Presentase Tugas");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(604, 171, -1, -1));

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel5.setText("Presentase Absen");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 214, -1, -1));

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel6.setText("Presentase UTS");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 298, -1, -1));

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel7.setText("Presentase UAS");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 362, -1, -1));

        jLabel8.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel8.setText("UTS");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(271, 303, -1, -1));

        jLabel9.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel9.setText("UAS");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(271, 348, -1, -1));

        jLabel10.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel10.setText("Tugas 2");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(832, 225, -1, -1));

        jLabel11.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel11.setText("Tugas 3");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(832, 270, -1, -1));

        jLabel12.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel12.setText("Kehadiran");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(271, 214, -1, -1));

        jLabel13.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel13.setText("Tugas 1");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(832, 171, -1, -1));

        txtUTS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUTSKeyTyped(evt);
            }
        });
        getContentPane().add(txtUTS, new org.netbeans.lib.awtextra.AbsoluteConstraints(316, 298, 48, -1));

        txtUAS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUASKeyTyped(evt);
            }
        });
        getContentPane().add(txtUAS, new org.netbeans.lib.awtextra.AbsoluteConstraints(316, 343, 48, -1));

        tableNilai.setModel(new javax.swing.table.DefaultTableModel(
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
        tableNilai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableNilaiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableNilai);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, 1157, 206));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/simple-art-cubes-abstract.jpg"))); // NOI18N
        jLabel14.setText("jLabel14");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxMKItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxMKItemStateChanged
        // TODO add your handling code here:
        txtKDMK.setText(kdMK[cbxMK.getSelectedIndex()]);
    }//GEN-LAST:event_cbxMKItemStateChanged

    private void cbxMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxMKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxMKActionPerformed

    private void txtKehadiranKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKehadiranKeyTyped
        // TODO add your handling code here:
        if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
        }
    }//GEN-LAST:event_txtKehadiranKeyTyped

    private void txtTgs3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTgs3KeyTyped
        // TODO add your handling code here:
        if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
        }
    }//GEN-LAST:event_txtTgs3KeyTyped

    private void txtTgs1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTgs1KeyTyped
        // TODO add your handling code here:
        if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
             JOptionPane.showMessageDialog(null,"Pada Kolom Jumlah Hanya Bisa Memasukan Karakter Angka");
        }
    }//GEN-LAST:event_txtTgs1KeyTyped

    private void txtUASKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUASKeyTyped
        // TODO add your handling code here:
        if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
             JOptionPane.showMessageDialog(null,"Pada Kolom Jumlah Hanya Bisa Memasukan Karakter Angka");
        }
    }//GEN-LAST:event_txtUASKeyTyped

    private void txtTgs2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTgs2KeyTyped
        // TODO add your handling code here:
        if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
             JOptionPane.showMessageDialog(null,"Pada Kolom Jumlah Hanya Bisa Memasukan Karakter Angka");
        }
    }//GEN-LAST:event_txtTgs2KeyTyped

    private void tableNilaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNilaiMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount()==1) {
            tampil_field();
        }
    }//GEN-LAST:event_tableNilaiMouseClicked

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        // TODO add your handling code here
        String tampilan = "yyyy";
        SimpleDateFormat format = new SimpleDateFormat(tampilan);

        if ((
            (txtKehadiran.getText().isEmpty()) ||
            (txtPresAbsensi.getText().isEmpty()) ||
            (txtPresTugas.getText().isEmpty()) ||
            (txtPresUTS.getText().isEmpty()) ||
            (txtPresUAS.getText().isEmpty()) ||
            (txtTgs1.getText().isEmpty()) ||
            (txtTgs2.getText().isEmpty()) ||
            (txtTgs3.getText().isEmpty()) ||
            (txtKDMK.getText().isEmpty()) ||
            (txtUTS.getText().isEmpty()) ||
            (txtKDMK.getText().isEmpty()) ||
            (txtUAS.getText().isEmpty()) ||
            (txtUAS.getText().isEmpty()) )){
            JOptionPane.showMessageDialog(null, "Data tidak boleh kosong, silahkan dilengkapi");
            txtPresAbsensi.requestFocus();
        } else {
            try {
                String Kehadiran = txtKehadiran.getText();
                String Tgs1 = txtTgs1.getText();
                String Tgs2 = txtTgs2.getText();
                String Tgs3 = txtTgs3.getText();
                String KDMK = txtKDMK.getText();
                String UTS = txtUTS.getText();
                String UAS = txtUAS.getText();
                String nama = "";

                presAbsen = Double.parseDouble(txtPresAbsensi.getText());
                presTugas = Double.parseDouble(txtPresTugas.getText());
                presUTS = Double.parseDouble(txtPresUTS.getText());
                presUAS = Double.parseDouble(txtPresUAS.getText());
                
                Class.forName(driver);
                Connection kon = DriverManager.getConnection(database, user, pass);

                double nilaiAbsen, nilaiTugas, nilaiUTS, nilaiUAS = 0;
                double nilaiAkhir = 0;
                char index;
                String ket;
                

                Double nKehadiran = Double.parseDouble(txtKehadiran.getText());
                Double nTgs1 = Double.parseDouble(txtTgs3.getText());
                Double nTgs2 = Double.parseDouble(txtTgs1.getText());
                Double nTgs3 = Double.parseDouble(txtTgs3.getText());
                Double nUTS  = Double.parseDouble(txtUAS.getText());
                Double nUAS  = Double.parseDouble(txtTgs2.getText());
                
                Double totalPres = presAbsen + presTugas + presUAS + presUTS;

                if (nKehadiran > 14 | nKehadiran < 0) {
                    JOptionPane.showMessageDialog(null, "Kehadiran harus di rang 0 -14");
                } else if (rangNilai(nTgs1) | rangNilai(nTgs2) | rangNilai(nTgs3) | rangNilai(nUTS) | rangNilai(nUAS)) {
                    JOptionPane.showMessageDialog(null, "Nilai harus di rang 0 - 100");
                } else if (rangNilai(presAbsen) | rangNilai(presTugas) | rangNilai(presUAS) | rangNilai(presUTS)){
                    JOptionPane.showMessageDialog(null, "Presentase harus di rang 0% - 100%");
                } else if (totalPres != 100){
                    JOptionPane.showMessageDialog(null, "Total Presentase harus 100%");
                } else {
                    nilaiAbsen = (((nKehadiran/14)*100*presAbsen)/100);
                    nilaiTugas = ((nTgs1+nTgs2+nTgs3))/3*(presTugas/100);
                    nilaiUTS = (nUTS*presUTS/100);
                    nilaiUAS = (nUAS*presUAS/100);

                    nilaiAkhir = nilaiAbsen + nilaiTugas + nilaiUTS + nilaiUAS;

                    //cari index dan keterangan
                    if ((nilaiAkhir >= 80 && nilaiAkhir <= 100)) {
                        index = 'A';
                        ket = "Lulus";
                    } else if ((nilaiAkhir >= 68 && nilaiAkhir <= 79)) {
                        index = 'B';
                        ket = "Lulus";
                    } else if ((nilaiAkhir >= 56 && nilaiAkhir <= 67)) {
                        index = 'C';
                        ket = "Lulus";
                    } else if ((nilaiAkhir >= 45 && nilaiAkhir <= 55)) {
                        index = 'D';
                        ket = "Tidak Lulus";
                    } else {
                        index = 'E';
                        ket = "Tidak Lulus";
                    }

                    //cek jumlah kehadiran
                    if (Double.parseDouble(txtKehadiran.getText()) <= 11) {
                        ket = "Tidak Lulus";
                    }

                    int dialogResult = JOptionPane.showConfirmDialog (null,
                        "Ubah Data "+txtKDMK.getText(),
                        "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(dialogResult == JOptionPane.YES_OPTION){
                        Statement stt= kon.createStatement();
                        String SQL = "UPDATE `t_simulasi_nilai` set"       
                            + "`kd_mk`= '"+KDMK+"', "
                            + "`presAbsen`= '"+presAbsen+"', "
                            + "`presTugas`= '"+presTugas+"', "
                            + "`presUTS`= '"+presUTS+"', "
                            + "`presUAS`= '"+presUAS+"', "
                            + "`absensi`= '"+Kehadiran+"', "
                            + "`Tgs_1`  = '"+Tgs1+"', "
                            + "`Tgs_2`= '"+Tgs2+"', "
                            + "`Tgs_3`= '"+Tgs3+"', "
                            + "`UTS`= '"+UTS+"', "
                            + "`UAS`= '"+UAS+"', "
                            + "`nilai_absen`= '"+nilaiAbsen+"', "
                            + "`nilai_tugas`= '"+nilaiTugas+"', "
                            + "`nilai_UTS`= '"+nilaiUTS+"', "
                            + "`nilai_UAS`= '"+nilaiUAS+"', "
                            + "`nilai_akhir`= '"+nilaiAkhir+"', "
                            + "`index`= '"+index+"', "
                            + "`ket` = '"+ket+"'"
                            + "WHERE "
                            + "`kd_nilai` = '"+tableModel.getValueAt(row,0).toString()+"';";
                        stt.executeUpdate(SQL);
                        

                        Statement sttKD= kon.createStatement();
                        String SQLKD = "select * from t_simulasi_nilai "
                        + "INNER JOIN t_mata_kuliah using (kd_mk) "
                        + "WHERE "
                        + "`kd_nilai` = '"+tableModel.getValueAt(row,0).toString()+"';";
                        ResultSet resKD = stt.executeQuery(SQLKD);

                        while (resKD.next()){
                            nama = resKD.getString("nama_mk");
                        }
                        
                        data[0] = tableModel.getValueAt(row,0).toString();
                        data[1] = nama;
                        data[2] = String.valueOf(presAbsen);
                        data[3] = String.valueOf(presTugas);
                        data[4] = String.valueOf(presUTS);
                        data[5] = String.valueOf(presUAS);
                        data[6] = Kehadiran;
                        data[7] = Tgs1;
                        data[8] = Tgs2;
                        data[9] = Tgs3;
                        data[10] = UTS;
                        data[11] = UAS;
                        data[12] = String.valueOf(nilaiAbsen);
                        data[13] = String.valueOf(nilaiTugas);
                        data[14] = String.valueOf(nilaiUTS);
                        data[15] = String.valueOf(nilaiUAS);
                        data[16] = String.valueOf(nilaiAkhir);
                        data[17] = String.valueOf(index);
                        data[18] = ket;
                        tableModel.removeRow(row);
                        tableModel.insertRow(row, data);

                        stt.close();
                        kon.close();
                        membersihkan_teks();
                        btnSimpan.setEnabled(false);
                        nonaktif_teks();
                    }
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
        txtKehadiran.requestFocus();
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
                String SQL = "delete from `t_simulasi_nilai` "
                + "where "
                + "`kd_nilai` ='"+tableModel.getValueAt(row, 0).toString()+"'";
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
        String data[] = new String[19];
        aktif_teks();
        
        if ((
            (txtKehadiran.getText().isEmpty()) ||
            (txtPresAbsensi.getText().isEmpty()) ||
            (txtPresTugas.getText().isEmpty()) ||
            (txtPresUTS.getText().isEmpty()) ||
            (txtPresUAS.getText().isEmpty()) ||
            (txtTgs3.getText().isEmpty()) ||
            (txtTgs1.getText().isEmpty()) ||
            (txtTgs3.getText().isEmpty()) ||
            (txtKDMK.getText().isEmpty()) ||
            (txtUTS.getText().isEmpty()) ||
            (txtKDMK.getText().isEmpty()) ||
            (txtUAS.getText().isEmpty()) ||
            (txtUAS.getText().isEmpty()) )){
            JOptionPane.showMessageDialog(null, "Data tidak boleh kosong, silahkan dilengkapi");
            txtPresAbsensi.requestFocus();
        } else {
            try {

                Class.forName(driver);
                Connection kon = DriverManager.getConnection(database, user, pass);

                double nilaiAbsen, nilaiTugas, nilaiUTS, nilaiUAS = 0;
                double nilaiAkhir = 0;
                char index;
                String ket;
                
                presAbsen = Double.parseDouble(txtPresAbsensi.getText());
                presTugas = Double.parseDouble(txtPresTugas.getText());
                presUTS = Double.parseDouble(txtPresUTS.getText());
                presUAS = Double.parseDouble(txtPresUAS.getText());

                Double nKehadiran = Double.parseDouble(txtKehadiran.getText());
                Double nTgs1 = Double.parseDouble(txtTgs3.getText());
                Double nTgs2 = Double.parseDouble(txtTgs1.getText());
                Double nTgs3 = Double.parseDouble(txtTgs3.getText());
                Double nUTS  = Double.parseDouble(txtUAS.getText());
                Double nUAS  = Double.parseDouble(txtTgs2.getText());

                Double totalPres = presAbsen + presTugas + presUAS + presUTS;
                
                if (nKehadiran > 14 | nKehadiran < 0) {
                    JOptionPane.showMessageDialog(null, "Kehadiran harus di rang 0 -14");
                } else if (rangNilai(nTgs1) | rangNilai(nTgs2) | rangNilai(nTgs3) | rangNilai(nUTS) | rangNilai(nUAS)) {
                    JOptionPane.showMessageDialog(null, "Nilai harus di rang 0 - 100");
                } else if (rangNilai(presAbsen) | rangNilai(presTugas) | rangNilai(presUAS) | rangNilai(presUTS)){
                    JOptionPane.showMessageDialog(null, "Presentase harus di rang 0% - 100%");
                } else if (totalPres != 100){
                    JOptionPane.showMessageDialog(null, "Total Presentase harus 100%");
                } else {
                    nilaiAbsen = (((nKehadiran/14)*100*presAbsen)/100);
                    nilaiTugas = ((nTgs1+nTgs2+nTgs3))/3*(presTugas/100);
                    nilaiUTS = (nUTS*presUTS/100);
                    nilaiUAS = (nUAS*presUAS/100);

                    nilaiAkhir = nilaiAbsen + nilaiTugas + nilaiUTS + nilaiUAS;

                    //ubah format penulisan
                    String fnilaiAbsen = String.format("%.2f",nilaiAbsen);
                    String fnilaiTugas = String.format("%.2f",nilaiTugas);
                    String fnilaiAkhir = String.format("%.2f",nilaiAkhir);
                    String fnilaiUTS = String.format("%.2f",nilaiUTS);
                    String fnilaiUAS = String.format("%.2f",nilaiUAS);

                    if ((nilaiAkhir >= 80 && nilaiAkhir <= 100)) {
                        index = 'A';
                        ket = "Lulus";
                    } else if ((nilaiAkhir >= 68 && nilaiAkhir <= 79)) {
                        index = 'B';
                        ket = "Lulus";
                    } else if ((nilaiAkhir >= 56 && nilaiAkhir <= 67)) {
                        index = 'C';
                        ket = "Lulus";
                    } else if ((nilaiAkhir >= 45 && nilaiAkhir <= 55)) {
                        index = 'D';
                        ket = "Tidak Lulus";
                    } else {
                        index = 'E';
                        ket = "Tidak Lulus";
                    }

                    //cek jumlah kehadiran
                    if (Double.parseDouble(txtKehadiran.getText()) <= 11) {
                        ket = "Tidak Lulus";
                    }

                    int dialogResult = JOptionPane.showConfirmDialog (null,
                        "Simpan Data "+txtKDMK.getText(),
                        "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(dialogResult == JOptionPane.YES_OPTION){

                        Statement stt= kon.createStatement();
                        String SQL = "INSERT INTO `t_simulasi_nilai` ("
                        + "`kd_nilai`, "
                        + "`kd_mk`, "
                        + "`presAbsen`, "
                        + "`presTugas`, "
                        + "`presUTS`, "
                        + "`presUAS`, "
                        + "`absensi`, "
                        + "`Tgs_1`, "
                        + "`Tgs_2`, "
                        + "`Tgs_3`, "
                        + "`UTS`, "
                        + "`UAS`, "
                        + "`nilai_absen`, "
                        + "`nilai_tugas`, "
                        + "`nilai_UTS`, "
                        + "`nilai_UAS`, "
                        + "`nilai_akhir`, "
                        + "`index`, "
                        + "`ket`) "
                        + "VALUES "
                        + "(NULL, "
                        + "'"+txtKDMK.getText()+"',"
                        + "'"+txtKehadiran.getText()+"',"
                        + "'"+txtPresAbsensi.getText()+"',"
                        + "'"+txtPresTugas.getText()+"',"
                        + "'"+txtPresUTS.getText()+"',"
                        + "'"+txtPresUAS.getText()+"',"
                        + "'"+txtTgs1.getText()+"',"
                        + "'"+txtTgs2.getText()+"',"
                        + "'"+txtTgs3.getText()+"',"
                        + "'"+txtUTS.getText()+"',"
                        + "'"+txtUAS.getText()+"',"
                        + "'"+fnilaiAbsen+"',"
                        + "'"+fnilaiTugas+"',"
                        + "'"+fnilaiUTS+"',"
                        + "'"+fnilaiUAS+"',"
                        + "'"+fnilaiAkhir+"',"
                        + "'"+index+"',"
                        + "'"+ket+"')";

                        stt.executeUpdate(SQL);

                        String nMK = String.valueOf(cbxMK.getSelectedItem());
                        
                        data[0] = String.valueOf("67");
                        data[1] = nMK.substring(3,nMK.length());
                        data[2] = txtPresAbsensi.getText();
                        data[3] = txtPresTugas.getText();
                        data[4] = txtPresUTS.getText();
                        data[5] = txtPresUAS.getText();
                        data[6] = txtKehadiran.getText();
                        data[7] = txtTgs1.getText();
                        data[8] = txtTgs2.getText();
                        data[9] = txtTgs3.getText();
                        data[10] = txtUTS.getText();
                        data[11] = txtUAS.getText();
                        data[12] = String.valueOf(fnilaiAbsen);
                        data[13] = String.valueOf(fnilaiTugas);
                        data[14] = String.valueOf(fnilaiUTS);
                        data[15] = String.valueOf(fnilaiUAS);
                        data[16] = String.valueOf(fnilaiAkhir);
                        data[17] = String.valueOf(index);
                        data[18] = ket;
                        
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

    private void txtPresTugasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPresTugasKeyTyped
        // TODO add your handling code here:
        if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
             JOptionPane.showMessageDialog(null,"Pada Kolom Jumlah Hanya Bisa Memasukan Karakter Huruf");
        }
    }//GEN-LAST:event_txtPresTugasKeyTyped

    private void txtPresUASKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPresUASKeyTyped
        // TODO add your handling code here:
        if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
        }
    }//GEN-LAST:event_txtPresUASKeyTyped

    private void txtPresUTSKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPresUTSKeyTyped
        // TODO add your handling code here:
        if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
             JOptionPane.showMessageDialog(null,"Pada Kolom Jumlah Hanya Bisa Memasukan Karakter Huruf");
        }
    }//GEN-LAST:event_txtPresUTSKeyTyped

    private void txtPresAbsensiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPresAbsensiKeyTyped
        // TODO add your handling code here:
        if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
             JOptionPane.showMessageDialog(null,"Pada Kolom Jumlah Hanya Bisa Memasukan Karakter Angka");
        }
    }//GEN-LAST:event_txtPresAbsensiKeyTyped

    private void txtUTSKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUTSKeyTyped
        // TODO add your handling code here:
        if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
             JOptionPane.showMessageDialog(null,"Pada Kolom Jumlah Hanya Bisa Memasukan Karakter Angka");
        }
    }//GEN-LAST:event_txtUTSKeyTyped

    private void txtTgs3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTgs3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTgs3ActionPerformed

    private void txtKDMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKDMKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKDMKActionPerformed

    private void txtPresAbsensiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPresAbsensiActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtPresAbsensiActionPerformed

    private void txtKehadiranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKehadiranActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKehadiranActionPerformed

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
            java.util.logging.Logger.getLogger(frmSimulasiNilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmSimulasiNilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmSimulasiNilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmSimulasiNilai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmSimulasiNilai().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnKeluar;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnUbah;
    private javax.swing.JComboBox<String> cbxMK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableNilai;
    private javax.swing.JTextField txtKDMK;
    private javax.swing.JTextField txtKehadiran;
    private javax.swing.JTextField txtPresAbsensi;
    private javax.swing.JTextField txtPresTugas;
    private javax.swing.JTextField txtPresUAS;
    private javax.swing.JTextField txtPresUTS;
    private javax.swing.JTextField txtTgs1;
    private javax.swing.JTextField txtTgs2;
    private javax.swing.JTextField txtTgs3;
    private javax.swing.JTextField txtUAS;
    private javax.swing.JTextField txtUTS;
    // End of variables declaration//GEN-END:variables
}
