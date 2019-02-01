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
        nicknames.add(new Nickname(1L, "Dark Knight"));
        nicknames.add(new Nickname(2L, "Nikita"));
        nicknames.add(new Nickname(3L, "Mr. Robot"));
        nicknames.add(new Nickname(4L, "James Bond"));
        nicknames.add(new Nickname(5L, "Darlene"));
        nicknames.add(new Nickname(6L, "Jason Bourne"));
        nicknames.add(new Nickname(7L, "Ronin"));
        nicknames.add(new Nickname(8L, "Kukliński"));
        nicknames.add(new Nickname(9L, "Carmen"));
    }

    @Override
    public Nickname findNicknameById(Long id) throws NicknameNotFoundException {
        for (int i = 0; i < nicknames.size() - 1; i++) {
//            System.out.println("id" + id);
//            System.out.println(nicknames.get(i).getId());
//            System.out.println(nicknames.get(i).getName());
            if (nicknames.get(i).getId().compareTo(id) ==  0) {
//                System.out.println("to jest " + nicknames.get(i).getName());
                return nicknames.get(i);
            }
//            System.out.println();
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
        System.out.println("repository, setNicknameIsFree used");
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
