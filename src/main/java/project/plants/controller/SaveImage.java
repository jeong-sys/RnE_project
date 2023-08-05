package project.plants.controller;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SaveImage {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/plants";
        String user = "root";
        String password = "12345678";

        // 이미지 파일 경로
        File imageFile = new File("/Users/heoyujeong/Downloads/img_1.png");

        try (Connection connection = DriverManager.getConnection(url, user, password);
             FileInputStream fis = new FileInputStream(imageFile);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO images (data) VALUES (?)")) {

            byte[] imageData = new byte[(int) imageFile.length()];
            fis.read(imageData);

            statement.setBytes(1, imageData);
            statement.executeUpdate();
        }
    }
}
