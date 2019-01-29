package org.tajniacy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tajniacy.exception.NicknameNotFoundException;
import org.tajniacy.model.Nickname;
import org.tajniacy.repository.NicknameRepository;
import org.tajniacy.service.NicknameService;

import java.util.List;

@Service
public class NicknameServiceImpl implements NicknameService {

    @Autowired
    private NicknameRepository nicknameRepository;

    @Override
    public Nickname getNextFreeNickname() throws NicknameNotFoundException {
        Long i = 1L;
        boolean keepWorking = true;

        while (keepWorking) {
            if (nicknameRepository.checkIfNicknameIsFree(i)) {
                break;
            }
            i++;
        }
        nicknameRepository.setNicknameIsFree(i, false);
        return nicknameRepository.findNicknameById(i);
    }

    @Override
    public void setNicknameIsFree(Long id, boolean value) throws NicknameNotFoundException {
        System.out.println("Service, setNicknameIsFree");
        nicknameRepository.setNicknameIsFree(id, value);
    }

    @Override
    public List<Nickname> getAllNicknames() {
        return nicknameRepository.getAllNicknames();
    }

    @Override
    public List<Nickname> getAllUsedNicknames() {
        return nicknameRepository.getAllUsedNicknames();
    }

    @Override
    public Nickname getNicknameById(Long id) throws NicknameNotFoundException {
        return nicknameRepository.findNicknameById(id);
    }
}
