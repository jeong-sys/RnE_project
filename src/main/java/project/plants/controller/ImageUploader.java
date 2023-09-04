package project.plants.controller;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ImageUploader{
    public static void main(String[] args) {
        File imageFile = new File("./src/main/resources/static/img/output1.png"); // 사진 파일 경로
        
        try {
            // DB 정보
            String url = "jdbc:mysql://localhost:3401/plant";
            String user = "root";
            String password = "1qaz!@#$%";

            Connection con = DriverManager.getConnection(url, user, password); // DB 연결
            FileInputStream fis = new FileInputStream(imageFile); // FileInputStream : 파일로부터 바이트 입력받아 바이트 단위로 출력

            // SQL 구문 실행 Statement 클래스 기능 향, 매개변수(인자) 관련 작업에 특화
            PreparedStatement statement = con.prepareStatement("INSERT INTO test (ID, FILENAME, FILE) VALUES (?, ?, ?)");

            statement.setInt(1,1); // set을 이용해서 "?" 자리에 값을 집어넣음, 인덱스 해당 숫자 : 물음표의 순번
            statement.setString(2, "X-ray");
            statement.setBinaryStream(3, fis, (int)imageFile.length()); // Stream 형의 파일 업로드
            statement.executeUpdate();

            System.out.println("이미지 업로드 성공");
            statement.close();

        } catch (Exception e) {
            System.out.println(e.toString());
            System.out.println("업로드 실패");
        }
    }
}
