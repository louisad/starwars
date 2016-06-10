import java.util.Iterator;
import java.util.LinkedList;

/**
 * 
 * @author Sébastien
 * @author Hello
 *
 */
public class OptimizedNightPlanner {

	/**
	 * A method to compute the value of a night, planning the stars with the first finished
	 * @param donnees : tableau de tableaux representants les donnees d'une etoile pour une nuit
	 */
	public static int value_FF(LinkedList<Night> data, LinkedList<Integer> importance, LinkedList<Integer> starID) {
        
		Night[] donnees = new Night[data.size()];
		int[] imp = new int[data.size()];
		int[] id = new int[data.size()];
		for(int ind = 0;ind<data.size();ind++) {
			donnees[ind] = data.get(ind);
			imp[ind] = importance.get(ind);
			id[ind] = starID.get(ind);
		}
		
		/*Trier par ordre de fin croissante*/
		for(int i = 0; i<donnees.length-1;i++) {
			for(int j = i+1; j<donnees.length;j++) {
				if(donnees[i].getFin() > donnees[j].getFin()) {
					Night n1 = donnees[i];
					Night n2 = donnees[j];
					donnees[j] = n1;
					donnees[i] = n2;
					
					Integer i1 = imp[i];
					Integer i2 = imp[j];
					imp[j] = i1;
					imp[i] = i2;
					
					Integer i3 = id[i];
					Integer i4 = id[j];
					id[j] = i3;
					id[i] = i4;
				}
			}
		}
		
		/*Prendre ceux qui sont compatibles*/
		LinkedList<Integer> bons = new LinkedList<Integer>();
		bons.add(0);
		int last = 0;
		for(int i = 1;i<donnees.length;i++) {
			if(donnees[last].getFin()<donnees[i].getDebut()) {
				bons.add(i);
				last= i;
			}
		}
		
		/*Calcul de la duree totale de la nuit 'bons' */
		/* + Calcul du debut au plus tot du planning*/
		int dureeTotaleBons = 0;
		int debutAuPlusTotPlanning = 10^10;
		int finAuPlusTardPlanning = 0;
		Iterator<Integer> iter = bons.iterator();
		while(iter.hasNext()){
			int myInt = iter.next();
			dureeTotaleBons += donnees[myInt].getDuree();
			if (debutAuPlusTotPlanning > donnees[myInt].getDebut()){
				debutAuPlusTotPlanning = donnees[myInt].getDebut();
			}
			if (finAuPlusTardPlanning < donnees[myInt].getFin()){
				finAuPlusTardPlanning = donnees[myInt].getFin();
			}
		}
		
		
		/*Verifie si la duree totale de la nuit ne depasse pas la plage maximale d observation */
		if (dureeTotaleBons > finAuPlusTardPlanning - debutAuPlusTotPlanning){
			bons.remove(0);
		}
		
		/*Compter leur valeur*/
		int valeur = 0;
		Iterator<Integer> i = bons.iterator();
		while(i.hasNext()) {
			int index = i.next();
			valeur += imp[index];
			//System.out.println("Etoile "+id[index]+" Deb "+donnees[index].getDebut()+" Fin "+donnees[index].getFin());
		}
		return valeur;
	}
}
