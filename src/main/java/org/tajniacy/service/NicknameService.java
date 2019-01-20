package org.tajniacy.service;

import org.tajniacy.exception.NicknameNotFoundException;
import org.tajniacy.model.Nickname;
import java.util.List;

public interface NicknameService {

    Nickname getNextFreeNickname() throws NicknameNotFoundException;

    void setNicknameIsFree(Long id, boolean value) throws NicknameNotFoundException;

    List<Nickname> getAllNicknames();

    List<Nickname> getAllUsedNicknames();

}
