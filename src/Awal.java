
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gumilang
 */
public class Awal extends javax.swing.JFrame {

    public Awal() {
        initComponents();
        
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width/2 - getWidth()/2, size.height/2 - getHeight()/2);
        
        show_user();
        fetch();
        FillCombo();
    }

    public void fetch(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=DB_utama;user=sa;password=admin";
            Connection con = DriverManager.getConnection(url);
            String query2 = "select * from Table_peminjaman";
            PreparedStatement pst = con.prepareStatement(query2);
            ResultSet rs = pst.executeQuery();
            
            tablepinjam.setModel(DbUtils.resultSetToTableModel(rs));
            tablepinjam.removeColumn(tablepinjam.getColumnModel().getColumn(0));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public ArrayList<User> userList(){
        ArrayList<User>usersList = new ArrayList<>();
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=DB_utama;user=sa;password=admin";
            Connection con = DriverManager.getConnection(url);
            String query1 = "Select * From Table_peminjaman";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query1);
            User user;
            while(rs.next()){
                user=new User(rs.getInt("no_regist"), rs.getString("Nama"), rs.getString("Unit"), rs.getString("Barang"), rs.getString("Pinjam"), rs.getString("Kembali"), rs.getString("Status"));
                usersList.add(user);
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return usersList;
    }
    
    public void show_user(){
        ArrayList<User> list = userList();
        DefaultTableModel model = (DefaultTableModel)tablepinjam.getModel();
        Object[] row = new Object[7];
        for(int i=0;i<list.size();i++){
            row[0]=list.get(i).getno_regist();
            row[1]=list.get(i).getNama();
            row[2]=list.get(i).getUnit();
            row[3]=list.get(i).getBarang();
            row[4]=list.get(i).getPinjam();
            row[5]=list.get(i).getKembali();
            row[6]=list.get(i).getStatus();
            model.addRow(row);
        }
    }
    
    public void FillCombo(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=DB_utama;user=sa;password=admin";
            Connection con = DriverManager.getConnection(url);
            String query3 = "Select * From Table_peminjaman";
            PreparedStatement pst = con.prepareStatement(query3);
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                String name = rs.getString("Nama");
                namabarang.addItem(name);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
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
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        namapeminjam = new javax.swing.JTextField();
        unit = new javax.swing.JTextField();
        namabarang = new javax.swing.JComboBox();
        tglpinjam = new com.toedter.calendar.JDateChooser();
        tglkembali = new com.toedter.calendar.JDateChooser();
        status = new javax.swing.JComboBox();
        submit = new javax.swing.JButton();
        update = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablepinjam = new javax.swing.JTable();
        cari = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        admin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nama Peminjam");

        jLabel2.setText("Unit");

        jLabel3.setText("Nama Barang");

        jLabel4.setText("Tanggal Pinjam");

        jLabel5.setText("Tanggal Kembali");

        jLabel6.setText("Status");

        status.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "pinjam", "kembali" }));

        submit.setText("Submit");
        submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitActionPerformed(evt);
            }
        });

        update.setText("Update");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        tablepinjam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "no_regist", "Nama", "Unit", "Barang", "Pinjam", "Kembali", "Status"
            }
        ));
        tablepinjam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablepinjamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablepinjam);
        if (tablepinjam.getColumnModel().getColumnCount() > 0) {
            tablepinjam.getColumnModel().getColumn(0).setHeaderValue("no_regist");
            tablepinjam.getColumnModel().getColumn(1).setResizable(false);
        }

        cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cariKeyReleased(evt);
            }
        });

        jLabel7.setText("Search");

        admin.setText("Admin");
        admin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(admin)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6))
                            .addGap(27, 27, 27)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(namapeminjam, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                                .addComponent(unit)
                                .addComponent(tglkembali, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tglpinjam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(namabarang, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(status, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(submit)
                                            .addGap(0, 0, Short.MAX_VALUE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(update)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(namapeminjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(unit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(namabarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(tglpinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(tglkembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submit)
                    .addComponent(update)
                    .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(admin)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=DB_utama;user=sa;password=admin";
            Connection con = DriverManager.getConnection(url);
            int row = tablepinjam.getSelectedRow();
            String value = (tablepinjam.getModel().getValueAt(row, 0).toString());
            String query = "UPDATE Table_peminjaman SET Kembali=?, Status=? where no_regist="+value;
            PreparedStatement pst = con.prepareStatement(query);
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date1 = sdf.format(tglkembali.getDate());
            pst.setString(1, date1);
            String course1;
            course1 = status.getSelectedItem().toString();
            pst.setString(2, course1);
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Sukses Update Ke-database");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        fetch();
    }//GEN-LAST:event_updateActionPerformed

    private void submitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitActionPerformed
        // TODO add your handling code here:
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=DB_utama;user=sa;password=admin";
            Connection con = DriverManager.getConnection(url);
            String query = "insert into Table_peminjaman(Nama,Unit,Barang,Pinjam,Kembali,Status)values(?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, namapeminjam.getText());
            pst.setString(2, unit.getText());;
            String course;
            course = namabarang.getSelectedItem().toString();
            pst.setString(3, course);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(tglpinjam.getDate());
            pst.setString(4, date);
            String date1 = sdf.format(tglkembali.getDate());
            pst.setString(5, date1);
            String course1;
            course1 = status.getSelectedItem().toString();
            pst.setString(6, course1);
            pst.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Sukses Insert Ke-database");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        fetch();
    }//GEN-LAST:event_submitActionPerformed

    private void cariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariKeyReleased
        DefaultTableModel table = (DefaultTableModel)tablepinjam.getModel();
        String search = cari.getText().toLowerCase();
        TableRowSorter<DefaultTableModel> tr = new  TableRowSorter<DefaultTableModel>(table);
        tablepinjam.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(search));
        
    }//GEN-LAST:event_cariKeyReleased

    private void tablepinjamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablepinjamMouseClicked
        // TODO add your handling code here:
        try{
            int i = tablepinjam.getSelectedRow();
            TableModel model = tablepinjam.getModel();
            namapeminjam.setText(model.getValueAt(i, 1).toString());
            unit.setText(model.getValueAt(i, 2).toString());
            String subject = model.getValueAt(0, 3).toString();
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(i, 4));
            tglpinjam.setDate(date);
            Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(i, 5));
            tglkembali.setDate(date1);
            String subject1 = model.getValueAt(i, 6).toString();
            switch(subject1){
                case "pinjam":
                    status.setSelectedIndex(0);
                    break;
                case "kembali":
                    status.setSelectedIndex(1);
                    break;
            }
        }catch (ParseException ex){
            Logger.getLogger(Awal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tablepinjamMouseClicked

    private void adminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminActionPerformed
        Login field = new Login();
        field.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_adminActionPerformed

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
            java.util.logging.Logger.getLogger(Awal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Awal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Awal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Awal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Awal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton admin;
    private javax.swing.JTextField cari;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox namabarang;
    private javax.swing.JTextField namapeminjam;
    private javax.swing.JComboBox status;
    private javax.swing.JButton submit;
    private javax.swing.JTable tablepinjam;
    private com.toedter.calendar.JDateChooser tglkembali;
    private com.toedter.calendar.JDateChooser tglpinjam;
    private javax.swing.JTextField unit;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
