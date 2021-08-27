package com.example.month4leson2.ui.fragments.character;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.month4leson2.data.repositories.CharacterRepository;
import com.example.month4leson2.model.Character;
import com.example.month4leson2.model.RickAndMortyResponse;

import java.util.List;

public class CharacterViewModel extends ViewModel {

    private final CharacterRepository characterRepository = new CharacterRepository();

   public MutableLiveData<RickAndMortyResponse<Character>> techCharacters() {
        return characterRepository.techCharacters();
    }

  public   MutableLiveData<Character> fetchCharacter(int id) {
        return characterRepository.fetchCharacter(id);
    }

    List<Character> getCharacters() {
        return characterRepository.getCharacters();
    }
}
