package controleur;

import javax.swing.JOptionPane;
import vue.VueMenu;

/**
 * Contrôleur de la fenêtre VueMenu
 * @author bts
 * @version 1.0
 */

public class CtrlMenu extends CtrlAbstrait {

    public CtrlMenu(CtrlPrincipal ctrlPrincipal) {
        super(ctrlPrincipal);
        vue = new VueMenu(this);
    }

    /**
     * clic sur le bouton Quitter de la vue principale Le contrôleur délègue
     * l'action au contrôleur frontal
     */
    public void menuQuitter() {
        // Confirmer avant de quitter
        int rep = JOptionPane.showConfirmDialog(getVue(), "Quitter l'application\nEtes-vous sûr(e) ?", "GSB", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (rep == JOptionPane.YES_OPTION) {
            // mettre fin à l'application
            this.getCtrlPrincipal().action(EnumAction.MENU_QUITTER);
        }
    }

    /**
     * clic sur le bouton Visiteur de la vue Principale Le contrôleur délègue
     * l'action au contrôleur frontal
     */
    public void menuVisiteur() {
        this.getCtrlPrincipal().action(EnumAction.MENU_VISITEUR);
    }

    @Override
    public VueMenu getVue() {
        return (VueMenu) vue;
    }
}
