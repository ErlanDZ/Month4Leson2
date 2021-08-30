package com.example.month4leson2.ui.fragments.episode;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.month4leson2.App;
import com.example.month4leson2.base.BaseFragment;
import com.example.month4leson2.base.BaseViewModel;
import com.example.month4leson2.data.repositories.CharacterRepository;
import com.example.month4leson2.data.repositories.EpisodeRepository;
import com.example.month4leson2.model.Character;
import com.example.month4leson2.model.EpisodeModel;
import com.example.month4leson2.model.RickAndMortyResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodeViewModel extends BaseViewModel {

    public int page = 1;
    private final EpisodeRepository episodeRepository = new EpisodeRepository();

    public MutableLiveData<RickAndMortyResponse<EpisodeModel>> fetchEpisodes() {
        return episodeRepository.fetchEpisodes(page);
    }

    public   MutableLiveData<EpisodeModel> fetchEpisode(int id) {
        return episodeRepository.fetchEpisode(id);
    }

    List<EpisodeModel> getEpisode() {
        return episodeRepository.getEpisode();
    }
}
