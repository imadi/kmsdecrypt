package com.adi.testawssdk;

import com.amazonaws.services.kms.AWSKMS;
import com.amazonaws.services.kms.AWSKMSClientBuilder;
import com.amazonaws.services.kms.model.DecryptRequest;
import com.amazonaws.services.kms.model.DecryptResult;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) throws IOException {
        AWSKMS kmsClient = AWSKMSClientBuilder.standard().withRegion("us-east-1").build();
        Path path = Paths.get("/Users/adi/Downloads/ec.txt");
        byte[] bytes = Files.readAllBytes(path);
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        DecryptRequest decryptRequest = new DecryptRequest();
        decryptRequest.setCiphertextBlob(buffer);
        DecryptResult decryptResult = kmsClient.decrypt(decryptRequest);
        System.out.println("DecryptedText" + new String(decryptResult.getPlaintext().array()));

        System.out.println("Hello World!");
    }
}
