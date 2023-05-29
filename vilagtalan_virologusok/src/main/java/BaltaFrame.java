import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * A baltázás célpontját kiválasztó párbeszédablakot valósít meg.
 * Egy ComboBox-ból tudjuk kiválasztani azt a virológust, akit le akarunk baltázni, majd OK gombot nyomva tudjuk véglegesíteni a döntésünket
 *
 * @author Benczik
 */
public class BaltaFrame extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox comboBox1 = new JComboBox();
    private JLabel feliratLabel;
    private JPanel panel;
    private JPanel buttonsPanel;

    /**
     * Konstruktor, itt valósítjuk meg az eseménykezelőt, amely egy lambda lesz,
     * kezeli, hogy az egyes gombok lenyomásánál mit kell tenni
     *
     * @param src Az eredeti frame referenciája (frissítéshez szükséges)
     */
    public BaltaFrame(MainFrame src) {
        $$$setupUI$$$();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
/**
 * Az OK gomb megnyomásakor a következőket tesszük:
 * A comboboxban kiválasztott elemmel, mint paraméterrel meghívjuk a controller useAxe() függvényét
 * Ha annak üres string a visszatérési értéke, akkor sikeresek voltunk, becsukhatjuk a BaltaFrame-t (onOK() meghívása)
 * Ha nem üres stringet return-öl, akkor azt megjelenítjük egy üzenetablakban a hibaüzenetet
 * @author Benczik
 */
        buttonOK.addActionListener(_event -> {
                try {
                    String args = comboBox1.getSelectedItem().toString();
                    String error = Controller.useAxe(args);
                    if (!error.equals(""))
                        JOptionPane.showMessageDialog(contentPane, error);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(contentPane, ex.getMessage());
                }
                onOK();
            }
        );
/**
 * A Cancel a gomb megnyomásakor nem teszünk semmit, meghívjuk az onCancel fgv-t, ami eldobja a Frame-t
 */
        buttonCancel.addActionListener(__event -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(_event -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    /**
     * Beállítja az ablak címét, valamint feltölti a comboboxot az aktív virológussal egy mezőn álló virológusok id-jaival
     *
     * @author Benczik
     */
    private void createUIComponents() {
        setTitle("Balta használata");
        if (Controller.listNgh() == null) {
            System.out.println("nincs szomszédos virologus");
        } else {
            for (Object s : Controller.listNgh().toArray()) {
                comboBox1.addItem((String) s);
            }
        }
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        contentPane = new JPanel();
        contentPane.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(10, 10, 10, 10), -1, -1));
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(buttonsPanel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1, true, false));
        buttonsPanel.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonOK = new JButton();
        buttonOK.setText("OK");
        panel1.add(buttonOK, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonCancel = new JButton();
        buttonCancel.setText("Cancel");
        panel1.add(buttonCancel, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panel = new JPanel();
        panel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        feliratLabel = new JLabel();
        feliratLabel.setText("Melyik azonosítójú virológust szeretnéd lebaltázni?");
        panel.add(feliratLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panel.add(comboBox1, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

}
