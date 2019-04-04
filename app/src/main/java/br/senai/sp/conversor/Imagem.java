package br.senai.sp.conversor;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class Imagem {

    public static Bitmap arrayToBitmap(byte[] imagemArray){
        Bitmap imagemBitmap = BitmapFactory.decodeByteArray(imagemArray, 0, imagemArray.length);
        return imagemBitmap;
    }

    public static byte[] ImageViewToArray(ImageView imageView){
        Bitmap bm = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bm, 700, 400, true);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapReduzido.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);

        return byteArrayOutputStream.toByteArray();
    }
}
