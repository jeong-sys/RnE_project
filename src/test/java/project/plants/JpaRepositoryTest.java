package project.plants;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import project.plants.repo.ImageRepository;

@SpringBootTest
@Transactional
public class JpaRepositoryTest {
    @Autowired ImageRepository imageRepository;

    @Test
    public void findAllFilePathTest() {       
        List<String> datas = imageRepository.findFilePathById(1L);

        datas.forEach(filepath -> System.out.println("test: " + filepath));
    }    
}
