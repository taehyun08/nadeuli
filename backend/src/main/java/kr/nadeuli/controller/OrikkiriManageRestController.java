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
@RequestMapping("/orikkirimanage")
@RequiredArgsConstructor
@Log4j2
public class OrikkiriManageRestController {

    private final OrikkiriManageService orikkiriManageService;

    @PostMapping("/addOrikkiri")
    public void addOrikkiri(@RequestBody OrikkiriDTO orikkiriDTO) throws Exception {
        orikkiriManageService.addOrikkiri(orikkiriDTO);
    }

    @PutMapping("/updateOrikkiri")
    public void updateOrikkiri(@RequestBody OrikkiriDTO orikkiriDTO) throws Exception {
        orikkiriManageService.updateOrikkiri(orikkiriDTO);
    }

    @GetMapping("/getOrikkiri/{orikkiriId}")
    public OrikkiriDTO getOrikkiri(@PathVariable long orikkiriId) throws Exception {
        return orikkiriManageService.getOrikkiri(orikkiriId);
    }

    @DeleteMapping("/deleteOrikkiri/{orikkiriId}")
    public void deleteOrikkiri(@PathVariable long orikkiriId) throws Exception {
        orikkiriManageService.deleteOrikkiri(orikkiriId);
    }

    @PostMapping("/addAnsQuestion")
    public void addAnsQuestion(@RequestBody AnsQuestionDTO ansQuestionDTO) throws Exception {
        orikkiriManageService.addAnsQuestion(ansQuestionDTO);
    }

    @PutMapping("/updateAnsQuestion")
    public void updateAnsQuestion(@RequestBody AnsQuestionDTO ansQuestionDTO) throws Exception {
        orikkiriManageService.updateAnsQuestion(ansQuestionDTO);
    }

    @GetMapping("/getAnsQuestion/{ansQuestionId}")
    public AnsQuestionDTO getAnsQuestion(@PathVariable long ansQuestionId) throws Exception {
        return orikkiriManageService.getAnsQuestion(ansQuestionId);
    }

    @GetMapping("/getAnsQuestionList/{orikkiriId}")
    public List<AnsQuestionDTO> getAnsQuestionList(@PathVariable long orikkiriId, SearchDTO searchDTO) throws Exception {
        return orikkiriManageService.getAnsQuestionList(orikkiriId, searchDTO);
    }

    @DeleteMapping("/deleteAnsQuestion/{ansQuestionId}")
    public void deleteAnsQuestion(@PathVariable long ansQuestionId) throws Exception {
        orikkiriManageService.deleteAnsQuestion(ansQuestionId);
    }



}
