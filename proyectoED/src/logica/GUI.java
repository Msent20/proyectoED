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

import org.jdesktop.swingx.plaf.LoginPaneUI;
import org.jdesktop.xswingx.PromptSupport;

import TDAArbol.Arbol;
import TDAArbol.EmptyTreeException;
import TDAArbol.InvalidPositionException;
import TDAArbol.Position;
import TDALista.DoubleLinkedList;
import TDALista.PositionList;

import javax.swing.JEditorPane;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class GUI {

	private JFrame frame;
	private logica logi;
	private JButton CargarArbol;
	private JButton btnAgregarNodo, btnClonarbol ,btnMostrarRuta, btnListarArchivos,btnListarCarpetas, btnPosorden, btnSimularImpresion, btnPreorden, btnMostrarNiveles;
	private JTextField textField;
	private JEditorPane contenedor;
	private JTree tree , treeClon;
	private PositionList<DefaultMutableTreeNode> nodos;
	private DefaultTreeModel modelo , modeloClon;


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
		
		CargarArbol = new JButton("Cargar árbol");
		CargarArbol.setBackground(SystemColor.textHighlight);
		CargarArbol.setBounds(36, 31, 105, 29);
		panelGeneral.add(CargarArbol);
		CargarArbol.addActionListener(new ActionListener() {
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				String raiz = JOptionPane.showInputDialog(null,"Ingresar raiz");
				if(!raiz.isEmpty()) {
					logi.cargar_Arbol(raiz);
					btnAgregarNodo.setEnabled(true);
					btnListarArchivos.setEnabled(true);
					btnListarCarpetas.setEnabled(true);
					btnMostrarNiveles.setEnabled(true);
					btnPosorden.setEnabled(true);
					btnPreorden.setEnabled(true);
					btnMostrarRuta.setEnabled(true);
					btnSimularImpresion.setEnabled(true);
					CargarArbol.setEnabled(false);
					btnClonarbol.setEnabled(true);
					DefaultMutableTreeNode root = new DefaultMutableTreeNode(raiz);
					modelo.setRoot(root);
					nodos.addFirst(root);
				}else
					JOptionPane.showMessageDialog(new JFrame(),"Complete el campo correctamente.", "Dialog", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		btnAgregarNodo = new JButton("Agregar nodo");
		btnAgregarNodo.setBounds(36, 71, 105, 29);
		btnAgregarNodo.setEnabled(false);
		panelGeneral.add(btnAgregarNodo);
		
		btnListarArchivos = new JButton("Listar archivos");
		btnListarArchivos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contenedor.setText(logi.listar_Archivos());
			}
		});
		btnListarArchivos.setEnabled(false);
		btnListarArchivos.setBounds(328, 31, 123, 29);
		panelGeneral.add(btnListarArchivos);
		
		btnListarCarpetas = new JButton("Listar carpetas");
		btnListarCarpetas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contenedor.setText(logi.listar_Carpetas());
			}
		});
		btnListarCarpetas.setEnabled(false);
		btnListarCarpetas.setBounds(328, 78, 123, 29);
		panelGeneral.add(btnListarCarpetas);
		
		btnMostrarNiveles = new JButton("Mostrar niveles");
		btnMostrarNiveles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!logi.mostrar_Niveles().isEmpty())
					contenedor.setText(logi.mostrar_Niveles());
				else
					JOptionPane.showMessageDialog(new JFrame(),"No hay archivos y carpetas para mostrar.", "Dialog", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnMostrarNiveles.setEnabled(false);
		btnMostrarNiveles.setBounds(463, 31, 152, 29);
		panelGeneral.add(btnMostrarNiveles);
		
		btnSimularImpresion = new JButton("Simular impresion");
		btnSimularImpresion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contenedor.setText(logi.simular_Impresion());
			}
		});
		btnSimularImpresion.setEnabled(false);
		btnSimularImpresion.setBounds(463, 78, 152, 29);
		panelGeneral.add(btnSimularImpresion);
		
		btnPreorden = new JButton("Preorden");
		btnPreorden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(logi.mostrar_Preorden());
			}
		});
		btnPreorden.setEnabled(false);
		btnPreorden.setBounds(353, 306, 117, 29);
		panelGeneral.add(btnPreorden);
		
		btnPosorden = new JButton("Posorden");
		btnPosorden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(logi.mostrar_PostOrden());
			}
		});
		btnPosorden.setEnabled(false);
		btnPosorden.setBounds(482, 306, 117, 29);
		panelGeneral.add(btnPosorden);
		
		btnMostrarRuta = new JButton("Mostrar ruta");
		btnMostrarRuta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ruta= JOptionPane.showInputDialog(null,"Ingrese el archivo:");
				if(!ruta.isEmpty())
					textField.setText(logi.mostrar_Ruta(ruta));
				else
					JOptionPane.showMessageDialog(new JFrame(),"Complete el campo correctamente.", "Dialog", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnMostrarRuta.setEnabled(false);
		btnMostrarRuta.setBounds(419, 347, 117, 29);
		panelGeneral.add(btnMostrarRuta);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(347, 401, 252, 26);
		panelGeneral.add(textField);
		textField.setColumns(10);
		
		contenedor= new JEditorPane();
		contenedor.setEditable(false);
		contenedor.setBounds(328, 129, 287, 138);
		panelGeneral.add(contenedor);
		
		tree = new JTree();
		
		nodos = new DoubleLinkedList<DefaultMutableTreeNode>();
		modelo = new DefaultTreeModel(null);
		tree.setModel(modelo);
		tree.setBounds(38, 129, 105, 247);
		panelGeneral.add(tree);
		
		treeClon = new JTree();
		
		treeClon.setModel(null);
		treeClon.setBounds(153, 129, 130, 247);
		panelGeneral.add(treeClon);
		
		
		btnClonarbol = new JButton("Clonar �rbol");
		btnClonarbol.setBounds(151, 74, 117, 23);
		btnClonarbol.setEnabled(false);
		panelGeneral.add(btnClonarbol);
		
		btnClonarbol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Arbol<String> aClon = logi.clonar_Arbol();				
					String rotRoot = aClon.root().element();
					DefaultMutableTreeNode r = new DefaultMutableTreeNode(rotRoot);
					modeloClon = new DefaultTreeModel(r);
					treeClon.setModel(modeloClon);
					llenarArbol(aClon.root() , aClon, r);
					expandir();
				} catch (EmptyTreeException e1) {}
				
				
			}		
		});
		
		btnAgregarNodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String padre = JOptionPane.showInputDialog(null,"Ingrese carpeta contenedora.");
				String hijo= JOptionPane.showInputDialog(null,"Ingrese nombre del archivo");
				if(padre.isEmpty() || hijo.isEmpty()) 
					JOptionPane.showMessageDialog(new JFrame(),"Complete el campo correctamente.", "Dialog", JOptionPane.INFORMATION_MESSAGE);
				else if (!logi.agregar_Nodo(padre, hijo))
					JOptionPane.showMessageDialog(new JFrame(),"Archivo o carpeta ya existente.", "Dialog", JOptionPane.INFORMATION_MESSAGE);
				else {
					
					DefaultMutableTreeNode padreD = buscarNodo(padre);
					DefaultMutableTreeNode hijoD = new DefaultMutableTreeNode(hijo);
					modelo.insertNodeInto(hijoD, padreD, padreD.getChildCount());
					modelo.reload();
					expandir();
					nodos.addLast(hijoD);
				}
			}
		});
	
		
		
	}
	
	private void llenarArbol(Position<String> t, Arbol<String> a, DefaultMutableTreeNode padre) {
	
		try {
			for (Position<String> pos : a.children(t)) {
				
				DefaultMutableTreeNode child = new DefaultMutableTreeNode(pos.element());
				
				
				modeloClon.insertNodeInto(child, padre, padre.getChildCount());
				modeloClon.reload();
				llenarArbol(pos , a, child);
				
			}
		} catch (InvalidPositionException e) {}
	
		
	}

	
	private void expandir() {
		
		for (int i = 0 ; i < tree.getRowCount() ; i++)
			tree.expandRow(i);
	}
	
	private DefaultMutableTreeNode buscarNodo(String r) {
		
		Iterator<DefaultMutableTreeNode> it = nodos.iterator();
		boolean encontre = false;
		DefaultMutableTreeNode toRet = null;
		
		while (it.hasNext() && !encontre) {
			
			DefaultMutableTreeNode nodo = it.next();
			if ((nodo.getUserObject()).equals(r)) {
				encontre = true;
				toRet = nodo;
			}
		}
	
		return toRet;
	}
	
	
}
