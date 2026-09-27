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

    public int getStartIndex() {
        for (int i = 0; i < parts.size(); i++) {
            if (parts.get(i).isStartHere()) {
                return i;
            }
        }
        return 0;
    }

    public PillarPart getRoadPartToBuild(int count) {
        if (parts.isEmpty()) return null;

        // If template defines a 1:1 part-per-step sequence (like BigBridge with 22 parts and maxSequence 22),
        // each part corresponds directly to a step in the sequence.
        if (parts.size() == maxSequence && maxSequence > 0) {
            int index = (count + getStartIndex()) % maxSequence;
            return parts.get(index);
        }

        // Modulo pattern matching based on each part's repeatEvery:
        // Evaluates parts in order (or offset from Start Here), returning the first part whose repeatEvery triggers.
        int startOffset = getStartIndex();
        int effectiveCount = count + startOffset;
        for (int i = 0; i < parts.size(); i++) {
            PillarPart part = parts.get(i);
            int re = part.getRepeatEvery();
            if (re > 0 && (effectiveCount % re) == 0) {
                return part;
            }
        }
        return null;
    }
}