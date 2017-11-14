package project.service;

import project.entity.GameType;
import project.form.IndexForm;

import java.util.List;

public interface IndexService {
    List<GameType> find(IndexForm indexForm);

    List<GameType> findAll();
}
