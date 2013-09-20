package com.example.androiddeneme2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	private ListView listview;
	private static ArrayList<Track> veritabaniParcalar;
	private ArrayAdapter arrayAdapter;
	private static ArrayList<String> titleList;

	private static final String NAMESPACE = "http://aliyesilkanat.somee.com";
	private static final String METHOD_NAME = "getLinks";// your webservice
															// web method name
	private static final String SOAP_ACTION = "http://aliyesilkanat.somee.com/getLinks";
	private static final String URL = "http://aliyesilkanat.somee.com/webservice.asmx";

	private static final int ID_ERASE = 5;
	private static int silinecekParcaID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		ActionItem eraseItem = new ActionItem(ID_ERASE, "Delete", getResources()
				.getDrawable(R.drawable.ic_delete));
		final QuickAction quickAction = new QuickAction(this,
				QuickAction.VERTICAL);
		quickAction.addActionItem(eraseItem);

		parcalariCek();

		listview = (ListView) findViewById(R.id.lwParcalar);
		arrayAdapter = new ArrayAdapter(this,
				android.R.layout.simple_list_item_1, titleList);
		listview.setAdapter(arrayAdapter);
		listview.setTextFilterEnabled(true);
		quickAction
				.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
					@Override
					public void onItemClick(QuickAction source, int pos,
							int actionId) {
						ActionItem actionItem = quickAction.getActionItem(pos);
						
						// here we can filter which action item was clicked with
						// pos or actionId parameter

						if (actionId == ID_ERASE)
						// SÝLME PROSEDÜRÜ
						{
							parcaSil(silinecekParcaID);
							parcalariCek();
							listview = (ListView) findViewById(R.id.lwParcalar);
							arrayAdapter = new ArrayAdapter(MainActivity.this,
									android.R.layout.simple_list_item_1,
									titleList);
							listview.setAdapter(arrayAdapter);
						}

					}
				});

		listview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long id) {

				Uri uri = Uri.parse(veritabaniParcalar.get(position).getLink());
				startActivity(new Intent(Intent.ACTION_VIEW, uri));
			}
		});
		listview.setOnItemLongClickListener(new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int pos, long id) {
				// TODO Auto-generated method stub
				silinecekParcaID = veritabaniParcalar.get(pos).getID();
				quickAction.show(arg1);
				return true;
			}
		});
	}

	private static String soundcloudJsonParsing(String TrackURL) {
		try {
			// Create a new HTTP Client
			DefaultHttpClient defaultClient = new DefaultHttpClient();
			// Setup the get request
			HttpGet httpGetRequest = new HttpGet(
					"http://api.soundcloud.com/resolve.json?url=" + TrackURL
							+ "&client_id=e34e376166a1cc64a549df85f16e5b2e");

			// Execute the request in the client
			HttpResponse httpResponse = defaultClient.execute(httpGetRequest);
			// Grab the response
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					httpResponse.getEntity().getContent(), "UTF-8"));
			String json = reader.readLine();

			// Instantiate a JSON object from the request response
			JSONObject jsonObject = new JSONObject(json);
			return jsonObject.getString("title");
		} catch (Exception e) {
			// In your production code handle any errors and catch the
			// individual exceptions
			e.printStackTrace();
		}

		return null;
	}

	private static void parcalariCek() {
		veritabaniParcalar = new ArrayList<Track>();
		titleList = new ArrayList<String>();
		SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;
		envelope.setOutputSoapObject(Request);

		try {
			HttpTransportSE aht = new HttpTransportSE(URL);
			aht.call(SOAP_ACTION, envelope);

			SoapObject result = (SoapObject) envelope.bodyIn;
			SoapObject result2 = (SoapObject) result.getProperty(0);

			for (int i = 0; i < result2.getPropertyCount(); i++) {
				SoapObject resultTrack = (SoapObject) result2.getProperty(i);
				veritabaniParcalar.add(new Track(Integer.valueOf(resultTrack
						.getProperty(0).toString()), resultTrack.getProperty(1)
						.toString()));
			}

			for (int i = 0; i < veritabaniParcalar.size(); i++) {
				veritabaniParcalar.set(i, new Track(veritabaniParcalar.get(i)
						.getID(), veritabaniParcalar.get(i).getLink(),
						soundcloudJsonParsing(veritabaniParcalar.get(i)
								.getLink())));
			}

			for (Track t : veritabaniParcalar)
				titleList.add(t.getTitle());

		}

		catch (Exception e) {
			e.printStackTrace();

		}

	}

	private static void parcaSil(int id) {
		SoapObject Request = new SoapObject(NAMESPACE, "deleteLink");
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		 
	        Request.addProperty("id",id);
	        
		envelope.dotNet = true;
//		envelope.encodingStyle=SoapEnvelope.ENC;
//		envelope.implicitTypes=true;
		envelope.setOutputSoapObject(Request);
		
		HttpTransportSE aht = new HttpTransportSE(URL);
		aht.debug=true;
		try {
			
			aht.call("http://aliyesilkanat.somee.com/deleteLink", envelope);
			SoapObject response = (SoapObject)envelope.bodyIn;
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;

	}

}
