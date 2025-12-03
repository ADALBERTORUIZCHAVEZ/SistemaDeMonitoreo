
package Cliente;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import com.fazecast.jSerialComm.SerialPort;

public class VistaMonitor extends javax.swing.JPanel {
    private VentanaPrincipal ventana;
    private ClienteSocket cliente;
    private boolean monitoreando = false;
    private Thread hiloDatos;
    
    private XYSeries serieX;
    private XYSeries serieY;
    private XYSeries serieZ;
    private XYSeriesCollection dataset;
    private JFreeChart chart;

    public VistaMonitor(VentanaPrincipal ventana) {
        initComponents();
        this.ventana = ventana;
        cliente = new ClienteSocket();
        cliente.conectar("localhost", 5000); 
        cargarPuertos();
        cmbPuertos.addActionListener(e -> {
            String puertoSeleccionado = (String) cmbPuertos.getSelectedItem();
            System.out.println("Puerto seleccionado: " + puertoSeleccionado);
        });
        SwingUtilities.invokeLater(() -> {
            inicializarGrafica();
        });
    }

    private void iniciarMonitoreo() {

    if (monitoreando) {
        JOptionPane.showMessageDialog(this, "El monitoreo ya está activo");
        return;
    }

    monitoreando = true;

    hiloDatos = new Thread(() -> {

        while (monitoreando) {
            try {
                // ======== SIMULACIÓN ESTILO ARDUINO ============
                int num_x = (int) (Math.random() * 101);
                int num_y = (int) (Math.random() * 101);
                int num_z = (int) (Math.random() * 101);

                String dataString = "x:" + num_x + ",y:" + num_y + ",z:" + num_z;

                System.out.println("[Simulado Arduino] " + dataString);

                // ======== PARSEAR STRING ESTILO ARDUINO ============
                String[] partes = dataString.split(",");

                int x = Integer.parseInt(partes[0].split(":")[1]);
                int y = Integer.parseInt(partes[1].split(":")[1]);
                int z = Integer.parseInt(partes[2].split(":")[1]);
                
                // Actualizar gráfica
                serieX.add(System.currentTimeMillis() / 1000, x);
                serieY.add(System.currentTimeMillis() / 1000, y);
                serieZ.add(System.currentTimeMillis() / 1000, z);

                // ======== FECHA Y HORA ========
                LocalDateTime ahora = LocalDateTime.now();
                String fecha = ahora.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String hora = ahora.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

                // ======== MENSAJE JSON HACIA SERVIDOR ========
                String json = MensajeCliente.crearMensajeInsertar(x, y, z, fecha, hora);

                String respuesta = cliente.enviarMensaje(json);

                System.out.println("[Servidor] Respuesta: " + respuesta);

                Thread.sleep(1000);

            } catch (Exception e) {
                System.out.println("Error en monitoreo: " + e.getMessage());
            }
        }

    });

    hiloDatos.start();
}
     // -------------------------
    //    MÉTODO PARA DETENER
    // -------------------------
    private void detenerMonitoreo() {
        monitoreando = false;

        if (hiloDatos != null) {
            hiloDatos.interrupt();
        }

        JOptionPane.showMessageDialog(this, "Monitoreo detenido");
    }
    
    private void inicializarGrafica() {

    // Crear las series
    serieX = new XYSeries("X");
    serieY = new XYSeries("Y");
    serieZ = new XYSeries("Z");

    dataset = new XYSeriesCollection();
    dataset.addSeries(serieX);
    dataset.addSeries(serieY);
    dataset.addSeries(serieZ);

    // Crear gráfica de líneas
    chart = ChartFactory.createXYLineChart(
            "Datos del Sensor",
            "Tiempo (s)",
            "Valor",
            dataset
    );

    // Meter la gráfica dentro del panel
    ChartPanel chartPanel = new ChartPanel(chart);
    chartPanel.setPreferredSize(panelGrafica.getSize());

    panelGrafica.setLayout(new java.awt.BorderLayout());
    panelGrafica.removeAll();
    panelGrafica.add(chartPanel, java.awt.BorderLayout.CENTER);
    panelGrafica.validate();
    panelGrafica.repaint();
}
    
    private void cargarPuertos() {
    cmbPuertos.removeAllItems();
    cmbPuertos.addItem("Simulación");

    SerialPort[] puertos = SerialPort.getCommPorts();
    for (SerialPort p : puertos) {
        cmbPuertos.addItem(p.getSystemPortName());
    }
}
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnVolver = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnIniciar = new javax.swing.JButton();
        btnDetener = new javax.swing.JButton();
        cmbPuertos = new javax.swing.JComboBox<>();
        panelGrafica = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(0, 82, 158));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("MONITOR EN TIEMPO REAL");

        btnVolver.setBackground(new java.awt.Color(0, 192, 248));
        btnVolver.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnVolver.setForeground(new java.awt.Color(255, 255, 255));
        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(btnVolver)
                .addGap(105, 105, 105)
                .addComponent(jLabel1)
                .addContainerGap(169, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnVolver))
                .addGap(13, 13, 13))
        );

        jPanel2.setBackground(new java.awt.Color(248, 187, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        btnIniciar.setBackground(new java.awt.Color(0, 192, 248));
        btnIniciar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnIniciar.setForeground(new java.awt.Color(255, 255, 255));
        btnIniciar.setText("Iniciar");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        btnDetener.setBackground(new java.awt.Color(0, 192, 248));
        btnDetener.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDetener.setForeground(new java.awt.Color(255, 255, 255));
        btnDetener.setText("Detener");
        btnDetener.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetenerActionPerformed(evt);
            }
        });

        cmbPuertos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbPuertos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbPuertos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPuertosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelGraficaLayout = new javax.swing.GroupLayout(panelGrafica);
        panelGrafica.setLayout(panelGraficaLayout);
        panelGraficaLayout.setHorizontalGroup(
            panelGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelGraficaLayout.setVerticalGroup(
            panelGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 309, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelGrafica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnIniciar)
                        .addGap(44, 44, 44)
                        .addComponent(btnDetener)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbPuertos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIniciar)
                    .addComponent(btnDetener)
                    .addComponent(cmbPuertos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(panelGrafica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        // TODO add your handling code here:
        iniciarMonitoreo();
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void btnDetenerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetenerActionPerformed
        // TODO add your handling code here:
        detenerMonitoreo();
    }//GEN-LAST:event_btnDetenerActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        // TODO add your handling code here:
        ventana.mostrarVista(VentanaPrincipal.INICIO);
    }//GEN-LAST:event_btnVolverActionPerformed

    private void cmbPuertosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPuertosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPuertosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDetener;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox<String> cmbPuertos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel panelGrafica;
    // End of variables declaration//GEN-END:variables
}
