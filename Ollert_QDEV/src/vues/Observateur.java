package vues;

import donnees.Colonne;

import java.io.Serializable;
import java.util.List;

public interface Observateur extends Serializable {
    void actualiser();
}
