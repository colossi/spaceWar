package Jogo;

import javax.swing.JFrame;

public class ContainerdeJanelas extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public ContainerdeJanelas(){
		
		add(new Fase());
		
		setTitle("Space War - Corrida contra o tempo...");
		//Tamanho da Tela
		setSize(640,400);
		//Tamanho de tela não pode ser alterada
		setResizable(false);
		//Configuração de Sair do jogo pelo X
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//local onde a tela do jogo vai ser apresentado
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args){
		new ContainerdeJanelas();
	}

}
