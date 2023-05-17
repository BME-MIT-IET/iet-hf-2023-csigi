package vilagtalan_virologusok;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    /**
     * A hierarchia legfelső, mindent magába foglaló panelje
     */
    private JPanel a;
    /**
     * A lépés akciót megvalósító gomb
     */
    private JButton lepButton;
    /**
     * Az ágens kenése akciót megvalósító gomb
     */
    private JButton agenstKenButton;
    /**
     * A kód letapogatása labor faláról akciót megvalósító gomb
     */
    private JButton letapogatButton;
    /**
     * A védőfelszerelés felvétele akciót megvalósító gomb
     */
    private JButton vedofelszerelestFelveszButton;
    /**
     * Új játék indítását lehetővé tevő gomb
     */
    private JButton ujJatekButton;
    /**
     * A saját köröd befejezését lehetővé tevő gomb, ilyenkor a soronkövetkező játékos lesz az aktív játékos
     */
    private JButton kovetkezoJatekosButton;
    /**
     * Nem szerkeszthető szövegdoboz, az aktív virológussal egy mezőn álló virológusokat mutatja
     */
    private JTextField virologusokTextField;
    /**
     * Nem szerkeszthető szövegdoboz, az aktív virológus mezőjével szomszédos mezőket mutatja
     */
    private JTextField szomszedokTextField;
    /**
     * Az anyag felvétele akciót megvalósító gomb
     */
    private JButton anyagotFelveszButton;
    /**
     * Nem szerkeszthető szövegdoboz, az aktív virológusnál lévő védőfelszereléseket mutatja
     */
    private JTextField vedofelszekTextField;
    /**
     * Nem szerkeszthető szövegdoboz, az aktív virológus által ismert kódokat mutatja
     */
    private JTextField kodokTextField;
    /**
     * A másik virológus megtámadása akciót megvalósító gomb
     */
    private JButton megtamadButton;
    /**
     * Felirat az anyagmennyiséget jelző szám mellett magyarázatképp
     */
    private JLabel AnyagLabel;
    /**
     * Nem szerkeszthető szövegdoboz, az aktív virológus anyagkészletét mutatja
     */
    private JTextField AnyagTF;
    /**
     * A baltázás akciót megvalósító gomb
     */
    private JButton baltazButton;
    /**
     * Ennek a felirata jelzi, hogy mi az épp aktív játékos neve
     */
    private JLabel currPlayerLabel;
    /**
     * Felirat, amely annak az aktuális mező típusát megjelenítő szövegdobozt magyarázza
     */
    private JLabel MezoTypeLabel;
    /**
     * Nem szerkeszthető szövegdoboz, amely annak a mezőnek a típusát jeleníti meg, amin a soron lévő játékos virológusa áll.
     */
    private JTextField MezoTypeTF;
    /**
     * Védőfelszerelés eldobását lehetővé tévő gomb
     */
    private JButton VedofelszEldobButton;

    /**
     * A létrehozást, megjelenítést elvégző függvény
     * @param args a program indulásakor betáplált paraméter
     */
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setContentPane(frame.a);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Konstruktor, beállítja a címet, meghívja a komponenseket generáló függvényt.
     */
    public MainFrame() {
        super("Világtalan virológusok");
        createUIComponents();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Az egyes gombokhoz actionListenert rendel. Kiíratja a szövegdobozokba az oda illő adatokat.
     */
    private void createUIComponents() {
        // A megtámadási szándékot kezelő actionListener, megjelenít egy ilyen ablakot
        megtamadButton.addActionListener(e -> {
            TamadFrame frame = new TamadFrame();
            frame.pack();
            frame.setVisible(true);
        });

        // A lépési szándékot kezelő actionListener, megjelenít egy ilyen ablakot
        lepButton.addActionListener(e -> {
            LepFrame frame = new LepFrame(this);
            frame.pack();
            frame.setVisible(true);
        });

        //Agenssel velo kenes gombjanak kezelese, pop-up window letrehozasa
        agenstKenButton.addActionListener(e -> {
            KenFrame frame = new KenFrame(this);
            frame.pack();
            frame.setVisible(true);
        });

        // Az új játék indításának kérését kezelő actionListener, megjelenít egy ilyen ablakot
        ujJatekButton.addActionListener(e -> {
            NewGameFrame frame = new NewGameFrame(this);
            frame.pack();
            frame.setVisible(true);
        });

        // A baltázási szándékot kezelő actionListener, megjelenít egy ilyen ablakot
        baltazButton.addActionListener(e -> {
            BaltaFrame frame = new BaltaFrame(this);
            frame.pack();
            frame.setVisible(true);
        });

        // A védőfelszerelés eldobását kezelő actionListener, megjelenít egy ilyen ablakot
        VedofelszEldobButton.addActionListener(e -> {
            VedofelszEldobFrame frame = new VedofelszEldobFrame(this);
            frame.pack();
            frame.setVisible(true);
        });


        /*
         * A letapogat gomb megnyomásakor meghívjuk a controller letapogatást végző függvényét,
         * ha hibát kapunk, kiírjuk egy üzenetablakban, majd frissítjük az ablakot
         * @author Benczik
         */
        letapogatButton.addActionListener(e -> {
            String error = Controller.getcode();
            if (!error.equals("")) {
                JOptionPane.showMessageDialog(a, error);
            }
            this.update();
        });

        // A védőfelszerelés felvételének szándékát kezelő actionListener
        vedofelszerelestFelveszButton.addActionListener(e -> {
            String error = Controller.getEquipment();
            if (!error.equals("")) {
                JOptionPane.showMessageDialog(a, error);
            }
            this.update();
        });

        // Az anyagfelvételi kérést kezelő actionListener
        anyagotFelveszButton.addActionListener(e -> {
            String error = Controller.getMatter();
            if (!error.equals("")) {
                JOptionPane.showMessageDialog(a, error);
            }
            this.update();
        });

        // A következő játékos kérését kezelő actionListener
        kovetkezoJatekosButton.addActionListener(e -> {
            Controller.nextPlayer();
            this.update();
        });

        this.setContentPane(this.a);
        update();
        this.pack();
        this.setVisible(true);
    }

    /**
     * A kiírt értékek aktualizálása újbóli lekérdezéssel a Jatek aktuális állapota alapján
     */
    public void update() {
        // Soron lévő virológus ID-jának kiírása
        currPlayerLabel.setText("a " + Controller.getActiveVirologusId() + " azonosítójú virológus köre van");

        printToTF(virologusokTextField, Controller.listNgh());
        virologusokTextField.setEditable(false);
        printToTF(szomszedokTextField, Controller.listNghMezo());
        szomszedokTextField.setEditable(false);
        printToTF(vedofelszekTextField, Controller.listeq());
        vedofelszekTextField.setEditable(false);
        printToTF(kodokTextField, Controller.listcodes());
        kodokTextField.setEditable(false);

        AnyagTF.setText(Integer.toString(Controller.showmatter(Controller.getActiveVirologusId())));
        AnyagTF.setEditable(false);

        MezoTypeTF.setText(Controller.getMostMezoType() + ", id: " + Controller.getMostMezoId());
        MezoTypeTF.setEditable(false);
    }

    /**
     * Listázza a megadott szövegdobozba a megadott lista tartalmát
     *
     * @param tf    Ide történik a listázás
     * @param lista Ezt a string listát írja ki
     */
    private void printToTF(JTextField tf, ArrayList<String> lista) {
        tf.setText("");
        if (lista != null) {
            if (lista.size() == 0)
                tf.setText("-");
            else {
                for (int i = 0; i < lista.size(); ++i) {
                    if (lista.size() > 1 && i < lista.size() - 1)
                        tf.setText(tf.getText() + lista.get(i) + ", ");
                    else
                        tf.setText(tf.getText() + lista.get(i));
                }
            }
        }
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        a = new JPanel();
        a.setLayout(new GridBagLayout());
        lepButton = new JButton();
        lepButton.setText("Lép");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        a.add(lepButton, gbc);
        agenstKenButton = new JButton();
        agenstKenButton.setText("Ágenst ken");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        a.add(agenstKenButton, gbc);
        letapogatButton = new JButton();
        letapogatButton.setText("Letapogat");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        a.add(letapogatButton, gbc);
        vedofelszerelestFelveszButton = new JButton();
        vedofelszerelestFelveszButton.setText("Védőfelszerelést felvesz");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        a.add(vedofelszerelestFelveszButton, gbc);
        ujJatekButton = new JButton();
        ujJatekButton.setText("Új játék");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        a.add(ujJatekButton, gbc);
        kovetkezoJatekosButton = new JButton();
        kovetkezoJatekosButton.setText("Következő játékos");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        a.add(kovetkezoJatekosButton, gbc);
        final JLabel label1 = new JLabel();
        label1.setText("Virológusok a mezőn:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        a.add(label1, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("Szomszédos mezők:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        a.add(label2, gbc);
        virologusokTextField = new JTextField();
        virologusokTextField.setEditable(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 6;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        a.add(virologusokTextField, gbc);
        szomszedokTextField = new JTextField();
        szomszedokTextField.setEditable(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 6;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        a.add(szomszedokTextField, gbc);
        final JLabel label3 = new JLabel();
        label3.setText("Védofelszereléseid:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        a.add(label3, gbc);
        vedofelszekTextField = new JTextField();
        vedofelszekTextField.setEditable(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 6;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        a.add(vedofelszekTextField, gbc);
        anyagotFelveszButton = new JButton();
        anyagotFelveszButton.setText("Anyagot felvesz");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        a.add(anyagotFelveszButton, gbc);
        megtamadButton = new JButton();
        megtamadButton.setText("Megtámad");
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        a.add(megtamadButton, gbc);
        kodokTextField = new JTextField();
        kodokTextField.setEditable(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 6;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        a.add(kodokTextField, gbc);
        AnyagLabel = new JLabel();
        AnyagLabel.setText("Anyagod:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        a.add(AnyagLabel, gbc);
        final JLabel label4 = new JLabel();
        label4.setHorizontalAlignment(10);
        label4.setHorizontalTextPosition(11);
        label4.setText("Ismert kódjaid:");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        a.add(label4, gbc);
        AnyagTF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        a.add(AnyagTF, gbc);
        baltazButton = new JButton();
        baltazButton.setText("Baltáz");
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        a.add(baltazButton, gbc);
        MezoTypeLabel = new JLabel();
        MezoTypeLabel.setText("Jelenlegi meződ típusa:");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        a.add(MezoTypeLabel, gbc);
        MezoTypeTF = new JTextField();
        MezoTypeTF.setEditable(true);
        MezoTypeTF.setEnabled(true);
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        a.add(MezoTypeTF, gbc);
        VedofelszEldobButton = new JButton();
        VedofelszEldobButton.setText("Védőfelszerelés eldobása");
        gbc = new GridBagConstraints();
        gbc.gridx = 7;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        a.add(VedofelszEldobButton, gbc);
        currPlayerLabel = new JLabel();
        currPlayerLabel.setText("a 0 azonosítójú virológus köre van");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        a.add(currPlayerLabel, gbc);
        label4.setLabelFor(kodokTextField);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return a;
    }

}
