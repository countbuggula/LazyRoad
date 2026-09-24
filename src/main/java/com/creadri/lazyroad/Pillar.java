package com.creadri.lazyroad;

import java.util.ArrayList;

public class Pillar {

    private ArrayList<PillarPart> parts;
    private int maxSequence;

    public Pillar() {
        this.parts = new ArrayList<>();
    }

    public Pillar(int partsSize) {
        this.parts = new ArrayList<>(partsSize);
    }

    public int size() {
        return parts.size();
    }

    public PillarPart getPillarPart(int index) {
        return parts.get(index);
    }

    public void setPillarPart(int index, PillarPart part) {
        parts.set(index, part);
    }

    public boolean addPillarPart(PillarPart part) {
        parts.add(part);
        return true;
    }

    public void removePillarPart(PillarPart part) {
        parts.remove(part);
    }

    public int getMaxSequence() {
        return maxSequence;
    }

    public void setMaxSequence(int maxSequence) {
        this.maxSequence = maxSequence;
    }

    public ArrayList<PillarPart> getParts() {
        return parts;
    }

    public void setParts(ArrayList<PillarPart> parts) {
        this.parts = parts;
    }

    public PillarPart getRoadPartToBuild(int count) {
        if (parts.isEmpty()) return null;
        if (maxSequence == 0) return parts.get(0);
        return parts.get(count % maxSequence);
    }
}