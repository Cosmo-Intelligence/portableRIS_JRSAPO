インストール方法を示します。

【適用方法】
1. {TOMCAT_HOME}\webapps をバックアップします。
（バックアップ は C:\Program Files\Apache Software Foundation 以下に置かないで下さい）

2.テーブルをrris/rris1@rris環境へ追加します。
　Environment\CREATE_TABLE_PORTABLERIS_TERMINALINFO.sql

3. PortableRIS\META-INF\context.xmlを設定します。
   ※rris,mwm,mppsの各DBの接続先を変更します。
     [Resource > name]から、対象のDBを確認して下さい。

4. PortableRIS\WEB-INF\PortableRIS.config.xmlを設定します。
   ※コメントを参考に設定して下さい。

5. PortableRIS\WEB-INF\classes\log4j.xmlを設定します。
   ※必要に応じて、ログファイルの出力先を変更します。

6. Tomcat サービス停止します。  ★この時点から Tomcat関連アプリケーション が停止します。

7. {TOMCAT_HOME}\webapps配下に、
   PortableRIS（手順2,3,4で設定したもの）を配置します。
 
8. Tomcat サービス開始します。   ★この時点から Tomcat関連アプリケーション が開始します。

9. {TOMCAT_HOME}\logs 以下に
   PortableRIS.log
   が作成されることを確認して下さい。

9. 動作確認して下さい。


以上
