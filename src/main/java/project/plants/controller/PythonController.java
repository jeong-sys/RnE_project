package project.plants.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.python.util.PythonInterpreter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/showTotal")
public class PythonController {
    private String trainingStatus = "Pending";

    @GetMapping
    public String showTrainingPage() {
        trainingStatus = "학습중";
        runPythonScript();
        trainingStatus = "학습 종료";
        return "pythonCheck";
    }

    @GetMapping("/getTrainingStatus")
    @ResponseBody
    public String getTrainingStatus() {
        return trainingStatus;
    }

    public String runPythonScript() {

        StringBuilder output = new StringBuilder();

        String pythonScriptPath = "src/main/python/Learning.py";

        ProcessBuilder processBuilder = new ProcessBuilder("python", pythonScriptPath);

        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                // Handle error
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();
    }

    @PostMapping("/uploadImage")
    public ModelAndView uploadImage(@RequestParam("image") MultipartFile image) {
        ModelAndView modelAndView = new ModelAndView();

        try {
            // 이미지를 /src/save 디렉토리에 저장
            String saveDir = "src/main/resources/static/predictImg/";
            Path filePath = Paths.get(saveDir + image.getOriginalFilename());
            Files.write(filePath, image.getBytes());

            // 파이썬 스크립트 실행하여 이미지 라벨 예측
            ProcessBuilder processBuilder = new ProcessBuilder("python", "src/main/python/predict.py", filePath.toString());
            Process process = processBuilder.start();

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            String predictedLabel = stdInput.readLine(); // 예측된 라벨 가져오기

            // 에러 출력
            String error;
            while ((error = stdError.readLine()) != null) {
                System.out.println("Error: " + error);
            }

            // 예측 결과 출력
            System.out.println("Predicted label: 1" + predictedLabel);

            modelAndView.addObject("result", predictedLabel);
            modelAndView.setViewName("predictionResult");

        } catch (Exception e) {
            e.printStackTrace();
            modelAndView.setViewName("errorPage"); // 에러 페이지로 리디렉션
        }

        return modelAndView;
    }

//    @PostMapping("/uploadImage")
//    public ModelAndView uploadImage(@RequestParam("image") MultipartFile image) {
//        ModelAndView modelAndView = new ModelAndView();
//
//        try {
//            // 이미지를 /src/save 디렉토리에 저장
//            String saveDir = "src/main/resources/static/predictImg/";
//            Path filePath = Paths.get(saveDir + image.getOriginalFilename());
//            Files.write(filePath, image.getBytes());
//
//            // 파이썬 스크립트 실행하여 이미지 라벨 예측
//            ProcessBuilder processBuilder = new ProcessBuilder("python", "predict.py", filePath.toString());
//            Process process = processBuilder.start();
//
//            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            String predictedLabel = reader.readLine(); // 예측된 라벨 가져오기
//
//            modelAndView.addObject("result", predictedLabel);
//            modelAndView.setViewName("predictionResult");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            modelAndView.setViewName("errorPage"); // 에러 페이지로 리디렉션 (선택 사항)
//        }
//
//        return modelAndView;
//    }
}