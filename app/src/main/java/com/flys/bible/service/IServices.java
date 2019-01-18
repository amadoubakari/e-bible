package com.flys.bible.service;

import com.flys.bible.entities.Chapitre;
import com.flys.bible.entities.Titre;

import java.io.Serializable;
import java.util.Collection;

public interface IServices extends Serializable {
    public Collection<Titre> getTitlesOfChapter(Chapitre chapitre);
}
