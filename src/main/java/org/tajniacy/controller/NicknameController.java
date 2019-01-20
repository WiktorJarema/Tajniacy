package org.tajniacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tajniacy.model.Nickname;
import org.tajniacy.service.NicknameService;

import java.util.List;

@Controller
@RestController
public class NicknameController {

    @Autowired
    private NicknameService nicknameService;

    @GetMapping(path = "/nicknames")
    public List<Nickname> getAllNicknames() {
        return nicknameService.getAllNicknames();
    }

    @GetMapping(path = "/usednicknames")
    public List<Nickname> getAllUsedNicknames() {
        return nicknameService.getAllUsedNicknames();
    }

}
