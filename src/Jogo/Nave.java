package Jogo;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class Nave {
	
	//cordenação da nave
	private int x,y;
	private int dx,dy;
	private Image imagem;
	private boolean isVisibel;
	
	//Missel
	private List<Missel> misseis;
	//referente a altura e largura do Missel
	private int altura, largura;
	
	//GETs e SETs
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
	public int getDx() {
		return dx;
	}
	public void setDx(int dx) {
		this.dx = dx;
	}
	public int getDy() {
		return dy;
	}
	public void setDy(int dy) {
		this.dy = dy;
	}
	public Image getImagem() {
		return imagem;
	}
	public void setImagem(Image imagem) {
		this.imagem = imagem;
	}
	
	public boolean isVisibel() {
		return isVisibel;
	}
	public void setVisibel(boolean isVisibel) {
		this.isVisibel = isVisibel;
	}
	
	//GETs e SETs referente ao Misseis
	public List<Missel> getMisseis() {
		return misseis;
	}
	public void setMisseis(List<Missel> misseis) {
		this.misseis = misseis;
	}
	public int getAltura() {
		return altura;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}
	public int getLargura() {
		return largura;
	}
	public void setLargura(int largura) {
		this.largura = largura;
	}
	
	
	public Nave(){
		ImageIcon referencia = new ImageIcon("Imagens\\nave.gif");
		imagem = referencia.getImage();
		
		altura = imagem.getHeight(null);
		largura = imagem.getWidth(null);
		
		//Lista de Missel
		misseis = new ArrayList<Missel>();
		
		this.x = 100;
		this.y = 100;
	}
	
	//Movimentação da Nave
	public void mover(){
		//System.out.println(x + ","+y);
		x += dx;
		y += dy;
		
		if(this.x < 1){
			x = 1;
		}
		
		if(this.x > 462){
			x = 462;
		}
		
		if(this.y < 1){
			y = 1;
		}
		
		if(this.y > 340){
			y = 340;
		}
	}
	
	//atira misseis
	public void atira(){
		this.misseis.add(new Missel(x + largura, y + altura/2));
	}
	
	//Movimentação da nave através do Teclado
	public void KeyPressed(KeyEvent tecla){
		
		int codigo = tecla.getKeyCode();
		
		//Ação com a barra de espaço para atirar misseis
		if(codigo == KeyEvent.VK_SPACE){
			atira();
		}
		
		if(codigo == KeyEvent.VK_UP){
			dy = -1;
		}
		
		if(codigo == KeyEvent.VK_DOWN){
			dy = 1;
		}
		
		if(codigo == KeyEvent.VK_LEFT){
			dx = -1;
		}
		
		if(codigo == KeyEvent.VK_RIGHT){
			dx = 1;
		}	
		
	}
	
	public void KeyReleased(KeyEvent tecla){
		
		int codigo = tecla.getKeyCode();
		
		if(codigo == KeyEvent.VK_UP){
			dy = 0;
		}
		
		if(codigo == KeyEvent.VK_DOWN){
			dy = 0;
		}
		
		if(codigo == KeyEvent.VK_LEFT){
			dx = 0;
		}
		
		if(codigo == KeyEvent.VK_RIGHT){
			dx = 0;
		}
		
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x, y, largura, altura);
	}
	
}
