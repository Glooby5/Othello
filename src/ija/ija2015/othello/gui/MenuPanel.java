package ija.ija2015.othello.gui;

import javax.swing.*;

/**
 *
 * @author Ondřej
 */
public class MenuPanel extends javax.swing.JPanel {

    public MenuPanel() {
        initComponents();
    }

    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jBtnStartGame = new javax.swing.JButton();
        jLRival = new javax.swing.JLabel();
        jLMenu = new javax.swing.JLabel();
        jLSize = new javax.swing.JLabel();
        jSpinSize = new javax.swing.JSpinner();
        jSpinRival = new javax.swing.JSpinner();
        jLImage = new javax.swing.JLabel(new ImageIcon("othello.png"));

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
                jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
                jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(137, 186, 59));
        setMaximumSize(new java.awt.Dimension(300, 400));
        setPreferredSize(new java.awt.Dimension(300, 400));

        jBtnStartGame.setText("Hotovo");

        jLRival.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLRival.setText("Protivník");

        jLMenu.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLMenu.setText("Menu");

        jLSize.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLSize.setText("Rozměr");

        jSpinSize.setModel(new javax.swing.SpinnerListModel(new String[] {"6x6", "8x8", "10x10", "12x12"}));

        jSpinRival.setModel(new javax.swing.SpinnerListModel(new String[] {"Člověk", "Algoritmus 1", "Algoritmus 2"}));

        jLImage.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLImage.setToolTipText("");
        jLImage.setAlignmentX(0.5F);
        jLImage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLImage.setMaximumSize(new java.awt.Dimension(130, 130));
        jLImage.setMinimumSize(new java.awt.Dimension(130, 130));
        jLImage.setName(""); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLImage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLRival)
                                                        .addComponent(jLSize))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jSpinSize, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jSpinRival, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLMenu)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jBtnStartGame)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jLMenu)
                                .addGap(18, 18, 18)
                                .addComponent(jBtnStartGame)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLImage, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLSize)
                                        .addComponent(jSpinSize, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLRival)
                                        .addComponent(jSpinRival, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(36, Short.MAX_VALUE))
        );

        jLImage.getAccessibleContext().setAccessibleName("");
        jLImage.getAccessibleContext().setAccessibleDescription("");
    }

    private javax.swing.JButton jBtnStartGame;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLImage;
    private javax.swing.JLabel jLMenu;
    private javax.swing.JLabel jLRival;
    private javax.swing.JLabel jLSize;
    private javax.swing.JSpinner jSpinRival;
    private javax.swing.JSpinner jSpinSize;
}
