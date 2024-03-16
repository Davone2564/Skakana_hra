import java.util.ArrayList;

/**
 * Trieda Sachovnica vytvorí maximálne jednu šachovnicu, ktorá sa dá zobraziť na plátne
 * 
 * @author (Dávid Mičo) 
 * @version (08.01.2021)
 */
public class Sachovnica {
    //atribúty triedy
    private static Sachovnica instancia; // jediná inštancia šachovnice ktorá sa dá vytvoriť
    private static final int ROZMER_POLICKA = 75; // finálny atribút triedy ktorý určuje rozmer jedného políčka šachovnice
    //atribúty inštancie
    private Policko[][] policka;
    private Znak[] pismena; // pole súradníc stĺpcov pod šachovnicou
    private Znak[] cisla; // pole súradníc riadkov vľavo od šachovnice
    private final int pocetPolicok = 8; // počet políčok na jednom riadku šachovnice
    private String farba1 = "white";
    private String farba2 = "black"; // farby políčok na šachovnici
    
    /**
     * Konštruktor triedy Sachovnica bez parametrov - vytvorí šachovnicu s pevne danou veľkosťou a umiestnením na plátne
     */
    private Sachovnica() {
        this.policka = new Policko[this.pocetPolicok][this.pocetPolicok];
        int rozmerPolicka = Sachovnica.ROZMER_POLICKA;
        // vytvorenie políčok šachovnice
        for (int riadok = 0; riadok < this.policka.length; riadok++) {
            for (int stlpec = 0; stlpec < this.policka[0].length; stlpec++) {
                if (riadok % 2 == 0) {
                    if (stlpec % 2 == 0) {
                        this.policka[riadok][stlpec] = new Policko(riadok, stlpec, stlpec * rozmerPolicka + 6 * rozmerPolicka, riadok * rozmerPolicka, this.farba1);
                    } else {
                        this.policka[riadok][stlpec] = new Policko(riadok, stlpec, stlpec * rozmerPolicka + 6 * rozmerPolicka, riadok * rozmerPolicka, this.farba2);
                    }
                } else {
                    if (stlpec % 2 == 0) {
                        this.policka[riadok][stlpec] = new Policko(riadok, stlpec, stlpec * rozmerPolicka + 6 * rozmerPolicka, riadok * rozmerPolicka, this.farba2);
                    } else {
                        this.policka[riadok][stlpec] = new Policko(riadok, stlpec, stlpec * rozmerPolicka + 6 * rozmerPolicka, riadok * rozmerPolicka, this.farba1);
                    }
                }
            }
        }     
        
        this.pismena = new Znak[8];
        this.cisla = new Znak[8];
        // vytvorenie pomocného riadku pod políčkami šachovnice, určujúceho súradnice stĺpcov šachovnice 
        this.pismena[0] = new Znak("A", 0 * rozmerPolicka + (rozmerPolicka + 15) + 5 * rozmerPolicka, rozmerPolicka * 9 - 10);  
        this.pismena[1] = new Znak("B", 1 * rozmerPolicka + (rozmerPolicka + 15) + 5 * rozmerPolicka, rozmerPolicka * 9 - 10);
        this.pismena[2] = new Znak("C", 2 * rozmerPolicka + (rozmerPolicka + 15) + 5 * rozmerPolicka, rozmerPolicka * 9 - 10);
        this.pismena[3] = new Znak("D", 3 * rozmerPolicka + (rozmerPolicka + 15) + 5 * rozmerPolicka, rozmerPolicka * 9 - 10); 
        this.pismena[4] = new Znak("E", 4 * rozmerPolicka + (rozmerPolicka + 15) + 5 * rozmerPolicka, rozmerPolicka * 9 - 10);
        this.pismena[5] = new Znak("F", 5 * rozmerPolicka + (rozmerPolicka + 15) + 5 * rozmerPolicka, rozmerPolicka * 9 - 10);
        this.pismena[6] = new Znak("G", 6 * rozmerPolicka + (rozmerPolicka + 15) + 5 * rozmerPolicka, rozmerPolicka * 9 - 10);
        this.pismena[7] = new Znak("H", 7 * rozmerPolicka + (rozmerPolicka + 15) + 5 * rozmerPolicka, rozmerPolicka * 9 - 10); 
        // vytvorenie pomocného stĺpca vľavo od šachovnice, určujúceho súradnice riadkov šachovnice
        for (int i = 8; i > 0; i--) {
            this.cisla[i - 1] = new Znak(i + "", 20 + 5 * rozmerPolicka, (9 - i) * rozmerPolicka - 15);
        }    
    }
    
    // metódy triedy
    /**
     * Metóda vráti jedinú inštanciu triedy Sachovnica, ak nie je ešte vytvorená, tak ju vytvorí <br>
     * @return vráti inštanciu triedy Sachovnica
     */
    public static Sachovnica getSachovnicu() {
        if (Sachovnica.instancia == null) {
            Sachovnica.instancia = new Sachovnica();
        }
        
        return Sachovnica.instancia;
    }    
    
    /**
     * @return vráti rozmer políčka na šachovnici
     */
    public static int getRozmerPolicka() {
        return Sachovnica.ROZMER_POLICKA;
    }    
    
    // metódy inštancie
    /**
     * Metóda zistí či je označené dané políčko(môže na neho figúrka preskočiť)
     * @param paRiadok číslo riadku políčka na šachovnici
     * @param paStlpec číslo stĺpca políčka na šachovnici
     * @return vráti true ak je dané políčko označené
     */
    public boolean oznacenePolicko(int paRiadok, int paStlpec) {
        return this.policka[paRiadok][paStlpec].jeOznacene(); 
    }    
    
    /**
     * Metóda zobrazí celú šachovnicu na plátne
     */
    public void zobraz() {
        for (int i = 0; i < 8; i++) {
            this.pismena[i].zobraz();
        }    
        
        for (int j = 0; j < 8; j++) {
            this.cisla[j].zobraz();
        } 
        
        for (int riadok = 0; riadok < this.policka.length; riadok++) {
            for (int stlpec = 0; stlpec < this.policka[0].length; stlpec++) {
                this.policka[riadok][stlpec].zobraz();
            }
        }  
    }   
    
    /**
     * Metóda označí dané políčko (môže na neho figúrka preskočiť) na šachovnici
     * @param paRiadok číslo riadku políčka ktoré chceme označiť
     * @param paStlpec číslo stĺpca políčka ktoré chceme označiť
     */
    public void oznacPolicko(int paRiadok, int paStlpec) {
        this.policka[paRiadok][paStlpec].setFarba("green");
    }    
    
    /**
     * Metóda zvýrazní dané políčko (nachádza sa na ňom figúrka, ktorú sme si zvolili) na šachovnici
     * @param paRiadok číslo riadku políčka ktoré chceme zvýrazniť
     * @param paStlpec číslo stĺpca políčka ktoré chceme zvýrazniť
     */
    public void zvyrazniPolicko(int paRiadok, int paStlpec) {
        this.policka[paRiadok][paStlpec].setFarba("magenta");
    }    
    
    /**
     * Metóda odznačí všetky políčka na šachovnici
     */
    public void odznacPolicka() {
        for (int riadok = 0; riadok < this.policka.length; riadok++) {
            for (int stlpec = 0; stlpec < this.policka[riadok].length; stlpec++) { 
                if ((this.policka[riadok][stlpec].getFarba().equals("green")) || (this.policka[riadok][stlpec].getFarba().equals("magenta"))) {
                    this.policka[riadok][stlpec].setFarba("black");
                }
            }
        }
    }   
    
    /**
     * Metóda zistí či je na šachovnici políčko s danými súradnicami
     * @param paRiadok číslo riadku políčka
     * @param paStlpec číslo stĺpca políčka
     * @return vráti true ak existuje políčko s danými súradnicami
     */
    public boolean existujePolicko(int paRiadok, int paStlpec) {
        return (paRiadok < this.policka.length) && (paRiadok >= 0) && (paStlpec < this.policka[0].length) && (paStlpec >= 0);
    }     
    
    /**
     * Metóda nájde a vráti susedné políčka daného políčka
     * @param paRiadok číslo riadku daného políčka
     * @param paStlpec číslo stĺpca daného políčka
     * @return kontajner naplnený susednými políčkami daného políčka
     */
    public ArrayList<Policko> getSusedPolicka(int paRiadok, int paStlpec) {
        ArrayList<Policko> susPolicka = new ArrayList<Policko>();
        // metóda hľadá všetky susedné políčka(čiernej farby)
        if (this.existujePolicko(paRiadok + 1, paStlpec + 1)) {
            susPolicka.add(this.policka[paRiadok + 1][paStlpec + 1]);
        }
        
        if (this.existujePolicko(paRiadok + 1, paStlpec - 1)) {
            susPolicka.add(this.policka[paRiadok + 1][paStlpec - 1]);
        }
        
        if (this.existujePolicko(paRiadok - 1, paStlpec - 1)) {
            susPolicka.add(this.policka[paRiadok - 1][paStlpec - 1]);
        }
            
        if (this.existujePolicko(paRiadok - 1, paStlpec + 1)) {
            susPolicka.add(this.policka[paRiadok - 1][paStlpec + 1]);
        }
        
        return susPolicka;
    }    
}
