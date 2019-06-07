package com.example.software;


import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class firebase_add extends AppCompatActivity {
    private static final String TAG = "firebase_add";

    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIPTION = "description";
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri mImageUri;

    private DatabaseReference mDatabaseRef;

   // private StorageTask mUploadTask;
    private EditText editTextTitle;
    private EditText editTextDescription;
    private Button button,add;
    private TextView a,b;
    private ImageView mimage;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private StorageReference mStorageRef;
    private String downloadimageurl;
    // Write a message to the database
  /*FirebaseDatabase data = FirebaseDatabase.getInstance();
    DatabaseReference myRef = data.getReference();
    DatabaseReference child = myRef.child("a");*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firebaseadd);
        button = findViewById(R.id.login);
        //  mStorageRef = FirebaseStorage.getInstance().getReference();
        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        mimage = findViewById(R.id.image);
        a = findViewById(R.id.textView2);
        b = findViewById(R.id.textView3);
        add = findViewById(R.id.add);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl("gs://softwarefinal-df21c.appspot.com/").child("cairo-university-696x298.jpg");
        try {
            final File localFile = File.createTempFile("cairo-university-696x298", "jpg");
            storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    mimage.setImageBitmap(bitmap);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                }
            });
        } catch (IOException e ) {
            Toast.makeText(firebase_add.this, "Error!", Toast.LENGTH_SHORT).show();

        }
        // mStorageRef = FirebaseStorage.getInstance().getReference("Uploads");
    /*    mStorageRef = FirebaseStorage.getInstance().getReference("Cairo");
        //final StorageReference storageReference = mStorageRef
               // .child(filePaths.FIREBASE_IMAGE_STORAGE + "/" + user_id + "/photo" + (count + 1));
      mDatabaseRef = FirebaseDatabase.getInstance().getReference("Cairo");
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //saveNote();
                //    OpenActivity_added();
//read();
                openFileChooser();
            }

        });
        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                    uploadFile();


            }

        });
    }
   /* @Override
    protected void onStart(){
        super.onStart();
        child.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String str =dataSnapshot.getValue(String.class);
                a.setText(str);
                Picasso.get()
                        .load(str)
                        .into(mimage);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }*/
   /* public void saveNote() {
        int title =R.drawable.back;
                //editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();

        Map<String, Object> note = new HashMap<>();
        note.put(KEY_TITLE, title);
        //note.put(KEY_DESCRIPTION, description);

        db.collection("Notebook").document("My First Note").set(note)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(firebase_add.this, "Note saved", Toast.LENGTH_SHORT).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(firebase_add.this, "Error!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });
    }
    public void read() {
        db.collection("Notebook")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Toast.makeText(firebase_add.this, "Note read", Toast.LENGTH_SHORT).show();

                                Log.d(TAG, document.getId() + " => " + document.getData());
                                a.setText(document.getId() + " => " +document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }
    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();
mimage.setImageURI(mImageUri);
            //Picasso.with(this).load(mImageUri).into(mimage);
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile() {
        Toast.makeText(firebase_add.this, "xxx", Toast.LENGTH_SHORT).show();
        if (mImageUri != null) {
            final StorageReference filepath = mStorageRef.child(mImageUri.getLastPathSegment()
                    + "." + getFileExtension(mImageUri));
            final UploadTask uploadTask= filepath.putFile(mImageUri);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(firebase_add.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                } }).
                addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                           // Toast.makeText(firebase_add.this, "image uploaded successfully", Toast.LENGTH_SHORT).show();


                            Toast.makeText(firebase_add.this, "Upload successful", Toast.LENGTH_SHORT).show();
                            Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                                @Override
                                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                    if(!task.isSuccessful()){
                                        throw task.getException();
                                    }
                                   downloadimageurl=filepath.getDownloadUrl().toString();
                                    return  filepath.getDownloadUrl();
                                }
                            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    if (task.isSuccessful()){
                                        downloadimageurl=task.getResult().toString();
                                        Toast.makeText(firebase_add.this, "got url is success ", Toast.LENGTH_SHORT).show();
                                  // saveproductintodatabase();
                                    }
                                }
                            });

                        }
                    });
                   /* .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(firebase_add.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })*/
                  /* .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            mProgressBar.setProgress((int) progress);
                        }
                    });*/
      /*  } else {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }

        }

  /*  private void saveproductintodatabase() {
        HashMap<String,Object> productMap= new HashMap<>();
        productMap.put("image",downloadimageurl);
        mDatabaseRef.child(downloadimageurl).updateChildren(productMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
if(task.isSuccessful()){
    Toast.makeText(firebase_add.this, "product is save successful", Toast.LENGTH_SHORT).show();



else{
    Toast.makeText(firebase_add.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
}
            }
        });
   } }*/
    }}