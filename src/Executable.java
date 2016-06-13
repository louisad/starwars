public class Executable {

	public static void main(String[] args) {

		Genetic sol = new Genetic("ins_400_71_1", 500);
		
		/*Pilot the treads*/
		sol.live(1);
		
		Schedule alpha = sol.getAlpha();
		Recuit rec = new Recuit(alpha, 80, 0.1, 5);
		System.out.println(rec.activate());
		
		System.out.println("Valeur de la solution finale : " + rec.getValue(false));
		
		for(int n = 0; n < 71; n++){
			System.out.println("----------------------  Nuit : " + n + " -----------------------");
			LinkedList<Etoile> myList = new LinkedList<Etoile>();
			for(int j = 0; j < rec.getSchedule().getStarsNights().length; j++){
				if(rec.getSchedule().getStarNight(j) == n){
					myList.add(rec.getSchedule().getStars()[j]);
					//System.out.println("L etoile " + j + " est regardee nuit " + n);
					//interet += rec.getSchedule().get
					//System.out.println("Debut " + rec.getSchedule().getStars()[j].getNight(n).getDebut());
					//System.out.println("Duree " + rec.getSchedule().getStars()[j].getNight(n).getDuree());
					//System.out.println("Fin " + rec.getSchedule().getStars()[j].getNight(n).getFin());
					/*for (int indice = 0; indice < rec.getSchedule().getStarsNights().length; indice++){
					if(checkTab[indice] == j && indice != j){
					System.out.println("DOUBLON de " + j + " a l indice " + indice);
					}*/
					//checkTab[j] = j;
					}
			}
			Etoile[] myTab = new Etoile[myList.size()];
			Iterator<Etoile> iter = myList.iterator();
			for (int p = 0; p < myTab.length; p++){
				while (iter.hasNext()){
					Etoile currentEtoile = iter.next();
					myTab[p] = currentEtoile;
					p += 1;
					//System.out.println(myTab[p].getID());
				}
			}
			/*Tri du tableau par ordre croissant de debut*/
			for (int ind1 = 0 ; ind1 < myTab.length-1; ind1++){
				for (int ind2 = ind1+1 ; ind2 < myTab.length; ind2++){
					int deb1 = myTab[ind1].getNight(n).getDebut();
					//System.out.println(deb1);
					int deb2 = myTab[ind2].getNight(n).getDebut();
					//System.out.println(deb2);
					if(deb1 > deb2){
						Etoile e1 = myTab[ind1];
						Etoile e2 = myTab[ind2];
						myTab[ind1] = e2;
						myTab[ind2] = e1;
					}
				}
			}
			/*Check*/
			//System.out.println("Debut de la nuit " + n + " a " + myTab[0].getNight(n).getDebut());
			int sum = myTab[0].getNight(n).getDebut();
			for(int ind = 0; ind < myTab.length; ind++){
				if(sum >= myTab[ind].getNight(n).getDebut()){
					System.out.println("L observation de letoile " + myTab[ind].getID() + " commence a " + sum + ".");
					sum +=  myTab[ind].getNight(n).getDuree();
					System.out.println("L observation de letoile " + myTab[ind].getID() + " termine a " + sum +".");
				}
				else{
					System.out.println("L observation de letoile " + myTab[ind].getID() + " commence a " + myTab[ind].getNight(n).getDebut() + ".");
					sum = myTab[ind].getNight(n).getDebut() + myTab[ind].getNight(n).getDuree();
					if(sum > myTab[ind].getNight(n).getFin()){
						System.out.println(" ! ! ! ! ! ERROR ! ! ! ! ! ");
					}
					else{
					System.out.println("L observation de letoile " + myTab[ind].getID() + " termine a " + sum +".");
					}
				}
			}
				
		}
				
	}

}
