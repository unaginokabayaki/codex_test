package com.example;

import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Simple sample program that builds an XML document and prints it to standard output.
 */
public class XmlGenerator {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document = builder.newDocument();

            Element root = document.createElement("library");
            document.appendChild(root);

            Element book = document.createElement("book");
            book.setAttribute("id", "1");
            root.appendChild(book);

            Element title = document.createElement("title");
            title.appendChild(document.createTextNode("Effective Java"));
            book.appendChild(title);

            Element author = document.createElement("author");
            author.appendChild(document.createTextNode("Joshua Bloch"));
            book.appendChild(author);

            Element year = document.createElement("published");
            year.appendChild(document.createTextNode("2018"));
            book.appendChild(year);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            DOMSource source = new DOMSource(document);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            transformer.transform(source, result);

            System.out.println(writer.toString());
        } catch (Exception e) {
            System.err.println("Failed to generate XML: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
