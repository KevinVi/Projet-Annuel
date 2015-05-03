package Modèles;

public class ZDialogInfo {
	  private String nom, surface, com, login, mdp;

	  public ZDialogInfo(){}
	  public ZDialogInfo(String nom, String surface, String com, String login, String mdp){
	    this.nom = nom;
	    this.surface = surface;
	    this.com = com;
	    this.login = login;
	    this.mdp = mdp;
	  }

	  public String toString(){
	    String str;
	    if(this.nom != null && this.surface != null && this.com != null && this.login != null && this.mdp != null){
	      str = "Description de l'objet InfoZDialog";
	      str += "Nom : " + this.nom + "\n";
	      str += "Surface : " + this.surface + "\n";
	      str += "Commentaire : " + this.com + "\n";
	      str += "Login : " + this.login + "\n";
	      str += "Mdp : " + this.mdp + "\n";
	    }
	    else{
	      str = "Aucune information !";
	    }
	    return str;
	  }
	}