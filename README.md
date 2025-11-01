# codex_test

## 実行方法

コンパイル後、プログラム実行時に出力ファイル名を入力します。

```
javac -d out src/main/java/com/example/XmlGenerator.java
java -cp out com.example.XmlGenerator
```

- 実行すると「出力ファイル名を入力してください: 」と表示されるので、
  例: `output.xml` のようにファイル名（パス可）を入力してください。
  ディレクトリが存在しない場合は自動作成します。
