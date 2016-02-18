package com.adanac.framework.lang.exception;

/**
 * Handy class for wrapping runtime <code>Exceptions</code> with a root cause.
 * 
 * This class is <code>abstract</code> to force the programmer to extend the class. <code>getMessage</code> will include
 * nested exception information; <code>printStackTrace</code> and other like methods will delegate to the wrapped
 * exception, if any.
 * 
 * @author adanac
 * @version 1.0
 */
public class AbstractNestedRuntimeException extends RuntimeException {
	/**
	 * serial number
	 */
	private static final long serialVersionUID = 7534751658054481518L;

	/**
	 * 构造方法
	 */
	public AbstractNestedRuntimeException() {
		super();
	}

	/**
	 * Construct a <code>AbstractNestedRuntimeException</code> with the specified detail message.
	 * 
	 * @param cause the nested exception
	 */
	public AbstractNestedRuntimeException(Throwable cause) {
		super(cause);
	}

	/**
	 * Construct a <code>AbstractNestedRuntimeException</code> with the specified detail message.
	 * 
	 * @param msg the detail message
	 */
	public AbstractNestedRuntimeException(final String msg) {
		super(msg);
	}

	/**
	 * Construct a <code>AbstractNestedRuntimeException</code> with the specified detail message and nested exception.
	 * 
	 * @param msg the detail message
	 * @param cause the nested exception
	 */
	public AbstractNestedRuntimeException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public Throwable getRootCause() {
		Throwable rootCause = null;
		Throwable cause = getCause();
		while (cause != null && cause != rootCause) {
			rootCause = cause;
			cause = cause.getCause();
		}
		return rootCause;
	}

	/**
	 * Retrieve the most specific cause of this exception, that is, either the innermost cause (root cause) or this
	 * exception itself.
	 * <p>
	 * Differs from {@link #getRootCause()} in that it falls back to the present exception if there is no root cause.
	 * 
	 * @return the most specific cause (never <code>null</code>)
	 * 
	 * */
	public Throwable getMostSpecificCause() {
		Throwable rootCause = getRootCause();
		return (rootCause != null ? rootCause : this);
	}

	/**
	 * Check whether this exception contains an exception of the given type: either it is of the given class itself or
	 * it contains a nested cause of the given type.
	 * 
	 * @param exType the exception type to look for
	 * @return whether there is a nested exception of the specified type
	 */
	public boolean contains(Class<?> exType) {
		if (exType == null) {
			return false;
		}
		if (exType.isInstance(this)) {
			return true;
		}
		Throwable cause = getCause();
		if (cause == this) {
			return false;
		}
		if (cause instanceof AbstractNestedRuntimeException) {
			return ((AbstractNestedRuntimeException) cause).contains(exType);
		} else {
			while (cause != null) {
				if (exType.isInstance(cause)) {
					return true;
				}
				if (cause.getCause() == cause) {
					break;
				}
				cause = cause.getCause();
			}
			return false;
		}
	}
}
