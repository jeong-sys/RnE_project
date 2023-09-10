package project.plants.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    private JdbcTemplate jdbcTemplate; // JDBC 객체 생성 (JDBC 데이터 베이스에 대한 접근 방식, 구조적 반복 줄일 수 있음) : SQL쿼리 실행


    public List<String> getImages(int page, String condition){
        ArrayList<String> encodedImageList = new ArrayList<>();

        int startIndex = (page - 1) * 2 + 1; // 페이지별 이미지 시작 인덱스 (2장씩 페이지마다 보여줌)
        int endIndex = page * 2; // 페이지 이미지 끝 인덱스

        String sql = "SELECT filepath FROM " + condition + " WHERE ID >= ? AND ID <= ?"; // filepath 가져오는 SQL 쿼리

        try {
            // SQL 쿼리 실행(조회)
            jdbcTemplate.query(sql, new Object[]{startIndex, endIndex}, new RowMapper<String>() { // RowMapper : ResultSet을 객체로 변환
                @Override
                public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                    // RowMapper의 mapRow() 메서드 : rs(결과값을 담아 사용자가 원하는 객체에 담음), rowNum(반복되는 루프 중 현재 행의 번호를 나타냄)
                    try {
                        String filepath = rs.getString("filepath"); // 파일 경로 가져옴
                        System.out.println("filepath : " + filepath);
                        byte[] imageBytes = Files.readAllBytes(Paths.get(filepath)); // 이미지 바이트 읽어옴
                        return Base64.getEncoder().encodeToString(imageBytes); // 이미지 바이트를 Base64로 인코딩
                    } catch (Exception e) {
                        throw new SQLException("Failed to read the file.", e);
                    }
                }
            }).forEach(encodedImage -> encodedImageList.add(encodedImage)); // 인코딩 이미지 리스트에 추가
            // forEach 람다식 : list.forEach(변수 -> 반복처리(변수))

        } catch (Exception e) {
            System.out.println("SQL 문이 틀렸습니다");
            e.printStackTrace(); // 예외 당시 메서드 정보와 예외 메시지 화면 출력
        }

        return encodedImageList; // 인코딩 이미지 리스트 반환
    }
}