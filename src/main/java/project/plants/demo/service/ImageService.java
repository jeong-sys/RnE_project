package project.plants.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import project.plants.demo.repo.test1_Repository;
import project.plants.demo.repo.test2_Repository;
import project.plants.demo.repo.test3_Repository;
import project.plants.demo.repo.test4_Repository;

@Service
public class ImageService {
    private final test1_Repository test1Repository;
    private final test2_Repository test2Repository;
    private final test3_Repository test3Repository;
    private final test4_Repository test4Repository;
    @Autowired
    public ImageService(@Qualifier("test1_Repository") test1_Repository test1Repository,
                        @Qualifier("test2_Repository") test2_Repository test2Repository,
                        @Qualifier("test3_Repository") test3_Repository test3Repository,
                        @Qualifier("test4_Repository") test4_Repository test4Repository){
        this.test1Repository = test1Repository;
        this.test2Repository = test2Repository;
        this.test3Repository = test3Repository;
        this.test4Repository = test4Repository;
    }

    public List<String> getImages(int page, String condition) {

        ArrayList<String> encodedImages = new ArrayList<>();

        Long startIndex = Long.valueOf((page - 1) * 2 + 1);

        if("test1".equals(condition)){
            test1Repository.findFilePathById(startIndex).forEach(filePath -> {
                try{
                    byte[] imageBytes = Files.readAllBytes(Paths.get(filePath));
                    encodedImages.add(Base64.getEncoder().encodeToString(imageBytes));
                } catch (IOException e) {
                }
            });
        }
        if("test2".equals(condition)){
            test2Repository.findFilePathById(startIndex).forEach(filePath -> {
                try{
                    byte[] imageBytes = Files.readAllBytes(Paths.get(filePath));
                    encodedImages.add(Base64.getEncoder().encodeToString(imageBytes));
                } catch (IOException e) {
                }
            });
        }
        if("test3".equals(condition)){
            test3Repository.findFilePathById(startIndex).forEach(filePath -> {
                try{
                    byte[] imageBytes = Files.readAllBytes(Paths.get(filePath));
                    encodedImages.add(Base64.getEncoder().encodeToString(imageBytes));
                } catch (IOException e) {
                }
            });
        }
        if("test4".equals(condition)){
            test4Repository.findFilePathById(startIndex).forEach(filePath -> {
                try{
                    byte[] imageBytes = Files.readAllBytes(Paths.get(filePath));
                    encodedImages.add(Base64.getEncoder().encodeToString(imageBytes));
                } catch (IOException e) {
                }
            });
        }
        return encodedImages;
    }

}