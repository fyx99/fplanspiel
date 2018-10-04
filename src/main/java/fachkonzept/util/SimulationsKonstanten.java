package fachkonzept.util;

public class SimulationsKonstanten {
    public static double getProduktMarktpreis(ProduktArt produktArt) {
        // hier als vorläufiges beispiel mit dem string -> soll natürlich wenn dann als enum
        double preis = 0;
        switch(produktArt) {
        case Holzstuhl:
            preis = 40;
            break;
        case Stoffstuhl:
            preis = 40;
            break;
        case Lederstuhl:
            preis = 40;
            break;
        case Holztisch:
            preis = 40;
            break;
        case Glastisch:
            preis = 40;
            break;
        case Kunststofftisch:
            preis = 40;
            break;
        case Holzschrank:
            break;
        case Edelstahlschrank:
            preis = 40;
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
            volumen = 50;
            break;
        case Stoffstuhl:
            volumen = 8808;
            break;
        case Lederstuhl:
            volumen = 40;
            break;
        case Holztisch:
            volumen = 40;
            break;
        case Glastisch:
            volumen = 40;
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

}
