package controleur;

import java.util.List;
import javax.swing.JOptionPane;
import modele.dao.*;
import modele.metier.*;
import vue.VueCmptRendu;


/**
 * Contrôleur de la fenêtre VueVisiteur
 *
 */
public class CtrlCmptRendu extends CtrlAbstrait {
    
   private DaoCmptRendu daoCmptRendu = new DaoCmptRendu();
   private DaoOffrir daoOffrir = new DaoOffrir();
    /**
     *
     * @param ctrlPrincipal
     */
    public CtrlCmptRendu(CtrlPrincipal ctrlPrincipal) {
        super(ctrlPrincipal);
        vue = new VueCmptRendu(this);
        actualiser();
    }

    public final void actualiser() {
        try {
            chargerListeCmptRendus();
        } catch (DaoException ex) {
            JOptionPane.showMessageDialog(getVue(), "CtrlCmptRendu - actualiser - " + ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * visiteurFermer réaction au clic sur le bouton Fermer de la vueVisiteur 
     * Le contrôle est rendu au contrôleur frontal
     */
    public void cmptRenduFermer() {
        this.getCtrlPrincipal().action(EnumAction.CMPTRENDU_FERMER);
    }
   /**Bouton suivant. rajoute +1 a chaque utilisateur 
    * 
    */
    public void cmptRenduSuivant(){
        int index = getVue().getjComboBoxCmptRendu().getSelectedIndex()+1;
        if(index== getVue().getjComboBoxCmptRendu().getItemCount())index=0;
        getVue().getjComboBoxCmptRendu().setSelectedIndex(index);
    }
    /**
     Bouton précédent enleve -1 a chaque utilisateur
     */
    public void cmptRenduPrecedent(){
        int index = getVue().getjComboBoxCmptRendu().getSelectedIndex()-1;
        if(index== -1) index=getVue() .getjComboBoxCmptRendu() .getItemCount() -1;
        getVue().getjComboBoxCmptRendu().setSelectedIndex(index);
    }
    /**
     * chargerListeVisiteurs renseigner le modèle du composant jComboBoxVisiteur
     * à partir de la base de données
     *
     * @throws DaoException
     */
    private void chargerListeCmptRendus() throws DaoException {
        List<CmptRendu> desCmptRendus = daoCmptRendu.getAll();
        getVue().getModeleJComboBoxCmptRendu().removeAllElements();
        for (CmptRendu unCmptRendu : desCmptRendus) {
            getVue().getModeleJComboBoxCmptRendu().addElement(unCmptRendu);
        }
    }
    /**
     * visiteurSelectionner renseigner le modèle des composants
     * JTextField (TxtNom, TxtPrenom, TxtAdrs, TxtVille, TxtCp),
     * JComboBoxSecteur, JComboBoxLabo
     *
     */
    public void cmptRenduSelectionner (){
        CmptRendu cmptRenduSelect = (CmptRendu) getVue().getjComboBoxCmptRendu().getSelectedItem();
        if (cmptRenduSelect != null){
            
            getVue().getModeleJComboBoxPraticien().setSelectedItem(cmptRenduSelect.getPraticien());
            getVue().getModeleJComboBoxMedicament().setSelectedItem(cmptRenduSelect.getMedicament());
            getVue().getTxtDate().setText(cmptRenduSelect.getDate());
            getVue().getTxtMotif().setText(cmptRenduSelect.getMotif());
            getVue().getjTextAreaBilan().setText(cmptRenduSelect.getBilan());  
        }       
    }
   
    @Override
    public VueCmptRendu getVue() {
        return (VueCmptRendu) vue;
    }
}