package org.tajniacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.tajniacy.exception.NicknameNotFoundException;
import org.tajniacy.model.Nickname;
import org.tajniacy.service.NicknameService;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class NicknameRestController {

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

    @GetMapping(path = "/currentnickname")
    public Nickname getAllNicknames(HttpSession session) {

        Object nicknameObject = session.getAttribute("nickname");
        if (nicknameObject != null) {
            return (Nickname) nicknameObject;
        } else {
            return null;
        }

    }

    @GetMapping(path = "/nicknames/{id:\\d+}")
    public Nickname getNickname(@PathVariable(name = "id") Long id) throws NicknameNotFoundException {

        return nicknameService.getNicknameById(id);

    }



}
