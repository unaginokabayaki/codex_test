package com.example;

import java.io.File;
import java.util.Scanner;

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
 * XMLドキュメントを組み立て、入力されたファイル名へ整形出力するサンプル。
 * Simple sample program that builds an XML document and writes it to a user-specified file.
 */
public class XmlGenerator {
    public static void main(String[] args) {
        try {
            // ユーザーに出力ファイル名の入力を促す
            System.out.print("出力ファイル名を入力してください: ");
            Scanner scanner = new Scanner(System.in);
            String outputPath = scanner.nextLine().trim();
            if (outputPath.isEmpty()) {
                System.err.println("ファイル名が空です。処理を終了します。");
                return;
            }

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
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            // DOMをソースに、ファイルへ出力するためのターゲットを準備
            DOMSource source = new DOMSource(document);
            File outFile = new File(outputPath);
            File parent = outFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            StreamResult result = new StreamResult(outFile);
            // 変換実行：DOM -> XMLファイル
            transformer.transform(source, result);

            throw Exception("Test Exception");

            // 出力完了メッセージ
            System.out.println("Wrote XML to: " + outFile.getAbsolutePath());
        } catch (Exception e) {
            // 例外発生時はエラーメッセージを標準エラーに出力し、スタックトレースも表示
            System.err.println("Failed to generate XML: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
