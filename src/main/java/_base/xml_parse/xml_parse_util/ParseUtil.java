package _base.xml_parse.xml_parse_util;

import org.xml.sax.Attributes;

public class ParseUtil {

    public static String AttributeToString(Attributes attributes){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < attributes.getLength(); i++) {
            sb.append(String.format("%s:%s  \t" , attributes.getLocalName(i),attributes.getValue(i),attributes.getType(i)));
        }
        return sb.toString();
    }
}
