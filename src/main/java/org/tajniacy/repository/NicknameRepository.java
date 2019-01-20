package org.tajniacy.repository;

import org.tajniacy.exception.NicknameNotFoundException;
import org.tajniacy.model.Nickname;

import java.util.List;

public interface NicknameRepository {

    Nickname findNicknameById(Long id) throws NicknameNotFoundException;

    boolean checkIfNicknameIsFree(Long id) throws NicknameNotFoundException;

    void setNicknameIsFree(Long id, boolean value) throws NicknameNotFoundException;

    List<Nickname> getAllNicknames();

    List<Nickname> getAllUsedNicknames();

}
