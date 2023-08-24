package project.plants.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.plaf.nimbus.State;
import java.sql.*;

@Controller
public class ImageGetController {

    // DB 연결
    @Value("${db.url}")
    private String url;

    @Value("${db.user}")
    private String user;

    @Value("${db.password}")
    private String password;

    private Connection con;
    private Statement stmt;
    private ResultSet rs; // resultset : 결과값 저장 및 한 행 단위로 불러오기 가능

    @GetMapping("/getImage")
    @ResponseBody // view 페이지가 아닌, 실제 데이터(=사진)으로 반환
    public byte[] getImage() {

        byte[] imageData = null; // 이미지 데이터 저장

        try  {
            con = DriverManager.getConnection(this.url, this.user, this.password);
            System.out.println("DB 연결 성공");
            stmt = con.createStatement();
            System.out.println("statement 객체 생성 성공");

            // 이미지 가져오기, plants 테이블 조회
            rs = stmt.executeQuery("SELECT FILE FROM plants WHERE ID=1"); // 아이디가 1인 이미지 불러오기
            if (rs.next()) {
                imageData = rs.getBytes("FILE"); // byte 배열로 가져옴
            }
            rs.close();

        } catch (SQLException e) { // 오류 내용 출력
            System.out.println("DB연결 실패하거나, SQL문이 틀렸습니다.");
        }

        return imageData;
    }

    // view 로 출력
    @GetMapping("/showImagePage")
    public String showImagePage() {
        return "displayImage";
    }

}
