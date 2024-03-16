import java.awt.geom.Ellipse2D;

/**
 * Cudzia trieda.
 * 
 * Kruh, s ktorým možno pohybovať a nakreslí sa na plátno.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0  (15 July 2000)
 */

public class Kruh {
    private int priemer;
    private int lavyHornyX;
    private int lavyHornyY;
    private String farba;
    private boolean jeViditelny;
    
    /**
     *  
     */
    public Kruh(int paLavyHornyX, int paLavyHornyY, int paPriemer, String paFarba) {
        this.priemer = paPriemer;
        this.lavyHornyX = paLavyHornyX;
        this.lavyHornyY = paLavyHornyY;
        this.farba = paFarba;
        this.jeViditelny = false;
    }

    /**
     * (Kruh) Zobraz sa.
     */
    public void zobraz() {
        this.jeViditelny = true;
        this.nakresli();
    }
    
    /**
     * (Kruh) Skry sa.
     */
    public void skry() {
        this.zmaz();
        this.jeViditelny = false;
    }

    /**
     * (Kruh) Posuň sa vodorovne o dĺžku danú parametrom. 
     */
    public void posunVodorovne(int vzdialenost) {
        this.zmaz();
        this.lavyHornyX += vzdialenost;
        this.nakresli();
    }

    /**
     * (Kruh) Posuň sa zvisle o dĺžku danú parametrom. 
     */
    public void posunZvisle(int vzdialenost) {
        this.zmaz();
        this.lavyHornyY += vzdialenost;
        this.nakresli();
    }

    /**
     * (Kruh) Zmeň priemer na hodnotu danú parametrom.
     * Priemer musí byť nezáporné celé číslo. 
     */
    public void zmenPriemer(int priemer) {
        this.zmaz();
        this.priemer = priemer;
        this.nakresli();
    }

    /**
     * (Kruh) Zmeň farbu na hodnotu danú parametrom.
     * Nazov farby musí byť po anglicky.
     * Možné farby sú tieto:
     * červená - "red"
     * žltá    - "yellow"
     * modrá   - "blue"
     * zelená  - "green"
     * fialová - "magenta"
     * čierna  - "black"
     */
    public void zmenFarbu(String farba) {
        this.farba = farba;
        this.nakresli();
    }

    /*
     * Draw the circle with current specifications on screen.
     */
    private void nakresli() {
        if (this.jeViditelny) {
            Platno canvas = Platno.dajPlatno();
            canvas.draw(this, this.farba, new Ellipse2D.Double(this.lavyHornyX, this.lavyHornyY, 
                                                          this.priemer, this.priemer));
            canvas.wait(10);
        }
    }

    /*
     * Erase the circle on screen.
     */
    private void zmaz() {
        if (this.jeViditelny) {
            Platno canvas = Platno.dajPlatno();
            canvas.erase(this);
        }
    }
}
