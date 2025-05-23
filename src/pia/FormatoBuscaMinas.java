/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.JButton;
import javax.swing.JOptionPane;



/**
 *
 * @author 52811
 */
public class FormatoBuscaMinas extends javax.swing.JFrame 
{
    int nFilas = 10;
    int nColumnas = 10;
    int nMinas = 10;
    
    JButton[][] botones;
    
    TableroBuscaMinas tableroBuscaMinas;
    
    public FormatoBuscaMinas() 
    {
        initComponents();
        juegoNuevo();
    }
    void descargaControles()
    {
        if(botones != null)
        {
            for(int i = 0 ; i < botones.length; i++)
            {
                for(int j  = 0; j < botones[i].length; j++)
                {
                    if(botones[i][j] != null)
                    {
                        getContentPane().remove(botones[i][j]);
                    }
                }
            }
        }
    }
    private void juegoNuevo()
    {
        descargaControles();
        cargaControles();
        crearTableroBuscaminas();
        repaint();
    }
    private void crearTableroBuscaminas()
    {
        tableroBuscaMinas = new TableroBuscaMinas(nFilas, nColumnas, nMinas);
        tableroBuscaMinas.setPartidaPerdida(new Consumer<List<Casillas>>() 
        {
            @Override
            public void accept(List<Casillas> t)
            {
                for(Casillas casillasMinas: t)
                {
                    botones[casillasMinas.getPosicionF()][casillasMinas.getPosicionC()].setText("*");
                }
                for(int i = 0 ; i < botones.length ; i++)
                {
                    for(int j = 0 ; j < botones[i].length ; j++)
                    {
                        botones[i][j].setEnabled(false);
                    }
                }
            }
        });
        
        tableroBuscaMinas.imprimirTablero();
        
        
        
        tableroBuscaMinas.setEventoPartidaGanada(new Consumer<List<Casillas>>() 
        {
            @Override
            public void accept(List<Casillas> t)
            {
                for(Casillas casillasMinas: t)
                {
                    botones[casillasMinas.getPosicionF()][casillasMinas.getPosicionC()].setText(":)");
                }
            }
        });
        tableroBuscaMinas.setCasillaAbierta(new Consumer<Casillas>()
        {
            @Override
            public void accept(Casillas t)
            {
                botones[t.getPosicionF()][t.getPosicionC()].setEnabled(false);
                botones[t.getPosicionF()][t.getPosicionC()].setText(t.getnMinasAlrededor()==0?"":t.getnMinasAlrededor()+"");
            }
        });
        
            
        
    }
    private void cargaControles()
    {
        int posX = 25;
        int posY = 25;
        int anchoControles = 30;
        int altoControles = 30;
        
        botones = new JButton[nFilas][nColumnas];
        for(int i = 0 ; i < botones.length ; i++)
        {
            for(int j = 0 ; j < botones[i].length ; j++)
            {
                botones[i][j] = new JButton();
                botones[i][j].setName(i+","+j);
                botones[i][j].setBorder(null);
                if(i == 0 && j == 0)
                {
                    botones[i][j].setBounds(posX, posY, anchoControles, 
                    altoControles);
                }
                else if(i == 0 && j != 0)
                {
                    botones[i][j].setBounds(botones[i][j-1].getX()+ botones[i][j-1].getWidth(),posY, anchoControles, 
                    altoControles);
                }
                else
                {
                    botones[i][j].setBounds(botones[i-1][j].getX(),botones[i-1][j].getY()+ botones[i-1][j].getHeight(), anchoControles, 
                    altoControles);
                }
                botones[i][j].addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent e) 
                    {
                        btnClick(e);
                    }
                });
                        
                getContentPane().add(botones[i][j]);
            }
        }
    }
    private void btnClick(ActionEvent e)
    {
        JButton btn = (JButton)e.getSource();
        String[] coordenada = btn.getName().split(",");
        int posFila = Integer.parseInt(coordenada[0]);
        int posColumna = Integer.parseInt(coordenada[1]);
        //JOptionPane.showMessageDialog(rootPane, posFila+","+posColumna);
        tableroBuscaMinas.seleccionarCasilla(posFila, posColumna);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        DificultadFacil = new javax.swing.JMenuItem();
        DificultadMedia = new javax.swing.JMenuItem();
        DificultadDificil = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("Juego");

        DificultadFacil.setText("Facil");
        DificultadFacil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DificultadFacilActionPerformed(evt);
            }
        });
        jMenu1.add(DificultadFacil);

        DificultadMedia.setText("Media");
        DificultadMedia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DificultadMediaActionPerformed(evt);
            }
        });
        jMenu1.add(DificultadMedia);

        DificultadDificil.setText("Dificil");
        DificultadDificil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DificultadDificilActionPerformed(evt);
            }
        });
        jMenu1.add(DificultadDificil);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DificultadDificilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DificultadDificilActionPerformed
        this.nFilas = 15;
        this.nColumnas = 15;
        this.nMinas = 40;
        juegoNuevo();
    }//GEN-LAST:event_DificultadDificilActionPerformed

    private void DificultadMediaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DificultadMediaActionPerformed
        this.nFilas = 13;
        this.nColumnas = 13;
        this.nMinas = 25;
        juegoNuevo();
    }//GEN-LAST:event_DificultadMediaActionPerformed

    private void DificultadFacilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DificultadFacilActionPerformed
        this.nFilas = 10;
        this.nColumnas = 10;
        this.nMinas = 10;
        juegoNuevo();
    }//GEN-LAST:event_DificultadFacilActionPerformed

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
            java.util.logging.Logger.getLogger(FormatoBuscaMinas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormatoBuscaMinas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormatoBuscaMinas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormatoBuscaMinas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormatoBuscaMinas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem DificultadDificil;
    private javax.swing.JMenuItem DificultadFacil;
    private javax.swing.JMenuItem DificultadMedia;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    // End of variables declaration//GEN-END:variables
}
