/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.system.livraison;

/**
 *
 * @author user
 */
public class Livreur extends Thread {
    private GestionLivraison gestionLivraison;

    public Livreur(GestionLivraison gestionLivraison) {
        this.gestionLivraison = gestionLivraison;
    }

    @Override
    public void run() {
        while (true) {
            Colis colis = gestionLivraison.livrerColis();
            if (colis != null) {
                try {
                    Thread.sleep(2000);  // Simule le temps de livraison
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                gestionLivraison.marquerLivraison(colis);
            }
        }
    }
}

