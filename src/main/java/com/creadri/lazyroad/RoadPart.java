package com.creadri.lazyroad;

public class RoadPart implements Comparable<RoadPart> {
    private String[][] blockDatas;
    private int height;
    private int width;

    private int groundLayer;
    private int repeatEvery;

    public RoadPart() {}

    public RoadPart(int height, int width) {
        blockDatas = new String[height][width];
        this.height = height;
        this.width = width;
    }

    public boolean isToBuild(int count, int maxSequence) {
        return ((count % maxSequence) % repeatEvery) == 0;
    }

    public boolean isToBuild(int count) {
        return count % repeatEvery == 0;
    }

    public String[][] getBlockDatas() {
        return blockDatas;
    }

    public void setBlockDatas(String[][] blockDatas) {
        this.blockDatas = blockDatas;
    }

    public int getGroundLayer() {
        return groundLayer;
    }

    public void setGroundLayer(int groundLayer) {
        this.groundLayer = groundLayer;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getRepeatEvery() {
        return repeatEvery;
    }

    public void setRepeatEvery(int repeatEvery) {
        this.repeatEvery = repeatEvery;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int compareTo(RoadPart o) {
        if (repeatEvery > o.repeatEvery) {
            return -1;
        } else if (repeatEvery < o.repeatEvery) {
            return 1;
        }
        return 0;
    }
}