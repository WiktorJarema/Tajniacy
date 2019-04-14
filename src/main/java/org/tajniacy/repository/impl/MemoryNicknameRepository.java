package org.tajniacy.repository.impl;

import org.springframework.stereotype.Repository;
import org.tajniacy.exception.NicknameNotFoundException;
import org.tajniacy.model.Nickname;
import org.tajniacy.repository.NicknameRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemoryNicknameRepository implements NicknameRepository {

    private List<Nickname> nicknames;

    public MemoryNicknameRepository() {
        nicknames = new ArrayList<>();
        nicknames.add(new Nickname(1L, "1_Jason Bourne"));
        nicknames.add(new Nickname(2L, "2_Nikita"));
        nicknames.add(new Nickname(3L, "3_Mr. Robot"));
        nicknames.add(new Nickname(4L, "4_James Bond"));
        nicknames.add(new Nickname(5L, "5_Darlene"));
        nicknames.add(new Nickname(6L, "6_Dark Knight"));
        nicknames.add(new Nickname(7L, "7_Ronin"));
        nicknames.add(new Nickname(8L, "8_Kukliński"));
        nicknames.add(new Nickname(9L, "9_Carmen"));
    }

    @Override
    public Nickname findNicknameById(Long id) throws NicknameNotFoundException {
        for (int i = 0; i < nicknames.size() - 1; i++) {

            if (nicknames.get(i).getId().compareTo(id) ==  0) {
                return nicknames.get(i);
            }

        }
        throw new NicknameNotFoundException();
    }

    @Override
    public boolean checkIfNicknameIsFree(Long id) throws NicknameNotFoundException {
        return findNicknameById(id).getIsFree();
    }

    @Override
    public void setNicknameIsFree(Long id, boolean value) throws NicknameNotFoundException{
        findNicknameById(id).setIsFree(value);
    }

    @Override
    public List<Nickname> getAllNicknames() {
        return nicknames;
    }

    @Override
    public List<Nickname> getAllUsedNicknames() {
        ArrayList<Nickname> allUsedNicknames = new ArrayList<>();
        for (int i = 0; i < nicknames.size(); i++) {
            if (nicknames.get(i).getIsFree() == false) {
                allUsedNicknames.add(nicknames.get(i));
            }
        }
        return allUsedNicknames;
    }

}
