package project.plants.controller;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ImageUploader{

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/plants";
        String user = "root";
        String password = "12345678";

        // DB에 사진 업로드 후, 제대로 업로드 되었는지 확인해보기.

        File imageFile = new File("/Users/heoyujeong/r&e/plants/src/main/resources/static/img/output2.png"); // 사진 파일 경로

        try (Connection connection = DriverManager.getConnection(url, user, password);
             FileInputStream fis = new FileInputStream(imageFile);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO plants (ID, FILENAME, FILE) VALUES (?, ?, ?)")) {
            // key가 없음

            statement.setInt(1,1); // set을 이용해서 "?" 자리에 값을 집어넣음, 인덱스 해당 숫자 : 물음표의 순번
            statement.setString(2, "X-ray");
            statement.setBinaryStream(3, fis, (int)imageFile.length()); // Stream 형의 파일 업로드
            statement.executeUpdate();

//            byte[] imageData = new byte[(int) imageFile.length()];
//            fis.read(imageData);
//
//            statement.setBytes(1, imageData);
//            statement.executeUpdate();

            System.out.println("Image uploaded successfully!");
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
