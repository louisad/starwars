/**
 * 
 */

/**
 * @author S�bastien
 *
 */
public abstract class Solver {

	protected Etoile[] data;
	
	public Solver(String src) {
		data = Filehandler.read(src);
	}
}
