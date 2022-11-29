package com.optic.socialmediagamer.providers;

import android.content.Context;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.optic.socialmediagamer.utils.CompressorBitmapImage;

import java.io.File;
import java.util.Date;

public class ImageProvider {

    StorageReference mStorage;

    public ImageProvider() {
        mStorage = FirebaseStorage.getInstance().getReference();
    }

    public UploadTask save(Context context, File file) {
        byte[] imageByte = CompressorBitmapImage.getImage(context, file.getPath(), 200, 200);
        StorageReference storage = FirebaseStorage.getInstance().getReference().child(new Date() + ".jpg");
        mStorage = storage;
        return storage.putBytes(imageByte);
    }

    public StorageReference getStorage() {
        return mStorage;
    }

}
