import javax.swing.JOptionPane;

/**
 * Trieda Aplikacia, ktorá zabezpečuje chod hry a riadi chod celého programu
 * 
 * @author (Dávid Mičo) 
 * @version (08.01.2021)
 */
public class Aplikacia {
    /**
     * Súkromný konštruktor triedy Aplikacia bez parametrov
     */
    private Aplikacia() {
    }
    
    //metódy triedy
    /**
     * Metóda zabezpečuje chod hry. Po spustení tejto metódy sa spustí celá hra <br>
     * @param args prípadné parametre ktoré sme v tele metódy nijako nevyužili
     */
    public static void main(String[] args) {
        Hra hra = new Hra();
        hra.zobrazHraciePole();
        JOptionPane.showMessageDialog(null, "Vitaj pri hre Skákaná. Keď budeš zadávať súradnice políčka, tak napíš najprv veľké písmeno stĺpca a potom číslo riadku. ");
        while (hra.getStav() == StavHry.NEROZHODNUTA) {
            // dáva možnosť modrému tímu zasiahnuť do hry
            JOptionPane.showMessageDialog(null, "Na rade je modrý tím");
            hra.zvolFigurku();
            while ((!hra.getFarbaZvolFigurky().equals("blue")) || (!hra.jeOznacenePolicko()) || (!hra.jeZvolFigurka())) {
                String farba = hra.getFarbaZvolFigurky();
                hra.odznacFigurku();
                if ((farba.equals("red") || (!hra.jeOznacenePolicko())) && (!farba.equals(""))) {    
                    JOptionPane.showMessageDialog(null, "Túto figúrku nie je možné zvoliť");
                }    
                hra.zvolFigurku();
            }    
            hra.posunZvolenuFigurku();
            while (hra.jeZvolFigurka()) {
                hra.posunZvolenuFigurku();
            }    
            hra.skontrolujVyhru();
            if (hra.getStav() != StavHry.NEROZHODNUTA) {
                break;
            }    
            // dáva možnosť červenému tímu zasiahnuť do hry
            JOptionPane.showMessageDialog(null, "Na rade je červený tím");
            hra.zvolFigurku();
            while ((!hra.getFarbaZvolFigurky().equals("red")) || (!hra.jeOznacenePolicko()) || (!hra.jeZvolFigurka())) {
                String farba = hra.getFarbaZvolFigurky();
                hra.odznacFigurku();
                if ((farba.equals("blue")) || (!hra.jeOznacenePolicko())) {
                    JOptionPane.showMessageDialog(null, "Túto figúrku nie je možné zvoliť");
                }
                hra.zvolFigurku();
            }  
            hra.posunZvolenuFigurku();
            while (hra.jeZvolFigurka()) {
                hra.posunZvolenuFigurku();
            }    
            hra.skontrolujVyhru();
        }    
        // keď nájde víťaza, tak ponúkne hráčom ďalšiu hru
        int potvrdenie = JOptionPane.showConfirmDialog(null, "Prajete si hrať znova?", "Select an Option", 0);
        if (potvrdenie == 0) {
            Aplikacia.main(null);
        } else {
            System.exit(0);
        }    
    }
}
