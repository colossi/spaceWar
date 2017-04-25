package Jogo;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import java.util.Random;

public class Alienigenas_Inimigo {
	
	private Image imagem;
	private int x, y;
	private int yPadrao;
	private int largura, altura;
	private boolean isVisible;
	
	private static final int LARGURA_TELA = 700;
	private static final int VELOCIDADE = 1;
	private static final int ALTURA_SOBE = 50;
	
	private static int contador = 0;
	
	public Alienigenas_Inimigo(int x, int y){
		this.x = x;
		this.y = y;
		this.yPadrao = y;
		
		ImageIcon referencia;
		
		
		if(contador++ %5 == 0){
			referencia = new ImageIcon("Imagens\\relogio007.png");
		}else{
			referencia = new ImageIcon("Imagens\\calendario001.png");
		}
		
		imagem = referencia.getImage();
		
		//Retorna a largura em pixels das imagens.Caso contr�rio, GetWidth retorna 0.
		this.largura = imagem.getWidth(null);
		//Retorna a altura em pixels das imagens. Caso contr�rio, GetHeight retorna 0.
		this. altura = imagem.getHeight(null);
		
		isVisible = true;
		
	}

	//GETs e SETs
	public Image getImagem() {
		return imagem;
	}

	public void setImagem(Image imagem) {
		this.imagem = imagem;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
	
	//Movimenta��o do Inimigo
	public void moverInimigo(){
		if(this.x < 0){
			this.x = LARGURA_TELA;
		}else {
			this.x -= VELOCIDADE;
		}
		
		//Fun��o Sobe e Desce
		if (this.x % 80 < 30) {
			this.y = this.yPadrao - ALTURA_SOBE;
		} else {
			this.y = this.yPadrao;
		}
		
	}	
	
	public Rectangle getBounds(){
		return new Rectangle(x, y, largura, altura);
	}

}
