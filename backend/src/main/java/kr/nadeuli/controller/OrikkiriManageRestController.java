package kr.nadeuli.controller;

import kr.nadeuli.dto.AnsQuestionDTO;
import kr.nadeuli.dto.OrikkiriDTO;
import kr.nadeuli.dto.SearchDTO;
import kr.nadeuli.service.orikkirimanage.OrikkiriManageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orikkiriManage")
@RequiredArgsConstructor
@Log4j2
public class OrikkiriManageRestController {

    private final OrikkiriManageService orikkiriManageService;

    @PostMapping("/addOrikkiri")
    public String addOrikkiri(@RequestBody OrikkiriDTO orikkiriDTO) throws Exception {
        orikkiriManageService.addOrikkiri(orikkiriDTO);
        return "{\"success\": true}";
    }

    @PostMapping("/updateOrikkiri")
    public String updateOrikkiri(@RequestBody OrikkiriDTO orikkiriDTO) throws Exception {
        orikkiriManageService.updateOrikkiri(orikkiriDTO);
        return "{\"success\": true}";
    }

    @GetMapping("/getOrikkiri/{orikkiriId}")
    public OrikkiriDTO getOrikkiri(@PathVariable long orikkiriId) throws Exception {
        return orikkiriManageService.getOrikkiri(orikkiriId);
    }

    @GetMapping("/deleteOrikkiri/{orikkiriId}")
    public String deleteOrikkiri(@PathVariable long orikkiriId) throws Exception {
        orikkiriManageService.deleteOrikkiri(orikkiriId);
        return "{\"success\": true}";
    }

    @PostMapping("/addAnsQuestion")
    public String addAnsQuestion(@RequestBody AnsQuestionDTO ansQuestionDTO) throws Exception {
        orikkiriManageService.addAnsQuestion(ansQuestionDTO);
        return "{\"success\": true}";
    }

    @PostMapping("/updateAnsQuestion")
    public String updateAnsQuestion(@RequestBody AnsQuestionDTO ansQuestionDTO) throws Exception {
        orikkiriManageService.updateAnsQuestion(ansQuestionDTO);
        return "{\"success\": true}";
    }

    @GetMapping("/getAnsQuestion/{ansQuestionId}")
    public AnsQuestionDTO getAnsQuestion(@PathVariable long ansQuestionId) throws Exception {
        return orikkiriManageService.getAnsQuestion(ansQuestionId);
    }

    @GetMapping("/getAnsQuestionList/{orikkiriId}")
    public List<AnsQuestionDTO> getAnsQuestionList(@PathVariable long orikkiriId, SearchDTO searchDTO) throws Exception {
        return orikkiriManageService.getAnsQuestionList(orikkiriId, searchDTO);
    }

    @GetMapping("/deleteAnsQuestion/{ansQuestionId}")
    public String deleteAnsQuestion(@PathVariable long ansQuestionId) throws Exception {
        orikkiriManageService.deleteAnsQuestion(ansQuestionId);
        return "{\"success\": true}";
    }


}
