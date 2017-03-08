package vue;

import controleur.CtrlAbstrait;
import javax.swing.JFrame;

/**
 * VueAbstraite
 * modèle de vue
 * - un lien vers le contrôleur
 * chaque contrôleur a un lien vers une vue
 * @author bts
 * @version  1.0
 */

public abstract class VueAbstraite extends JFrame{
    // associations
    protected CtrlAbstrait controleur=null;
    
    public VueAbstraite(CtrlAbstrait ctrl) {
        this.controleur = ctrl;
    }       
    
}
