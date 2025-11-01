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
 * XMLドキュメントを組み立てて標準出力へ整形出力するサンプル。
 * Simple sample program that builds an XML document and prints it to standard output.
 */
public class XmlGenerator {
    public static void main(String[] args) {
        try {
            // DOMのDocumentを生成するためのファクトリ/ビルダーを用意
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // 空のDOM Documentを作成
            Document document = builder.newDocument();

            // ルート要素 <library> を作成してDocumentに追加
            Element root = document.createElement("library");
            document.appendChild(root);

            // 子要素 <book id="1"> を作成してルートに追加
            Element book = document.createElement("book");
            book.setAttribute("id", "1");
            root.appendChild(book);

            // <title>要素を作成し、テキストノードを追加
            Element title = document.createElement("title");
            title.appendChild(document.createTextNode("Effective Java"));
            book.appendChild(title);

            // <author>要素を作成し、テキストノードを追加
            Element author = document.createElement("author");
            author.appendChild(document.createTextNode("Joshua Bloch"));
            book.appendChild(author);

            // <published>要素を作成し、テキストノードを追加
            Element year = document.createElement("published");
            year.appendChild(document.createTextNode("2018"));
            book.appendChild(year);

            // DOMを整形済みXMLへ変換するためのTransformerを用意
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // インデント設定（見やすい整形出力のため）
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            // DOMをソースに、文字列へ出力するためのターゲット（Writer）を準備
            DOMSource source = new DOMSource(document);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            // 変換実行：DOM -> XML文字列
            transformer.transform(source, result);

            // 生成したXML文字列を標準出力に出力
            System.out.println(writer.toString());
        } catch (Exception e) {
            // 例外発生時はエラーメッセージを標準エラーに出力し、スタックトレースも表示
            System.err.println("Failed to generate XML: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
