// OPTIONS MENU
// -----------------------------------
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.options_menu, menu);
       return true;
   }

   @Override
   public boolean onOptionsItemSelected(@NonNull MenuItem item) {

       Toast.makeText(this,item.getTitle()+" selected", Toast.LENGTH_SHORT).show();

       switch(item.getItemId())
       {
           case R.id.opMenu1ID: {
               Toast.makeText(this, "Item 1 selected", Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(MainActivity.this,recycle.class);
               startActivity(intent);
               return true;
           }
           case R.id.opMenu2ID: {
               Toast.makeText(this, "Item 2 selected", Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(MainActivity.this,fragActivity.class);
               startActivity(intent);
               return true;
           }

           case R.id.opMenu3ID: {
               Toast.makeText(this, "Item 3 selected", Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(MainActivity.this,shrd_pref.class);
               startActivity(intent);
               return true;
           }
       }
       return true;
   }
//-------------------------------------------------------
   //----------------------- POP UP Menu
   //--------- popup menu code -----------------
       popupButton = (Button)findViewById(R.id.btnPopMenuID);
       popupButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Toast.makeText(MainActivity.this, "Pop up menu clicked", Toast.LENGTH_SHORT).show();


               // pop up menu

               PopupMenu popupmenu = new PopupMenu(MainActivity.this, popupButton);
               popupmenu.getMenuInflater().inflate(R.menu.popup_menu, popupmenu.getMenu());

               popupmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                   @Override
                   public boolean onMenuItemClick(MenuItem menuItem) {
                       Toast.makeText(MainActivity.this, "You Clicked " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                       return true;
                   }
               });

           }
       });

       //------ end popup menu code

//----------------------------- CONTEXT Menu -- LIST VIEW
ListView mylistview;
ArrayList<String> personlist = new ArrayList<String>(Arrays.asList("ABC","XYZ","HUU"));

//------- List items display inside oncreate
        mylistview = (ListView) findViewById(R.id.mylistID);
        ArrayAdapter<String> myadapter = new ArrayAdapter<String>(MainActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arrlist);
        mylistview.setAdapter(myadapter);
//----------------------------- CONTEXT MENU -- Context menu
registerForContextMenu(mylistview);//inside onCreate()
@Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        Toast.makeText(this, "Selected"+item.getTitle(), Toast.LENGTH_SHORT).show();
        switch(item.getItemId())
        {
            case R.id.conMenu1ID:
                return true;
            case R.id.conMenu2ID:
                return true;
        }


        return super.onContextItemSelected(item);
    }

// -------------- Fragments-----------------
FragmentTransaction f1 = getSupportFragmentManager().beginTransaction();
frag1 ff1 = new frag1();
f1.replace(R.id.frag_container,ff1);
f1.commit();

//------------------------ RECYCLER VIEW
//------------create

//------------View inside main MainActivity oncreate
RecyclerView rv = (RecyclerView) findViewById(R.id.rvID);
LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());

myadapter myad  = new myadapter(recycle.this,personNames);
rv.setLayoutManager(llm);
rv.setAdapter(myad);

// in custom add function
myad.notifyDataSetChanged()


// ----------in another class
public class myadapter extends RecyclerView.Adapter {
    ArrayList<String> personlist;
    Context context;

    public myadapter(Context context,ArrayList<String> personlist) {
        this.personlist = personlist;
        this.context = context;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
//        myViewHolder vh = new myViewHolder(v);
//        return vh;
         return new myViewHolder(v).linkAdapter(this);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        myViewHolder holder = (myViewHolder)viewHolder;
        holder.t1.setText(personlist.get(position));
        holder.t2.setText(personlist.get(position));
    }

    @Override
    public int getItemCount() {
        return personlist.size();
    }


}

class myViewHolder extends RecyclerView.ViewHolder{
    myadapter adapter;
    TextView t1, t2;
    public myViewHolder(@NonNull View itemView) {
        super(itemView);

        t1 = (TextView) itemView.findViewById(R.id.tv1ID);
        t2 = (TextView) itemView.findViewById(R.id.tv2ID);
    }

    public myViewHolder linkAdapter(myadapter myad)
    {
        this.adapter = myad;
        return this;
    }
}

///with FAB
public class study_material extends AppCompatActivity {

    String []data = {"2+5=7", "2-7 = -5", "3*8 = 24", "8/2 = 4"};
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_material);

        //------- adding items
        List<String> items = new LinkedList<>();
        items.add("examples");

        RecyclerView recyclerView = findViewById((R.id.recyclerView));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DemoAdapter adapter = new DemoAdapter(items);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.btnAddID).setOnClickListener(view -> {
            items.add(data[count%4]);
            count++;
            adapter.notifyItemInserted(items.size()-1);
        });

    }
}


/////---------------------- NOTIFIICATIONS
// inside some button onclick OnClickListener
 Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logoutimg);

 int NOTIFICATION_ID = 234;
 NotificationManager notificationManager = (NotificationManager) MainActivity.this.getSystemService(MainActivity.this.NOTIFICATION_SERVICE);
 String CHANNEL_ID = "my_channel_01";
 if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
     CHANNEL_ID = "my_channel_01";
     CharSequence name = "my_channel";
     String Description = "This is my channel";
     int importance = NotificationManager.IMPORTANCE_HIGH;
     NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
     mChannel.setDescription(Description);
     mChannel.enableLights(true);

     mChannel.enableVibration(true);
     mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
     mChannel.setShowBadge(false);
     notificationManager.createNotificationChannel(mChannel);
 }

 NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
         .setSmallIcon(R.drawable.logoutimg)
         .setLargeIcon(bitmap)
         .setStyle(new NotificationCompat.BigPictureStyle()
                 .bigPicture(bitmap)
                 .bigLargeIcon(bitmap))
         .setContentTitle("ThankYou for using the Mathom App.")
         .setContentText("Please Share the app with your friends!");

//                Intent resultIntent = new Intent(MainActivity.this, notification.class);
 Intent resultIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
 TaskStackBuilder stackBuilder = TaskStackBuilder.create(MainActivity.this);
 stackBuilder.addParentStack(notification.class);
 stackBuilder.addNextIntent(resultIntent);
 PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
 builder.setContentIntent(resultPendingIntent);
 notificationManager.notify(NOTIFICATION_ID, builder.build());

 MainActivity.this.finish();
 System.exit(0);


////////////// SHared Preferences--------------------------------------
/// outside oncreate
SharedPreferences sharedpreferences;
EditText name;
public static final String mypreference = "pref";
public static final String Name = "nameKey";

/// inside oncreate
sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);

if (sharedpreferences.contains(Name)) {
    name.setText(sharedpreferences.getString(Name, ""));

}

/// outside onCreate
public void Save(View view) {

        String n = name.getText().toString();
        String e = email.getText().toString();
        String s = sports.getText().toString();
        String t = teamname.getText().toString();

        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Name, n);
        editor.putString(Email, e);
        editor.putString(Sports, s);
        editor.putString(Teamname, t);

        editor.commit();

        Toast.makeText(this, "contents saved", Toast.LENGTH_SHORT).show();
    }

    public void clear(View view) {

        name.setText("");
        email.setText("");
        sports.setText("");
        teamname.setText("");
        Toast.makeText(this, "contents cleared", Toast.LENGTH_SHORT).show();

    }

    public void Get(View view) {

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        if (sharedpreferences.contains(Name)) {
            name.setText(sharedpreferences.getString(Name, ""));
        }
        if (sharedpreferences.contains(Email)) {
            email.setText(sharedpreferences.getString(Email, ""));

        }
        if (sharedpreferences.contains(Sports)) {
            sports.setText(sharedpreferences.getString(Sports, ""));

        }
        if (sharedpreferences.contains(Teamname)) {
            teamname.setText(sharedpreferences.getString(Teamname, ""));

        }
        Toast.makeText(this, "contents retrieved", Toast.LENGTH_SHORT).show();
    }

