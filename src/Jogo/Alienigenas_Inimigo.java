package Jogo;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Alienigenas_Inimigo {
	
	private Image imagem;
	private int x, y;
	private int largura, altura;
	private boolean isVisible;
	
	private static final int LARGURA_TELA = 500;
	private static final int VELOCIDADE = 1;
	
	private static int contador = 0;
	
	public Alienigenas_Inimigo(int x, int y){
		this.x = x;
		this.y = y;
		
		ImageIcon referencia;
		
		
		if(contador++ %5 == 0){
			referencia = new ImageIcon("Imagens\\inimigo_1.gif");
		}else{
			referencia = new ImageIcon("Imagens\\inimigo_2.gif");
		}
		
		imagem = referencia.getImage();
		
		this.largura = imagem.getWidth(null);
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
	
	public void moverInimigo(){
		if(this.x < 0){
			this.x = LARGURA_TELA;
		}else {
			this.x -= VELOCIDADE;
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x, y, largura, altura);
	}

}
