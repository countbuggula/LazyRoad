package com.creadri.lazyroad;

import java.util.ArrayList;
import java.util.Collections;

public class Road {
    private ArrayList<RoadPart> parts;
    private int maxGradient;
    private RoadPart stairs;
    private int maxSequence;

    public Road() {
        this.parts = new ArrayList<>();
    }
    
    public Road(int partsSize) {
        this.parts = new ArrayList<>(partsSize);
    }

    public int size() {
        return parts.size();
    }
    
    public RoadPart getRoadPart(int index) {
        return parts.get(index);
    }
    
    public void setRoadPart(int index, RoadPart part) {
        parts.set(index, part);
        Collections.sort(parts);
        maxSequence = parts.get(0).getRepeatEvery();
    }
    
    public void removeRoadPart(RoadPart part) {
        parts.remove(part);
    }
    
    public boolean addRoadPart(RoadPart part) {
        int index = Collections.binarySearch(parts, part);
        if (index >= 0) {
            return false;
        }
        parts.add(part);
        Collections.sort(parts);
        maxSequence = parts.get(0).getRepeatEvery();
        return true;
    }

    public int getStartIndex() {
        if (parts.isEmpty()) return 0;
        
        RoadPart targetPart = null;
        for (RoadPart p : parts) {
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
            // (0 + offset) % seq + 1 == targetStep => offset = (targetStep - 1) % seq
            return (targetStep - 1 + seq) % seq;
        }

        // If 1:1 part-per-step sequence (parts.size() == maxSequence)
        if (parts.size() == maxSequence && maxSequence > 0) {
            return parts.indexOf(targetPart);
        }

        // Modulo solver
        for (int offset = 0; offset < seq; offset++) {
            for (RoadPart p : parts) {
                if (p.isToBuild(offset, seq)) {
                    if (p == targetPart) {
                        return offset;
                    }
                    break;
                }
            }
        }
        return 0;
    }

    public RoadPart getRoadPartToBuild(int count) {
        if (parts.isEmpty()) return null;
        int seq = maxSequence > 0 ? maxSequence : 1;
        int startOffset = getStartIndex();
        int step = ((count + startOffset) % seq) + 1; // 1-indexed step in [1, seq]

        // 1. Check for parts explicitly assigned to this step via triggerPositions
        for (RoadPart part : parts) {
            java.util.List<Integer> triggers = part.getTriggerPositions();
            if (triggers != null && !triggers.isEmpty()) {
                if (triggers.contains(step)) {
                    return part;
                }
            }
        }

        // 2. Fall back to repeatEvery evaluation
        int effectiveCount = count + startOffset;
        for (RoadPart part : parts) {
            java.util.List<Integer> triggers = part.getTriggerPositions();
            if (triggers == null || triggers.isEmpty()) {
                if (part.isToBuild(effectiveCount, seq)) {
                    return part;
                }
            }
        }
        return null;
    }

    public int getMaxGradient() {
        return maxGradient;
    }

    public void setMaxGradient(int maxGradient) {
        this.maxGradient = maxGradient;
    }

    public RoadPart getStairs() {
        return stairs;
    }

    public void setStairs(RoadPart stairs) {
        this.stairs = stairs;
    }

    public int getMaxSequence() {
        return maxSequence;
    }
    
    public void setMaxSequence(int maxSequence) {
        this.maxSequence = maxSequence;
    }

    public ArrayList<RoadPart> getParts() {
        return parts;
    }

    public void setParts(ArrayList<RoadPart> parts) {
        this.parts = parts;
    }
}