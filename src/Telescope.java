import java.util.LinkedList;

/**
 * @author S�bastien
 *
 */
public class Telescope {

	private LinkedList<Observation> planning;
	
	public Telescope() {
		this.planning = new LinkedList<Observation>();
	}
	
	public boolean add(Observation o) {
		return false;
	}
}
