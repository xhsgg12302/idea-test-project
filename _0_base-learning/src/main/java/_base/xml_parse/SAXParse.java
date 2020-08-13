package _base.xml_parse;

import _base.xml_parse.xml_parse_util.ParseUtil;
import org.junit.Test;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileReader;

public class SAXParse {


    @Test
    public void SAX() throws Exception {
        SAXParser parser = SAXParserFactory.newInstance().newSAXParser();

        parser.parse(new File("src/main/resources/test-files/test.xml"), new MyDefaultHandler());
    }

    @Test
    public void SAX2() throws  Exception{
        XMLReader xmlReader = XMLReaderFactory.createXMLReader();
        xmlReader.setContentHandler(new MyDefaultHandler());

        xmlReader.parse(new InputSource(new FileReader("src/main/resources/test-files/test.xml")));
    }

}

class MyDefaultHandler extends DefaultHandler implements ContentHandler {

    /**
     * 开始文档时调用
     */
    @Override
    public void startDocument() {
        System.out.println("================start=======================");
    }


    /**
     * 开始标签时调用
     * @param uri   这个标签的命名空间
     * @param localName    标签的名字 例：<sina:wtfu sina:abc="wtfu.site.com"/> 中的标签名为wtfu
     * @param qName     限定名 例：<sina:wtfu sina:abc="wtfu.site.com"/> 中的标签名为sina:wtfu    qName = prefix + localName
     * @param attributes    以数组封装的属性结构
     */
    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) {

        System.out.println(String.format("uri:%s  localName:%s  qName:%s   attributes:(%s)", uri, localName, qName, ParseUtil.AttributeToString(attributes)));
        //System.out.println("MyDefaultHandler.startElement()-->" + qName);
    }

    /**
     * 结束标签时调用
     *
     * @param qName: 结束标签的标签名称
     */
    @Override
    public void endElement(String uri, String localName, String qName) {
        System.out.println(String.format("uri:%s  localName:%s  qName:%s ", uri, localName, qName));
    }

    /**
     * 读到文本内容的时调用
     *
     * @param ch:     表示当前读完的所有文本内容
     * @param start：  表示当前文本内容的开始位置
     * @param length: 表示当前文本内容的长度
     */
    @Override
    public void characters(char[] ch, int start, int length) {
        System.out.println(String.format("internal content:%s", new String(ch, start, length)));
    }

    /**
     * 结束文档时调用
     */
    @Override
    public void endDocument() {
        System.out.println("================end=======================");
    }

}
