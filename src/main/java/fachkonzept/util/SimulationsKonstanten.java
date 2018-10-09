package fachkonzept.util;

public class SimulationsKonstanten {
    public static double getProduktMarktpreis(ProduktArt produktArt) {
        // hier als vorläufiges beispiel mit dem string -> soll natürlich wenn dann als enum
        double preis = 0;
        switch(produktArt) {
        case Holzstuhl:
            preis = 30;
            break;
        case Stoffstuhl:
            preis = 40;
            break;
        case Lederstuhl:
            preis = 75;
            break;
        case Holztisch:
            preis = 40;
            break;
        case Glastisch:
            preis = 99;
            break;
        case Kunststofftisch:
            preis = 40;
            break;
        case Holzschrank:
            break;
        case Edelstahlschrank:
            preis = 220;
            break;
        case Glasschrank:
            preis = 40;
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
            volumen = 250;
            break;
        case Stoffstuhl:
            volumen = 8808;
            break;
        case Lederstuhl:
            volumen = 55;
            break;
        case Holztisch:
            volumen = 40;
            break;
        case Glastisch:
            volumen = 47;
            break;
        case Kunststofftisch:
            volumen = 40;
            break;
        case Holzschrank:
            break;
        case Edelstahlschrank:
            volumen = 40;
            break;
        case Glasschrank:
            volumen = 40;
            break;
        default:
            return -1;
        }
        return volumen;
    }
    
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
    
    public static final int MATERIAL_MARKT_MENGE = 1000000;

}
