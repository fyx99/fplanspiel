package fachkonzept.util;

public class SimulationsKonstanten {
    public static double getProduktMarktpreis(ProduktArt produktArt) {
        // hier als vorläufiges beispiel mit dem string -> soll natürlich wenn dann als enum
        double preis = 0;
        switch(produktArt) {
        case Holzstuhl:
            preis = 60;
            break;
        case Stoffstuhl:
            preis = 90;
            break;
        case Lederstuhl:
            preis = 135;
            break;
        case Holztisch:
            preis = 250;
            break;
        case Glastisch:
            preis = 539;
            break;
        case Kunststofftisch:
            preis = 64;
            break;
        case Holzschrank:
            preis = 440;
            break;
        case Edelstahlschrank:
            preis = 560;
            break;
        case Glasschrank:
            preis = 780;
            break;
        default:
            return -1;
        }
        return preis;
    }
    public static int getProduktMarktvolumen(ProduktArt produktArt) {
        // hier als vorläufiges beispiel mit dem string -> soll natürlich wenn dann als enum
        int volumen = 0;
        switch(produktArt) {
        case Holzstuhl:
            volumen = 75;
            break;
        case Stoffstuhl:
            volumen = 42;
            break;
        case Lederstuhl:
            volumen = 37;
            break;
        case Holztisch:
            volumen = 37;
            break;
        case Glastisch:
            volumen = 36;
            break;
        case Kunststofftisch:
            volumen = 135;
            break;
        case Holzschrank:
            volumen = 48;
            break;
        case Edelstahlschrank:
            volumen = 50;
            break;
        case Glasschrank:
            volumen = 40;
            break;
        default:
            return -1;
        }
        return volumen;
    }

    public static final int MATERIAL_MARKT_MENGE = 100000;
    public static final int MASCHINEN_MARKT_MENGE = 100000;
    public static final int FINANZ_MARKT_MENGE = 100000;
    

    public static final double MASCHINEN_MARKT_PREISANPASSUNG = 0.1;	//prozentzahl pro dopplung
    public static final double MATERIAL_MARKT_PREISANPASSUNG = 0.1;	//prozentzahl pro dopplung
    
    public static final int VERTRIEBKOSTEN_PRO_EURO = 2;
    
    
    public static int getMaschinenPreise(MaschinenArt ma) {
        int a = 0;
        switch (ma) {
        case Holzstuhlmaschine:
            a = 7000;
            break;
        case Lederstuhlmaschine:
            a=8000;
            break;
        case Holzschrankmaschine:
            a=11000;
            break;
        case Glastischmaschine:
            a=11500;
            break;
        case Kunststofftischmaschine:
            a=8500;
            break;
        case Stoffstuhlmaschine:
            a=7500;
            break;
        case Holztischmaschine:
            a=9500;
            break;
        case Edelstahlschrankmaschine:
            a=12000;
            break;
        case Glasschrankmaschine:
            a=12500;
            break;
        default:
            break;
        }
        return a;
    }


}
