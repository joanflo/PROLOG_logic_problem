
package practicaprolog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * El motor d'inferència s'encarrega de trobar una solució emprant les altres classes
 * i els seus mètodes amb les regles per a la resolució.
 * @author joan i bernat
 */
public class MotorInferencia {
    
    //Objecte que inclou les llistes d'informació del problema:
    private LlistesInformacio dadesProblema;
    //Mapes on guardam totes les permutacions d'una llista deterinada:
    private Map llistesPermutadesDames;
    private Map llistesPermutadesVestits;
    private Map llistesPermutadesFlors;
    private Map llistesPermutadesMaterials;
    //Variables auxiliars per a les permutacions:
    private ArrayList<String> llistaModificada;
    private int clau;//del hashmap
    private int numLlista;//0=dames, 1=vestits, 2=flors, 3=materials
    
    
    /**
     * Instanciam les dades del problema amb la seva classe, així com les
     * hashtables que usam per a permutar, entre d'altres
     * @param dadesProblema 
     */
    public MotorInferencia(LlistesInformacio dadesProblema) {
        this.dadesProblema = dadesProblema;
        llistesPermutadesDames = new HashMap();
        llistesPermutadesVestits = new HashMap();
        llistesPermutadesFlors = new HashMap();
        llistesPermutadesMaterials = new HashMap();
        llistaModificada = new ArrayList<String>(3);
        numLlista = 0;
    }
    
    
    /**
     * Iniciam le motor d'inferència, és a dir, començam a processar la informació
     * del problema amb permutacions, regles i comparacions de manera que satisfeim
     * l'encunciat donat.
     */
    public void iniciaMotor(){
        //Permutam totes les llistes:
        permutar(dadesProblema.getDames());
        permutar(dadesProblema.getVestits());
        permutar(dadesProblema.getFlors());
        permutar(dadesProblema.getMaterials());
        
        //Variables auxiliars:
        int numLlistaDama;
        int numLlistaVestit;
        int numLlistaFlor;
        int numLlistaMaterial;
        ArrayList<String> auxDama = null;
        ArrayList<String> auxVestit = null;
        ArrayList<String> auxFlor = null;
        ArrayList<String> auxMaterial = null;
        boolean solucioTrobada = false;
        
        //Per cada llista de dames:
        for (numLlistaDama=0; numLlistaDama<llistesPermutadesDames.size(); numLlistaDama++) {
            auxDama = (ArrayList<String>) llistesPermutadesDames.get(numLlistaDama);
            
            //Per cada llista de vestits:
            for (numLlistaVestit=0; numLlistaVestit<llistesPermutadesVestits.size(); numLlistaVestit++) {
                auxVestit = (ArrayList<String>) llistesPermutadesVestits.get(numLlistaVestit);
                
                //Per cada llista de flors:
                for (numLlistaFlor=0; numLlistaFlor<llistesPermutadesFlors.size(); numLlistaFlor++) {
                    auxFlor = (ArrayList<String>) llistesPermutadesFlors.get(numLlistaFlor);
                    
                    //Per cada llista de materials:
                    for (numLlistaMaterial=0; numLlistaMaterial<llistesPermutadesMaterials.size(); numLlistaMaterial++) {
                        auxMaterial = (ArrayList<String>) llistesPermutadesMaterials.get(numLlistaMaterial);
                        
                        //Arribats aqui tenim al nostre abast una combinació de 4 llistes permutades:
                        //la llista de dames, vestits, flors i materials. Volem veure que es verifiquen
                        //TOTES les condicions del problema:
                        if (condicionsDelProblemaSatisfetes(auxDama, auxVestit, auxFlor, auxMaterial)) {
                            //Forçam la sortida dels FOR (així evitam la permutació de solucions i cerques innecessàries)
                            numLlistaDama = llistesPermutadesDames.size();
                            numLlistaVestit = llistesPermutadesVestits.size();
                            numLlistaFlor = llistesPermutadesFlors.size();
                            numLlistaMaterial = llistesPermutadesMaterials.size();
                            solucioTrobada = true;
                        }
                        
                    }
                }
            }
        }
        
        /* Tot i que sabem d'avantmà que l'enunciat té solució degut a que l'hem
         * resolt a mà, empleam el següent IF per a generalitzar l'algoritme (així
         * es podrà usar amb altres problemes, que potser no tinguin solució)
         */
        if (solucioTrobada) {
            //Arribats a aquest punt hem trobat una solució
            for (int x=0; x<auxDama.size(); x++) {
                mostraSolucio(auxDama.get(x),auxVestit.get(x),auxFlor.get(x),auxMaterial.get(x));
            }
        } else {
            System.out.println("No s'ha trobat cap solució al problema");
        }
        
    }
    
    
    /**
     * Mètode que s'encarrega d'avaluar quines llistes de totes les permutades
     * no compleixen les següents condicions:
     * -Lady S duia el vestit d'Abreu.
     * -Lady S no duia la rosa.
     * -La rosa estava al vestit de Bultó
     * -El vestit de Bultó és de seda.
     * -El vestit d'Abreu no era de franel·la.
     * -Lady R no duia la tulipa vermella.
     * -La núvia que duia el vestit d'organdí no duia la tulipa vermella.
     * Si no compleixen la condició les eliminam del hashmap
     */
    private boolean condicionsDelProblemaSatisfetes(ArrayList<String> auxDama, ArrayList<String> auxVestit, ArrayList<String> auxFlor, ArrayList<String> auxMaterial) {
        int posicioDamaLadyS;
        int posicioVestitAbreu;
        int posicioFlorRosa;
        int posicioMaterialSeda;
        int posicioVestitBulto;
        int posicioVestitFranella;
        int posicioDamaLadyR;
        int posicioFlorTulipa;
        int posicioMaterialOrgandi;
        
        //Lady S duia el vestit d'Abreu:
        posicioDamaLadyS = trobarElement("Lady S", auxDama);
        posicioVestitAbreu = trobarElement("Abreu", auxVestit);
        if (posicioDamaLadyS == posicioVestitAbreu) {
            
            //Lady S no duia la rosa
            //AND
            //La flor groga (rosa) estava al vestit de Bultó:
            posicioFlorRosa = trobarElement("rosa", auxFlor);
            posicioVestitBulto = trobarElement("Bulto", auxVestit);
            if ((posicioDamaLadyS != posicioFlorRosa) && (posicioFlorRosa == posicioVestitBulto)) {
                
                //El vestit de Bultó és de seda
                //AND
                //El vestit d'Abreu no era de franel·la
                //AND
                //Lady R no duia la tulipa vermella
                //AND
                //La núvia que duia el vestit d'organdí no duia la tulipa vermella:
                posicioMaterialSeda = trobarElement("seda", auxMaterial);
                posicioVestitFranella = trobarElement("franella", auxMaterial);
                posicioDamaLadyR = trobarElement("Lady R", auxDama);
                posicioFlorTulipa = trobarElement("tulipa", auxFlor);
                posicioMaterialOrgandi = trobarElement("organdi", auxMaterial);
                if ((posicioMaterialSeda == posicioVestitBulto)
                        && (posicioVestitAbreu != posicioVestitFranella)
                        && (posicioDamaLadyR != posicioFlorTulipa)
                        && (posicioMaterialOrgandi != posicioFlorTulipa)) {
                    
                    return true;
                    
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
        
    }
    
    
    /**
     * Torna la posició d'un element donat
     * @param element
     * @param llista
     * @return 
     */
    private int trobarElement(String element, ArrayList<String> llista) {
        boolean trobat = false;
        int i = 0;
        while ((i<llista.size()) && (!trobat)) {
            if (element.equalsIgnoreCase(llista.get(i))) {
                trobat = true;
            } else {
                i++;
            }
        }
        if (trobat) {
            return i;
        } else {
            return -1;
        }
    }

    
    /**
     * Permuta una llista donada per paràmetre
     * @param llistaOriginal
     * @param llistaPermutada
     */
    private void permutar(ArrayList<String> llistaOriginal) {
        llistaModificada.clear();
        clau = 0;
        permutarLlista(llistaModificada, llistaOriginal);
        numLlista++;
    }
    
    
    /**
     * Permuta una llista pasada per paràmetre mitjançant la recursivitat i es
     * serveix d'una altra llista auxiliar. Guarda les permutacions en un hashmap
     * @param llistaModificada
     * @param llistaOriginal 
     * @param llistaPermutada
     */
    private void permutarLlista(ArrayList<String> llistaModificada, ArrayList<String> llistaOriginal) {
        if (llistaOriginal.isEmpty()) {
            setLlistaPermutada(clau, llistaModificada);
            clau++;
        } else {
            for (int i=0; i<llistaOriginal.size(); i++) {
                String aux = llistaOriginal.remove(i);
                llistaModificada.add(aux);
                permutarLlista(llistaModificada, llistaOriginal);
                llistaModificada.remove(llistaModificada.size()-1);
                llistaOriginal.add(i, aux);
            }
        }
    }
    
    
    /**
     * Seteam les llistes que pertoqui
     * @param clau
     * @param llista 
     */
    private void setLlistaPermutada(int clau, ArrayList<String> llista) {
        ArrayList<String> llistaAux = new ArrayList<String>();
        llistaAux = (ArrayList<String>) llista.clone();
        switch (numLlista) {
            case 0:
                llistesPermutadesDames.put(clau, llistaAux);
                break;
            case 1:
                llistesPermutadesVestits.put(clau, llistaAux);
                break;
            case 2:
                llistesPermutadesFlors.put(clau, llistaAux);
                break;
            case 3:
                llistesPermutadesMaterials.put(clau, llistaAux);
                break;
        }
    }
    
    
    /**
     * Mostra els elements d'informació de la solució a l'usuari per pantalla.
     * @param dama
     * @param vestit
     * @param flor
     * @param material 
     */
    private void mostraSolucio(String dama, String vestit, String flor, String material) {
        System.out.println(dama+" porta el vestit de "+vestit+" amb la "+flor+" i el material era "+material);
    }
    
    
}