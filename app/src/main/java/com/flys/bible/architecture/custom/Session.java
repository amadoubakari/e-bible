package com.flys.bible.architecture.custom;

import com.flys.bible.architecture.core.AbstractSession;

public class Session extends AbstractSession {
  // données à partager entre fragments eux-mêmes et entre fragments et activité
  // les éléments qui ne peuvent être sérialisés en jSON doivent avoir l'annotation @JsonIgnore
  // ne pas oublier les getters et setters nécessaires pour la sérialisation / désérialisation jSON
    private boolean installed;

    public boolean isInstalled() {
        return installed;
    }

    public void setInstalled(boolean installed) {
        this.installed = installed;
    }
}
