![image](https://github.com/Davone2564/Skakana_hra/assets/45235000/5a48b2b5-96dc-4f39-868d-276b4a933ccf)
**ŽILINSKÁ UNIVERZITA V ŽILINE**

**FAKULTA RIADENIA A INFORMATIKY**

**INFORMATIKA 1**

Semestrálna práca

Dávid Mičo 5ZYI18

**Školský rok 2020/2021**

**ZADANIE SEMESTRÁLNEJ PRÁCE**

Ako zadanie semestrálnej práce som si vybral stolovú hru **Skákaná**. Pri semestrálnej práci som riešil najmä problém ako vyriešiť dvojskok a trojskok jednej figúrky. Taktiež som si vyhľadal ako si môžem vytvoriť ľubovoľný Unicode znak ako objekt a nakresliť ho na plátno. Potreboval som totiž pomocné súradnice na jednoduchšie zadávanie súradníc používateľom do dialógového okna. V triede Aplikacia som riešil ako najefektívnejšie využiť už naprogramované triedy pri chode celého programu.

**UML DIAGRAM TRIED**

![image](https://github.com/Davone2564/Skakana_hra/assets/45235000/a21c599a-7e8a-4363-9ec7-5c12e22dccc2)


**TRIEDY**

Trieda **Policko** obsahuje:

-   3 atribúty: **stvorec** – grafické znázornenie políčka

    **riadok** – číslo riadku na ktorom sa dané políčko nachádza na šachovnici

    **stlpec -** číslo stĺpca na ktorom sa dané políčko nachádza na šachovnici

-   konštruktor **Policko** je parametrický, ako parametre sa zadávajú číslo riadku a stĺpca šachovnice, x – ové a y – ové súradnice daného políčka(štvorca) na šachovnici a nakoniec farba políčka
-   metódu **jeOznacene** s návratovou hodnotou typu boolean, ktorá vráti hodnotu true ak je políčko označené
-   metódu **getFarba** s návratovou hodnotou typu String, ktorá vráti momentálnu farbu políčka
-   metódu **zobraz** bez návratovej hodnoty, ktorá zobrazí políčko na plátne
-   metódu **setFarba** bez návratovej hodnoty, ktorá nastaví farbu políčku podľa zadaného parametra
-   metódu **getSurRiadku** s návratovou hodnotou typu int, ktorá vráti číslo riadku šachovnice, na ktorom sa políčko nachádza
-   metódu **getSurStlpca** s návratovou hodnotou typu int, ktorá vráti číslo stĺpca šachovnice, na ktorom sa políčko nachádza

Trieda **Figurka** obsahuje:

-   5 atribútov: **kruh –** grafické zobrazenie figúrky

    **rozmerFigurky –** rozmer figúrky

    **riadok -** číslo riadku na ktorom sa daná figúrka nachádza na šachovnici

    **stlpec -** číslo stĺpca na ktorom sa daná figúrka nachádza na šachovnici

    **farba –** farba figúrky

-   konštruktor **Figurka** je parametrický, ako parametre sa zadávajú číslo riadku a stĺpca šachovnice, x – ové a y – ové súradnice danej figúrky(kruhu) na šachovnici a nakoniec farba figúrky
-   metódu **zobraz** bez návratovej hodnoty, ktorá zobrazí figúrku na plátne
-   metódu **skry** bez návratovej hodnoty, ktorá skryje figúrku, ak je zobrazená na plátne
-   metódu **getSurRiadku** s návratovou hodnotou typu int, ktorá vráti číslo riadku šachovnice, na ktorom sa figúrka nachádza
-   metódu **getSurStlpca** s návratovou hodnotou typu int, ktorá vráti číslo stĺpca šachovnice, na ktorom sa figúrka nachádza
-   metódu **getFarba** s návratovou hodnotou typu String, ktorá vráti momentálnu farbu figúrky
-   metóda **setSurRiadku** bez návratovej hodnoty, ktorá nastaví figúrke súradnicu riadku na šachovnici podľa zadaného parametra
-   metóda **setSurStlpca** bez návratovej hodnoty, ktorá nastaví figúrke súradnicu stĺpca na šachovnici podľa zadaného parametra
-   metóda **posunOPolicko** bez návratovej hodnoty, ktorá posunie figúrku o počet políčok po x – ovej a y- ovej osi podľa zadaných parametrov

Trieda **Hra** obsahuje:

-   6 atribútov: **stav –** vyjadruje aký je momentálne stav hry

    **sachovnica** – šachovnica z ktorej sa skladá hracie pole

    **jeZvolFigurka** – vyjadruje skutočnosť či je zvolená figúrka

    **jeOznacenePolicko –** vyjadruje či je aspoň jedno políčko označené

    **zvolFigurka –** obsahuje zvolenú figúrku(ak nejaká taká existuje)

    **figurky –** dvojrozmerná matica ktorá obsahuje všetky figúrky na šachovnici

-   konštruktor **Hra** je bezparametrický, ktorý vytvorí celé hracie pole
-   metóda **getStav** s návratovou hodnotou typu StavHry, ktorá vráti aktuálny stav hry
-   metóda **getFarbaZvolFigurky** s návratovou hodnotou typu String, ktorá vráti farbu zvolenej figúrky
-   metóda **jeZvolFigurka** s návratovou hodnotou typu boolean, ktorá vráti true ak je zvolená figúrka
-   metóda **jeOznacenePolicko** s návratovou hodnotou typu boolean, ktorá vráti true ak je označené aspoň jedno políčko
-   metóda **zobrazHraciePole** bez návratovej hodnoty, ktorá zobrazí celé hracie pole
-   metóda **posunZvolenuFigurku** bez návratovej hodnoty, ktorá posunie figúrku na pole ktorého súradnice zadá používateľ do dialógového okna
-   metóda **zvolFigurku** bez návratovej hodnoty, ktorá zvolí figúrku ktorej súradnice používateľ zadal do dialógového okna

**Pravidlá Skákanej**

**Hra** **Skákaná** je hra podobná klasickej stolovej hre Dáma. Hrá sa tak isto ako dáma na klasickej čiernobielej **šachovnici** 8x8. Rozdiel je v tom, že sa figúrky nemôžu navzájom vyhadzovať a môžu sa pohybovať ľubovoľným smerom(aj dozadu). Hra má podporu pre dvoch hráčov. Stačí keď sa pred začiatkom hry dohodnú akej farby bude ich tím. Buď modrý alebo červený. **Figúrka** môže preskakovať aj figúrky z vlastného tímu ale treba si zapamätať, že figúrky sa navzájom nevyhadzujú, ich počet na šachovnici ostáva stále rovnaký. **Víťazstvo** sa dá dosiahnuť tak, že jeden z hráčov usporiada svoje figúrky úplne na opačnú stranu šachovnice, kde na začiatku stáli figúrky súperovho tímu. Hra sa riadi pomocou dialógových okien, kde najprv zadávame súradnice figúrky, ktorú chceme zvoliť. **Súradnice** sa zadávajú tak, že najprv zadáme veľké písmeno stĺpca a potom číslo riadku kde sa nachádza daná figúrka. Keď zvolíme figúrku, tak sa nám zelenou farbou označia políčka, na ktoré sa môžeme s figúrkou posunúť. Takým istým spôsobom ako pri volení figúrky zadáme súradnice poľa, na ktoré chceme posunúť figúrku(toto pole musí byť označené zelenou farbou).
