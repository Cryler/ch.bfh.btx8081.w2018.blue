
/**
 *date: 21.12.2018   -  time: 13:08:11
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package exception;

public class InvalidUsernameException extends Exception {
	
	/**
	 * @author gundy1
	 */


	private static final long serialVersionUID = 1L;

	public InvalidUsernameException(String msg) {
		super(msg);
	}
}
