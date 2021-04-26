package com.istloja.vistas;

import com.istloja.controlador.Borradordb;
import com.istloja.controlador.NuevaContraseñadb;
import com.istloja.modelo.Borrador;
import com.istloja.modelo.NuevaContraseña;
import com.istloja.modeloTablas.Comunicacion;
import com.istloja.modeloTablas.ModeloTablaBorrador;
import com.istloja.modeloTablas.ModeloTablaContraseñas;
import com.istloja.placeholder.TextPrompt1;
import com.istloja.Utilidades;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Principal extends javax.swing.JFrame implements Comunicacion {

    private final Utilidades utilidades;
    private NuevaContraseña nuevaContraseña;
    private final NuevaContraseñadb controladorContraseña;
    private final GestionNuevaContraseña gestionContraseña;
    private Borrador nuevoBorrador;
    private final Borradordb controladorBorrador;
    private final GestionNuevoBorrador gestionBorrador;

    private final ModeloTablaContraseñas modeloTablaContraseñas;
    private final ModeloTablaBorrador modeloTablaContraseñasNoGuardadas;

    public Principal() {
        super("SISTEMA DE GESTIÓN DE CONTRASEÑAS");

        controladorContraseña = new NuevaContraseñadb();
        controladorBorrador = new Borradordb();

        modeloTablaContraseñas = new ModeloTablaContraseñas(controladorContraseña.obtenerContraseñasGuardadas(), this);
        modeloTablaContraseñasNoGuardadas = new ModeloTablaBorrador(controladorBorrador.obtenerContraseñasNoGuardadas(), this);

        initComponents();
        jFrame1.setLocationRelativeTo(null);
        utilidades = new Utilidades();

        btn_editar.setEnabled(false);
        btn_eliminar.setEnabled(false);

        gestionBorrador = new GestionNuevoBorrador(jtxt_contraseña, jtxt_repetirContraseña, jcb_tipoContraseña, nombre_SitioApp, jtxt_propietario, utilidades, this);
        gestionContraseña = new GestionNuevaContraseña(jtxt_contraseña, jtxt_repetirContraseña, jcb_tipoContraseña, nombre_SitioApp, jtxt_propietario, utilidades, this);

        TextPrompt1 prueba = new TextPrompt1("Colocar la contraseña ", jtxt_contraseña);
        TextPrompt1 prueba1 = new TextPrompt1("Repita nuevamente la contraseña ", jtxt_repetirContraseña);
        TextPrompt1 prueba2 = new TextPrompt1("Sitio web o aplicación ", nombre_SitioApp);
        TextPrompt1 prueba0 = new TextPrompt1("Aquí va el propietario de la contraseña ", jtxt_propietario);
        TextPrompt1 prueba3 = new TextPrompt1("Aquí se mostrará su contraseña", jtxt_gnerar);
        TextPrompt1 prueba4 = new TextPrompt1("Ingrese datos para buscar", txtParametroBusquedaGuardado);
        TextPrompt1 prueba5 = new TextPrompt1("Ingrese datos para buscar", txtParametroBusquedaNoGuardado);
        TextPrompt1 prueba6 = new TextPrompt1("Aquí se mostrará su contraseña a validar", jtxt_contraseña_calificar);

    }

    public void desbloquear() {
        btn_editar.setEnabled(true);
        btn_eliminar.setEnabled(true);
    }

    public void desbloquear2() {
        btn_guardarContraseña.setEnabled(true);
        btn_guardar_borradores.setEnabled(true);
    }

    public void bloquear() {
        btn_editar.setEnabled(false);
        btn_eliminar.setEnabled(false);
    }

    public void bloquear2() {
        btn_guardarContraseña.setEnabled(false);
        btn_guardar_borradores.setEnabled(false);
    }

    public void ultimaContraseña() {
        List<NuevaContraseña> obtenerContraseña = controladorContraseña.obtenerContraseñasGuardadas();
        NuevaContraseña contraseñas = obtenerContraseña.get(obtenerContraseña.size() - 1);
        System.out.println(contraseñas);
        jtxt_contraseña.setText(contraseñas.getContraseña());
        jtxt_repetirContraseña.setText(jtxt_contraseña.getText());
        jcb_tipoContraseña.setSelectedIndex(Integer.parseInt(contraseñas.getTipoContraseña()));
        nombre_SitioApp.setText(contraseñas.getNombreSitioApp());
        jtxt_propietario.setText(contraseñas.getPropietario());

    }

    public void aleatorio() {

        String LetrasMay, LetrasMin, CaracteresEsp, numeros, resultado = "";

        LetrasMay = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        LetrasMin = "abcdefghijklmnñopqrstuvwxyz";
        CaracteresEsp = "+-*/=%&#!^~|<>([]{}:;.,";
        numeros = "1234567890";

        String numeroCaracteres = String.valueOf(js_mayusculas.getValue());
        resultado = utilidades.aleatorio(resultado, LetrasMay, Integer.parseInt(numeroCaracteres));

        String numeroCaracteres2 = String.valueOf(js_minusculas.getValue());
        resultado = utilidades.aleatorio(resultado, LetrasMin, Integer.parseInt(numeroCaracteres2));

        String numeroCaracteres3 = String.valueOf(js_caracteres.getValue());
        resultado = utilidades.aleatorio(resultado, CaracteresEsp, Integer.parseInt(numeroCaracteres3));

        String numeroCaracteres4 = String.valueOf(js_numeros.getValue());
        resultado = utilidades.aleatorio(resultado, numeros, Integer.parseInt(numeroCaracteres4));

        System.out.println("" + resultado);

        String arrayPalabras[] = resultado.split("");
        Collections.shuffle(Arrays.asList(arrayPalabras));
        resultado = "";

        for (int i = 0; arrayPalabras.length > i; i++) {
            resultado = resultado + arrayPalabras[i];

        }
        System.out.println("" + resultado);
        jtxt_gnerar.setText(resultado);
    }

    public void esFuerte(String contraseña) {
        String resultado = "Fuerte";    // Resultado de password valido
        String valido = "";

        int length = 0;                     // Almacenamos numero de caracteres en el pass
        int numCount = 0;                   // Variable usada para almacenar numeros en el password
        int capCount = 0;                   // Variable usada para almacenar mayusculas en el password
        int capSignos = 0;                  // Variable usada para almacenar los signos
        int capMin = 0;

        for (int x = 0; x < contraseña.length(); x++) {
            if ((contraseña.charAt(x) >= 47 && contraseña.charAt(x) <= 58) //numeros
                    || (contraseña.charAt(x) >= 64 && contraseña.charAt(x) <= 91) //mayusculas
                    || (contraseña.charAt(x) >= 32 && contraseña.charAt(x) <= 44) //signos
                    || (contraseña.charAt(x) >= 97 && contraseña.charAt(x) <= 122)) {  //minusculas

            }
            if ((contraseña.charAt(x) > 32 && contraseña.charAt(x) < 44)) { // Cuenta la cantidad signos
                capSignos++;
            }
            if ((contraseña.charAt(x) > 47 && contraseña.charAt(x) < 58)) { // Cuenta la cantidad de numero
                numCount++;
            }

            if ((contraseña.charAt(x) > 64 && contraseña.charAt(x) < 91)) { // Cuenta la cantidad de mayuscula
                capCount++;
            }
            if ((contraseña.charAt(x) >= 97 && contraseña.charAt(x) <= 122)) {
                capMin++;
            }

            length = (x + 1); // Cuenta la longitud del password

        } // Final del bucle

        if (capSignos < 1) {// Revisa la longitud minima de 8 caracteres del password
            resultado = "Débil";
            valido = "Su contraseña no tiene carácteres especiales";
        }
        if (numCount < 1) { // Revisa que el password contenga minimo 1 numero
            resultado = "Débil";
            valido = "Coloque al menos un número";
        }

        if (capCount < 1) {   // Revisa que el password contenga minimo 1 mayuscula
            resultado = "Débil";
            valido = "Coloque al menos una mayúscula";
        }
        if (capMin < 1) {   // Revisa que el password contenga minimo 1 mayuscula
            resultado = "Débil";
            valido = "Coloque al menos una minúscula";
        }

        if (length < 8) {   // Revisa la longitud minima de 8 caracteres del password
            resultado = "Débil";
            valido = "Su contraseña al menos debe tener 8 carácteres";
        }
        texto1.setText(valido);
        texto.setText(resultado);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar1 = new java.awt.MenuBar();
        menu1 = new java.awt.Menu();
        menu2 = new java.awt.Menu();
        menuBar2 = new java.awt.MenuBar();
        menu3 = new java.awt.Menu();
        menu4 = new java.awt.Menu();
        menuBar3 = new java.awt.MenuBar();
        menu5 = new java.awt.Menu();
        menu6 = new java.awt.Menu();
        jToggleButton1 = new javax.swing.JToggleButton();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jPopupMenu3 = new javax.swing.JPopupMenu();
        jFrame1 = new javax.swing.JFrame();
        jPopupMenu4 = new javax.swing.JPopupMenu();
        canvas1 = new java.awt.Canvas();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenuBar4 = new javax.swing.JMenuBar();
        jMenu7 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jMenuBar5 = new javax.swing.JMenuBar();
        jMenu9 = new javax.swing.JMenu();
        jMenu10 = new javax.swing.JMenu();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        scrollPane1 = new java.awt.ScrollPane();
        jLabel38 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        panel1 = new java.awt.Panel();
        jPanel7 = new javax.swing.JPanel();
        jScrollBar1 = new javax.swing.JScrollBar();
        jFrame2 = new javax.swing.JFrame();
        jDialog1 = new javax.swing.JDialog();
        jDialog2 = new javax.swing.JDialog();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel9 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        Inicio = new javax.swing.JTabbedPane();
        jp_Inicio = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        Registrar_una_Nueva_Contraseña = new javax.swing.JButton();
        Generar_una_contraseña = new javax.swing.JButton();
        Lista_de_contraseñas = new javax.swing.JButton();
        Calificador_de_contraseña_segura = new javax.swing.JButton();
        Contraseñas_no_guardadas = new javax.swing.JButton();
        Salir_a_Login = new javax.swing.JButton();
        Salir_del_sistema = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jp_guardarContraseña = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btn_limpiar = new javax.swing.JButton();
        btn_guardarContraseña = new javax.swing.JButton();
        btn_guardar_borradores = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jtxt_contraseña = new javax.swing.JPasswordField();
        jtxt_repetirContraseña = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        nombre_SitioApp = new javax.swing.JTextField();
        jcb_tipoContraseña = new javax.swing.JComboBox<>();
        ver_Contraseña = new javax.swing.JCheckBox();
        ver_repetir_contraseña = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jtxt_propietario = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jp_generadorContraseñas = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        btn_limpiar_contraseña_generada = new javax.swing.JButton();
        btn_Exportar = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        btn_repetir = new javax.swing.JButton();
        js_mayusculas = new javax.swing.JSpinner();
        js_minusculas = new javax.swing.JSpinner();
        js_caracteres = new javax.swing.JSpinner();
        js_numeros = new javax.swing.JSpinner();
        jtxt_gnerar = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jp_listaContraseñas = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtParametroBusquedaGuardado = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaContraseña = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jcb_mostrarContraseñas = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jp_calificadorContraseñas = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        btn_limpiar_calificador = new javax.swing.JButton();
        texto = new javax.swing.JLabel();
        btn_comprobar = new javax.swing.JButton();
        jch_verCalificador = new javax.swing.JCheckBox();
        jtxt_contraseña_calificar = new javax.swing.JPasswordField();
        texto1 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jp_borradores = new javax.swing.JPanel();
        txtParametroBusquedaNoGuardado = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jcb_mostrar_borrador = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        acerca_de = new javax.swing.JMenu();
        jm_Salir = new javax.swing.JMenu();

        menu1.setLabel("File");
        menuBar1.add(menu1);

        menu2.setLabel("Edit");
        menuBar1.add(menu2);

        menu3.setLabel("File");
        menuBar2.add(menu3);

        menu4.setLabel("Edit");
        menuBar2.add(menu4);

        menu5.setLabel("File");
        menuBar3.add(menu5);

        menu6.setLabel("Edit");
        menuBar3.add(menu6);

        jToggleButton1.setText("jToggleButton1");

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        jMenu5.setText("File");
        jMenuBar3.add(jMenu5);

        jMenu6.setText("Edit");
        jMenuBar3.add(jMenu6);

        jMenu7.setText("File");
        jMenuBar4.add(jMenu7);

        jMenu8.setText("Edit");
        jMenuBar4.add(jMenu8);

        jMenu9.setText("File");
        jMenuBar5.add(jMenu9);

        jMenu10.setText("Edit");
        jMenuBar5.add(jMenu10);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 970, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 518, Short.MAX_VALUE)
        );

        jLabel39.setText("jLabel39");

        jLabel38.setText("jLabel38");
        scrollPane1.add(jLabel38);

        jLabel37.setText("jLabel37");
        scrollPane1.add(jLabel37);

        jLabel36.setText("jLabel36");
        scrollPane1.add(jLabel36);

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 970, Short.MAX_VALUE)
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 518, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

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

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        jLabel23.setText("jLabel23");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(347, 347, 347)
                .addComponent(jLabel23)
                .addContainerGap(575, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel23)
                .addContainerGap(654, Short.MAX_VALUE))
        );

        jScrollPane3.setViewportView(jPanel9);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 947, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 518, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        Inicio.setBackground(new java.awt.Color(130, 224, 170));
        Inicio.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jp_Inicio.setBackground(new java.awt.Color(102, 204, 255));
        jp_Inicio.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel24.setBackground(new java.awt.Color(255, 255, 255));
        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("SISTEMA DE GESTIÓN DE CONTRASEÑAS");
        jp_Inicio.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 740, -1));

        Registrar_una_Nueva_Contraseña.setBackground(new java.awt.Color(255, 255, 255));
        Registrar_una_Nueva_Contraseña.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Registrar_una_Nueva_Contraseña.setForeground(new java.awt.Color(102, 204, 255));
        Registrar_una_Nueva_Contraseña.setText("Registrar una Nueva Contraseña");
        Registrar_una_Nueva_Contraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Registrar_una_Nueva_ContraseñaActionPerformed(evt);
            }
        });
        jp_Inicio.add(Registrar_una_Nueva_Contraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, -1, -1));

        Generar_una_contraseña.setBackground(new java.awt.Color(255, 255, 255));
        Generar_una_contraseña.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Generar_una_contraseña.setForeground(new java.awt.Color(102, 204, 255));
        Generar_una_contraseña.setText("Generar una contraseña");
        Generar_una_contraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Generar_una_contraseñaActionPerformed(evt);
            }
        });
        jp_Inicio.add(Generar_una_contraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 350, -1, -1));

        Lista_de_contraseñas.setBackground(new java.awt.Color(255, 255, 255));
        Lista_de_contraseñas.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Lista_de_contraseñas.setForeground(new java.awt.Color(102, 204, 255));
        Lista_de_contraseñas.setText("Lista de contraseñas");
        Lista_de_contraseñas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Lista_de_contraseñasActionPerformed(evt);
            }
        });
        jp_Inicio.add(Lista_de_contraseñas, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 350, -1, -1));

        Calificador_de_contraseña_segura.setBackground(new java.awt.Color(255, 255, 255));
        Calificador_de_contraseña_segura.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Calificador_de_contraseña_segura.setForeground(new java.awt.Color(102, 204, 255));
        Calificador_de_contraseña_segura.setText("Calificador de contraseña segura");
        Calificador_de_contraseña_segura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Calificador_de_contraseña_seguraActionPerformed(evt);
            }
        });
        jp_Inicio.add(Calificador_de_contraseña_segura, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 210, -1, -1));

        Contraseñas_no_guardadas.setBackground(new java.awt.Color(255, 255, 255));
        Contraseñas_no_guardadas.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Contraseñas_no_guardadas.setForeground(new java.awt.Color(102, 204, 255));
        Contraseñas_no_guardadas.setText("Contraseñas no guardadas");
        Contraseñas_no_guardadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Contraseñas_no_guardadasActionPerformed(evt);
            }
        });
        jp_Inicio.add(Contraseñas_no_guardadas, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 210, -1, -1));

        Salir_a_Login.setBackground(new java.awt.Color(255, 255, 255));
        Salir_a_Login.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Salir_a_Login.setForeground(new java.awt.Color(102, 204, 255));
        Salir_a_Login.setText("Salir a Login");
        Salir_a_Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Salir_a_LoginActionPerformed(evt);
            }
        });
        jp_Inicio.add(Salir_a_Login, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 350, 140, -1));

        Salir_del_sistema.setBackground(new java.awt.Color(255, 255, 255));
        Salir_del_sistema.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        Salir_del_sistema.setForeground(new java.awt.Color(102, 204, 255));
        Salir_del_sistema.setText("Salir del sistema");
        Salir_del_sistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Salir_del_sistemaActionPerformed(evt);
            }
        });
        jp_Inicio.add(Salir_del_sistema, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 350, -1, -1));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/portapapeles (1).png"))); // NOI18N
        jp_Inicio.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, -1, -1));

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/portapapeles (3).png"))); // NOI18N
        jp_Inicio.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 140, -1, -1));

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/candado.png"))); // NOI18N
        jp_Inicio.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 140, -1, -1));

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/crear.png"))); // NOI18N
        jp_Inicio.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, -1, -1));

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/lista-de-quehaceres.png"))); // NOI18N
        jp_Inicio.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 280, -1, -1));

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/salida.png"))); // NOI18N
        jp_Inicio.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 280, -1, -1));

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/login.png"))); // NOI18N
        jp_Inicio.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 280, -1, -1));

        jLabel26.setBackground(new java.awt.Color(255, 255, 255));
        jLabel26.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText(" Bienvenido querido usuario al menú de su sistema");
        jp_Inicio.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, -1, -1));

        Inicio.addTab("Inicio", jp_Inicio);

        jp_guardarContraseña.setBackground(new java.awt.Color(102, 204, 255));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Guardar contraseñas");

        btn_limpiar.setBackground(new java.awt.Color(102, 204, 255));
        btn_limpiar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btn_limpiar.setForeground(new java.awt.Color(255, 255, 255));
        btn_limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/limpiar.png"))); // NOI18N
        btn_limpiar.setText("Limpiar");
        btn_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limpiarActionPerformed(evt);
            }
        });

        btn_guardarContraseña.setBackground(new java.awt.Color(102, 204, 255));
        btn_guardarContraseña.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btn_guardarContraseña.setForeground(new java.awt.Color(255, 255, 255));
        btn_guardarContraseña.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/disquete.png"))); // NOI18N
        btn_guardarContraseña.setText("Guardar");
        btn_guardarContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarContraseñaActionPerformed(evt);
            }
        });

        btn_guardar_borradores.setBackground(new java.awt.Color(102, 204, 255));
        btn_guardar_borradores.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btn_guardar_borradores.setForeground(new java.awt.Color(255, 255, 255));
        btn_guardar_borradores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/disco-flexible.png"))); // NOI18N
        btn_guardar_borradores.setText("Guardar en borradores");
        btn_guardar_borradores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardar_borradoresActionPerformed(evt);
            }
        });

        btn_editar.setBackground(new java.awt.Color(102, 204, 255));
        btn_editar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btn_editar.setForeground(new java.awt.Color(255, 255, 255));
        btn_editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/editar.png"))); // NOI18N
        btn_editar.setText("Editar");
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });

        btn_eliminar.setBackground(new java.awt.Color(102, 204, 255));
        btn_eliminar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btn_eliminar.setForeground(new java.awt.Color(255, 255, 255));
        btn_eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/eliminar.png"))); // NOI18N
        btn_eliminar.setText("Eliminar");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(150, 232, 240));
        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText(" Tipo de contraseña: ");
        jLabel3.setOpaque(true);

        jLabel4.setBackground(new java.awt.Color(150, 232, 240));
        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText(" Nombre del sitio web o aplicación: ");
        jLabel4.setOpaque(true);

        jtxt_contraseña.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jtxt_contraseña.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jtxt_contraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxt_contraseñaKeyTyped(evt);
            }
        });

        jtxt_repetirContraseña.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jtxt_repetirContraseña.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jtxt_repetirContraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtxt_repetirContraseñaKeyTyped(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(150, 232, 240));
        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText(" Repita la contraseña: ");
        jLabel5.setOpaque(true);

        nombre_SitioApp.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        nombre_SitioApp.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jcb_tipoContraseña.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jcb_tipoContraseña.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sitio web", "Aplicación" }));

        ver_Contraseña.setText("Ver");
        ver_Contraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ver_ContraseñaActionPerformed(evt);
            }
        });

        ver_repetir_contraseña.setText("Ver");
        ver_repetir_contraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ver_repetir_contraseñaActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(150, 232, 240));
        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText(" Contraseña:");
        jLabel2.setOpaque(true);

        jLabel25.setBackground(new java.awt.Color(150, 232, 240));
        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText(" Nombre del sitio web o aplicación: ");
        jLabel25.setOpaque(true);

        jLabel28.setBackground(new java.awt.Color(150, 232, 240));
        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText(" Nombre del sitio web o aplicación: ");
        jLabel28.setOpaque(true);

        jLabel35.setBackground(new java.awt.Color(150, 232, 240));
        jLabel35.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText(" Nombre del sitio web o aplicación: ");
        jLabel35.setOpaque(true);

        jtxt_propietario.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jtxt_propietario.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel40.setBackground(new java.awt.Color(150, 232, 240));
        jLabel40.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText(" Propietario de la contraseña: ");
        jLabel40.setOpaque(true);

        javax.swing.GroupLayout jp_guardarContraseñaLayout = new javax.swing.GroupLayout(jp_guardarContraseña);
        jp_guardarContraseña.setLayout(jp_guardarContraseñaLayout);
        jp_guardarContraseñaLayout.setHorizontalGroup(
            jp_guardarContraseñaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_guardarContraseñaLayout.createSequentialGroup()
                .addGroup(jp_guardarContraseñaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_guardarContraseñaLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jp_guardarContraseñaLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(jtxt_contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(ver_Contraseña))
                    .addGroup(jp_guardarContraseñaLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(jtxt_repetirContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(ver_repetir_contraseña))
                    .addGroup(jp_guardarContraseñaLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(jcb_tipoContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jp_guardarContraseñaLayout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addComponent(btn_guardarContraseña)
                        .addGap(13, 13, 13)
                        .addComponent(btn_guardar_borradores)
                        .addGap(21, 21, 21)
                        .addComponent(btn_limpiar)
                        .addGap(27, 27, 27)
                        .addComponent(btn_editar)
                        .addGap(17, 17, 17)
                        .addComponent(btn_eliminar))
                    .addGroup(jp_guardarContraseñaLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(jp_guardarContraseñaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(62, 62, 62)
                        .addGroup(jp_guardarContraseñaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nombre_SitioApp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jtxt_propietario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(48, 48, 48))
        );
        jp_guardarContraseñaLayout.setVerticalGroup(
            jp_guardarContraseñaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_guardarContraseñaLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jp_guardarContraseñaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxt_contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ver_Contraseña))
                .addGap(14, 14, 14)
                .addGroup(jp_guardarContraseñaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxt_repetirContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jp_guardarContraseñaLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(ver_repetir_contraseña)))
                .addGap(15, 15, 15)
                .addGroup(jp_guardarContraseñaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcb_tipoContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jp_guardarContraseñaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombre_SitioApp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jp_guardarContraseñaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jtxt_propietario)
                    .addComponent(jLabel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(50, 50, 50)
                .addGroup(jp_guardarContraseñaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_guardarContraseña)
                    .addComponent(btn_guardar_borradores)
                    .addComponent(btn_limpiar)
                    .addComponent(btn_editar)
                    .addComponent(btn_eliminar)))
        );

        Inicio.addTab("Guardar contraseña", jp_guardarContraseña);

        jp_generadorContraseñas.setBackground(new java.awt.Color(102, 204, 255));
        jp_generadorContraseñas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Generador de contraseñas");
        jp_generadorContraseñas.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 28, 431, -1));

        btn_limpiar_contraseña_generada.setBackground(new java.awt.Color(102, 204, 255));
        btn_limpiar_contraseña_generada.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btn_limpiar_contraseña_generada.setForeground(new java.awt.Color(255, 255, 255));
        btn_limpiar_contraseña_generada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/limpiar.png"))); // NOI18N
        btn_limpiar_contraseña_generada.setText("Limpiar");
        btn_limpiar_contraseña_generada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limpiar_contraseña_generadaActionPerformed(evt);
            }
        });
        jp_generadorContraseñas.add(btn_limpiar_contraseña_generada, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 330, 105, -1));

        btn_Exportar.setBackground(new java.awt.Color(102, 204, 255));
        btn_Exportar.setForeground(new java.awt.Color(255, 255, 255));
        btn_Exportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/archivo-txt.png"))); // NOI18N
        btn_Exportar.setText("Exportar");
        btn_Exportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ExportarActionPerformed(evt);
            }
        });
        jp_generadorContraseñas.add(btn_Exportar, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 330, 111, -1));

        jLabel13.setBackground(new java.awt.Color(153, 204, 255));
        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText(" Su contraseña es: ");
        jLabel13.setOpaque(true);
        jp_generadorContraseñas.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 263, -1));

        btn_repetir.setBackground(new java.awt.Color(102, 204, 255));
        btn_repetir.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btn_repetir.setForeground(new java.awt.Color(255, 255, 255));
        btn_repetir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/repetir.png"))); // NOI18N
        btn_repetir.setText("Generar ");
        btn_repetir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_repetirActionPerformed(evt);
            }
        });
        jp_generadorContraseñas.add(btn_repetir, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 260, 100, 28));

        js_mayusculas.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        js_mayusculas.setModel(new javax.swing.SpinnerNumberModel(0, 0, 5, 1));
        jp_generadorContraseñas.add(js_mayusculas, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 564, -1));

        js_minusculas.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        js_minusculas.setModel(new javax.swing.SpinnerNumberModel(0, null, 5, 1));
        jp_generadorContraseñas.add(js_minusculas, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, 564, -1));

        js_caracteres.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        js_caracteres.setModel(new javax.swing.SpinnerNumberModel(0, null, 4, 1));
        jp_generadorContraseñas.add(js_caracteres, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 170, 564, -1));

        js_numeros.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        js_numeros.setModel(new javax.swing.SpinnerNumberModel(0, null, 4, 1));
        jp_generadorContraseñas.add(js_numeros, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 220, 564, -1));

        jtxt_gnerar.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jp_generadorContraseñas.add(jtxt_gnerar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 260, 450, -1));

        jLabel9.setBackground(new java.awt.Color(153, 204, 255));
        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText(" Número de mayúsculas: ");
        jLabel9.setOpaque(true);
        jp_generadorContraseñas.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 263, 28));

        jLabel10.setBackground(new java.awt.Color(153, 204, 255));
        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText(" Número de minúsculas: ");
        jLabel10.setOpaque(true);
        jp_generadorContraseñas.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 263, 28));

        jLabel11.setBackground(new java.awt.Color(153, 204, 255));
        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText(" Número de carácteres especiales: ");
        jLabel11.setOpaque(true);
        jp_generadorContraseñas.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, 28));

        jLabel12.setBackground(new java.awt.Color(153, 204, 255));
        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText(" Cantidad de números: ");
        jLabel12.setOpaque(true);
        jp_generadorContraseñas.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 263, 28));

        Inicio.addTab("Generador de contraseñas", jp_generadorContraseñas);

        jp_listaContraseñas.setBackground(new java.awt.Color(102, 204, 255));
        jp_listaContraseñas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/lupa.png"))); // NOI18N
        jp_listaContraseñas.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(691, 53, -1, -1));

        txtParametroBusquedaGuardado.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtParametroBusquedaGuardado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtParametroBusquedaGuardadoKeyReleased(evt);
            }
        });
        jp_listaContraseñas.add(txtParametroBusquedaGuardado, new org.netbeans.lib.awtextra.AbsoluteConstraints(727, 53, 194, 28));

        tablaContraseña.setModel(modeloTablaContraseñas
        );
        jScrollPane2.setViewportView(tablaContraseña);

        jp_listaContraseñas.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 99, 894, 190));

        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText(" Para editar o eliminar un registro, de click sobre el dos veces ");
        jp_listaContraseñas.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 894, -1));

        jcb_mostrarContraseñas.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jcb_mostrarContraseñas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mostrar todo", "Mostrar por última fecha", "Mostrar por fechas pasadas", "Mostrar solo por sitio web", "Mostrar solo por aplicación" }));
        jcb_mostrarContraseñas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcb_mostrarContraseñasActionPerformed(evt);
            }
        });
        jp_listaContraseñas.add(jcb_mostrarContraseñas, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 330, 175, -1));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/embudo.png"))); // NOI18N
        jLabel16.setText("Filtro de búsqueda");
        jp_listaContraseñas.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 330, -1, -1));

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/lista-de-verificacion.png"))); // NOI18N
        jLabel17.setText("Lista de contraseñas");
        jp_listaContraseñas.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 53, -1, -1));

        Inicio.addTab("Contraseñas", jp_listaContraseñas);

        jp_calificadorContraseñas.setBackground(new java.awt.Color(102, 204, 255));
        jp_calificadorContraseñas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText(" Calificador de contraseñas ");
        jp_calificadorContraseñas.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 42, 445, -1));

        btn_limpiar_calificador.setBackground(new java.awt.Color(45, 208, 240));
        btn_limpiar_calificador.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btn_limpiar_calificador.setForeground(new java.awt.Color(255, 255, 255));
        btn_limpiar_calificador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/limpiar.png"))); // NOI18N
        btn_limpiar_calificador.setText("Limpiar");
        btn_limpiar_calificador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limpiar_calificadorActionPerformed(evt);
            }
        });
        jp_calificadorContraseñas.add(btn_limpiar_calificador, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 280, 125, -1));

        texto.setFont(new java.awt.Font("Times New Roman", 2, 18)); // NOI18N
        jp_calificadorContraseñas.add(texto, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, 139, 28));

        btn_comprobar.setForeground(new java.awt.Color(255, 255, 255));
        btn_comprobar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/comprobado.png"))); // NOI18N
        btn_comprobar.setText("Comprobar");
        btn_comprobar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_comprobarActionPerformed(evt);
            }
        });
        jp_calificadorContraseñas.add(btn_comprobar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 230, -1, -1));

        jch_verCalificador.setText("Ver");
        jch_verCalificador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jch_verCalificadorActionPerformed(evt);
            }
        });
        jp_calificadorContraseñas.add(jch_verCalificador, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 150, -1, -1));

        jtxt_contraseña_calificar.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jtxt_contraseña_calificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxt_contraseña_calificarActionPerformed(evt);
            }
        });
        jp_calificadorContraseñas.add(jtxt_contraseña_calificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 620, -1));
        jp_calificadorContraseñas.add(texto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 650, 575, 21));

        jLabel21.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Coloca la contraseña que quieras validar");
        jp_calificadorContraseñas.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 807, -1));

        jLabel22.setBackground(new java.awt.Color(150, 232, 240));
        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText(" Contraseña: ");
        jLabel22.setOpaque(true);
        jp_calificadorContraseñas.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, 28));

        Inicio.addTab("Calificador de contraseñas", jp_calificadorContraseñas);

        jp_borradores.setBackground(new java.awt.Color(102, 204, 255));
        jp_borradores.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtParametroBusquedaNoGuardado.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtParametroBusquedaNoGuardado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtParametroBusquedaNoGuardadoKeyReleased(evt);
            }
        });
        jp_borradores.add(txtParametroBusquedaNoGuardado, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 60, 200, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/lista-de-verificacion.png"))); // NOI18N
        jLabel6.setText("Lista de contraseñas no guardadas");
        jp_borradores.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 403, -1));

        jcb_mostrar_borrador.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jcb_mostrar_borrador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mostrar todo", "Mostrar por última fecha", "Mostrar por fechas pasadas", "Mostrar solo por sitio web", "Mostrar solo por aplicación" }));
        jcb_mostrar_borrador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcb_mostrar_borradorActionPerformed(evt);
            }
        });
        jp_borradores.add(jcb_mostrar_borrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 310, 214, -1));
        jcb_mostrar_borrador.getAccessibleContext().setAccessibleName("Filtrar búsqueda");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/embudo.png"))); // NOI18N
        jLabel7.setText("Filtro de búsqueda");
        jp_borradores.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 310, -1, -1));

        jTable1.setModel(modeloTablaContraseñasNoGuardadas);
        jScrollPane1.setViewportView(jTable1);

        jp_borradores.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 890, 170));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imágenes/lupa.png"))); // NOI18N
        jp_borradores.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 60, -1, 30));

        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText(" Para eliminar un registro, de click sobre el dos veces ");
        jp_borradores.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, -1, -1));

        Inicio.addTab("Borradores", jp_borradores);

        acerca_de.setText("Acerca de");
        acerca_de.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                acerca_deMouseClicked(evt);
            }
        });
        jMenuBar1.add(acerca_de);

        jm_Salir.setText("Salir");
        jm_Salir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jm_SalirMouseClicked(evt);
            }
        });
        jMenuBar1.add(jm_Salir);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Inicio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 952, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 471, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jm_SalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jm_SalirMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jm_SalirMouseClicked

    private void jcb_mostrar_borradorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcb_mostrar_borradorActionPerformed
        // TODO add your handling code here:
        if (jcb_mostrar_borrador.getSelectedItem().equals("Mostrar solo por sitio web")) {
            List<Borrador> contraseñaNombre = controladorBorrador.mostrarSitioWebBorrador("Sitio web");
            modeloTablaContraseñasNoGuardadas.setPersonas(contraseñaNombre);
            modeloTablaContraseñasNoGuardadas.fireTableDataChanged();

        } else if (jcb_mostrar_borrador.getSelectedItem().equals("Mostrar todo")) {
            List<Borrador> contraseñaNombre = controladorBorrador.obtenerContraseñasNoGuardadas();
            modeloTablaContraseñasNoGuardadas.setPersonas(contraseñaNombre);
            modeloTablaContraseñasNoGuardadas.fireTableDataChanged();

        } else if (jcb_mostrar_borrador.getSelectedItem().equals("Mostrar solo por aplicación")) {
            List<Borrador> contraseñaNombre = controladorBorrador.mostrarSitioWebBorrador("Aplicación");
            modeloTablaContraseñasNoGuardadas.setPersonas(contraseñaNombre);
            modeloTablaContraseñasNoGuardadas.fireTableDataChanged();

        } else if (jcb_mostrar_borrador.getSelectedItem().equals("Mostrar por última fecha")) {
            List<Borrador> contraseñaNombre = controladorBorrador.mostrarFecha_RecienteBorrador();
            modeloTablaContraseñasNoGuardadas.setPersonas(contraseñaNombre);
            modeloTablaContraseñasNoGuardadas.fireTableDataChanged();

        } else if (jcb_mostrar_borrador.getSelectedItem().equals("Mostrar por fechas pasadas")) {
            List<Borrador> contraseñaNombre = controladorBorrador.mostrarFecha_PasadaBorrador();
            modeloTablaContraseñasNoGuardadas.setPersonas(contraseñaNombre);
            modeloTablaContraseñasNoGuardadas.fireTableDataChanged();
        }
    }//GEN-LAST:event_jcb_mostrar_borradorActionPerformed

    private void txtParametroBusquedaNoGuardadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtParametroBusquedaNoGuardadoKeyReleased
        // TODO add your handling code here:
        List<Borrador> contraseñaNombre = controladorBorrador.getContraseñaNombreSitioApp(txtParametroBusquedaNoGuardado.getText());
        modeloTablaContraseñasNoGuardadas.setPersonas(contraseñaNombre);
        modeloTablaContraseñasNoGuardadas.fireTableDataChanged();
    }//GEN-LAST:event_txtParametroBusquedaNoGuardadoKeyReleased

    private void jcb_mostrarContraseñasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcb_mostrarContraseñasActionPerformed
        // TODO add your handling code here:
        if (jcb_mostrarContraseñas.getSelectedItem().equals("Mostrar solo por sitio web")) {
            List<NuevaContraseña> contraseñaNombre = controladorContraseña.mostrarSitioWeb("Sitio web");
            modeloTablaContraseñas.setPersonas(contraseñaNombre);
            modeloTablaContraseñas.fireTableDataChanged();

        } else if (jcb_mostrarContraseñas.getSelectedItem().equals("Mostrar todo")) {
            List<NuevaContraseña> contraseñaNombre = controladorContraseña.obtenerContraseñasGuardadas();
            modeloTablaContraseñas.setPersonas(contraseñaNombre);
            modeloTablaContraseñas.fireTableDataChanged();

        } else if (jcb_mostrarContraseñas.getSelectedItem().equals("Mostrar solo por aplicación")) {
            List<NuevaContraseña> contraseñaNombre = controladorContraseña.mostrarSitioWeb("Aplicación");
            modeloTablaContraseñas.setPersonas(contraseñaNombre);
            modeloTablaContraseñas.fireTableDataChanged();

        } else if (jcb_mostrarContraseñas.getSelectedItem().equals("Mostrar por última fecha")) {
            List<NuevaContraseña> contraseñaNombre = controladorContraseña.mostrarFecha_Reciente();
            modeloTablaContraseñas.setPersonas(contraseñaNombre);
            modeloTablaContraseñas.fireTableDataChanged();

        } else if (jcb_mostrarContraseñas.getSelectedItem().equals("Mostrar por fechas pasadas")) {
            List<NuevaContraseña> contraseñaNombre = controladorContraseña.mostrarFecha_Pasada();
            modeloTablaContraseñas.setPersonas(contraseñaNombre);
            modeloTablaContraseñas.fireTableDataChanged();
        }
    }//GEN-LAST:event_jcb_mostrarContraseñasActionPerformed

    private void txtParametroBusquedaGuardadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtParametroBusquedaGuardadoKeyReleased
        // TODO add your handling code here:
        List<NuevaContraseña> contraseñaNombre = controladorContraseña.getContraseñaNombreSitioApp(txtParametroBusquedaGuardado.getText());
        modeloTablaContraseñas.setPersonas(contraseñaNombre);
        modeloTablaContraseñas.fireTableDataChanged();
    }//GEN-LAST:event_txtParametroBusquedaGuardadoKeyReleased

    private void btn_ExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ExportarActionPerformed
        // TODO add your handling code here:
        //Creamos el objeto JFileChooser
        if (jtxt_gnerar.getText().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Por favor primero genere una contraseña para poder exportar.", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            JFileChooser fc = new JFileChooser();
            //Creamos un filtro
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.TXT", "txt");
            //Le indicamos el filtro
            fc.setFileFilter(filtro);
            //Abrimos la ventana, guardamos la opción selecionada por el usuario
            int seleccion = fc.showSaveDialog(this);
            //Sie el usuaio da click en aceptar
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                //Seleccionamos el fichero
                File fichero = fc.getSelectedFile();
                try (FileWriter fw = new FileWriter(fichero)) {
                    //Escribimos el texto en el fichero
                    fw.write(this.jtxt_gnerar.getText());
                    js_mayusculas.setValue(0);
                    js_minusculas.setValue(0);
                    js_caracteres.setValue(0);
                    js_numeros.setValue(0);
                    jtxt_gnerar.setText("");
                    JOptionPane.showMessageDialog(rootPane, "Tu contraseña fué guardada con éxito en tu PC.", "ÉXITO", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException el) {
                    el.getMessage();
                }
            }
        }
    }//GEN-LAST:event_btn_ExportarActionPerformed

    private void btn_limpiar_contraseña_generadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiar_contraseña_generadaActionPerformed
        // TODO add your handling code here:
        js_mayusculas.setValue(0);
        js_minusculas.setValue(0);
        js_caracteres.setValue(0);
        js_numeros.setValue(0);
        jtxt_gnerar.setText("");
        js_mayusculas.requestFocus();
    }//GEN-LAST:event_btn_limpiar_contraseña_generadaActionPerformed

    private void btn_repetirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_repetirActionPerformed
        // TODO add your handling code here:
        aleatorio();
    }//GEN-LAST:event_btn_repetirActionPerformed

    private void btn_guardar_borradoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardar_borradoresActionPerformed
        // TODO add your handling code here:
        Borrador borrador = gestionBorrador.guardarEditarContraseña(false);
        if (borrador != null) {
            if (!jtxt_repetirContraseña.getText().equals(jtxt_contraseña.getText())) {
                JOptionPane.showMessageDialog(rootPane, "La contraseñas deben ser iguales.", "ERROR", JOptionPane.ERROR_MESSAGE);
                jtxt_repetirContraseña.requestFocus();
            } else {
                if (controladorBorrador.crearBorrador(borrador)) {
                    JOptionPane.showMessageDialog(rootPane, "Tu contraseña fué guardada con éxito en borradores.", "ÉXITO", JOptionPane.INFORMATION_MESSAGE);
                    List<Borrador> contraseñaNombre = controladorBorrador.obtenerContraseñasNoGuardadas();
                    modeloTablaContraseñasNoGuardadas.setPersonas(contraseñaNombre);
                    modeloTablaContraseñasNoGuardadas.fireTableDataChanged();
                    gestionBorrador.limpiar();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "No se pudo guardar tu contraseña en borradores.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btn_guardar_borradoresActionPerformed

    private void btn_guardarContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarContraseñaActionPerformed
        //MÉTODOS*************************************************************************************************************************
        NuevaContraseña contraseñaEditar = gestionContraseña.guardarEditarContraseña(false);

        if (contraseñaEditar != null) {
            if (!jtxt_repetirContraseña.getText().equals(jtxt_contraseña.getText())) {
                JOptionPane.showMessageDialog(rootPane, "La contraseñas deben ser iguales.", "ERROR", JOptionPane.ERROR_MESSAGE);
                jtxt_repetirContraseña.requestFocus();
            } else {
                if (controladorContraseña.crearContraseña(contraseñaEditar)) {
                    JOptionPane.showMessageDialog(rootPane, "Tu contraseña fué guardada con éxito.", "ÉXITO", JOptionPane.INFORMATION_MESSAGE);
                    List<NuevaContraseña> contraseñaNombre = controladorContraseña.obtenerContraseñasGuardadas();
                    modeloTablaContraseñas.setPersonas(contraseñaNombre);
                    modeloTablaContraseñas.fireTableDataChanged();
                    gestionContraseña.limpiar();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "No se pudo guardar tu contraseña.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btn_guardarContraseñaActionPerformed

    private void btn_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiarActionPerformed
        // TODO add your handling code here:
        gestionContraseña.limpiar();
    }//GEN-LAST:event_btn_limpiarActionPerformed

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        // TODO add your handling code here:
        NuevaContraseña contraseña = gestionContraseña.guardarEditarContraseña(true);
        if (contraseña != null) {
            contraseña.setFechaRegistro(nuevaContraseña.getFechaRegistro());
            contraseña.setIdContraseña(nuevaContraseña.getIdContraseña());
            if (controladorContraseña.actualizarContraseña(contraseña)) {
                JOptionPane.showMessageDialog(rootPane, "Contraseña editada con éxito.", "ÉXITO", JOptionPane.INFORMATION_MESSAGE);
                gestionContraseña.limpiar();
                List<NuevaContraseña> contraseñaNombre = controladorContraseña.obtenerContraseñasGuardadas();
                modeloTablaContraseñas.setPersonas(contraseñaNombre);
                modeloTablaContraseñas.fireTableDataChanged();
                bloquear();
                Inicio.setSelectedIndex(3);
                desbloquear2();
                nuevaContraseña = null;
            } else {
                JOptionPane.showMessageDialog(rootPane, "No se pudo editar su contraseña.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_editarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        // TODO add your handling code here:

        if (nuevaContraseña != null) {
            nuevaContraseña.setIdContraseña(nuevaContraseña.getIdContraseña());
            if (controladorContraseña.eliminarPersona(nuevaContraseña)) {
                JOptionPane.showMessageDialog(rootPane, "Contraseña eliminada con exito del sitema.", "ÉXITO", JOptionPane.INFORMATION_MESSAGE);
                gestionContraseña.limpiar();
                List<NuevaContraseña> contraseñaNombre = controladorContraseña.obtenerContraseñasGuardadas();
                modeloTablaContraseñas.setPersonas(contraseñaNombre);
                modeloTablaContraseñas.fireTableDataChanged();
                bloquear();
                Inicio.setSelectedIndex(3);
                desbloquear2();
                nuevaContraseña = null;

            } else {
                JOptionPane.showMessageDialog(rootPane, "No se puede eliminar la contraseña.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_comprobarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_comprobarActionPerformed
        // TODO add your handling code here:
        esFuerte(jtxt_contraseña_calificar.getText());
    }//GEN-LAST:event_btn_comprobarActionPerformed

    private void jch_verCalificadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jch_verCalificadorActionPerformed
        // TODO add your handling code here:
        if (jch_verCalificador.isSelected()) {
            jtxt_contraseña_calificar.setEchoChar((char) 0);
        } else {
            jtxt_contraseña_calificar.setEchoChar('*');
        }
    }//GEN-LAST:event_jch_verCalificadorActionPerformed

    private void jtxt_contraseña_calificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxt_contraseña_calificarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxt_contraseña_calificarActionPerformed

    private void btn_limpiar_calificadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiar_calificadorActionPerformed
        // TODO add your handling code here:
        jtxt_contraseña_calificar.setText("");
        texto1.setText("");
        texto.setText("");
    }//GEN-LAST:event_btn_limpiar_calificadorActionPerformed

    private void acerca_deMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_acerca_deMouseClicked
        // TODO add your handling code here:
        AcercaDe acercaDe = new AcercaDe(this, ejecutar);
        acercaDe.setVisible(true);
    }//GEN-LAST:event_acerca_deMouseClicked

    private void Salir_del_sistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Salir_del_sistemaActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_Salir_del_sistemaActionPerformed

    private void Salir_a_LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Salir_a_LoginActionPerformed
        // TODO add your handling code here:
        Login login = new Login();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_Salir_a_LoginActionPerformed

    private void Contraseñas_no_guardadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Contraseñas_no_guardadasActionPerformed
        // TODO add your handling code here:
        Inicio.setSelectedIndex(5);
    }//GEN-LAST:event_Contraseñas_no_guardadasActionPerformed

    private void Calificador_de_contraseña_seguraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Calificador_de_contraseña_seguraActionPerformed
        // TODO add your handling code here:
        Inicio.setSelectedIndex(4);
    }//GEN-LAST:event_Calificador_de_contraseña_seguraActionPerformed

    private void Lista_de_contraseñasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Lista_de_contraseñasActionPerformed
        // TODO add your handling code here:
        Inicio.setSelectedIndex(3);
    }//GEN-LAST:event_Lista_de_contraseñasActionPerformed

    private void Generar_una_contraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Generar_una_contraseñaActionPerformed
        // TODO add your handling code here:
        Inicio.setSelectedIndex(2);
    }//GEN-LAST:event_Generar_una_contraseñaActionPerformed

    private void Registrar_una_Nueva_ContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Registrar_una_Nueva_ContraseñaActionPerformed
        // TODO add your handling code here:
        Inicio.setSelectedIndex(1);
    }//GEN-LAST:event_Registrar_una_Nueva_ContraseñaActionPerformed

    private void ver_repetir_contraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ver_repetir_contraseñaActionPerformed
        // TODO add your handling code here:
        if (ver_repetir_contraseña.isSelected()) {
            jtxt_repetirContraseña.setEchoChar((char) 0);
        } else {
            jtxt_repetirContraseña.setEchoChar('*');
        }
    }//GEN-LAST:event_ver_repetir_contraseñaActionPerformed

    private void ver_ContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ver_ContraseñaActionPerformed
        // TODO add your handling code here:
        if (ver_Contraseña.isSelected()) {
            jtxt_contraseña.setEchoChar((char) 0);
        } else {
            jtxt_contraseña.setEchoChar('*');
        }
    }//GEN-LAST:event_ver_ContraseñaActionPerformed

    private void jtxt_repetirContraseñaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_repetirContraseñaKeyTyped
        // TODO add your handling code here:
        if (evt.getKeyChar() == KeyEvent.VK_SPACE) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep(); //sonido de no aceptar más caracteres.
        }
    }//GEN-LAST:event_jtxt_repetirContraseñaKeyTyped

    private void jtxt_contraseñaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtxt_contraseñaKeyTyped
        // TODO add your handling code here:
        if (evt.getKeyChar() == KeyEvent.VK_SPACE) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep(); //sonido de no aceptar más caracteres.
        }
    }//GEN-LAST:event_jtxt_contraseñaKeyTyped
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new Principal().setVisible(true);
        });
    }
    int contadorClick2 = 0;
    boolean ejecutar2;

    @Override
    public void clickListaContraseña(NuevaContraseña nc) {
        ejecutar2 = true;
        //HILO DE PROGRMACIÓN
        new Thread(() -> {
            while (ejecutar2) {
                try {
                    //Parar un segundo
                    Thread.sleep(1000);
                    contadorClick2 = 0;
                    ejecutar2 = false;
                    System.out.println("Reinicia CLICK");
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }).start();

        contadorClick2++;
        if (contadorClick2 == 2) {
            if (JOptionPane.showConfirmDialog(rootPane, "¿Desea modificar este registro?",
                    "Editar Registro", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                Inicio.setSelectedIndex(1);
                jtxt_contraseña.setText(utilidades.desencriptar(nc.getContraseña()));
                jtxt_repetirContraseña.setText(jtxt_contraseña.getText());
                jcb_tipoContraseña.setSelectedItem(nc.getTipoContraseña());
                nombre_SitioApp.setText(nc.getNombreSitioApp());
                jtxt_propietario.setText(nc.getPropietario());
                nuevaContraseña = nc;
                desbloquear();
                bloquear2();

            }
            contadorClick2 = 0;
            ejecutar2 = false;
        }
        System.out.println("contadorClick" + contadorClick2);
    }
    int contadorClick = 0;
    boolean ejecutar;

    @Override
    public void clickListaEliminar(Borrador b) {
        ejecutar2 = true;
        //HILO DE PROGRMACIÓN
        new Thread(() -> {
            while (ejecutar) {
                try {
                    //Parar un segundo
                    Thread.sleep(1000);
                    contadorClick = 0;
                    ejecutar = false;
                    System.out.println("Reinicia CLICK");
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }).start();

        contadorClick++;
        if (contadorClick == 2) {
            if (JOptionPane.showConfirmDialog(rootPane, "¿Desea eliminar el borrador?",
                    "Eliminar Registro", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                jtxt_contraseña.setText(utilidades.desencriptar(b.getContraseña()));
                jtxt_repetirContraseña.setText(jtxt_contraseña.getText());
                jcb_tipoContraseña.setSelectedItem(b.getTipoContraseña());
                nombre_SitioApp.setText(b.getNombreSitioApp());
                jtxt_propietario.setText(b.getPropietario());
                nuevoBorrador = b;

                if (controladorBorrador.elminarBorrador(nuevoBorrador)) {
                    JOptionPane.showMessageDialog(rootPane, "Borrador eliminado con exito del sitema.", "ÉXITO", JOptionPane.INFORMATION_MESSAGE);
                    gestionBorrador.limpiar();
                    List<Borrador> contraseñaNombre = controladorBorrador.obtenerContraseñasNoGuardadas();
                    modeloTablaContraseñasNoGuardadas.setPersonas(contraseñaNombre);
                    modeloTablaContraseñasNoGuardadas.fireTableDataChanged();
                    nuevoBorrador = null;
                } else {
                    JOptionPane.showMessageDialog(rootPane, "No se puede eliminar el borrador.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                if (JOptionPane.showConfirmDialog(rootPane, "¿Entonces desea guardar este registro?",
                        "Guardar Registro", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    Inicio.setSelectedIndex(1);
                    jtxt_contraseña.setText(utilidades.desencriptar(b.getContraseña()));
                    jtxt_repetirContraseña.setText(jtxt_contraseña.getText());
                    jcb_tipoContraseña.setSelectedItem(b.getTipoContraseña());
                    nombre_SitioApp.setText(b.getNombreSitioApp());
                    jtxt_propietario.setText(b.getPropietario());
                    nuevoBorrador = b;
                    desbloquear();
                    bloquear();
                    controladorBorrador.elminarBorrador(nuevoBorrador);
                    List<Borrador> contraseñaNombre = controladorBorrador.obtenerContraseñasNoGuardadas();
                    modeloTablaContraseñasNoGuardadas.setPersonas(contraseñaNombre);
                    modeloTablaContraseñasNoGuardadas.fireTableDataChanged();
                }
            }
            contadorClick = 0;
            ejecutar = false;
        }
        System.out.println("contadorClick" + contadorClick);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Calificador_de_contraseña_segura;
    private javax.swing.JButton Contraseñas_no_guardadas;
    private javax.swing.JButton Generar_una_contraseña;
    private javax.swing.JTabbedPane Inicio;
    private javax.swing.JButton Lista_de_contraseñas;
    private javax.swing.JButton Registrar_una_Nueva_Contraseña;
    private javax.swing.JButton Salir_a_Login;
    private javax.swing.JButton Salir_del_sistema;
    private javax.swing.JMenu acerca_de;
    private javax.swing.JButton btn_Exportar;
    private javax.swing.JButton btn_comprobar;
    private javax.swing.JButton btn_editar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_guardarContraseña;
    private javax.swing.JButton btn_guardar_borradores;
    private javax.swing.JButton btn_limpiar;
    private javax.swing.JButton btn_limpiar_calificador;
    private javax.swing.JButton btn_limpiar_contraseña_generada;
    private javax.swing.JButton btn_repetir;
    private java.awt.Canvas canvas1;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuBar jMenuBar4;
    private javax.swing.JMenuBar jMenuBar5;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu jPopupMenu3;
    private javax.swing.JPopupMenu jPopupMenu4;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JComboBox<String> jcb_mostrarContraseñas;
    private javax.swing.JComboBox<String> jcb_mostrar_borrador;
    private javax.swing.JComboBox<String> jcb_tipoContraseña;
    private javax.swing.JCheckBox jch_verCalificador;
    private javax.swing.JMenu jm_Salir;
    private javax.swing.JPanel jp_Inicio;
    private javax.swing.JPanel jp_borradores;
    private javax.swing.JPanel jp_calificadorContraseñas;
    private javax.swing.JPanel jp_generadorContraseñas;
    private javax.swing.JPanel jp_guardarContraseña;
    private javax.swing.JPanel jp_listaContraseñas;
    private javax.swing.JSpinner js_caracteres;
    private javax.swing.JSpinner js_mayusculas;
    private javax.swing.JSpinner js_minusculas;
    private javax.swing.JSpinner js_numeros;
    private javax.swing.JPasswordField jtxt_contraseña;
    private javax.swing.JPasswordField jtxt_contraseña_calificar;
    private javax.swing.JTextField jtxt_gnerar;
    private javax.swing.JTextField jtxt_propietario;
    private javax.swing.JPasswordField jtxt_repetirContraseña;
    private java.awt.Menu menu1;
    private java.awt.Menu menu2;
    private java.awt.Menu menu3;
    private java.awt.Menu menu4;
    private java.awt.Menu menu5;
    private java.awt.Menu menu6;
    private java.awt.MenuBar menuBar1;
    private java.awt.MenuBar menuBar2;
    private java.awt.MenuBar menuBar3;
    private javax.swing.JTextField nombre_SitioApp;
    private java.awt.Panel panel1;
    private java.awt.ScrollPane scrollPane1;
    private javax.swing.JTable tablaContraseña;
    private javax.swing.JLabel texto;
    private javax.swing.JLabel texto1;
    private javax.swing.JTextField txtParametroBusquedaGuardado;
    private javax.swing.JTextField txtParametroBusquedaNoGuardado;
    private javax.swing.JCheckBox ver_Contraseña;
    private javax.swing.JCheckBox ver_repetir_contraseña;
    // End of variables declaration//GEN-END:variables
}
