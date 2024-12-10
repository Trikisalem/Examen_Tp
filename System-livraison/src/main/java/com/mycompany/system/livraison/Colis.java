package com.mycompany.system.livraison;
    public class Colis {
        private final String id;
        private final String expediteur;
        private final String destinataire;
        private String etat;

        public Colis(String id, String expediteur, String destinataire, String en_attente) {
            this.id = id;
            this.expediteur = expediteur;
            this.destinataire = destinataire;
            this.etat = "En attente";
        }

        public String getId() {
            return id;
        }

        public String getExpediteur() {
            return expediteur;
        }

        public String getDestinataire() {
            return destinataire;
        }

        public String getEtat() {
            return etat;
        }

        public void setEtat(String etat) {
            this.etat = etat;
        }

    void setLocalisation(String en_route) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    }
