package io.sambell.shop.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import io.sambell.shop.entity.metier.User;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.Normalizer;

public class QRCodeGenerator {
    public static void generateQRCode(User user) {
        String outputMessage = "";
        try {
            String qrCodePath = "../.docs/qrcode/";
            String qrCodeName = qrCodePath + user.getId() + "-qrcode.png";
            // String qrCodeName = qrCodePath + user.getNom() +"-" + user.getPrenom() + "-" + user.getId() + "-qrcode.png";

            var qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(
            "Member Code: " + user.getId() +
                    "\nNom: " + user.getLastName() +
                    "\nPrenom: " + user.getFirstName() +
                    "\nEmail: " + user.getEmail() +
                    "\nRole: " + user.getRole(),
                    BarcodeFormat.QR_CODE,
                    250,
                    250
            );

            Path path = FileSystems.getDefault().getPath(Normalizer.normalize(qrCodeName.toLowerCase(), Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", ""));

            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
            outputMessage = "Print qrcode done";
        } catch (Exception e) {
            outputMessage = "Print qrcode fail";
            System.out.println("Error Cause -> " + e.getCause());
            System.out.println("Error Message -> " + e.getMessage());
        } finally {
            System.out.println(outputMessage);
        }
    }

}
