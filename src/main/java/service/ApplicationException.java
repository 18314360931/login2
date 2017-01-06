package service;

/**
 * 应用异常
 */
public class ApplicationException extends RuntimeException {

	public ApplicationException() {
	}

	public ApplicationException(String message) {
		super(message);
	}

}
