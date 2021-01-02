package com.flys.bible.architecture.custom;

import com.flys.bible.architecture.core.AbstractSession;
import com.flys.bible.entities.Chapitre;
import com.flys.bible.entities.DailyVerset;
import com.flys.bible.entities.Verset;

import java.util.Collection;
import java.util.List;

public class Session extends AbstractSession {
    // données à partager entre fragments eux-mêmes et entre fragments et activité
    // les éléments qui ne peuvent être sérialisés en jSON doivent avoir l'annotation @JsonIgnore
    // ne pas oublier les getters et setters nécessaires pour la sérialisation / désérialisation jSON
    private boolean installed;

    private List<Chapitre> chapitres;

    private Collection<Verset> versets;

    private List<DailyVerset> dailyVersets;

    public boolean isInstalled() {
        return installed;
    }

    public void setInstalled(boolean installed) {
        this.installed = installed;
    }

    public List<Chapitre> getChapitres() {
        return chapitres;
    }

    public void setChapitres(List<Chapitre> chapitres) {
        this.chapitres = chapitres;
    }

    public Collection<Verset> getVersets() {
        return versets;
    }

    public void setVersets(Collection<Verset> versets) {
        this.versets = versets;
    }

    public List<DailyVerset> getDailyVersets() {
        return dailyVersets;
    }

    public void setDailyVersets(List<DailyVerset> dailyVersets) {
        this.dailyVersets = dailyVersets;
    }
    public void addDailyVerset(DailyVerset dailyVerset){
        this.dailyVersets.add(dailyVerset);
    }
}
