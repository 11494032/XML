package com.example.administrator.xml_gen_par;

import android.content.res.AssetManager;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;



public class MainActivity extends AppCompatActivity {

    private TextView ed_num;
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

  public void click3( View view)throws Exception {
      ArrayList<Sms> al = null;
      Sms sms = null;
      AssetManager assetManager = getAssets();

      InputStream fis = assetManager.open("info.xml");
      XmlPullParser xpp = Xml.newPullParser();

      xpp.setInput(fis, "utf-8");

      int eventType = xpp.next();
      while (eventType != XmlPullParser.END_DOCUMENT) {

          if (eventType == XmlPullParser.START_TAG) {
              String tagName = xpp.getName();
              if ("smses".equals(tagName)) {
                  al = new ArrayList<Sms>();
              } else if ("sms".equals(tagName)) {
                  sms = new Sms();
              } else if ("address".equals(tagName)) {
                  String name = xpp.nextText();
                  System.out.println(name);
                sms.setAddress(name);
              } else if ("body".equals(tagName)) {
                  String name = xpp.nextText();
                  System.out.println(name);
                 sms.setBody(name);
              } else if ("time".equals(tagName)) {
                  String name = xpp.nextText();
                  System.out.println(name);
                  sms.setTime(name);
              }

          } else if (eventType == XmlPullParser.END_TAG) {
              String tagName = xpp.getName();
              if("sms".equals(tagName) )
                     al.add(sms);
          }
          eventType = xpp.next();
      }
   //   System.out.println("End document");

      fis.close();
      showSms(al);
  }

    private void showSms( ArrayList<Sms> smses)
    {
        StringBuilder sb = new StringBuilder();
        for( Sms sms:smses)
        {
            sb.append( sms.toString()+"\n");
        }


        ed_num = (TextView) findViewById(R.id.tv_sms);

        ed_num.setText(sb.toString());

    }


}
