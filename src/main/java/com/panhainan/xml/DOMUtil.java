package com.panhainan.xml;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DOMUtil {
    DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

    public Document parse(String filePath) throws Exception {
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document document = builder.parse(new File(filePath));
        return document;
    }

    public void updateNodeAttr(String filePath, String nodeName, String attrName, String attrNewValue) throws Exception {
        DOMUtil parser = new DOMUtil();
        Document document = parser.parse(filePath);
        //get root element
        Element rootElement = document.getDocumentElement();
        NodeList nodes = rootElement.getElementsByTagName(nodeName);
        if (nodes != null) {
            for (int i = 0, len = nodes.getLength(); i < len; i++) {
                Node node = nodes.item(i);
                if (node != null && node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    if (nodeName.equals(element.getNodeName())) {
                        Attr attr = element.getAttributeNode(attrName);
                        if (attr != null) {
                            if (attrName.equals(attr.getName())) {
                                if (!attrNewValue.equals(attr.getValue())) {
                                    element.removeAttributeNode(attr);
                                    attr.setValue(attrNewValue);
                                    element.setAttributeNode(attr);
                                    updateXMLFile(document, filePath);
                                }
                            }
                        } else {
                            NamedNodeMap map = element.getAttributes();
                            for (int k = 0, lenk = map.getLength(); k < lenk; k++) {
                                Node nn = map.item(k);
                                if ("name".equals(nn.getNodeName()) && attrName.equals(nn.getNodeValue())) {
                                    Attr a = element.getAttributeNode("value");
                                    if (a != null) {
                                        element.removeAttributeNode(a);
                                        a.setValue(attrNewValue);
                                        element.setAttributeNode(a);
                                        updateXMLFile(document, filePath);
                                    }
                                }
                            }
                        }

                    }
                }

            }
        }
    }

    public void updateXMLFile(Document document, String filePath) throws TransformerException {
        //-------------------------保存到文件中
        //创建一个用来转换DOM对象的工厂对象
        TransformerFactory factory = TransformerFactory.newInstance();
        //获得转换器对象
        Transformer t = factory.newTransformer();
        //定义要转换的源对象
        DOMSource xml = new DOMSource(document);
        //定义要转换到的目标文件
        StreamResult s = new StreamResult(new File(filePath));
        //开始转换
        t.transform(xml, s);
    }

    public static void main(String[] args) throws Exception {
        String filePath = "C:\\Users\\FirePan\\AppData\\Local\\Temp\\1483090907924\\mule-domain-config.xml";//"mule-domain-config.xml";
        String nodeName = "http:listener-config";//spring:property"http:listener-config";
        String attrName = "port";
        String attrNewValue = "6666";
        DOMUtil domUtil =new DOMUtil();
        domUtil.updateNodeAttr(filePath, nodeName, attrName, attrNewValue);
    }
}