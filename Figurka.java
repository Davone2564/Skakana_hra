
/**
 * Trieda Figurka vytvorí figúrku na šachovnici
 * 
 * @author (Dávid Mičo) 
 * @version (08.01.2021)
 */
public class Figurka {
    // atribúty inštancie
    private Kruh kruh;
    private int rozmerFigurky;
    private int riadok;
    private int stlpec; // číslo riadku a stĺpca na šachovnici
    private String farba;
    
    /**
     * Konštruktor triedy Figurka s parametrami - vytvorí figúrku s pevne daným rozmerom, danou farbou a súradnicami <br>
     * @param surRiadku je dané číslo riadku políčka na šachovnici na ktorom sa nachádza figúrka
     * @param surStlpca je dané číslo stĺpca políčka na šachovnici na ktorom sa nachádza figúrka
     * @param paLavyHornyX je X-ová súradnica ľavého horného rohu figúrky na plátne
     * @param paLavyHornyY je Y-ová súradnica ľavého horného rohu figúrky na plátne
     * @param paFarba je farba figúrky
     */
    public Figurka(int surRiadku, int surStlpca, int paLavyHornyX, int paLavyHornyY, String paFarba) {
        // inicializácia atribútov inštancie
        this.riadok = surRiadku;
        this.stlpec = surStlpca;
        this.rozmerFigurky = (Sachovnica.getRozmerPolicka() / 5) * 3;
        this.farba = paFarba;
        this.kruh = new Kruh(paLavyHornyX, paLavyHornyY, this.rozmerFigurky, paFarba);
    }
    
    // metódy inštancie
    /**
     * Metóda zobrazí figúrku na plátne
     */
    public void zobraz() {
        this.kruh.zobraz();
    }    
    
    /**
     * Metóda skryje figúrku(ak je už zobrazená na plátne)
     */
    public void skry() {
        this.kruh.skry();
    }    
    
    /**
     * @return vráti číslo riadku políčka, na ktorom sa nachádza figúrka
     */
    public int getSurRiadku() {
        return this.riadok;
    }    
    
    /**
     * @return vráti číslo stĺpca políčka, na ktorom sa nachádza figúrka
     */
    public int getSurStlpca() {
        return this.stlpec;
    }    
    
    /**
     * @return vráti farbu figúrky
     */
    public String getFarba() {
        return this.farba;
    }    
    
    /**
     * Metóda nastaví figúrke číslo riadku políčka, na ktoré ju presúvame <br>
     * @param paSurRiadku číslo riadku políčka na šachovnici, kde presúvame figúrku
     */
    public void setSurRiadku(int paSurRiadku) {
        this.riadok = paSurRiadku;
    }    
    
    /**
     * Metóda nastaví figúrke číslo stĺpca políčka, na ktoré ju presúvame
     * @param paSurStlpca číslo riadku políčka na šachovnici, kde presúvame figúrku
     */
    public void setSurStlpca(int paSurStlpca) {
        this.stlpec = paSurStlpca;
    }    
    
    /**
     * Figúrka sa posunie o jedno políčok na vertikálnej, horizontálnej alebo diagonálnej osi <br>
     * @param paPosunX určuje o koľko políčok sa figúrka posunie horizontálne. Keď je záporný tak sa pohybuje po osi X v zápornom smere.
     * @param paPosunY určuje o koľko políčok sa figúrka posunie vertikálne. Keď je záporný tak sa pohybuje po osi Y v zápornom smere.
     */
    public void posunOPolicko(int paPosunX, int paPosunY) {
        this.kruh.posunVodorovne(paPosunX * Sachovnica.getRozmerPolicka());
        this.kruh.posunZvisle(paPosunY * Sachovnica.getRozmerPolicka());
    }    
}
