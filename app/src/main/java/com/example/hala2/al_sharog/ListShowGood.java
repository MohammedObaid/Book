package com.example.hala2.al_sharog;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hala2.al_sharog.DB.DB_Handeler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by mohammed_Obaid on 22/09/2018.
 */

public class ListShowGood extends Fragment {
    Context context;
    View view;
    ImageView headerImage;
    int type;

    ArrayList<Book> books;

    DB_Handeler db_handeler;

//    CenterRepository centerRepository;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.listshowgood,container,false);
        context=getActivity();


        RecyclerView recyclerView = (RecyclerView) view
                .findViewById(R.id.product_list_recycler_view);

        LinearLayoutManager linearLayoutManager  = new LinearLayoutManager(
                getActivity().getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

//        centerRepository=new CenterRepository();
        db_handeler=new DB_Handeler(getActivity());
        db_handeler.open();
        books= db_handeler.getAll_Store();
        db_handeler.close();


        ProductListAdapter adapter = new ProductListAdapter(books,
                getActivity());


        adapter.SetOnItemClickListener(new ProductListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

//                if(books.get(position).getType()==1) {
//
//                    if(products.get(position).getId()==8||products.get(position).getId()==9||products.get(position).getId()==10||products.get(position).getId()==11||products.get(position).getId()==12||products.get(position).getId()==13)
//                    {
//                        Toast.makeText(getActivity(),context.getString(R.string.NoInfo),Toast.LENGTH_LONG).show();
//                        return;
//                    }
//                    Bundle bundle = new Bundle();
//                    bundle.putInt("Id", products.get(position).getId());
//                    bundle.putInt("type", products.get(position).getType());
//                    ProductDetails productDetails = new ProductDetails();
//                    productDetails.setArguments(bundle);
//
//                    getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fadein, R.anim.fadeout)
//                            .replace(R.id.Activity_layout, productDetails, "ProductDetails").addToBackStack(null).commit();
//                }
//
//
//                if(products.get(position).getType()==2) {
//                    Bundle bundle = new Bundle();
//                    bundle.putInt("Id", products.get(position).getId());
//                    bundle.putInt("type", products.get(position).getType());
//                    ProductDetails productDetails = new ProductDetails();
//                    productDetails.setArguments(bundle);
//
//                    getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fadein, R.anim.fadeout)
//                            .replace(R.id.Activity_layout, productDetails, "ProductDetails").addToBackStack(null).commit();
//                }
//
//                if(products.get(position).getType()==5) {
//
//                    if(products.get(position).getId()==8||products.get(position).getId()==9||products.get(position).getId()==10||products.get(position).getId()==11||products.get(position).getId()==12||products.get(position).getId()==13)
//                    {
//                        Toast.makeText(getActivity(),context.getString(R.string.NoInfo),Toast.LENGTH_LONG).show();
//                        return;
//                    }
//                    Bundle bundle = new Bundle();
//                    bundle.putInt("Id", products.get(position).getId());
//                    bundle.putInt("type", products.get(position).getType());
//                    ProductDetails productDetails = new ProductDetails();
//                    productDetails.setArguments(bundle);
//
//                    getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fadein, R.anim.fadeout)
//                            .replace(R.id.Activity_layout, productDetails, "ProductDetails").addToBackStack(null).commit();
//                }
//
//                if(products.get(position).getType()==3) {
//
//                    switch (products.get(position).getId())
//                    {
//                        case 99:
//                            if(products.get(position).getUse()!=null)
//                            {
//                                PDFViewer pdfViewer = new PDFViewer();
//                                Bundle bundle=new Bundle();
//                                bundle.putInt("id",99);
//                                pdfViewer.setArguments(bundle);
//                                ((MainActivity)getActivity()).getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fadein, R.anim.fadeout)
//                                        .replace(R.id.Activity_layout, pdfViewer, "PDFViewer").addToBackStack(null).commit();
//                            }
//                            break;
//
//                    }
//
//                }
//
//                if(products.get(position).getType()==4) {
//
//                    switch (products.get(position).getId())
//                    {
//                        case 100:
//                            if(products.get(position).getUse()!=null) {
////                                openPDFFiles(products.get(position).getUse());
//                                PDFViewer pdfViewer = new PDFViewer();
//                                Bundle bundle=new Bundle();
//                                bundle.putInt("id",100);
//                                pdfViewer.setArguments(bundle);
//                                ((MainActivity)getActivity()).getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fadein, R.anim.fadeout)
//                                        .replace(R.id.Activity_layout, pdfViewer, "PDFViewer").addToBackStack(null).commit();
//                            }
//                            break;
//                        case 101:
//                            if(products.get(position).getUse()!=null)
//                            {
////                                openPDFFiles(products.get(position).getUse());
//                                PDFViewer pdfViewer = new PDFViewer();
//                                Bundle bundle=new Bundle();
//                                bundle.putInt("id",101);
//                                pdfViewer.setArguments(bundle);
//                                ((MainActivity)getActivity()).getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fadein, R.anim.fadeout)
//                                        .replace(R.id.Activity_layout, pdfViewer, "PDFViewer").addToBackStack(null).commit();
//                            }
////                                openPDFFiles(products.get(position).getUse());
//                            break;
//                        case 102:
//                            if(products.get(position).getUse()!=null)
//                            {
//
////                                PDFViewer pdfViewer = new PDFViewer();
////                                Bundle bundle=new Bundle();
////                                bundle.putInt("id",102);
////                                ((MainActivity)getActivity()).getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fadein, R.anim.fadeout)
////                                        .replace(R.id.Activity_layout, pdfViewer, "PDFViewer").addToBackStack(null).commit();
//                            }
////                                openPDFFiles(products.get(position).getUse());
//                            break;
//
//
//
//                    }
//
//                }


            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        return view ;
    }



    private void copyFile(InputStream in, OutputStream out) throws IOException

    {
        byte[] buffer = new byte[10*1024];
        int read;
        while ((read = in.read(buffer)) != -1)

        {
            out.write(buffer, 0, read);
        }
    }

    String TAG="TAG";

    private void openPDFFiles(String fileName) //fileName is the pdf file name which is keep in assets folder. ex file.pdf
    {
        AssetManager assetManager = context.getAssets();

        InputStream in = null;
        OutputStream out = null;
        File file = new File(context.getFilesDir(), "");
        try {
            in = assetManager.open(fileName);
            out = context.openFileOutput(file.getName(), MODE_PRIVATE);

            copyFile(in, out);
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.parse("file://" + context.getFilesDir() + "/"+fileName), "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            context.startActivity(intent);
        }catch (RuntimeException ex){

            Toast.makeText(context, "There's no PDF Reader found in your device", Toast.LENGTH_SHORT).show();
        }

    }


    public void displaypdf(String path) {

        File file = null;
        file = new File(path);
        Toast.makeText(context.getApplicationContext(), file.toString() , Toast.LENGTH_LONG).show();
        if(file.exists()) {
            Intent target = new Intent(Intent.ACTION_VIEW);
            target.setDataAndType(Uri.fromFile(file), "application/pdf");
            target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

            Intent intent = Intent.createChooser(target, "Open File");
            try {
                context.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                // Instruct the user to install a PDF reader here, or something
            }
        }
        else
            Toast.makeText(context.getApplicationContext(), "File path is incorrect." , Toast.LENGTH_LONG).show();
    }


}
