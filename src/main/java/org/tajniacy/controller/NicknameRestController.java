package org.tajniacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.tajniacy.exception.NicknameNotFoundException;
import org.tajniacy.listener.NicknameSessionListener;
import org.tajniacy.model.Nickname;
import org.tajniacy.service.NicknameService;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.transaction.Transactional;
import java.util.List;

@CrossOrigin
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

    @CrossOrigin
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

    @CrossOrigin
    @PatchMapping(path = "/resetsessiontimeout")
    public String resetSessionTimeout(HttpSession session) {

        Object nicknameObject = session.getAttribute("nickname");
        if (nicknameObject != null) {
            session.setMaxInactiveInterval(10);
            Nickname usedNickname = (Nickname) nicknameObject;
            System.out.println("Reset MaxInactiveInterval dla: " + usedNickname.getName());
            return "Reset MaxInactiveInterval ok";
        }
        System.out.println("Nie udało się zresetować Reset MaxInactiveInterval");

        return "Błąd resetu MaxInactiveInterval";
    }

    @GetMapping(path = "/numberofusersonline")
    public String getNumberOfUsersOnline() {
        return String.valueOf(nicknameService.getAllUsedNicknames().size());
    }

    @GetMapping(path = "/listener-numberofusersonline")
    public String getNumberOfUsersOnlineFromSessionListener() {
        return NicknameSessionListener.getActiveSessions().toString();
    }

}
