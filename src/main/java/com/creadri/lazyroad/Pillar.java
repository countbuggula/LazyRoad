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
        if (parts.isEmpty()) return 0;

        PillarPart targetPart = null;
        for (PillarPart p : parts) {
            if (p.isStartHere()) {
                targetPart = p;
                break;
            }
        }

        if (targetPart == null) {
            return 0;
        }

        int seq = maxSequence > 0 ? maxSequence : 1;

        // If targetPart defines trigger positions, solve for offset where targetPart triggers at count 0
        if (targetPart.getTriggerPositions() != null && !targetPart.getTriggerPositions().isEmpty()) {
            int targetStep = targetPart.getTriggerPositions().get(0); // 1-indexed
            return (targetStep - 1 + seq) % seq;
        }

        // If 1:1 part-per-step sequence (parts.size() == maxSequence)
        if (parts.size() == maxSequence && maxSequence > 0) {
            return parts.indexOf(targetPart);
        }

        // Modulo solver
        for (int offset = 0; offset < seq; offset++) {
            for (PillarPart p : parts) {
                int re = p.getRepeatEvery();
                if (re > 0 && (offset % re) == 0) {
                    if (p == targetPart) {
                        return offset;
                    }
                    break;
                }
            }
        }

        return parts.indexOf(targetPart);
    }

    public PillarPart getRoadPartToBuild(int count) {
        if (parts.isEmpty()) return null;
        int seq = maxSequence > 0 ? maxSequence : 1;
        int startOffset = getStartIndex();
        int step = ((count + startOffset) % seq) + 1; // 1-indexed step in [1, seq]

        // 1. Check for parts explicitly assigned to this step via triggerPositions
        for (PillarPart part : parts) {
            java.util.List<Integer> triggers = part.getTriggerPositions();
            if (triggers != null && !triggers.isEmpty()) {
                if (triggers.contains(step)) {
                    return part;
                }
            }
        }

        // 2. If template defines a 1:1 part-per-step sequence (like BigBridge with 22 parts and maxSequence 22)
        // and no trigger positions are used, preserve direct 1:1 slot mapping
        if (parts.size() == maxSequence && maxSequence > 0) {
            int index = (count + startOffset) % maxSequence;
            return parts.get(index);
        }

        // 3. Fall back to repeatEvery evaluation
        int effectiveCount = count + startOffset;
        for (PillarPart part : parts) {
            java.util.List<Integer> triggers = part.getTriggerPositions();
            if (triggers == null || triggers.isEmpty()) {
                int re = part.getRepeatEvery();
                if (re > 0 && (effectiveCount % re) == 0) {
                    return part;
                }
            }
        }
        return null;
    }
}