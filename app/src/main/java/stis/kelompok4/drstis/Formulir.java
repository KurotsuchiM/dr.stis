//package stis.kelompok4.drstis;
//
//public class Formulir extends AppCompatActivity {
//
//	EditText reservasi_nama, reservasi_tanggal, reservasi_jam, keluhan;
//	Button submit;
//	RequestQueue requestQueue;
//	String insertUrl = "http://192.168.100.6/res/insert_form.php";
//
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.formulir);
//		//formulir layout itu buat sementara jadi nanti diganti yang bener
//
//		reservasi_nama = (EditText) findViewById(R.id.editText);
//		reservasi_tanggal = (EditText) findViewById(R.id.editText2);
//		reservasi_jam = (EditText) findViewById(R.id.editText3);
//		submit = (Button) findViewById(R.id.submit);
//
//		requestQueue = Volley.newRequestQueue(getApplicationContext());
//
//	insert.setOnClickListener(new View.OnClickListener() {
//		public void onClick(View view) {
//			StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>(){
//				@Override
//				public void onResponse(String response) {
//					System.out.println(response.toString());
//				}
//			}, new Response)
//		}
//	})
//
//	}
//}
