package controleur;

import static controleur.EnumAction.*;
import javax.swing.JOptionPane;
import modele.jdbc.Jdbc;

/**
 * Controleur principal (ou frontal) - un lien vers chaque contrôleur de base
 *
 * @author bts
 * @version 1.0
 */
public class CtrlPrincipal {

    private CtrlVisiteur ctrlVisiteur = null;
    private CtrlMenu ctrlMenu = null;

    /**
     * action par défaut action au démarrage de l'application
     */
    public void action() {
        if (ctrlMenu == null) {
            ctrlMenu = new CtrlMenu(this);
        }
        ctrlMenu.getVue().setEnabled(true);
        ctrlMenu.getVue().setVisible(true);
    }

    public void action(EnumAction action) {
        switch (action) {
            case MENU_VISITEUR: // activation de vueVisiteur depuis vueMenu
                menuVisiteur();
                break;
            case VISITEUR_FERMER: // retour à vueMenu depuis la vueVisiteur
                visiteurFermer();
                break;
            case MENU_QUITTER: // fin de l'application depuis vueMenu
                menuQuitter();
                break;
        }
    }

    /**
     * Fin définitive de l'application La demande de confirmation est gérée par
     * le contrôleur spécialisé
     */
    private void menuQuitter() {
        try {
            Jdbc.getInstance().deconnecter();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "CtrlPrincipal - fermeture connexion BD", JOptionPane.ERROR_MESSAGE);
        } finally {
            System.exit(0);
        }
    }

    /**
     * Transition vueMenu / vueVisiteur
     */
    private void menuVisiteur() {
        if (ctrlVisiteur == null) {
            ctrlVisiteur = new CtrlVisiteur(this);
        } else {
            // si la le contrôleur et sa vue existent déjà
            // il faut rafraîchir le contenu à partir de la base de données
            ctrlVisiteur.actualiser();
        }
        // vueVisiteur est une fenêtre modale :
        // -> vueMenu reste visible, mais n'est pas active
        ctrlMenu.getVue().setEnabled(false);
        ctrlVisiteur.getVue().setVisible(true);
    }

    /**
     * Transition vueVisiteur / vueMenu
     */
    private void visiteurFermer() {
        if (ctrlMenu == null) {
            ctrlMenu = new CtrlMenu(this);
        }
        ctrlVisiteur.getVue().setVisible(false);
        ctrlMenu.getVue().setEnabled(true);
        ctrlMenu.getVue().setVisible(true);
    }
}
