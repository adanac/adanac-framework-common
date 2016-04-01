package com.adanac.framework.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * XML处理工具类
 * @author adanac
 * @version 1.0
 */
public class XmlUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(XmlUtils.class);

	/**
	 * 功能描述: <br>
	 * 按照xpath获取指定根节点下符合条件的子节点
	 * 
	 * @param doc
	 * @param xquery
	 * @param ns
	 * @return
	 */
	public static NodeList runXpath(Node doc, String xquery, NamespaceContext ns) {
		NodeList list = null;
		try {
			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();
			if (null != ns) {
				xpath.setNamespaceContext(ns);
			}
			XPathExpression expression = xpath.compile(xquery);

			Object result = expression.evaluate(doc, XPathConstants.NODESET);
			list = (NodeList) result;
		} catch (XPathExpressionException e) {
			LOGGER.warn("XMLUtils:  unable to evaluate xpath", e);
		}

		return list;
	}

	/**
	 * 
	 * 功能描述: <br>
	 * 在指定xml节点下，按照xquery取值
	 * 
	 * @param doc
	 * @param xquery
	 * @return
	 */
	public static String getValueByXpath(Node doc, String xquery) {
		try {
			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();
			XPathExpression expression = xpath.compile(xquery);
			String result = (String) expression.evaluate(doc, XPathConstants.STRING);
			return result;
		} catch (XPathExpressionException e) {
			LOGGER.warn("XMLUtils:  unable to evaluate xpath", e);
		}

		return null;
	}

	/**
	 * 
	 * 功能描述: <br>
	 * 将指定节点转换为String类型
	 * 
	 * @param node
	 * @return
	 */
	public static String nodeAsString(Node node) {
		String nodeStr = "";
		TransformerFactory tff = TransformerFactory.newInstance();

		Transformer tf;
		try {
			tf = tff.newTransformer();
			tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			tf.transform(new DOMSource(node), new StreamResult(bos));
			return bos.toString("UTF-8");

		} catch (TransformerConfigurationException e) {
			LOGGER.warn("XMLUtils#nodeAsString: ", e);
		} catch (TransformerException e) {
			LOGGER.warn("XMLUtils#nodeAsString: ", e);
		} catch (UnsupportedEncodingException e) {
			LOGGER.warn("XMLUtils#nodeAsString: ", e);
		}

		return nodeStr;
	}

	/**
	 * 
	 * 功能描述: <br>
	 * 将Map中的键值对转换成XML的形式
	 * 
	 * @param paramMap
	 * @return
	 */
	public static String mapToXml(Map<String, String> paramMap) {
		StringBuilder sb = new StringBuilder("");
		Set<Entry<String, String>> entrySet = paramMap.entrySet();
		for (Entry<String, String> entry : entrySet) {
			sb.append("<");
			sb.append(entry.getKey());
			sb.append(">");
			// value
			sb.append(entry.getValue());
			sb.append("</");
			sb.append(entry.getKey());
			sb.append(">");
		}
		return sb.toString();
	}

	/**
	 * 功能描述: <br>
	 * 〈功能详细描述〉
	 * 
	 * @param responseXml
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static Document createDocFromXml(String responseXml)
			throws ParserConfigurationException, SAXException, IOException {
		Document doc;
		InputSource is = new InputSource(new StringReader(responseXml));
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();
		doc = builder.parse(is);
		return doc;
	}

	/**
	 * yangyang
	 * 功能描述: <br> 替换XML限定的特殊字符
	 * 〈功能详细描述〉
	 *
	 * @param strData
	 * @param regex
	 * @param replacement
	 * @return
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public static String replaceString(String strData, String regex, String replacement) {
		if (strData == null) {
			return null;
		}
		int index;
		index = strData.indexOf(regex);
		String strNew = "";
		if (index >= 0) {
			while (index >= 0) {
				strNew += strData.substring(0, index) + replacement;
				strData = strData.substring(index + regex.length());
				index = strData.indexOf(regex);
			}
			strNew += strData;
			return strNew;
		}
		return strData;
	}

	/**
	 * yangyang
	 * 功能描述: <br>替换字符串中特殊字符 
	 * 〈功能详细描述〉
	 *
	 * @param strData
	 * @return
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public static String encodeString(String strData) {
		if (strData == null) {
			return "";
		}
		strData = replaceString(strData, "&", "&amp;");
		strData = replaceString(strData, "<", "&lt;");
		strData = replaceString(strData, ">", "&gt;");
		strData = replaceString(strData, "'", "&apos;");
		strData = replaceString(strData, "\"", "&quot;");
		return strData;
	}

	/**
	 * yangyang  
	 * 功能描述: <br>还原字符串中特殊字符 
	 * 〈功能详细描述〉
	 *
	 * @param strData
	 * @return
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public static String decodeString(String strData) {
		strData = replaceString(strData, "&lt;", "<");
		strData = replaceString(strData, "&gt;", ">");
		strData = replaceString(strData, "&apos;", "'");
		strData = replaceString(strData, "&quot;", "\"");
		strData = replaceString(strData, "&amp;", "&");
		return strData;
	}
}
