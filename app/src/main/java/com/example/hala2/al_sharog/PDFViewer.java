package com.example.hala2.al_sharog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.barteksc.pdfviewer.PDFView;

/**
 * Created by mohammed_Obaid on 28/10/2018.
 */

public class PDFViewer  extends Fragment{
    PDFView pdfView;
    int id;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.pdfviewer,container,false);
        id =getArguments().getInt("id");
        if(id==99) {
            pdfView = (PDFView) view.findViewById(R.id.pdfView);
            pdfView.fromAsset("files/batteryone.pdf").load();

        }
        else if(id==100) {
            pdfView = (PDFView) view.findViewById(R.id.pdfView);
            pdfView.fromAsset("files/tiresopen.pdf").load();

        }
        else if(id==101) {
            pdfView = (PDFView) view.findViewById(R.id.pdfView);
            pdfView.fromAsset("files/tirestow.pdf").load();

        }




        return view;
    }
}
