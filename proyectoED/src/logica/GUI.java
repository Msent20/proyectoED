package logica;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;


import javax.swing.JOptionPane;
import javax.swing.JTextPane;

import org.jdesktop.xswingx.PromptSupport;

import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class GUI {

	private JFrame frame;
	private JTextField txtIngreseRaiz;
	private JTextField txtAgregarNodo;
	private JTextField textRuta;
	private JTextField txtRuta;
	private logica logi;
	private JTextField txtPadre;
	private JTextPane textImprimir;
	private JButton btnImprimir, AddNodo, btnRuta, btnNiveles, btnListarCarpetas, btnListarArchivos, btnClonar;
	private JEditorPane editorPane,txtClon,txtListar;
	private JComboBox comboBox;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		logi = new logica();
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 653, 551);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelGeneral = new JPanel();
		frame.getContentPane().add(panelGeneral, BorderLayout.CENTER);
		panelGeneral.setLayout(null);
		
		JPanel panelBotones = new JPanel();
		panelBotones.setBounds(0, 0, 326, 264);
		panelGeneral.add(panelBotones);
		panelBotones.setLayout(new GridLayout(6, 2, 0, 0));
	
		
	
		
		JButton CargarArbol = new JButton("Cargar árbol");
		CargarArbol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtIngreseRaiz.getText().isEmpty()) {
					logi.cargar_Arbol(txtIngreseRaiz.getText());
					btnImprimir.setEnabled(true);
					AddNodo.setEnabled(true);
					comboBox.setEnabled(true);
					btnListarArchivos.setEnabled(true);
					btnListarCarpetas.setEnabled(true);
					btnNiveles.setEnabled(true);
					btnRuta.setEnabled(true);
					txtPadre.setEnabled(true);
					txtAgregarNodo.setEnabled(true);
					textRuta.setEnabled(true);
					btnClonar.setEnabled(true);
					
				}
				else {
					JOptionPane.showMessageDialog(new JFrame(),"Complete correctamente los campos.", "Dialog", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		panelBotones.add(CargarArbol);
		
		txtIngreseRaiz = new JTextField();
		panelBotones.add(txtIngreseRaiz);
		txtIngreseRaiz.setText("");
		txtIngreseRaiz.setColumns(10);
		PromptSupport.setPrompt("Ingrese raíz", txtIngreseRaiz);
		PromptSupport.setFontStyle(2, txtIngreseRaiz);
		PromptSupport.setBackground(Color.WHITE, txtIngreseRaiz);
		
		AddNodo = new JButton("Agregar");
		AddNodo.setEnabled(false);
		AddNodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtPadre.getText().isEmpty() && txtAgregarNodo.getText().isEmpty())
					JOptionPane.showMessageDialog(new JFrame(),"Complete correctamente los campos.", "Dialog", JOptionPane.INFORMATION_MESSAGE);
				else 
					if(!logi.agregar_Nodo(txtPadre.getText(), txtAgregarNodo.getText()))
						JOptionPane.showMessageDialog(new JFrame(),"Carpeta o archivo ya existente, ingrese otro nombre.", "Dialog", JOptionPane.INFORMATION_MESSAGE);
					
			}
		});
		panelBotones.add(AddNodo);
		
		txtPadre = new JTextField();
		txtPadre.setToolTipText("");
		txtPadre.setEnabled(false);
		panelBotones.add(txtPadre);
		txtPadre.setColumns(10);
		PromptSupport.setPrompt("Agregar padre", txtPadre);
		PromptSupport.setFontStyle(2, txtPadre);
		PromptSupport.setBackground(Color.WHITE, txtPadre);
		
		txtAgregarNodo = new JTextField();
		txtAgregarNodo.setEnabled(false);
		panelBotones.add(txtAgregarNodo);
		txtAgregarNodo.setColumns(10);
		PromptSupport.setPrompt("Agregar nodo", txtAgregarNodo);
		PromptSupport.setFontStyle(2, txtAgregarNodo);
		PromptSupport.setBackground(Color.WHITE, txtAgregarNodo);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 264, 326, 264);
		panelGeneral.add(panel);
		panel.setLayout(null);
		
	
		
		btnListarArchivos = new JButton("Listar archivos");
		btnListarArchivos.setEnabled(false);
		btnListarArchivos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtListar.setText(logi.listar_Archivos());
			}
		});
		btnListarArchivos.setBounds(190, 51, 120, 30);
		panel.add(btnListarArchivos);
		
		btnListarCarpetas = new JButton("Listar carpetas");
		btnListarCarpetas.setEnabled(false);
		btnListarCarpetas.setBounds(190, 9, 120, 30);
		btnListarCarpetas.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
					
					txtListar.setText(logi.listar_Carpetas());
				
			}
		});
		panel.add(btnListarCarpetas);
		
		btnClonar = new JButton("Clonar árbol");
		btnClonar.setBounds(46, 9, 120, 30);
		panel.add(btnClonar);
		btnClonar.setEnabled(false);
		
		txtClon = new JTextPane();
		txtClon.setEditable(false);
		txtClon.setBounds(46, 83, 110, 152);
		panel.add(txtClon);
		
		 txtListar = new JTextPane();
		 txtListar.setEditable(false);
		txtListar.setBounds(200, 83, 110, 152);
		panel.add(txtListar);
		
			
			
			btnNiveles = new JButton("Mostrar niveles");
			btnNiveles.setBounds(46, 51, 120, 30);
			panel.add(btnNiveles);
			btnNiveles.setEnabled(false);
			btnNiveles.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(!logi.mostrar_Niveles().isEmpty())
						txtClon.setText(logi.mostrar_Niveles());
					else
						JOptionPane.showMessageDialog(new JFrame(),"Complete correctamente los campos.", "Dialog", JOptionPane.INFORMATION_MESSAGE);
				}
			});
		btnClonar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtClon.setText(logi.arbolClon());
			}
		});
		
		btnRuta = new JButton("Mostrar ruta");
		btnRuta.setEnabled(false);
		btnRuta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textRuta.getText().isEmpty())
					JOptionPane.showMessageDialog(new JFrame(),"Complete correctamente los campos.", "Dialog", JOptionPane.INFORMATION_MESSAGE);
				else
					txtRuta.setText(logi.mostrar_Ruta(textRuta.getText()));
			}
		});
		btnRuta.setBounds(338, 28, 108, 26);
		panelGeneral.add(btnRuta);
		
		textRuta = new JTextField();
		textRuta.setEnabled(false);
		textRuta.setText("");
		textRuta.setBounds(494, 24, 130, 33);
		panelGeneral.add(textRuta);
		textRuta.setColumns(10);
		PromptSupport.setPrompt("Ingrese archivo", textRuta);
		PromptSupport.setFontStyle(2, textRuta);
		PromptSupport.setBackground(Color.WHITE, textRuta);
		
		txtRuta = new JTextField();
		txtRuta.setEditable(false);
		txtRuta.setBounds(400, 66, 130, 51);
		panelGeneral.add(txtRuta);
		txtRuta.setColumns(10);
		
		editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setBounds(454, 143, 170, 41);
		panelGeneral.add(editorPane);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Posorden", "Preorden"}));
		comboBox.setEnabled(false);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedItem().toString().equals("Posorden"))
					editorPane.setText(logi.mostrar_PostOrden());
				else {
					if(comboBox.getSelectedItem().toString().equals("Preorden"))
						editorPane.setText(logi.mostrar_Preorden());
				}
			}
		});
		comboBox.setToolTipText("");
		comboBox.setBounds(363, 157, 52, 27);
		panelGeneral.add(comboBox);
		
		btnImprimir = new JButton("Imprimir archivos");

		btnImprimir.setEnabled(false);
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {  
				textImprimir.setText(logi.simular_Impresion());
			}
		});
		btnImprimir.setBounds(400, 235, 158, 41);
		panelGeneral.add(btnImprimir);
		
		textImprimir = new JTextPane();
		textImprimir.setEditable(false);
		textImprimir.setBounds(375, 311, 218, 155);
		panelGeneral.add(textImprimir);
		
	}
}
