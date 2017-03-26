package com.example.android.a071;

import android.content.Context;
import android.content.DialogInterface;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//java不能多重繼承，只能用implement加東西
    Button b1,b2,b3,b32,b4,b5,b6,b7;
    int[] count2={0,0,0}; //統計 喜歡、不喜歡、沒意見 預設各0人 count2是指第二顆按鈕的統計

    Context context;//把第一個畫面存起來, this會隨著進行而改變
    StringBuilder sb=new StringBuilder(); //做袋子，大家共用，用append累加，每次要用要歸零

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;//this=MainActivity 整個主畫面，整個LayOut 在context放入主畫面
        //
        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
        b32=(Button)findViewById(R.id.button32);
        b4=(Button)findViewById(R.id.button4);
        b5=(Button)findViewById(R.id.button5);
        b6=(Button)findViewById(R.id.button6);
        b7=(Button)findViewById(R.id.button7);

        //button listener
        b1.setOnClickListener(this);//這個畫面(很多按鈕的)
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b32.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);


    }
    @Override  //在implement View的OnClickListener以後才generate得出來
        public void onClick(View v) {
        switch (v.getId()){
            case R.id.button: //用id判斷 呼叫show dialog
                showDialog();
                break;
            case R.id.button2:
                showDialog2();
                break;
            case R.id.button3:
                showDialog3();//統計、投票
                break;
            case R.id.button32:
                showDialog32();//多欄輸入
                break;
            case R.id.button4: //單選
                showDialog4();
                break;
            case R.id.button5:
                showDialog5();
                break;
            case R.id.button6:
                showDialog6();
                break;
            case R.id.button7:
                showDialog7();
                break;
        }
    }
void showDialog32(){
    //把Dialog吹出來
    LayoutInflater lay=getLayoutInflater(); //產生畫面
    View layoutView=lay.inflate(R.layout.dialog2,(ViewGroup)findViewById(R.id.root));

    final  EditText wt=(EditText) layoutView.findViewById(R.id.editText2);
    final  EditText ht=(EditText) layoutView.findViewById(R.id.editText3);

    wt.setText("50");
    ht.setText("1.65");

    //final 不被下一代繼承
    sb.setLength(0);//袋子每一次都要歸零


        //做對話框
    AlertDialog.Builder ad=new AlertDialog.Builder(context);
    ad.setTitle(R.string.dialog32);
    ad.setIcon(android.R.drawable.ic_dialog_info);
    ad.setView(layoutView);//畫面出現

    ad.setPositiveButton(R.string.ok,new DialogInterface.OnClickListener(){
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();//解散

            sb.append(wt.getText()+"\n"+ht.getText());//抓資料
            Toast.makeText(context,sb.toString(),Toast.LENGTH_LONG).show();
        }
    });


    ad.setNegativeButton(R.string.cancel,new DialogInterface.OnClickListener(){
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();//解散

        }
    });
    ad.create().show();
}
        void showDialog7()
        {
        //把Dialog吹出來
        LayoutInflater lay=getLayoutInflater(); //產生畫面
        View layoutView=lay.inflate(R.layout.dialog,(ViewGroup)findViewById(R.id.root));
        Button bb=(Button) layoutView.findViewById(R.id.button8);
        final  TextView tv=(TextView)layoutView.findViewById(R.id.textView);
        //final 不被下一代繼承
        sb.setLength(0);//袋子每一次都要歸零

        bb.setOnClickListener(new android.view.View.OnClickListener(){
            @Override
            public void onClick(View v) {//寫隨機數
                int min=1;
                int max=49;
                Random rr=new Random();//只要有人點就去找1-49隨機數
                int nn=rr.nextInt(max-min+1)+min;
                tv.setText(nn+",");

                sb.append(nn+",");//放入袋子，會留存按幾下就存幾個
            }
        });
            //做對話框
            AlertDialog.Builder ad=new AlertDialog.Builder(context);
            ad.setTitle(R.string.dialog7);
            ad.setIcon(android.R.drawable.ic_dialog_info);
            ad.setView(layoutView);//畫面出現

            ad.setPositiveButton(R.string.ok,new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();//解散
                    Toast.makeText(context,sb.toString(),Toast.LENGTH_LONG).show();
                }
            });


            ad.setNegativeButton(R.string.cancel,new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();//解散

            }
        });
            ad.create().show();
    }


    void showDialog6(){
        AlertDialog.Builder ad=new AlertDialog.Builder(context);
        ad.setTitle(R.string.dialog6);
        ad.setIcon(android.R.drawable.ic_dialog_info);
        final List<String> ll= Arrays.asList(getResources().getStringArray(R.array.fruit));

        sb.setLength(0);//因為append會累加袋子的東西StringBuilder 所以要歸零，在放新的東西之前

        ad.setItems(R.array.fruit, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        sb.append(ll.get(which)+",");
                    }

        }
        );
        ad.create().show();
    }
    void showDialog5(){
        AlertDialog.Builder ad=new AlertDialog.Builder(context);
        ad.setTitle(R.string.dialog5);
        ad.setIcon(android.R.drawable.ic_dialog_info);

        final String[] fruit=getResources().getStringArray(R.array.fruit); //陣列放多選資料
        final boolean[] fruitChecked=new boolean[fruit.length]; //有沒有選
        //Q:final是甚麼意思:不被下一代繼承
        sb.setLength(0);//因為append會累加袋子的東西StringBuilder 所以要歸零，在放新的東西之前
        ad.setMultiChoiceItems(fruit,fruitChecked,new DialogInterface.OnMultiChoiceClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
               fruitChecked[which]=isChecked;

            }
        });
        ad.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {

                for (int a=0; a<fruit.length;a++){ //多選項，要寫選擇與否用loop
                    if(fruitChecked[a]){//如果有選
                        sb.append(fruit[a]+","); //把資料放到sb, append是附加，上一項丟進StringBuilder的東西會看見，要拉回0
                    }
                }
                dialog.dismiss();//解散
                Toast.makeText(context,sb.toString(),Toast.LENGTH_LONG).show();

            }
        });
        ad.setNegativeButton(R.string.cancel,new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();//解散
            }
        });
        ad.create().show();
    }

    void showDialog4(){ //單選，按確定以後才出現Toast
        AlertDialog.Builder ad=new AlertDialog.Builder(context);
        ad.setTitle(R.string.dialog4);
        ad.setIcon(android.R.drawable.ic_dialog_info);
        final List<String> ll= Arrays.asList(getResources().getStringArray(R.array.meat));
        //把ll放出去
        ad.setSingleChoiceItems(R.array.meat,0,new DialogInterface.OnClickListener(){//單選
            @Override
            public void onClick(DialogInterface dialog, int which) {
               sb.append(ll.get(which));
                //把ll放進袋子
            }
        });
        ad.setPositiveButton(R.string.ok,new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context,sb.toString(),Toast.LENGTH_LONG).show();
                dialog.dismiss();//解散
            }
        });
        ad.setNegativeButton(R.string.cancel,new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();//解散
            }
            });
        ad.create().show();
    }

    void showDialog3(){
        AlertDialog.Builder ad=new AlertDialog.Builder(context);
        ad.setTitle(R.string.dialog3);
        ad.setIcon(android.R.drawable.ic_dialog_info);
        ad.setMessage(R.string.age);
        final EditText age=new EditText(context);
        ad.setView(age);
        ad.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){ //new Dialog...是Listener
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();//解散

                Toast.makeText(context,
                        getResources().getString(R.string.age2)+age.getText(),Toast.LENGTH_LONG).show();
            }
        });
        ad.setNegativeButton(R.string.cancel,new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();//解散

                Toast.makeText(context,
                        getResources().getString(R.string.age2) + age.getText(), Toast.LENGTH_LONG).show();
            }
            });
        ad.create().show();
    }


    void showDialog2(){//投票
        AlertDialog.Builder ad=new AlertDialog.Builder(this);
        ad.setTitle(R.string.dialog2);
        ad.setIcon(android.R.drawable.ic_dialog_info);
        ad.setMessage(R.string.vote);


        ad.setPositiveButton(R.string.yes,new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                count2[0]++; //統計 第0個位置 喜歡
                dialog.dismiss();//解散，不用了，不統計，關掉對話框
                Toast.makeText(context,
                        getResources().getString(R.string.yes)+"("+count2[0]+")"+//此時this已經到了跳出的對話框了
                        getResources().getString(R.string.no)+"("+count2[1]+")"+
                        getResources().getString(R.string.neutral)+"("+count2[2]+")",Toast.LENGTH_LONG).show();
            }
        }); //只要有按鍵都要有listener
        ad.setNegativeButton(R.string.no, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                count2[1]++;//第1個位置 不喜歡
                dialog.dismiss();//解散，不用了
                Toast.makeText(context,
                        getResources().getString(R.string.yes)+"("+count2[0]+")"+//此時this已經到了跳出的對話框了
                        getResources().getString(R.string.no)+"("+count2[1]+")"+
                        getResources().getString(R.string.neutral)+"("+count2[2]+")",Toast.LENGTH_LONG).show();

            }
        });
        ad.setNeutralButton(R.string.neutral,new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                count2[2]++;//第2個位置 沒意見
                dialog.dismiss();//解散，不用了
                Toast.makeText(context,
                        getResources().getString(R.string.yes)+"("+count2[0]+")"+//此時this已經到了跳出的對話框了
                        getResources().getString(R.string.no)+"("+count2[1]+")"+
                        getResources().getString(R.string.neutral)+"("+count2[2]+")",Toast.LENGTH_LONG).show();

            }
        });
        ad.create().show(); //顯示對話框

    }
    void showDialog(){ //確定或取消?
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle(R.string.dialog1);
        ad.setIcon(android.R.drawable.ic_dialog_info);
        ad.setMessage(R.string.exit);

        ad.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override //確定
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();//解散，不用了

            }
        });


        ad.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener(){
            @Override //取消
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();//解散，不用了

            }
        });
        ad.create().show();//顯示對話框

    }
}
