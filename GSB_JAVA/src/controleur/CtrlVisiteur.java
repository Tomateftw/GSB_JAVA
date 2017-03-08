package controleur;

import java.util.List;
import javax.swing.JOptionPane;
import modele.dao.*;
import modele.metier.*;
import vue.VueVisiteur;


/**
 * Contrôleur de la fenêtre VueVisiteur
 *
 */
public class CtrlVisiteur extends CtrlAbstrait {
    
   private DaoVisiteur daoVisiteur = new DaoVisiteur();

    /**
     *
     * @param ctrlPrincipal
     */
    public CtrlVisiteur(CtrlPrincipal ctrlPrincipal) {
        super(ctrlPrincipal);
        vue = new VueVisiteur(this);
        actualiser();
    }

    public final void actualiser() {
        try {
            chargerListeVisiteurs();
        } catch (DaoException ex) {
            JOptionPane.showMessageDialog(getVue(), "CtrlVisiteur - actualiser - " + ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * visiteurFermer réaction au clic sur le bouton Fermer de la vueVisiteur 
     * Le contrôle est rendu au contrôleur frontal
     */
    public void visiteurFermer() {
        this.getCtrlPrincipal().action(EnumAction.VISITEUR_FERMER);
    }
   /**Bouton suivant. rajoute +1 a chaque utilisateur 
    * 
    */
    public void visiteurSuivant(){
        int index = getVue().getjComboBoxVisiteur().getSelectedIndex()+1;
        if(index== getVue().getjComboBoxVisiteur().getItemCount())index=0;
        getVue().getjComboBoxVisiteur().setSelectedIndex(index);
    }
    
    /**
     Bouton précédent enleve -1 a chaque utilisateur
     */
    public void visiteurPrecedent(){
        int index = getVue().getjComboBoxVisiteur().getSelectedIndex()-1;
        if(index== -1) index=getVue() .getjComboBoxVisiteur() .getItemCount() -1;
        getVue().getjComboBoxVisiteur().setSelectedIndex(index);
    }
    /**
     * chargerListeVisiteurs renseigner le modèle du composant jComboBoxVisiteur
     * à partir de la base de données
     *
     * @throws DaoException
     */
    private void chargerListeVisiteurs() throws DaoException {
        List<Visiteur> desVisiteurs = daoVisiteur.getAll();
        getVue().getModeleJComboBoxVisiteur().removeAllElements();
        for (Visiteur unVisiteur : desVisiteurs) {
            getVue().getModeleJComboBoxVisiteur().addElement(unVisiteur);
        }
    }
    /**
     * visiteurSelectionner renseigner le modèle des composants
     * JTextField (TxtNom, TxtPrenom, TxtAdrs, TxtVille, TxtCp),
     * JComboBoxSecteur, JComboBoxLabo
     *
     */
    public void visiteurSelectionner (){
        Visiteur visiteurSelect = (Visiteur) getVue().getjComboBoxVisiteur().getSelectedItem();
        if (visiteurSelect != null){
            
            getVue().getTxtNom().setText(visiteurSelect.getNom());
            getVue().getTxtPrenom().setText(visiteurSelect.getPrenom());
            getVue().getTxtAdrs().setText(visiteurSelect.getAdresse());
            getVue().getTxtVille().setText(visiteurSelect.getVille());
            getVue().getTxtCp().setText(visiteurSelect.getCp());
            getVue().getModeleJComboBoxSecteur().setSelectedItem(visiteurSelect.getSecteur());
            getVue().getModeleJComboBoxLabo().setSelectedItem(visiteurSelect.getLabo());
        }       
    }
   
    @Override
    public VueVisiteur getVue() {
        return (VueVisiteur) vue;
    }
}