package com.example.myapplication;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ListView mListView;
    private Button mButtonSend;
    private EditText mEditTextMessage;
    private ChatMessageAdapter mAdapter;
    private LinearLayout optionsbtns;
    private int level = 0;
    private LinearLayout search;
    private LinearLayout send_message_layout;
    private String language = "English";
    private String level_0[] = {"Do you want to give us feedback?", "Do you need help with something?"};
    private String level_0h[]={"क्या आप हमें सुझाव देना चाहते हैं?", "क्या आपको कुछ मदद चाहिए?"};
    private String to_be_sent;
    private int count;
    private String two_d[][];
    private String level_2_feedback[] = {"Tst"};
    Connection con = null;
    String level1send="";
    String subcat="";
    private String two_d_nav[][];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getJSON("http://192.168.43.29/Android/getdata.php");
        getJSONnav("http://192.168.43.29/Android/getdatanav.php");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.listView);
        mButtonSend = (Button) findViewById(R.id.btn_send);
        mEditTextMessage = (EditText) findViewById(R.id.et_message);
        mAdapter = new ChatMessageAdapter(this, new ArrayList<ChatMessage>());
        mListView.setAdapter(mAdapter);
        optionsbtns = (LinearLayout) findViewById(R.id.options_btns);
        optionsbtns.setVisibility(View.GONE);
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) optionsbtns.getLayoutParams();
        //lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
        search = findViewById(R.id.send_message_layout);
        send_message_layout = (LinearLayout) findViewById(R.id.send_message_layout);
        mimicOtherMessage("Hello! Which language do you prefer?" + "\n" + "नमस्कार! आप कौन सी भाषा पसंद करते हैं?");
        search.setVisibility(View.GONE);
        optionsbtns.setVisibility(View.VISIBLE);
        LayoutInflater inflater = getLayoutInflater();
        View english_btn = inflater.inflate(R.layout.options_mine_message, optionsbtns, false);
        TextView textView_english = (TextView) english_btn.findViewById(R.id.option_for_text);
        textView_english.setText("English");
        optionsbtns.addView(english_btn);
        View hindi_btn = inflater.inflate(R.layout.options_mine_message, optionsbtns, false);
        TextView textView_hindi = (TextView) hindi_btn.findViewById(R.id.option_for_text);
        textView_hindi.setText("हिंदी");
        optionsbtns.addView(hindi_btn);
        //English Language selected
        textView_english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getJSON("http://192.168.43.29/Android/getdata.php");
                getJSONnav("http://192.168.43.29/Android/getdatanav.php");
                optionsbtns.removeAllViews();
                for (int i = 0; i < level_0.length; i++)//Feedback or Help
                    {
                        LayoutInflater inflater = getLayoutInflater();
                        View myLayout = inflater.inflate(R.layout.options_mine_message, send_message_layout, false);
                        final Button textView = (Button) myLayout.findViewById(R.id.option_for_text);
                        textView.setText(level_0[i]);
                        textView.setOnClickListener(this);
                        textView.setTag(i);
                        optionsbtns.addView(myLayout);
                        textView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (v.getTag().toString().equals("0")) { //Feedback selected--base categories displayed
                                    mimicOtherMessage("Is it one of these categories?");
                                    optionsbtns.removeAllViews();
                                    for (int j = 0; j < two_d.length; j++) {
                                        LayoutInflater inflater = getLayoutInflater();
                                        View myLayout = inflater.inflate(R.layout.options_mine_message, optionsbtns, false);
                                        final Button textView = myLayout.findViewById(R.id.option_for_text);
                                        if (two_d[j][0].equals("")) continue;
                                        textView.setText(two_d[j][0]);
                                        textView.setOnClickListener(this);
                                        textView.setTag(j);
                                        optionsbtns.addView(myLayout);
                                        textView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {//Sub categories in feedback to be displayed
                                                mimicOtherMessage("Any particular topic?");
                                                final int subcat_no = Integer.parseInt(view.getTag().toString());
                                                level1send=two_d[Integer.parseInt(view.getTag().toString())][0];
                                                optionsbtns.removeAllViews();
                                                for (int k = 1; k < two_d[0].length; k++) {  //Change level_2_feedback based om the category selected, the tag
                                                    if (two_d[subcat_no][k].equals("")) continue;
                                                    LayoutInflater inflater = getLayoutInflater();
                                                    View myLayout = inflater.inflate(R.layout.options_mine_message, optionsbtns, false);
                                                    final Button textView = myLayout.findViewById(R.id.option_for_text);
                                                    textView.setText(two_d[subcat_no][k]);
                                                    textView.setOnClickListener(this);
                                                    textView.setTag(k);
                                                    optionsbtns.addView(myLayout);
                                                    textView.setOnClickListener(new View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View view) {
                                                            //Once the subcategory has been selected
                                                            //Send as json wipe the screen
                                                            if (Integer.parseInt(view.getTag().toString()) == two_d[0].length - 1) {
                                                                optionsbtns.removeAllViews();
                                                                mimicOtherMessage("Please type out your feedback");
                                                                search.setVisibility(View.VISIBLE);
                                                                mButtonSend.setOnClickListener(this);
                                                                mButtonSend.setOnClickListener(new View.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(View view) {
                                                                        search.setVisibility(View.INVISIBLE);
                                                                        sendMessage(mEditTextMessage.getText().toString());
                                                                        mimicOtherMessage("Thank you!");
                                                                        InputMethodManager imm = (InputMethodManager)
                                                                                getSystemService(Context.INPUT_METHOD_SERVICE);
                                                                        imm.hideSoftInputFromWindow(
                                                                                send_message_layout.getWindowToken(), 0);
                                                                    }
                                                                });

                                                            } else
                                                            {

                                                                subcat = view.getTag().toString();

                                                            try {
                                                                JSONObject toSend = new JSONObject();
                                                                toSend.put("level1", level1send);
                                                                toSend.put("subcat", subcat);
                                                                JSONTransmitter transmitter = new JSONTransmitter();
                                                                transmitter.execute(new JSONObject[]{toSend});

                                                            } catch (JSONException e) {
                                                                e.printStackTrace();
                                                            }
                                                            mimicOtherMessage("Thank You!");
                                                            optionsbtns.removeAllViews();
                                                        }
                                                            }
                                                            });
                                                    }
                                                    }
                                                    });
                                        }
                                        }
                          if(v.getTag().toString().equals("1"))//Navigation or help
                          {
                              //Navigation selected--base categories displayed
                              mimicOtherMessage("Is it one of these categories?");
                              optionsbtns.removeAllViews();
                              for(int j=0;j<two_d_nav.length;j++)
                              {   if(two_d_nav[j][0].equals("")||two_d_nav[j][0].equals(null)){continue;}
                                  LayoutInflater inflater = getLayoutInflater();
                                  View myLayout = inflater.inflate(R.layout.options_mine_message, optionsbtns, false);
                                  final Button textView =  myLayout.findViewById(R.id.option_for_text);
                                  textView.setText(two_d_nav[j][0]);
                                  textView.setOnClickListener(this);
                                  textView.setTag(j);
                                  optionsbtns.addView(myLayout);
                                  textView.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {//Sub categories in nav to be displayed
                                          mimicOtherMessage("Any particular topic?");
                                          optionsbtns.removeAllViews();
                                          final int maincatno =Integer.parseInt(view.getTag().toString());
                                          for(int k=1;k<7;k++)
                                          {  //Change nav based om the category selected, the tag
                                              if(two_d_nav[maincatno][k].equals("")||two_d_nav[maincatno][k].equals(null))continue;
                                              LayoutInflater inflater = getLayoutInflater();
                                              View myLayout = inflater.inflate(R.layout.options_mine_message, optionsbtns, false);
                                              final Button textView =  myLayout.findViewById(R.id.option_for_text);
                                              textView.setText(two_d_nav[maincatno][k]);
                                              textView.setOnClickListener(this);
                                              textView.setTag(k);
                                              optionsbtns.addView(myLayout);
                                              textView.setOnClickListener(new View.OnClickListener() {
                                                  @Override
                                                  public void onClick(View view) {
                                                      mimicOtherMessage(two_d_nav[maincatno][6+Integer.parseInt(view.getTag().toString())]);
                                                      optionsbtns.removeAllViews();
                                                      mimicOtherMessage("Thank You!");
                                                  }
                                              });
                                          }
                                      }
                                  });

                              }
                          }
                                                            }
                                                        });
                                                    }

                                                }
                                            }
        );
        //Hindi Language selected
        textView_hindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getJSON("http://192.168.43.29/Android/getdatahindi.php");
                getJSONnav("http://192.168.43.29/Android/getdatanavhindi.php");
                optionsbtns.removeAllViews();
                for (int i = 0; i < level_0h.length; i++)//Feedback or Help
                {
                    LayoutInflater inflater = getLayoutInflater();
                    View myLayout = inflater.inflate(R.layout.options_mine_message, send_message_layout, false);
                    final Button textView = (Button) myLayout.findViewById(R.id.option_for_text);
                    textView.setText(level_0h[i]);
                    textView.setOnClickListener(this);
                    textView.setTag(i);
                    optionsbtns.addView(myLayout);
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (v.getTag().toString().equals("0")) { //Feedback selected--base categories displayed
                                mimicOtherMessage("क्या यह इन श्रेणियों में से एक है?");
                                optionsbtns.removeAllViews();
                                for (int j = 0; j < two_d.length; j++) {
                                    LayoutInflater inflater = getLayoutInflater();
                                    View myLayout = inflater.inflate(R.layout.options_mine_message, optionsbtns, false);
                                    final Button textView = myLayout.findViewById(R.id.option_for_text);
                                    if (two_d[j][0].equals("")) continue;
                                    textView.setText(two_d[j][0]);
                                    textView.setOnClickListener(this);
                                    textView.setTag(j);
                                    optionsbtns.addView(myLayout);
                                    textView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {//Sub categories in feedback to be displayed
                                            mimicOtherMessage("कोई विशिष्ट विषय?");
                                            final int subcat_no = Integer.parseInt(view.getTag().toString());
                                            level1send=two_d[Integer.parseInt(view.getTag().toString())][0];
                                            optionsbtns.removeAllViews();
                                            for (int k = 1; k < two_d[0].length; k++) {  //Change level_2_feedback based om the category selected, the tag
                                                if (two_d[subcat_no][k].equals("")) continue;
                                                LayoutInflater inflater = getLayoutInflater();
                                                View myLayout = inflater.inflate(R.layout.options_mine_message, optionsbtns, false);
                                                final Button textView = myLayout.findViewById(R.id.option_for_text);
                                                textView.setText(two_d[subcat_no][k]);
                                                textView.setOnClickListener(this);
                                                textView.setTag(k);
                                                optionsbtns.addView(myLayout);
                                                textView.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        //Once the subcategory has been selected
                                                        //Send as json wipe the screen
                                                        if (Integer.parseInt(view.getTag().toString()) == two_d[0].length - 1) {
                                                            optionsbtns.removeAllViews();
                                                            mimicOtherMessage("कृपया अपना फीडबैक टाइप करें");
                                                            search.setVisibility(View.VISIBLE);
                                                            mButtonSend.setOnClickListener(this);
                                                            mButtonSend.setOnClickListener(new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View view) {
                                                                    search.setVisibility(View.INVISIBLE);
                                                                    sendMessage(mEditTextMessage.getText().toString());
                                                                    mimicOtherMessage("धन्यवाद!");
                                                                    InputMethodManager imm = (InputMethodManager)
                                                                            getSystemService(Context.INPUT_METHOD_SERVICE);
                                                                    imm.hideSoftInputFromWindow(
                                                                            send_message_layout.getWindowToken(), 0);
                                                                }
                                                            });

                                                        } else
                                                        {

                                                            subcat = view.getTag().toString();

                                                            try {
                                                                JSONObject toSend = new JSONObject();
                                                                toSend.put("level1", level1send);
                                                                toSend.put("subcat", subcat);
                                                                JSONTransmitter transmitter = new JSONTransmitter();
                                                                transmitter.execute(new JSONObject[]{toSend});

                                                            } catch (JSONException e) {
                                                                e.printStackTrace();
                                                            }
                                                            mimicOtherMessage("धन्यवाद!");
                                                            optionsbtns.removeAllViews();
                                                        }
                                                    }
                                                });
                                            }
                                        }
                                    });
                                }
                            }
                            if(v.getTag().toString().equals("1"))//Navigation or help
                            {
                                //Navigation selected--base categories displayed
                                mimicOtherMessage("कोई विशिष्ट विषय?");
                                optionsbtns.removeAllViews();
                                for(int j=0;j<two_d_nav.length;j++)
                                {   if(two_d_nav[j][0].equals("")||two_d_nav[j][0].equals(null)){continue;}
                                    LayoutInflater inflater = getLayoutInflater();
                                    View myLayout = inflater.inflate(R.layout.options_mine_message, optionsbtns, false);
                                    final Button textView =  myLayout.findViewById(R.id.option_for_text);
                                    textView.setText(two_d_nav[j][0]);
                                    textView.setOnClickListener(this);
                                    textView.setTag(j);
                                    optionsbtns.addView(myLayout);
                                    textView.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {//Sub categories in nav to be displayed
                                            mimicOtherMessage("क्या यह इन श्रेणियों में से एक है?");
                                            optionsbtns.removeAllViews();
                                            final int maincatno =Integer.parseInt(view.getTag().toString());
                                            for(int k=1;k<7;k++)
                                            {  //Change nav based om the category selected, the tag
                                                if(two_d_nav[maincatno][k].equals("")||two_d_nav[maincatno][k].equals(null))continue;
                                                LayoutInflater inflater = getLayoutInflater();
                                                View myLayout = inflater.inflate(R.layout.options_mine_message, optionsbtns, false);
                                                final Button textView =  myLayout.findViewById(R.id.option_for_text);
                                                textView.setText(two_d_nav[maincatno][k]);
                                                textView.setOnClickListener(this);
                                                textView.setTag(k);
                                                optionsbtns.addView(myLayout);
                                                textView.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View view) {
                                                        mimicOtherMessage(two_d_nav[maincatno][6+Integer.parseInt(view.getTag().toString())]);
                                                        optionsbtns.removeAllViews();
                                                        mimicOtherMessage("धन्यवाद!");
                                                    }
                                                });
                                            }
                                        }
                                    });

                                }
                            }
                        }
                    });
                }

            }
                                          }
        );
    }

    private void sendMessage(String message) {
        ChatMessage chatMessage = new ChatMessage(message, true, false);
        String temp = message;
        mAdapter.add(chatMessage);
    }

    private void mimicOtherMessage(String message) {
        ChatMessage chatMessage = new ChatMessage(message, false, false);
        mAdapter.add(chatMessage);
    }

    private void getJSON(final String urlWebService) {
        /*
         * As fetching the json string is a network operation
         * And we cannot perform a network operation in main thread
         * so we need an AsyncTask
         * The constrains defined here are
         * Void -> We are not passing anything
         * Void -> Nothing at progress update as well
         * String -> After completion it should return a string and it will be the json string
         * */
        class GetJSON extends AsyncTask<Void, Void, String> {

            //this method will be called before execution
            //you can display a progress bar or something
            //so that user can understand that he should wait
            //as network operation may take some time
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            //this method will be called after execution
            //so here we are displaying a toast with the json string
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            //in this method we are fetching the json string
            @Override
            protected String doInBackground(Void... voids) {


                try {
                    //creating a URL
                    URL url = new URL(urlWebService);

                    //Opening the URL using HttpURLConnection
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    //StringBuilder object to read the string from the service
                    StringBuilder sb = new StringBuilder();

                    //We will use a buffered reader to read the string from service
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    //A simple string to read values from each line
                    String json;

                    //reading until we don't find null
                    while ((json = bufferedReader.readLine()) != null) {

                        //appending it to string builder
                        sb.append(json + "\n");
                    }

                    //finally returning the read string
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }

            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }

    private void loadIntoListView(String json) throws JSONException {
        //creating a json array from the json string
        JSONArray jsonArray = new JSONArray(json);

        //creating a string array for listview
        two_d = new String[jsonArray.length()][8];

        //looping through all the elements in json array

        int k = 0;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            two_d[i][k++] = obj.getString("level_1");
            two_d[i][k++] = obj.getString("Sub_Category1");
            two_d[i][k++] = obj.getString("Sub_Category2");
            two_d[i][k++] = obj.getString("Sub_Category3");
            two_d[i][k++] = obj.getString("Sub_Category4");
            two_d[i][k++] = obj.getString("Sub_Category5");
            two_d[i][k++] = obj.getString("Sub_Category6");
            two_d[i][k++] = obj.getString("Other");
            k = 0;
        }
    }
    private void getJSONnav(final String urlWebService) {
        /*
         * As fetching the json string is a network operation
         * And we cannot perform a network operation in main thread
         * so we need an AsyncTask
         * The constrains defined here are
         * Void -> We are not passing anything
         * Void -> Nothing at progress update as well
         * String -> After completion it should return a string and it will be the json string
         * */
        class GetJSONnav extends AsyncTask<Void, Void, String> {

            //this method will be called before execution
            //you can display a progress bar or something
            //so that user can understand that he should wait
            //as network operation may take some time
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            //this method will be called after execution
            //so here we are displaying a toast with the json string
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    loadIntoListViewNav(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            //in this method we are fetching the json string
            @Override
            protected String doInBackground(Void... voids) {


                try {
                    //creating a URL
                    URL url = new URL(urlWebService);

                    //Opening the URL using HttpURLConnection
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    //StringBuilder object to read the string from the service
                    StringBuilder sb = new StringBuilder();

                    //We will use a buffered reader to read the string from service
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    //A simple string to read values from each line
                    String json;

                    //reading until we don't find null
                    while ((json = bufferedReader.readLine()) != null) {

                        //appending it to string builder
                        sb.append(json + "\n");
                    }

                    //finally returning the read string
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }

            }
        }
        GetJSONnav getJSON = new GetJSONnav();
        getJSON.execute();
    }
    private void loadIntoListViewNav(String json) throws JSONException {
        //creating a json array from the json string
        JSONArray jsonArray = new JSONArray(json);

        //creating a string array for listview
        two_d_nav = new String[jsonArray.length()][13];

        //looping through all the elements in json array

        int k = 0;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            two_d_nav[i][k++] = obj.getString("level_1");
            two_d_nav[i][k++] = obj.getString("Sub_Category1");
            two_d_nav[i][k++] = obj.getString("Sub_Category2");
            two_d_nav[i][k++] = obj.getString("Sub_Category3");
            two_d_nav[i][k++] = obj.getString("Sub_Category4");
            two_d_nav[i][k++] = obj.getString("Sub_Category5");
            two_d_nav[i][k++] = obj.getString("Sub_Category6");
            two_d_nav[i][k++] = obj.getString("answer1");
            two_d_nav[i][k++] = obj.getString("answer2");
            two_d_nav[i][k++] = obj.getString("answer3");
            two_d_nav[i][k++] = obj.getString("answer4");
            two_d_nav[i][k++] = obj.getString("answer5");
            two_d_nav[i][k++] = obj.getString("answer6");
            k = 0;

        }
        for(int i=0;i<jsonArray.length();i++)
            for(int j=0;j<13;j++)
                System.out.println(two_d_nav[i][j]);
    }
}

