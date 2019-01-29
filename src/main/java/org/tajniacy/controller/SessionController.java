//package org.tajniacy.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.tajniacy.exception.NicknameNotFoundException;
//import org.tajniacy.model.Nickname;
//import org.tajniacy.service.NicknameService;
//
//import javax.servlet.http.HttpSession;
//
//@Controller
//public class SessionController {
//
//    @Autowired
//    NicknameService nicknameService;
//
//
//    @GetMapping(path = "/new", produces = "text/html; charset=UTF-8")
//    @ResponseBody
//    public String setNewNickname(HttpSession session) throws NicknameNotFoundException {
//
//        Object nicknameObj = session.getAttribute("nickname");
//        if (nicknameObj == null) {
//            Nickname newNickname = nicknameService.getNextFreeNickname();
//            session.setAttribute("nickname", newNickname);
//            session.setMaxInactiveInterval(5);
//
//            return "Cześć, to będzie Twój nick: " + newNickname.getName();
//        } else {
//            Nickname usedNickname = (Nickname) nicknameObj;
//            return "A my się już znamy, cześć " + usedNickname.getName();
//        }
//
//    }
//
//}
