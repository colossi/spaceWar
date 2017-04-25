package Jogo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1;
	
	private Image fundo;
	private Nave nave;
	private Timer timer;
	
	//Verifica se está jogando o não
	private boolean jogo;
	
	private int vida = 5;
	
	private List<Alienigenas_Inimigo> inimigo;
	
	//Matriz de coordenadas de linhas e colunas
	//1º Valor = x e 2º Valor =y
	//Matriz onde aparece os inimigos
	private int[][] coordenadas = {
			{2380, 29},{2600, 59},{1380,89},{780,109},
			{580, 139},{880, 239},{790, 259},{760,50},
			{790, 150},{1980,209},{560,45},{510,70},
			{930,159},{590,80},{530,60},{940,59},
			{990,30},{920,200},{900,259},{660,50},
			{540,90},{810,220},{860,20},{740,180},
			{820,128},{490,170},{700,30},{920,300},
			{856,328},{456,320},
			};
	

	
	public Fase(){
		//Configuração de Imagem de Fundo
		ImageIcon referencia = new ImageIcon("Imagens\\fundo009.jpg");
		fundo = referencia.getImage();
		
		nave = new Nave();
		//Jogo funcionando ou rodando
		jogo = true;
		
		inicializaInimigos();
		
		//Tempo do movimento dos inimigos
		timer = new Timer(5, this);
		timer.start();
		
		addKeyListener(new TecladoAdapter());
		setFocusable(true);
		setDoubleBuffered(true);
	}
	
	//Chama os inimigos 
	public void inicializaInimigos(){
		inimigo = new ArrayList<Alienigenas_Inimigo>();
			
		for(int i=0; i < coordenadas.length;i++){
			inimigo.add(new Alienigenas_Inimigo(coordenadas[i][0], coordenadas[i][1]));
		}
	}
	
	public void paint(Graphics g){
		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(fundo, 0, 0, null);
		
		if(vida > 0){
			
			//O drawImage renderiza a imagem.
			graficos.drawImage(nave.getImagem(), nave.getX(), nave.getY(), this);
		
			List<Missel> misseis = nave.getMisseis();
		
			//apresenta ao usuário os misseis que estão no jogo
			for(int i=0; i < misseis.size(); i++){
				Missel m = (Missel) misseis.get(i);
				graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
			}
		
			for(int i = 0; i < inimigo.size(); i++){
				Alienigenas_Inimigo in = inimigo.get(i);
				graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
			}
			
			graficos.setColor(Color.WHITE);
			//Apresenta o número de inimigos no canto da tela
			graficos.drawString("Inimigos:" + inimigo.size(), 5, 15);
			
		} else {
			ImageIcon fimdoJogo = new ImageIcon("Imagens\\Fim002.png");
			graficos.drawImage(fimdoJogo.getImage(), 0, 0, null);
		}
		
		//Fecha e abre outra janela, ou seja, a do GameOver
		g.dispose();
	}

	//Movimentação da Nave
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//verifica se matou os inimigos
		if(inimigo.size() == 0){
			jogo = false;
		}
		
		List<Missel> misseis = nave.getMisseis();
		
		//Quantidade de Misseis atirados pela nave
		for(int i = 0;i<misseis.size(); i++){
			Missel m = (Missel) misseis.get(i);
			
			if(m.isVisible()){
				m.moverMissel();
			}else{
				misseis.remove(i);
			}
		}
		
		for(int i = 0; i < inimigo.size(); i++){
			Alienigenas_Inimigo in = inimigo.get(i);
			
			if(in.isVisible()){
				in.moverInimigo();
			}else {
				inimigo.remove(i);
			}
		}
		
		nave.mover();
		//verifica colissões
		colisoes();
		repaint();
	}
	
	//Pega os movimentos da Tecla
	private class TecladoAdapter extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			//keyPressed é disparado sempre que qualquer tecla do teclado é pressionada
			nave.KeyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			nave.KeyReleased(e);
		}
		
	}
	
	//Colisoes do jogo com o inimigo
	public void colisoes(){
		Rectangle formaNave = nave.getBounds();
		Rectangle formaAling;
		Rectangle formaMissel;
		
		for(int i=0; i < inimigo.size();i++){
			Alienigenas_Inimigo tempAling = inimigo.get(i);
			formaAling = tempAling.getBounds();
			
			if(formaNave.intersects(formaAling)){
				nave.setVisibel(false);
				tempAling.setVisible(false);
				jogo = false;
				vida--;
			}
			
		}
		
		List<Missel> misseis = nave.getMisseis();
		
		for(int i = 0; i< misseis.size(); i++){
			Missel tempMissel = misseis.get(i);
			formaMissel = tempMissel.getBounds();
			
			for(int j=0; j < inimigo.size(); j++){
				Alienigenas_Inimigo tempInimigo = inimigo.get(j);
				formaAling = tempInimigo.getBounds();
				
				if(formaMissel.intersects(formaAling)){
					tempInimigo.setVisible(false);
					tempMissel.setVisible(false);
				}
			}
		}
	}

}
