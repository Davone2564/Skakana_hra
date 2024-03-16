
/**
 * Trieda Policko vytvorí políčko, ktoré má číslo svojho riadku a stĺpca na šachovnici.
 * 
 * @author (Dávid Mičo) 
 * @version (08.01.2021)
 */
public class Policko {
    // atribúty inštancie
    private Stvorec stvorec;
    private int riadok;
    private int stlpec; // číslo riadku a stĺpca na šachovnici
    
    /**
     * Konštruktor triedy Policko s parametrami - vytvorí políčko s daným číslom riadku a stĺpca <br>
     * @param surRiadku dané číslo riadku políčka na šachovnici
     * @param surStlpca dané číslo stĺpca políčka na šachovnici
     * @param paLavyHornyX X-ová súradnica ľavého horného rohu políčka na plátne
     * @param paLavyHornyY Y-ová súradnica ľavého horného rohu políčka na plátne
     * @param paFarba farba políčka
     */
    public Policko(int surRiadku, int surStlpca, int paLavyHornyX, int paLavyHornyY, String paFarba) {
        // inicializácia atribútov inštancie
        this.riadok = surRiadku;
        this.stlpec = surStlpca;
        this.stvorec = new Stvorec(paLavyHornyX, paLavyHornyY, Sachovnica.getRozmerPolicka(), paFarba);
    }
    
    //metódy inštancie
    /**
     * @return vráti true ak je políčko označené(môže na neho figúrka preskočiť)
     */
    public boolean jeOznacene() {
        return this.stvorec.getFarba().equals("green");
    }    
    
    /**
     * @return vráti farbu políčka
     */
    public String getFarba() {
        return this.stvorec.getFarba();
    }    
    
    /**
     * Metóda zobrazí políčko na plátne
     */
    public void zobraz() {
        this.stvorec.zobraz();
    }    
    
    /**
     * Metóda nastaví farbu políčka <br>
     * @param paFarba farba ktorú chceme políčku nastaviť
     */
    public void setFarba(String paFarba) {
        this.stvorec.setFarba(paFarba);
    }    
    
    /**
     * @return vráti číslo riadku na šachovnici, ktoré patrí políčku
     */
    public int getSurRiadku() {
        return this.riadok;
    }    
    
    /**
     * @return vráti číslo stĺpca na šachovnici, ktoré patrí políčku
     */
    public int getSurStlpca() {
        return this.stlpec;
    }
}
