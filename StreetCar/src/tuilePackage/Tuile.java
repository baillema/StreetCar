 package tuilePackage;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.text.html.HTMLDocument.Iterator;

import joueurPackage.MainJoueur;
import constantesPackages.Constantes;

public class Tuile implements ActionsToken{
	private boolean immuable;
	private String orientation;
	private ArrayList<Connection> listeConnections;
	BufferedImage imageTuile;
	
	/*
	 * 2 Constructeurs
	 */
	public Tuile (){
		immuable = false;
		orientation = Constantes.Orientation.nord;
		listeConnections = new ArrayList<Connection>();
	}
	
	public Tuile (boolean presenceArbres){
		immuable = presenceArbres;
		orientation = Constantes.Orientation.nord;
		listeConnections = new ArrayList<Connection>();
	}
	
	/*
	 * 12 Constructeur de tuile fixes
	 */
	public static Tuile newLigneDroite() {
		Tuile t = new Tuile(false);
		t.addConnection(new Connection(Constantes.Orientation.nord, Constantes.Orientation.sud));
		t.setImage(Constantes.Images.initTuile("ligneDroite.jpg"));
		return t;
	}

	public static Tuile newVirage() {
		Tuile t = new Tuile(false);
		t.addConnection(new Connection(Constantes.Orientation.est, Constantes.Orientation.nord));
		t.setImage(Constantes.Images.initTuile("virage.jpg"));
		return t;
	}

	public static Tuile newBifurcationDroite() {
		Tuile t = new Tuile(false);
		t.addConnection(new Connection(Constantes.Orientation.nord, Constantes.Orientation.sud));
		t.addConnection(new Connection(Constantes.Orientation.ouest, Constantes.Orientation.nord));
		t.setImage(Constantes.Images.initTuile("bifurcationDroite.jpg"));
		return t;
	}
	
	public static Tuile newBifurcationGauche() {
		Tuile t = new Tuile(false);
		t.addConnection(new Connection(Constantes.Orientation.nord, Constantes.Orientation.sud));
		t.addConnection(new Connection(Constantes.Orientation.est, Constantes.Orientation.nord));
		t.setImage(Constantes.Images.initTuile("bifurcationGauche.jpg"));
		return t;
	}
	
	public static Tuile newSeparation() {
		Tuile t = new Tuile(false);
		t.addConnection(new Connection(Constantes.Orientation.ouest, Constantes.Orientation.nord));
		t.addConnection(new Connection(Constantes.Orientation.est, Constantes.Orientation.nord));
		t.setImage(Constantes.Images.initTuile("separation.jpg"));
		return t;
	}
	
	public static Tuile newDoubleVirage() {
		Tuile t = new Tuile(false);
		t.addConnection(new Connection(Constantes.Orientation.ouest, Constantes.Orientation.sud));
		t.addConnection(new Connection(Constantes.Orientation.est, Constantes.Orientation.nord));
		t.setImage(Constantes.Images.initTuile("doubleVirage.jpg"));
		return t;
	}
	
	public static Tuile newDoubleBifurcation() {
		Tuile t = new Tuile(true);
		t.addConnection(new Connection(Constantes.Orientation.ouest, Constantes.Orientation.sud));
		t.addConnection(new Connection(Constantes.Orientation.est, Constantes.Orientation.sud));
		t.addConnection(new Connection(Constantes.Orientation.nord, Constantes.Orientation.sud));
		t.setImage(Constantes.Images.initTuile("doubleBifurcation.jpg"));
		return t;
	}
	
	public static Tuile newCroisement() {
		Tuile t = new Tuile(true);
		t.addConnection(new Connection(Constantes.Orientation.ouest, Constantes.Orientation.est));
		t.addConnection(new Connection(Constantes.Orientation.nord, Constantes.Orientation.sud));
		t.setImage(Constantes.Images.initTuile("croisement.jpg"));
		return t;
	}
	
	public static Tuile newBifurcationsSeparesGauche() {
		Tuile t = new Tuile(true);
		t.addConnection(new Connection(Constantes.Orientation.ouest, Constantes.Orientation.sud));
		t.addConnection(new Connection(Constantes.Orientation.est, Constantes.Orientation.nord));
		t.addConnection(new Connection(Constantes.Orientation.nord, Constantes.Orientation.sud));
		t.setImage(Constantes.Images.initTuile("bifurcationSepareGauche.jpg"));
		return t;
	}
	
	public static Tuile newBifurcationsSeparesDroite() {
		Tuile t = new Tuile(true);
		t.addConnection(new Connection(Constantes.Orientation.est, Constantes.Orientation.sud));
		t.addConnection(new Connection(Constantes.Orientation.ouest, Constantes.Orientation.nord));
		t.addConnection(new Connection(Constantes.Orientation.nord, Constantes.Orientation.sud));
		t.setImage(Constantes.Images.initTuile("bifurcationSepareDroite.jpg"));
		return t;
	}
	
	public static Tuile newQuadrupleVirages() {
		Tuile t = new Tuile(true);
		t.addConnection(new Connection(Constantes.Orientation.est, Constantes.Orientation.sud));
		t.addConnection(new Connection(Constantes.Orientation.ouest, Constantes.Orientation.nord));
		t.addConnection(new Connection(Constantes.Orientation.ouest, Constantes.Orientation.sud));
		t.addConnection(new Connection(Constantes.Orientation.est, Constantes.Orientation.nord));
		t.setImage(Constantes.Images.initTuile("quadrupleVirage.jpg"));
		return t;
	}
	
	public static Tuile newBifurcationsEmbrassees() {
		Tuile t = new Tuile(true);
		t.addConnection(new Connection(Constantes.Orientation.est, Constantes.Orientation.nord));
		t.addConnection(new Connection(Constantes.Orientation.ouest, Constantes.Orientation.est));
		t.addConnection(new Connection(Constantes.Orientation.ouest, Constantes.Orientation.nord));
		t.setImage(Constantes.Images.initTuile("bifurcationsEmbrassees.jpg"));
		return t;
	}
	
	/*
	 * les 6 Accesseurs
	 */
	public boolean getPresenceArbres (){
		return immuable;
	}
	
	public ArrayList<Connection> getListeConnections (){
		return listeConnections;
	}
	
	public String getOrientation (){
		return orientation;
	}	
	
	public BufferedImage getImage (){
		return imageTuile;
	}
	
	public void setImage (BufferedImage i){
		imageTuile = i;
	}
	
	public void setPresenceArbres (boolean newPresence){
		immuable = newPresence;
	}
	
	public void setListeConnections (ArrayList<Connection> newListe){
		if ( !listeConnections.isEmpty() ){
			listeConnections.clear();
		}
		if ( listeConnections.addAll(newListe) ){
			System.out.println("update de la liste ok");
		}
		else {
			System.out.println("update refuse/annule");
		}
	}
	
	public void setOrientation (String newOrientation){
		orientation = new String(newOrientation);
	}
	
	public void setImage (String nomImage, boolean backGround){
		if (backGround){
			imageTuile = Constantes.Images.initBackground(nomImage);
		}
		else {
			imageTuile = Constantes.Images.initTuile(nomImage);
		}
	}
	
	
	
	/*
	 * Methodes de l'objet
	 */
	
	public void addConnection (Connection nouvelleConnection){
		listeConnections.add(nouvelleConnection);
	}
	
	/**
	 * A FAIRE/FINIR <== EST FINI
	 * @param nouvTuile
	 * @return
	 */
	public boolean canConnectTo (Tuile nouvTuile, String cote){
		boolean connectionTrouvee = false;
		System.out.println(this+" "+nouvTuile+" "+cote);
		switch (cote) {
		case Constantes.Orientation.nord:
			connectionTrouvee = ( this.connectionsExistantes(Constantes.Orientation.nord) && nouvTuile.connectionsExistantes(Constantes.Orientation.sud) )
						     || ( !this.connectionsExistantes(Constantes.Orientation.nord) && !nouvTuile.connectionsExistantes(Constantes.Orientation.sud) );
			break;
		case Constantes.Orientation.sud:
			connectionTrouvee = ( this.connectionsExistantes(Constantes.Orientation.sud) && nouvTuile.connectionsExistantes(Constantes.Orientation.nord) )
							 || ( !this.connectionsExistantes(Constantes.Orientation.sud) && !nouvTuile.connectionsExistantes(Constantes.Orientation.nord) );
			break;
		case Constantes.Orientation.est:
			connectionTrouvee = ( this.connectionsExistantes(Constantes.Orientation.est) && nouvTuile.connectionsExistantes(Constantes.Orientation.ouest) )	
							 || ( !this.connectionsExistantes(Constantes.Orientation.est) && !nouvTuile.connectionsExistantes(Constantes.Orientation.ouest) );
			break;
		case Constantes.Orientation.ouest:
			connectionTrouvee = ( this.connectionsExistantes(Constantes.Orientation.ouest) && nouvTuile.connectionsExistantes(Constantes.Orientation.est) )
							 || ( !this.connectionsExistantes(Constantes.Orientation.ouest) && !nouvTuile.connectionsExistantes(Constantes.Orientation.est) );
			break;
		default:
			throw new RuntimeException("cote non indique pour adjacence des Tuiles");
		}
		
		
		return connectionTrouvee;
	}
	
	public void rotation(String sensRotation) {
		switch (sensRotation){
		case Constantes.Rotation.rotationDroite:
			effectuerRotationDroite();
			break;
		case Constantes.Rotation.rotationGauche:
			effectuerRotationGauche();
			break;
		default:
			break;
		}
	}
	
	public String toString (){
		String resultat = "{";
		
		resultat = resultat + orientation + ",";
		resultat = resultat + listeConnections.toString()+ "}";
		
		return resultat;
	}
	
	public boolean equals (Tuile autreTuile){
		boolean resultat = false;
		
		resultat = ( immuable == autreTuile.getPresenceArbres() && listesIdentiques(autreTuile.getListeConnections()) );
		
		return resultat;
	}
	
	public Tuile clone (){
		Tuile newTuile = new Tuile(immuable);
		
		newTuile.setListeConnections(listeConnections);
		
		return newTuile;
	}
	
	
	/*
	 * Methodes privees de la classe
	 */
	
	/**
	 * retourne vrai si la liste appelante et celle en parametre sont identiques
	 * @param autreListe : liste de connections qui sera comparee avec la liste de la tuile appelante
	 * @return true : les 2 listes sont identiques
	 */
	/* /!\ ATTENTION /!\ CORRIGEE MAIS A REVERIFIER
	 * sous quelles conditions 2 tuiles sont elles identiques ???
	 * 		=> sous quelles conditions 2 listes de connections sont identiques ???
	 * 
	 * 
	 */
	private boolean listesIdentiques (ArrayList<Connection> autreListe){
		boolean estContenu = false;
		
		ListIterator<Connection> iterateurConnections = listeConnections.listIterator();
		while ( iterateurConnections.hasNext() && !estContenu ){
			ListIterator<Connection> iterateurAutresConnections = autreListe.listIterator();
			Connection connection1 = iterateurConnections.next();
			while ( iterateurAutresConnections.hasNext() && !estContenu){
				estContenu = estContenu || connection1.equals(iterateurAutresConnections.next());
			}
			if (estContenu){
				System.out.println("connection " + connection1.toString() + " ou une identique se trouve dans la liste donnee en parametre");
				estContenu = false;
			}
			else {
				System.out.println("aucune connection identique a " + connection1.toString() + " dans la liste donnee en parametre");
			}
		}
		
		return estContenu;
	}
	
	/**
	 * modifie l'orientation de la tuile appelante d'un cran dans le sens horaire
	 */
	private void effectuerRotationDroite (){
		switch (orientation){
		case Constantes.Orientation.nord:
			orientation = Constantes.Orientation.est;
			break;
		case Constantes.Orientation.sud:
			orientation = Constantes.Orientation.ouest;
			break;
		case Constantes.Orientation.est:
			orientation = Constantes.Orientation.sud;
			break;
		case Constantes.Orientation.ouest:
			orientation = Constantes.Orientation.nord;
			break;
		default:
			break;
		}
		rotationDroiteConnections();
	}
	
	/**
	 * modifie l'orientation de la tuile appelante d'un cran dans le sens anti-horaire
	 */
	private void effectuerRotationGauche (){
		switch (orientation){
		case Constantes.Orientation.nord:
			orientation = Constantes.Orientation.ouest;
			break;
		case Constantes.Orientation.sud:
			orientation = Constantes.Orientation.est;
			break;
		case Constantes.Orientation.est:
			orientation = Constantes.Orientation.nord;
			break;
		case Constantes.Orientation.ouest:
			orientation = Constantes.Orientation.sud;
			break;
		default:
			break;
		}
		rotationGaucheConnections();
	}
	
	/**
	 * tourne toutes les connections de la tuile appelante d'un cran dans le sens horaire 
	 */
	private void rotationDroiteConnections (){
		ListIterator<Connection> iterateurConnections = listeConnections.listIterator();
		while ( iterateurConnections.hasNext() ){
			iterateurConnections.next().rotationDroite();
		}
	}
	
	/**
	 * tourne toutes les connections de la tuile appelante d'un cran dans le sens anti-horaire
	 */
	private void rotationGaucheConnections (){
		ListIterator<Connection> iterateurConnections = listeConnections.listIterator();
		while ( iterateurConnections.hasNext() ){
			iterateurConnections.next().rotationGauche();
		}
	}
	
	/**
	 * retourne vrai si la tuile appelante possede au moins une connexion avec l'une de ses extremites
	 * sur le cote indique
	 * retourne faux sinon
	 * @param cote : chaine de caracteres indiquant sur quel cote doit etre chercher l'extremite des connections
	 * @return true : une connection possede une extremite est sur le cote indique, false sinon.
	 */
	private boolean connectionsExistantes (String cote){
		boolean connectionTrouvee = false;
		ListIterator<Connection> iterateurConnections = listeConnections.listIterator();
		
		switch (cote){
		case Constantes.Orientation.nord:
			while ( iterateurConnections.hasNext() && !connectionTrouvee){
				connectionTrouvee = iterateurConnections.next().isConnectedTo(Constantes.Orientation.nord);
			}
			break;
		case Constantes.Orientation.sud:
			while ( iterateurConnections.hasNext() && !connectionTrouvee){
				connectionTrouvee = iterateurConnections.next().isConnectedTo(Constantes.Orientation.sud);
			}
			break;
		case Constantes.Orientation.est:
			while ( iterateurConnections.hasNext() && !connectionTrouvee){
				connectionTrouvee = iterateurConnections.next().isConnectedTo(Constantes.Orientation.est);
			}
			break;
		case Constantes.Orientation.ouest:
			while ( iterateurConnections.hasNext() && !connectionTrouvee){
				connectionTrouvee = iterateurConnections.next().isConnectedTo(Constantes.Orientation.ouest);
			}
			break;
		default:
			throw new RuntimeException("cote non indique pour adjacence des connections");
		}
		
		return connectionTrouvee;
	}

	@SuppressWarnings("unchecked")
	public boolean canReplace(Tuile ancTuile) {
		
		boolean yesItCan = true;
		ArrayList<Connection> listeConnections = (ArrayList<Connection>) this.listeConnections.clone();
		
		for (Connection c : ancTuile.getListeConnections()) {
			boolean thisOneIsHere = false;
			ListIterator<Connection> i = (ListIterator<Connection>) listeConnections.listIterator();
			Connection c2;
			while ((i.hasNext()) && !thisOneIsHere) {
				if ((c2 = i.next()).equals(c)) {
					thisOneIsHere = true;
					listeConnections.remove(c2);
				}
			}
			yesItCan &= thisOneIsHere;
		}
		yesItCan &= !listeConnections.isEmpty();
		return yesItCan;
	}
}
