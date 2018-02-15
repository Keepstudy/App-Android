package com.example.laercio.hello;

import android.content.Context;
import android.media.Image;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by laercio on 2/14/18.
 */

public class ItemFeedAdapter extends BaseAdapter {

    LayoutInflater mInflater;
    Context feedContexto;

    String[] feedFotoPerfil;
    String[] feedNome;
    String[] feedObjetivo;
    String[] feedPostagemImagem;
    String[] feedDataRefeicao;
    String[] feedCalorias;

    public ItemFeedAdapter(Context contexto, String[] fotoPerfil, String[] nome, String[] objetivo,
                           String[] postagemImagem, String[] dataRefeicao, String[] calorias){
        feedFotoPerfil = fotoPerfil;
        feedNome = nome;
        feedObjetivo = objetivo;
        feedPostagemImagem = postagemImagem;
        feedDataRefeicao = dataRefeicao;
        feedCalorias = calorias;
        feedContexto = contexto;
        mInflater = (LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return feedNome.length;
    }

    @Override
    public Object getItem(int i) {
        return feedNome[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = mInflater.inflate(R.layout.itemnewsfeed,null);

        ImageView fotoPerfilImageView   = (ImageView) v.findViewById(R.id.feedFotoPerfil);
        ImageView fotoPostagemImageView = (ImageView) v.findViewById(R.id.feedPostagemImagem);

        TextView nomeTextView         = (TextView) v.findViewById(R.id.feedNome);
        TextView objetivoTextView     = (TextView) v.findViewById(R.id.feedObjetivo);
        TextView caloriasTextView     = (TextView) v.findViewById(R.id.feedCalorias);
        TextView dataRefeicaoTextView = (TextView) v.findViewById(R.id.feedDataRefeicao);

        String fotoPerfil     = feedFotoPerfil[i];
        String nome           = feedNome[i];
        String calorias       = feedCalorias[i];
        String dataRefeicao   = feedDataRefeicao[i];
        String objetivo       = feedObjetivo[i];
        String postagemImagem = feedPostagemImagem[i];


        nomeTextView.setText(nome);
        objetivoTextView.setText(objetivo);
        caloriasTextView.setText(calorias);
        dataRefeicaoTextView.setText(dataRefeicao);

        // Perfil Picture
        fotoPerfilImageView.setMaxHeight(80);
        fotoPerfilImageView.setMaxWidth(80);
        fotoPerfilImageView.setAdjustViewBounds(true);

        if(fotoPerfil != null)
            Picasso.with(feedContexto).load(fotoPerfil).into(fotoPerfilImageView);

        //Post Picture
        fotoPostagemImageView.setMaxHeight(280);
        fotoPostagemImageView.setMaxWidth(320);
        fotoPostagemImageView.setAdjustViewBounds(true);
        Picasso.with(feedContexto).load(postagemImagem).into(fotoPostagemImageView);

        return v;
    }
}
