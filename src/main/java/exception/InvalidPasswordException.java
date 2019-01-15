
/**
 *date: 21.12.2018   -  time: 13:12:12
 *user: yanng   -  yann.gund@gmx.ch
 *
 */
package exception;

public class InvalidPasswordException extends Exception{
	
	/**
	 * @author gundy1
	 */

	
	private static final long serialVersionUID = 1L;

	public InvalidPasswordException(String msg) {
		super(msg);
	}
}
