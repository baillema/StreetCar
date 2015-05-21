package graphique;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import constantesPackages.Constantes;
import tuilePackage.Tuile;
import mainPackage.Moteur;
import mainPackage.Plateau;

public class Panneau extends JPanel{
	String name;
	Color couleur;
	
	//Ajout Mathieu
	int depart = 100;
	int tailleCase = 50;
	int ecart = 220;
	int nbCases = 12;
	String message = "X = 0, Y = 0";
	BufferedImage fond;
	BufferedImage plateau ;
	BufferedImage tuile001 ;
	BufferedImage tuile002 ;
	BufferedImage tuile003;
	BufferedImage tuile004 ;
	BufferedImage tuile005 ;
	BufferedImage tuile006 ;
	BufferedImage tuile007;
	BufferedImage tuile008 ;
	BufferedImage tuile009 ;
	BufferedImage tuile010 ;
	BufferedImage tuile011 ;
	public int main = -1;
	public int carte = -1;
	public int caseX = -1;
	public int caseY = -1;
	Moteur mot;
	//Ajout Mathieu
		
	int typeDeZone;
	boolean contoursDessines;
		
	public Panneau (Color newCouleur, String newName){
		super();	
		name = newName;
		couleur = newCouleur;
		contoursDessines = false;
		addMouseListener(new GestionSouris(this));
		addMouseListener(new EcouteTerrain(this));
		initImage();
	}
	
	public Panneau (Color newCouleur, String newName, int numeroDeZone){
		super();		
		name = newName;
		couleur = newCouleur;
		typeDeZone = numeroDeZone;
		contoursDessines = false;
		addMouseListener(new GestionSouris(this));
		//On est sensé créer un mouse listener par joueur, à modifier
		initImage();
	}
	
	public Panneau (Color newCouleur, String newName, int numeroDeZone, Moteur m){
		super();		
		name = newName;
		couleur = newCouleur;
		typeDeZone = numeroDeZone;
		mot = m;
		contoursDessines = false;
		addMouseListener(new GestionSouris(this));
		//On est sensé créer un mouse listener par joueur, à modifier
		addMouseListener(new EcouteTerrain(this, m));
		initImage();
	}
	
	public String getName (){
		return name;
	}
	
	public int getTypeZone (){
		return typeDeZone;
	}
	
	public void paintComponent (Graphics g){
		Graphics2D crayon = (Graphics2D) g;
		
		int largeur = getSize().width;
		int hauteur = getSize().height;
		
		nettoyage(crayon);
		
		crayon.setColor(couleur);
		crayon.fillRect(0, 0, largeur, hauteur);
		
		if(typeDeZone == Constantes.Panneau.plateau)
		{
			dessinerFond(crayon);
			dessinerPlateau(crayon);
			dessinerContenuPlateau(crayon, mot.getPlateau());
			dessinerMain1(crayon);
			dessinerMain2(crayon);
			
			
			//Visuel des cases
			//crayon.drawImage(tuile001, depart+5*tailleCase+1, depart+tailleCase*3+1, tailleCase-1, tailleCase-1, this);
			//crayon.drawImage(tuile001, depart+5*tailleCase+1, depart+tailleCase*4+1, tailleCase-1, tailleCase-1, this);
			
			if(main != -1){ colorMain(crayon); }
			if(caseX != -1){ colorCase(crayon); }
		}
		
		if (contoursDessines){
			crayon.setColor(Color.black);
			crayon.drawRect(0, 0, largeur-1, hauteur-1);
		}
		
	}
	
	private void dessinerContenuPlateau(Graphics2D crayon, Plateau plateau) {
		for(int i = 0; i<12; i++)
		{
			for(int j = 0; j<12; j++)
			{
				if(plateau.getTuileAt(i, j) != null)
				{
					dessinerTuile(crayon,plateau.getTuileAt(i, j), i, j);
				}
			}
		}
		
	}

	private void dessinerTuile(Graphics2D drawable, Tuile tuileAt, int x, int y) {
		BufferedImage img = tuileAt.getImage();
		String orient = tuileAt.getOrientation();
		int angle;
			
		switch(orient)
		{
			case(Constantes.Orientation.nord) :
				angle = 0;
				break;
			case(Constantes.Orientation.sud) :
				angle = 180;
				break;
			case(Constantes.Orientation.est) :
				angle = 90;
				break;
			case(Constantes.Orientation.ouest) :
				angle = 270;
				break;
			default :
				angle = 0;
				break;
		}
		
		if(img != null)
		{
			rotation(drawable,img,angle,tailleCase+depart+x*tailleCase+1,tailleCase+depart+y*tailleCase+1,tailleCase-1);
		}
		
	}

	private void rotation(Graphics2D drawable, BufferedImage img, int angle, int x, int y, int taille)
	{
		double rotationRequired = Math.toRadians(angle);
		double locationX = img.getWidth() / 2;
		double locationY = img.getHeight() / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		
		drawable.drawImage(op.filter(img,null), x, y, taille, taille, this);		
	}

	public void activerContours() {
		contoursDessines = true;
		repaint();
	}
	
	public void desactiverContours() {
		contoursDessines = false;
		repaint();
	}
	
	private void nettoyage(Graphics2D drawable) {
		drawable.setColor(Color.white);
		drawable.fillRect(0, 0, getWidth(), getHeight());
	}

	private void colorCase(Graphics2D drawable) {
		drawable.setColor(Color.white);
		drawable.drawRect(caseX*50+depart, caseY*50+depart, 50,50);		
		caseX = -1;
		drawable.setColor(Color.gray);	
	}

	private void colorMain(Graphics2D drawable) {
		drawable.setColor(Color.white);
		if(main == 2){ drawable.drawRect(carte*depart+ecart, 20, tailleCase+20, tailleCase+20); }
		else drawable.drawRect(carte*depart+ecart, 820, tailleCase+20, tailleCase+20);
		main = -1;
		drawable.setColor(Color.gray);		
	}

	private void dessinerPlateau(Graphics2D drawable) {
		drawable.drawImage(plateau, depart, depart, depart+12*tailleCase,depart+12*tailleCase, this);
		
	}

	private void dessinerFond(Graphics2D drawable) {
		drawable.drawImage(fond, 0, 0, this.getWidth(), this.getHeight(), this);
		
	}

	private void dessinerMain1(Graphics2D drawable) {
		//Attention, ici c'est toujours la main de base, il faut remplacer par la main du joueur 1
		for(int i = 0;i<5;i++)
		{
			//drawable.drawRect(i*depart+ecart, 820, tailleCase+20, tailleCase+20);
			if(i == 0 || i == 1 || i == 2) drawable.drawImage(tuile001, i*depart+ecart, 820, tailleCase+20, tailleCase+20, this);
			else drawable.drawImage(tuile002, i*depart+ecart, 820, tailleCase+20, tailleCase+20, this);
		}
	}

	private void dessinerMain2(Graphics2D drawable) {
		//Attention, ici c'est toujours la main de base, il faut remplacer par la main du joueur 2
		for(int i = 0;i<5;i++)
		{
			//drawable.drawRect(i*depart+ecart, 20, tailleCase+20, tailleCase+20);
			if(i == 0 || i == 1 || i == 2) drawable.drawImage(tuile001, i*depart+ecart, 20, tailleCase+20, tailleCase+20, this);
			else drawable.drawImage(tuile002, i*depart+ecart, 20, tailleCase+20, tailleCase+20, this);
		}
	}
	
	private void initImage (){
		//Chargement des images
		fond = Constantes.Images.initBackground("tramOui.png");
		plateau = Constantes.Images.initBackground("plateau.png");
		tuile001 = Constantes.Images.initTuile("001.jpg");
		tuile002 = Constantes.Images.initTuile("002.jpg");
		tuile003 = Constantes.Images.initTuile("003.jpg");
		tuile004 = Constantes.Images.initTuile("004.jpg");
		tuile005 = Constantes.Images.initTuile("005.jpg");
		tuile006 = Constantes.Images.initTuile("006.jpg");
		tuile007 = Constantes.Images.initTuile("007.jpg");
		tuile008 = Constantes.Images.initTuile("008.jpg");
		tuile009 = Constantes.Images.initTuile("009.jpg");
		tuile010 = Constantes.Images.initTuile("010.jpg");
		tuile011 = Constantes.Images.initTuile("011.jpg");
	}
	
}
