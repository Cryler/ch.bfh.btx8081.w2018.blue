
/**
 *date: 21.12.2018   -  time: 13:13:14
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package exception;

public class InvalidEmailException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidEmailException(String msg) {
		super(msg);
	}
}
