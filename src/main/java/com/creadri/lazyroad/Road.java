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

    public RoadPart getRoadPartToBuild(int count) {
        for (RoadPart part : parts) {
            if (part.isToBuild(count, maxSequence)) {
                return part;
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