import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Trieda Hra vytvorí hru Skákaná, ktorá obsahuje šachovnicu a figúrky
 * 
 * @author (Dávid Mičo) 
 * @version (08.01.2021)
 */
public class Hra {
    //atribúty inštancie
    private StavHry stav;
    private Sachovnica sachovnica;
    private boolean jeZvolFigurka = false; // uchováva informáciu či je momentálne zvolená figúrka
    private boolean jeOznacenePolicko = false; // uchováva informáciu či je nejaké políčko označené
    private Figurka zvolFigurka;
    private Figurka[][] figurky;
    
    /**
     * Konštruktor triedy Hra bez parametrov - vypýta si šachovnicu od triedy Sachovnica a vytvorí na nej figúrky
     */
    public Hra() {
        // inicializácia atribútov inštancie
        this.sachovnica = Sachovnica.getSachovnicu();
        int rozmerPolicka = Sachovnica.getRozmerPolicka();
        int odsadenie = (rozmerPolicka / 5);
        this.figurky = new Figurka[8][8];
        this.stav = StavHry.NEROZHODNUTA;
        // vytváranie a umiestňovanie figúrok na šachovnicu
        for (int riadok = 0; riadok < this.figurky.length; riadok++) {
            for (int stlpec = 0; stlpec < this.figurky[riadok].length; stlpec++) {
                switch (riadok) {
                    case 0:
                        if (stlpec % 2 == 1) {
                            this.figurky[riadok][stlpec] = new Figurka(riadok, stlpec, stlpec * rozmerPolicka + odsadenie + 6 * rozmerPolicka, riadok * rozmerPolicka + odsadenie, "red");
                            break;
                        } else {
                            break;
                        }    
                    case 1:
                        if (stlpec % 2 == 0) {
                            this.figurky[riadok][stlpec] = new Figurka(riadok, stlpec, stlpec * rozmerPolicka + odsadenie + 6 * rozmerPolicka, riadok * rozmerPolicka + odsadenie, "red");
                            break;
                        } else {
                            break;
                        }    
                    case 6:
                        if (stlpec % 2 == 1) {
                            this.figurky[riadok][stlpec] = new Figurka(riadok, stlpec, stlpec * rozmerPolicka + odsadenie + 6 * rozmerPolicka, riadok * rozmerPolicka + odsadenie, "blue");
                            break;
                        } else {
                            break;
                        }    
                    case 7:
                        if (stlpec % 2 == 0) {
                            this.figurky[riadok][stlpec] = new Figurka(riadok, stlpec, stlpec * rozmerPolicka + odsadenie + 6 * rozmerPolicka, riadok * rozmerPolicka + odsadenie, "blue");
                            break;
                        } else {
                            break;
                        }    
                }       
            }   
        }
    }    
    
    //metódy inštancie
    /**
     * @return vracia stav hry
     */
    public StavHry getStav() {
        return this.stav;
    }    
    
    /**
     * @return vráti farbu zvolenej figúrky
     */
    public String getFarbaZvolFigurky() {
        if (this.zvolFigurka != null) {
            return this.zvolFigurka.getFarba();
        }    
        return "";
    }    
    
    /**
     * @return vráti true ak je momentálne zvolená figúrka
     */
    public boolean jeZvolFigurka() {
        return this.jeZvolFigurka;
    }    
    
    /**
     * @return vráti true ak je momentálne označené aspoň jedno políčko
     */
    public boolean jeOznacenePolicko() {
        return this.jeOznacenePolicko;
    }
    
    /**
     * Metóda zobrazí celé hracie pole(šachovnicu a figúrky)
     */
    public void zobrazHraciePole() {
        this.sachovnica.zobraz();
        for (int riadok = 0; riadok < this.figurky.length; riadok++) {
            for (int stlpec = 0; stlpec < this.figurky[riadok].length; stlpec++) {
                if (this.figurky[riadok][stlpec] != null) {
                    this.figurky[riadok][stlpec].zobraz();
                }    
            }
        }
    }   
    
    /**
     * Metóda posunie danú figúrku o daný počet políčok na osi X a osi Y <br>
     * @param paRiadok číslo riadku šachovnice na ktorom sa nachádza figúrka, ktorú chceme posunúť
     * @param paStlpec číslo stĺpca šachovnice na ktorom sa nachádza figúrka, ktorú chceme posunúť
     * @param paPosunX počet políčok o ktorý chceme posunúť figúrku na osi X (ak je počet záporný tak posúvame v zápornom smere)
     * @param paPosunY počet políčok o ktorý chceme posunúť figúrku na osi Y (ak je počet záporný tak posúvame v zápornom smere)
     */
    private void posunFigurku(int paRiadok, int paStlpec, int paPosunX, int paPosunY) {
        this.figurky[paRiadok][paStlpec].posunOPolicko(paPosunX, paPosunY);
        this.figurky[paRiadok][paStlpec].setSurRiadku(paRiadok + paPosunY);
        this.figurky[paRiadok][paStlpec].setSurStlpca(paStlpec + paPosunX);
        
        this.figurky[paRiadok + paPosunY][paStlpec + paPosunX] = this.figurky[paRiadok][paStlpec];
        this.figurky[paRiadok][paStlpec] = null;
    }
    
    /**
     * Metóda posunie zvolenú figúrku o daný počet políčok na osi X a osi Y <br>
     * @param paPosunX počet políčok o ktorý chceme posunúť figúrku na osi X (ak je počet záporný tak posúvame v zápornom smere)
     * @param paPosunY počet políčok o ktorý chceme posunúť figúrku na osi Y (ak je počet záporný tak posúvame v zápornom smere)
     */
    private void posunZvolenuFigurku(int paPosunX, int paPosunY) {
        this.sachovnica.odznacPolicka();
        this.jeOznacenePolicko = false;
        this.posunFigurku(this.zvolFigurka.getSurRiadku(), this.zvolFigurka.getSurStlpca(), paPosunX, paPosunY);
        this.zvolFigurka = null;
    }   
    
    /**
     * Metóda posunie figúrku na políčko, ktorého súradnice zadáme do dialógového okna
     */
    public void posunZvolenuFigurku() {
        String suradnice = JOptionPane.showInputDialog(null, "Zadaj súradnice políčka, na ktoré chceš presunúť zvolenú figúrku");
        if (suradnice != null) {
            if (suradnice.length() == 2) {
                String stlpec = suradnice.substring(0, 1);
                String riadok = suradnice.substring(1); 
                int[] surMatice = this.urcSuradniceMatice(stlpec, riadok);
                if (surMatice != null) {
                    // metóda zistí či je políčko na ktoré chceme presunúť figúrku označené a ak je tak ju tam presúva
                    if (this.sachovnica.oznacenePolicko(surMatice[0], surMatice[1])) {
                        int posunY = surMatice[0] - this.zvolFigurka.getSurRiadku();
                        int posunX = surMatice[1] - this.zvolFigurka.getSurStlpca();
                        this.posunZvolenuFigurku(posunX, posunY);
                        this.zvolFigurka = null;
                        this.jeZvolFigurka = false;
                    } else {
                        JOptionPane.showMessageDialog(null, "Nie je možné presunúť figúrku na dané políčko");
                    } 
                } else {
                    JOptionPane.showMessageDialog(null, "Bol zadaný chybný vstup");
                }    
            } else {
                JOptionPane.showMessageDialog(null, "Chybný vstup");
            }    
        } else {
            int potvrdenie = JOptionPane.showConfirmDialog(null, "Chcete ukončiť hru?", "Select an Option", 0);
            if (potvrdenie == 0) {
                System.exit(0);
            } 
        }    
    }    
    
    /**
     * Metóda zvolí figúrku, ktorá sa nachádza na danom riadku a stĺpci a potom označí všetky políčka na ktoré sa môže presúvať <br>
     * @param paRiadok číslo riadku na ktorom sa nachádza figúrka, ktorú chceme zvoliť
     * @param paStlpec číslo stĺpca na ktorom sa nachádza figúrka, ktorú chceme zvoliť
     */
    private void zvolFigurku(int paRiadok, int paStlpec) {
        if (this.figurky[paRiadok][paStlpec] != null) {
            this.zvolFigurka = this.figurky[paRiadok][paStlpec];
            this.sachovnica.zvyrazniPolicko(paRiadok, paStlpec);
            this.figurky[paRiadok][paStlpec].zobraz();
            ArrayList<Policko> vedlPolicka = this.sachovnica.getSusedPolicka(paRiadok, paStlpec);
            // metóda hľadá susedné políčka na ktoré by sa mohla figúrka neskôr posunúť a potom ich označuje zelenou farbou
            for (Policko policko: vedlPolicka) {
                if ((this.figurky[policko.getSurRiadku()][policko.getSurStlpca()] == null) && (!policko.jeOznacene())) {
                    this.sachovnica.oznacPolicko(policko.getSurRiadku(), policko.getSurStlpca());
                    this.jeOznacenePolicko = true;
                } else if (this.figurky[policko.getSurRiadku()][policko.getSurStlpca()] != null) {
                    int posunX = policko.getSurStlpca() - paStlpec;
                    int posunY = policko.getSurRiadku() - paRiadok;
                    ArrayList<Policko> vedlPolicka1 = this.sachovnica.getSusedPolicka(policko.getSurRiadku(), policko.getSurStlpca());
                    for (Policko policko1: vedlPolicka1) {
                        if (policko1.getSurRiadku() == (policko.getSurRiadku() + posunY) && (policko1.getSurStlpca() == (policko.getSurStlpca() + posunX))) {
                            if (this.figurky[policko1.getSurRiadku()][policko1.getSurStlpca()] == null) {
                                this.sachovnica.oznacPolicko(policko1.getSurRiadku(), policko1.getSurStlpca());
                                this.jeOznacenePolicko = true;
                                ArrayList<Policko> vedlPolicka2 = this.sachovnica.getSusedPolicka(policko1.getSurRiadku(), policko1.getSurStlpca());
                                for (Policko policko2: vedlPolicka2) {
                                    if (this.figurky[policko2.getSurRiadku()][policko2.getSurStlpca()] != null) {
                                        int posunX1 = policko2.getSurStlpca() - policko1.getSurStlpca();
                                        int posunY1 = policko2.getSurRiadku() - policko1.getSurRiadku();
                                        ArrayList<Policko> vedlPolicka3 = this.sachovnica.getSusedPolicka(policko2.getSurRiadku(), policko2.getSurStlpca());
                                        for (Policko policko3: vedlPolicka3) {
                                            if (policko3.getSurRiadku() == (policko2.getSurRiadku() + posunY1) && (policko3.getSurStlpca() == (policko2.getSurStlpca() + posunX1))) {
                                                if (this.figurky[policko3.getSurRiadku()][policko3.getSurStlpca()] == null) {
                                                    this.sachovnica.oznacPolicko(policko3.getSurRiadku(), policko3.getSurStlpca());
                                                    ArrayList<Policko> vedlPolicka4 = this.sachovnica.getSusedPolicka(policko3.getSurRiadku(), policko3.getSurStlpca());
                                                    for (Policko policko4: vedlPolicka4) {
                                                        if (this.figurky[policko4.getSurRiadku()][policko4.getSurStlpca()] != null) {
                                                            int posunX2 = policko4.getSurStlpca() - policko3.getSurStlpca();
                                                            int posunY2 = policko4.getSurRiadku() - policko3.getSurRiadku();
                                                            ArrayList<Policko> vedlPolicka5 = this.sachovnica.getSusedPolicka(policko4.getSurRiadku(), policko4.getSurStlpca());
                                                            for (Policko policko5: vedlPolicka5) {
                                                                if ((policko5.getSurRiadku() == policko4.getSurRiadku() + posunY2) && (policko5.getSurStlpca() == policko4.getSurStlpca() + posunX2)) {
                                                                    if (this.figurky[policko5.getSurRiadku()][policko5.getSurStlpca()] == null) {
                                                                        this.sachovnica.oznacPolicko(policko5.getSurRiadku(), policko5.getSurStlpca());
                                                                    }    
                                                                }    
                                                            }    
                                                        }    
                                                    }    
                                                }    
                                            }    
                                        }   
                                    }    
                                }    
                            } 
                        }   
                    }
                }    
            }
        } else {
            JOptionPane.showMessageDialog(null, "Na tomto políčku sa nenachádza žiadna figúrka");
        } 
    }    
    
    /**
     * Metóda zvolí figúrku na základe súradníc ktoré zadávame do dialógového okna a označí polia na ktoré sa môže figúrka neskôr posunúť
     */
    public void zvolFigurku() {
        String suradnice = JOptionPane.showInputDialog(null, "Zadaj súradnice políčka, na ktorom je figúrka, ktorú chceš zvoliť");
        if (suradnice != null) {
            if (suradnice.length() == 2) {
                String stlpec = suradnice.substring(0, 1);
                String riadok = suradnice.substring(1); 
                int[] surMatice = this.urcSuradniceMatice(stlpec, riadok);
                if (surMatice != null) {                
                    this.zvolFigurku(surMatice[0], surMatice[1]);
                    if (this.zvolFigurka != null) {
                        this.jeZvolFigurka = true;
                    }    
                } else {
                    JOptionPane.showMessageDialog(null, "Bol zadaný chybný vstup");
                }    
            } else {
                JOptionPane.showMessageDialog(null, "Zadali ste chybný vstup");    
            } 
        } else {
            int potvrdenie = JOptionPane.showConfirmDialog(null, "Chcete ukončiť hru?", "Select an Option", 0);
            if (potvrdenie == 0) {
                System.exit(0);
            } 
        }    
    }    
    
    /**
     * Metóda odznačí zvolenú figúrku a označené políčka
     */
    public void odznacFigurku() {
        this.sachovnica.odznacPolicka();
        if (this.jeZvolFigurka) {
            this.figurky[this.zvolFigurka.getSurRiadku()][this.zvolFigurka.getSurStlpca()].zobraz();
        }
        this.zvolFigurka = null;
        this.jeZvolFigurka = false;
    }    
    
    /**
     * Metóda pretransformuje súradnice zadané do dialógového okna na súradnice matice, ktorá predstavuje políčka na šachovnici <br>
     * @param surStlpca súradnica stĺpca šachovnice ktorú zadal užívateľ do dialógového okna
     * @param surRiadku súradnica riadku šachovnice ktorú zadal užívateľ do dialógového okna
     * @return vracia pole, v ktorom sa nachádzajú pretransformované súradnice 
     */
    private int[] urcSuradniceMatice(String surStlpca, String surRiadku) {
        int riadokMatice;
        int stlpecMatice;
        // premieňa súradnice riadku šachovnice
        if (Character.isDigit(surRiadku.charAt(0))) {
            if ((Integer.parseInt(surRiadku) > 0) && (Integer.parseInt(surRiadku) < 9)) {
                riadokMatice = 8 - Integer.parseInt(surRiadku);
            } else {
                return null;
            }
        } else {
            return null;
        }    
        // premieňa súradnice stĺpca šachovnice
        switch (surStlpca) {
            case "A":
                stlpecMatice = 0;
                break;                
            case "B":
                stlpecMatice = 1;
                break;
            case "C":
                stlpecMatice = 2;
                break;
            case "D":
                stlpecMatice = 3;
                break;
            case "E":
                stlpecMatice = 4;
                break;
            case "F":
                stlpecMatice = 5;
                break;
            case "G":
                stlpecMatice = 6;
                break;
            case "H":
                stlpecMatice = 7;
                break;
            default:
                return null;
        }    
        int[] suradnice = {riadokMatice, stlpecMatice};
        return suradnice;
    }    
    
    /**
     * Metóda kontroluje, či už nejaký tím nezvíťazil. Ak zvíťazil tak vyvolá dialógové okno s menom víťaza.
     */
    public void skontrolujVyhru() {
        int modryTim = 0;
        int cervenyTim = 0;
        // kontroluje či sú figúrky na opačnej strane matice ako začínali(za každú figúrku pripočíta jeden bod)
        for (int riadok = 0; riadok < this.figurky.length; riadok++) {
            for (int stlpec = 0; stlpec < this.figurky[riadok].length; stlpec++) {
                switch (riadok) {
                    case 0:
                        if (stlpec % 2 == 1) {
                            if (this.figurky[riadok][stlpec] != null) {                                
                                if (this.figurky[riadok][stlpec].getFarba().equals("blue")) {
                                    modryTim++;
                                }    
                            }    
                        }    
                        break;
                    case 1:
                        if (stlpec % 2 == 0) {
                            if (this.figurky[riadok][stlpec] != null) {
                                if (this.figurky[riadok][stlpec].getFarba().equals("blue")) {
                                    modryTim++;
                                }   
                            }    
                        }
                        break;
                    case 6:
                        if (stlpec % 2 == 1) {
                            if (this.figurky[riadok][stlpec] != null) {
                                if (this.figurky[riadok][stlpec].getFarba().equals("red")) {
                                    cervenyTim++;
                                } 
                            }     
                        }
                        break;
                    case 7:
                        if (stlpec % 2 == 0) {
                            if (this.figurky[riadok][stlpec] != null) {    
                                if (this.figurky[riadok][stlpec].getFarba().equals("red")) {
                                    cervenyTim++;
                                } 
                            }    
                        }   
                        break;
                }               
            }    
        }   
        // ak nejaký tím nazbieral dostatok bodov tak vyvolá dialógové okno s menom víťaza
        if (cervenyTim == 8) {
            this.stav = StavHry.VYHRA_CERVENY;
            JOptionPane.showMessageDialog(null, "Víťazom sa stáva červený tím.");
        } else if (modryTim == 8) {
            this.stav = StavHry.VYHRA_MODRY;
            JOptionPane.showMessageDialog(null, "Víťazom sa stáva modrý tím.");
        }    
    }      
}
