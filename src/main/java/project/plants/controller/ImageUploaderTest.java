package project.plants.controller;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ImageUploaderTest {

    public static void main(String[] args) {

        File imageFile = new File("/Users/heoyujeong/r&e/plants/src/main/resources/static/img/output1.png"); // 사진 파일 경로
        File imageFile2 = new File("/Users/heoyujeong/r&e/plants/src/main/resources/static/img/output2.jpeg");

        try {
            // DB 정보
            String url = "jdbc:mysql://localhost:3306/plants_db";
            String user = "root";
            String password = "12345678";

            Connection con = DriverManager.getConnection(url, user, password); // DB 연결
            FileInputStream fis = new FileInputStream(imageFile); // FileInputStream : 파일로부터 바이트 입력받아 바이트 단위로 출력

            // SQL 구문 실행 Statement 클래스 기능 향, 매개변수(인자) 관련 작업에 특화
            PreparedStatement st = con.prepareStatement("INSERT INTO water_o(id, days, filename, FILE, environment_id) VALUES (?, ?, ?, ?, ?)");

            // 2
            st.setInt(1,3); // set을 이용해서 "?" 자리에 값을 집어넣음, 인덱스 해당 숫자 : 물음표의 순번
            st.setInt(2,2);
            st.setString(3, "real");
            st.setBinaryStream(4, fis, (int)imageFile2.length()); // Stream 형의 파일 업로드
            st.setInt(5, 1);
            st.executeUpdate();

            System.out.println("이미지 업로드 성공");
            st.close();

        } catch (Exception e) {
            System.out.println("업로드 실패");
        }
    }

}
