インストール方法を示します。

【適用方法】
1. C:\Program Files\Apache Software Foundation\Tomcat 8.0\webapps をバックアップします。
（バックアップ は C:\Program Files\Apache Software Foundation 以下に置かないで下さい）

2. PortableRIS\META-INF\context.xmlを設定します。
   ※rris,mwm,mppsの各DBの接続先を変更します。
     [Resource > name]から、対象のDBを確認して下さい。

3. PortableRIS\WEB-INF\PortableRIS.config.xmlを設定します。
   ※コメントを参考に設定して下さい。

4. PortableRIS\WEB-INF\classes\log4j.xmlを設定します。
   ※必要に応じて、ログファイルの出力先を変更します。

5. Tomcat サービス停止します。  ★この時点から Tomcat関連アプリケーション が停止します。

6. C:\Program Files\Apache Software Foundation\Tomcat 8.0\webapps配下に、
   PortableRIS（手順2,3,4で設定したもの）を配置します。

7. Tomcat サービス開始します。   ★この時点から Tomcat関連アプリケーション が開始します。

8. C:\Program Files\Apache Software Foundation\Tomcat 8.0\logs 以下に
   PortableRIS.log
   が作成されることを確認して下さい。

9. 動作確認して下さい。


以上
