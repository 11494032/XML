package com.example.administrator.xml_gen_par;

import android.content.res.AssetManager;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click1( View view)throws Exception
    {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version='1.0' encoding='utf-8' standalone='yes' ?>");
        sb.append("<smses>");
        for( int i = 0; i < 50; i++ )
        {
            sb.append("<sms>");
            sb.append("<adress>");
            sb.append((5554+i)+"");
            sb.append("</adress>");
            sb.append("<boby>");
            sb.append("我是短信内容"+i);
            sb.append("</boby>");
            sb.append("<time>");
            sb.append((new Date().getTime())+"");
            sb.append("</time>");
            sb.append("</sms>");
        }
        sb.append("</smses>");
        FileOutputStream fos = null;

        fos = this.openFileOutput("info.xml",MODE_PRIVATE);
        fos.write(sb.toString().getBytes());
        fos.close();

    }
    public void click2( View view) throws Exception {
        FileOutputStream fos = openFileOutput("info.xml",MODE_PRIVATE);

        XmlSerializer serializer = Xml.newSerializer();

        serializer.setOutput(fos, "utf-8");

        serializer.startDocument("utf-8",true);

        serializer.startTag(null,"smses");

        for ( int i = 0; i<50; i++ )
        {
            serializer.startTag(null,"sms");
            serializer.startTag(null,"address");
            serializer.text(""+(5554+i));
            serializer.endTag(null,"address");
            serializer.startTag(null,"body");
            serializer.text("我的内容是"+i);
            serializer.endTag(null,"body");
            serializer.startTag(null,"time");
            serializer.text(new Date().getTime()+"");
            serializer.endTag(null,"time");
            serializer.endTag(null,"sms");

        }
        serializer.endTag(null,"smses");

        serializer.endDocument();

        fos.close();

    }

  public void click3( View view)throws Exception
  {

      AssetManager assetManager =  getAssets();

      InputStream fis = assetManager.open("info.xml");
   //   XmlPullParser xpp = Xml.newPullParser();

   //   xpp.setInput(fis,"utf-8");

//      int eventType = xpp.next();
//      while (eventType != XmlPullParser.END_DOCUMENT)
//      {
//          if(eventType == XmlPullParser.START_DOCUMENT) {
//              System.out.println("Start document");
//          } else if(eventType == XmlPullParser.START_TAG) {
//              System.out.println("Start tag "+xpp.getName());
//          } else if(eventType == XmlPullParser.END_TAG) {
//              System.out.println("End tag "+xpp.getName());
//          } else if(eventType == XmlPullParser.TEXT) {
//              System.out.println("Text "+xpp.getText());
//          }
//      eventType = xpp.next();
//     }
   // System.out.println("End document");





}

}
