
import java.sql.Connection;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Programme de démonstration de JDBC. Permet de se connecter à une BD Oracle et
 * de tester différentes commandes SQL sur des tables représentant les
 * consommations de café de différents programmeurs.
 *
 * Les différentes opérations possibles sont : <ol> <li>Plus gros consommateurs
 * de café sur une semaine</li> <li>Nombre total de tasse pour un programmeur
 * donné</li> <li>La liste triées des consommations sur une semaine</li>
 * <li>Saisir les consommations de tous les programmeurs pour une semaine
 * donnée</li> <li>Exécuter une requête quelconque saisie au clavier et afficher
 * ses résultats</li> </ol>
 *
 *
 * @author Samir AZZAG
 */
public class GestionCafes {

  /**
   * le scanner pour la saisie des données au clavier
   */
  private static Scanner sc = new Scanner(System.in);
  /**
   * la connexion jdbc utilisée pour effectuer les différentes requêtes
   */
  private static Connection conn = null;

  /**
   * Affiche le ou les programmeurs ayant consommé le nombre maximum de café en
   * une semaine et leur consommation pour cette semaine.
   */
  public static void plusGrosConsommateurs() {

    System.out.println("Les plus gros consommateurs de cafés sont :");
    // TODO
    System.out.println("Opération non encore implémentée");
  }

  /**
   * Affiche pour une semaine donnée la liste des programmeurs triée dans
   * l'ordre décroissant selon leur nombre de consommations et le nombre total
   * de tasses consommées cette semaine
   */
  public static void consommationsPourUneSemaine() {

    System.out.print("Numéro de la semaine : ");
    int numeroDeSemaine = sc.nextInt();
    // TODO
    System.out.println("Opération non encore implémentée");
  }

  /**
   * pour un programmeur donné affiche le nombre total de tasses de café
   * consommées.
   */
  public static void nbreTotalDeTasses() {

    System.out.print("Identifiant du programmeur : ");
    int idProgrammeur = sc.nextInt();
    //TODO
    System.out.println("Opération non encore implémentée");
  }

  /**
   * Saisit un numéro de semaine et ensuite pour chaque programmeur permet de
   * rentrer le nombre de tasses qu'il a consommées durant cette semaine.
   */
  public static void sasirConsommations() {

    System.out.print("Numéro de de la semaine : ");
    int noSemaine = sc.nextInt();
    //TODO
    System.out.println("Opération non encore implémentée");
  }

  /**
   * Exécute une requête libre définie par une chaîne donnée au clavier et
   * affiche les méta données concernant le résultat de cette requête
   * quelconque.<br/> <ul> <li>Si la command renvoie un ResultSet (Query) cette
   * méthode indique : <ul> <li>le nombre de colonnes, et pour chaque colonne le
   * nom et le type de la colonne.</li> <li>le contenu du resultSet est affiché
   * ligne par ligne sur la sortie standard.</li> </ul> </li> <li>Si la commande
   * ne renvoie pas un ResultSet (Update) cette méthode indique le nombre de
   * lignes de la table qui ont été modifiées. <li> </ul>
   */
  public static void requeteLibreEtMetaDonnees() {

    System.out.print("Rentrez votre requête : ");
    String cmd = sc.next() + sc.nextLine();
    // TODO
    System.out.println("Opération non encore implémentée");
  }

  /**
   * affiche le menu présentant les différentes opérations possibles
   */
  public static void affMenu() {
    System.out.println("\n\n------------------------------------------");
    System.out.println("1 : Plus gros consommateurs sur une semaine");
    System.out.println("2 : Nombre total de tasses consommées par un programmeur");
    System.out.println("3 : Consommations pour une semaine donnée");
    System.out.println("4 : Sasie des consommations pour une semaine");
    System.out.println("5 : Requête Libre ");
    System.out.println("0 : Quitter l'application");
  }

  public static void main(String[] args) {

    // TODO
    // chargement du driver JDBC
    // et ouverture d'une connexion

    System.out.println("Connexion réussie !");

    boolean encore = true;
    do {
      affMenu();
      try {
        System.out.print("votre choix : ");
        int rep = sc.nextInt();

        System.out.println("\n\n");

        switch (rep) {
          case 0:
            System.out.print("voulez-vous vraimment quitter le programme O/N ?");
            encore = sc.next().toUpperCase().charAt(0) != 'O';
            break;
          case 1:
            plusGrosConsommateurs();
            break;
          case 2:
            nbreTotalDeTasses();
            break;
          case 3:
            consommationsPourUneSemaine();
            break;
          case 4:
            sasirConsommations();
            break;
          case 5:
            requeteLibreEtMetaDonnees();
            break;
          default:
            System.out.println("valeur erronée !");
        }  // end switch
      } catch (InputMismatchException ex) {
        System.out.println("saisie incorrecte\nRecommencez !!");
        sc.nextLine();   // pour "vider" le scanner
      }
    } while (encore);

    // TODO
    // Fermer la connexion à la BD

  }
}
