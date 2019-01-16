package mutant.exception;

/**
 * Exception created to handle errors when an invalid DNA is given to system.
 * 
 * @author Hélio De Rosa Junior
 */
public class InvalidDnaException extends Exception {

	private static final long serialVersionUID = 1L;
	private String message;
	
	public InvalidDnaException(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
