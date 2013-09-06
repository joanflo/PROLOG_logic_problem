
package practicaprolog;

import java.util.ArrayList;

/**
 * Classe que inclou les llistes que s'usen al llarg del problema i que contenen
 * les dades d'aquest
 * @author joan i bernat
 */
public class LlistesInformacio {
    
    //Hi haurà 4 llistes, una per cada element d'informació del problema:
    private ArrayList<String> dames;
    private ArrayList<String> vestits;
    private ArrayList<String> flors;
    private ArrayList<String> materials;
    
    /**
     * Al constructor instanciam les llistes i cridam al mètode per inicialitzar-les
     */
    public LlistesInformacio(){
        dames = new ArrayList<String>(3);
        vestits = new ArrayList<String>(3);
        flors = new ArrayList<String>(3);
        materials = new ArrayList<String>(3);
        inicialitzaLlistes();
    }
    
    /**
     * Inicialitzam cada una de les llistes amb els seus corresponents valors
     * donats per l'enunciat del problema
     */
    private void inicialitzaLlistes(){
        dames.add("Lady Q");
        dames.add("Lady R");
        dames.add("Lady S");
        vestits.add("Abreu");
        vestits.add("Bulto");
        vestits.add("Coromines");
        flors.add("tulipa");
        flors.add("clavell");
        flors.add("rosa");
        materials.add("organdi");
        materials.add("seda");
        materials.add("franella");
    }

    /**
     * Torna la llista de dames
     */
    public ArrayList<String> getDames() {
        return dames;
    }
    
    /**
     * Torna la llista de vestits
     */
    public ArrayList<String> getVestits() {
        return vestits;
    }
    
    /**
     * Torna la llista de flors
     */
    public ArrayList<String> getFlors() {
        return flors;
    }
    
    /**
     * Torna la llista de materials
     */
    public ArrayList<String> getMaterials() {
        return materials;
    }
    
}