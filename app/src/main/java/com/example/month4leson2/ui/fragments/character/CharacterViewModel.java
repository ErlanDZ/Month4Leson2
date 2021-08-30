package com.example.month4leson2.ui.fragments.character;

import androidx.lifecycle.MutableLiveData;

import com.example.month4leson2.base.BaseViewModel;
import com.example.month4leson2.data.repositories.CharacterRepository;
import com.example.month4leson2.model.Character;
import com.example.month4leson2.model.RickAndMortyResponse;

import java.util.List;

public class CharacterViewModel extends BaseViewModel {

    private final CharacterRepository characterRepository = new CharacterRepository();
    public int page = 1;

    public MutableLiveData<RickAndMortyResponse<Character>> techCharacters() {
            return characterRepository.techCharacters(page);
    }

    public MutableLiveData<Character> fetchCharacter(int id) {
        return characterRepository.fetchCharacter(id);
    }

    List<Character> getCharacters() {
        return characterRepository.getCharacters();
    }
}
