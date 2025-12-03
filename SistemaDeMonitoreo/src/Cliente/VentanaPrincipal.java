
package Cliente;

import java.awt.CardLayout;

public class VentanaPrincipal extends javax.swing.JFrame {

    public static final String INICIO = "inicio";
    public static final String MONITOR = "monitor";
    public static final String HISTORICO = "historico";
    
    private VistaInicio vistaInicio;
    private VistaMonitor vistaMonitor;
    private VistaHistorico vistaHistorico;
    
    public VentanaPrincipal() {
        initComponents();
        this.getContentPane().setLayout(new java.awt.CardLayout());
        setLocationRelativeTo(null);  
        iniciarVistas();
    }

    private void iniciarVistas() {
        // Cargar la vista Inicio
        vistaInicio = new VistaInicio(this);
        vistaMonitor = new VistaMonitor(this);
        vistaHistorico = new VistaHistorico(this);

        // Agregar al CardLayout
        this.getContentPane().add(vistaInicio, INICIO);
        this.getContentPane().add(vistaMonitor, MONITOR);
        this.getContentPane().add(vistaHistorico, HISTORICO);

        // Mostrar la primera vista
        mostrarVista(INICIO);
    }
    
     public void mostrarVista(String vista) {
        CardLayout cl = (CardLayout) this.getContentPane().getLayout();
        cl.show(this.getContentPane(), vista);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
