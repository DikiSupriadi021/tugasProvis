/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kemahasiswaan_10116331_10116352;

/**
 *
 * @author my
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
    import javax.swing.*;

    //fungsi Import yang digunakna untuk SQL
    import java.sql.*;

    //fungsi import yang digunaka untuk tanggal
    import java.text.SimpleDateFormat;
    import java.util.Date;
    import java.util.logging.Level;
    import java.util.logging.Logger;

public class frmNilaiMahasiswa extends javax.swing.JFrame {

    /**
     * Creates new form frmNilaiMahasiswa
     */
    koneksi dbsetting;
    String driver, database, user, pass;
    Object tabel;
    String nimMHS[] = new String[20];
    String kdMK[] = new String[20];
    String data[] = new String[20];
    int banyakData = 0;
    double presAbsen,presTugas,presUTS,presUAS;
    
    public frmNilaiMahasiswa() {
        initComponents();
        
        dbsetting = new koneksi();
        driver = dbsetting.SettingPanel("DBDriver");
        database = dbsetting.SettingPanel("DBDatabase");
        user = dbsetting.SettingPanel("DBUsername");
        pass = dbsetting.SettingPanel("DBPassword");
        
        getDataCbx();
        tableNilai.setModel(tableModel);
        settableload();
        nonaktif_teks();
        buttonAwal();
        
    }
    
    private javax.swing.table.DefaultTableModel tableModel = getDefaultTableModel();
    
    private javax.swing.table.DefaultTableModel getDefaultTableModel() {
        
        //menambahkan judul header
        return new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String [] {"Kd Nilai","Nama","Nama M.K","Absensi","Tgs 1","Tgs 2","Tgs 3"
                        ,"UTS","UAS","Nilai Absen","Nilai Tugas","Niali Akhir","Index"
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
            while (resMhs.next()) {
                i++;
                cbxNama.addItem(i+". "+resMhs.getString("nama"));
                nimMHS[i] = resMhs.getString("nim");
                System.out.println(nimMHS[i]);
            } 
            System.out.println(resMhs.getRow());;
            
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
    
    
    private void settableload() {
        try {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            
            Statement stt= kon.createStatement();
            String SQL = "select * from t_nilai "
                    + "INNER JOIN t_mata_kuliah using (kd_mk) "
                    + "INNER JOIN t_mahasiswa USING (nim)";
            ResultSet res = stt.executeQuery(SQL);
            
            
            while (res.next()){
                data[0] = res.getString("kd_nilai");
                data[1] = res.getString("nama");
                data[2] = res.getString("nama_mk");
                data[3] = res.getString("absensi");
                data[4] = res.getString("tgs_1");
                data[5] = res.getString("tgs_2");
                data[6] = res.getString("tgs_3");
                data[7] = res.getString("UTS");
                data[8] = res.getString("UAS");
                data[9] = res.getString("nilai_absen");
                data[10] = res.getString("nilai_tugas");
                data[11] = res.getString("nilai_akhir");
                data[12] = res.getString("index");
                data[13] = res.getString("ket");
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
    
    public boolean rangNilai(double x) {
        boolean stat;
        if (x > 100 | x < 0) {
            stat = true;
        } else {
            stat = false;
        }
        
        return stat;
    }
    
    public void membersihkan_teks() {
        txtNIM.setText("");
        txtKehadiran.setText("");
        txtTgs1.setText("");
        txtTgs2.setText("");
        txtTgs3.setText("");
        txtKDMK.setText("");
        txtUTS.setText("");
        txtUAS.setText("");
        
    }
    
    public void nonaktif_teks() {
        txtNIM.setEnabled(false);
        txtKehadiran.setEnabled(false);
        txtTgs1.setEnabled(false);
        txtTgs2.setEnabled(false);
        txtTgs3.setEnabled(false);
        txtKDMK.setEnabled(false);
        txtUTS.setEnabled(false);
        txtUAS.setEnabled(false);
        txtAngkatan.setEnabled(false);
        cbxMK.setEnabled(false);
        cbxNama.setEnabled(false);
    }
    
    public void aktif_teks() {
        txtNIM.setEnabled(false);
        txtKehadiran.setEnabled(true);
        txtTgs1.setEnabled(true);
        txtTgs2.setEnabled(true);
        txtTgs3.setEnabled(true);
        txtKDMK.setEnabled(false);
        txtUTS.setEnabled(true);
        txtUAS.setEnabled(true);
        txtAngkatan.setEnabled(true);
        cbxMK.setEnabled(true);
        cbxNama.setEnabled(true);
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
            String SQL = "SELECT * FROM `t_nilai` WHERE `kd_nilai` = "+
                    tableModel.getValueAt(row, 0).toString();
            ResultSet res = stt.executeQuery(SQL);
            while (res.next()){
                data[0] = res.getString("nim");
                data[1] = res.getString("kd_mk");
            }
            
            txtNIM.setText(data[0]);
            txtKDMK.setText(data[1]);
            txtKehadiran.setText(tableModel.getValueAt(row, 3).toString());
            txtTgs1.setText(tableModel.getValueAt(row, 4).toString());
            txtTgs2.setText(tableModel.getValueAt(row, 5).toString());
            txtTgs3.setText(tableModel.getValueAt(row, 6).toString());
            txtUTS.setText(tableModel.getValueAt(row, 7).toString());
            txtUAS.setText(tableModel.getValueAt(row, 8).toString());

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
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbxFilter = new javax.swing.JComboBox<>();
        txtCariData = new javax.swing.JTextField();
        btnCariData = new javax.swing.JButton();
        btnTampil = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbxNama = new javax.swing.JComboBox<>();
        cbxMK = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtNIM = new javax.swing.JTextField();
        txtKehadiran = new javax.swing.JTextField();
        txtTgs1 = new javax.swing.JTextField();
        txtTgs2 = new javax.swing.JTextField();
        txtTgs3 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtUTS = new javax.swing.JTextField();
        txtUAS = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtKDMK = new javax.swing.JTextField();
        txtAngkatan = new com.toedter.calendar.JYearChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableNilai = new javax.swing.JTable();
        btnTambah = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnKeluar = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/student.png"))); // NOI18N
        jLabel1.setText("       FORM NILAI MAHASISWA");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 790, 77));

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel2.setText("Pencarian Data Mata Kuliah");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, -1, -1));

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel3.setText("Masukan Data");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 560, -1, -1));

        cbxFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "kd_nilai", "nim", "kd_mk", "absensi", "Tgs_1", "Tgs_2", "Tgs_3", "UTS", "UAS", "nilai_absen", "nilai_tugas", "nilai_akhir", "index ", "ket" }));
        cbxFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxFilterActionPerformed(evt);
            }
        });
        getContentPane().add(cbxFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 550, -1, -1));
        getContentPane().add(txtCariData, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 550, 145, -1));

        btnCariData.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/musica-searcher.png"))); // NOI18N
        btnCariData.setToolTipText("");
        btnCariData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariDataActionPerformed(evt);
            }
        });
        getContentPane().add(btnCariData, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 550, -1, -1));

        btnTampil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/list.png"))); // NOI18N
        btnTampil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTampilActionPerformed(evt);
            }
        });
        getContentPane().add(btnTampil, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 550, -1, -1));

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel4.setText("Nama");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 222, -1, -1));

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel7.setText("Nama M.K.");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(638, 222, -1, -1));

        cbxNama.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih Nama MHS -" }));
        cbxNama.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxNamaItemStateChanged(evt);
            }
        });
        cbxNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxNamaActionPerformed(evt);
            }
        });
        cbxNama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cbxNamaKeyReleased(evt);
            }
        });
        getContentPane().add(cbxNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 217, -1, -1));

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
        getContentPane().add(cbxMK, new org.netbeans.lib.awtextra.AbsoluteConstraints(786, 217, 172, -1));

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel5.setText("NIM");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 267, -1, -1));

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel6.setText("Kehadiran");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 312, -1, -1));

        jLabel8.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel8.setText("Tugas 1");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 357, -1, -1));

        jLabel9.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel9.setText("Tugas 2");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 402, -1, -1));

        jLabel10.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel10.setText("Tugas 3");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 447, -1, -1));

        txtNIM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNIMKeyTyped(evt);
            }
        });
        getContentPane().add(txtNIM, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 262, 145, -1));

        txtKehadiran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtKehadiranKeyTyped(evt);
            }
        });
        getContentPane().add(txtKehadiran, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 307, 48, -1));

        txtTgs1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTgs1ActionPerformed(evt);
            }
        });
        txtTgs1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTgs1KeyTyped(evt);
            }
        });
        getContentPane().add(txtTgs1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 352, 48, -1));

        txtTgs2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTgs2KeyTyped(evt);
            }
        });
        getContentPane().add(txtTgs2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 397, 48, -1));

        txtTgs3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTgs3KeyTyped(evt);
            }
        });
        getContentPane().add(txtTgs3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 442, 48, -1));

        jLabel11.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel11.setText("Pertemuan");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 312, -1, -1));

        jLabel12.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel12.setText("UTS");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(638, 312, -1, -1));

        txtUTS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUTSKeyTyped(evt);
            }
        });
        getContentPane().add(txtUTS, new org.netbeans.lib.awtextra.AbsoluteConstraints(786, 307, 48, -1));

        txtUAS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtUASKeyTyped(evt);
            }
        });
        getContentPane().add(txtUAS, new org.netbeans.lib.awtextra.AbsoluteConstraints(786, 352, 48, -1));

        jLabel13.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel13.setText("UAS");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(638, 357, -1, -1));

        jLabel14.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel14.setText("Angkatan");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(638, 397, -1, -1));

        jLabel15.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel15.setText("Kode Mata Kuliah");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(638, 267, -1, -1));
        getContentPane().add(txtKDMK, new org.netbeans.lib.awtextra.AbsoluteConstraints(786, 262, 145, -1));
        getContentPane().add(txtAngkatan, new org.netbeans.lib.awtextra.AbsoluteConstraints(786, 397, 120, -1));

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

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 590, 1170, 128));

        btnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/add(1).png"))); // NOI18N
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });
        getContentPane().add(btnTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, -1, -1));

        btnUbah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/edit.png"))); // NOI18N
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });
        getContentPane().add(btnUbah, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, -1, -1));

        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete.png"))); // NOI18N
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });
        getContentPane().add(btnHapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, -1, -1));

        btnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save-button.png"))); // NOI18N
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        getContentPane().add(btnSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 160, -1, -1));

        btnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/delete.png"))); // NOI18N
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });
        getContentPane().add(btnBatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 160, -1, -1));

        btnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/home.png"))); // NOI18N
        btnKeluar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKeluarActionPerformed(evt);
            }
        });
        getContentPane().add(btnKeluar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 50, 50));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/simple-art-cubes-abstract.jpg"))); // NOI18N
        jLabel16.setText("jLabel16");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 780));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxFilterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxFilterActionPerformed

    private void cbxNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxNamaActionPerformed

    private void cbxMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxMKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxMKActionPerformed

    private void cbxNamaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbxNamaKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cbxNamaKeyReleased

    private void cbxNamaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxNamaItemStateChanged
        // TODO add your handling code here:
        txtNIM.setText(nimMHS[cbxNama.getSelectedIndex()]);
        System.out.println(cbxNama.getSelectedIndex());
    }//GEN-LAST:event_cbxNamaItemStateChanged

    private void cbxMKItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxMKItemStateChanged
        // TODO add your handling code here:
        txtKDMK.setText(kdMK[cbxMK.getSelectedIndex()]);
    }//GEN-LAST:event_cbxMKItemStateChanged

    private void tableNilaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNilaiMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount()==1) {
            tampil_field();
        }
    }//GEN-LAST:event_tableNilaiMouseClicked

    private void txtNIMKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNIMKeyTyped
        // TODO add your handling code here:
        filterhuruf(evt);
    }//GEN-LAST:event_txtNIMKeyTyped

    private void txtKehadiranKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKehadiranKeyTyped
        // TODO add your handling code here:
        filterhuruf(evt);
    }//GEN-LAST:event_txtKehadiranKeyTyped

    private void txtTgs1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTgs1KeyTyped
        // TODO add your handling code here:
        filterhuruf(evt);
    }//GEN-LAST:event_txtTgs1KeyTyped

    private void txtTgs2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTgs2KeyTyped
        // TODO add your handling code here:
        filterhuruf(evt);
    }//GEN-LAST:event_txtTgs2KeyTyped

    private void txtTgs3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTgs3KeyTyped
        // TODO add your handling code here:
        filterhuruf(evt);
    }//GEN-LAST:event_txtTgs3KeyTyped

    private void txtUTSKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUTSKeyTyped
        // TODO add your handling code here:
        filterhuruf(evt);
    }//GEN-LAST:event_txtUTSKeyTyped

    private void txtUASKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUASKeyTyped
        // TODO add your handling code here:
        filterhuruf(evt);
    }//GEN-LAST:event_txtUASKeyTyped

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
            SQL = "select * from t_nilai where "+Filter+" = "
            + "'%"+ txtCariData.getText()+"%'";

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

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        buttonAwal();
        membersihkan_teks();
        nonaktif_teks();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        String data[] = new String[14];
        String tampilan = "yyyy";
        SimpleDateFormat format = new SimpleDateFormat(tampilan);
        String angkatan = String.valueOf(format.format(txtAngkatan.getYear()));

        aktif_teks();

        if ((txtNIM.getText().isEmpty()) ||
            (txtKehadiran.getText().isEmpty()) ||
            (txtTgs1.getText().isEmpty()) ||
            (txtTgs2.getText().isEmpty()) ||
            (txtTgs3.getText().isEmpty()) ||
            (txtKDMK.getText().isEmpty()) ||
            (txtUTS.getText().isEmpty()) ||
            (txtUAS.getText().isEmpty()) ||
            (angkatan.isEmpty())){
            JOptionPane.showMessageDialog(null, "Data tidak boleh kosong, silahkan dilengkapi");
            txtKehadiran.requestFocus();
        } else {
            try {

                Class.forName(driver);
                Connection kon = DriverManager.getConnection(database, user, pass);

                double nilaiAbsen, nilaiTugas, nilaiUTS, nilaiUAS = 0;
                double nilaiAkhir = 0;
                char index;
                String ket;
                presAbsen = 5;
                presTugas = 25;
                presUTS = 30;
                presUAS = 40;

                Double nKehadiran = Double.parseDouble(txtKehadiran.getText());
                Double nTgs1 = Double.parseDouble(txtTgs1.getText());
                Double nTgs2 = Double.parseDouble(txtTgs2.getText());
                Double nTgs3 = Double.parseDouble(txtTgs3.getText());
                Double nUTS  = Double.parseDouble(txtUTS.getText());
                Double nUAS  = Double.parseDouble(txtUAS.getText());

                if (nKehadiran > 14 | nKehadiran < 0) {
                    JOptionPane.showMessageDialog(null, "Kehadiran harus di rang 0 -14");
                } else if (rangNilai(nTgs1) | rangNilai(nTgs2) | rangNilai(nTgs3) | rangNilai(nUTS) | rangNilai(nUAS)) {
                    JOptionPane.showMessageDialog(null, "Nilai harus di rang 0 - 100");
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
                        "Simpan Data "+txtNIM.getText()+" ("+txtKDMK.getText()+")",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(dialogResult == JOptionPane.YES_OPTION){

                        Statement stt= kon.createStatement();
                        String SQL = "INSERT INTO `t_nilai` ("
                        + "`kd_nilai`, "
                        + "`nim`, "
                        + "`kd_mk`, "
                        + "`absensi`, "
                        + "`Tgs_1`, "
                        + "`Tgs_2`, "
                        + "`Tgs_3`, "
                        + "`UTS`, "
                        + "`UAS`, "
                        + "`nilai_absen`, "
                        + "`nilai_tugas`, "
                        + "`nilai_akhir`, "
                        + "`index`, "
                        + "`ket`) "
                        + "VALUES "
                        + "(NULL, "
                        + "'"+txtNIM.getText()+"',"
                        + "'"+txtKDMK.getText()+"',"
                        + "'"+txtKehadiran.getText()+"',"
                        + "'"+txtTgs1.getText()+"',"
                        + "'"+txtTgs2.getText()+"',"
                        + "'"+txtTgs3.getText()+"',"
                        + "'"+txtUTS.getText()+"',"
                        + "'"+txtUAS.getText()+"',"
                        + "'"+fnilaiAbsen+"',"
                        + "'"+fnilaiTugas+"',"
                        + "'"+fnilaiAkhir+"',"
                        + "'"+index+"',"
                        + "'"+ket+"')";

                        stt.executeUpdate(SQL);

                        String nama = String.valueOf(cbxNama.getSelectedItem());
                        String nMK = String.valueOf(cbxMK.getSelectedItem());
                        data[0] = String.valueOf("67");
                        data[1] = nama.substring(3,nama.length());
                        data[2] = nMK.substring(3,nMK.length());
                        data[3] = txtKehadiran.getText();
                        data[4] = txtTgs1.getText();
                        data[5] = txtTgs2.getText();
                        data[6] = txtTgs3.getText();
                        data[7] = txtUTS.getText();
                        data[8] = txtUAS.getText();
                        data[9] = String.valueOf(fnilaiAbsen);
                        data[10] = String.valueOf(fnilaiTugas);
                        data[11] = String.valueOf(fnilaiAkhir);
                        data[12] = String.valueOf(index);
                        data[13] = ket;
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
                String SQL = "delete from `t_nilai` "
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

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        // TODO add your handling code here
        String tampilan = "yyyy";
        SimpleDateFormat format = new SimpleDateFormat(tampilan);
        String angkatan = String.valueOf(format.format(txtAngkatan.getYear()));

        String NIM = txtNIM.getText();
        String Kehadiran = txtKehadiran.getText();
        String Tgs1 = txtTgs1.getText();
        String Tgs2 = txtTgs2.getText();
        String Tgs3 = txtTgs3.getText();
        String KDMK = txtKDMK.getText();
        String UTS = txtUTS.getText();
        String UAS = txtUAS.getText();
        String nama="";

        if ((txtNIM.getText().isEmpty()) ||
            (txtKehadiran.getText().isEmpty()) ||
            (txtTgs1.getText().isEmpty()) ||
            (txtTgs2.getText().isEmpty()) ||
            (txtTgs3.getText().isEmpty()) ||
            (txtKDMK.getText().isEmpty()) ||
            (txtUTS.getText().isEmpty()) ||
            (txtUAS.getText().isEmpty()) ||
            (angkatan.isEmpty())){
            JOptionPane.showMessageDialog(null, "Data tidak boleh kosong, silahkan dilengkapi");
            txtKehadiran.requestFocus();
        } else {
            try {
                Class.forName(driver);
                Connection kon = DriverManager.getConnection(database, user, pass);

                double nilaiAbsen, nilaiTugas, nilaiUTS, nilaiUAS = 0;
                double nilaiAkhir = 0;
                char index;
                String ket;
                presAbsen = 5;
                presTugas = 25;
                presUTS = 30;
                presUAS = 40;

                Double nKehadiran = Double.parseDouble(txtKehadiran.getText());
                Double nTgs1 = Double.parseDouble(txtTgs1.getText());
                Double nTgs2 = Double.parseDouble(txtTgs2.getText());
                Double nTgs3 = Double.parseDouble(txtTgs3.getText());
                Double nUTS  = Double.parseDouble(txtUTS.getText());
                Double nUAS  = Double.parseDouble(txtUAS.getText());

                if (nKehadiran > 14 | nKehadiran < 0) {
                    JOptionPane.showMessageDialog(null, "Kehadiran harus di rang 0 -14");
                } else if (rangNilai(nTgs1) | rangNilai(nTgs2) | rangNilai(nTgs3) | rangNilai(nUTS) | rangNilai(nUAS)) {
                    JOptionPane.showMessageDialog(null, "Nilai harus di rang 0 - 100");
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
                        "Ubah Data "+txtNIM.getText()+" ("+txtKDMK.getText()+")",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(dialogResult == JOptionPane.YES_OPTION){
                        Statement stt= kon.createStatement();
                        String SQL = "UPDATE `t_nilai` set"
                        + "`nim`        = '"+NIM+"', "
                        + "`kd_mk`      = '"+KDMK+"', "
                        + "`absensi`    = '"+Kehadiran+"', "
                        + "`Tgs_1`      = '"+Tgs1+"', "
                        + "`Tgs_2`      = '"+Tgs2+"', "
                        + "`Tgs_3`      = '"+Tgs3+"', "
                        + "`UTS`        = '"+UTS+"', "
                        + "`UAS`        = '"+UAS+"', "
                        + "`nilai_absen`= '"+String.format("%.2f",nilaiAbsen)+"', "
                        + "`nilai_tugas`= '"+String.format("%.2f",nilaiTugas)+"', "
                        + "`nilai_akhir`= '"+String.format("%.2f",nilaiAkhir)+"', "
                        + "`index`      = '"+index+"', "
                        + "`ket`        = '"+ket+"'"
                        + "WHERE "
                        + "`kd_nilai` = '"+tableModel.getValueAt(row,0).toString()+"';";
                        stt.executeUpdate(SQL);

                        Statement sttKD= kon.createStatement();
                        String SQLKD = "select * from t_nilai "
                        + "INNER JOIN t_mata_kuliah using (kd_mk) "
                        + "INNER JOIN t_mahasiswa USING (nim)"
                        + "WHERE "
                        + "`kd_nilai` = '"+tableModel.getValueAt(row,0).toString()+"';";
                        ResultSet resKD = stt.executeQuery(SQLKD);

                        while (resKD.next()){
                            nama = resKD.getString("nama");
                        }

                        data[0] = tableModel.getValueAt(row,0).toString();
                        data[1] = nama;
                        data[2] = KDMK;
                        data[3] = Kehadiran;
                        data[4] = Tgs1;
                        data[5] = Tgs2;
                        data[6] = Tgs3;
                        data[7] = UTS;
                        data[8] = UAS;
                        data[9] = String.valueOf(nilaiAbsen);
                        data[10] = String.valueOf(nilaiTugas);
                        data[11] = String.valueOf(nilaiAkhir);
                        data[12] = String.valueOf(index);
                        data[13] = ket;
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

    private void txtTgs1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTgs1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTgs1ActionPerformed
void filterhuruf(KeyEvent txtNoIdentitas){
        if(Character.isAlphabetic(txtNoIdentitas.getKeyChar())){
            txtNoIdentitas.consume();
            JOptionPane.showMessageDialog(null,"Pada Kolom Jumlah Hanya Bisa Memasukan Karakter Angka");
        }
    }
void filterangka(KeyEvent txtNama){
        if(Character.isDigit(txtNama.getKeyChar())){
            txtNama.consume();
            JOptionPane.showMessageDialog(null,"Pada Kolom Jumlah Hanya Bisa Memasukan Karakter Huruf");
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
            java.util.logging.Logger.getLogger(frmNilaiMahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmNilaiMahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmNilaiMahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmNilaiMahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmNilaiMahasiswa().setVisible(true);
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
    private javax.swing.JComboBox<String> cbxMK;
    private javax.swing.JComboBox<String> cbxNama;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private com.toedter.calendar.JYearChooser txtAngkatan;
    private javax.swing.JTextField txtCariData;
    private javax.swing.JTextField txtKDMK;
    private javax.swing.JTextField txtKehadiran;
    private javax.swing.JTextField txtNIM;
    private javax.swing.JTextField txtTgs1;
    private javax.swing.JTextField txtTgs2;
    private javax.swing.JTextField txtTgs3;
    private javax.swing.JTextField txtUAS;
    private javax.swing.JTextField txtUTS;
    // End of variables declaration//GEN-END:variables
}
