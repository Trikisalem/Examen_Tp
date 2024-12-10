/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.system.livraison;

/**
 *
 * @author user
 */
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class GestionLivraison {
    public Queue<Colis> colisQueue;
    private Semaphore semaphore;

    public GestionLivraison() {
        colisQueue = new LinkedList<>();
        semaphore = new Semaphore(1);  // Semaphor pour synchroniser l'accès
    }

    public void enregistrerColis(Colis colis) {
        try {
            semaphore.acquire();  // Verrouille la ressource
            colisQueue.add(colis);
            System.out.println("Colis enregistré: " + colis.getId());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release();  // Libère la ressource
        }
    }

    public Colis livrerColis() {
        Colis colis = null;
        try {
            semaphore.acquire();
            if (!colisQueue.isEmpty()) {
                colis = colisQueue.poll();
                colis.setEtat("en transit");
                colis.setLocalisation("En route");
                System.out.println("Colis " + colis.getId() + " est en transit.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release();
        }
        return colis;
    }

    public void marquerLivraison(Colis colis) {
        try {
            semaphore.acquire();
            if (colis != null) {
                colis.setEtat("livré");
                colis.setLocalisation("Livré");
                System.out.println("Colis " + colis.getId() + " est livré.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release();
        }
    }
}

