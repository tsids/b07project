package com.example.b07project;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductsFragment extends Fragment {
    FirebaseDatabase db;

    //This needs to be replaced with a value from the store-activity (an intention)
    String KEY = "0";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayList<Product> products = new ArrayList<>();
    public ProductsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductsFragment newInstance(String param1, String param2) {
        ProductsFragment fragment = new ProductsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    private void setProducts(DataSnapshot snapshot, RecyclerView recycler){
        ArrayList<Product> products = new ArrayList<>();
        for (DataSnapshot postSnapshot: snapshot.getChildren()) {
            Product product = postSnapshot.getValue(Product.class);
            products.add(product);
        }
        recycler.setAdapter(new OwnerProductRecyclerAdapter(this.getContext(),products,this));
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_products, container, false);

        db = FirebaseDatabase.getInstance("https://b07project-4cc9c-default-rtdb.firebaseio.com/");
        DatabaseReference ref= db.getReference();
        //How the key is found will need to be updated
        DatabaseReference query = ref.child("stores").child(KEY).child("products");

        RecyclerView recycler = v.findViewById(R.id.prod_recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                setProducts(snapshot,recycler);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return v;
    }

    public void loadEdit(int pos){
        DatabaseReference ref= db.getReference();
        DatabaseReference query = ref.child("stores").child(KEY).child("products");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> keys = new ArrayList<>();

                Intent intent = new Intent(getContext(), EditProductActivity.class);

                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    keys.add(key);
                }

                intent.putExtra("prod_id", keys.get(pos));
                intent.putExtra("store_id",KEY);

                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //intent.putExtra("pos")
    }
}