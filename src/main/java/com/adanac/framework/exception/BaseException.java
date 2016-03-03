package com.adanac.framework.exception;

/**
 * 
 * BaseException 框架定义的异常类,也是引入本框架的应用唯一可以创建和使用的异常类：
 * 	<ul> 1.应用捕获到系统异常后如要再重新抛出，一定要封装为BaseException再重新抛出<br>
 * 	 2.应用要抛出业务异常，只能抛出BaseException类型的异常 </ul>
 * <p>
 * BaseException根据不同的需求和使用场景提供了灵活的创建构造函数，涉及到以下几个参数：
 * cause,logMsg ,code ,messageArgs,defaultFriendlyMessage。
 * <p>
 * 
 * <b>logMsg:</b> 异常日志message,用于记录到日志系统的message
 * <p>
 * <b>code：</b>  异常code，指定一个code编码来标识不同的异常。 
 * 
 * 	 <ul> code除了用于标识异常外，还具有另外一个隐含的功能：一个code可以关联到对应的一个friendlyMessage，这个friendlyMessage是当发生异常时，
 * 		     在页面上显示给用户看的友好异常message，它区别于logMsg，logMsg是用于在封装或创建BaseException异常时附加的用于记日志和调试的日志message。 
 * 		  code和 friendlyMessage之间映射关系在系统消息资源属性文件中配置.</ul>
 * 
 * <p>
 * <b>messageArgs：</b>  code 对应消息的参数数组。当code对应的message包含参数，通过这个参数数组来传递实际参数值
 *<p>
 * <b>defaultFriendlyMessage:</b> 缺省的提供页面调用显示的友好异常message。
 * <ul>   框架在确定页面显示的友好异常message的时候，按照下面的逻辑顺序来选择,一旦找到就不再往下找：<br/>
 *    	      异常有code，根据code查找<br/>
 *    <ul> 	1.在系统message资源文件(messages.properties)中查找code对应的message<br/>	  
 *        	2.使用defaultFriendlyMessage<br/>
 *          3.查找系统message资源文件(messages.properties)中key为"exception.defaultMessage"的message<br/> 
 *    </ul>       
 *        异常没有code，根据根异常类全名查找 <br/>        
 *     <ul> 1.在系统message资源文件(messages.properties)查找根异常(使用类的全名,如java.io.IOException)对应的message<br/>
 *          2.查找系统message资源文件(messages.properties)中key为"exception.defaultMessage"的message<br/>  
 *     </ul>        
 * </ul>  
 * 
 * BaseException-->AbstractNestedRuntimeException-->RuntimeException-->Exception-->Throwable-->(implements)Serializable
 * 
 * @author adanac
 * @version 1.0
 */
public class BaseException extends AbstractNestedRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8237921832769817314L;
	private String code; // 异常code
	private String friendlyMessage = ""; // 用于页面显示的友好异常提示信息

	private Object[] messageArgs; // code对应消息的参数对象数组
	private String defaultFriendlyMessage; // 缺省友好异常提示信息

	public BaseException() {

	}

	/**
	 * 用指定的cause异常构造一个新的BaseException
	 * @param cause the exception cause
	 */
	public BaseException(Throwable cause) {
		super(cause);
	}

	/**
	 * 用指定的异常日志 message构造一个BaseException
	 * @param logMsg the detail message
	 */
	public BaseException(String logMsg) {
		super(logMsg);
	}

	// /**
	// * 用指定的异常日志 message和cause异常构造一个BaseException
	// * @param logMsg the exception log message
	// * @param cause the exception cause
	// */
	// public BaseException(String logMsg,Throwable cause){
	// super(logMsg,cause);
	// }

	/**
	 * 用指定code和cause异常构造一个BaseException
	 * 
	 * @param code the exception code
	 * @param cause the exception cause 
	 */
	public BaseException(String code, Throwable cause) {
		super(cause);
		this.code = code;
	}

	/**
	 *  
	 * 用指定的code和cause构造一个BaseException,并指定code对应message的参数值 
	 * 
	 * @param code the exception code
	 * @param cause the root cause 
	 * @param messageArgs the argument array of the message corresponding to code	
	 */
	public BaseException(String code, Throwable cause, Object[] messageArgs) {
		super(cause);
		this.code = code;
		this.messageArgs = messageArgs;
	}

	/**
	 * 用指定code和异常日志 message构造一个BaseException 
	 * 
	 * @param code the exception code
	 * @param logMsg the log message 
	 *
	 */
	public BaseException(String code, String logMsg) {
		super(logMsg);
		this.code = code;
	}

	/**
	 * 用指定的code和异常日志 message构造一个BaseException,并指定code对应message的参数值
	 * 
	 * @param code the exception code
	 * @param logMsg the detail message
	 * @param messageArgs the argument array of the message corresponding to code	
	 * 
	 */
	public BaseException(String code, String logMsg, Object[] messageArgs) {
		super(logMsg);
		this.code = code;
		this.messageArgs = messageArgs;
	}

	/**
	 * 用指定code和异常日志 message以及异常cause构造一个BaseException,
	 * 
	 * @param code the exception code
	 * @param logMsg the detail message	 
	 * @param cause the root cause
	 */
	public BaseException(String code, String logMsg, Throwable cause) {
		super(logMsg, cause);
		this.code = code;
	}

	/**
	 * 用指定的异常code和异常日志 message以及异常cause构造一个BaseException,
	 * 并传递code对应message的参数值
	 * 
	 * @param code the exception code
	 * @param logMsg the detail message	
	 * @param cause the root cause 
	 * @param messageArgs the argument array of the message corresponding to code
	 */
	public BaseException(String code, String logMsg, Throwable cause, Object[] messageArgs) {
		super(logMsg, cause);
		this.code = code;
		this.messageArgs = messageArgs;
	}

	/**
	 * 用指定的code和异常cause以及缺省的页面友好message构造一个BaseException
	 * 
	 * @param code the exception code
	 * @param cause the root cause
	 * @param defaultFriendlyMessage the default friendly message if the friendly message 
	 * corresponding to code is not exist.
	 */
	public BaseException(String code, Throwable cause, String defaultFriendlyMessage) {
		super(cause);
		this.code = code;
		this.defaultFriendlyMessage = defaultFriendlyMessage;
	}

	/**
	 * 用指定的code和异常cause构造一个BaseException,传递code对应message的参数值，
	 * 并指定缺省的页面友好message
	 * 
	 * @param code the exception code
	 * @param cause the root cause 
	 * @param messageArgs the argument array of the message corresponding to code
	 * @param defaultFriendlyMessage the default friendly message if the friendly message 
	 * corresponding to code is not exist.
	 * 
	 */
	public BaseException(String code, Throwable cause, Object[] messageArgs, String defaultFriendlyMessage) {
		super(cause);
		this.code = code;
		this.messageArgs = messageArgs;
		this.defaultFriendlyMessage = defaultFriendlyMessage;
	}

	/**
	 * 用指定的code和异常日志message构造一个BaseException,并指定缺省的页面友好message
	 * 
	 * @param code the exception code	
	 * @param logMsg the detail message 
	 * @param defaultFriendlyMessage the default friendly message if the friendly message 
	 * corresponding to code is not exist.
	 *  
	 */
	public BaseException(String code, String logMsg, String defaultFriendlyMessage) {
		super(logMsg);
		this.code = code;
		this.defaultFriendlyMessage = defaultFriendlyMessage;
	}

	/**
	 * 用指定异常code和异常日志message构造一个BaseException,传递code对应message的参数值,
	 * 并指定缺省的页面友好message
	 * 
	 * @param code the exception code
	 * @param logMsg the detail message	 
	 * @param messageArgs the argument array of the message corresponding to code	 
	 * @param defaultFriendlyMessage the default friendly message if the friendly message 
	 * corresponding to code is not exist.
	 *  
	 */
	public BaseException(String code, String logMsg, Object[] messageArgs, String defaultFriendlyMessage) {
		super(logMsg);
		this.code = code;
		this.messageArgs = messageArgs;
		this.defaultFriendlyMessage = defaultFriendlyMessage;
	}

	/**
	 * 用指定的code和异常日志message以及异常cause构造一个BaseException,
	 * 并指定缺省的页面友好message
	 * 
	 * @param code the exception code
	 * @param logMsg the detail message
	 * @param cause the root cause 	 
	 * @param defaultFriendlyMessage the default friendly message if the friendly message 
	 * corresponding to code is not exist.
	 *  
	 */
	public BaseException(String code, String logMsg, Throwable cause, String defaultFriendlyMessage) {
		super(logMsg, cause);
		this.code = code;
		this.defaultFriendlyMessage = defaultFriendlyMessage;
	}

	/**
	 * 用指定异常code和异常日志message以及异常cause构造一个BaseException,传递code对应message的参数值,
	 * 并指定缺省的页面友好message
	 * 
	 * @param code the exception code
	 * @param logMsg the detail message
	 * @param cause the root cause 
	 * @param messageArgs the argument array of the message corresponding to code
	 * @param defaultFriendlyMessage the default friendly message if the friendly message 
	 * corresponding to code is not exist.
	 *  
	 */
	public BaseException(String code, String logMsg, Throwable cause, Object[] messageArgs,
			String defaultFriendlyMessage) {
		super(logMsg, cause);
		this.code = code;
		this.messageArgs = messageArgs;
		this.defaultFriendlyMessage = defaultFriendlyMessage;
	}

	/**
	 * Return the detail message, including the message from the nested exception
	 * if there is one.
	 *//*
		 * @Override public String getMessage() { if(code!=null &&
		 * code.trim().length()>0){ StringBuilder sb = new StringBuilder();
		 * sb.append("Code: ").append(code) .append("\rMessage: "
		 * ).append(super.getMessage()); return sb.toString(); } return
		 * super.getMessage(); }
		 */

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the friendlyMessage
	 */
	public String getFriendlyMessage() {
		return friendlyMessage;
	}

	/**
	 * @param friendlyMessage the friendlyMessage to set
	 */
	public void setFriendlyMessage(String friendlyMessage) {
		this.friendlyMessage = friendlyMessage;
	}

	/**
	 * @return the messageArgs
	 */
	public Object[] getMessageArgs() {
		return messageArgs;
	}

	/**
	 * @param messageArgs the messageArgs to set
	 */
	public void setMessageArgs(Object[] messageArgs) {
		this.messageArgs = messageArgs;
	}

	/**
	 * @return the formattedMessage
	 */
	public String getDefaultFriendlyMessage() {
		return defaultFriendlyMessage;
	}

	/**
	 * @param defaultFriendlyMessage the defaultFriendlyMessage to set
	 */
	public void setDefaultFriendlyMessage(String defaultFriendlyMessage) {
		this.defaultFriendlyMessage = defaultFriendlyMessage;
	}
}
