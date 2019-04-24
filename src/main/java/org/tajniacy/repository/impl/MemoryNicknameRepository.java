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
        nicknames.add(new Nickname(1L, "Jason Bourne"));
        nicknames.add(new Nickname(2L, "Nikita"));
        nicknames.add(new Nickname(3L, "Mr. Robot"));
        nicknames.add(new Nickname(4L, "James Bond"));
        nicknames.add(new Nickname(5L, "Darlene"));
        nicknames.add(new Nickname(6L, "Dr. Watson"));
        nicknames.add(new Nickname(7L, "Dark Knight"));
        nicknames.add(new Nickname(8L, "Wonder Woman"));
        nicknames.add(new Nickname(9L, "Ronin"));
        nicknames.add(new Nickname(10L, "Iron Man"));
        nicknames.add(new Nickname(11L, "Optimus Prime"));
        nicknames.add(new Nickname(12L, "Carmen"));
        nicknames.add(new Nickname(13L, "Spiderman"));
        nicknames.add(new Nickname(14L, "Robin"));
        nicknames.add(new Nickname(15L, "Sherlock"));
        nicknames.add(new Nickname(16L, "He-Man"));
        nicknames.add(new Nickname(17L, "Gen. Kukliński"));
        nicknames.add(new Nickname(18L, "Bumblebee"));
        nicknames.add(new Nickname(19L, "Deadpool"));
        nicknames.add(new Nickname(20L, "Ulysses"));

        nicknames.add(new Nickname(21L, "Piotr"));
        nicknames.add(new Nickname(22L, "Paweł"));
        nicknames.add(new Nickname(23L, "Monika"));
        nicknames.add(new Nickname(24L, "Janusz"));
        nicknames.add(new Nickname(25L, "Karolina"));
        nicknames.add(new Nickname(26L, "Wacław"));
        nicknames.add(new Nickname(27L, "Zenek"));
        nicknames.add(new Nickname(28L, "Karol"));
        nicknames.add(new Nickname(29L, "Agnieszka"));
        nicknames.add(new Nickname(30L, "Ewa"));
        nicknames.add(new Nickname(31L, "Tomasz"));
        nicknames.add(new Nickname(32L, "Krzysztof"));
        nicknames.add(new Nickname(33L, "Jan"));
        nicknames.add(new Nickname(34L, "Robert"));
        nicknames.add(new Nickname(35L, "Ania"));
        nicknames.add(new Nickname(36L, "Mateusz"));
        nicknames.add(new Nickname(37L, "Kamil"));
        nicknames.add(new Nickname(38L, "Basia"));
        nicknames.add(new Nickname(39L, "Roman"));
        nicknames.add(new Nickname(40L, "Łukasz"));
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
