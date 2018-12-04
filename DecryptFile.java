package com.adi.testawssdk;

import com.amazonaws.services.kms.AWSKMS;
import com.amazonaws.services.kms.AWSKMSClientBuilder;
import com.amazonaws.services.kms.model.DecryptRequest;
import com.amazonaws.services.kms.model.DecryptResult;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class App {
    public static void main(String[] args) throws IOException {
        AWSKMS kmsClient = AWSKMSClientBuilder.standard().withRegion("us-east-1").build();
        RandomAccessFile aFile = new RandomAccessFile("/Users/adi/Downloads/ec.txt", "r");
        FileChannel inChannel = aFile.getChannel();
        long fileSize = inChannel.size();
        ByteBuffer buffer = ByteBuffer.allocate((int) fileSize);
        inChannel.read(buffer);
        buffer.flip();
        inChannel.close();
        aFile.close();
        DecryptRequest decryptRequest = new DecryptRequest();
        decryptRequest.setCiphertextBlob(buffer);
        DecryptResult decryptResult = kmsClient.decrypt(decryptRequest);
        System.out.println(new String(decryptResult.getPlaintext().array()));

        System.out.println("Hello World!");
    }
}
