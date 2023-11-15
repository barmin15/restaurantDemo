package com.restaurant.restaurantdemoserver.qrCode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class QrCodeGenerator {
    public static byte[] generateByteQRCode(String text, int width, int height) {
        ByteArrayOutputStream outputStream = null;
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
            outputStream = new ByteArrayOutputStream();
            MatrixToImageConfig config = new MatrixToImageConfig(0xFFF8EDE3, 0xFF798777);
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream, config);

        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
        assert outputStream != null;
        return outputStream.toByteArray();
    }
}
