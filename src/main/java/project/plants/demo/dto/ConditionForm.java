//package project.plants.demo.dto;
//
//import lombok.Data;
//import project.plants.demo.entity.PlantEntity;
//
//@Data // 생성자(디폴트, All), getter, setter, toString 등 모두 생성
//public class ConditionForm {
//    private String pcondition;
//
//    // 빌더 패턴으로 객체 생성
//    public PlantEntity toEntity(){
//        return PlantEntity.builder()
//                .id(null)
//                .pcondition(pcondition)
//                .build();
//    }
//}
