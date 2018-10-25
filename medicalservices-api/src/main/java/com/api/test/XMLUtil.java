package com.api.test;

import org.dom4j.*;

import java.util.*;
import java.util.Map.Entry;

public class XMLUtil {
	/**
	 * 根据Map组装xml消息体，值对象仅支持基本数据类型、String、BigInteger、BigDecimal，
	 * 以及包含元素为上述支持数据类型的Map
	 * 
	 * @param vo
	 * @param rootElement
	 * @return
	 */
	public static String map2xmlBody(Map<String, Object> vo,
			String rootElement) {
		Document doc = DocumentHelper.createDocument();
		Element body = DocumentHelper.createElement(rootElement);
		doc.add(body);
		__buildMap2xmlBody(body, vo);
		return doc.asXML();
	}

	public static String map2xmlBodyForGBK(Map<String, Object> vo,
			String rootElement) {
		Document doc = DocumentHelper.createDocument();
		Element body = DocumentHelper.createElement(rootElement);
		doc.add(body);
		__buildMap2xmlBody(body, vo);
		doc.setXMLEncoding("GBK");
		return doc.asXML();
	}

	@SuppressWarnings("unchecked")
	private static void __buildMap2xmlBody(Element body,
			Map<String, Object> vo) {
		if (vo != null) {
			Iterator<Entry<String, Object>> it = vo.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, Object> entry = (Entry<String, Object>) it
						.next();
				if (entry != null) {
					Object obj = entry.getValue();
					String key = entry.getKey();
					Element element = DocumentHelper.createElement(key);
					if (obj != null) {
						if (obj instanceof String) {
							element.setText((String) obj);
						} else {
							if (obj instanceof Character
									|| obj instanceof Boolean
									|| obj instanceof Number
									|| obj instanceof java.math.BigInteger
									|| obj instanceof java.math.BigDecimal) {
								// org.dom4j.Attribute attr =
								// DocumentHelper.createAttribute(element,
								// "type", obj.getClass().getCanonicalName());
								// element.add(attr);
								element.setText(String.valueOf(obj));
							} else if (obj instanceof Map) {
								// org.dom4j.Attribute attr =
								// DocumentHelper.createAttribute(element,
								// "type",
								// java.util.Map.class.getCanonicalName());
								// element.add(attr);
								__buildMap2xmlBody(element,
										(Map<String, Object>)obj);
							} else {
							}
						}
					}
					body.add(element);
				}
			}
		}
	}

	/**
	 * 根据xml消息体转化为Map
	 *
	 * @param xml
	 * @param rootElement
	 * @return
	 * @throws DocumentException
	 */
	@SuppressWarnings("rawtypes")
	public static Map xmlBody2map(String xml, String rootElement)
			throws DocumentException {
		if (xml != null && !"".equals(xml)) {
			int i = xml.indexOf("<" + rootElement + ">");
			if (i > -1) {
				xml = xml.substring(i, xml.length());
			}
		}
		Document doc = DocumentHelper.parseText(xml);
		Element body = (Element) (doc.selectSingleNode("/" + rootElement));
		Map vo = (Map) xml2map(body);
		return vo;
	}

	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	private static Map __buildXmlBody2map(Element body) {
		Map vo = new HashMap();
		if (body != null) {
			List<Element> elements = body.elements();
			for (Element element : elements) {
				String key = element.getName();
				if (key != null && !"".equals(key)) {
					String text = element.getText().trim();
					String value = text;
					vo.put(key, value);
				}
			}
		}
		return vo;
	}

	@SuppressWarnings("unchecked")
	private static Object xml2map(Element element) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Element> elements = element.elements();
		if (elements.size() == 0) {
			map.put(element.getName(), element.getText());
			if (!element.isRootElement()) {
				return element.getText();
			}
		} else if (elements.size() == 1) {
			map.put(elements.get(0).getName(), xml2map(elements.get(0)));
		} else if (elements.size() > 1) {
			// 多个子节点的话就得考虑list的情况了，比如多个子节点有节点名称相同的
			// 构造一个map用来去重
			Map<String, Element> tempMap = new HashMap<String, Element>();
			for (Element ele : elements) {
				tempMap.put(ele.getName(), ele);
			}

			Iterator<Entry<String, Element>> it = tempMap.entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, Element> entry = (Entry<String, Element>) it
						.next();
				if (entry != null) {
					Element obj = entry.getValue();
					String string = entry.getKey();
					Namespace namespace = obj.getNamespace();
					List<Element> elements2 = element.elements(new QName(
							string, namespace));
					// 如果同名的数目大于1则表示要构建list
					if (elements2.size() > 1) {
						List<Object> list = new ArrayList<Object>();
						for (Element ele : elements2) {
							list.add(xml2map(ele));
						}
						map.put(string, list);
					} else {
						// 同名的数量不大于1则直接递归去
						map.put(string, xml2map(elements2.get(0)));
					}
				}
			}
		}

		return map;
	}

}
